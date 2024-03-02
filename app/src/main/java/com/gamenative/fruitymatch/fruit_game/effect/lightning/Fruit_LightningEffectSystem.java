package com.gamenative.fruitymatch.fruit_game.effect.lightning;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LightningEffectSystem {

    private final Pool<Fruit_LightningEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LightningEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_LightningEffect>() {
            @Override
            public Fruit_LightningEffect createObject() {
                return new Fruit_LightningEffect(Fruit_LightningEffectSystem.this, engine, Fruit_Textures.LIGHTNING_ANIMATION);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, int height, int rotation) {
        mEffectPool.obtainObject().activate(x, y, height, rotation);
    }

    public void returnToPool(Fruit_LightningEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
