package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Interfaces.onEditSection2FcCallBack;
import com.rcons.fcallbacks.Model.SectionF2C;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;


public class SectionF2CAdapter extends RecyclerView.Adapter<SectionF2CAdapter.MyViewHolder> {
    private Context context;
    ArrayList<SectionF2C> sectionF2ArrayList;
      String packaging = "";
    onEditSection2FcCallBack f2CallBack;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtID;
        private final TextView txtF2CCrop;
        private final TextView txtF2CCropOther;
        private final TextView txtF2cCanal;
        private final TextView txtF2cAcre;

        private final ImageView btnEdit;
        private final ImageView btnDelete;



        public MyViewHolder(View view) {
            super(view);
            txtID = view.findViewById(R.id.txtID);
            txtF2CCrop = view.findViewById(R.id.txtF2CCrop);
            txtF2CCropOther = view.findViewById(R.id.txtF2CCropOther);
            txtF2cCanal = view.findViewById(R.id.txtF2cCanal);
            txtF2cAcre = view.findViewById(R.id.txtF2cAcre);


            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);


            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public SectionF2CAdapter(ArrayList<SectionF2C> horizontalList, Context context , onEditSection2FcCallBack callBack) {
        this.sectionF2ArrayList = horizontalList;
        this.context = context;
        this.f2CallBack = callBack;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_sectionf2c_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        SectionF2C f2 = sectionF2ArrayList.get(position);
        holder.txtID.setText("ID : " + f2.getId());

        holder.txtF2CCrop.setText("Crop F2c :\n" + f2.getF2c_crop_english_name());
        if(!StringUtils.isEmpty(f2.getF2c_other())){
            holder.txtF2CCropOther.setText("Crop F2c Other :\n" + f2.getF2c_other());
        }else {
            holder.txtF2CCropOther.setText("Crop F2c Other :\n" + "N/A");
        }

        holder.txtF2cAcre.setText("F2a Acre :\n" + f2.getF2c_acre());
        holder.txtF2cCanal.setText("F2a Kanal :\n" + f2.getF2c_kanal());




        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f2CallBack.onSectionF2cDelete(position);

            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f2CallBack.onSectionF2cEdit(position);

            }
        });




    }


    @Override
    public int getItemCount() {
        return sectionF2ArrayList.size();
    }



}