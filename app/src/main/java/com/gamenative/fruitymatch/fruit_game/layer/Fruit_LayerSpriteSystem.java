package com.gamenative.fruitymatch.fruit_game.layer;

import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_LayerSpriteSystem<T extends Fruit_LayerSprite> extends Entity {

    protected final int mTotalRow;
    protected final int mTotalCol;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_LayerSpriteSystem(Engine engine) {
        super(engine);
        mTotalRow = Fruit_Level.LEVEL_DATA.getRow();
        mTotalCol = Fruit_Level.LEVEL_DATA.getColumn();
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getTotalRow() {
        return mTotalRow;
    }

    public int getTotalColumn() {
        return mTotalCol;
    }

    public abstract T[][] getChild();

    public abstract T getChildAt(int row, int col);
    //========================================================

}
