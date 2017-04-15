package com.boophee.baloo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText editText_email;
        EditText editText_password;
        Button button_signup;
        ProgressBar progressBar_signup;
        editText_email= (EditText)findViewById(R.id.editText_email);
        editText_password=(EditText)findViewById(R.id.editText_password);
        button_signup=(Button)findViewById(R.id.button_signup);
        progressBar_signup= (ProgressBar)findViewById(R.id.progressBar_signup);

    }
}
