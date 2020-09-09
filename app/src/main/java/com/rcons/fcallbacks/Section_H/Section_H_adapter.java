package com.rcons.fcallbacks.Section_H;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Section_F.Section_f12_f15_items;
import com.rcons.fcallbacks.Section_F.onf12_f15Click;

import java.util.ArrayList;
import java.util.List;


public class Section_H_adapter extends RecyclerView.Adapter<Section_H_adapter.MyViewHolder> {
    ArrayList<Crops_items> crops_itemsArrayList;
    private Context context;

    public Section_H_adapter(ArrayList<Crops_items> horizontalList, Context context) {
        this.crops_itemsArrayList = horizontalList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.major_crops, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Crops_items crops_items = crops_itemsArrayList.get(position);
        holder.crop_name.setText(crops_items.getCrop_name());
        holder.qestion_text.setText(crops_items.getQestion_text());
        holder.rbtn_ans1.setText(crops_items.getRbtn_ans1());
        holder.rbtn_ans2.setText(crops_items.getRbtn_ans2());
        holder.rbtn_ans3.setText(crops_items.getRbtn_ans3());
        holder.rbtn_ans4.setText(crops_items.getRbtn_ans4());
        holder.rbtn_ans5.setText(crops_items.getRbtn_ans5());
        holder.rbtn_ans6.setText(crops_items.getRbtn_ans6());
        holder.rbtn_ans7.setText(crops_items.getRbtn_ans7());
        holder.rbtn_ans8.setText(crops_items.getRbtn_ans8());


    }

    @Override
    public int getItemCount() {
        return crops_itemsArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final RelativeLayout mainLayout;

        private final TextView crop_name;
        private final TextView qestion_text;
        private final RadioButton rbtn_ans1;
        private final RadioButton rbtn_ans2;
        private final RadioButton rbtn_ans3;
        private final RadioButton rbtn_ans4;
        private final RadioButton rbtn_ans5;
        private final RadioButton rbtn_ans6;
        private final RadioButton rbtn_ans7;
        private final RadioButton rbtn_ans8;



        public MyViewHolder(View view) {
            super(view);
            crop_name = view.findViewById(R.id.crop_name);
            qestion_text = view.findViewById(R.id.qestion_text);
            rbtn_ans1 = view.findViewById(R.id.rbtn_ans1);
            rbtn_ans2 = view.findViewById(R.id.rbtn_ans2);
            rbtn_ans3 = view.findViewById(R.id.rbtn_ans3);
            rbtn_ans4 = view.findViewById(R.id.rbtn_ans4);
            rbtn_ans5 = view.findViewById(R.id.rbtn_ans5);
            rbtn_ans6 = view.findViewById(R.id.rbtn_ans6);
            rbtn_ans7 = view.findViewById(R.id.rbtn_ans7);
            rbtn_ans8 = view.findViewById(R.id.rbtn_ans8);

            mainLayout = view.findViewById(R.id.relativeLayout_crops);


        }
    }


}