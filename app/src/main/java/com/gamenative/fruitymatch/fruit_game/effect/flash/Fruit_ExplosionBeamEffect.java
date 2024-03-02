package com.gamenative.fruitymatch.fruit_game.effect.flash;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosionBeamEffect extends Sprite {

    private static final long TIME_TO_LIVE = 400;

    private final Fruit_ExplosionBeamEffectSystem mParent;
    private final ScaleModifier mScaleModifier;
    private final FadeOutModifier mFadeOutModifier;
    private final float mSpeed;

    private float mSpeedX;
    private float mSpeedY;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosionBeamEffect(Fruit_ExplosionBeamEffectSystem explosionBeamEffectSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = explosionBeamEffectSystem;
        mScaleModifier = new ScaleModifier(0.5f, 2f, TIME_TO_LIVE);
        mFadeOutModifier = new FadeOutModifier(TIME_TO_LIVE);
        mFadeOutModifier.setAutoRemove(true);
        mSpeed = 2000f / 1000;
        setLayer(Fruit_GameLayer.EFFECT_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onRemove() {
        mParent.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        mX += mSpeedX * elapsedMillis;
        mY += mSpeedY * elapsedMillis;
        mScaleModifier.update(this, elapsedMillis);
        mFadeOutModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, Fruit_FlashDirection direction) {
        setCenterX(x);
        setCenterY(y);
        setRotation(direction.getAngle());
        mSpeedX = direction.getDirectionX() * mSpeed;
        mSpeedY = direction.getDirectionY() * mSpeed;
        mScaleModifier.init(this);
        mFadeOutModifier.init(this);
        addToGame();
    }
    //========================================================

}
