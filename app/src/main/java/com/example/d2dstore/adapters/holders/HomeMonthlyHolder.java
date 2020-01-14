package com.example.d2dstore.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.R;

public class HomeMonthlyHolder extends RecyclerView.ViewHolder {

    public TextView tvMonth;
    public TextView tvIncome;
    public TextView tvExpense;
    public TextView tvNItems;

    public HomeMonthlyHolder(@NonNull View itemView) {
        super(itemView);

        tvMonth = itemView.findViewById(R.id.tv_month_view);
        tvIncome = itemView.findViewById(R.id.tv_income_view);
        tvExpense = itemView.findViewById(R.id.tv_expense_view);
        tvNItems = itemView.findViewById(R.id.tv_n_items_view);
    }
}
