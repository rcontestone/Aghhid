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

import com.rcons.fcallbacks.Model.ZaatModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;


public class ZaatAdapter extends RecyclerView.Adapter<ZaatAdapter.MyViewHolder> implements Filterable {
    ArrayList<ZaatModel> zaatArrayList;
    com.rcons.fcallbacks.Interfaces.onZaatClick onZaatClick;
    String packaging = "";
    private Context context;
    private CustomFilter filter;

    public ZaatAdapter(ArrayList<ZaatModel> horizontalList, Context context) {
        this.zaatArrayList = horizontalList;
        this.context = context;
        onZaatClick = (com.rcons.fcallbacks.Interfaces.onZaatClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_zaat, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ZaatModel zaat = zaatArrayList.get(position);
        holder.txtZaatName.setText(zaat.getZaatCode() + " ~ " + zaat.getZaatName());


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onZaatClick.onEachZaatClick(zaat);

            }
        });


    }

    @Override
    public int getItemCount() {
        return zaatArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(zaatArrayList, this);
        }

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtZaatName;


        public MyViewHolder(View view) {
            super(view);
            txtZaatName = view.findViewById(R.id.txtZaatName);
            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public class CustomFilter extends Filter {

        ZaatAdapter adapter;
        List<ZaatModel> filterList;

        public CustomFilter(List<ZaatModel> filterList, ZaatAdapter adapter) {
            this.adapter = adapter;
            this.filterList = filterList;

        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            //CHECK CONSTRAINT VALIDITY
            if (constraint != null && constraint.length() > 0) {
                //CHANGE TO UPPER
                constraint = constraint.toString().toUpperCase();
                //STORE OUR FILTERED PLAYERS
                ArrayList<ZaatModel> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    //CHECK
                    if (filterList.get(i).getZaatName().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.zaatArrayList = (ArrayList<ZaatModel>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}