package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity{

    private TempFragment fragmentCelsius;
    private TempFragment fragmentFahrenheit;
    private TempFragment fragmentKelvin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentCelsius = new TempFragment();
        fragmentFahrenheit = new TempFragment();
        fragmentKelvin = new TempFragment();

        //Update Fahrenheit and kelvin from Celsius
        fragmentCelsius.setListener(new TempFragment.FragmentListener() {
            @Override
            public void onInputSend(float input) {
                fragmentFahrenheit.updateTemp(input * 9/5 + 32f);
                fragmentKelvin.updateTemp(input + 273.15f);
            }
        });

        fragmentFahrenheit.setListener(new TempFragment.FragmentListener() {
            @Override
            public void onInputSend(float input) {
                fragmentCelsius.updateTemp((input - 32f) * 5/9);
                fragmentKelvin.updateTemp((input - 32f) * 5/9 + 273.15f);
            }
        });

        fragmentKelvin.setListener(new TempFragment.FragmentListener() {
            @Override
            public void onInputSend(float input) {
                fragmentCelsius.updateTemp(input - 273.15f);
                fragmentFahrenheit.updateTemp((input - 273.15f) * 9/5 + 32f);
            }
        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentCelsius)
                .replace(R.id.layoutB, fragmentFahrenheit)
                .replace(R.id.layoutC, fragmentKelvin)
                .commit();
    }

}