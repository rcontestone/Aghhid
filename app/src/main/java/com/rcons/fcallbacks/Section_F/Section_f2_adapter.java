package com.rcons.fcallbacks.Section_F;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;


public class Section_f2_adapter extends RecyclerView.Adapter<Section_f2_adapter.MyViewHolder> implements Filterable {
    ArrayList<Section_f2_items> section_f2_itemsArrayList;
    onf2Click onf2Click;
    private Context context;
    private CustomFilter filter;

    public Section_f2_adapter(ArrayList<Section_f2_items> horizontalList, Context context) {
        this.section_f2_itemsArrayList = horizontalList;
        this.context = context;
        onf2Click = (onf2Click) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.f2a_f2b_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Section_f2_items section_f2_items = section_f2_itemsArrayList.get(position);
        holder.txtF1Crop.setText(section_f2_items.getTxtF1Crop());
        holder.txtF1Crop_others.setText(section_f2_items.getTxtF1Crop_others());
        holder.txtF2aAcre.setText(section_f2_items.getTxtF2aAcre());
        holder.txtF2aCanal.setText(section_f2_items.getTxtF2aCanal());
        holder.txtF2bCrop.setText(section_f2_items.getTxtF2bCrop());
        holder.txtF2bCrop_others.setText(section_f2_items.getTxtF2bCrop_others());
        holder.txtF2bAcre.setText(section_f2_items.getTxtF2bAcre());
        holder.txtF2bCanal.setText(section_f2_items.getTxtF2bCanal());

        //  holder.mainLayout.setOnClickListener(view -> onf2Click.onf2Click(section_f2_items));
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onf2Click.onf2Click(section_f2_items);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return section_f2_itemsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(section_f2_itemsArrayList, this);
        }

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final RelativeLayout mainLayout;
        private final TextView txtF1Crop;
        private final TextView txtF1Crop_others;
        private final TextView txtF2aAcre;
        private final TextView txtF2aCanal;
        private final TextView txtF2bCrop;
        private final TextView txtF2bCrop_others;
        private final TextView txtF2bAcre;
        private final TextView txtF2bCanal;

        public MyViewHolder(View view) {
            super(view);
            txtF1Crop = view.findViewById(R.id.txtF1Crop);
            txtF1Crop_others = view.findViewById(R.id.txtF1Crop_others);
            txtF2aAcre = view.findViewById(R.id.txtF2aAcre);
            txtF2aCanal = view.findViewById(R.id.txtF2aCanal);
            txtF2bCrop = view.findViewById(R.id.txtF2bCrop);
            txtF2bCrop_others = view.findViewById(R.id.txtF2bCrop_others);
            txtF2bAcre = view.findViewById(R.id.txtF2bAcre);
            txtF2bCanal = view.findViewById(R.id.txtF2bCanal);

            mainLayout = view.findViewById(R.id.relativeLayout_f2a_f2b);


        }
    }

    public class CustomFilter extends Filter {

        Section_f2_adapter adapter;
        List<Section_f2_items> filterList;

        public CustomFilter(List<Section_f2_items> filterList, Section_f2_adapter adapter) {
            this.adapter = adapter;
            this.filterList = filterList;

        }

        //FILTERING OCURS
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Section_f2_items> filteredPlayers = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getTxtF1Crop().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.section_f2_itemsArrayList = (ArrayList<Section_f2_items>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}