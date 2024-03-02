package com.gamenative.fruitymatch.fruit_ui.fruit_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_ad.Fruit_Ads_Utils;
import com.gamenative.fruitymatch.fruit_ad.Fruit_Common_Utils;
import com.gamenative.fruitymatch.fruit_ad.Fruit_SharePrefs;
import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Musics;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Preferences;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_ExitDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_SettingDialog;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.gson.Gson;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameFragment;
import com.nativegame.natyengine.ui.GameImage;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_MenuFragment extends GameFragment implements View.OnClickListener {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_MenuFragment() {
        // Required empty public constructor
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fruit_fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//appmob nativead

//        Fruit_Ads_Utils.showAdMobANative(getActivity(),null);-

//App lovin Banner Ads.....
//        LinearLayout layoutBannerAdTop=(LinearLayout)view.findViewById(R.id.layoutBannerAdTop);
//        TextView lblAdSpaceTop=(TextView)view.findViewById(R.id.lblAdSpaceTop);
//       layoutBannerAdTop.setVisibility(View.VISIBLE);
//       Fruit_Common_Utils.loadBannerAds(getActivity(),layoutBannerAdTop,lblAdSpaceTop);


        GameImage imageLogo = (GameImage) view.findViewById(R.id.image_logo);
        imageLogo.popUp(1000, 300);
        Animation scaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fruit_logo_pulse);
        imageLogo.startAnimation(scaleAnimation);

        GameImage imageLogoBg = (GameImage) view.findViewById(R.id.image_logo_bg);
        imageLogoBg.popUp(300, 300);
        Animation rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fruit_logo_rotate);
        imageLogoBg.startAnimation(rotateAnimation);

        // Init button
        GameButton btnPlay = (GameButton) view.findViewById(R.id.btn_start);
        btnPlay.popUp(200, 600);
        btnPlay.setOnClickListener(this);

        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fruit_button_pulse);
        btnPlay.startAnimation(pulseAnimation);
//

        GameButton btnSetting = (GameButton) view.findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);


        boolean musicEnable = Fruit_Preferences.PREF_SETTING.getBoolean(Fruit_Preferences.KEY_MUSIC, true);
        boolean soundEnable = Fruit_Preferences.PREF_SETTING.getBoolean(Fruit_Preferences.KEY_SOUND, true);
        getGameActivity().getMusicManager().setAudioEnable(musicEnable);
        getGameActivity().getSoundManager().setAudioEnable(soundEnable);

        // Play bg music
        Fruit_Musics.BG_MUSIC.play();
    }

    @Override
    public boolean onBackPressed() {
        showExitDialog();
        return true;
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_start) {
//            Applovin Interstital Ad Call
//            Fruit_Ads_Utils.showAppLovinInterstitialAd(getActivity(),null);
            getGameActivity().navigateToFragment(new Fruit_MapFragment());
        } else if (id == R.id.btn_setting) {
//            Applovin Reward  Ad Call
//            Fruit_Ads_Utils.showAppLovinRewardedAd(getActivity(), null);
//            Admob Reward  Ad Call
//            Fruit_Ads_Utils.showAdMobRewarded(getActivity());

            showSettingDialog();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void showExitDialog() {
        Fruit_ExitDialog exitDialog = new Fruit_ExitDialog(getGameActivity()) {
            @Override
            public void exit() {

                getGameActivity().finish();
            }
        };
        getGameActivity().showDialog(exitDialog);
    }

    private void showSettingDialog() {
        Fruit_SettingDialog settingDialog = new Fruit_SettingDialog(getGameActivity());
        getGameActivity().showDialog(settingDialog);
    }


}
