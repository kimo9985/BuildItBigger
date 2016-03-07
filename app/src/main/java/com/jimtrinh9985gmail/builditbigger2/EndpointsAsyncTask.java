package com.jimtrinh9985gmail.builditbigger2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jimtrinh9985gmail.builditbigger2.androidlibrary.ImageActivity;
import com.jimtrinh9985gmail.builditbigger2.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Kimo on 2/29/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;
    private String asyncJoke;

    public EndpointsAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            //publishProgress(values);
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        asyncJoke = result;
        if (result != null) {
            startAsyncJoke();
        } else {
            Toast.makeText(mContext, "Sorry, we are all out of Jokes!", Toast.LENGTH_LONG).show();
        }
    }

    private void startAsyncJoke() {
        Intent intent = new Intent(mContext, ImageActivity.class);
        intent.putExtra(ImageActivity.KEY, asyncJoke);
        mContext.startActivity(intent);
    }
}