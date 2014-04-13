# libgdx-example

libgdx clojure example for libgdx 1.0 snapshot

defaults to desktop developement with lein repl or lein run, so specify profile when building jar: `lein with-profile desktop uberjar`

# targeting android

an older but perhaps more detailed explanation found here: 

http://blog.juncoapps.com/2013/07/31/running-libgdx-as-a-clojure-game-in-android/

.

 1. build a vanilla android activity
 2. make sure you build the clojure project with android profile: `lein with-profile android uberjar`. **note**: you can make a symbolic link of your uberjar into your vanilla android project libs directory instead of copying the jar each time.
 3. add the armeabi folders to your libs folder in your android project
 4. add the gdx-backend-android.jar to the libs folder in your android project, you can download this and arm folders from: [libgdx-nightlies][1]
 5. if using an ide, try and right click the jars and add to project, then modify your project properties to include the gdx-backend and the clojure uberjar when compiling.
 6. modify your vanilla android activity to read similarly: 

```java 
import com.badlogic.gdx.backends.android.AndroidApplication;
import libgdxexample.android;

public class MyActivity extends AndroidApplication {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        initialize(android.app());
    }
}
```

## License


Distributed under the Eclipse Public License, the same as Clojure.


  [1]: http://libgdx.badlogicgames.com/nightlies/