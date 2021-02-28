package com.example.conversionapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentA extends Fragment {

    private Object TextWatcher;

    public interface FragmentAListener{
        void onInputASend(String input);
    }


    private EditText et_celsius;
    private FragmentAListener listener;

    public FragmentA() {
        // Required empty public constructor
    }

    private final TextWatcher watcherCelsius = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = et_celsius.getText().toString();
            //Stuur naar fragment B
            if (et_celsius.hasFocus()) {
                if (et_celsius.length() >= 0 && !TextUtils.isEmpty(et_celsius.getText().toString())) {
                    try {
                        listener.onInputASend(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    listener.onInputASend("0");
                }
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        et_celsius = v.findViewById(R.id.et_celsius);
        et_celsius.addTextChangedListener(watcherCelsius);

        return v;
    }

    //ontvangt data van buitenaf (bvb in fragment b wordt op knop gedrukt)
    public void updateCelsius(Double input){
        String newInput = input.toString();
        et_celsius.setText(newInput);
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