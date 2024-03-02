package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamTileCombineHandler extends Fruit_IceCreamCombineHandler {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamTileCombineHandler(Engine engine) {
        super(engine);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public long getStartDelay() {
        return 0;
    }

    @Override
    public boolean checkSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        if (tileA.getSpecialType() == Fruit_SpecialType.ICE_CREAM
                && tileB.getSpecialType() == Fruit_SpecialType.NONE
                && tileB.getTileType() != Fruit_FruitType.NONE) {
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }
        if (tileB.getSpecialType() == Fruit_SpecialType.ICE_CREAM
                && tileA.getSpecialType() == Fruit_SpecialType.NONE
                && tileA.getTileType() != Fruit_FruitType.NONE) {
            handleSpecialCombine(tiles, tileB, tileA, row, col);
            return true;
        }

        return false;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile colorTile, Fruit_Tile fruitTile, int row, int col) {
        colorTile.setTileState(Fruit_TileState.MATCH);
        fruitTile.setTileState(Fruit_TileState.MATCH);

        // Pop the same type tile
        Fruit_TileType targetType = fruitTile.getTileType();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop multiple times
                if (t.getTileType() == targetType && t.getTileState() == Fruit_TileState.IDLE) {
                    // We pop this tile and obstacle around
                    t.matchTile();
                    // Add lightning effect from color tile to target tile
                    playLightningEffect(colorTile, t);
                }
            }
        }

        playTileEffect(colorTile, fruitTile);
    }
    //========================================================

}
