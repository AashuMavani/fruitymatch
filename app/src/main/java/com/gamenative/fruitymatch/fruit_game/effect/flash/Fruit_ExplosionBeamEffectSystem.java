package com.gamenative.fruitymatch.fruit_game.effect.flash;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosionBeamEffectSystem {

    private final Pool<Fruit_ExplosionBeamEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosionBeamEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_ExplosionBeamEffect>() {
            @Override
            public Fruit_ExplosionBeamEffect createObject() {
                return new Fruit_ExplosionBeamEffect(Fruit_ExplosionBeamEffectSystem.this, engine, Fruit_Textures.FLASH_BEAM);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        int size = Fruit_FlashDirection.values().length;
        for (int i = 0; i < size; i++) {
            mEffectPool.obtainObject().activate(x, y, Fruit_FlashDirection.values()[i]);
        }
    }

    public void returnToPool(Fruit_ExplosionBeamEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
