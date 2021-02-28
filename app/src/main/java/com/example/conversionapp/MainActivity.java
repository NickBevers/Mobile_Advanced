package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {
    @Override
    public void onInputASend(String input) {
        fragmentB.updateFahrenheit(input);
    }

    @Override
    public void onInputBSend(String input) {
        fragmentA.updateCelsius(input);
    }

    private FragmentA fragmentA;
    private FragmentB fragmentB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentA)
                .replace(R.id.layoutB, fragmentB)
                .commit();
    }
}