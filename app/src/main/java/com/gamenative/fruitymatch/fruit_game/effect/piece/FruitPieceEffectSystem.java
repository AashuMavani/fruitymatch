package com.gamenative.fruitymatch.fruit_game.effect.piece;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class FruitPieceEffectSystem {

    private final Pool<FruitPieceEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public FruitPieceEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<FruitPieceEffect>() {
            @Override
            public FruitPieceEffect createObject() {
                return new FruitPieceEffect(FruitPieceEffectSystem.this, engine, Fruit_Textures.CHERRY);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y, Fruit_FruitType fruitType, Fruit_SpecialType specialType) {
        mEffectPool.obtainObject().activate(x, y, fruitType, specialType, FruitPiece.LEFT);
        mEffectPool.obtainObject().activate(x, y, fruitType, specialType, FruitPiece.RIGHT);
    }

    public void returnToPool(FruitPieceEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
