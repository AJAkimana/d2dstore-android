package com.example.d2dstore.backgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.d2dstore.backgroundTasks.controllers.LoginController;

public class AuthTask extends AsyncTask<Object, Object, Boolean> {

    String email;
    String password;
    Context context;

    public AuthTask(Context context, String email, String password) {
        this.email = email;
        this.password = password;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        try {

            LoginController loginController = new LoginController(context, email, password);
            loginController.start();
        } catch (Exception e) {
            Log.d("LOGIN_TASK_EROR", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
