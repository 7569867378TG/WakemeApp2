package com.example.bumclap.wakemeapp2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static com.example.bumclap.wakemeapp2.R.layout.support_simple_spinner_dropdown_item;

/**
 * Created by Bumclap on 19/2/15.
 */
//FRAGMENT PARA SECCION DE PERFIL
public class LineasFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private String linea;
    private String [] paradas;
    private Spinner spnLineas;
    private Spinner spnParadas;
    private Button btnGuardarCambios;
    public LineasFragment(){}
    private View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        rootView= inflater.inflate(R.layout.lineas, container, false);
        spnLineas = (Spinner)rootView.findViewById(R.id.spinnerLines);
        spnParadas =(Spinner)rootView.findViewById(R.id.spinnerParadas);
        btnGuardarCambios = (Button)rootView.findViewById(R.id.BtnGuardarCambios);
        spnLineas.setOnItemSelectedListener(this);
        return  rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==spnLineas.getId()){

            String selected = parent.getItemAtPosition(position).toString();
            String [] currentSpinner;
            ArrayAdapter<String> adapter;
            spnParadas.setClickable(true);
            btnGuardarCambios.setClickable(true);
            if(selected.equals("1")){
                currentSpinner = getResources().getStringArray(R.array.linea1);
            }
            else if(selected.equals("2")){
                currentSpinner = getResources().getStringArray(R.array.linea2);
            }
            else if(selected.equals("3")){
                currentSpinner = getResources().getStringArray(R.array.linea3);
            }
            else{
                currentSpinner = getResources().getStringArray(R.array.default_parada);
                btnGuardarCambios.setClickable(false);
                spnParadas.setClickable(false);
            }

            int a =android.R.layout.simple_spinner_item;
            adapter= new ArrayAdapter<String>(rootView.getContext(),a,currentSpinner);
            spnParadas.setAdapter(adapter);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
