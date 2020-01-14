package com.example.d2dstore.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.CustomViewHolder> {

    int layoutId;
    protected List<?> dataList = new ArrayList<>();
    Context baseContext;
    public View parentView;

    public BaseAdapter(Context baseContext, int layoutId, List<?> list) {
        this.baseContext = baseContext;
        this.layoutId = layoutId;
        this.dataList = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(layoutId, viewGroup, false);

        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Log.d("MONTLY_BIND_HOLDER", "Executed");
        onBindViewHolder(position, dataList.get(position));
    }

    protected abstract void onBindViewHolder(int position, Object itemView);

    public abstract View getView(View view);

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView;
            getView(itemView);
        }
    }

    public <T extends View> T bind(int id){
        return parentView.findViewById(id);
    }
}
