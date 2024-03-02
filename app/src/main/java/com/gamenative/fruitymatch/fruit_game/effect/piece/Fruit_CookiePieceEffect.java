package com.gamenative.fruitymatch.fruit_game.effect.piece;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.modifier.RotationModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.RandomUtils;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CookiePieceEffect extends Sprite {

    private static final long TIME_TO_LIVE = 500;

    private final Fruit_CookiePiece mCookiePiece;
    private final RotationModifier mRotationModifier;
    private final FadeOutModifier mFadeOutModifier;

    private float mSpeedX;
    private float mSpeedY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_CookiePieceEffect(Engine engine, Texture texture, Fruit_CookiePiece cookiePiece) {
        super(engine, texture);
        mCookiePiece = cookiePiece;
        mRotationModifier = new RotationModifier(0, RandomUtils.nextSign() * 30f, TIME_TO_LIVE);
        mFadeOutModifier = new FadeOutModifier(TIME_TO_LIVE);
        mFadeOutModifier.setAutoRemove(true);
        setLayer(Fruit_GameLayer.EFFECT_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_CookiePiece getCookiePiece() {
        return mCookiePiece;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mX += mSpeedX * elapsedMillis;
        mY += mSpeedY * elapsedMillis;
        mRotationModifier.update(this, elapsedMillis);
        mFadeOutModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mSpeedX = mCookiePiece.getDirectionX() * 200f / 1000;
        mSpeedY = mCookiePiece.getDirectionY() * 200f / 1000;
        mRotationModifier.init(this);
        mFadeOutModifier.init(this);
        addToGame();
    }
    //========================================================

}
