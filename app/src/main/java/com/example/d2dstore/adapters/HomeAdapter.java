package com.example.d2dstore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.R;
import com.example.d2dstore.adapters.holders.HomeOverviewHolder;
import com.example.d2dstore.models.OverViewResponse;
import com.example.d2dstore.utils.Constants;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeOverviewHolder> {
    List<OverViewResponse.Store> overviews;

    public HomeAdapter(List<OverViewResponse.Store> overviews) {
        this.overviews = overviews;
    }

    @Override
    public HomeOverviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_row, parent, false);

        HomeOverviewHolder viewHolder = new HomeOverviewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeOverviewHolder holder, int position) {
        OverViewResponse.Store overview = overviews.get(position);
        Integer balance  = overview.getInflow() - overview.getOutflow();

        holder.incomeTv.setText(String.valueOf(overview.getInflow()));
        holder.incomeTitleTv.setText(Constants.STORE_INCOME);
        holder.expenseTv.setText(String.valueOf(overview.getOutflow()));
        holder.expenseTitleTv.setText(Constants.STORE_EXPENSES);
        holder.totalItemsTv.setText(String.valueOf(overview.getTotalInput()));
        holder.totalItemsTitleTv.setText(Constants.STORE_TOTAL_ITEMS);
        holder.balanceTv.setText(String.valueOf(balance));
        holder.balanceTitleTv.setText(Constants.STORE_BALANCE);
    }

    @Override
    public int getItemCount() {
        return overviews.size();
    }
}
