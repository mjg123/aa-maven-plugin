(ns mvn-aa-plugin.mojo
  (:require [mvn-aa-plugin.aa :as aa])
  (:gen-class 
  		:name    "mvn_aa_plugin.mojo" 
  		:extends "org.apache.maven.plugin.AbstractMojo"
  		:prefix  "mojo-"
  		:methods [[setMessage  [String] void]
  		          [setShowFile [String] void]]))

(def params (atom {:message "ascii-art maven plugin" :show-file :not-set}))

(defn mojo-setMessage  [this name] (swap! params assoc :message   name))
(defn mojo-setShowFile [this name] (swap! params assoc :show-file name))


(defn- info [this msg]
  (.info (.getLog this) msg))


(defn mojo-execute [this]
  (try
    (let [ascii (aa/get-ascii-art (@params :show-file))
          msg   (@params :message)]
      (info this (str "\n" (aa/merge-art ascii msg) "\n")))
    (catch java.io.FileNotFoundException e
      (.error (.getLog this) aa/error-message) 
      (throw (org.apache.maven.plugin.MojoExecutionException. "arrrgh" e)))))
