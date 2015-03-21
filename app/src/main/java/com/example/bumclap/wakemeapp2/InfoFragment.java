package com.example.bumclap.wakemeapp2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PC on 12/03/2015.
 */
public class InfoFragment extends Fragment {

    public InfoFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View rootView= inflater.inflate(R.layout.informacion, container, false);
        return  rootView;
    }
}
