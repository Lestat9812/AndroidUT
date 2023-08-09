package com.example.utbus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.utbus.Activities.client.MapClientActivity;
import com.example.utbus.Activities.driver.MapDriverActivity;
import com.example.utbus.R;
import com.google.firebase.auth.FirebaseAuth;

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

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
           String user = mPref.getString( "user","");
            if (user.equals("client")){
                Intent intent = new Intent(MainActivity.this, MapClientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            //Toast.makeText(LoginActivity.this, "El ingreso fue exitoso", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(MainActivity.this, MapDriverActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //Toast.makeText(LoginActivity.this, "El email o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        }
    }


    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, selecOptionAuthActivity.class);
        startActivity(intent);
    }
    }