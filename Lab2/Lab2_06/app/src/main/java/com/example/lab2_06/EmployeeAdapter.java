package com.example.lab2_06;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private Activity context;
    private ArrayList<Employee> arrEmployee;

    private LayoutInflater layoutInflater;
    public EmployeeAdapter(Activity context, ArrayList<Employee> arr) {
        this.context = context;
        arrEmployee = arr;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        // Gán dữ lieu
        Employee employee = arrEmployee.get(position);


        if (employee.getFullName()!=null) {
            holder.txtFullName.setText(employee.getFullName());
        }
        else holder.txtFullName.setText("");

        if (employee.getManager())
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.txtPosition.setVisibility(View.GONE);
        }
        else
        {
            holder.ivManager.setVisibility(View.GONE);
            holder.txtPosition.setVisibility(View.VISIBLE);
            holder.txtPosition.setText("Staff");
        }

        if (position%2==0)
        {
            holder.llParent.setBackgroundResource(R.color.white);
        }
        else
        {
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        if (arrEmployee == null) return -1;
        return arrEmployee.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFullName;
        private TextView txtPosition;
        private ImageView ivManager;
        private LinearLayout llParent;
        public EmployeeViewHolder(View itemView) {
            super(itemView);

            txtFullName = itemView.findViewById(R.id.item_employee_tv_fullname);
            txtPosition = itemView.findViewById(R.id.item_employee_tv_position);
            ivManager = itemView.findViewById(R.id.item_employee_iv_manager);
            llParent = itemView.findViewById(R.id.item_employee_ll_parent);
        }
    }
}
