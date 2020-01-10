package com.example.d2dstore.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.R;

public class HomeOverviewHolder extends RecyclerView.ViewHolder{

    public TextView totalItemsTv;
    public TextView incomeTv;
    public TextView expenseTv;
    public TextView balanceTv;
    public TextView totalItemsTitleTv;
    public TextView incomeTitleTv;
    public TextView expenseTitleTv;
    public TextView balanceTitleTv;

    public HomeOverviewHolder(@NonNull View itemView) {
        super(itemView);
        totalItemsTv = itemView.findViewById(R.id.total_items_tv);
        totalItemsTitleTv = itemView.findViewById(R.id.total_items_title_tv);
        incomeTv = itemView.findViewById(R.id.inflow_tv);
        incomeTitleTv = itemView.findViewById(R.id.inflow_title_tv);
        expenseTv = itemView.findViewById(R.id.outflow_tv);
        expenseTitleTv = itemView.findViewById(R.id.outflow_title_tv);
        balanceTv = itemView.findViewById(R.id.balance_tv);
        balanceTitleTv = itemView.findViewById(R.id.balance_title_tv);
    }
}
