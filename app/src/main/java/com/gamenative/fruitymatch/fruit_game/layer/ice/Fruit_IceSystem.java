package com.gamenative.fruitymatch.fruit_game.layer.ice;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceSystem extends Fruit_LayerSpriteSystem<Fruit_Ice> {

    private final Fruit_Ice[][] mIces;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceSystem(Engine engine) {
        super(engine);
        mIces = new Fruit_Ice[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getIce().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Ice[][] getChild() {
        return mIces;
    }

    @Override
    public Fruit_Ice getChildAt(int row, int col) {
        return mIces[row][col];
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
                Fruit_IceType type = Fruit_IceInitializer.getType(c);
                int layer = Fruit_IceInitializer.getLayer(c);
                Fruit_Ice ice = new Fruit_Ice(mEngine, type.getTexture(layer), type, layer);
                ice.setPosition(i, j);
                ice.addToGame();
                mIces[i][j] = ice;
            }
        }
    }
    //========================================================

}
