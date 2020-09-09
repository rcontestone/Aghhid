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
import com.rcons.fcallbacks.Model.EnterFormModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;
import java.util.List;


public class EnterFormAdapter extends RecyclerView.Adapter<EnterFormAdapter.MyViewHolder> implements Filterable {
    private Context context;
    ArrayList<EnterFormModel> EnterFormModelArrayList;
    private CustomFilter filter;
    com.rcons.fcallbacks.Interfaces.onEnterFormClick onEnterFormClick;
    String packaging = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout mainLayout;
        private final TextView txtEmpID,txtFarmerID,txtOrderID;

        public MyViewHolder(View view) {
            super(view);
            txtEmpID = view.findViewById(R.id.txtEmpID);
            txtFarmerID = view.findViewById(R.id.txtFarmerID);
            txtOrderID = view.findViewById(R.id.txtOrderID);

            mainLayout = view.findViewById(R.id.mainLayout);


        }
    }


    public EnterFormAdapter(ArrayList<EnterFormModel> horizontalList, Context context) {
        this.EnterFormModelArrayList = horizontalList;
        this.context = context;
        onEnterFormClick = (com.rcons.fcallbacks.Interfaces.onEnterFormClick) context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_enter_form, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final EnterFormModel enterform = EnterFormModelArrayList.get(position);
        holder.txtEmpID.setText("EmpID: "+enterform.getEmp_id());
        holder.txtOrderID.setText("Order ID: "+enterform.getOrder_id());
        holder.txtFarmerID.setText("Farmer ID: "+enterform.getFarmerID());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEnterFormClick.onEachEnterFormClick(enterform);

            }
        });

    }

    @Override
    public int getItemCount() {
        return EnterFormModelArrayList.size();
    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(EnterFormModelArrayList, this);
        }

        return filter;
    }

    public class CustomFilter extends Filter {

        EnterFormAdapter adapter;
        List<EnterFormModel> filterList;

        public CustomFilter(List<EnterFormModel> filterList, EnterFormAdapter adapter) {
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
                ArrayList<EnterFormModel> filteredPlayers = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    //CHECK
                    if (filterList.get(i).getEmp_id().toLowerCase().contains(((String) constraint).toLowerCase()) || filterList.get(i).getFarmerID().toLowerCase().contains(((String) constraint).toLowerCase())) {
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

            adapter.EnterFormModelArrayList = (ArrayList<EnterFormModel>) results.values;

            adapter.notifyDataSetChanged();
        }

    }

}