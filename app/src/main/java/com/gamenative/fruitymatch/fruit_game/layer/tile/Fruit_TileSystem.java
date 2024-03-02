package com.gamenative.fruitymatch.fruit_game.layer.tile;

import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSpriteSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TileSystem extends Fruit_LayerSpriteSystem<Fruit_Tile> {

    private final Fruit_Tile[][] mTiles;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_TileSystem(Engine engine) {
        super(engine);
        mTiles = new Fruit_Tile[mTotalRow][mTotalCol];
        initTile(Fruit_Level.LEVEL_DATA.getTile().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_Tile[][] getChild() {
        return mTiles;
    }

    @Override
    public Fruit_Tile getChildAt(int row, int col) {
        return mTiles[row][col];
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void initTile(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                Fruit_Tile tile = Fruit_TileInitializer.createTile(this, mEngine, i, j, c);
                tile.setPosition(i, j);
                if (c != 'e') {
                    // We do not add empty tile
                    tile.addToGame();
                }
                mTiles[i][j] = tile;
            }
        }
    }
    //========================================================

}
