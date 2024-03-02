package com.gamenative.fruitymatch.fruit_ad;

import static android.view.View.VISIBLE;
import static com.gamenative.fruitymatch.fruit_ad.Fruit_Common_Utils.getRandomAdUnitId;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.gamenative.fruitymatch.Fruit_App_Controller;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.gson.Gson;

import java.util.Collections;

public class Fruit_Ads_Utils {
    private static boolean isShowingAds = false;
    private static Activity currentActivity;
    private static AdShownListener adShownListener;
    private static AdShownListener adShownListenerInterstitial;
    private static MaxAppOpenAd appOpenAdAppLovin;
    private static MaxAppOpenAd appOpenAdAdMob;
    private MaxNativeAdLoader nativeAdLoader;
    private static MaxInterstitialAd interstitialAdAppLovin;
    private static MaxRewardedAd rewardedAppLovinAd;
    private static AdShownListener adShownListenerRewarded;
    private static VideoAdShownListener videoAdShownListenerInterstitial;
    private static VideoAdShownListener videoAdShownListenerRewarded;
    public static String adFailUrl;
    public static AdView mAdView;
    public static FrameLayout Ads;
    String adMobAppOpen, adMobBanner, adMobInterstitial, adMobRewarded, adMobANative;
    static AdView adView;
       private static InterstitialAd mInterstitialAd;
    public static void loadAdMobInterstitialAds(FragmentActivity activity, Object o) {
    }

    public interface AdShownListener {
        void onAdDismiss();
    }

    public interface VideoAdShownListener {
        void onAdDismiss(boolean isAdShown);
    }


    public static void showAppLovinInterstitialAd(Activity activity, AdShownListener adShownListener1) {

        try {
            currentActivity = activity;
//            adShownListenerInterstitial = adShownListener1;
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            interstitialAdAppLovin = new MaxInterstitialAd(getRandomAdUnitId(responseMain.getLovinInterstitialID()), getCurrentActivity());
            interstitialAdAppLovin.loadAd();
            Log.d("AAAAAAAAAAA1", "showAppLovinInterstitialAd: " + interstitialAdAppLovin);
            Log.d("AAAAAAAAAAA1", "showAppLovinInterstitialAd: " + interstitialAdAppLovin.isReady());

            // Load the first ad

//            if (interstitialAdAppLovin != null && interstitialAdAppLovin.isReady() && (currentActivity != null && !currentActivity.isFinishing())) {
            if (interstitialAdAppLovin != null) {
                Fruit_Common_Utils.showAdLoader(activity, "Loading ads...");
                Log.d("AAAAAAAAAAA2", "showAppLovinInterstitialAd: " + interstitialAdAppLovin);

                interstitialAdAppLovin.setListener(new MaxAdListener() {
                    @Override
                    public void onAdLoaded(MaxAd maxAd) {
                        Log.e("#onAdLoaded", "onAdLoaded");
                        Fruit_Common_Utils.dismissAdLoader();
                        if (interstitialAdAppLovin != null && interstitialAdAppLovin.isReady() && !isIsShowingAds() && (currentActivity != null && !currentActivity.isFinishing())) {
                            isShowingAds = true;
                            interstitialAdAppLovin.showAd();
                        }
                    }

                    @Override
                    public void onAdDisplayed(MaxAd maxAd) {
                        Log.e("#onAdDisplayed", "onAdDisplayed");
                    }

                    @Override
                    public void onAdHidden(MaxAd maxAd) {
                        Log.e("#onAdHidden", "onAdHidden");
                    }

                    @Override
                    public void onAdClicked(MaxAd maxAd) {
                        Log.e("#onAdClicked", "onAdClicked");
                    }

                    @Override
                    public void onAdLoadFailed(String s, MaxError maxError) {
                        Log.e("#onAdLoadFailed", "onAdLoadFailed");
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                        Log.e("#onAdDisplayFailed", "onAdDisplayFailed");
                    }
                });

//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Fruit_Common_Utils.dismissAdLoader();
//                        isShowingAds = true;
//                        interstitialAdAppLovin.showAd();
//                    }
//                }, 1000);
            } else {
                if (interstitialAdAppLovin == null && Fruit_Common_Utils.isLoadAppLovinInterstitialAds()) {
                    Fruit_Common_Utils.showAdLoader(activity, "Loading video ads...");
                    Log.d("AAAAAAAAAAA3", "showAppLovinInterstitialAd: " + Fruit_Common_Utils.isShowAppLovinNativeAds());
                    loadApplovinInterstitialAds(true);
                } else {
                    if (adShownListenerInterstitial != null) {
                        adShownListenerInterstitial.onAdDismiss();
                        adShownListenerInterstitial = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadApplovinInterstitialAds(boolean isShowAdAfterLoading) {
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        if (Fruit_Common_Utils.isLoadAppLovinInterstitialAds()) {
            interstitialAdAppLovin = new MaxInterstitialAd(getRandomAdUnitId(responseMain.getLovinInterstitialID()), getCurrentActivity());
            interstitialAdAppLovin.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    Fruit_Common_Utils.dismissAdLoader();
                    if (isShowAdAfterLoading && interstitialAdAppLovin != null && interstitialAdAppLovin.isReady() && !isIsShowingAds() && (currentActivity != null && !currentActivity.isFinishing())) {
                        isShowingAds = true;
                        interstitialAdAppLovin.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    Fruit_Common_Utils.dismissAdLoader();
                    isShowingAds = false;
                    if (adShownListenerInterstitial != null) {
                        adShownListenerInterstitial.onAdDismiss();
                        adShownListenerInterstitial = null;
                    }
                    if (videoAdShownListenerInterstitial != null) {
                        videoAdShownListenerInterstitial.onAdDismiss(true);
                        videoAdShownListenerInterstitial = null;
                    }
                    interstitialAdAppLovin = null;
                    loadApplovinInterstitialAds(false);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                    Fruit_Common_Utils.dismissAdLoader();
                    isShowingAds = false;
                    if (adShownListenerInterstitial != null) {
                        adShownListenerInterstitial.onAdDismiss();
                        adShownListenerInterstitial = null;
                    }
                    if (videoAdShownListenerInterstitial != null) {
                        videoAdShownListenerInterstitial.onAdDismiss(false);
                        videoAdShownListenerInterstitial = null;
                    }
                    interstitialAdAppLovin = null;
                    if (isShowAdAfterLoading) {
                        Fruit_Common_Utils.openUrl(currentActivity, adFailUrl);
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    isShowingAds = false;
                    if (adShownListenerInterstitial != null) {
                        adShownListenerInterstitial.onAdDismiss();
                        adShownListenerInterstitial = null;
                    }
                    if (videoAdShownListenerInterstitial != null) {
                        videoAdShownListenerInterstitial.onAdDismiss(false);
                        videoAdShownListenerInterstitial = null;
                    }
                    interstitialAdAppLovin = null;
                    if (isShowAdAfterLoading) {
                        Fruit_Common_Utils.openUrl(currentActivity, adFailUrl);
                    }
                    loadApplovinInterstitialAds(false);
                }
            });

            // Load the first ad
            interstitialAdAppLovin.loadAd();
        }
    }

    public static void loadAppOpenMobAdd(final Context context) {
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        if (Fruit_Common_Utils.isShowAdMobAppOpenAds()) {
            appOpenAdAdMob = new MaxAppOpenAd(Fruit_Common_Utils.getRandomAdUnitId(Collections.singletonList(responseMain.getAdMobAppOpen())), Fruit_App_Controller.getContext());
            appOpenAdAdMob.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd maxAd) {
                    try {
                        Fruit_App_Controller.getContext().sendBroadcast(new Intent(Fruit_Constants.APP_OPEN_ADD_LOADED).setPackage(Fruit_App_Controller.getContext().getPackageName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd maxAd) {

                }

                @Override
                public void onAdHidden(MaxAd maxAd) {
                    appOpenAdAdMob = null;
                    isShowingAds = false;
                    dismissAppOpenAdListener();
                    loadAppOpenMobAdd(Fruit_App_Controller.getContext());

                }

                @Override
                public void onAdClicked(MaxAd maxAd) {

                }

                @Override
                public void onAdLoadFailed(String s, MaxError maxError) {

                }

                @Override
                public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                    dismissAppOpenAdListener();
                    isShowingAds = false;
                    loadAppOpenMobAdd(Fruit_App_Controller.getContext());

                }
            });
            appOpenAdAdMob.loadAd();

        }
    }

    public static void loadAppOpenAdd(final Context context) {
        //AppLogger.getInstance().e("loadAppOpenAdd loadAppOpenAdd STARTED: ", "CALLED===");
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        if (Fruit_Common_Utils.isShowAppLovinAppOpenAds()) {
            appOpenAdAppLovin = new MaxAppOpenAd(Fruit_Common_Utils.getRandomAdUnitId(responseMain.getLovinAppOpenID()), Fruit_App_Controller.getContext());
            appOpenAdAppLovin.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    try {
                        Fruit_App_Controller.getContext().sendBroadcast(new Intent(Fruit_Constants.APP_OPEN_ADD_LOADED).setPackage(Fruit_App_Controller.getContext().getPackageName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    //AppLogger.getInstance().e("Applovin AppOpen onAdDisplayed: ", "CALLED===");
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    //AppLogger.getInstance().e("Applovin AppOpen onAdHidden: ", "CALLED===");
                    appOpenAdAppLovin = null;
                    isShowingAds = false;
                    dismissAppOpenAdListener();
                    loadAppOpenAdd(Fruit_App_Controller.getContext());
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                    //AppLogger.getInstance().e("Applovin AppOpen onAdClicked: ", "CALLED===");
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    //AppLogger.getInstance().e("Applovin AppOpen onAdLoadFailed: ", "CALLED===");
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    //AppLogger.getInstance().e("Applovin AppOpen onAdDisplayFailed: ", "CALLED===");
                    dismissAppOpenAdListener();
                    isShowingAds = false;
                    loadAppOpenAdd(Fruit_App_Controller.getContext());
                }
            });
            appOpenAdAppLovin.loadAd();
        } else {
            dismissAppOpenAdListener();
            isShowingAds = false;
        }
    }

    private static void dismissAppOpenAdListener() {
        if (adShownListener != null) {
            adShownListener.onAdDismiss();
            adShownListener = null;
        } else {
            // User was not getting moved to main screen if app goes in background while displaying ap open ad as static adShownListener object was getting null when app goes in background
            Fruit_App_Controller.getContext().sendBroadcast(new Intent(Fruit_Constants.APP_OPEN_ADD_DISMISSED).setPackage(Fruit_App_Controller.getContext().getPackageName()));
        }
    }

    public static void showAppOpenAdd(Activity activity, AdShownListener adShownListener1) {
        try {
            adShownListener = adShownListener1;
            if (appOpenAdAppLovin != null && appOpenAdAppLovin.isReady() && (activity != null && !activity.isFinishing()) && !isIsShowingAds()) {
                isShowingAds = true;
                appOpenAdAppLovin.showAd();
            } else {
                if (adShownListener != null) {
                    adShownListener.onAdDismiss();
                    adShownListener = null;
                }
                if (Fruit_Common_Utils.isShowAppLovinAppOpenAds() && appOpenAdAppLovin == null) {
                    loadAppOpenAdd(Fruit_App_Controller.getContext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isIsShowingAds() {
        return isShowingAds;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
//        //AppLogger.getInstance().e("SET ACT1", "" + activity);
        if (!isShowingAds) {
            //AppLogger.getInstance().e("SET ACTIVITY", "=" + activity);
            currentActivity = activity;
        }
    }

    public static void loadRewardedAd(boolean isShowAdAfterLoading) {
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        if (Fruit_Common_Utils.isLoadAppLovinRewardedAds()) {
            rewardedAppLovinAd = MaxRewardedAd.getInstance(getRandomAdUnitId(responseMain.getLovinRewardID()), getCurrentActivity());
            rewardedAppLovinAd.setListener(new MaxRewardedAdListener() {
                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {

                }

                @Override
                public void onRewardedVideoStarted(MaxAd ad) {

                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    //AppLogger.getInstance().e("APPLOVIN REWARDED", "REWARD :onAdLoaded");
                    Fruit_Common_Utils.dismissAdLoader();
                    if (isShowAdAfterLoading && rewardedAppLovinAd != null && rewardedAppLovinAd.isReady() && !isIsShowingAds() && (currentActivity != null && !currentActivity.isFinishing())) {
                        Log.d("AAAAAAAAAAA3", "showAppLovinInterstitialAd: " + Fruit_Common_Utils.isShowAppLovinNativeAds());
                        isShowingAds = true;
                        rewardedAppLovinAd.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    //AppLogger.getInstance().e("APPLOVIN REWARDED ", "REWARD INST:onAdHidden");
                    isShowingAds = false;
                    if (adShownListenerRewarded != null) {
                        adShownListenerRewarded.onAdDismiss();
                        adShownListenerRewarded = null;
                    }
                    if (videoAdShownListenerRewarded != null) {
                        videoAdShownListenerRewarded.onAdDismiss(true);
                        videoAdShownListenerRewarded = null;
                    }
                    rewardedAppLovinAd = null;
                    loadRewardedAd(false);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    Fruit_Common_Utils.dismissAdLoader();
                    //AppLogger.getInstance().e("APPLOVIN REWARDED FAIL", "REWARD INST:onAdLoadFailed");
                    isShowingAds = false;
                    if (adShownListenerRewarded != null) {
                        adShownListenerRewarded.onAdDismiss();
                        adShownListenerRewarded = null;
                    }
                    if (videoAdShownListenerRewarded != null) {
                        videoAdShownListenerRewarded.onAdDismiss(false);
                        videoAdShownListenerRewarded = null;
                    }
                    rewardedAppLovinAd = null;
                    if (isShowAdAfterLoading) {
                        Fruit_Common_Utils.openUrl(currentActivity, adFailUrl);
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    //AppLogger.getInstance().e("APPLOVIN REWARDED FAIL", "REWARD INST:onAdDisplayFailed");
                    isShowingAds = false;
                    if (adShownListenerRewarded != null) {
                        adShownListenerRewarded.onAdDismiss();
                        adShownListenerRewarded = null;
                    }
                    if (videoAdShownListenerRewarded != null) {
                        videoAdShownListenerRewarded.onAdDismiss(false);
                        videoAdShownListenerRewarded = null;
                    }
                    rewardedAppLovinAd = null;
                    if (isShowAdAfterLoading) {
                        Fruit_Common_Utils.openUrl(currentActivity, adFailUrl);
                    }
                    loadRewardedAd(false);
                }
            });
            rewardedAppLovinAd.loadAd();
        }
    }


    public static void showAppLovinRewardedAd(Activity activity) {
        try {
            currentActivity = activity;
//            adShownListenerRewarded = adShownListener1;
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            rewardedAppLovinAd = MaxRewardedAd.getInstance(getRandomAdUnitId(responseMain.getLovinRewardID()), getCurrentActivity());
            rewardedAppLovinAd.loadAd();
            if (rewardedAppLovinAd != null) {
                Log.d("HHHHHH====", "showAppLovinRewardedAd: " + rewardedAppLovinAd);
                Fruit_Common_Utils.showAdLoader(activity, "Loading video ads...");
                rewardedAppLovinAd.setListener(new MaxRewardedAdListener() {
                    @Override
                    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {

                    }

                    @Override
                    public void onRewardedVideoStarted(MaxAd maxAd) {

                    }

                    @Override
                    public void onRewardedVideoCompleted(MaxAd maxAd) {

                    }

                    @Override
                    public void onAdLoaded(MaxAd maxAd) {
                        Fruit_Common_Utils.dismissAdLoader();
                        if ((rewardedAppLovinAd != null) && rewardedAppLovinAd.isReady() && !isIsShowingAds() && ((currentActivity != null) && !currentActivity.isFinishing())) {
                            isShowingAds = true;
                            rewardedAppLovinAd.showAd();
                            Log.d("KKKKK", "onAdLoaded: " + Fruit_Ads_Utils.rewardedAppLovinAd);

                        }

                    }

                    @Override
                    public void onAdDisplayed(MaxAd maxAd) {
                        Log.d("KKKKK", "onAdLoaded: " + Fruit_Ads_Utils.rewardedAppLovinAd);

                    }

                    @Override
                    public void onAdHidden(MaxAd maxAd) {
                        Log.d("KKKKK", "onAdLoaded: " + Fruit_Ads_Utils.rewardedAppLovinAd);


                    }

                    @Override
                    public void onAdClicked(MaxAd maxAd) {
                        Log.d("KKKKK", "onAdLoaded: " + Fruit_Ads_Utils.rewardedAppLovinAd);

                    }

                    @Override
                    public void onAdLoadFailed(String s, MaxError maxError) {
                        Log.d("KKKKK", "onAdLoaded: " + Fruit_Ads_Utils.rewardedAppLovinAd);

                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {

                    }
                });

//               Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Fruit_Common_Utils.dismissAdLoader();
//                        isShowingAds = true;
//                        rewardedAppLovinAd.showAd();
//                    }
//                }, 1000);
            } else {
                if (rewardedAppLovinAd == null && Fruit_Common_Utils.isLoadAppLovinRewardedAds()) {
                    Fruit_Common_Utils.showAdLoader(activity, "Loading video ads...");
                    Log.d("HHHHHH====1", "showAppLovinRewardedAd: " + Fruit_Common_Utils.isLoadAppLovinRewardedAds());
                    loadRewardedAd(true);
                } else {
                    if (adShownListenerRewarded != null) {
                        adShownListenerRewarded.onAdDismiss();
                        adShownListenerRewarded = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    Admob Ads


    public static void showAdmobbannerAd(Activity activity, FrameLayout frameLayout) {
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        frameLayout = activity.findViewById(R.id.adView);
        adView = new AdView(activity);
        adView.setAdUnitId(responseMain.getAdMobBanner());
        adView.setAdSize(AdSize.BANNER);

        // Replace ad container with new ad view.
        frameLayout.removeAllViews();
        frameLayout.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


    }
    public static void showAdMobInterstitial(Activity activity) {
        Fruit_Common_Utils.showAdLoader(activity, "Loading video ads...");
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        AdRequest adRequest = new AdRequest.Builder().build();

        if (Fruit_Common_Utils.isLoadAdMobInterstitialAds()) {
            InterstitialAd.load(activity, responseMain.getAdMobInterstitial(), adRequest, new InterstitialAdLoadCallback() {

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    Fruit_Common_Utils.dismissAdLoader();
                    super.onAdFailedToLoad(loadAdError);
                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    Fruit_Common_Utils.dismissAdLoader();
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd = interstitialAd;
                    Log.e("adloded","adload");

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {

                            super.onAdDismissedFullScreenContent();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            mInterstitialAd = null;
                        }
                    });
                }
            });
            if (mInterstitialAd != null) {
               mInterstitialAd.show(getCurrentActivity());
           } else {
               Log.d("TAG", "The interstitial ad wasn't ready yet.");
           }
        }


    }

    public static RewardedAd rewardedAd;
    public static void showAdMobRewarded(Activity activity){
//       try {
           Fruit_Common_Utils.showAdLoader(activity, "Loading video ads...");
           FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        Log.d("ABCCCCCCCC", "showAdMobRewarded: "+responseMain.getAdMobRewarded());
           AdRequest adRequest = new AdRequest.Builder().build();
           com.google.android.gms.ads.rewarded.RewardedAd.load(activity, responseMain.getAdMobRewarded(),
                   adRequest, new RewardedAdLoadCallback() {
                       @Override
                       public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                           // Handle the error.
                           Fruit_Common_Utils.dismissAdLoader();
                           Log.d("ZZZZZZ", loadAdError.toString());
                           rewardedAd = null;
                       }

                       @Override
                       public void onAdLoaded(@NonNull RewardedAd ad) {
                           Fruit_Common_Utils.dismissAdLoader();
                           rewardedAd = ad;
//                           Log.d("ZZZZZZ", "Ad was loaded.");
                       }
                   });
           if (rewardedAd != null) {
//               Activity activityContext = activity;
               rewardedAd.show(activity, new OnUserEarnedRewardListener() {
                   @Override
                   public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                       // Handle the reward.
//                       Log.d("ZZZZZZ", "The user earned the reward.");
                       int rewardAmount = rewardItem.getAmount();
                       String rewardType = rewardItem.getType();
                   }
               });
           } else {
//               Log.d("ZZZZZZ", "The rewarded ad wasn't ready yet.");
           }
//       } catch (Exception e) {
//           throw new RuntimeException(e);
//       }


    }
    public static void showAdMobANative(FragmentActivity activity, Object o) {
        FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
        Log.d("ZZZZZZ", "showAdMobnative: " + responseMain.getAdMobANative());
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        MobileAds.initialize(activity);
        AdLoader adLoader = new AdLoader.Builder(activity, responseMain.getAdMobANative())
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new NativeTemplateStyle.Builder().build();
                        TemplateView template = activity.findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());



    }
//    public void requestAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//        RewardedAd.load(mActivity, mActivity.getString(R.string.txt_admob_reward),
//                adRequest, new RewardedAdLoadCallback() {
//                    @Override
//                    public void onAdFailedToLoad(LoadAdError loadAdError) {
//                        // Handle the error
//                        // Toast.makeText(mActivity, "Fail!!!", Toast.LENGTH_SHORT).show();
//                        mRewardedAd = null;
//                    }
//
//                    @Override
//                    public void onAdLoaded(RewardedAd rewardedAd) {
//                        // Toast.makeText(mActivity, "Succeed!!!", Toast.LENGTH_SHORT).show();
//                        mRewardedAd = rewardedAd;
//                    }
//                });
//    }






}










