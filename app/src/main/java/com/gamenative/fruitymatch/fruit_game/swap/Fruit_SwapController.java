package com.gamenative.fruitymatch.fruit_game.swap;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_Match3Algorithm;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.algorithm.special.combine.Fruit_SpecialCombineHandler;
import com.gamenative.fruitymatch.fruit_game.algorithm.special.combine.Fruit_SpecialCombineHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;
import com.nativegame.natyengine.entity.timer.Timer;
import com.nativegame.natyengine.entity.timer.TimerEvent;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.input.touch.TouchEvent;
import com.nativegame.natyengine.input.touch.TouchEventListener;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SwapController extends Entity implements TouchEventListener,
        EventListener, Fruit_SwapModifier.SwapListener, TimerEvent.TimerEventListener {

    private final Fruit_Tile[][] mTiles;
    private final int mTotalRow;
    private final int mTotalCol;
    private final int mMarginX;
    private final int mMarginY;
    private final Fruit_SwapModifier mSwapModifier;
    private final Fruit_SwapModifier mSwapBackModifier;
    private final Fruit_SpecialCombineHandlerManager mSpecialCombineHandler;
    private final Timer mTimer;

    private Fruit_Tile mTouchDownTile;
    private Fruit_Tile mTouchUpTile;
    private boolean mEnable = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SwapController(Engine engine, Fruit_TileSystem tileSystem) {
        super(engine);
        mTiles = tileSystem.getChild();
        mTotalRow = tileSystem.getTotalRow();
        mTotalCol = tileSystem.getTotalColumn();
        mMarginX = (Fruit_GameWorld.WORLD_WIDTH - mTotalCol * 300) / 2;
        mMarginY = (Fruit_GameWorld.WORLD_HEIGHT - mTotalRow * 300) / 2;
        mSwapModifier = new Fruit_SwapModifier(engine);
        mSwapModifier.setListener(this);
        mSwapBackModifier = new Fruit_SwapModifier(engine);
        mSpecialCombineHandler = new Fruit_SpecialCombineHandlerManager(engine);
        mTimer = new Timer(engine);
        mTimer.addTimerEvent(new TimerEvent(this));
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onTouchEvent(int type, float touchX, float touchY) {
        if (!mEnable) {
            return;
        }
        switch (type) {
            case TouchEvent.TOUCH_DOWN:
                // Check is out of bound
                if (touchX < mMarginX
                        || touchY < mMarginY
                        || touchX > Fruit_GameWorld.WORLD_WIDTH - mMarginX
                        || touchY > Fruit_GameWorld.WORLD_HEIGHT - mMarginY) {
                    return;
                }
                int touchDownCol = (int) ((touchX - mMarginX) / 300);
                int touchDownRow = (int) ((touchY - mMarginY) / 300);
                mTouchDownTile = mTiles[touchDownRow][touchDownCol];
                mTouchDownTile.selectTile();
                break;
            case TouchEvent.TOUCH_UP:
                if (mTouchDownTile == null) {
                    return;
                }
                mTouchDownTile.unSelectTile();
                int row = mTouchDownTile.getRow();
                int col = mTouchDownTile.getColumn();
                if (touchX < mTouchDownTile.getX()) {
                    // Swap left tile
                    if (col > 0) {
                        mTouchUpTile = mTiles[row][col - 1];
                    }
                } else if (touchX > mTouchDownTile.getEndX()) {
                    // Swap right tile
                    if (col < mTotalCol - 1) {
                        mTouchUpTile = mTiles[row][col + 1];
                    }
                } else if (touchY < mTouchDownTile.getY()) {
                    // Swap up tile
                    if (row > 0) {
                        mTouchUpTile = mTiles[row - 1][col];
                    }
                } else if (touchY > mTouchDownTile.getEndY()) {
                    // Swap down tile
                    if (row < mTotalRow - 1) {
                        mTouchUpTile = mTiles[row + 1][col];
                    }
                }
                // Check is tile swappable
                if (mTouchUpTile != null && mTouchUpTile.isSwappable() && mTouchDownTile.isSwappable()) {
                    Fruit_Match3Algorithm.swapTile(mTiles, mTouchDownTile, mTouchUpTile);
                    mSwapModifier.activate(mTouchDownTile, mTouchUpTile);
                    mEnable = false;
                }
                // Reset touch tile
                mTouchDownTile = null;
                mTouchUpTile = null;
                break;
        }
    }

    @Override
    public void onSwap(Fruit_Tile tileA, Fruit_Tile tileB) {
        // Check is special combine detected
        if (checkSpecialCombine(tileA, tileB)) {
            mTimer.start();
        } else {
            // Otherwise, check is player match any tile
            Fruit_Match3Algorithm.findMatchTile(mTiles, mTotalRow, mTotalCol);
            if (Fruit_Match3Algorithm.isMatch(mTiles, mTotalRow, mTotalCol)) {
                // Start the Algorithm if found
                tileA.setSelect(true);
                tileB.setSelect(true);
                dispatchEvent(Fruit_GameEvent.PLAYER_SWAP);
            } else {
                // Swap back if not found
                Fruit_Match3Algorithm.swapTile(mTiles, tileA, tileB);
                mSwapBackModifier.activate(tileA, tileB);
                mEnable = true;
            }
        }
    }

    @Override
    public void onEvent(Event event) {
        switch ((Fruit_GameEvent) event) {
            case START_GAME:
            case STOP_COMBO:
            case REMOVE_BOOSTER:
            case ADD_EXTRA_MOVES:
                mEnable = true;
                break;
            case ADD_BOOSTER:
                mEnable = false;
                break;
            case GAME_WIN:
            case GAME_OVER:
                removeFromGame();
                break;
        }
    }

    @Override
    public void onTimerEvent(long eventTime) {
        dispatchEvent(Fruit_GameEvent.PLAYER_SWAP);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private boolean checkSpecialCombine(Fruit_Tile tileA, Fruit_Tile tileB) {
        Fruit_SpecialCombineHandler handler = mSpecialCombineHandler.checkSpecialCombine(mTiles, tileA, tileB, mTotalRow, mTotalCol);
        if (handler != null) {
            mTimer.getAllTimerEvents().get(0).setEventTime(handler.getStartDelay());
            return true;
        }

        return false;
    }
    //========================================================

}