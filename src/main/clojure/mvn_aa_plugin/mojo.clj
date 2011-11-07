(ns mvn-aa-plugin.mojo
  (:gen-class 
  		:name    "mvn_aa_plugin.mojo" 
  		:extends "org.apache.maven.plugin.AbstractMojo"
  		:prefix  "mojo-")
  (:require [mvn-aa-plugin.aa :as aa]))

(defn- info [this msg]
  (.info (.getLog this) msg))

(defn mojo-execute [this]
  (info this aa/default-message))
