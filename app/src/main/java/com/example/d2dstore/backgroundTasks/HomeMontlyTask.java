package com.example.d2dstore.backgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.backgroundTasks.controllers.MontlyController;

public class HomeMontlyTask extends AsyncTask<Object, Object, Boolean> {

    Context context;
    RecyclerView recyclerView;

    public HomeMontlyTask(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        try {
            MontlyController montlyController = new MontlyController(context, recyclerView);
            montlyController.start();
        } catch (Exception e) {
            Log.d("MONTLY_HOME_TASK_EROR", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
