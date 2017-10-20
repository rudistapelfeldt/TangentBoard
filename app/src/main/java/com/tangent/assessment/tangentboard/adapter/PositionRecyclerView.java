package com.tangent.assessment.tangentboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.model.Position;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/20.
 */

public class PositionRecyclerView extends RecyclerView.Adapter<PositionRecyclerView.PositionViewHolder>{

    private static final String TAG = PositionRecyclerView.class.getSimpleName();

    private ArrayList<Position> mItems = new ArrayList<>();

    private LayoutInflater mInflater;

    public PositionRecyclerView(Context context, ArrayList<Position> itemList){
        this.mItems = itemList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public PositionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.position_row, parent, false);
        return new PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PositionViewHolder holder, int position) {

        try{
            holder.mId.setText(String.valueOf(mItems.get(position).getmId()));
            holder.mLevel.setText(mItems.get(position).getmLevel());
            holder.mName.setText(mItems.get(position).getmName());
            holder.mSort.setText(String.valueOf(mItems.get(position).getmSort()));

        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class PositionViewHolder extends RecyclerView.ViewHolder {

        private TextView mId, mName, mLevel, mSort;

        public PositionViewHolder(View view) {
            super(view);

            mId = (TextView) view.findViewById(R.id.p_id);

            mName = (TextView) view.findViewById(R.id.p_name);

            mLevel = (TextView) view.findViewById(R.id.p_level);

            mSort = (TextView) view.findViewById(R.id.p_sort);

        }
    }
}
