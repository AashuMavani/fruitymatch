package com.gamenative.fruitymatch.fruit_game.counter;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.runnable.RunnableEntity;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.ui.GameActivity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ScoreCounter extends RunnableEntity implements EventListener {

    private static final int POINTS_PER_FRUIT = 10;

    private final TextView mTxtScore;
    private final Animation mPulseAnimation;

    private int mPoints;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ScoreCounter(GameActivity activity, Engine engine) {
        super(activity, engine);
        mTxtScore = activity.findViewById(R.id.txt_score);
        mPulseAnimation = AnimationUtils.loadAnimation(activity, R.anim.fruit_text_pulse);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onStart() {
        mPoints = 0;
        setPostRunnable(true);
    }

    @Override
    protected void onUpdateRunnable() {
        mTxtScore.setText(String.valueOf(mPoints));
        mTxtScore.startAnimation(mPulseAnimation);
    }

    @Override
    public void onEvent(Event event) {
        switch ((Fruit_GameEvent) event) {
            case PLAYER_SCORE:
            case ADD_BONUS:
                mPoints += POINTS_PER_FRUIT;
                Fruit_Level.LEVEL_DATA.setScore(mPoints);
                setPostRunnable(true);
                break;
            case GAME_OVER:
            case BONUS_TIME_END:
                removeFromGame();
                break;
        }
    }
    //========================================================

}
