package com.example.conversionapp;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    public interface FragmentBListener{
        void onInputBSend(String input);
    }

    private EditText et_fahrenheit;
    private FragmentBListener listener;

    public FragmentB() {
        // Required empty public constructor
    }

    private final TextWatcher watcherFahrenheit = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = et_fahrenheit.getText().toString();
            //Stuur naar fragment B
            if (et_fahrenheit.hasFocus()){
                if (et_fahrenheit.length() >= 0 && !TextUtils.isEmpty(et_fahrenheit.getText().toString())){
                    try {
                        listener.onInputBSend(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    listener.onInputBSend("0");
                }
            }
            else{
                return;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        et_fahrenheit = v.findViewById(R.id.et_fahrenheit);
        et_fahrenheit.addTextChangedListener(watcherFahrenheit);


        return v;
    }

    //ontvangt data van buitenaf (bvb in fragment b wordt op knop gedrukt)
    public void updateFahrenheit(Double input){
        String newInput = input.toString();
        et_fahrenheit.setText(newInput);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener){
            listener = (FragmentBListener)context;
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