package com.example.conversionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TempFragment extends Fragment {

    private Object TextWatcher;
    private EditText etTemp;
    private FragmentListener listener;

    public interface FragmentListener{
        void onInputSend(float input);

    }


    public TempFragment() {
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
            String input = etTemp.getText().toString();
            //Stuur naar fragment B
            if (etTemp.hasFocus()) {
                if (etTemp.length() >= 0 && !TextUtils.isEmpty(etTemp.getText().toString())) {
                    try {
                        listener.onInputSend(Float.parseFloat(input));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    listener.onInputSend(0);
                }
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.temp_fragment, container, false);

        etTemp = v.findViewById(R.id.et_celsius);
        etTemp.addTextChangedListener(watcherCelsius);

        return v;
    }


    public void setListener(FragmentListener listener){
        this.listener = listener;
    }

    public void updateTemp(float input){
        etTemp.setText(String.format("%.2f", input));
    }
}