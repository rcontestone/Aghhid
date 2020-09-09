package com.rcons.fcallbacks.Summary;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.R;


import java.util.ArrayList;
import java.util.List;

public class summaryadapter extends RecyclerView.Adapter<summaryadapter.ViewHolder> {
    private static String TAG = "Adapter";
    ArrayList<summary_items> myListDataArrayList;
    List<summary_items> modelList;
    String packaging = "";
    DatabaseAdapter databaseAccess;
    private String username;
    private String newCalls;
    private String pendingCalls;
    private String successfullCalls;
    private summary_items[] listdata;
    private Context context;
    private ArrayList<String> mCleanCopyDataset;
    private ArrayList<String> mDataset;

    public summaryadapter(ArrayList<summary_items> horizontalList, Context context) {
        this.myListDataArrayList = horizontalList;
        this.context = context;

    }

    // RecyclerView recyclerView;
    public summaryadapter(summary_items[] listdata, DatabaseAdapter databaseAccess) {
        this.listdata = listdata;
        this.databaseAccess = databaseAccess;
    }

    public static int dpToPx(int dp, View itemView) {
        float density = itemView.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_records, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final summary_items myListData = myListDataArrayList.get(position);

        holder.txtUsername.setText(myListData.getUsername());
        holder.txtNewCallCounter_user.setText(myListData.getNewCalls());
        holder.txtPendingCallCounter_user.setText(myListData.getPendingCalls());
        holder.txtCompletedCallCounter_user.setText(myListData.getcompletedCallsCalls());
        holder.txtSuccessfullCallCounter_user.setText(myListData.getSuccessfullCalls());

    }

    @Override
    public int getItemCount() {
        return myListDataArrayList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtUsername, txtNewCallCounter_user, txtPendingCallCounter_user, txtCompletedCallCounter_user, txtSuccessfullCallCounter_user;
        public RelativeLayout relativeLayout;
        public LinearLayout layout_newCalls,layout_pendCalls,layout_completedCalls,layout_SuccessfullCalls;

        public ViewHolder(View itemView) {
            super(itemView);
            int screenSize = itemView.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
            this.txtUsername = itemView.findViewById(R.id.txtUsername);
            this.txtNewCallCounter_user = itemView.findViewById(R.id.txtNewCallCounter_user);
            this.txtPendingCallCounter_user = itemView.findViewById(R.id.txtPendingCallCounter_user);
            this.txtCompletedCallCounter_user = itemView.findViewById(R.id.txtCompletedCallCounter_user);
            this.txtSuccessfullCallCounter_user = itemView.findViewById(R.id.txtSuccessfullCallCounter_user);

            this.layout_newCalls = itemView.findViewById(R.id.layout_newCalls);
            this.layout_pendCalls = itemView.findViewById(R.id.layout_pendCalls);
            this.layout_completedCalls = itemView.findViewById(R.id.layout_completedCalls);
            this.layout_SuccessfullCalls = itemView.findViewById(R.id.layout_SuccessfullCalls);

            relativeLayout = itemView.findViewById(R.id.relativeLayout_summary);
            switch (screenSize) {
                case Configuration.SCREENLAYOUT_SIZE_LARGE:

                    break;
                case Configuration.SCREENLAYOUT_SIZE_NORMAL:

                    setMargins(this.layout_newCalls,-5,0,0,0);
                    setMargins(this.layout_pendCalls,-10,0,0,0);
                    setMargins(this.layout_completedCalls,-5,0,0,0);
                    setMargins(this.layout_SuccessfullCalls,-4,0,0,0);
                    this.txtUsername.setWidth(dpToPx(70, itemView));
                    this.txtNewCallCounter_user.setWidth(dpToPx(10, itemView));
                    this.txtPendingCallCounter_user.setWidth(dpToPx(70, itemView));
                    this.txtCompletedCallCounter_user.setWidth(dpToPx(10, itemView));
                    this.txtSuccessfullCallCounter_user.setWidth(dpToPx(10, itemView));
                    break;
                case Configuration.SCREENLAYOUT_SIZE_SMALL:

                    break;
            }
        }
        public static void setMargins(View view, int left, int top, int right, int bottom) {
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

                final float scale = view.getResources().getDisplayMetrics().density;
                // convert the DP into pixel
                int l = (int) (left * scale + 0.5f);
                int r = (int) (right * scale + 0.5f);
                int t = (int) (top * scale + 0.5f);
                int b = (int) (bottom * scale + 0.5f);

                p.setMargins(l, t, r, b);
                view.requestLayout();
            }

        }}
    }