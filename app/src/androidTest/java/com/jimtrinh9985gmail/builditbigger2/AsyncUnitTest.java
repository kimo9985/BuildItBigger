package com.jimtrinh9985gmail.builditbigger2;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;

/**
 * Created by Kimo on 3/3/2016.
 */
public class AsyncUnitTest extends AndroidTestCase {

    public void asyncJokeTest() {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(getContext());
        endpointsAsyncTask.execute();

        String result = null;
        try {
            result = endpointsAsyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals("An actual joke here would be funnier", result);
    }
}
