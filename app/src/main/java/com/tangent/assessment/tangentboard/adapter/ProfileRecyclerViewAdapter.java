package com.tangent.assessment.tangentboard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/18.
 */

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ProfileViewHolder> {

    private static final String TAG = ProfileRecyclerViewAdapter.class.getSimpleName();

    private ArrayList<String> mItems;


    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder{

        private TextView mItem;

        private TextView mValue;

        public ProfileViewHolder(View view){
            super(view);

            mItem = (TextView)view.findViewById(R.id.prof_label);

            mValue = (TextView)view.findViewById(R.id.prof_value);
        }
    }

}
