package com.example.den_week4;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

//public class MainActivity extends AppCompatActivity {
//
//
//
//
//    private static final int MAX_WAIT_TIME = 5000; // 5 seconds max wait
//    private AppOpenAd appOpenAd;
//    private static final String TEST_APP_OPEN_AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        loadOpenAd();
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // If ad doesn't load in max wait time, skip
//        new Handler().postDelayed(this::goToNextScreen, MAX_WAIT_TIME);
//    }
//
//    private void loadOpenAd() {
//        AdRequest request = new AdRequest.Builder().build();
//
//        AppOpenAd.load(
//                this,
//                TEST_APP_OPEN_AD_UNIT_ID,
//                request,
//                new AppOpenAd.AppOpenAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull AppOpenAd ad) {
//                        appOpenAd = ad;
//                        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                            @Override
//                            public void onAdDismissedFullScreenContent() {
//                                goToNextScreen();
//                            }
//
//                            @Override
//                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
//                                goToNextScreen();
//                            }
//                        });
//
//                        // Show immediately after load
//                        appOpenAd.show(MainActivity.this);
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        goToNextScreen();
//                    }
//                }
//        );
//    }
//
//    private void goToNextScreen() {
//        if (!isFinishing()) {
//            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//            finish();
//        }
//    }
//}


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView loadingText;

    private static final int MAX_WAIT_TIME = 5000; // 5 seconds
    private AppOpenAd appOpenAd;
    private static final String TEST_APP_OPEN_AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        loadingText = findViewById(R.id.loadingText);

        showLoader(true);
        loadOpenAd();

        new Handler().postDelayed(this::goToNextScreen, MAX_WAIT_TIME);
    }

    private void loadOpenAd() {
        AdRequest request = new AdRequest.Builder().build();

        AppOpenAd.load(
                this,
                TEST_APP_OPEN_AD_UNIT_ID,
                request,
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd ad) {
                        showLoader(false);
                        appOpenAd = ad;
                        appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                goToNextScreen();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                goToNextScreen();
                            }
                        });
                        appOpenAd.show(MainActivity.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        showLoader(false);
                        goToNextScreen();
                    }
                }
        );
    }

    private void showLoader(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        loadingText.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void goToNextScreen() {
        if (!isFinishing()) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            finish();
        }
    }
}
