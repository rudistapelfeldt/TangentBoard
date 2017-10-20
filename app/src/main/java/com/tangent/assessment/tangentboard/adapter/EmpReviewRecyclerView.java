package com.tangent.assessment.tangentboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.model.EmployeeReview;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/20.
 */

public class EmpReviewRecyclerView extends RecyclerView.Adapter<EmpReviewRecyclerView.EmpReviewViewHolder>{

    private static final String TAG = EmpReviewRecyclerView.class.getSimpleName();

    private ArrayList<EmployeeReview> mItems = new ArrayList<>();

    private LayoutInflater mInflater;

    public EmpReviewRecyclerView(Context context, ArrayList<EmployeeReview> itemList){

        this.mItems = itemList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public EmpReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.emp_review_row, parent, false);
        return new EmpReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmpReviewViewHolder holder, int position) {
        try{
            holder.mId.setText(String.valueOf(mItems.get(position).getmId()));
            holder.mDate.setText(mItems.get(position).getmDate());
            holder.mSalary.setText(mItems.get(position).getmSalary());
            holder.mType.setText(mItems.get(position).getmType());
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class EmpReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView mId, mDate, mSalary, mType;

        public EmpReviewViewHolder(View view) {
            super(view);

            mId = (TextView) view.findViewById(R.id.emp_r_id);

            mDate = (TextView) view.findViewById(R.id.emp_r_date);

            mSalary = (TextView) view.findViewById(R.id.emp_r_salary);

            mType = (TextView) view.findViewById(R.id.emp_r_type);

        }
    }
}
