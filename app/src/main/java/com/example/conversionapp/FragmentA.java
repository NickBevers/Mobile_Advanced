package com.example.conversionapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentA extends Fragment {

    public interface FragmentAListener{
        void onInputASend(String input);
    }

    private EditText et_celsius;
    private FragmentAListener listener;

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        et_celsius = v.findViewById(R.id.et_celsius);

        v.findViewById(R.id.button_to_fahrenheit).setOnClickListener(bv -> {
            String input = et_celsius.getText().toString();

            //Stuur naar fragment B
            listener.onInputASend(input);
        });


        return v;
    }

    //ontvangt data van buitenaf (bvb in fragment b wordt op knop gedrukt)
    public void updateCelsius(String input){
        et_celsius.setText(input);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener){
            listener = (FragmentAListener)context;
        }
        else{
            throw new RuntimeException(
                    String.format("%s must implement FragmentListener", context.toString())
            );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}