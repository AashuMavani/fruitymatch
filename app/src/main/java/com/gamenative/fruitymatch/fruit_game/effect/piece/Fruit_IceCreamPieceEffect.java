package com.gamenative.fruitymatch.fruit_game.effect.piece;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.ScaleOutModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamPieceEffect extends Sprite {

    private static final long TIME_TO_LIVE = 500;

    private final Fruit_IceCreamPieceEffectSystem mParent;
    private final ScaleOutModifier mScaleOutModifier;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamPieceEffect(Fruit_IceCreamPieceEffectSystem iceCreamPieceEffectSystem, Engine engine, Texture texture) {
        super(engine, texture);
        mParent = iceCreamPieceEffectSystem;
        mScaleOutModifier = new ScaleOutModifier(TIME_TO_LIVE);
        mScaleOutModifier.setAutoRemove(true);
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
        mScaleOutModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mScaleOutModifier.init(this);
        addToGame();
    }
    //========================================================

}
