package com.example.a20230307_mfa_nycschools.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20230307_mfa_nycschools.R;
import com.example.a20230307_mfa_nycschools.model.NYCSchool;

import java.util.List;

public class NYCSchoolAdapter extends RecyclerView.Adapter<NYCSchoolAdapter.NYCSchoolHolder> {

    private final List<NYCSchool> schoolList;

    public NYCSchoolAdapter(List<NYCSchool> list){
        this.schoolList = list;
    }

    @NonNull
    @Override
    public NYCSchoolHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nyc_school_layout, parent, false);
        return new NYCSchoolHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NYCSchoolHolder holder, int position) {
        NYCSchool school = schoolList.get(position);
        holder.getSchoolName().setText(school.getName());
        holder.getSchoolName().setOnClickListener(view -> {
            NavController controller = Navigation.findNavController(holder.itemView);
            Bundle bundle = new Bundle();
            bundle.putString("schoolId", school.getId());
            controller.navigate(R.id.action_NYCSchoolListFragment_to_NYCSATFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    protected static class NYCSchoolHolder extends RecyclerView.ViewHolder {
        private final TextView schoolName;
        public NYCSchoolHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.schoolName);
        }

        public TextView getSchoolName(){
            return schoolName;
        }
    }
}
