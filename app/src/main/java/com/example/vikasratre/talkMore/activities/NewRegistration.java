package com.example.vikasratre.talkMore.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.vikasratre.talkMore.R;

public class NewRegistration extends AppCompatActivity {

    private EditText email,user_name,pass,conf_pass;
    Button sign_in;
    ImageButton image_selector;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            setContentView(R.layout.activity_new_registration);
            email = findViewById(R.id.e_mail);
            user_name = findViewById(R.id.user_name);
            pass = findViewById(R.id.pass);
            conf_pass = findViewById(R.id.conf_pass);
            sign_in = findViewById(R.id.sign_in);
            image_selector = findViewById(R.id.image_selector);

            awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

            awesomeValidation.addValidation(this, R.id.e_mail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
            awesomeValidation.addValidation(this, R.id.user_name, "^{3,30}$" , R.string.emailerror);
            awesomeValidation.addValidation(this, R.id.pass, "^(?=.*?[a-zA-Z])(?=.*?[0-9])[\\w@#$%^?~-]{6,30}$" , R.string.error_invalid_password);
            awesomeValidation.addValidation(this, R.id.conf_pass, "^(?=.*?[a-zA-Z])(?=.*?[0-9])[\\w@#$%^?~-]{6,30}$" , R.string.error_invalid_password);
            awesomeValidation.addValidation(this, R.id.conf_pass, pass.getText().toString() , R.string.error_incorrect_password);

            image_selector.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(awesomeValidation.validate()){
                        Toast.makeText(NewRegistration.this, "all fields verified", Toast.LENGTH_LONG).
                        show();
                    }
                }
            });

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"error_msg : "+e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }
}
