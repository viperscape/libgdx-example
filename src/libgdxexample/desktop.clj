(ns libgdxexample.desktop
  (:require [libgdxexample.core])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication])
  (:gen-class))

(defn -main  []
  (LwjglApplication. (libgdxexample.core.Game.) "libgdxexample" 800 600))
