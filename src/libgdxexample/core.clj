(ns libgdxexample.core
  (:import (com.badlogic.gdx ApplicationListener Gdx Graphics)
           (com.badlogic.gdx.graphics GL10 Color Camera PerspectiveCamera)
           (com.badlogic.gdx.backends.lwjgl LwjglApplication)
           (com.badlogic.gdx.assets AssetManager)
           (com.badlogic.gdx.graphics.g3d Model ModelBatch ModelInstance)
           (com.badlogic.gdx.graphics.g3d.lights Lights DirectionalLight)
           (com.badlogic.gdx.graphics.g3d.utils CameraInputController))

  (:gen-class))
 
 
(declare cam,camcontrol,lights,AM,MI,MB)
(def assetloaded false)
 
 
(defn display []
  (.update camcontrol)
 
  (doto (Gdx/gl)
    (.glViewport 0,0,(.getWidth Gdx/graphics),(.getHeight Gdx/graphics))
    (.glClear GL10/GL_COLOR_BUFFER_BIT)
    (.glClear GL10/GL_DEPTH_BUFFER_BIT))
 
  (.begin MB cam)
    (doto MB (.render MI lights))
  (.end MB)
  )
 
(defn app-listener []
  (proxy [ApplicationListener] []
    (resize [w h] )
    (create []
 
      (def lights (Lights. 0.4 0.4 0.4))
      (doto lights (.add  (.set (DirectionalLight.) 0.8 0.8 0.8 -1 0.8 -0.2)))
 
      (def cam (new PerspectiveCamera 67 800 600))
      (doto (.position cam) (.set 1 1 1))
      (.lookAt cam 0 0 0)
      (.update cam)
 
      (def camcontrol (new CameraInputController cam))
      (doto (Gdx/input) (.setInputProcessor camcontrol))
 
      (def MB (new ModelBatch))
      (def AM (new AssetManager))
      (doto AM (.load "resources/ship.obj" Model))
     )
 
    (render []
      (do 
        (if (.update AM)
          (if assetloaded
            (display)
            (do
              (prn "loading!!")
 
              (def MI (new ModelInstance (.get AM "resources/ship.obj" Model)))
 
              (prn "assets loaded!")
              (def assetloaded true))))
 
        ))
    (pause [] )
    (resume [] )
    (dispose [] (.dispose MB)(.dispose AM)) ;should find a way to clear our MI
 ))
 
 
(defn app []
  (LwjglApplication. (app-listener) "Clojure 3d Game" 800 600 false))
 



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (app))
