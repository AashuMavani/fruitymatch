package com.gamenative.fruitymatch.fruit_game.effect;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.sprite.AnimateSprite;
import com.nativegame.natyengine.texture.texture2d.Texture2DGroup;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SmokeEffect extends AnimateSprite {

    private static final long TIME_TO_LIVE = 400;

    private final FadeOutModifier mFadeOutModifier;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SmokeEffect(Engine engine, Texture2DGroup textureGroup) {
        super(engine, textureGroup);
        mFadeOutModifier = new FadeOutModifier(TIME_TO_LIVE);
        setAnimation(50, false);
        setAnimationAutoStart(true);
        setAnimationAutoRemove(true);
        setLayer(Fruit_GameLayer.EFFECT_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mFadeOutModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mFadeOutModifier.init(this);
        addToGame();
    }
    //========================================================

}
