(defproject libgdx-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["libgdx" "http://libgdx.badlogicgames.com/nightlies/maven/"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.badlogic.gdx/gdx "0.9.9-SNAPSHOT"]
                 [com.badlogic.gdx/gdx-backend-lwjgl "0.9.9-SNAPSHOT"]]
  :main libgdx-example.core)
