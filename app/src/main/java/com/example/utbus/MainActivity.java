package com.example.utbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mButtonIAmStudent;
    Button mButtonIAmDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mButtonIAmStudent = findViewById(R.id.btnIAmStudent);
        mButtonIAmDriver = findViewById(R.id.btnIAmDriver);

        mButtonIAmStudent.setOnClickListener(view -> goToSelectAuth());
        mButtonIAmDriver.setOnClickListener(view -> goToselectAuth());
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