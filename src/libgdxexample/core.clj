(ns libgdxexample.core
  (:import [com.badlogic.gdx ApplicationListener Gdx Graphics Game Screen]
           [com.badlogic.gdx.graphics Color Camera PerspectiveCamera]
           [com.badlogic.gdx.assets AssetManager]
           [com.badlogic.gdx.graphics.g3d Model ModelBatch ModelInstance]
           [com.badlogic.gdx.graphics.g3d.utils CameraInputController]

           [com.badlogic.gdx.graphics Color GL20]
           [com.badlogic.gdx.graphics.g2d BitmapFont]
           [com.badlogic.gdx.scenes.scene2d Stage]
           [com.badlogic.gdx.scenes.scene2d.ui Label Label$LabelStyle])
  (:gen-class
   :name libgdxexample.core.Game
   :extends com.badlogic.gdx.Game))

(def server nil)
(def stage (atom nil))

(defonce fns (ref [])) ;;fns to be eval in gl thread

(defmacro glfn [r fn]
  "adds the fn to the list of fn's which get eval 
  during opengl runtime in the opengl thread"
  `(dosync (ref-set ~r (conj @fns (delay ~fn)))))

(defn do-glfn []
  "gets called repeatedly during update loop, 
  gives gl context outside of thread; see glfn"
  (if-not (empty? @fns)
    (try
     (doall (map deref @fns))
     (dosync (ref-set fns []))
     (catch Exception e (str "exception: " (.getMessage e)) )) ))



(def game-screen
  (proxy [Screen] []
    (show []
      (reset! stage (Stage.))
      (let [style (Label$LabelStyle. (BitmapFont.) (Color. 1 1 1 1))
            label (Label. "Hello world!!!" style)]
        (.addActor @stage label)))
    (render [delta]
      (do-glfn)
      (.glClearColor (Gdx/gl) 0 0 0 0)
      (.glClear (Gdx/gl) GL20/GL_COLOR_BUFFER_BIT)
      (doto @stage
        (.act delta)
        (.draw)))
    (resize [w h])
    (dispose[] (reset! stage nil))
    (hide [])
    (pause [])
    (resume [])))

(defn -create [^Game this]
  (.setScreen this game-screen))


(defn example-glfncall []
  (glfn fns (do
              (reset! stage (Stage.))
              (let [style (Label$LabelStyle. (BitmapFont.) (Color. 1 1 1 1))
                    label (Label. "ahhhh!!" style)]
                (.addActor @stage label)))))
