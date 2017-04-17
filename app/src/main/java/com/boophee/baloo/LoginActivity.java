package com.boophee.baloo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by uday on 16/4/17.
 */

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editText_email;
        final EditText editText_password;
        Button button_login;
        final ProgressBar progressBar_login;
        editText_email= (EditText)findViewById(R.id.editText_login_email);
        editText_password= (EditText)findViewById(R.id.editText_login_password);
        button_login= (Button)findViewById(R.id.button_login);
        progressBar_login=(ProgressBar)findViewById(R.id.progressBar_login);
        final FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        progressBar_login.setVisibility(View.INVISIBLE);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginActivity.this,"Enter a valid email",Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this,"enter  password",Toast.LENGTH_SHORT).show();
                }
                progressBar_login.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"redirectin to new activity",Toast.LENGTH_SHORT).show();
                            progressBar_login.setVisibility(View.INVISIBLE);
                            Intent intent =  new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"enter a valid combination of email and password", Toast.LENGTH_SHORT).show();
                            progressBar_login.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }
}
