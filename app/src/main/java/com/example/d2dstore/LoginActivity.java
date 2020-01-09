package com.example.d2dstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.d2dstore.utils.LoginUtils;
import com.example.d2dstore.utils.PreferenceManager;

public class LoginActivity extends AppCompatActivity {

    LoginUtils loginUtils;

    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailView = findViewById(R.id.email);
        EditText passwordView = findViewById(R.id.password);
        CheckBox rememberView = findViewById(R.id.checkBoxRememberMe);
        Button signingView = findViewById(R.id.email_sign_in_button);

        loginUtils = new LoginUtils(getApplicationContext(), emailView, passwordView, rememberView);

        preferenceManager = new PreferenceManager(getApplicationContext());

        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int imeActionId = getResources().getInteger(R.integer.passwordImeActionId);
                if(actionId == imeActionId || actionId == EditorInfo.IME_NULL){
                    loginUtils.attemptLogin();
                    return true;
                }
                return false;
            }
        });

        signingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUtils.attemptLogin();
            }
        });

        if(preferenceManager.userHasAccess()){
            loginUtils.startHomeActivity();
        }
    }
}
