package com.gamenative.fruitymatch.fruit_ui.fruit_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Musics;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_game.Fruit_JuicyMatch;
import com.nativegame.natyengine.Game;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameFragment;
import com.nativegame.natyengine.ui.GameView;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_JuicyMatchFragment extends GameFragment implements View.OnClickListener {

    private Game mGame;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_JuicyMatchFragment() {
        // Required empty public constructor
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fruit_fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init button
        GameButton btnPause = (GameButton) view.findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);

        // Update bg music
        Fruit_Musics.BG_MUSIC.setCurrentStream(false);
        Fruit_Musics.BG_MUSIC.stop();
        Fruit_Musics.GAME_MUSIC.setCurrentStream(true);
        Fruit_Musics.GAME_MUSIC.play();
    }

    @Override
    protected void onViewCreated(View view) {
        mGame = new Fruit_JuicyMatch(getGameActivity(), (GameView) view.findViewById(R.id.game_view), getGameActivity().getEngine());
        mGame.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGame.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGame.stop();

        // Update bg music
        Fruit_Musics.GAME_MUSIC.setCurrentStream(false);
        Fruit_Musics.GAME_MUSIC.stop();
        Fruit_Musics.BG_MUSIC.setCurrentStream(true);
        Fruit_Musics.BG_MUSIC.play();
    }

    @Override
    public boolean onBackPressed() {
        mGame.pause();
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_pause) {
            mGame.pause();
            Fruit_Sounds.BUTTON_CLICK.play();
        }
    }
    //========================================================

}
