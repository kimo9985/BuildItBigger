/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.jimtrinh9985gmail.builditbigger2.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jimtrinh9985gmail.builditbigger2.JavaJokes;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.builditbigger2.jimtrinh9985gmail.com",
    ownerName = "backend.builditbigger2.jimtrinh9985gmail.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that tells a joke **/
    @ApiMethod(name = "sayHi")
    public MyBean sayHi() {
        MyBean response = new MyBean();
        String javaJokes = new JavaJokes().getJavaJoke();
        response.setData(javaJokes);

        return response;
    }

}
