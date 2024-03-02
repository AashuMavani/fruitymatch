package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ExplosionBeamEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ExplosionFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_DoubleExplosiveTileCombineHandler extends Fruit_BaseSpecialCombineHandler {

    private static final int GLITTER_NUM = 12;

    private final Fruit_ExplosionFlashEffectSystem mFlashEffectSystem;
    private final Fruit_ExplosionBeamEffectSystem mBeamEffectSystem;
    private final ParticleSystem mGlitterParticleSystem;
    private final ParticleSystem mRingLightParticleSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_DoubleExplosiveTileCombineHandler(Engine engine) {
        super(engine);
        mFlashEffectSystem = new Fruit_ExplosionFlashEffectSystem(engine, 1);
        mBeamEffectSystem = new Fruit_ExplosionBeamEffectSystem(engine, 8);
        mGlitterParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.GLITTER, GLITTER_NUM)
                .setDuration(600)
                .setSpeedWithAngle(1500, 2500)
                .setInitialRotation(0, 360)
                .setRotationSpeed(-360, 360)
                .setAlpha(255, 0, 200)
                .setScale(1.2f, 0.5f, 200)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mRingLightParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.FLASH_RING, 1)
                .setDuration(500)
                .setScale(0, 10)
                .setAlpha(255, 55)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
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
        // Check are both tiles explosion special tile
        if (tileA.getSpecialType() == Fruit_SpecialType.EXPLOSIVE
                && tileB.getSpecialType() == Fruit_SpecialType.EXPLOSIVE) {
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
        mGlitterParticleSystem.oneShot(tileA.getCenterX(), tileA.getCenterY(), GLITTER_NUM);
        mFlashEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
        mBeamEffectSystem.activate(tileA.getCenterX(), tileA.getCenterY());
        mRingLightParticleSystem.oneShot(tileA.getCenterX(), tileA.getCenterY(), 1);
        mEngine.dispatchEvent(Fruit_GameEvent.PULSE_GAME);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        int targetRow = tileA.getRow();
        int targetCol = tileA.getColumn();

        // Pop 5 X 5 tiles around
        for (int i = targetRow - 2; i <= targetRow + 2; i++) {
            for (int j = targetCol - 2; j <= targetCol + 2; j++) {
                // We make sure the index not out of bound
                if (i < 0 || i > row - 1 || j < 0 || j > col - 1) {
                    continue;
                }
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop the tile multiple time
                if (t.getTileState() == Fruit_TileState.IDLE) {
                    t.popTile();
                }
            }
        }

        playTileEffect(tileA, tileB);
    }
    //========================================================

}
