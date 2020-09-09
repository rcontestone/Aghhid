package com.rcons.fcallbacks.Section_B_Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.R;

import java.util.ArrayList;


public class Section_C2Adapter extends RecyclerView.Adapter<Section_C2Adapter.MyViewHolder> {
    ArrayList<Section_C2Model> section_c2ModelsArrayList;
    com.rcons.fcallbacks.Section_B_Recycler.onEachIDClick onEachIDClick;
    private Context context;

    public Section_C2Adapter(ArrayList<Section_C2Model> horizontalList, Context context) {
        this.section_c2ModelsArrayList = horizontalList;
        this.context = context;
        onEachIDClick = (com.rcons.fcallbacks.Section_B_Recycler.onEachIDClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_child_id, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Section_C2Model section_c2Model = section_c2ModelsArrayList.get(position);
        holder.txtID.setText("Child ID : " + section_c2Model.getId());
        holder.txtage.setText("Age : " + section_c2Model.getAge());
        String Gender = section_c2Model.getGender();
        if (Gender.equalsIgnoreCase("1")) {
            holder.txtgender.setText("Gender : Female");
        } else if (Gender.equalsIgnoreCase("2")) {
            holder.txtgender.setText("Gender : Male");
        }
        holder.txtgrade.setText("Grade : " + section_c2Model.getGrade());
        holder.mainLayout.setOnClickListener(view -> onEachIDClick.onEachIDClick(section_c2Model));


    }

    @Override
    public int getItemCount() {
        return section_c2ModelsArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtID;
        private final TextView txtage;
        private final TextView txtgender;
        private final TextView txtgrade;

        public MyViewHolder(View view) {
            super(view);
            txtID = view.findViewById(R.id.txtID);
            txtage = view.findViewById(R.id.txtage);
            txtgrade = view.findViewById(R.id.txtgrade);
            txtgender = view.findViewById(R.id.txtgender);


            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


}