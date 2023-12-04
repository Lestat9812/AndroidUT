package com.example.utbus.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.utbus.Activities.client.RegisterActivity;
import com.example.utbus.Activities.driver.RegisterDriverActivity;
import com.example.utbus.R;

public class selectOptionAuthActivity extends AppCompatActivity {

    Button mButtonGoToLogin;

    Button mButtonGoToRegister;
    SharedPreferences mPref;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_option_auth);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Seleccionar una opcion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mButtonGoToLogin = findViewById(R.id.btnGoToLogin);
        mButtonGoToRegister = findViewById(R.id.btnGoToRegister);
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

    }

   public void goToLogin (){
        Intent intent = new Intent(selectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
   }
    public void goToRegister (){
        String typeUser = mPref.getString("user", "");
        if (typeUser.equals("client")){
            Intent intent = new Intent(selectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(selectOptionAuthActivity.this, RegisterDriverActivity.class);
            startActivity(intent);
        }
    }
}