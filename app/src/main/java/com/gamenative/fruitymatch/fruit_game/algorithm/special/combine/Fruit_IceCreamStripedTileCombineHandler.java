package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.timer.Timer;
import com.nativegame.natyengine.entity.timer.TimerEvent;
import com.nativegame.natyengine.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamStripedTileCombineHandler extends Fruit_IceCreamCombineHandler implements TimerEvent.TimerEventListener {

    private static final long START_DELAY = 1200;

    private final Timer mTimer;

    private final List<Fruit_Tile> mSpecialTiles = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamStripedTileCombineHandler(Engine engine) {
        super(engine);
        mTimer = new Timer(engine);
        mTimer.addTimerEvent(new TimerEvent(this, START_DELAY));
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public long getStartDelay() {
        return START_DELAY;
    }

    @Override
    public boolean checkSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        if (tileA.getSpecialType() == Fruit_SpecialType.ICE_CREAM
                && (tileB.getSpecialType() == Fruit_SpecialType.ROW_STRIPED
                || tileB.getSpecialType() == Fruit_SpecialType.COLUMN_STRIPED)) {
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }
        if (tileB.getSpecialType() == Fruit_SpecialType.ICE_CREAM
                && (tileA.getSpecialType() == Fruit_SpecialType.ROW_STRIPED
                || tileA.getSpecialType() == Fruit_SpecialType.COLUMN_STRIPED)) {
            handleSpecialCombine(tiles, tileB, tileA, row, col);
            return true;
        }

        return false;
    }

    @Override
    protected void playTileEffect(Fruit_Tile colorTile, Fruit_Tile fruitTile) {
        super.playTileEffect(colorTile, fruitTile);
        fruitTile.playTileEffect();
        colorTile.hideTile();
        fruitTile.hideTile();
        // Important to hide these tiles here, since the Algorithm hasn't been added yet
    }

    @Override
    public void onTimerEvent(long eventTime) {
        int size = mSpecialTiles.size();
        for (int i = 0; i < size; i++) {
            Fruit_Tile tile = mSpecialTiles.get(i);
            tile.popTile();
        }
        mSpecialTiles.clear();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile colorTile, Fruit_Tile fruitTile, int row, int col) {
        colorTile.setTileState(Fruit_TileState.MATCH);
        fruitTile.setTileState(Fruit_TileState.MATCH);

        // Transform and pop the same type tile
        Fruit_TileType targetType = fruitTile.getTileType();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop multiple times
                if (t.getTileType() == targetType && t.getTileState() == Fruit_TileState.IDLE) {
                    // We transform this tile to row or column special type
                    t.setSpecialType(RandomUtils.nextBoolean()
                            ? Fruit_SpecialType.ROW_STRIPED : Fruit_SpecialType.COLUMN_STRIPED);
                    mSpecialTiles.add(t);
                    // Add lightning effect from color tile to target tile
                    playLightningEffect(colorTile, t);
                }
            }
        }

        playTileEffect(colorTile, fruitTile);
        mTimer.start();
    }
    //========================================================

}
