package com.example.utbus.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.utbus.Activities.client.MapClientActivity;
import com.example.utbus.Activities.client.RegisterActivity;
import com.example.utbus.Activities.driver.MapDriverActivity;
import com.example.utbus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    Button mButtonLogin;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    Toolbar mToolbar;
    SharedPreferences mPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputPassword = findViewById(R.id.textInputPasword);
        mButtonLogin = findViewById(R.id.btnLogin);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            login();    
            }
        });
    }

    private void login() {
            String email = mTextInputEmail.getText().toString();
            String password = mTextInputPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()){
                if (password.length() >= 6){
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task <AuthResult> task) {
                            if (task.isSuccessful()){
                                String user = mPref.getString("user", "");
                                if (user.equals("client")){
                                    Intent intent = new Intent(LoginActivity.this, MapClientActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                //Toast.makeText(LoginActivity.this, "El ingreso fue exitoso", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, MapDriverActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                //Toast.makeText(LoginActivity.this, "El email o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(this, "La contraseña debe ser mayor a 6 digitos", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(this, "La contraseña y el email son obligatorios", Toast.LENGTH_SHORT).show();
            }
    }
}