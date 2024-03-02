package com.gamenative.fruitymatch.fruit_game.layer.shell;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ShellSystem extends Fruit_LayerSpriteSystem<Fruit_Shell> {

    private final Fruit_Shell[][] mShells;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ShellSystem(Engine engine) {
        super(engine);
        mShells = new Fruit_Shell[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getShell().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Shell[][] getChild() {
        return mShells;
    }

    @Override
    public Fruit_Shell getChildAt(int row, int col) {
        return mShells[row][col];
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
                Fruit_ShellType type = Fruit_ShellInitializer.getType(c);
                Fruit_Shell shell = new Fruit_Shell(mEngine, Fruit_Textures.EMPTY, type);
                shell.setPosition(i, j);
                shell.addToGame();
                mShells[i][j] = shell;
            }
        }
    }
    //========================================================

}
