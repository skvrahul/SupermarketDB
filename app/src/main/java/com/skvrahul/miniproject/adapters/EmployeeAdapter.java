package com.skvrahul.miniproject.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skvrahul.miniproject.R;
import com.skvrahul.miniproject.models.Employee;

import java.util.List;

import static android.content.ContentValues.TAG;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {
    @Override
    public int getItemCount() {
        return Employees.size();
    }

    public interface DeleteClickListener{
        void onClick(Employee item);
    }
    private List<Employee> Employees;
    private DeleteClickListener deleteClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name,phoneNumber,startDate, salary;


        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.e_id_view);
            name = view.findViewById(R.id.e_name_view);
            phoneNumber = view.findViewById(R.id.e_phone_view);
            salary = view.findViewById(R.id.e_sal_view);
            //startDate = view.findViewById(R.id.e_da)
            //delete = view.findViewById(R.id.cart_item_delete);

        }
    }


    public EmployeeAdapter(List<Employee> Employees) {
        this.Employees = Employees;
    }

    public EmployeeAdapter(List<Employee> Employees, DeleteClickListener deleteClickListener) {
        this.Employees = Employees;
        //this.deleteClickListener = deleteClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View employeeView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list_display, parent, false);

        return new MyViewHolder(employeeView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Employee e = Employees.get(position);
        holder.name.setText(e.getEmpName());
        holder.id.setText(e.getE_id()+"");
        holder.phoneNumber.setText(e.getPhoneNum());
        holder.salary.setText(e.getSalary()+"");
        Log.d(TAG, "onBindViewHolder: called");
    }


}