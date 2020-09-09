package com.rcons.fcallbacks.TRT;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.LoginActivity;
import com.rcons.fcallbacks.Main.MainMenuActivity;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_B;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StartUpMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallScreen extends AppCompatActivity {

    @BindView(R.id.txt_phone_number)
    TextView txt_phone_number;

    @BindView(R.id.sync_out_layout)
    LinearLayout sync_out_layout;

    @BindView(R.id.callScreen)
    RelativeLayout callScreen;

    @BindView(R.id.btn_Sync)
    Button btn_Sync;

    @BindView(R.id.btn_Logout)
    Button btn_Logout;
    @BindView(R.id.btn_Exit)
    Button btn_Exit;

    @BindView(R.id.txt_city_name)
    TextView txt_city_name;

    @BindView(R.id.btnCallNow)
    FloatingActionButton btnCallNow;

    String username = "";
    String id = "";
    String emp_id = "";
    String order_id = "";
    String farmer_id = "";
    String sr_no = "";
    String phone_number = "";
    String status_code = "";
    String city_name = "";

    DatabaseAdapter databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_screen);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(CallScreen.this);
        databaseAccess.Open();

        GetData();


        btn_Sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallScreen.this, StartUpMainActivity.class);
                startActivity(intent);

            }
        });

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RConsUtils.LogoutUser();
                Intent intent = new Intent(CallScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogMessage(CallScreen.this, "Alert", "Are you sure you want to exit ?");
            }
        });

        btnCallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallScreen.this, pq_Section_B.class);
                intent.putExtra("city_name", city_name);
                intent.putExtra("phone_number", phone_number);
                intent.putExtra("id", id);
                intent.putExtra("emp_id", emp_id);
                intent.putExtra("order_id", order_id);
                intent.putExtra("farmer_id", farmer_id);
                intent.putExtra("sr_no", sr_no);
                startActivityForResult(intent, 77);
            }
        });

    }

    void GetData() {
        try {
            Cursor cursor = databaseAccess.gettrt_NewCalls();
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                status_code = cursor.getString(cursor.getColumnIndex("status_code"));
                city_name = cursor.getString(cursor.getColumnIndex("city_name"));
                phone_number = cursor.getString(cursor.getColumnIndex("phone_number"));
                id = cursor.getString(cursor.getColumnIndex("id"));
                emp_id = cursor.getString(cursor.getColumnIndex("emp_id"));
                order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                farmer_id = cursor.getString(cursor.getColumnIndex("farmer_id"));
                sr_no = cursor.getString(cursor.getColumnIndex("sr_no"));
                txt_phone_number.setText("Cell# " + phone_number);
                txt_city_name.setText(city_name);
            } else {
                txt_phone_number.setText("No Required Data Found");
                btnCallNow.setVisibility(View.GONE);
                txt_city_name.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 77) {
                GetData();
            }
        }


    }

    void ShowDialogMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.my_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnYes = dialogView.findViewById(R.id.btnYes);
        btnYes.setText("Yes");
        btnCancel.setText("No");
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }

}