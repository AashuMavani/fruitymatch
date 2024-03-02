package com.gamenative.fruitymatch.fruit_game.effect.piece;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamPieceEffectSystem {

    private final Pool<Fruit_IceCreamPieceEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamPieceEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_IceCreamPieceEffect>() {
            @Override
            public Fruit_IceCreamPieceEffect createObject() {
                return new Fruit_IceCreamPieceEffect(Fruit_IceCreamPieceEffectSystem.this, engine, Fruit_Textures.ICE_CREAM);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y);
        Fruit_Sounds.ICE_CREAM_TRANSFORM.play();
    }

    public void returnToPool(Fruit_IceCreamPieceEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
