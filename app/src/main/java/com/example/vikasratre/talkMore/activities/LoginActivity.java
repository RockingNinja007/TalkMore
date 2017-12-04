package com.example.vikasratre.talkMore.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.vikasratre.talkMore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {

    //private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    //============ layout view variables ================
    EditText email,pass;
    Button login,new_reg;
    TextView er_msg;

    //=========== internet variables ====================
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        try{
            email = findViewById(R.id.email);
            pass = findViewById(R.id.password);
            er_msg = findViewById(R.id.login_errors);
            login = findViewById(R.id.login);
            new_reg = findViewById(R.id.register);

            mAuth = FirebaseAuth.getInstance();
            awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

            //================ fields validation ======================
            awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
            awesomeValidation.addValidation(this, R.id.password, "^(?=.*?[a-zA-Z])(?=.*?[0-9])[\\w@#$%^?~-]{6,30}$" , R.string.error_incorrect_password);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                        if (awesomeValidation.validate()) {

                            final ProgressDialog nDialog;
                            nDialog = new ProgressDialog(LoginActivity.this);
                            nDialog.setMessage("Signing in Please wait..");
                            nDialog.setIndeterminate(false);
                            nDialog.setCancelable(false);
                            nDialog.show();


                            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                onLoginSuccess();
                                            } else {
                                                try {
                                                    nDialog.dismiss();
                                                    //er_msg.setVisibility(View.VISIBLE);
                                                    //Toast.makeText(LoginActivity.this, ""+getConnectivityStatus(LoginActivity.this), Toast.LENGTH_SHORT).show();
                                                    if (getConnectivityStatus(LoginActivity.this) == 0) {
                                                        //er_msg.setText("Login Failed...check ur internet conectivity");
                                                        show_snackbar("check ur internet conectivity",v);
                                                    } else {
                                                        show_snackbar("Invalid user id or password",v);
                                                    }
                                                } catch (Exception e) {
                                                    //er_msg.setText(e.getMessage());
                                                    show_snackbar(e.getMessage(),v);
                                                }
                                            }
                                        }
                                    });
                        }
                    }
                    catch (Exception e ){
                        Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });

            new_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tosignin = new Intent(LoginActivity.this,NewRegistration.class);
                    tosignin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(tosignin);
                    finish();
                }
            });
        }

        catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currenrUser = mAuth.getCurrentUser();
        if (currenrUser !=null){
            onLoginSuccess();
        }
    }

    private void onLoginSuccess(){
        Intent toAccount = new Intent(LoginActivity.this,Dashboard.class);
        toAccount.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toAccount);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void show_snackbar(String message,View view){
       Snackbar snackbar =  Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void new_registration( View view){
        //==================== Intent to new registration =================

    }

}
