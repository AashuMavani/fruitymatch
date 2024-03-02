package com.gamenative.fruitymatch.fruit_game.layer.entrypoint;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_EntryPointSystem extends Fruit_LayerSpriteSystem<Fruit_EntryPoint> {

    private final Fruit_EntryPoint[][] mEntryPoints;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_EntryPointSystem(Engine engine) {
        super(engine);
        mEntryPoints = new Fruit_EntryPoint[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getEntry().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_EntryPoint[][] getChild() {
        return mEntryPoints;
    }

    @Override
    public Fruit_EntryPoint getChildAt(int row, int col) {
        return mEntryPoints[row][col];
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
                Fruit_EntryPointType type = Fruit_EntryPointInitializer.getType(c);
                Fruit_EntryPoint entry = new Fruit_EntryPoint(mEngine, type.getTexture(), type);
                entry.setPosition(i, j);
                entry.addToGame();
                mEntryPoints[i][j] = entry;
            }
        }
    }
    //========================================================

}
