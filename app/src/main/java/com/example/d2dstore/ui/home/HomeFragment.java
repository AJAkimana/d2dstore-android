package com.example.d2dstore.ui.home;

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
import com.example.d2dstore.backgroundTasks.HomeOverviewTask;
import com.example.d2dstore.backgroundTasks.controllers.OverViewController;
import com.example.d2dstore.utils.PreferenceManager;

import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private PreferenceManager preferenceManager;
    private HomeOverviewTask homeOverviewTask;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        preferenceManager = new PreferenceManager(getContext());

        Map<String, String> user = preferenceManager.getUserInfo();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView homeRecycleView = root.findViewById(R.id.home_recycler_view);

//        textView.setText("Welcome "+ user.get("names"));
        homeOverviewTask = new HomeOverviewTask(getContext(), homeRecycleView);

        homeOverviewTask.execute((Void) null);

        return root;
    }
}