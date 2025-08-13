//package com.example.den_week4;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//
//public class LoginActivity extends AppCompatActivity {
//
//    EditText etEmail, etPassword;
//    Button btnLogin;
//    AdView adView;
//
//    SharedPreferences sharedPreferences;
//    private static final String PREF_NAME = "user_pref";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        etEmail = findViewById(R.id.etEmail);
//        etPassword = findViewById(R.id.etPassword);
//        btnLogin = findViewById(R.id.btnLogin);
//        adView = findViewById(R.id.adView);
//
//        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//
//        // Initialize Mobile Ads SDK
//        MobileAds.initialize(this, initializationStatus -> {});
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//
//        btnLogin.setOnClickListener(v -> {
//            String email = etEmail.getText().toString().trim();
//            String password = etPassword.getText().toString().trim();
//
//            String savedEmail = sharedPreferences.getString("email", "");
//            String savedPassword = sharedPreferences.getString("password", "");
//
//            if (email.equals(savedEmail) && password.equals(savedPassword)) {
//                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, MoodSelectionActivity.class));
//                finish();
//            } else {
//                Toast.makeText(LoginActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
//
//
//


package com.example.den_week4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.google.android.gms.ads.MobileAds;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    AdView adView;
    ProgressBar adProgressBar;
    TextView adStatusText;

    SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        adView = findViewById(R.id.adView);
        adProgressBar = findViewById(R.id.adProgressBar);
        adStatusText = findViewById(R.id.adStatusText);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Show progress while loading
        adProgressBar.setVisibility(View.VISIBLE);
        adStatusText.setVisibility(View.VISIBLE);
        adStatusText.setText("Loading ad...");

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {});

        // Load banner ad with listener
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adProgressBar.setVisibility(View.GONE);
                adStatusText.setText("Ad loaded");
            }

            @Override
            public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError adError) {
                adProgressBar.setVisibility(View.GONE);
                adStatusText.setText("Ad failed to load: " + adError.getMessage());
            }
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            String savedEmail = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password", "");

            if (email.equals(savedEmail) && password.equals(savedPassword)) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MoodSelectionActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
