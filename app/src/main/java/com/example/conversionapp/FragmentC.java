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

public class FragmentC extends Fragment {

    public interface FragmentCListener {
        void onInputCSend(String input);
    }

    private EditText et_kelvin;
    private FragmentCListener listener;

    public FragmentC() {
        // Required empty public constructor
    }

    private final TextWatcher watcherKelvin = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = et_kelvin.getText().toString();
            //Stuur naar fragment B
            if (et_kelvin.hasFocus()){
                if (et_kelvin.length() >= 0 && !TextUtils.isEmpty(et_kelvin.getText().toString())){
                    try {
                        listener.onInputCSend(input);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    listener.onInputCSend("0");
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
        View v = inflater.inflate(R.layout.fragment_c, container, false);

        et_kelvin = v.findViewById(R.id.et_kelvin);
        et_kelvin.addTextChangedListener(watcherKelvin);


        return v;
    }

    //ontvangt data van buitenaf (bvb in fragment b wordt op knop ingedrukt)
    public void updateKelvin(Double input){
        String newInput = input.toString();
        et_kelvin.setText(newInput);
    }



    @Override
    public void onAttach(Context context) throws RuntimeException {
        super.onAttach(context);
        if (context instanceof FragmentCListener){
            listener = (FragmentCListener)context;
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