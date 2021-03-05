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
            public void onInputSend(String input) {
                fragmentFahrenheit.updateTemp(input);
                fragmentKelvin.updateTemp(input);
            }
        });

        fragmentFahrenheit.setListener(new TempFragment.FragmentListener() {
            @Override
            public void onInputSend(String input) {

            }
        });

        fragmentKelvin.setListener(new TempFragment.FragmentListener() {
            @Override
            public void onInputSend(String input) {

            }
        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentCelsius)
                .replace(R.id.layoutB, fragmentFahrenheit)
                .replace(R.id.layoutC, fragmentKelvin)
                .commit();
    }

}