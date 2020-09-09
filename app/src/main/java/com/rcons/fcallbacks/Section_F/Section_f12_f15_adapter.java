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


public class Section_f12_f15_adapter extends RecyclerView.Adapter<Section_f12_f15_adapter.MyViewHolder> implements Filterable {
    ArrayList<Section_f12_f15_items> section_f12_f15_itemsArrayList;
    onf12_f15Click onf12_f15Click;
    private Context context;
    private CustomFilter filter;

    public Section_f12_f15_adapter(ArrayList<Section_f12_f15_items> horizontalList, Context context) {
        this.section_f12_f15_itemsArrayList = horizontalList;
        this.context = context;
        onf12_f15Click = (onf12_f15Click) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.f12_f15_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Section_f12_f15_items section_f12_f15_items = section_f12_f15_itemsArrayList.get(position);
        holder.txtF12.setText(section_f12_f15_items.getTxtF12());
        holder.txtF12_other.setText(section_f12_f15_items.getTxtF12_other());
        holder.txtF13.setText(section_f12_f15_items.getTxtF13());
        holder.txtF13_other.setText(section_f12_f15_items.getTxtF13_other());
        holder.txtF14.setText(section_f12_f15_items.getTxtF14());
        holder.txtF14_other.setText(section_f12_f15_items.getTxtF14_other());
        holder.txtF15.setText(section_f12_f15_items.getTxtF15());
        holder.txtF15_other.setText(section_f12_f15_items.getTxtF15_other());

        //  holder.mainLayout.setOnClickListener(view -> onf2Click.onf2Click(section_f2_items));
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onf12_f15Click.onf12_f15Click(section_f12_f15_items);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return section_f12_f15_itemsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(section_f12_f15_itemsArrayList, this);
        }

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final RelativeLayout mainLayout;

        private final TextView txtF12;
        private final TextView txtF12_other;
        private final TextView txtF13;
        private final TextView txtF13_other;
        private final TextView txtF14;
        private final TextView txtF14_other;
        private final TextView txtF15;
        private final TextView txtF15_other;


        public MyViewHolder(View view) {
            super(view);
            txtF12 = view.findViewById(R.id.txtF12);
            txtF12_other = view.findViewById(R.id.txtF12_other);
            txtF13 = view.findViewById(R.id.txtF13);
            txtF13_other = view.findViewById(R.id.txtF13_other);
            txtF14 = view.findViewById(R.id.txtF14);
            txtF14_other = view.findViewById(R.id.txtF14_other);
            txtF15 = view.findViewById(R.id.txtF15);
            txtF15_other = view.findViewById(R.id.txtF15_other);

            mainLayout = view.findViewById(R.id.relativeLayout_f12_f15);


        }
    }

    public class CustomFilter extends Filter {

        Section_f12_f15_adapter adapter;
        List<Section_f12_f15_items> filterList;

        public CustomFilter(List<Section_f12_f15_items> filterList, Section_f12_f15_adapter adapter) {
            this.adapter = adapter;
            this.filterList = filterList;

        }

        //FILTERING OCURS
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Section_f12_f15_items> filteredPlayers = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getTxtF12().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.section_f12_f15_itemsArrayList = (ArrayList<Section_f12_f15_items>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}