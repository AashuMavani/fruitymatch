package com.gamenative.fruitymatch.fruit_game.effect.flash;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_RowFlashEffectSystem {

    private final Pool<Fruit_RowFlashEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_RowFlashEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_RowFlashEffect>() {
            @Override
            public Fruit_RowFlashEffect createObject() {
                return new Fruit_RowFlashEffect(Fruit_RowFlashEffectSystem.this, engine, Fruit_Textures.FLASH_ROW_ANIMATION);
            }
        }, count * 2);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y, Fruit_FlashDirection.LEFT);
        mEffectPool.obtainObject().activate(x, y, Fruit_FlashDirection.RIGHT);
        Fruit_Sounds.STRIPED_EXPLODE.play();
    }

    public void returnToPool(Fruit_RowFlashEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
