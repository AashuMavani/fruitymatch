package com.gamenative.fruitymatch.fruit_game.layer.lock;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LockSystem extends Fruit_LayerSpriteSystem<Fruit_Lock> {

    private final Fruit_Lock[][] mLocke;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LockSystem(Engine engine) {
        super(engine);
        mLocke = new Fruit_Lock[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getLock().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Lock[][] getChild() {
        return mLocke;
    }

    @Override
    public Fruit_Lock getChildAt(int row, int col) {
        return mLocke[row][col];
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                Fruit_LockType type = Fruit_LockInitializer.getType(c);
                Fruit_Lock lock = new Fruit_Lock(mEngine, type.getTexture(), type);
                lock.setPosition(i, j);
                lock.addToGame();
                mLocke[i][j] = lock;
            }
        }
    }
    //========================================================

}
