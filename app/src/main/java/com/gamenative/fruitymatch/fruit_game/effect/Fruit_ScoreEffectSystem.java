package com.gamenative.fruitymatch.fruit_game.effect;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ScoreEffectSystem {

    private final Pool<Fruit_ScoreEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ScoreEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_ScoreEffect>() {
            @Override
            public Fruit_ScoreEffect createObject() {
                return new Fruit_ScoreEffect(Fruit_ScoreEffectSystem.this, engine, Fruit_Textures.SCORE_PINK);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, Fruit_FruitType fruitType) {
        mEffectPool.obtainObject().activate(x, y, fruitType);
    }

    public void returnToPool(Fruit_ScoreEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
