package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener, FragmentC.FragmentCListener {

    double inputVal;
    double valFahrenheit;
    double valCelsius;
    double valKelvin;

    @Override
    public void onInputASend(String input) {
        inputVal = Double.parseDouble(String.valueOf(input));
        valFahrenheit = inputVal*1.8+32;
        valKelvin = inputVal+273.15;

        fragmentB.updateFahrenheit(valFahrenheit);
        fragmentC.updateKelvin(valKelvin);
    }

    @Override
    public void onInputBSend(String input) {
        inputVal = Double.parseDouble(String.valueOf(input));
        valCelsius = (inputVal-32)/1.8;
        valKelvin = (inputVal+459.67)*5/9;

        fragmentA.updateCelsius(valCelsius);
        fragmentC.updateKelvin(valKelvin);
    }

    @Override
    public void onInputCSend(String input) {
        inputVal = Double.parseDouble(String.valueOf(input));
        valCelsius = inputVal-273.15;
        valFahrenheit = (inputVal*9/5)-459.67;

        fragmentA.updateCelsius(valCelsius);
        fragmentB.updateFahrenheit(valFahrenheit);
    }


    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentA)
                .replace(R.id.layoutB, fragmentB)
                .replace(R.id.layoutC, fragmentC)
                .commit();
    }

}