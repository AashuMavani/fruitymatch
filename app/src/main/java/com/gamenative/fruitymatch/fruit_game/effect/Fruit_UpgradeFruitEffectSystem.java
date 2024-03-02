package com.gamenative.fruitymatch.fruit_game.effect;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_UpgradeFruitEffectSystem {

    private final Pool<Fruit_UpgradeFruitEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_UpgradeFruitEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_UpgradeFruitEffect>() {
            @Override
            public Fruit_UpgradeFruitEffect createObject() {
                return new Fruit_UpgradeFruitEffect(Fruit_UpgradeFruitEffectSystem.this, engine, Fruit_Textures.CHERRY);
            }
        }, count);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float startX, float startY, float endX, float endY, Fruit_FruitType fruitType) {
        mEffectPool.obtainObject().activate(startX, startY, endX, endY, fruitType);
        Fruit_Sounds.TILE_UPGRADE.play();
    }

    public void returnToPool(Fruit_UpgradeFruitEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
