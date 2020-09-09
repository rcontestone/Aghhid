package com.rcons.fcallbacks.Summary;

import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;


import java.util.ArrayList;

public class View_Summary extends AppCompatActivity {

    DatabaseAdapter databaseAccess;

    Cursor cursor;
    TextView txtNewCallCounter_total;
    TextView txtPendingCallCounter_total;
    TextView txtCompletedCallCounter_total;
    TextView txtSuccessfullCallCounter_total;
    TextView tvusername;
    TextView tvDate;
    RecyclerView recyler_view_summary;
    int newCallCounter_total = 0;
    int pendingCallCounter_total = 0;
    int completedCallCounter_total = 0;
    int SuccessfullCallCounter_total = 0;
    int newCallCounter_users = 0;
    int pendingCallCounter_users = 0;
    int completedCallCounter_users = 0;
    int SuccessfullCallCounter_users = 0;
    TextView newCalls_tv, pendCalls_tv, compCalls_tv, successCalls_tv, txtWeek;
    LinearLayoutManager manager;
    String userName;
    String day, month, year;
    summaryadapter adapter;
    ArrayList<summary_items> myListDataArrayList;

    LinearLayout layout_newCalls, layout_pendCalls, layout_completedCalls, layout_SuccessfullCalls;

    ArrayList<String> users = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);
        txtNewCallCounter_total = findViewById(R.id.txtNewCallCounter_total);
        txtPendingCallCounter_total = findViewById(R.id.txtPendingCallCounter_total);
        txtCompletedCallCounter_total = findViewById(R.id.txtCompletedCallCounter_total);
        txtSuccessfullCallCounter_total = findViewById(R.id.txtSuccessfullCallCounter_total);
        tvusername = findViewById(R.id.tvusername);
        tvDate = findViewById(R.id.tvDate);
        recyler_view_summary = findViewById(R.id.recyler_view_summary);

        layout_newCalls = findViewById(R.id.layout_newCalls);
        layout_pendCalls = findViewById(R.id.layout_pendCalls);
        layout_completedCalls = findViewById(R.id.layout_completedCalls);
        layout_SuccessfullCalls = findViewById(R.id.layout_SuccessfullCalls);

        newCalls_tv = findViewById(R.id.newCalls_tv);
        pendCalls_tv = findViewById(R.id.pendCalls_tv);
        compCalls_tv = findViewById(R.id.compCalls_tv);
        successCalls_tv = findViewById(R.id.successCalls_tv);

        txtWeek = (TextView) findViewById(R.id.txtWeek);
        userName = getIntent().getStringExtra("username");
        tvusername.setText(userName);

        day = MubDateAndTime.INSTANCE.getcurrentTime(2);
        month = MubDateAndTime.INSTANCE.getcurrentTime(1);
        year = MubDateAndTime.INSTANCE.getcurrentTime(0);

        tvDate.setText("Date : " + day + "/" + month + "/" + year);
        databaseAccess = new DatabaseAdapter(View_Summary.this);
        databaseAccess.Open();
        FetchCallsCounters();
        readUsers();
        readData();
        screensize();
        GetDataFromBasicTable();
    }


    void FetchCallsCounters() {
        try {
            newCallCounter_total = databaseAccess.getFarmerAllCallCount_summary();
            pendingCallCounter_total = databaseAccess.getPendingCall_summary();
            completedCallCounter_total = databaseAccess.getCompletedCallCounter_summary();
            SuccessfullCallCounter_total = databaseAccess.getSuccessFullCallCounter_summary();
            txtNewCallCounter_total.setText(String.valueOf(newCallCounter_total));
            txtPendingCallCounter_total.setText("" + pendingCallCounter_total);
            txtCompletedCallCounter_total.setText("" + completedCallCounter_total);
            txtSuccessfullCallCounter_total.setText("" + SuccessfullCallCounter_total);
        } catch (Exception e) {
            MubLog.cpnsoleLog(e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void readData() {
        try {
            manager = new LinearLayoutManager(View_Summary.this, RecyclerView.VERTICAL, false);
            recyler_view_summary.setLayoutManager(manager);
            myListDataArrayList = new ArrayList<>();
            if (!users.isEmpty()) {
                for (int i = 0; i < users.size(); i++) {
                    newCallCounter_users = databaseAccess.getNewCallsCount(users.get(i));
                    pendingCallCounter_users = databaseAccess.getPendingCall(users.get(i));
                    completedCallCounter_users = databaseAccess.getCompletedCallCounter(users.get(i));
                    SuccessfullCallCounter_users = databaseAccess.getSuccessFullCallCounter(users.get(i));
                    summary_items myListData = new summary_items();
                    myListData.setUsername(users.get(i));
                    myListData.setNewCalls(Integer.toString(newCallCounter_users));
                    myListData.setPendingCalls(Integer.toString(pendingCallCounter_users));
                    myListData.setcompletedCallslCalls(Integer.toString(completedCallCounter_users));
                    myListData.setSuccessfullCalls(Integer.toString(SuccessfullCallCounter_users));
                    myListDataArrayList.add(myListData);

                    Toast.makeText(View_Summary.this, (users.get(i)), Toast.LENGTH_SHORT).show();
                }

            }
            if (myListDataArrayList != null && myListDataArrayList.size() > 0) {
                adapter = new summaryadapter(myListDataArrayList, View_Summary.this);
                recyler_view_summary.setAdapter(adapter);
            }

        } catch (Exception e) {
            Toast.makeText(View_Summary.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void readUsers() {
        try {
            Cursor cursor;
            databaseAccess = new DatabaseAdapter(View_Summary.this);
            databaseAccess.Open();
            cursor = databaseAccess.getUsers();
            if (cursor != null && cursor.getCount() > 0) {
                recyler_view_summary.setVisibility(View.VISIBLE);
                if (cursor.moveToFirst()) {
                    do {
                        String str_username = (cursor.getString(cursor.getColumnIndex("rcons_user")));
                        if (!users.contains(str_username)) {
                            users.add(str_username);
                        }
                    } while (cursor.moveToNext());

                    cursor.close();
                }
                Toast.makeText(View_Summary.this, Integer.toString(users.size()), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(View_Summary.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void screensize() {

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:

                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:

                setMargins(this.layout_newCalls, -5, 0, 0, 0);
                setMargins(this.layout_pendCalls, -10, 0, 0, 0);
                setMargins(this.layout_completedCalls, -5, 0, 0, 0);
                setMargins(this.layout_SuccessfullCalls, -4, 0, 0, 0);
                newCalls_tv.setText("New");
                pendCalls_tv.setText("Pend");
                compCalls_tv.setText("Comp");
                successCalls_tv.setText("Success");
//                newCalls_tv.setHeight(40);
//                pendCalls_tv.setHeight(40);
//                compCalls_tv.setHeight(40);
//                successCalls_tv.setHeight(40);

                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:

                break;
        }
    }

    void setMargins(View view, int left, int top, int right, int bottom) {
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

    }

    void GetDataFromBasicTable() {

        cursor = databaseAccess.getUsers();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            txtWeek.setText("Month "+cursor.getString(cursor.getColumnIndex("month")));

        }
    }
}
