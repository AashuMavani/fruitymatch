package com.gamenative.fruitymatch.fruit_game.effect.combine;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;
import com.nativegame.natyengine.entity.modifier.DurationModifier;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamCombineRingEffectSystem extends Entity {

    private static final long TIME_TO_LIVE = 1500;
    private static final long TIME_TO_SPAWN = 500;
    private static final int SCALE_INCREMENT = 2;

    private final Pool<Fruit_IceCreamCombineRingEffect> mEffectPool;
    private final DurationModifier mDurationModifier;

    private float mTargetX;
    private float mTargetY;
    private float mCurrentScale;
    private long mTotalTime;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamCombineRingEffectSystem(Engine engine, int count) {
        super(engine);
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_IceCreamCombineRingEffect>() {
            @Override
            public Fruit_IceCreamCombineRingEffect createObject() {
                return new Fruit_IceCreamCombineRingEffect(Fruit_IceCreamCombineRingEffectSystem.this, engine,
                        Fruit_Textures.LIGHT_RING);
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
            // Add ring and update scale
            mEffectPool.obtainObject().activate(mTargetX, mTargetY, mCurrentScale);
            mCurrentScale -= SCALE_INCREMENT;
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
        mCurrentScale = 10;
        mDurationModifier.init(this);
        addToGame();
        mTotalTime = 0;
    }

    public void returnToPool(Fruit_IceCreamCombineRingEffect effect) {
        mEffectPool.returnObject(effect);
    }
    //========================================================

}
