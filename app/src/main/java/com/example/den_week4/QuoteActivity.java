package com.example.den_week4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuoteActivity extends AppCompatActivity {

    private AdView adView;
    private ProgressBar bannerProgress;
    private Map<String, List<String>> quotesMap;
    private Button panda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        // Load banner ad
//        adView = findViewById(R.id.adViewQuote);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        bannerProgress = findViewById(R.id.bannerProgress);
        bannerProgress.setVisibility(View.VISIBLE);


        adView = findViewById(R.id.adViewQuote);
        AdRequest adRequest = new AdRequest.Builder().build();

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerProgress.setVisibility(View.GONE);
                Log.d("Ads", "Banner ad loaded");
            }

            @Override
            public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError adError) {
                bannerProgress.setVisibility(View.GONE);
                Log.e("Ads", "Banner ad failed: " + adError.getMessage());
            }
        });

        adView.loadAd(adRequest);
        panda=findViewById(R.id.pandaButton);
    panda.setOnClickListener(v -> {
        Intent intent = new Intent(QuoteActivity.this, PandaActivity.class);
        startActivity(intent);
    });
        // Get mood from intent
        String mood = getIntent().getStringExtra("mood");

        // Initialize quotes
        quotesMap = new HashMap<>();
        quotesMap.put("very_happy", List.of(
                "Your joy is contagious. Keep shining!",
                "Happiness looks amazing on you."
        ));
        quotesMap.put("happy", List.of(
                "A happy soul is the best shield for a cruel world.",
                "Happiness is a direction, not a place."
        ));
        quotesMap.put("neutral", List.of(
                "Every day may not be good, but there’s something good in every day.",
                "Keep going, your story isn’t over."
        ));
        quotesMap.put("sad", List.of(
                "Stars can’t shine without darkness.",
                "Your struggles are the seeds of your strength."
        ));
        quotesMap.put("poor", List.of(
                "This too shall pass.",
                "Every storm runs out of rain."
        ));

        // Pick random quote from selected mood
        String selectedQuote = "Stay strong!";
        if (quotesMap.containsKey(mood)) {
            List<String> moodQuotes = quotesMap.get(mood);
            selectedQuote = moodQuotes.get(new Random().nextInt(moodQuotes.size()));
        }

        // Show the quote
        TextView quoteText = findViewById(R.id.quoteText);
        quoteText.setText(selectedQuote);
    }
}
