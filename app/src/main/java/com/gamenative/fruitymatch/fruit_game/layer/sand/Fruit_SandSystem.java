package com.gamenative.fruitymatch.fruit_game.layer.sand;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SandSystem extends Fruit_LayerSpriteSystem<Fruit_Sand> {

    private final Fruit_Sand[][] mSands;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SandSystem(Engine engine) {
        super(engine);
        mSands = new Fruit_Sand[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getSand().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Sand[][] getChild() {
        return mSands;
    }

    @Override
    public Fruit_Sand getChildAt(int row, int col) {
        return mSands[row][col];
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
                Fruit_SandType type = Fruit_SandInitializer.getType(c);
                int layer = Fruit_SandInitializer.getLayer(c);
                Fruit_Sand sand = new Fruit_Sand(mEngine, type.getTexture(layer), type, layer);
                sand.setPosition(i, j);
                sand.addToGame();
                mSands[i][j] = sand;
            }
        }
    }
    //========================================================

}
