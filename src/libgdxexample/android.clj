(ns libgdxexample.android
  (:require [libgdxexample.core])
  (:import [com.badlogic.gdx ApplicationListener])
  (:gen-class
   :implements [com.badlogic.gdx.ApplicationListener]
   :methods [#^{:static true} [app [] com.badlogic.gdx.ApplicationListener]]))


(defn -app []
  "java entry point calls internal game-loop"
  (libgdxexample.core.Game.))

(defn -main  [])
