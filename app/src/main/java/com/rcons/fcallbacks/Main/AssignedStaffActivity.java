package com.rcons.fcallbacks.Main;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.R;


public class AssignedStaffActivity extends AppCompatActivity {

    String farmerID;
    DatabaseAdapter databaseAccess;
    Cursor cursor;
    ImageView btnBack;
    TextView AoName, FaName, AiName, StaffCode,StaffName,ExtSelectActivity, ActivityPerformed, ActivityDatetime, DetailsOfActivity, VillageName,Designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_staff);

        databaseAccess = new DatabaseAdapter(AssignedStaffActivity.this);
        databaseAccess.Open();

        farmerID = getIntent().getStringExtra("farmerID");

        btnBack = findViewById(R.id.btnBack);
        AoName = (TextView) findViewById(R.id.ao_name);
        FaName = (TextView) findViewById(R.id.fa_name);
        AiName = (TextView) findViewById(R.id.ai_name);

        StaffCode = (TextView) findViewById(R.id.staff_code);
        StaffName = (TextView) findViewById(R.id.staff_name);
        ExtSelectActivity = (TextView) findViewById(R.id.ext_select_activity);
        ActivityPerformed = (TextView) findViewById(R.id.activity_performed);
        ActivityDatetime = (TextView) findViewById(R.id.activity_datetime);
        DetailsOfActivity = (TextView) findViewById(R.id.details_of_activity);
        VillageName = (TextView) findViewById(R.id.village_name);
        Designation = (TextView) findViewById(R.id.designation);

        //   getSupportActionBar().setTitle("Assigned Staff");

        cursor = databaseAccess.getAssignStaffInformation(farmerID);
        cursor.moveToFirst();


        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }
        });


        if (cursor != null && cursor.getCount() > 0) {
            AoName.setText(cursor.getString(cursor.getColumnIndex("ao_assigned")));
            FaName.setText(cursor.getString(cursor.getColumnIndex("fa_assigned")));
            AiName.setText(cursor.getString(cursor.getColumnIndex("ai_assigned")));

            StaffCode.setText("Staff ID " +cursor.getString(cursor.getColumnIndex("emp_id")));
            Designation.setText(cursor.getString(cursor.getColumnIndex("empost_designation")));
            StaffName.setText(cursor.getString(cursor.getColumnIndex("emp_fullname")));
//            ExtSelectActivity.setText(cursor.getString(cursor.getColumnIndex("ext_select_activity")));
//            ActivityPerformed.setText(cursor.getString(cursor.getColumnIndex("activity_performed")));
//            ActivityDatetime.setText(cursor.getString(cursor.getColumnIndex("activity_datetime")));
//            DetailsOfActivity.setText(cursor.getString(cursor.getColumnIndex("details_of_activity")));
//            VillageName.setText(cursor.getString(cursor.getColumnIndex("village")));


        }

    }
}
