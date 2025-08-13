//package com.example.den_week4;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    EditText etName, etEmail, etPassword;
//    Button btnRegister;
//    AdView adView;
//
//    SharedPreferences sharedPreferences;
//    private static final String SHARED_PREF_NAME = "user_pref";
//    private static final String KEY_NAME = "name";
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_PASSWORD = "password";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.register);
//
//        etName = findViewById(R.id.etName);
//        etEmail = findViewById(R.id.etEmail);
//        etPassword = findViewById(R.id.etPassword);
//        btnRegister = findViewById(R.id.btnRegister);
//        adView = findViewById(R.id.adView);
//
//        // Load Banner Ad
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//
//        adView.loadAd(adRequest);
//
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//        btnRegister.setOnClickListener(v -> {
//            String name = etName.getText().toString().trim();
//            String email = etEmail.getText().toString().trim();
//            String password = etPassword.getText().toString().trim();
//
//            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//                Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(KEY_NAME, name);
//            editor.putString(KEY_EMAIL, email);
//            editor.putString(KEY_PASSWORD, password);
//            editor.apply();
//
//            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//
//            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            finish();
//        });
//    }
//}

package com.example.den_week4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btnRegister;
    AdView adView;
    ProgressBar adProgress;
    TextView adStatus;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "user_pref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        adView = findViewById(R.id.adView);
        adProgress = findViewById(R.id.adProgress);
        adStatus = findViewById(R.id.adStatus);

        // Initially show loading UI
        adProgress.setVisibility(View.VISIBLE);
        adStatus.setText("Loading ad...");

        // Load Banner Ad
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adProgress.setVisibility(View.GONE);
                adStatus.setText("Ad loaded successfully");
            }

            @Override
            public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError adError) {
                adProgress.setVisibility(View.GONE);
                adStatus.setText("Failed to load ad");
            }
        });
        adView.loadAd(adRequest);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_EMAIL, email);
            editor.putString(KEY_PASSWORD, password);
            editor.apply();

            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}
