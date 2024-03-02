package com.gamenative.fruitymatch.fruit_game.effect.piece;

import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosionPieceEffectSystem {

    private final Pool<Fruit_ExplosionPieceEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosionPieceEffectSystem(Engine engine, Texture texture, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_ExplosionPieceEffect>() {
            @Override
            public Fruit_ExplosionPieceEffect createObject() {
                return new Fruit_ExplosionPieceEffect(Fruit_ExplosionPieceEffectSystem.this, engine, texture);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, int count) {
        for (int i = 0; i < count; i++) {
            mEffectPool.obtainObject().activate(x, y);
        }
    }

    public void returnToPool(Fruit_ExplosionPieceEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
