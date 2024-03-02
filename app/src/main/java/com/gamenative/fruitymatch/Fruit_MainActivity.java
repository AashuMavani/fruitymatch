package com.gamenative.fruitymatch;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.applovin.impl.mediation.ads.MaxNativeAdLoaderImpl;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.gamenative.fruitymatch.Ads_Models.Model;
import com.gamenative.fruitymatch.Fruit_Network.Fruit_instance;
import com.gamenative.fruitymatch.fruit_ad.Fruit_AdManager;
import com.gamenative.fruitymatch.fruit_ad.Fruit_Common_Utils;
import com.gamenative.fruitymatch.fruit_ad.Fruit_SharePrefs;
import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;
import com.gamenative.fruitymatch.fruit_timer.Fruit_LivesTimer;
import com.gamenative.fruitymatch.fruit_ui.fruit_fragment.Fruit_LoadingFragment;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.nativegame.natyengine.ui.GameActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oscar Liang on 2022/02/23
 */


public class Fruit_MainActivity extends GameActivity {
    //    RecyclerView recyclerView;
    List<FruitModels> modellist = new ArrayList<>();
    List<Model> adsmodellistn = new ArrayList<>();
    Button Ads;
    LinearLayout layoutInflate;
    ImageView ivFooterImage;
    private MaxAd nativeAd;
    LinearLayoutManager linearLayoutManager;
    //    Recyclerview_Adapter adapter;
    String adMobAppOpen, adMobBanner, adMobInterstitial, adMobRewarded, adMobANative;
    private Fruit_AdManager mAdManager;
    private Fruit_LivesTimer mLivesTimer;
    private MaxNativeAdLoader nativeAdLoader;
    private FruitModels responseMain;
    private MaxNativeAdLoaderImpl a;


    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_AdManager getAdManager() {
        return mAdManager;
    }

    public Fruit_LivesTimer getLivesTimer() {
        return mLivesTimer;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_JuicyMatch);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.fruit_fruit_activity_main);
        setFragmentContainer(R.id.layout_container);
//        Ads = findViewById(R.id.Ads);
//        layoutInflate=findViewById(R.id.layoutInflate);



        mAdManager = new Fruit_AdManager(this);
        mLivesTimer = new Fruit_LivesTimer(this);

//
//        LinearLayout fl_adplaceholder = findViewById(R.id.fl_adplaceholder);
//        TextView lblLoadingAds = findViewById(R.id.lblLoadingAds);

        Log.e("AppLovin show1: ", "===");

        Fruit_instance.CallAPI().callMain(adMobAppOpen, adMobBanner, adMobInterstitial, adMobRewarded, adMobANative).enqueue(new Callback<FruitModels>() {
            @Override
            public void onResponse(Call<FruitModels> call, Response<FruitModels> response) {
                Log.d("TTT==", "onResponse:response======= " + response.body());

                Fruit_Common_Utils.InitializeApplovinSDK();
                response.body().getAdMobANative();
                Log.d("TTT==", "onResponse:adMobANative======= " + response.body().getAdMobANative());
                response.body().getAdMobAppOpen();
                Log.d("TTT==", "onResponse:adMobAppOpen======= " + response.body().getAdMobAppOpen());
                response.body().getAdMobBanner();
                Log.d("TTT==", "onResponse:adMobBanner======= " + response.body().getAdMobBanner());
                response.body().getAdMobInterstitial();
                Log.d("TTT==", "onResponse:adMobInterstitial======= " + response.body().getAdMobInterstitial());
                response.body().getAdMobRewarded();
                Log.d("TTT==", "onResponse:adMobRewarded======= " + response.body().getAdMobRewarded());

                response.body().getLovinNativeID();
                Log.d("TTT==", "onResponse:getLovinRewardID..... " + response.body().getLovinNativeID());
                response.body().getLovinAppOpenID();
                Log.d("TTT==", "onResponse:getLovinAppOpenID....." + response.body().getLovinAppOpenID());
                response.body().getLovinBannerID();
                Log.d("TTT==", "onResponse:getLovinBannerID....." + response.body().getLovinBannerID());
                response.body().getLovinInterstitialID();
                Log.d("TTT==", "onResponse:getLovinInterstitialID......" + response.body().getLovinInterstitialID());
                response.body().getLovinRewardID();
                Log.d("TTT==", "onResponse:getLovinRewardID...... " + response.body().getLovinRewardID());
                response.body().getLovinSmallNativeID();
                Log.d("TTT==", "onResponse:getLovinSmallNativeID...... " + response.body().getLovinSmallNativeID());
                response.body().getWhichOne();
                Log.d("TTT==", "onResponse:getWhichOne...... " + response.body().getWhichOne());
                Log.d("TTT==", "onResponse:getWhichOne...... " + response.body().getIsAppAdShow());

                Log.e("#resonse", new Gson().toJson(response.body()));
                Fruit_SharePrefs.getInstance().putString(Fruit_SharePrefs.HomeData, new Gson().toJson(response.body()));
                Fruit_SharePrefs.getInstance().putString(Fruit_SharePrefs.WhichOne, response.body().getWhichOne());
                Fruit_SharePrefs.getInstance().putString(Fruit_SharePrefs.isAppAdShow, response.body().getIsAppAdShow());
               //native ad Applovin
//                if (Fruit_Common_Utils.isShowAppLovinNativeAds()) {
//                    Log.e("AppLovin show: ", "===");
//                    loadAppLovinNativeAds(fl_adplaceholder, lblLoadingAds);
////                    Fruit_Common_Utils.InitializeApplovinSDK();
//
                MobileAds.initialize(Fruit_MainActivity.this, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });

//
////            layoutInflate.addView(f);
//                }

            }

            @Override
            public void onFailure(Call<FruitModels> call, Throwable t) {
                Log.d("TTT", "onFailure: Error" + t.getLocalizedMessage());
                Toast.makeText(Fruit_MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


        // Show the menu fragment
        if (savedInstanceState == null) {
            navigateToFragment(new Fruit_LoadingFragment());
        }
    }



    private void loadAppLovinNativeAds(LinearLayout frameLayout, TextView lblLoadingAds) {
//        try {
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        nativeAdLoader = new MaxNativeAdLoader(Fruit_Common_Utils.getRandomAdUnitId(responseMain.getLovinNativeID()), Fruit_MainActivity.this);
        Log.d("applovin", "loadAppLovinNativeAds: " + Fruit_Common_Utils.getRandomAdUnitId(responseMain.getLovinNativeID()));

        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }
                nativeAd = ad;
                frameLayout.removeAllViews();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
                params.height = getResources().getDimensionPixelSize(R.dimen.adsize);
                params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                frameLayout.setLayoutParams(params);
                frameLayout.setPadding((int) getResources().getDimension(R.dimen.txt_state_level_size), (int) getResources().getDimension(R.dimen.txt_state_level_size), (int) getResources().getDimension(R.dimen.txt_state_level_size), (int) getResources().getDimension(R.dimen.txt_state_level_size));
                frameLayout.addView(nativeAdView);
                frameLayout.setVisibility(View.VISIBLE);
                lblLoadingAds.setVisibility(View.GONE);
                Log.e("AppLovin Loaded: ", "===");
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                frameLayout.setVisibility(View.GONE);
                lblLoadingAds.setVisibility(View.GONE);
                Log.e("AppLovin Failed: ", error.getMessage());
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {

            }
        });
        nativeAdLoader.loadAd();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    //========================================================


    //========================================================


}