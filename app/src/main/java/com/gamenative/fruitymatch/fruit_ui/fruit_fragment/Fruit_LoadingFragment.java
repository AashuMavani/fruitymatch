package com.gamenative.fruitymatch.fruit_ui.fruit_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.MobileAds;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Colors;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Fonts;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Musics;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Preferences;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.ui.GameFragment;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LoadingFragment extends GameFragment {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LoadingFragment() {
        // Required empty public constructor
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fruit_fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Load assets
                Fruit_Textures.load(getGameActivity().getTextureManager(), getContext());
                Fruit_Sounds.load(getGameActivity().getSoundManager());
                Fruit_Musics.load(getGameActivity().getMusicManager());
                Fruit_Fonts.load(getContext());
                Fruit_Colors.load(getContext());
                Fruit_Preferences.load(getContext());

                // Load ad
                MobileAds.initialize(getContext());

                // Navigate to menu when loading complete
                getGameActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getGameActivity().navigateToFragment(new Fruit_MenuFragment());
                    }
                });
            }
        }).start();
    }
    //========================================================

}
