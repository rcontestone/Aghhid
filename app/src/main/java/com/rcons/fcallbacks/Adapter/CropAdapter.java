package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Model.CropModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;


public class CropAdapter extends RecyclerView.Adapter<CropAdapter.MyViewHolder>  {
    private Context context;
    CropModel cropArrayList;
    String packaging = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mainLayout;
        private final TextView txtCropName;
        public MyViewHolder(View view) {
            super(view);
            txtCropName = view.findViewById(R.id.txtCropName);
            mainLayout = view.findViewById(R.id.mainLayout);

        }
    }


    public CropAdapter(CropModel horizontalList, Context context) {
        this.cropArrayList = horizontalList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_crop, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txtCropName.setText("Village: "+ cropArrayList.getCropUrduName().get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return cropArrayList.getCropID().size();
    }



    }

