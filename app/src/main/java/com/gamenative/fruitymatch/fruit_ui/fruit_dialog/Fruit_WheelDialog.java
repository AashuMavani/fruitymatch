package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

import com.gamenative.fruitymatch.Fruit_MainActivity;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_ad.Fruit_AdManager;
import com.gamenative.fruitymatch.fruit_ad.Fruit_Ads_Utils;
import com.gamenative.fruitymatch.fruit_ad.Fruit_SharePrefs;
import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Preferences;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_database.Fruit_DatabaseHelper;
import com.gamenative.fruitymatch.fruit_item.prize.Fruit_Prize;
import com.gamenative.fruitymatch.fruit_item.prize.Fruit_PrizeManager;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameText;
import com.nativegame.natyengine.util.RandomUtils;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_WheelDialog extends Fruit_BaseDialog implements Fruit_AdManager.AdRewardListener, View.OnClickListener {

    private static final long WHEEL_CD_TIME = 86400000;   // 1 day = 86400000ms

    private final boolean mIsWheelEnable;
    public FruitModels responseMain;
    boolean adshownonce = false;


    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_WheelDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_wheel);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);

        responseMain = new Gson().fromJson(Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.HomeData), FruitModels.class);


        // Init button
        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.popUp(200, 300);
        btnCancel.setOnClickListener(this);

        GameButton btnPlay = (GameButton) findViewById(R.id.btn_play); btnPlay.popUp(200, 700);
        btnPlay.setOnClickListener(this);
//
//        MobileAds.initialize(Fruit_MainActivity.this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit_Preferences.PREF_SETTING.putLong(Fruit_Preferences.KEY_LAST_PLAY_TIME, System.currentTimeMillis());

                if (responseMain.getIsAppAdShow().equals("1")&&responseMain.getStatus().equals("1")) {
                    Log.d("ACB====", "onClick: " + Fruit_SharePrefs.WhichOne);
                    if (Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.WhichOne).equals("applovin")) {
                        Fruit_Ads_Utils.showAdMobRewarded(activity);
//                        Log.e("AAAAAAAAAAAA", "onClick:adMob=== " + adshownonce);
                        Log.e("ZZZZZZZZZZZZZZZZZZZZZ", "onClick:appLovin===string===== " + Fruit_SharePrefs.WhichOne.equals("appLovin"));
                        Fruit_Preferences.PREF_SETTING.putLong(Fruit_Preferences.KEY_LAST_PLAY_TIME, System.currentTimeMillis());
                        adshownonce = true;
                        spinWheel();


                    } else if (Fruit_SharePrefs.getInstance().getString(Fruit_SharePrefs.WhichOne).equals("admob")) {
                        Log.d("ACB1====", "onClick: " + Fruit_SharePrefs.WhichOne);
                        Fruit_Ads_Utils.showAppLovinRewardedAd(activity);
//                        Fruit_Preferences.PREF_SETTING.putLong(Fruit_Preferences.KEY_LAST_PLAY_TIME, System.currentTimeMillis());
                        adshownonce = true;
                        Log.e("TAG", "onClick:appLovin=== " + adshownonce);
                        spinWheel();
                    }
                } else {
//                    Fruit_Preferences.PREF_SETTING.putLong(Fruit_Preferences.KEY_LAST_PLAY_TIME, System.currentTimeMillis());
                    adshownonce = false;
                    Log.e("TAG", "onClick:else=== " + adshownonce);
                    spinWheel();
                    dismiss();

                }


            }
        });
//
//        btnPlay.popUp(200, 700);
        // Init text
        GameText txtBonus = (GameText) findViewById(R.id.txt_wheel);
        txtBonus.popUp(200, 500);

        // Check is wheel ready
        long lastTime = Fruit_Preferences.PREF_SETTING.getLong(Fruit_Preferences.KEY_LAST_PLAY_TIME, 0);
        mIsWheelEnable = lastTime == 0 || (System.currentTimeMillis() - lastTime > WHEEL_CD_TIME);

        // Show watch ad button if wheel not ready
        if (!mIsWheelEnable) {
            btnPlay.setBackgroundResource(R.drawable.fruit_ui_btn_watch_ad);
            adshownonce = false;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismiss();
        }
        else if (id == R.id.btn_play) {
            if (mIsWheelEnable) {
                // Save current time and spin wheel
//                Fruit_Preferences.PREF_SETTING.putLong(Fruit_Preferences.KEY_LAST_PLAY_TIME, System.currentTimeMillis());
//                spinWheel();
            } else {
//                showRewardAd();
            }
            // Hide play button
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onEarnReward() {
        spinWheel();
    }

    @Override
    public void onLossReward() {
        // We do nothing
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void spinWheel() {
        // Generate a random degree from 0 to 360
        int random = RandomUtils.nextInt(360);
        // We want to rotate at least 3 times
        int degree = random + 720;

        // Wheel rotate animation
        RotateAnimation wheelAnimation = new RotateAnimation(0, degree,
                1, 0.5f, 1, 0.5f);
        wheelAnimation.setDuration(4000);
        wheelAnimation.setFillAfter(true);
        wheelAnimation.setInterpolator(new DecelerateInterpolator());
        wheelAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Dismiss and show prize
                dismiss();
                showPrize(degree);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        findViewById(R.id.image_wheel).startAnimation(wheelAnimation);

        // Pointer rotate animation
        RotateAnimation pointerAnimation = new RotateAnimation(0, -30,
                1, 0.5f, 1, 0.35f);
        pointerAnimation.setDuration(200);
        pointerAnimation.setRepeatMode(Animation.REVERSE);
        pointerAnimation.setRepeatCount(15);
        findViewById(R.id.image_wheel_pointer).startAnimation(pointerAnimation);

        // Play spinning sound
        Fruit_Sounds.WHEEL_SPIN.play();
    }

    private void showPrize(int degree) {
        // Get degree
        degree = degree % 360;

        // Save prizes base on degree
        Fruit_Prize prize = getPrize(degree);
        savePrizes(prize.getName(), prize.getCount());
        updateCoin();

        // Show prize dialog
        Fruit_NewPrizeDialog newPrizeDialog = new Fruit_NewPrizeDialog(mParent, prize);
        mParent.showDialog(newPrizeDialog);
    }

    private Fruit_Prize getPrize(int degree) {
        Fruit_PrizeManager prizeManager = new Fruit_PrizeManager();
        if (degree < 72) {
            return prizeManager.getPrize(Fruit_PrizeManager.PRIZE_HAMMER);
        } else if (degree < 144) {
            return prizeManager.getPrize(Fruit_PrizeManager.PRIZE_BOMB);
        } else if (degree < 216) {
            return prizeManager.getPrize(Fruit_PrizeManager.PRIZE_COIN_50);
        } else if (degree < 288) {
            return prizeManager.getPrize(Fruit_PrizeManager.PRIZE_GLOVE);
        } else {
            return prizeManager.getPrize(Fruit_PrizeManager.PRIZE_COIN_150);
        }
    }

    private void savePrizes(String name, int count) {
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mParent);
        int saving = databaseHelper.getItemCount(name);
        databaseHelper.updateItemCount(name, saving + count);
    }

//    private void showRewardAd() {
//        Fruit_AdManager adManager = ((Fruit_MainActivity) mParent).getAdManager();
//        adManager.setListener(this);
//        boolean isConnect = adManager.showRewardAd();
//        // Show error dialog if no internet connect
//        if (!isConnect) {
//            Fruit_ErrorDialog dialog = new Fruit_ErrorDialog(mParent) {
//                @Override
//                public void retry() {
//                    adManager.requestAd();
//                    showRewardAd();
//                }
//            };
//            mParent.showDialog(dialog);
//        }
//    }

    public void updateCoin() {
    }
    //========================================================

}
