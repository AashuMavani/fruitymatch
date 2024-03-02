package com.gamenative.fruitymatch.fruit_game.effect.combine;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_FlashDirection;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;
import com.nativegame.natyengine.entity.modifier.DurationModifier;
import com.nativegame.natyengine.util.RandomUtils;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamCombineBeamEffectSystem extends Entity {

    private static final long TIME_TO_LIVE = 2000;
    private static final long TIME_TO_SPAWN = 200;
    private static final int SPAWN_COUNT = 5;

    private final Pool<Fruit_IceCreamCombineBeamEffect> mEffectPool;
    private final DurationModifier mDurationModifier;

    private float mTargetX;
    private float mTargetY;
    private long mTotalTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamCombineBeamEffectSystem(Engine engine, int count) {
        super(engine);
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_IceCreamCombineBeamEffect>() {
            @Override
            public Fruit_IceCreamCombineBeamEffect createObject() {
                return new Fruit_IceCreamCombineBeamEffect(Fruit_IceCreamCombineBeamEffectSystem.this, engine,
                        Fruit_Textures.FLASH_BEAM);
            }
        }, count);
        mDurationModifier = new DurationModifier(TIME_TO_LIVE);
        mDurationModifier.setAutoRemove(true);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mTotalTime += elapsedMillis;
        if (mTotalTime >= TIME_TO_SPAWN) {
            // Add beam in random direction
            int size = Fruit_FlashDirection.values().length;
            for (int i = 0; i < SPAWN_COUNT; i++) {
                int index = RandomUtils.nextInt(size);
                mEffectPool.obtainObject().activate(mTargetX, mTargetY, Fruit_FlashDirection.values()[index]);
            }
            mTotalTime = 0;
        }
        mDurationModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        mTargetX = x;
        mTargetY = y;
        mDurationModifier.init(this);
        addToGame();
        mTotalTime = 0;
    }

    public void returnToPool(Fruit_IceCreamCombineBeamEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
