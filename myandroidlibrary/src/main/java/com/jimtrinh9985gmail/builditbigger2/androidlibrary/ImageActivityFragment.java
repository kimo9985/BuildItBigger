package com.jimtrinh9985gmail.builditbigger2.androidlibrary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageActivityFragment extends Fragment {

    ProgressBar progressBar;

    public ImageActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_image_activity, container, false);

        Intent myIntent = getActivity().getIntent();
        String javaJoke = myIntent.getStringExtra(ImageActivity.KEY);
        TextView textView = (TextView) root.findViewById(R.id.androidlib_text_view);
        textView.setText(javaJoke);
        return root;
    }

}
