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

import com.rcons.fcallbacks.Model.Tehsil;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;

public class SelectTehsilAdapter extends RecyclerView.Adapter<SelectTehsilAdapter.MyViewHolder> implements Filterable {
    private Context context;
    ArrayList<Tehsil> tehsilArrayList;
    private CustomFilter filter;
    com.rcons.fcallbacks.Interfaces.onTehsilClick onTehsilClick;
    String packaging = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtTehsil;


        public MyViewHolder(View view) {
            super(view);
            txtTehsil = view.findViewById(R.id.txtTehsil);

            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public SelectTehsilAdapter(ArrayList<Tehsil> horizontalList, Context context) {
        this.tehsilArrayList = horizontalList;
        this.context = context;
        onTehsilClick = (com.rcons.fcallbacks.Interfaces.onTehsilClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_tehsil, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Tehsil tehsil = tehsilArrayList.get(position);
        holder.txtTehsil.setText(tehsil.getTehsilID()+" - "+tehsil.getTehsilName() );

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTehsilClick.onEachTehsilClick(tehsil);

            }
        });




    }


    @Override
    public int getItemCount() {
        return tehsilArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(tehsilArrayList, this);
        }

        return filter;
    }

    public class CustomFilter extends Filter {

        SelectTehsilAdapter adapter;
        List<Tehsil> filterList;

        public CustomFilter(List<Tehsil> filterList, SelectTehsilAdapter adapter) {
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
                ArrayList<Tehsil> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    //CHECK
                    if (filterList.get(i).getTehsilName().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.tehsilArrayList = (ArrayList<Tehsil>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}