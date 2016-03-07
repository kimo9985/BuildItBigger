package com.jimtrinh9985gmail.builditbigger2;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jimtrinh9985gmail.builditbigger2.androidlibrary.ImageActivity;

/**
 * Created by Kimo on 2/29/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button toastButton = (Button) root.findViewById(R.id.toastJoke);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Use Toast to show joke pulled from java lib//
            public void onClick(View v) {
                String javaJokes = new JavaJokes().getJavaJoke();
                Toast.makeText(getActivity(), "derp, " + javaJokes, Toast.LENGTH_SHORT).show();
            }
        });

        Button launchButton = (Button) root.findViewById(R.id.launchJoke);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Use Explicit intent to pass joke from java lib to android lib//
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ImageActivity.class);
                myIntent.putExtra(ImageActivity.KEY, new JavaJokes().getJavaJoke());
                startActivity(myIntent);
            }
        });

        Button freeButton = (Button) root.findViewById(R.id.asyncJoke);
        freeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Use AsyncTask/GCM to launch Joke//
            public void onClick(View v) {
                freeAsyncJoke();
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @SuppressWarnings("unchecked")
    public void freeAsyncJoke() {
        new EndpointsAsyncTask(getActivity()).execute();
    }
}
