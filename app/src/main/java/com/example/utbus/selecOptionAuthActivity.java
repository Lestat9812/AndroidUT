package com.example.utbus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selecOptionAuthActivity extends AppCompatActivity {

    Toolbar mToolbar;
   Button mButtonGoToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_option_auth);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Seleccionar una opcion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mButtonGoToLogin = findViewById(R.id.btnLogin);
        //mButtonGoToLogin.setOnClickListener(view -> goToLogin());


    }

    public void goToLogin() {
       Intent intent = new Intent(selecOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}