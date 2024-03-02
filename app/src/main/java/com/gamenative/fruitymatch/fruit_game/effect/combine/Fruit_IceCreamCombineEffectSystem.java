package com.gamenative.fruitymatch.fruit_game.effect.combine;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamCombineEffectSystem {

    private final Pool<Fruit_IceCreamCombineEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamCombineEffectSystem(Engine engine, int count) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_IceCreamCombineEffect>() {
            @Override
            public Fruit_IceCreamCombineEffect createObject() {
                return new Fruit_IceCreamCombineEffect(Fruit_IceCreamCombineEffectSystem.this, engine, Fruit_Textures.ICE_CREAM);
            }
        }, count * 2);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mEffectPool.obtainObject().activate(x, y, Fruit_IceCreamCombineEffect.ColorCombineDirection.LEFT);
        mEffectPool.obtainObject().activate(x, y, Fruit_IceCreamCombineEffect.ColorCombineDirection.RIGHT);
        Fruit_Sounds.ICE_CREAM_COMBINE.play();
    }

    public void returnToPool(Fruit_IceCreamCombineEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
