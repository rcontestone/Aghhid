package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Interfaces.onEditSectionF2CallBack;
import com.rcons.fcallbacks.Model.SectionF2;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;


public class SectionF2Adapter extends RecyclerView.Adapter<SectionF2Adapter.MyViewHolder>  {
    private Context context;
    ArrayList<SectionF2> sectionF2ArrayList;
       String packaging = "";
    onEditSectionF2CallBack f2CallBack;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtID;
        private final TextView txtF1Crop;
        private final TextView txtF1CropOther;
        private final TextView txtF2aCanal;
        private final TextView txtF2aAcre;
        private final TextView txtF2bAcre;
        private final TextView txtF2bCrop;
        private final TextView txtF2bCropOther;
        private final TextView txtF2bCanal;
        private final ImageView btnEdit;
        private final ImageView btnDelete;



        public MyViewHolder(View view) {
            super(view);
            txtID = view.findViewById(R.id.txtID);
            txtF1Crop = view.findViewById(R.id.txtF1Crop);
            txtF1CropOther = view.findViewById(R.id.txtF1CropOther);
            txtF2aCanal = view.findViewById(R.id.txtF2aCanal);
            txtF2aAcre = view.findViewById(R.id.txtF2aAcre);
            txtF2bCrop = view.findViewById(R.id.txtF2bCrop);
            txtF2bCropOther = view.findViewById(R.id.txtF2bCropOther);
            txtF2bCanal = view.findViewById(R.id.txtF2bCanal);
            txtF2bAcre = view.findViewById(R.id.txtF2bAcre);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);


            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public SectionF2Adapter(ArrayList<SectionF2> horizontalList, Context context , onEditSectionF2CallBack callBack) {
        this.sectionF2ArrayList = horizontalList;
        this.context = context;
        this.f2CallBack = callBack;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_sectionf2_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        SectionF2 f2 = sectionF2ArrayList.get(position);
        holder.txtID.setText("ID : " + f2.getId());

        holder.txtF1Crop.setText("Crop F1 :\n" + f2.getF1crop_english_name());
        if(!StringUtils.isEmpty(f2.getF1_other())){
            holder.txtF1CropOther.setText("Crop F1 Other :\n" + f2.getF1_other());
        }else {
            holder.txtF1CropOther.setText("Crop F1 Other :\n" + "N/A");
        }

        holder.txtF2aAcre.setText("F2a Acre :\n" + f2.getF2a_acre());
        holder.txtF2aCanal.setText("F2a Kanal :\n" + f2.getF2a_kanal());




        if(!StringUtils.isEmpty(f2.getF2b_other())){
            holder.txtF2bCropOther.setText("2b Other : " + f2.getF2b_other());
        }else {
            holder.txtF2bCropOther.setText("2b Other :\n" + "N/A");
        }



        if(!StringUtils.isEmpty(f2.getF2bcrop_english_name())){
            holder.txtF2bCrop.setText("Crop F2b :\n" + f2.getF2bcrop_english_name());
          //  holder.txtF1CropOther.setText("Crop F1 Other :\n" + f2.getF1_other());
        }else {
            holder.txtF2bCrop.setText("Crop F2b :\n" + f2.getF2b_crop());
            //holder.txtF1CropOther.setText("Crop F1 Other :\n" + "N/A");
        }


        holder.txtF2bAcre.setText("F2b Arce : " + f2.getF2bAcre());
        holder.txtF2bCanal.setText("F2b Kanal : " + f2.getF2bKanal());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f2CallBack.onDelete(position);

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f2CallBack.onEdit(position);

            }
        });




    }


    @Override
    public int getItemCount() {
        return sectionF2ArrayList.size();
    }



}