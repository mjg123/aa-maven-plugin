(ns mvn-aa-plugin.mojo
  (:gen-class 
  		:name    "mvn_aa_plugin.mojo" 
  		:extends "org.apache.maven.plugin.AbstractMojo"
  		:prefix  "mojo-"))

(defn- info [this msg]
  (.info (.getLog this) msg))

(defn mojo-execute [this]
  (info this "HELLO BEAUTIFUL WORLD"))
