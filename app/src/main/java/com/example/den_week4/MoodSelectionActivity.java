//package com.example.den_week4;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.FullScreenContentCallback;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//public class MoodSelectionActivity extends AppCompatActivity {
//    private AdView adView;
//    private InterstitialAd mInterstitialAd;
//    private static final String TAG = "MoodSelectionActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mood_selection);
//
//        // Load Banner Ad
//        adView = findViewById(R.id.adViewMood);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//
//        // Load Interstitial Ad
//        loadInterstitialAd();
//
//        // Get emoji buttons
//        TextView veryHappyBtn = findViewById(R.id.veryHappyBtn);
//        TextView happyBtn = findViewById(R.id.happyBtn);
//        TextView neutralBtn = findViewById(R.id.neutralBtn);
//        TextView sadBtn = findViewById(R.id.sadBtn);
//        TextView poorBtn = findViewById(R.id.poorBtn);
//
//        // Set onClick listeners to send mood to QuoteActivity
//        veryHappyBtn.setOnClickListener(v -> showAdOrGo("very_happy"));
//        happyBtn.setOnClickListener(v -> showAdOrGo("happy"));
//        neutralBtn.setOnClickListener(v -> showAdOrGo("neutral"));
//        sadBtn.setOnClickListener(v -> showAdOrGo("sad"));
//        poorBtn.setOnClickListener(v -> showAdOrGo("poor"));
//    }
//
//    private void loadInterstitialAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//        InterstitialAd.load(
//                this,
//                "ca-app-pub-3940256099942544/1033173712",
//                adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        mInterstitialAd = interstitialAd;
//                        Log.d(TAG, "Interstitial Ad Loaded");
//
//                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                            @Override
//                            public void onAdDismissedFullScreenContent() {
//                                Log.d(TAG, "Ad dismissed");
//                                mInterstitialAd = null;
//                                loadInterstitialAd(); // Preload for next time
//                            }
//
//                            @Override
//                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
//                                Log.e(TAG, "Ad failed to show: " + adError.getMessage());
//                                mInterstitialAd = null;
//                            }
//
//                            @Override
//                            public void onAdShowedFullScreenContent() {
//                                Log.d(TAG, "Ad shown");
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull com.google.android.gms.ads.LoadAdError loadAdError) {
//                        Log.e(TAG, "Failed to load interstitial ad: " + loadAdError.getMessage());
//                        mInterstitialAd = null;
//                    }
//                }
//        );
//    }
//
//    private void showAdOrGo(String mood) {
//        if (mInterstitialAd != null) {
//            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    openQuoteScreen(mood);
//                }
//            });
//            mInterstitialAd.show(MoodSelectionActivity.this);
//        } else {
//            openQuoteScreen(mood);
//        }
//    }
//
//    private void openQuoteScreen(String mood) {
//        Intent intent = new Intent(MoodSelectionActivity.this, QuoteActivity.class);
//        intent.putExtra("mood", mood);
//        startActivity(intent);
//    }
//}


package com.example.den_week4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MoodSelectionActivity extends AppCompatActivity {
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "MoodSelectionActivity";
    private ProgressBar adProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_selection);

        adProgressBar = findViewById(R.id.adProgressBarMood);

        // Load Banner Ad with progress
        adView = findViewById(R.id.adViewMood);
        AdRequest adRequest = new AdRequest.Builder().build();

        adProgressBar.setVisibility(View.VISIBLE); // Show loading
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adProgressBar.setVisibility(View.GONE);
                Log.d(TAG, "Banner Ad Loaded");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                adProgressBar.setVisibility(View.GONE);
                Log.e(TAG, "Banner failed to load: " + adError.getMessage());
            }
        });
        adView.loadAd(adRequest);

        // Load Interstitial Ad
        loadInterstitialAd();

        // Get emoji buttons
        TextView veryHappyBtn = findViewById(R.id.veryHappyBtn);
        TextView happyBtn = findViewById(R.id.happyBtn);
        TextView neutralBtn = findViewById(R.id.neutralBtn);
        TextView sadBtn = findViewById(R.id.sadBtn);
        TextView poorBtn = findViewById(R.id.poorBtn);

        // Set onClick listeners to send mood to QuoteActivity
        veryHappyBtn.setOnClickListener(v -> showAdOrGo("very_happy"));
        happyBtn.setOnClickListener(v -> showAdOrGo("happy"));
        neutralBtn.setOnClickListener(v -> showAdOrGo("neutral"));
        sadBtn.setOnClickListener(v -> showAdOrGo("sad"));
        poorBtn.setOnClickListener(v -> showAdOrGo("poor"));
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.d(TAG, "Interstitial Ad Loaded");
                        Toast.makeText(MoodSelectionActivity.this, "Interstitial Ad is ready!", Toast.LENGTH_SHORT).show();

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d(TAG, "Ad dismissed");
                                mInterstitialAd = null;
                                loadInterstitialAd(); // Preload for next time
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                Log.e(TAG, "Ad failed to show: " + adError.getMessage());
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                Log.d(TAG, "Ad shown");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e(TAG, "Failed to load interstitial ad: " + loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                }
        );
    }

    private void showAdOrGo(String mood) {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    openQuoteScreen(mood);
                }
            });
            mInterstitialAd.show(MoodSelectionActivity.this);
        } else {
            openQuoteScreen(mood);
        }
    }

    private void openQuoteScreen(String mood) {
        Intent intent = new Intent(MoodSelectionActivity.this, QuoteActivity.class);
        intent.putExtra("mood", mood);
        startActivity(intent);
    }
}
