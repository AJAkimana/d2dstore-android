package com.example.d2dstore.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d2dstore.R;
import com.example.d2dstore.models.Store;

import java.util.ArrayList;
import java.util.List;

public class HomeMonthAdapter extends BaseAdapter {

    List<Store> storeList;
    TextView tvMonth;
    TextView tvIncome;
    TextView tvExpense;
    TextView tvNItems;
    ImageView imgOverflow;

    public HomeMonthAdapter(Context baseContext, List<Store> storeList) {
        super(baseContext, R.layout.item_row_month, storeList);
        this.storeList = storeList;
    }


    @Override
    protected void onBindViewHolder(final int position, Object itemView) {
        final Store store = storeList.get(position);

        tvMonth.setText(store.getMonth());
        tvIncome.setText(String.valueOf(store.getInflow()));
        tvExpense.setText(String.valueOf(store.getOutflow()));
        tvNItems.setText(store.getTotalStores() + " records");

        imgOverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(baseContext, store.getMonth(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View getView(View view) {
        tvMonth = bind(R.id.tv_month_view);
        tvIncome = bind(R.id.tv_income_view);
        tvExpense = bind(R.id.tv_expense_view);
        tvNItems = bind(R.id.tv_n_items_view);
        imgOverflow = bind(R.id.img_overflow);

        return view;
    }

    public int dpToPx(int dp){
        Resources r = baseContext.getResources();

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
