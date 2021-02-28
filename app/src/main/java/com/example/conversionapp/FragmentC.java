package com.example.conversionapp;

import android.content.Context;
import android.os.Bundle;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_c, container, false);

        et_kelvin = v.findViewById(R.id.et_kelvin);

        v.findViewById(R.id.button_to_kelvin).setOnClickListener(bv -> {
            String input = et_kelvin.getText().toString();

            //Stuur naar fragment C
            listener.onInputCSend(input);
        });


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