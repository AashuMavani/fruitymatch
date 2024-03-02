package com.gamenative.fruitymatch.fruit_ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.gamenative.fruitymatch.R;
import com.google.gson.Gson;
import com.nativegame.natyengine.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_AdManager {


    private final Activity mActivity;

    private RewardedAd mRewardedAd;
    private AdRewardListener mListener;


    private boolean mRewardEarned = false;
    private boolean mInterstitialEarned = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_AdManager(Activity activity) {
        mActivity = activity;
        requestAd();
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public AdRewardListener getListener() {
        return mListener;
    }

    public void setListener(AdRewardListener listener) {
        mListener = listener;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------


    public void requestAd() {
        if (mRewardedAd != null) {
            return;
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(mActivity, ResourceUtils.getString(mActivity, R.string.txt_admob_reward),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        mRewardedAd = null;
                        // Toast.makeText(mActivity, "Fail!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdLoaded(RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        // Toast.makeText(mActivity, "Succeed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public boolean showRewardAd() {
        if (mRewardedAd == null) {
            return false;
        }

        // Reset state
        mRewardEarned = false;

        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed
                mRewardedAd = null;
                // Check if user dismiss Ad before earn
                if (!mRewardEarned) {
                    mListener.onLossReward();
                }
                // Prepare next ad
                requestAd();
            }
        });

        mRewardedAd.show(mActivity, new OnUserEarnedRewardListener() {
            @Override
            public void onUserEarnedReward(RewardItem rewardItem) {
                mListener.onEarnReward();
                mRewardEarned = true;
                // Toast.makeText(mActivity, "Reward!", Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface AdRewardListener {


        void onEarnReward();

        void onLossReward();

    }

    //========================================================




}
