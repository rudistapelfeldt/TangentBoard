package com.tangent.assessment.tangentboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.model.EmployeeNextOfKin;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/20.
 */

public class NextOfKinRecyclerView extends RecyclerView.Adapter<NextOfKinRecyclerView.NextOfKinViewHolder>{

    private static final String TAG = NextOfKinRecyclerView.class.getSimpleName();

    private ArrayList<EmployeeNextOfKin> mItems = new ArrayList<>();

    private LayoutInflater mInflater;

    public NextOfKinRecyclerView(Context context, ArrayList<EmployeeNextOfKin> itemList){
        this.mItems = itemList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public NextOfKinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.nok_row, parent, false);
        return new NextOfKinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NextOfKinViewHolder holder, int position) {
        try{
            holder.mId.setText(String.valueOf(mItems.get(position).getmId()));
            holder.mName.setText(mItems.get(position).getmName());
            holder.mEmployee.setText(String.valueOf(mItems.get(position).getmEmployee()));
            holder.mPhysicalAddress.setText(mItems.get(position).getmPhysicalAddress());
            holder.mPhoneNumber.setText(mItems.get(position).getmPhoneNumber());
            holder.mRelationship.setText(mItems.get(position).getmRelationshp());
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class NextOfKinViewHolder extends RecyclerView.ViewHolder {

        private TextView mId, mName, mRelationship, mPhoneNumber, mPhysicalAddress, mEmployee;

        public NextOfKinViewHolder(View view) {
            super(view);

            mId = (TextView) view.findViewById(R.id.nok_id);

            mName = (TextView) view.findViewById(R.id.nok_name);

            mRelationship = (TextView) view.findViewById(R.id.nok_relationship);

            mPhoneNumber = (TextView) view.findViewById(R.id.nok_p_number);

            mPhysicalAddress = (TextView) view.findViewById(R.id.nok_p_address);

            mEmployee = (TextView) view.findViewById(R.id.nok_employee);
        }
    }
}
