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

import com.rcons.fcallbacks.Model.EnumNameModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;


public class EnumNameAdapter extends RecyclerView.Adapter<EnumNameAdapter.MyViewHolder> implements Filterable {
    ArrayList<EnumNameModel> enumNameArrayList;
    com.rcons.fcallbacks.Interfaces.onEnumNameClick onEnumNameClick;
    String packaging = "";
    private Context context;
    private CustomFilter filter;

    public EnumNameAdapter(ArrayList<EnumNameModel> horizontalList, Context context) {
        this.enumNameArrayList = horizontalList;
        this.context = context;
        onEnumNameClick = (com.rcons.fcallbacks.Interfaces.onEnumNameClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_enum_name, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final EnumNameModel enumNameCode = enumNameArrayList.get(position);
        holder.txtEnumName.setText(enumNameCode.getEnumCode() + " - " + enumNameCode.getEnumName());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEnumNameClick.onEachEnumNameClick(enumNameCode);

            }
        });


    }

    @Override
    public int getItemCount() {
        return enumNameArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(enumNameArrayList, this);
        }

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtEnumName;


        public MyViewHolder(View view) {
            super(view);
            txtEnumName = view.findViewById(R.id.txtEnumName);
            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }

    public class CustomFilter extends Filter {

        EnumNameAdapter adapter;
        List<EnumNameModel> filterList;

        public CustomFilter(List<EnumNameModel> filterList, EnumNameAdapter adapter) {
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
                ArrayList<EnumNameModel> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    //CHECK
                    if (filterList.get(i).getEnumName().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.enumNameArrayList = (ArrayList<EnumNameModel>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}