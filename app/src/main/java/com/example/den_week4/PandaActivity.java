package com.example.den_week4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class PandaActivity extends AppCompatActivity {

    int[] pandaImages = {
            R.drawable.panda1,
            R.drawable.panda2,
            R.drawable.panda4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.panda_activity);
         ProgressBar bannerProgress;
        ImageView imgPanda = findViewById(R.id.imageView);
        AdView adView=findViewById(R.id.adViewQuote);
        Button close = findViewById(R.id.close);
        bannerProgress = findViewById(R.id.bannerProgress);
        bannerProgress.setVisibility(View.VISIBLE);
        close.setOnClickListener(v->{
            finishAffinity();
            System.exit(0);
        });
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

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


        Random random = new Random();
        int randomIndex = random.nextInt(pandaImages.length);
        imgPanda.setImageResource(pandaImages[randomIndex]);
    }
}
