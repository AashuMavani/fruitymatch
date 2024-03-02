package com.gamenative.fruitymatch.fruit_game.effect.lightning;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LightningGlitterEffectSystem {

    private final Pool<Fruit_LightningGlitterEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LightningGlitterEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_LightningGlitterEffect>() {
            @Override
            public Fruit_LightningGlitterEffect createObject() {
                return new Fruit_LightningGlitterEffect(Fruit_LightningGlitterEffectSystem.this, engine, Fruit_Textures.GLITTER_BLUE);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y);
    }

    public void returnToPool(Fruit_LightningGlitterEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
