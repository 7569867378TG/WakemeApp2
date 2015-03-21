package com.example.bumclap.wakemeapp2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bumclap on 19/2/15.
 */
//FRAGMENT PARA SECCION DE HOMEL
public class HomeFragment extends Fragment{
    public HomeFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View rootView= inflater.inflate(R.layout.home, container, false);
        return  rootView;
    }


}
