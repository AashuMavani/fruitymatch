package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ColumnFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_RowFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_DoubleStripedTileCombineHandler extends Fruit_BaseSpecialCombineHandler {

    private final Fruit_RowFlashEffectSystem mRowFlashEffectSystem;
    private final Fruit_ColumnFlashEffectSystem mColumnFlashEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_DoubleStripedTileCombineHandler(Engine engine) {
        super(engine);
        mRowFlashEffectSystem = new Fruit_RowFlashEffectSystem(engine, 1);
        mColumnFlashEffectSystem = new Fruit_ColumnFlashEffectSystem(engine, 1);
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
        // Check are both tiles row or column special tile
        if ((tileA.getSpecialType() == Fruit_SpecialType.ROW_STRIPED
                || tileA.getSpecialType() == Fruit_SpecialType.COLUMN_STRIPED)
                && (tileB.getSpecialType() == Fruit_SpecialType.ROW_STRIPED
                || tileB.getSpecialType() == Fruit_SpecialType.COLUMN_STRIPED)) {
            // We make sure the origin special tiles not being detected
            tileA.setTileState(Fruit_TileState.MATCH);
            tileB.setTileState(Fruit_TileState.MATCH);
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }

        return false;
    }

    @Override
    protected void playTileEffect(Fruit_Tile tileA, Fruit_Tile tileB) {
        super.playTileEffect(tileA, tileB);
        mRowFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
        mColumnFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        int targetRow = tileA.getRow();
        int targetCol = tileA.getColumn();

        // Pop row tile
        for (int j = 0; j < col; j++) {
            Fruit_Tile t = tiles[targetRow][j];
            // We make sure not pop multiple times
            if (t.getTileState() == Fruit_TileState.IDLE) {
                t.popTile();
            }
        }

        // Pop column tile
        for (int i = 0; i < row; i++) {
            Fruit_Tile t = tiles[i][targetCol];
            // We make sure not pop multiple times
            if (t.getTileState() == Fruit_TileState.IDLE) {
                t.popTile();
            }
        }

        playTileEffect(tileA, tileB);
    }
    //========================================================

}
