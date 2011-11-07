(ns mvn-aa-plugin.mojo

  (:gen-class 
  		:name    "mvn_aa_plugin.mojo" 
  		:extends "org.apache.maven.plugin.AbstractMojo"
  		:prefix  "mojo-"
  		:methods [[setMessage [String] void]])

  (:require [mvn-aa-plugin.aa :as aa]))

(def params (atom {:message "ascii-art maven plugin"}))

(defn- info [this msg]
  (.info (.getLog this) msg))

(defn mojo-setMessage [this name]
  (swap! params assoc :message name))

(defn mojo-execute [this]
  (info this (str aa/default-message "\n\n    " (@params :message) "\n")))
