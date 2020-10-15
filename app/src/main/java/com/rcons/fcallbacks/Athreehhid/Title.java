package com.rcons.fcallbacks.Athreehhid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_A;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Title extends AppCompatActivity {
    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;
    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

    @BindView(R.id.edt_hhid)
    EditText edt_hhid;

    @BindView(R.id.edt_headname)
    EditText edt_headname;

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.btnBack)
    ImageView btniBack;

    String village_id = "";
    String village_name = "";
    String hhid = "";
    String head_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);

        village_id = getIntent().getStringExtra("villageID");
        village_name = getIntent().getStringExtra("Village_name");

        txt_School_Code.setText("Village Code : " + village_id);
        txt_Student_id.setText("Village Name : " + village_name);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hhid = edt_hhid.getText().toString();
                head_name = edt_headname.getText().toString();
                if (StringUtils.isEmpty(hhid)) {
                    toastMessage("Please Specify hhid");
                } else if (StringUtils.isEmpty(head_name)) {
                    toastMessage("Please Specify head name");
                } else {
                    Intent intent = new Intent(Title.this, pq_Section_A.class);
                    intent.putExtra("scode", village_id);
                    intent.putExtra("village_name", village_name);
                    intent.putExtra("studentid", hhid);
                    intent.putExtra("m1b_student_name", head_name);
                    startActivityForResult(intent, 88);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btniBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 88) {
            boolean isDataUpdated = data.getBooleanExtra("isDataUpdated", false);
            if (isDataUpdated) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("isDataUpdated", isDataUpdated);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

        }

    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("isDataUpdated", false);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}