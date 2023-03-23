package com.example.a20230307_mfa_nycschools.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20230307_mfa_nycschools.R;
import com.example.a20230307_mfa_nycschools.databinding.NycSchoolLayoutBinding;
import com.example.a20230307_mfa_nycschools.model.NYCSchool;

import java.util.List;

public class NYCSchoolAdapter extends RecyclerView.Adapter<NYCSchoolAdapter.NYCSchoolHolder> {

    private final List<NYCSchool> schoolList;
    private final NYCSchoolListFragment.NYCSchoolCallback callback;

    public NYCSchoolAdapter(List<NYCSchool> list, NYCSchoolListFragment.NYCSchoolCallback callback){
        this.schoolList = list;
        this.callback = callback;
    }


    @NonNull
    @Override
    public NYCSchoolHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NycSchoolLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.nyc_school_layout, parent, false);
        return new NYCSchoolHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NYCSchoolHolder holder, int position) {
        NYCSchool school = schoolList.get(position);
        holder.bind(school);
        holder.itemView.setOnClickListener(view -> callback.navigate(school));
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    protected static class NYCSchoolHolder extends RecyclerView.ViewHolder {
        private final NycSchoolLayoutBinding binding;

        public NYCSchoolHolder(@NonNull NycSchoolLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NYCSchool school){
            binding.setListedSchool(school);
            binding.executePendingBindings();
        }
    }
}
