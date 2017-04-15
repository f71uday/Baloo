package com.boophee.baloo;

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

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editText_email;
        final EditText editText_password;
        Button button_signup;
        final FirebaseAuth firebaseAuth;

        final ProgressBar progressBar_signup;
        editText_email= (EditText)findViewById(R.id.editText_email);
        editText_password=(EditText)findViewById(R.id.editText_password);
        button_signup=(Button)findViewById(R.id.button_signup);
        progressBar_signup= (ProgressBar)findViewById(R.id.progressBar_signup);
       firebaseAuth= FirebaseAuth.getInstance();
        progressBar_signup.setVisibility(View.INVISIBLE);
        button_signup.setOnClickListener(new View.OnClickListener() {
            String email= editText_email.getText().toString();
            String password =editText_password.getText().toString();

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login.this,"Email field can not be empty ",Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login.this,"password can not be empty ",Toast.LENGTH_SHORT);
                    return;
                }
                if (!email.contains("@"))
                {
                    Toast.makeText(Login.this,"enter a valid email adress",Toast.LENGTH_SHORT);
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
                             Toast.makeText(Login.this,"Account created successfully ",Toast.LENGTH_LONG);
                             progressBar_signup.setVisibility(View.INVISIBLE);
                         }
                         if (!task.isSuccessful())
                         {
                             progressBar_signup.setVisibility(View.INVISIBLE);
                             Toast.makeText(Login.this,"unable to create a account",Toast.LENGTH_SHORT);
                             return;

                         }

                    }
                });
            }
        });
    }
}
