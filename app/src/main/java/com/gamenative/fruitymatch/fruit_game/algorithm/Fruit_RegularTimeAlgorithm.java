package com.gamenative.fruitymatch.fruit_game.algorithm;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_Match3Algorithm;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.algorithm.layer.Fruit_LayerHandlerManager;
import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_RegularTimeAlgorithm extends Fruit_BaseAlgorithm {

    private static final int TIME_TO_PAUSE = 300;

    private final Fruit_LayerHandlerManager mLayerHandlerManager;
    private final Fruit_TargetHandlerManager mTargetHandlerManager;

    private AlgorithmState mState;
    private long mTotalTime;

    private enum AlgorithmState {
        CHECK_MATCH,
        MOVE_TILE,
        PAUSE_TILE
    }

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_RegularTimeAlgorithm(Engine engine, Fruit_TileSystem tileSystem, Fruit_LayerHandlerManager layerHandlerManager,
                                      Fruit_TargetHandlerManager targetHandlerManager) {
        super(engine, tileSystem);
        mLayerHandlerManager = layerHandlerManager;
        mTargetHandlerManager = targetHandlerManager;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        switch (mState) {
            case CHECK_MATCH:
                checkMatch();
                break;
            case MOVE_TILE:
                moveTile(elapsedMillis);
                break;
            case PAUSE_TILE:
                // We pause the tiles for a while before moving
                mTotalTime += elapsedMillis;
                if (mTotalTime >= TIME_TO_PAUSE) {
                    mState = AlgorithmState.MOVE_TILE;
                    mTotalTime = 0;
                }
                break;
        }
    }

    @Override
    public void initAlgorithm() {
        mLayerHandlerManager.initLayers(mTiles, mTotalRow, mTotalCol);
        Fruit_Match3Algorithm.initTile(mTiles, mTotalRow, mTotalCol);
    }

    @Override
    public void startAlgorithm() {
        mState = AlgorithmState.CHECK_MATCH;
        addToGame();
        mTotalTime = 0;
    }

    @Override
    public void removeAlgorithm() {
        mLayerHandlerManager.removeLayers(mTiles, mTotalRow, mTotalCol);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void checkMatch() {
        Fruit_Match3Algorithm.findMatchTile(mTiles, mTotalRow, mTotalCol);
        mTargetHandlerManager.checkTargets(mTiles, mTotalRow, mTotalCol);
        mLayerHandlerManager.updateLayers(mTargetHandlerManager, mTiles, mTotalRow, mTotalCol);
        if (mTargetHandlerManager.isTargetChange()) {
            dispatchEvent(Fruit_GameEvent.PLAYER_COLLECT);
        }
        // Check is any matches found
        if (!Fruit_Match3Algorithm.isMatch(mTiles, mTotalRow, mTotalCol)) {
            // Dispatch next event if no more matches
            if (mTargetHandlerManager.isTargetComplete()) {
                // Player collect all the target
                dispatchEvent(Fruit_GameEvent.GAME_WIN);
            } else if (Fruit_Level.LEVEL_DATA.getMove() == 0) {
                // Player run out of moves
                dispatchEvent(Fruit_GameEvent.PLAYER_OUT_OF_MOVE);
            } else {
                // Continue the game
                dispatchEvent(Fruit_GameEvent.STOP_COMBO);
            }
            removeFromGame();
        } else {
            // Add one combo
            dispatchEvent(Fruit_GameEvent.ADD_COMBO);
            // Run the algorithm if found
            mSpecialTileFinder.findSpecialTile(mTiles, mTotalRow, mTotalCol);
            Fruit_Match3Algorithm.playTileEffect(mTiles, mTotalRow, mTotalCol);
            Fruit_Match3Algorithm.checkUnreachableTile(mTiles, mTotalRow, mTotalCol);
            Fruit_Match3Algorithm.resetMatchTile(mTiles, mTotalRow, mTotalCol);
            mState = AlgorithmState.PAUSE_TILE;
        }
    }

    private void moveTile(long elapsedMillis) {
        Fruit_Match3Algorithm.moveTile(mTiles, mTotalRow, mTotalCol, elapsedMillis);
        // Update waiting tile state when moving
        if (Fruit_Match3Algorithm.isWaiting(mTiles, mTotalRow, mTotalCol)) {
            Fruit_Match3Algorithm.findUnreachableTile(mTiles, mTotalRow, mTotalCol);
            Fruit_Match3Algorithm.checkWaitingTile(mTiles, mTotalRow, mTotalCol);
            Fruit_Match3Algorithm.resetMatchTile(mTiles, mTotalRow, mTotalCol);
            // Important to not check isMoving(), so the tile will move continuously
        }
        // Check match if tiles stop moving
        if (!Fruit_Match3Algorithm.isMoving(mTiles, mTotalRow, mTotalCol)) {
            Fruit_Sounds.TILE_BOUNCE.play();
            mState = AlgorithmState.CHECK_MATCH;
        }
    }
    //========================================================

}
