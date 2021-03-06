package com.example.d2dstore.backgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.backgroundTasks.controllers.OverViewController;

public class HomeOverviewTask extends AsyncTask<Object, Object, Boolean> {

    Context context;
    RecyclerView recyclerView;

    public HomeOverviewTask(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        try {

            OverViewController loginController = new OverViewController(context, recyclerView);
            loginController.start();
        } catch (Exception e) {
            Log.d("OVER_HOME_TASK_EROR", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
