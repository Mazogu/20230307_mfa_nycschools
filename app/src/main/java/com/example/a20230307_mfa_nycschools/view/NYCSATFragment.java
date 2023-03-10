package com.example.a20230307_mfa_nycschools.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20230307_mfa_nycschools.R;
import com.example.a20230307_mfa_nycschools.databinding.FragmentNycSatBinding;
import com.example.a20230307_mfa_nycschools.viewmodel.NYCViewModel;

public class NYCSATFragment extends Fragment {

    private NYCViewModel viewModel;

    public NYCSATFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = NYCViewModel.Companion.getFactory().create(NYCViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments() != null && getArguments().getString("schoolId") != null){
            String id = getArguments().getString("schoolId");
            viewModel.getSATScores(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNycSatBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nyc_sat, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }
}