package com.example.myvungleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vungle.warren.AdConfig;
import com.vungle.warren.Banners;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleBanner;
import com.vungle.warren.error.VungleException;

public class MainActivity extends AppCompatActivity {
    Button showInterstitialBtn,showBannerBtn, showRewardedBtn, showMrecBtn;
    ViewGroup bannerAdContainer;
    VungleBanner vungleBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showInterstitialBtn = findViewById(R.id.showInterstitialBtn);
        showBannerBtn = findViewById(R.id.showBannerBtn);
        showRewardedBtn = findViewById(R.id.showRewardedBtn);
        showMrecBtn = findViewById(R.id.showMrecBtn);

        bannerAdContainer = (LinearLayout) findViewById(R.id.bannerAdContainer);

        Vungle.init("623d4c4b9a33c27e2342a141", getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Vungle SDK initialized successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(VungleException exception) {
                Toast.makeText(MainActivity.this, exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {

            }
        });

        showInterstitialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vungle.loadAd("INTERSTITIAL_PLACEMENT-9035309", new LoadAdCallback() {
                    @Override
                    public void onAdLoad(String id) {
                        if (Vungle.canPlayAd("INTERSTITIAL_PLACEMENT-9035309")){
                            Vungle.playAd("INTERSTITIAL_PLACEMENT-9035309",null,null);
                        }
                    }

                    @Override
                    public void onError(String id, VungleException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        showBannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Banners.loadBanner("BANNER_PLACEMENT-1921488", AdConfig.AdSize.BANNER, new LoadAdCallback() {
                    @Override
                    public void onAdLoad(String id) {
                        if (Banners.canPlayAd("BANNER_PLACEMENT-1921488", AdConfig.AdSize.BANNER)){
                            vungleBanner = Banners.getBanner("BANNER_PLACEMENT-1921488", AdConfig.AdSize.BANNER,null);
                            bannerAdContainer.removeAllViews();
                            bannerAdContainer.addView(vungleBanner);
                        }
                    }

                    @Override
                    public void onError(String id, VungleException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        showRewardedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vungle.loadAd("REWARDED_PLACEMENT-4348626", new LoadAdCallback() {
                    @Override
                    public void onAdLoad(String id) {
                        if (Vungle.canPlayAd("REWARDED_PLACEMENT-4348626")){
                            Vungle.playAd("REWARDED_PLACEMENT-4348626",null,null);
                        }
                    }

                    @Override
                    public void onError(String id, VungleException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        showMrecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vungle.loadAd("MREC_PLACEMENT-2686166", new LoadAdCallback() {
                    @Override
                    public void onAdLoad(String id) {
                        if (Vungle.canPlayAd("MREC_PLACEMENT-2686166")){
                            Vungle.playAd("MREC_PLACEMENT-2686166",null,null);
                        }
                    }

                    @Override
                    public void onError(String id, VungleException exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    protected void onDestroy() {
        if (vungleBanner != null) {
            vungleBanner.destroyAd();
        }
        super.onDestroy();
    }
}