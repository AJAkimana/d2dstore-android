package com.example.d2dstore.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2dstore.R;
import com.example.d2dstore.backgroundTasks.HomeMontlyTask;
import com.example.d2dstore.utils.Constants;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private HomeMontlyTask homeMontlyTask;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.rv_dashboard);
        final TextView emptView = root.findViewById(R.id.empty_view);

        homeMontlyTask = new HomeMontlyTask(getContext(), recyclerView);

        homeMontlyTask.execute((Void) null);

        if(Constants.montlyStoreList.size() == 0){
            emptView.setVisibility(View.VISIBLE);
        }
        return root;
    }
}