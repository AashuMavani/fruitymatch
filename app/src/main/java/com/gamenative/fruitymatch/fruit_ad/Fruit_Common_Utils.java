package com.gamenative.fruitymatch.fruit_ad;

import static android.view.View.VISIBLE;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.gamenative.fruitymatch.Fruit_App_Controller;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

public class Fruit_Common_Utils {
    private static Dialog dialogLoader, dialogAdLoader;



    public static boolean isLoadAppLovinInterstitialAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if (responseMain.getIsAppAdShow() != null && responseMain.getLovinInterstitialID().equals("1") && responseMain.getIsAppAdShow() != null && responseMain.getLovinInterstitialID().size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isLoadAdMobInterstitialAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            Log.e("response appshow",""+responseMain);

            if (responseMain.getIsAppAdShow() != null && responseMain.getIsAppAdShow().equals("1") && responseMain.getAdMobInterstitial() != null && responseMain.getAdMobInterstitial().length() > 0) {
             /*   if ((responseMain.getIsBackAdsInterstitial() != null && responseMain.getIsBackAdsInterstitial().equals("1"))
                        || (responseMain.getIsShowExtraAds() != null && responseMain.getIsShowExtraAds().equals("1"))
                        || (responseMain.getIsScreenBackAdshow() != null && responseMain.getIsScreenBackAdshow().equals("1")))*/


                return true;
            } else {


                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void InitializeApplovinSDK() {
        try {
            AppLovinSdk.getInstance(Fruit_App_Controller.getContext()).setMediationProvider("max");
            AppLovinSdk.initializeSdk(Fruit_App_Controller.getContext(), new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                    // AppLovin SDK is initialized, start loading ads
                    //AppLogger.getInstance().e("AppLovinSdk initialize", "DONE=========");
                    Fruit_Ads_Utils.loadAppOpenAdd(Fruit_App_Controller.getContext());
                    Fruit_Ads_Utils.loadApplovinInterstitialAds(false);
//                    Fruit_Ads_Utils.loadRewardedAd(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showAdLoader(Context activity, String title) {
        try {
            if (dialogAdLoader == null || !dialogAdLoader.isShowing()) {
                dialogAdLoader = new Dialog(activity, android.R.style.Theme_Light);
                dialogAdLoader.getWindow().setBackgroundDrawableResource(R.color.black_transparent);
                dialogAdLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogAdLoader.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                dialogAdLoader.setCancelable(true);
                dialogAdLoader.setCanceledOnTouchOutside(true);
                dialogAdLoader.setContentView(R.layout.fruit_dialog_ads_loader);
                TextView tvTitle = dialogAdLoader.findViewById(R.id.tvTitle);
                tvTitle.setText(title);
                dialogAdLoader.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dismissAdLoader() {
        try {
            if (dialogAdLoader != null && dialogAdLoader.isShowing()) {
                dialogAdLoader.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getRandomAdUnitId(List<String> list) {
        return list.get(getRandomNumberBetweenRange(0, list.size()));

    }

    public static int getRandomNumberBetweenRange(int min, int max) {
        if (max == 0) {
            return 0;
        }
        Random r = new Random();
        int i1 = r.nextInt(max - min) + min;// min inclusive & max exclusive
        return i1;
    }
    public static void openUrl(Context c, String url) {
        if (!Fruit_Common_Utils.isStringNullOrEmpty(url)) {
            if (url.contains("/t.me/") || url.contains("telegram") || url.contains("facebook.com") || url.contains("instagram.com") || url.contains("youtube.com") || url.contains("play.google.com/store/apps/details") || url.contains("market.android.com/details")) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    c.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    openUrlInChrome(c, url);
                }
            } else {
                openUrlInChrome(c, url);
            }
        }
    }
    public static boolean isStringNullOrEmpty(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }
    private static void openUrlInChrome(Context c, String url) {
        //AppLogger.getInstance().e("URL openUrlInChrome :============", url);
        if (!Fruit_Common_Utils.isStringNullOrEmpty(url)) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            try {
                intent.setPackage("com.android.chrome");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    intent.setPackage(null);
                    c.startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    setToast(c, "No application found to handle this url");
                }
            }
        }
    }
    public static boolean isShowAppLovinNativeAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if (responseMain.getIsAppAdShow() != null && responseMain.getIsAppAdShow().equals("1") && responseMain.getLovinNativeID() != null && responseMain.getLovinNativeID().size() > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void setToast(Context _mContext, String str) {
        Toast toast = Toast.makeText(_mContext, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static boolean isShowAdMobAppOpenAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if ((responseMain.getAdMobAppOpen() != null) && responseMain.getAdMobAppOpen().equals("1") && (responseMain.getAdMobAppOpen() != null))
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isShowAppLovinAppOpenAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if (responseMain.getIsAppAdShow() != null && responseMain.getIsAppAdShow().equals("1") && responseMain.getLovinAppOpenID() != null && responseMain.getLovinAppOpenID().size() > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isLoadAppLovinRewardedAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if (responseMain.getIsAppAdShow() != null && responseMain.getIsAppAdShow().equals("1") && responseMain.getLovinRewardID() != null && responseMain.getLovinRewardID().size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isShowAppLovinAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if (responseMain.getIsAppAdShow() != null && responseMain.getIsAppAdShow().equals("1"))
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isShowAppLovinBannerAds() {
        try {
            FruitModels responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);
            if (responseMain.getIsAppAdShow() != null && responseMain.getIsAppAdShow().equals("1") && responseMain.getLovinBannerID() != null && responseMain.getLovinBannerID().size() > 0) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void loadBannerAds(Activity c, LinearLayout layoutBannerAd, TextView lblAdSpace) {
        try {
            if (Fruit_Common_Utils.isShowAppLovinBannerAds()) {
                lblAdSpace.setHeight(c.getResources().getDimensionPixelSize(R.dimen.applovin_banner_height));
                MaxAdView adView = new MaxAdView(getRandomAdUnitId(new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class).getLovinBannerID()), c);


                adView.setListener(new MaxAdViewAdListener() {
                    @Override
                    public void onAdExpanded(MaxAd ad) {
                        Log.e("#adload",""+ad);
                    }

                    @Override
                    public void onAdCollapsed(MaxAd ad) {  Log.e("#adload",""+ad);


                    }

                    @Override
                    public void onAdLoaded(MaxAd ad) {
                        Log.e("#adload",""+ad);
                        lblAdSpace.setVisibility(View.GONE);
                        layoutBannerAd.removeAllViews();
                        layoutBannerAd.addView(adView);
                    }

                    @Override
                    public void onAdDisplayed(MaxAd ad) {
                        Log.e("#adload",""+ad);

                    }

                    @Override
                    public void onAdHidden(MaxAd ad) {
                        Log.e("#adload",""+ad);

                    }

                    @Override
                    public void onAdClicked(MaxAd ad) {
                        Log.e("#adload",""+ad);

                    }

                    @Override
                    public void onAdLoadFailed(String adUnitId, MaxError error) {
                        //AppLogger.getInstance().e("APPLOVIN BANNER onAdLoadFailed==", "===" + error.getMessage());
                        layoutBannerAd.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                        //AppLogger.getInstance().e("APPLOVIN BANNER onAdDisplayFailed==", "===" + error.getMessage());
                        layoutBannerAd.setVisibility(View.GONE);
                    }
                });
                adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, c.getResources().getDimensionPixelSize(R.dimen.applovin_banner_height)));
                adView.loadAd();
            } else {
                layoutBannerAd.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            layoutBannerAd.setVisibility(View.GONE);
        }
    }



}
