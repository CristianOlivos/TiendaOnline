package com.proyecyo.tiendaonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.proyecyo.tiendaonline.MainActivity;
import com.proyecyo.tiendaonline.R;

public class LoginActivity extends AppCompatActivity {
    Button signIn;
    EditText email,password;

    TextView signUp;

    FirebaseAuth auth;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        signIn=findViewById(R.id.login_btn);
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        signUp=findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

            }
        });

    signIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            loginUser();

            progressBar.setVisibility(View.VISIBLE);
        }


    });
    }

    private void loginUser() {


            String userEmail=email.getText().toString();
            String userPassword=password.getText().toString();


            if(TextUtils.isEmpty(userEmail)){

                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Correo Vacio!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(userPassword)){

                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Contraseña Vacia!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(userPassword.length()<6){

                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "La Contraseña como minimo debe tener 6 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }
        //lOGUEAR USUARIO
        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Ingreso Exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else
                    Toast.makeText(LoginActivity.this, "Error!"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}