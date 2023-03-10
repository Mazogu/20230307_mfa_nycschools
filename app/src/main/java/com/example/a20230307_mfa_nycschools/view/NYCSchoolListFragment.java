package com.example.a20230307_mfa_nycschools.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20230307_mfa_nycschools.R;
import com.example.a20230307_mfa_nycschools.viewmodel.NYCViewModel;


public class NYCSchoolListFragment extends Fragment {

    private NYCViewModel viewModel;

    public NYCSchoolListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NYCViewModel.Companion.getFactory().create(NYCViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_n_y_c_school_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.schoolList);
        viewModel.getSchoolList().observe(getViewLifecycleOwner(), nycSchools -> {
            if(!nycSchools.isEmpty()){
                recyclerView.setVisibility(View.VISIBLE);
                NYCSchoolAdapter adapter = new NYCSchoolAdapter(nycSchools);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
            else {
                //I guess I could've used the binder for this
                View box = view.findViewById(R.id.school_list_error);
                box.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.getNYCSchools();
    }
}