(ns mvn-aa-plugin.mojo
  (:import org.apache.commons.lang.WordUtils)
  (:use [clojure.string :only (split-lines)])
  (:require [mvn-aa-plugin.aa :as aa])
  (:gen-class 
  		:name    "mvn_aa_plugin.mojo" 
  		:extends "org.apache.maven.plugin.AbstractMojo"
  		:prefix  "mojo-"
  		:methods [[setMessage  [String] void]
  		          [setShowFile [String] void]]))

;; Class state

(def params (atom {:message "ascii-art maven plugin" :show-file :not-set}))

(defn mojo-setMessage  [this name] (swap! params assoc :message   name))
(defn mojo-setShowFile [this name] (swap! params assoc :show-file name))

(defn- info [this msg]
  (.info (.getLog this) msg))


;; ASCII art fns

(defn- get-ascii-art [file]
  (if (= file :not-set)
    aa/default-message
    (slurp file)))

(defn- wrap [msg]
  (WordUtils/wrap msg 18))
  
(defn- merge-art [ascii msg]
  (let [ascii-lines (count (split-lines ascii))
        msg (wrap msg)
        msg-lines (count (split-lines msg))
        non-overlap-lines (- ascii-lines msg-lines)]
        
    (apply str
      (interpose "\n"
        (map-indexed 
          #(if (<= non-overlap-lines %1)
            (let [msg-line ((split-lines msg) (- %1 non-overlap-lines))]
              (str " " msg-line " " (.substring %2 (+ 2 (count msg-line)))))
            (str %2)) 
          (split-lines ascii))))))


;; M-M-M-MOJO

(defn mojo-execute [this]
  (try
    (let [ascii (get-ascii-art (@params :show-file))
          msg   (@params :message)]
      (info this (str "\n" (merge-art ascii msg) "\n")))
    (catch java.io.FileNotFoundException e
      (.error (.getLog this) aa/error-message) 
      (throw (org.apache.maven.plugin.MojoExecutionException. "arrrgh" e)))))
