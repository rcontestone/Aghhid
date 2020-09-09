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

import com.rcons.fcallbacks.Model.Village;
import com.rcons.fcallbacks.R;


import java.util.ArrayList;
import java.util.List;


public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.MyViewHolder> implements Filterable {
    private Context context;
    ArrayList<Village> villageArrayList;
    private CustomFilter filter;
    com.rcons.fcallbacks.Interfaces.onVillageClick onVillageClick;
    String packaging = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtVillage;
        private final TextView txtUnionConcilName;
        private final TextView txtTehsilName;


        public MyViewHolder(View view) {
            super(view);
            txtVillage = view.findViewById(R.id.txtVillage);
            txtUnionConcilName = view.findViewById(R.id.txtUnionConcilName);
            txtTehsilName = view.findViewById(R.id.txtTehsilName);

            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public VillageAdapter(ArrayList<Village> horizontalList, Context context) {
        this.villageArrayList = horizontalList;
        this.context = context;
        onVillageClick = (com.rcons.fcallbacks.Interfaces.onVillageClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_village, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Village village = villageArrayList.get(position);
        holder.txtVillage.setText("Village: "+village.getVillageID()+" - "+village.getVillageName() );
        holder.txtUnionConcilName.setText("UC: "+village.getUnionConcilID()+" - "+village.getUnionConcilName() );
        holder.txtTehsilName.setText("Tehsil: "+village.getTehsilID()+" - "+village.getTehsilName() );

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVillageClick.onEachVillageClick(village);

            }
        });




    }


    @Override
    public int getItemCount() {
        return villageArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(villageArrayList, this);
        }

        return filter;
    }

    public class CustomFilter extends Filter {

        VillageAdapter adapter;
        List<Village> filterList;

        public CustomFilter(List<Village> filterList, VillageAdapter adapter) {
            this.adapter = adapter;
            this.filterList = filterList;

        }

        //FILTERING OCURS
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            //CHECK CONSTRAINT VALIDITY
            if (constraint != null && constraint.length() > 0) {
                //CHANGE TO UPPER
                constraint = constraint.toString().toUpperCase();
                //STORE OUR FILTERED PLAYERS
                ArrayList<Village> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    //CHECK
                    if (filterList.get(i).getVillageName().toLowerCase().contains(((String) constraint).toLowerCase()) || filterList.get(i).getUnionConcilName().toLowerCase().contains(((String) constraint).toLowerCase())) {
                        //ADD PLAYER TO FILTERED PLAYERS
                        filteredPlayers.add(filterList.get(i));
                    }
                }

                results.count = filteredPlayers.size();
                results.values = filteredPlayers;
            } else {
                results.count = filterList.size();
                results.values = filterList;

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            adapter.villageArrayList = (ArrayList<Village>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}