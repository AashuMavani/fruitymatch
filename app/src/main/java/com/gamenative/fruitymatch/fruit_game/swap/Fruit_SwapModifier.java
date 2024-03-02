package com.gamenative.fruitymatch.fruit_game.swap;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SwapModifier extends Entity {

    private SwapListener mListener;
    private Fruit_Tile mTileA;
    private Fruit_Tile mTileB;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SwapModifier(Engine engine) {
        super(engine);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public SwapListener getListener() {
        return mListener;
    }

    public void setListener(SwapListener listener) {
        mListener = listener;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mTileA.swapTile(elapsedMillis);
        mTileB.swapTile(elapsedMillis);
        // Remove after swap finished
        if (!mTileA.isMoving() && !mTileB.isMoving()) {
            if (mListener != null) {
                mListener.onSwap(mTileA, mTileB);
            }
            removeFromGame();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(Fruit_Tile tileA, Fruit_Tile tileB) {
        mTileA = tileA;
        mTileB = tileB;
        Fruit_Sounds.TILE_SWAP.play();
        addToGame();
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public interface SwapListener {

        void onSwap(Fruit_Tile tileA, Fruit_Tile tileB);

    }
    //========================================================

}
