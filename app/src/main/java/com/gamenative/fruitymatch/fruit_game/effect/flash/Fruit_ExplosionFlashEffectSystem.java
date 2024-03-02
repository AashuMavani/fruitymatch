package com.gamenative.fruitymatch.fruit_game.effect.flash;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosionFlashEffectSystem {

    private final Pool<Fruit_ExplosionFlashEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosionFlashEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_ExplosionFlashEffect>() {
            @Override
            public Fruit_ExplosionFlashEffect createObject() {
                return new Fruit_ExplosionFlashEffect(Fruit_ExplosionFlashEffectSystem.this, engine, Fruit_Textures.FLASH_EXPLOSION_ANIMATION);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y);
        Fruit_Sounds.EXPLOSIVE_EXPLODE.play();
    }

    public void returnToPool(Fruit_ExplosionFlashEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
