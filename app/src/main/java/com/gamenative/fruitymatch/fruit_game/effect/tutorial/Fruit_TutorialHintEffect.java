package com.gamenative.fruitymatch.fruit_game.effect.tutorial;

import android.graphics.Color;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.shape.primitive.Rectangle;
import com.nativegame.natyengine.input.touch.TouchEventListener;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TutorialHintEffect extends Rectangle implements TouchEventListener {

    protected final int mMarginX;
    protected final int mMarginY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_TutorialHintEffect(Engine engine, int width, int height) {
        super(engine, width, height);
        mMarginX = (Fruit_GameWorld.WORLD_WIDTH - Fruit_Level.LEVEL_DATA.getColumn() * width) / 2;
        mMarginY = (Fruit_GameWorld.WORLD_HEIGHT - Fruit_Level.LEVEL_DATA.getRow() * height) / 2;
        mPaint.setColor(Color.BLACK);
        setAlpha(200);
        setLayer(Fruit_GameLayer.EFFECT_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onTouchEvent(int type, float touchX, float touchY) {
        // Remove when player touch screen
        removeFromGame();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mX = x + mMarginX;
        mY = y + mMarginY;
        addToGame();
    }
    //========================================================

}
