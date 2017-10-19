package com.tangent.assessment.tangentboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.model.Employee;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/18.
 */

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.EmployeeViewHolder> {

    private static final String TAG = EmployeeRecyclerViewAdapter.class.getSimpleName();

    private ArrayList<Employee> mItems = new ArrayList<>();

    private LayoutInflater mInflater;


    public EmployeeRecyclerViewAdapter(Context context, ArrayList<Employee> employeeList){
        this.mItems = employeeList;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.employee_row, parent, false);

        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{

        private TextView mId, mFname, mLname, mEmail, mPosition, mAge;

        public EmployeeViewHolder(View view){
            super(view);

            mId = (TextView)view.findViewById(R.id.emp_id);

            mFname = (TextView)view.findViewById(R.id.emp_fname);

            mLname = (TextView)view.findViewById(R.id.emp_lname);

            mPosition = (TextView)view.findViewById(R.id.emp_position);

            mEmail = (TextView)view.findViewById(R.id.emp_email);

            mAge = (TextView)view.findViewById(R.id.emp_age);
        }
    }

}
