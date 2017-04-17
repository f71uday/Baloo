package com.boophee.baloo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText editText_email;
        final EditText editText_password;
        Button button_signup;
        final FirebaseAuth firebaseAuth;
        Button button_already_registered;
        final ProgressBar progressBar_signup;
        editText_email= (EditText)findViewById(R.id.editText_email);
        editText_password=(EditText)findViewById(R.id.editText_password);
        button_signup=(Button)findViewById(R.id.signup_button);
        button_already_registered= (Button)findViewById(R.id.button_alreadyregistered);
        progressBar_signup= (ProgressBar)findViewById(R.id.progressBar_signingup);
       firebaseAuth= FirebaseAuth.getInstance();
        progressBar_signup.setVisibility(View.INVISIBLE);


        button_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email= editText_email.getText().toString();
                String password =editText_password.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignUpActivity.this,"Email field can not be empty ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignUpActivity.this,"password can not be empty ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.contains("@"))
                {
                    Toast.makeText(SignUpActivity.this,"enter a valid email adress",Toast.LENGTH_SHORT).show();
                    editText_email.selectAll();
                    return;
                }
                progressBar_signup.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful())
                         {
                             //pass intent to new activity here
                             Toast.makeText(SignUpActivity.this,"Account created successfully ",Toast.LENGTH_LONG).show();
                             progressBar_signup.setVisibility(View.INVISIBLE);
                             Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                             startActivity(intent);
                         }
                         if (!task.isSuccessful())
                         {
                             progressBar_signup.setVisibility(View.INVISIBLE);
                             Toast.makeText(SignUpActivity.this,"unable to create a account",Toast.LENGTH_SHORT).show();
                             return;

                         }

                    }
                });
            }
        });
        button_already_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SignUpActivity.this,"Activity need to be created",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
