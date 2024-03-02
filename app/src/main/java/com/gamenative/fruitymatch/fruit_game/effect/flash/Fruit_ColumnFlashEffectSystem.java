package com.gamenative.fruitymatch.fruit_game.effect.flash;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ColumnFlashEffectSystem {

    private final Pool<Fruit_ColumnFlashEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ColumnFlashEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_ColumnFlashEffect>() {
            @Override
            public Fruit_ColumnFlashEffect createObject() {
                return new Fruit_ColumnFlashEffect(Fruit_ColumnFlashEffectSystem.this, engine, Fruit_Textures.FLASH_COLUMN_ANIMATION);
            }
        }, count * 2);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y, Fruit_FlashDirection.TOP);
        mEffectPool.obtainObject().activate(x, y, Fruit_FlashDirection.DOWN);
        Fruit_Sounds.STRIPED_EXPLODE.play();
    }

    public void returnToPool(Fruit_ColumnFlashEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
