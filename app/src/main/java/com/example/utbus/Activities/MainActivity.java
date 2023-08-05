package com.example.utbus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.utbus.R;

public class MainActivity extends AppCompatActivity {
    Button mButtonIAmStudent;
    Button mButtonIAmDriver;

    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();

        mButtonIAmStudent = findViewById(R.id.btnIAmStudent);
        mButtonIAmDriver = findViewById(R.id.btnIAmDriver);

        mButtonIAmStudent.setOnClickListener(view -> {
            editor.putString("user", "client");
            editor.apply();
            goToSelectAuth();
        });
        mButtonIAmDriver.setOnClickListener(view -> {
            editor.putString("user", "driver");
            editor.apply();
            goToselectAuth();
        });
    }

    private void goToselectAuth() {
        Intent intent = new Intent(MainActivity.this, selecOptionAuthActivity.class);
        startActivity(intent);
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, selecOptionAuthActivity.class);
        startActivity(intent);
    }
    }