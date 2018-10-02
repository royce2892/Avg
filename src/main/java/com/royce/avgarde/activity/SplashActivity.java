package com.royce.avgarde.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.royce.avgarde.R;
import com.royce.avgarde.utils.AppConstants;
import com.royce.avgarde.utils.PreferenceManager;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }

        if (PreferenceManager.getInstance(this).getBoolean(AppConstants.KEY_LOGGED_IN)) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            setContentView(R.layout.activity_splash);

            findViewById(R.id.button_get_started).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoGuide();
                }
            });

            // Crashlytics.getInstance().crash(); // Force a crash

        }
    }

    private void gotoGuide() {
        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        this.finish();
    }
}
