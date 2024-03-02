package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.effect.combine.Fruit_ExplosiveStripedTileCombineEffect;
import com.gamenative.fruitymatch.fruit_game.effect.combine.Fruit_ExplosiveStripedTileCombineRingEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ColumnFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_RowFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;
import com.nativegame.natyengine.entity.timer.Timer;
import com.nativegame.natyengine.entity.timer.TimerEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosiveStripedTileCombineHandler extends Fruit_BaseSpecialCombineHandler implements TimerEvent.TimerEventListener {

    private static final long START_DELAY = 600;

    private final Fruit_RowFlashEffectSystem mRowFlashEffect;
    private final Fruit_ColumnFlashEffectSystem mColumnFlashEffect;
    private final Fruit_ExplosiveStripedTileCombineEffect mExplosiveStripedTileCombineEffect;
    private final Fruit_ExplosiveStripedTileCombineRingEffectSystem mExplosionRowColumnCombineRingEffect;
    private final ParticleSystem mRingLightParticleSystem;
    private final ParticleSystem mLightParticleSystem;
    private final Timer mTimer;

    private final List<Fruit_Tile> mTilesToRemove = new ArrayList<>();
    private final List<Fruit_Tile> mTilesToAddRowFlash = new ArrayList<>();
    private final List<Fruit_Tile> mTilesToAddColumnFlash = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosiveStripedTileCombineHandler(Engine engine) {
        super(engine);
        mRowFlashEffect = new Fruit_RowFlashEffectSystem(engine, 3);
        mColumnFlashEffect = new Fruit_ColumnFlashEffectSystem(engine, 3);
        mExplosiveStripedTileCombineEffect = new Fruit_ExplosiveStripedTileCombineEffect(engine, Fruit_Textures.CHERRY);
        mExplosionRowColumnCombineRingEffect = new Fruit_ExplosiveStripedTileCombineRingEffectSystem(engine, 2);
        mRingLightParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.FLASH_RING, 1)
                .setDuration(500)
                .setScale(0, 10)
                .setAlpha(255, 55)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mLightParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG, 1)
                .setDuration(900)
                .setAlpha(255, 0, 500)
                .setScale(4, 4)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
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
        // First tile is explosion special tile
        if ((tileB.getSpecialType() == Fruit_SpecialType.ROW_STRIPED
                || tileB.getSpecialType() == Fruit_SpecialType.COLUMN_STRIPED)
                && tileA.getSpecialType() == Fruit_SpecialType.EXPLOSIVE) {
            // We make sure the origin special tiles not being detected
            tileA.setTileState(Fruit_TileState.MATCH);
            tileB.setTileState(Fruit_TileState.MATCH);
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }

        // First tile is row or column special tile
        if ((tileA.getSpecialType() == Fruit_SpecialType.ROW_STRIPED
                || tileA.getSpecialType() == Fruit_SpecialType.COLUMN_STRIPED)
                && tileB.getSpecialType() == Fruit_SpecialType.EXPLOSIVE) {
            // We make sure the origin special tiles not being detected
            tileA.setTileState(Fruit_TileState.MATCH);
            tileB.setTileState(Fruit_TileState.MATCH);
            handleSpecialCombine(tiles, tileB, tileA, row, col);
            return true;
        }

        return false;
    }

    @Override
    protected void playTileEffect(Fruit_Tile tileA, Fruit_Tile tileB) {
        super.playTileEffect(tileA, tileB);
        mLightParticleSystem.oneShot(tileA.getCenterX(), tileA.getCenterY(), 1);
        mExplosiveStripedTileCombineEffect.activate(tileA.getCenterX(), tileA.getCenterY(), tileA.getTexture());
        mExplosionRowColumnCombineRingEffect.activate(tileA.getCenterX(), tileA.getCenterY());
        Fruit_Sounds.EXPLOSIVE_STRIPED_COMBINE.play();
    }

    @Override
    public void onTimerEvent(long eventTime) {
        int size = mTilesToRemove.size();
        for (int i = 0; i < size; i++) {
            Fruit_Tile tile = mTilesToRemove.get(i);
            tile.popTile();
        }

        int sizeRowFlash = mTilesToAddRowFlash.size();
        for (int i = 0; i < sizeRowFlash; i++) {
            Fruit_Tile tile = mTilesToAddRowFlash.get(i);
            mRowFlashEffect.activate(tile.getCenterX(), tile.getCenterY());
        }

        int sizeColumnFlash = mTilesToAddColumnFlash.size();
        for (int i = 0; i < sizeColumnFlash; i++) {
            Fruit_Tile tile = mTilesToAddColumnFlash.get(i);
            mColumnFlashEffect.activate(tile.getCenterX(), tile.getCenterY());
        }

        // Add ring effect at intersection
        mTilesToAddRowFlash.retainAll(mTilesToAddColumnFlash);
        Fruit_Tile tile = mTilesToAddRowFlash.get(0);
        mRingLightParticleSystem.oneShot(tile.getCenterX(), tile.getCenterY(), 1);

        mTilesToRemove.clear();
        mTilesToAddRowFlash.clear();
        mTilesToAddColumnFlash.clear();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        int targetRow = tileA.getRow();
        int targetCol = tileA.getColumn();

        // Pop 3 row tile
        for (int i = targetRow - 1; i <= targetRow + 1; i++) {
            // We make sure the index not out of bound
            if (i < 0 || i > row - 1) {
                continue;
            }
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop multiple times
                if (t.getTileState() == Fruit_TileState.IDLE) {
                    mTilesToRemove.add(t);
                }
            }
        }

        // Pop 3 column tile
        for (int j = targetCol - 1; j <= targetCol + 1; j++) {
            // We make sure the index not out of bound
            if (j < 0 || j > col - 1) {
                continue;
            }
            for (int i = 0; i < row; i++) {
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop multiple times
                if (t.getTileState() == Fruit_TileState.IDLE) {
                    mTilesToRemove.add(t);
                }
            }
        }

        // Add 3 row flash
        for (int i = -1; i <= 1; i++) {
            // We make sure the flash not out of bound
            int flashRow = targetRow + i;
            if (flashRow < 0 || flashRow > row - 1) {
                continue;
            }
            mTilesToAddRowFlash.add(tiles[flashRow][targetCol]);
        }
        // Add 3 column flash
        for (int i = -1; i <= 1; i++) {
            // We make sure the flash not out of bound
            int flashCol = targetCol + i;
            if (flashCol < 0 || flashCol > col - 1) {
                continue;
            }
            mTilesToAddColumnFlash.add(tiles[targetRow][flashCol]);
        }

        playTileEffect(tileA, tileB);
        mTimer.start();
    }
    //========================================================

}
