package com.gamenative.fruitymatch.fruit_game.algorithm.special.handler;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ExplosionBeamEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ExplosionFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosiveTileHandler extends Fruit_BaseSpecialTileHandler {

    private static final int GLITTER_NUM = 8;

    private final Fruit_ExplosionFlashEffectSystem mFlashEffectSystem;
    private final Fruit_ExplosionBeamEffectSystem mBeamEffectSystem;
    private final ParticleSystem mGlitterParticleSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosiveTileHandler(Engine engine) {
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
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void handleSpecialTile(Fruit_Tile[][] tiles, Fruit_Tile tile, int row, int col) {
        int targetRow = tile.getRow();
        int targetCol = tile.getColumn();
        // Pop 3 X 3 tiles around
        for (int i = targetRow - 1; i <= targetRow + 1; i++) {
            for (int j = targetCol - 1; j <= targetCol + 1; j++) {
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

        playTileEffect(tile);
    }

    @Override
    protected void playTileEffect(Fruit_Tile tile) {
        super.playTileEffect(tile);
        mGlitterParticleSystem.oneShot(tile.getCenterX(), tile.getCenterY(), GLITTER_NUM);
        mFlashEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
        mBeamEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
        mEngine.dispatchEvent(Fruit_GameEvent.PULSE_GAME);
    }
    //========================================================

}
