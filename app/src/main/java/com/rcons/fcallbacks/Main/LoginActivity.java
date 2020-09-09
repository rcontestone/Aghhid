package com.rcons.fcallbacks.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.TRT.CallScreen;
import com.rcons.fcallbacks.Utilties.AppController;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.edtUserID) EditText username;
    @BindView(R.id.edtPassword) EditText password;
    @BindView(R.id.btnLoginNow) Button login;


    //////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        login.setOnClickListener(view -> {
            String name = username.getText().toString();
            String pass = password.getText().toString();
            if (name == null || name.equalsIgnoreCase("")) {
                Toast.makeText(LoginActivity.this, "Enter your name", Toast.LENGTH_LONG).show();
            } else if (pass == null || pass.equalsIgnoreCase("")) {
                Toast.makeText(LoginActivity.this, "Enter your password", Toast.LENGTH_LONG).show();
            } else if (pass.length() < 1) {
                Toast.makeText(LoginActivity.this, "Password must be of 5 characters", Toast.LENGTH_LONG).show();
            }else {


//                    RConsUtils.SaveUserInfo(name , pass);
//                    Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
//                    intent.putExtra("username" , name);
//                    intent.putExtra("password" , pass);
//
//                    startActivity(intent);
//                    finish();
                if(RConsUtils.isNetworkAvailable(LoginActivity.this)){
                    //LoginUser(name,pass);
                    
                   customlogin(name,pass);
                    
                    
                }else {
                    Toast.makeText(LoginActivity.this, "You are not connected to internet.", Toast.LENGTH_SHORT).show();
                }
            }
//                else if (username.getText().toString().equals("A") && password.getText().toString().equals("a")) {
//                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
//                    Intent Title = new Intent(LoginActivity.this, MainMenuActivity.class);
//                    startActivity(Title);
//                }

        });
    }

    private void customlogin(final String userName , final String userPassword) {

        try {
            RConsUtils.SaveUserInfo(userName.trim().toUpperCase() , userPassword.trim().toUpperCase());
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            intent.putExtra("username" , userName.trim().toUpperCase() );
            intent.putExtra("password" , userPassword.trim().toUpperCase() );

            startActivity(intent);
            finish();
        } catch (Exception e) {
       //     EmailDebugLog.getInstance(appContext).writeLog("[LoginActivity] inside customlogin() Exception is :"+e.toString());
        }
    }


    void LoginUser(final String userName , final String userPassword) {
        {
            try {
                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Authenticating User");
                if (dialog != null && !dialog.isShowing()) {
                    dialog.show();
                }

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Username", userName);
                jsonObject.put("Password", userPassword);

              //  String url = "https://webapiportal.azurewebsites.net/api/fcbs/authenticate";
                String url = "http://webapi.rconsdata.com/api/fcbs/Authenticate";

                JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            Boolean Success = response.getBoolean("Success");
                            String responseString = response.getString("Response");
                            if (Success) {
                                RConsUtils.SaveUserInfo(userName , userPassword);
                                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                                intent.putExtra("username" , userName);
                                intent.putExtra("password" , userPassword);

                                startActivity(intent);
                                finish();

                            } else {
                                if (StringUtils.isEmpty(responseString)) {
                                    responseString = "Unable to get login. Please try again.";
                                }
                                Toast.makeText(LoginActivity.this, responseString, Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "Unable to get login. Please try again.", Toast.LENGTH_SHORT).show();
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }


                        Log.e("Final Call", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Toast.makeText(LoginActivity.this, "Unable to get login. Please try again.", Toast.LENGTH_SHORT).show();

                        Log.d("TAG", "" + error.getMessage() + "," + error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> jsonObject = new HashMap<String, String>();
                        return jsonObject;
                    }

                    @Override
                    public String getBodyContentType() {
                        return /*(for exmaple)*/ "application/json";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }
                };
                sr.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                AppController.getInstance().addToRequestQueue(sr);
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Unable to get login. Please try again.", Toast.LENGTH_SHORT).show();

            }

        }
    }
}