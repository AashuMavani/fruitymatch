package com.gamenative.fruitymatch.fruit_game.layer.honey;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HoneySystem extends Fruit_LayerSpriteSystem<Fruit_Honey> {

    private final Fruit_Honey[][] mHoneys;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HoneySystem(Engine engine) {
        super(engine);
        mHoneys = new Fruit_Honey[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getHoney().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Honey[][] getChild() {
        return mHoneys;
    }

    @Override
    public Fruit_Honey getChildAt(int row, int col) {
        return mHoneys[row][col];
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
                Fruit_HoneyType type = Fruit_HoneyInitialize.getType(Character.toLowerCase(c));
                Fruit_Honey honey = new Fruit_Honey(mEngine, type.getTexture(), type);
                honey.setPosition(i, j);
                if (Character.isUpperCase(c)) {
                    honey.addToGame();
                }
                mHoneys[i][j] = honey;
            }
        }
    }
    //========================================================

}

