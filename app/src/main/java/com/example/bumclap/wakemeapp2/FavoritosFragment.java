package com.example.bumclap.wakemeapp2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PC on 12/03/2015.
 */
public class FavoritosFragment extends Fragment {

    public FavoritosFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View rootView= inflater.inflate(R.layout.favoritos, container, false);
        return  rootView;
    }
}