package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_BaseSpecialTileFinder implements Fruit_SpecialTileFinder {

    protected static final int MAX_FIND_NUM = 3;

    private final ParticleSystem mLightBgParticleSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_BaseSpecialTileFinder(Engine engine) {
        mLightBgParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG, MAX_FIND_NUM)
                .setDuration(750)
                .setAlpha(255, 0)
                .setScale(3, 3)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void setUpgradeTiles(Fruit_Tile[][] tiles, int row, int col);

    protected void playUpgradeEffect(Fruit_Tile tile) {
        // Add light bg
        mLightBgParticleSystem.oneShot(tile.getCenterX(), tile.getCenterY(), 1);
    }
    //========================================================

}
