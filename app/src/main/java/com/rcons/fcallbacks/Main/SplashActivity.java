package com.rcons.fcallbacks.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.TRT.CallScreen;
import com.rcons.fcallbacks.Utilties.RConsUtils;


/**
 * Created by Umer Waqas on 3/28/2018.
 */
public class SplashActivity extends Activity {

    private static final int REQUEST_INTERNET = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (RConsUtils.GetRegistrationState() == 1) {

                    String userName = RConsUtils.getUserName();
                    String userPassword = RConsUtils.getUserPassword();
                    Intent intent = new Intent(SplashActivity.this, MainMenuActivity.class);
                    intent.putExtra("username", userName);
                    intent.putExtra("password", userPassword);
                    startActivity(intent);


                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this,
                            LoginActivity.class));
                    finish();
                }


            }
        };
        mHandler.postDelayed(runnable, 500);

    }
}
