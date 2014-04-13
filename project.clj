(defproject libgdxexample "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["libgdx" "https://oss.sonatype.org/content/repositories/snapshots/"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.badlogicgames.gdx/gdx "1.0-SNAPSHOT"]]
  :aot [libgdxexample.core]
  :target-path "target/%s"
  :profiles {
             :desktop {:dependencies 
                       [[com.badlogicgames.gdx/gdx-backend-lwjgl "1.0-SNAPSHOT"]
                        [com.badlogicgames.gdx/gdx-platform "1.0-SNAPSHOT" 
                         :classifier "natives-desktop"]]
                       :main libgdxexample.desktop
                       :aot [libgdxexample.desktop]}
             :dev [:desktop]
             :android {:main libgdxexample.android
                       :aot [libgdxexample.android]}
             :core {:main libgdxexample.core}})
