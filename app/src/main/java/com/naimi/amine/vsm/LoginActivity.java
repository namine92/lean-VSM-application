package com.naimi.amine.vsm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText loginEdit;
    EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loginEdit = (EditText) findViewById(R.id.edit_login);
        passwordEdit = (EditText) findViewById(R.id.edit_pwd);
    }


    public void checkLogin(View arg0) {

        final String login = loginEdit.getText().toString();
        if (!isValidField(login)) {
            //Set error message for email field
            loginEdit.setError("Login invalide");
        }

        final String pass = passwordEdit.getText().toString();
        if (!isValidField(pass)) {
            //Set error message for password field
            passwordEdit.setError("Mot de passe invalide");
        }

        if (isValidField(login) && isValidField(pass)) {
            Intent i = new Intent(this, TimingsListActivity.class);
            startActivity(i);
        }

    }

    private boolean isValidField(String pass) {
        if (pass != null && pass.length() >= 1) {
            return true;
        }
        return false;
    }


}
