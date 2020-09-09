package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Interfaces.onF4CheckBoxClick;
import com.rcons.fcallbacks.Interfaces.onF6CheckBoxClick;
import com.rcons.fcallbacks.Model.Crop;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;


public class F6CropSelectionAdapter extends RecyclerView.Adapter<F6CropSelectionAdapter.MyViewHolder>  {
    private Context context;
    ArrayList<Crop> cropArrayList;

    onF6CheckBoxClick boxPressed;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final AppCompatCheckBox checkCropName;



        public MyViewHolder(View view) {
            super(view);
            checkCropName = view.findViewById(R.id.checkCropName);


            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public F6CropSelectionAdapter(ArrayList<Crop> horizontalList, Context context) {
        this.cropArrayList = horizontalList;
        boxPressed = (onF6CheckBoxClick) context;
        this.context = context;


    }
    public F6CropSelectionAdapter(ArrayList<Crop> horizontalList, Context context , onF6CheckBoxClick f6CheckBoxClick) {
        this.cropArrayList = horizontalList;
        this.boxPressed = f6CheckBoxClick;
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_selection_crop, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Crop crop = cropArrayList.get(position);
        holder.checkCropName.setText(crop.getCropUrduName());
        holder.checkCropName.setOnCheckedChangeListener(null);
               if (crop.getIsSelected()) {
            holder.checkCropName.setChecked(true);
        } else {
            holder.checkCropName.setChecked(false);
        }
        holder.checkCropName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                boxPressed.onSectionF6CheckBoxClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

}