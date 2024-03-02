package com.gamenative.fruitymatch.fruit_ui.fruit_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.gamenative.fruitymatch.fruit_ad.Fruit_AdManager;
import com.gamenative.fruitymatch.fruit_ad.Fruit_Ads_Utils;
import com.gamenative.fruitymatch.fruit_ad.Fruit_Common_Utils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.gamenative.fruitymatch.Fruit_MainActivity;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_database.Fruit_DatabaseHelper;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.gamenative.fruitymatch.fruit_timer.Fruit_LivesTimer;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_LevelDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_MoreCoinDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_MoreLivesDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_SettingDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_ShopDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_WheelDialog;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameFragment;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;
import com.nativegame.natyengine.util.ResourceUtils;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_MapFragment extends GameFragment implements View.OnClickListener {

    private static final int TOTAL_LEVEL = 100;
    private static final int LEVEL_PRE_PAGE = 20;
    private static final int MAX_PAGE = 5;

    private Fruit_LivesTimer mLivesTimer;

    private int mCurrentLevel;
    private int mCurrentPage;

    private boolean mInterstitialEarned = false;
    private InterstitialAd mInterstitialAd;


    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_MapFragment() {
        // Required empty public constructor
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fruit_fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLivesTimer = ((Fruit_MainActivity) getGameActivity()).getLivesTimer();

        // Init button
        GameButton btnSetting = (GameButton) view.findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);


        GameButton btnNext = (GameButton) view.findViewById(R.id.btn_page_next);
        btnNext.setOnClickListener(this);

        GameButton btnPrevious = (GameButton) view.findViewById(R.id.btn_page_previous);
        btnPrevious.setOnClickListener(this);

        GameButton btnShop = (GameButton) view.findViewById(R.id.btn_shop);
        btnShop.setOnClickListener(this);

        GameButton btnWheel = (GameButton) view.findViewById(R.id.btn_wheel);
        btnWheel.setOnClickListener(this);
        GameButton btnLives = (GameButton) view.findViewById(R.id.btn_lives);
        btnLives.setOnClickListener(this);

        GameButton btnCoin = (GameButton) view.findViewById(R.id.btn_coin);
        btnCoin.setOnClickListener(this);

        // Init level button and star
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(getContext());
        mCurrentLevel = databaseHelper.getAllLevelStars().size() + 1;
        if (mCurrentLevel > TOTAL_LEVEL) {
            mCurrentLevel = TOTAL_LEVEL;
        }
        mCurrentPage = (int) Math.ceil(mCurrentLevel * 1.0d / LEVEL_PRE_PAGE);
        updatePage(mCurrentPage);

        loadCoin();
        loadAd();



        // Show current level dialog
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLevelDialog(mCurrentLevel);
            }
        }, 800);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLivesTimer.startTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLivesTimer.stopTimer();
    }

    @Override
    public boolean onBackPressed() {
        getGameActivity().navigateToFragment(new Fruit_MenuFragment());
        return true;
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_page_next) {
            if (mCurrentPage < MAX_PAGE) {
                mCurrentPage++;
                updatePage(mCurrentPage);
            }
        } else if (id == R.id.btn_page_previous) {
            if (mCurrentPage > 1) {
                mCurrentPage--;
                updatePage(mCurrentPage);
            }
        } else if (id == R.id.btn_shop) {
            showShopDialog();
        } else if (id == R.id.btn_wheel) {
            //showMobRewardedAdd....
//            Fruit_Ads_Utils.showAdMobRewarded(getActivity());

//        showApplovinRewardad.......
//            Fruit_Ads_Utils.showAppLovinRewardedAd(getActivity());
            showWheelDialog();
        } else if (id == R.id.btn_coin) {
            showMoreCoinDialog();
        } else if (id == R.id.btn_lives) {
            if (mLivesTimer.getLivesCount() < Fruit_LivesTimer.MAX_LIVES) {
                showMoreLivesDialog();

            }
        } else if (id == R.id.btn_setting) {
            //showAdmobInterstitalAdd....
//            Fruit_Ads_Utils.showAdMobInterstitial(getActivity());
            showSettingDialog();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void updatePage(int page) {
        // Update page number text
        TextView currentPage = (TextView) getView().findViewById(R.id.txt_current_page);
        currentPage.setText(String.valueOf(page));

        TextView previousPage = (TextView) getView().findViewById(R.id.txt_previous_page);
        previousPage.setText(page == 1 ? "" : String.valueOf(page - 1));

        TextView nextPage = (TextView) getView().findViewById(R.id.txt_next_page);
        nextPage.setText(page == MAX_PAGE ? "" : String.valueOf(page + 1));

        // Update level button and star
        loadButton(page);
        loadStar(page);
    }

    private void loadButton(int page) {
        int increment = (page - 1) * 20;

        for (int i = 1; i <= LEVEL_PRE_PAGE; i++) {

            // Init level button
            String name = "btn_level_" + i;
            int id = getResources().getIdentifier(name, "id", getGameActivity().getPackageName());
            GameText txtLevel = (GameText) getView().findViewById(id);

            int level = i + increment;
            if (level <= mCurrentLevel) {
                txtLevel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fruit_Sounds.BUTTON_CLICK.play();
                        showLevelDialog(level);
                    }
                });
                txtLevel.setBackgroundResource(R.drawable.fruit_ui_btn_level);
                txtLevel.setEnabled(true);
            } else {
                txtLevel.setOnClickListener(null);
                txtLevel.setBackgroundResource(R.drawable.fruit_ui_btn_level_lock);
                txtLevel.setEnabled(false);
            }
            txtLevel.setText(String.valueOf(level));
            txtLevel.popUp(200, i * 30);
        }
    }

    private void loadStar(int page) {
        int increment = (page - 1) * 20;
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(getContext());
        List<Integer> stars = databaseHelper.getAllLevelStars();

        for (int i = 1; i <= LEVEL_PRE_PAGE; i++) {

            // Init level star
            String name = "image_level_star_" + i;
            int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
            GameImage imageStar = (GameImage) getView().findViewById(id);

            int level = i + increment;
            if (level < mCurrentLevel) {
                int star = stars.get(level - 1);
                switch (star) {
                    case 1:
                        imageStar.setBackgroundResource(R.drawable.fruit_ui_star_set_01);
                        break;
                    case 2:
                        imageStar.setBackgroundResource(R.drawable.fruit_ui_star_set_02);
                        break;
                    case 3:
                        imageStar.setBackgroundResource(R.drawable.fruit_ui_star_set_03);
                        break;
                }
                imageStar.setVisibility(View.VISIBLE);
            } else {
                imageStar.setVisibility(View.INVISIBLE);
            }
            imageStar.popUp(200, i * 40);
        }
    }

    private void loadCoin() {
        TextView textCoin = (TextView) getView().findViewById(R.id.txt_coin);
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(getContext());
        int coin = databaseHelper.getItemCount(Fruit_Item.COIN);
        textCoin.setText(String.valueOf(coin));
    }

    private void loadAd() {
        FrameLayout Ads = getView().findViewById(R.id.Ads);
//      Ads.addView( Fruit_Ads_Utils.showAdmobbannerAd());
//       Ads=setAdUnitId(Fruit_Ads_Utils.getAdMobBanner());
//        Fruit_Ads_Utils.showAdmobbannerAd(getActivity(),null);

//



    }

    private void showLevelDialog(int level) {
        // We load level data here before starting game
        Fruit_Level.load(getContext(), level);
        Fruit_LevelDialog levelDialog = new Fruit_LevelDialog(getGameActivity()) {
            @Override
            public void startGame() {
                // Check is player lives enough
                if (mLivesTimer.getLivesCount() > 0) {
                    getGameActivity().navigateToFragment(new Fruit_JuicyMatchFragment());
                } else {
                    showMoreLivesDialog();
                }
            }
        };
        getGameActivity().showDialog(levelDialog);
    }

    //


    private void showShopDialog() {
        Fruit_ShopDialog shopDialog = new Fruit_ShopDialog(getGameActivity()) {
            @Override
            public void updateCoin() {
                loadCoin();
            }
        };
        getGameActivity().showDialog(shopDialog);
    }

    private void showWheelDialog() {
        Fruit_WheelDialog wheelDialog = new Fruit_WheelDialog(getGameActivity()) {
            @Override
            public void updateCoin() {
                loadCoin();
            }
        };
        getGameActivity().showDialog(wheelDialog);
    }

    private void showMoreCoinDialog() {
        Fruit_MoreCoinDialog moreCoinDialog = new Fruit_MoreCoinDialog(getGameActivity()) {
            @Override
            public void updateCoin() {
                loadCoin();
            }
        };
        getGameActivity().showDialog(moreCoinDialog);
    }

    private void showMoreLivesDialog() {

        Fruit_MoreLivesDialog moreLivesDialog = new Fruit_MoreLivesDialog(getGameActivity());
        getGameActivity().showDialog(moreLivesDialog);
    }


    private void showSettingDialog() {
        Fruit_SettingDialog settingDialog = new Fruit_SettingDialog(getGameActivity());
        getGameActivity().showDialog(settingDialog);
    }
    //========================================================

}
