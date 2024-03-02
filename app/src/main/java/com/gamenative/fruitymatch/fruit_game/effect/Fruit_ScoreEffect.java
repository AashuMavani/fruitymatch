package com.gamenative.fruitymatch.fruit_game.effect;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.modifier.PositionYModifier;
import com.nativegame.natyengine.entity.modifier.ScaleInModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ScoreEffect extends Sprite {

    private static final long TIME_TO_SCALE = 500;
    private static final long TIME_TO_FADE = 400;

    private final Fruit_ScoreEffectSystem mParent;
    private final ScaleInModifier mScaleInModifier;
    private final FadeOutModifier mFadeOutModifier;
    private final PositionYModifier mPositionModifier;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ScoreEffect(Fruit_ScoreEffectSystem scoreEffectSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = scoreEffectSystem;
        mScaleInModifier = new ScaleInModifier(TIME_TO_SCALE, OvershootTweener.getInstance());
        mFadeOutModifier = new FadeOutModifier(TIME_TO_FADE, TIME_TO_SCALE);
        mPositionModifier = new PositionYModifier(TIME_TO_FADE, TIME_TO_SCALE);
        mPositionModifier.setAutoRemove(true);
        setLayer(Fruit_GameLayer.TEXT_LAYER);
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
        mScaleInModifier.update(this, elapsedMillis);
        mFadeOutModifier.update(this, elapsedMillis);
        mPositionModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, Fruit_FruitType fruitType) {
        setCenterX(x);
        setCenterY(y);
        setTexture(fruitType.getScoreTexture());
        mScaleInModifier.init(this);
        mFadeOutModifier.init(this);
        mPositionModifier.setValue(mY, mY - 150);
        mPositionModifier.init(this);
        addToGame();
    }
    //========================================================

}
