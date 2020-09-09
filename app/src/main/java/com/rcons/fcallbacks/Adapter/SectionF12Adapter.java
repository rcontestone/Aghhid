package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Interfaces.onEditSectionF12CallBack;
import com.rcons.fcallbacks.Model.SectionF12;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;


public class SectionF12Adapter extends RecyclerView.Adapter<SectionF12Adapter.MyViewHolder> {
    private Context context;
    ArrayList<SectionF12> sectionF2ArrayList;
       String packaging = "";
    onEditSectionF12CallBack f12CallBack;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtID;
        private final TextView txt12Crop;
        private final TextView txtF12CropOther;
        private final TextView txt13;
        private final TextView txt13_other;
        private final TextView txt14;
        private final TextView txt14_other;

        private final TextView txt15;
        private final TextView txt15_other;

        private final ImageView btnEdit;
        private final ImageView btnDelete;



        public MyViewHolder(View view) {
            super(view);
            txtID = view.findViewById(R.id.txtID);
            txt12Crop = view.findViewById(R.id.txt12Crop);
            txtF12CropOther = view.findViewById(R.id.txtF12CropOther);
            txt13 = view.findViewById(R.id.txt13);
            txt13_other = view.findViewById(R.id.txt13_other);
            txt14 = view.findViewById(R.id.txt14);
            txt14_other = view.findViewById(R.id.txt14_other);
            txt15 = view.findViewById(R.id.txt15);
            txt15_other = view.findViewById(R.id.txt15_other);


            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);


            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public SectionF12Adapter(ArrayList<SectionF12> horizontalList, Context context , onEditSectionF12CallBack callBack) {
        this.sectionF2ArrayList = horizontalList;
        this.context = context;
        this.f12CallBack = callBack;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_sectionf12_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        SectionF12 f2 = sectionF2ArrayList.get(position);
        holder.txtID.setText("ID : " + f2.getId());

        holder.txt12Crop.setText("Crop F12 :\n" + f2.getF2c_crop_english_name());
        if(!StringUtils.isEmpty(f2.getF12_other())){
            holder.txtF12CropOther.setText("Crop F12 Other :\n" + f2.getF12_other());
        }else {
            holder.txtF12CropOther.setText("Crop F12 Other :\n" + "N/A");
        }

        holder.txt13.setText("F13 :\n" + f2.getF13());
        holder.txt14.setText("F14 :\n" + f2.getF14());
        holder.txt15.setText("F15 :\n" + f2.getF15());


        if(!StringUtils.isEmpty(f2.getF13_other())){
            holder.txt13_other.setText("F13 Other :\n" + f2.getF13_other());
        }else {
            holder.txt13_other.setText("F13 Other :\n" + "N/A");
        }

        if(!StringUtils.isEmpty(f2.getF14_other())){
            holder.txt14_other.setText("F14 Other :\n" + f2.getF14_other());
        }else {
            holder.txt14_other.setText("F14 Other :\n" + "N/A");
        }

        if(!StringUtils.isEmpty(f2.getF15_other())){
            holder.txt15_other.setText("F15 Other :\n" + f2.getF15_other());
        }else {
            holder.txt15_other.setText("F15 Other :\n" + "N/A");
        }



        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f12CallBack.onF12Delete(position);

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f12CallBack.onF12Edit(position);

            }
        });




    }


    @Override
    public int getItemCount() {
        return sectionF2ArrayList.size();
    }


}