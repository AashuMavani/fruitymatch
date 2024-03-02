package com.gamenative.fruitymatch.fruit_game.layer.generator;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileResetter;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_GeneratorSystem extends Fruit_LayerSpriteSystem<Fruit_Generator> {

    private final Fruit_Generator[][] mGenerators;

    private final List<Fruit_TileResetter> mResetters = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_GeneratorSystem(Engine engine) {
        super(engine);
        mGenerators = new Fruit_Generator[mTotalRow][mTotalCol];
        init(Fruit_Level.LEVEL_DATA.getGenerator().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public List<Fruit_TileResetter> getResetters() {
        return mResetters;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Generator[][] getChild() {
        return mGenerators;
    }

    @Override
    public Fruit_Generator getChildAt(int row, int col) {
        return mGenerators[row][col];
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
                Fruit_Generator generator = Fruit_GeneratorInitializer.getGenerator(mEngine, c);
                generator.setPosition(i, j);
                generator.addToGame();
                mGenerators[i][j] = generator;
                mResetters.add(generator.getResetter());
            }
        }
    }
    //========================================================

}
