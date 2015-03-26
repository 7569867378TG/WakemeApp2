package com.example.grupo1.wakemeapp;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;


/**
 * Created by PC on 06/03/2015.
 */
public class AlarmaFragment extends Fragment implements SeekBar.OnSeekBarChangeListener , TextWatcher{
    private View rootView;
    private SeekBar sbDistancia;
    private int distValue;
    EditText etResultDistancia;
    ImageView btnAlarm;
    public AlarmaFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        rootView= inflater.inflate(R.layout.alarma, container, false);
        sbDistancia = (SeekBar) rootView.findViewById(R.id.sbDistancia);
        etResultDistancia = (EditText) rootView.findViewById(R.id.etResultDistancia);
        etResultDistancia.setText(String.valueOf(sbDistancia.getProgress()));
        btnAlarm =(ImageView) rootView.findViewById(R.id.btnAlarm);
        btnAlarm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setSelected(event.getAction()==MotionEvent.ACTION_DOWN);
                return true;
            }
        });
        //btnAlarm.getBackground().setAlpha(0);
        /*btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getBackground().setAlpha(0);
            }
        });*/
        sbDistancia.setOnSeekBarChangeListener(this);
        etResultDistancia.addTextChangedListener(this);
        return  rootView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(fromUser){

            if(seekBar.getId()==sbDistancia.getId()){

                distValue = progress;
                etResultDistancia.setText(String.valueOf(distValue));

            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.toString().length()>0){
            int i= Integer.parseInt(s.toString());
            if(0<=i && i<= sbDistancia.getMax()){

                sbDistancia.setProgress(i);
            }else{
                sbDistancia.setProgress(sbDistancia.getMax());
                etResultDistancia.setText(String.valueOf(sbDistancia.getMax()));
                etResultDistancia.setSelection(etResultDistancia.getText().length());
            }
        }else{
            sbDistancia.setProgress(0);
        }

    }


}
