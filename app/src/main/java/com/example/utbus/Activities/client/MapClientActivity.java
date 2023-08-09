package com.example.utbus.Activities.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.utbus.Activities.MainActivity;
import com.example.utbus.R;
import com.example.utbus.providers.AuthProvider;

public class MapClientActivity extends AppCompatActivity {

    Button mButtonLogout;
    AuthProvider mAuthprovider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_client);

        mButtonLogout = findViewById(R.id.btnLogout);
        mAuthprovider = new AuthProvider();

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuthprovider.logout();
                Intent intent = new Intent(MapClientActivity.this, MainActivity.class);
                   startActivity(intent);
                    finish();
            }
        });
    }
}