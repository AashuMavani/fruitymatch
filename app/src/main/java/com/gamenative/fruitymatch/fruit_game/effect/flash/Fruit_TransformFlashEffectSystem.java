package com.gamenative.fruitymatch.fruit_game.effect.flash;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TransformFlashEffectSystem {

    private final Pool<Fruit_TransformFlashEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_TransformFlashEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_TransformFlashEffect>() {
            @Override
            public Fruit_TransformFlashEffect createObject() {
                return new Fruit_TransformFlashEffect(Fruit_TransformFlashEffectSystem.this, engine,
                        Fruit_Textures.FLASH_TRANSFORM_ANIMATION);
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

    public void returnToPool(Fruit_TransformFlashEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
