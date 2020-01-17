package com.example.d2dstore.backgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.d2dstore.models.ServerResponse;
import com.example.d2dstore.models.Store;
import com.example.d2dstore.services.StoreService;
import com.example.d2dstore.utils.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecordDataTask extends AsyncTask<Object, Object, Boolean> {

    Context context;
    String tType;
    String type;
    String amount;
    String description;
    String actionDate;

    public RecordDataTask(Context context, String tType, String type, String amount, String description, String actionDate) {
        this.context = context;
        this.tType = tType;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.actionDate = actionDate;
    }

    @Override
    protected Boolean doInBackground(Object... objects) {
        RecordController controller = new RecordController();
        controller.start();
        return null;
    }

    class RecordController implements Callback<ServerResponse<Store>> {

        public void start(){
            Retrofit retrofit = Constants.getRetrofit(context);
            Map<String, String> recordData = new HashMap<>();

            recordData.put(serializeDetail(tType), description);
            recordData.put("amount", amount);
            recordData.put("date", actionDate);
            recordData.put("type", type.toLowerCase());

            StoreService storeService = retrofit.create(StoreService.class);

            Call<ServerResponse<Store>> call = storeService.recordStore(tType.toLowerCase(), recordData);

            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<ServerResponse<Store>> call, Response<ServerResponse<Store>> response) {
            if(response.isSuccessful()){
                Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show();
            }else{
                try {
                    String errorMessage = Constants.getServerError(response.errorBody().string());
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                }  catch (IOException e) {

                    Toast.makeText(context, Constants.UNKNOWN_ERROR, Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ServerResponse<Store>> call, Throwable t) {
            /**
             *
             * Network error
             * */
            Toast.makeText(context, Constants.NO_NETWORK_ERROR, Toast.LENGTH_LONG).show();
        }

        private String serializeDetail(String transactionType){
            String typeName = "details";
            switch (transactionType.toLowerCase()){
                case "store":
                    typeName = "details";
                    break;
                case "online":
                    typeName = "reasons";
                    break;
                case "debt":
                    typeName = "debtor";
                    break;
                default: break;
            }

            return typeName;
        }
    }
}
