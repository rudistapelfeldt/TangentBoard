package com.tangent.assessment.tangentboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        try{
            holder.mId.setText(String.valueOf(mItems.get(position).getmId()));
            holder.mFname.setText(mItems.get(position).getmFname());
            holder.mLname.setText(mItems.get(position).getmLname());
            holder.mPosition.setText(mItems.get(position).getmPosition());
            holder.mEmail.setText(mItems.get(position).getmEmail());
            holder.mAge.setText(String.valueOf(mItems.get(position).getmAge()));

        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
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
