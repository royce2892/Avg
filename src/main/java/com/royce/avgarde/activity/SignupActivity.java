package com.royce.avgarde.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.OnCountryPickerListener;
import com.royce.avgarde.R;
import com.royce.avgarde.utils.AppConstants;
import com.royce.avgarde.utils.PreferenceManager;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    Spinner mGenderSpinner;
    CountryPicker mCountryPicker;
    Button mCountry, mSignup;
    TextInputEditText mName, mUsername, mPass, mCpass, mEmail, mState;
    CheckBox mTnc, mCer;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Be an AvGardian");
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSpinner.setAdapter(adapter);

        mCountryPicker =
                new CountryPicker.Builder().with(this)
                        .listener(new OnCountryPickerListener() {
                            @Override
                            public void onSelectCountry(Country country) {
                                setmCountry(country);
                                //DO something here
                            }
                        })
                        .build();

        mCountry = findViewById(R.id.nationSelect);
        mName = findViewById(R.id.name);
        mUsername = findViewById(R.id.username);
        mPass = findViewById(R.id.password);
        mCpass = findViewById(R.id.confirm_password);
        mEmail = findViewById(R.id.email);
        mState = findViewById(R.id.state);
        mCer = findViewById(R.id.checkbox_certify);
        mTnc = findViewById(R.id.checkbox_tnc);
        mSignup = findViewById(R.id.signup);

        mCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountryPicker.showDialog(getSupportFragmentManager());
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndSignup();
            }
        });

        preferenceManager = PreferenceManager.getInstance(this);
    }

    private void checkAndSignup() {
        try {
            if (!(mCer.isChecked() && mTnc.isChecked())) {
                Toast.makeText(this, "You haven't checked the checkboxes", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!(mPass.getText().toString().contentEquals(mCpass.getText().toString()))) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            if ((TextUtils.isEmpty(mName.getText().toString()) ||
                    TextUtils.isEmpty(mUsername.getText().toString()) ||
                    TextUtils.isEmpty(mPass.getText().toString()) ||
                    TextUtils.isEmpty(mCpass.getText().toString()) ||
                    TextUtils.isEmpty(mEmail.getText().toString()) ||
                    TextUtils.isEmpty(mState.getText().toString()))) {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                return;
            }


            preferenceManager.put(AppConstants.KEY_NAME, mName.getText().toString());
            preferenceManager.put(AppConstants.KEY_USERNAME, mUsername.getText().toString());
            preferenceManager.put(AppConstants.KEY_EMAIL, mEmail.getText().toString());
            preferenceManager.put(AppConstants.KEY_PASS, mPass.getText().toString());
            preferenceManager.put(AppConstants.KEY_NATION, mCountry.getText().toString());
            preferenceManager.put(AppConstants.KEY_STATE, mState.getText().toString());
            preferenceManager.put(AppConstants.KEY_LOGGED_IN, true);

            showDialog();

        } catch (NullPointerException ex) {
            Toast.makeText(this, "Some fields are invalid, please check and try again", Toast.LENGTH_SHORT).show();

        }
    }

    private void showDialog() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Signing up");
        progress.setMessage("Please wait while we sign you up...");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);

        progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //theLayout.setVisibility(View.GONE);
                gotoMain();
            }
        });
    }

    private void gotoMain() {
        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
        this.finish();
    }

    private void setmCountry(Country mCountry) {
        Toast.makeText(this, mCountry.getName() + " selected", Toast.LENGTH_SHORT).show();
        this.mCountry.setText(mCountry.getName());
    }
}
