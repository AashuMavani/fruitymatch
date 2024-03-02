package com.gamenative.fruitymatch.fruit_game.effect.tutorial;

import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.pool.Pool;
import com.nativegame.natyengine.util.pool.SafeFixedObjectPool;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TutorialHintEffectSystem {

    private final Pool<Fruit_TutorialHintEffect> mEffectPool;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_TutorialHintEffectSystem(Engine engine) {
        mEffectPool = new SafeFixedObjectPool<>(new Pool.PoolObjectFactory<Fruit_TutorialHintEffect>() {
            @Override
            public Fruit_TutorialHintEffect createObject() {
                return new Fruit_TutorialHintEffect(engine, 300, 300);
            }
        }, 50);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(char[] chars) {
        int row = Fruit_Level.LEVEL_DATA.getRow();
        int col = Fruit_Level.LEVEL_DATA.getColumn();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = chars[i * col + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                Fruit_TutorialHintEffect effect = mEffectPool.obtainObject();
                effect.activate(j * 300, i * 300);
            }
        }
    }
    //========================================================

}
