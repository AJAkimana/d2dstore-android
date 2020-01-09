package com.example.d2dstore.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.d2dstore.HomeActivity;
import com.example.d2dstore.R;
import com.example.d2dstore.backgroundTasks.AuthTask;

//import retrofit.Callback;
//import retrofit.RetrofitError;
//import retrofit.client.Response;

public class LoginUtils extends Activity {
    Context context;
    EditText emailTextEdit, passwordTextEdit;
    ProgressDialog progressDialog;
    CheckBox checkBoxRemember;

    PreferenceManager manager;

    public LoginUtils(Context appContext,
                      EditText email,
                      EditText password,
                      CheckBox remember){
        this.context = appContext;
        this.emailTextEdit = email;
        this.passwordTextEdit = password;
        this.checkBoxRemember=remember;
        this.progressDialog = new ProgressDialog(appContext);

        manager = new PreferenceManager(appContext);
    }

    public void startHomeActivity() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        finish();
    }

    public void attemptLogin() {

        // Reset errors.
        emailTextEdit.setError(null);
        passwordTextEdit.setError(null);

        // Store values at the time of the login attempt.
        String email = emailTextEdit.getText().toString();
        String password = passwordTextEdit.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordTextEdit.setError(context.getString(R.string.error_invalid_password));
            focusView = passwordTextEdit;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailTextEdit.setError(context.getString(R.string.error_field_required));
            focusView = emailTextEdit;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailTextEdit.setError(context.getString(R.string.error_invalid_email));
            focusView = emailTextEdit;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait...");
//            progressDialog.show();

//            Api.getClient().register(email, password, new Callback<LoginResponse>() {
//                @Override
//                public void success(LoginResponse loginResponse, Response response) {
////                    progressDialog.dismiss();
//                    Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    Log.d("RETROFIT ERROR", "URL:"+ error.getUrl()+", Msg:"+error.getMessage());
//                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
////                    progressDialog.dismiss();
//                }
//            });
            AuthTask authTask = new AuthTask(context, email, password);
            authTask.execute((Void)null);
//            if (checkBoxRemember.isChecked())
//                manager.saveLoginDetails(email, password);
//            startHomeActivity();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
