package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Interfaces.onFarmerClick;
import com.rcons.fcallbacks.Model.Farmer;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;

public class EditFarmerAdapter extends RecyclerView.Adapter<EditFarmerAdapter.MyViewHolder> implements Filterable {
    ArrayList<Farmer> farmerArrayList;
    onFarmerClick farmerClick;
    private Context context;
    private CustomFilter filter;

    public EditFarmerAdapter(ArrayList<Farmer> horizontalList, Context context) {
        this.farmerArrayList = horizontalList;
        this.context = context;
        farmerClick = (onFarmerClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_edit_form_farmer, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Farmer farmer = farmerArrayList.get(position);
        holder.txtFarmerID.setText("Person ID : " + farmer.getFarmerID());
        holder.txtFarmerName.setText("Tehsil : " + farmer.getFarmerName());

        holder.mainLayout.setOnClickListener(view -> farmerClick.onEachFarmerClick(farmer));


    }

    @Override
    public int getItemCount() {
        return farmerArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(farmerArrayList, this);
        }

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final RelativeLayout mainLayout;
        private final TextView txtFarmerID;
        private final TextView txtFarmerName;

        public MyViewHolder(View view) {
            super(view);
            txtFarmerID = view.findViewById(R.id.txtFarmerID);
            txtFarmerName = view.findViewById(R.id.txtFarmerName);

            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }

    public class CustomFilter extends Filter {

        EditFarmerAdapter adapter;
        List<Farmer> filterList;

        public CustomFilter(List<Farmer> filterList, EditFarmerAdapter adapter) {
            this.adapter = adapter;
            this.filterList = filterList;

        }

        //FILTERING OCURS
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Farmer> filteredPlayers = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getFarmerID().toLowerCase().contains(((String) constraint).toLowerCase()) || filterList.get(i).getFarmerName().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.farmerArrayList = (ArrayList<Farmer>) results.values;

            adapter.notifyDataSetChanged();
        }

    }
}

