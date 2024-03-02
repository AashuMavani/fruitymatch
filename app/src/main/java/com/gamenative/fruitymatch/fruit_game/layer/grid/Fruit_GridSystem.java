package com.gamenative.fruitymatch.fruit_game.layer.grid;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_GridSystem extends Fruit_LayerSpriteSystem<Fruit_Grid> {

    private final Fruit_Grid[][] mGrids;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_GridSystem(Engine engine) {
        super(engine);
        mGrids = new Fruit_Grid[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getGrid().toCharArray());
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Grid[][] getChild() {
        return mGrids;
    }

    @Override
    public Fruit_Grid getChildAt(int row, int col) {
        return mGrids[row][col];
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
                Fruit_GridType type = Fruit_GridInitializer.getType(c);
                Fruit_Grid grid = new Fruit_Grid(mEngine, type.getTexture(), type);
                grid.setPosition(i, j);
                grid.addToGame();
                mGrids[i][j] = grid;
            }
        }
    }
    //========================================================

}
