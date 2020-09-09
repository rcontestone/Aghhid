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

import com.rcons.fcallbacks.Model.District;
import com.rcons.fcallbacks.R;


import java.util.ArrayList;
import java.util.List;


public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.MyViewHolder> implements Filterable {
    private Context context;
    ArrayList<District> districts;
    private CustomFilter filter;
    com.rcons.fcallbacks.Interfaces.onDistrictClick onDistrictClick;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtDistrict;


        public MyViewHolder(View view) {
            super(view);
            txtDistrict = view.findViewById(R.id.txtDistrict);

            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public DistrictAdapter(ArrayList<District> horizontalList, Context context) {
        this.districts = horizontalList;
        this.context = context;
        onDistrictClick = (com.rcons.fcallbacks.Interfaces.onDistrictClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_district, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final District district = districts.get(position);
        holder.txtDistrict.setText(district.getDistrictID() +" - " + district.getDistrictName());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDistrictClick.onEachDistrictClick(district);

            }
        });




    }


    @Override
    public int getItemCount() {
        return districts.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(districts, this);
        }

        return filter;
    }

    public class CustomFilter extends Filter {

        DistrictAdapter adapter;
        List<District> filterList;

        public CustomFilter(List<District> filterList, DistrictAdapter adapter) {
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
                ArrayList<District> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    //CHECK
                    if (filterList.get(i).getDistrictName().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.districts = (ArrayList<District>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}