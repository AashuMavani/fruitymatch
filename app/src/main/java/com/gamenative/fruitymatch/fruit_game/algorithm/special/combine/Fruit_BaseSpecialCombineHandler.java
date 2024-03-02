package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_BaseSpecialCombineHandler implements Fruit_SpecialCombineHandler {

    protected final Engine mEngine;
    private final ParticleSystem mLightBgParticleSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_BaseSpecialCombineHandler(Engine engine) {
        mEngine = engine;
        mLightBgParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG, 1)
                .setDuration(750)
                .setAlpha(255, 0)
                .setScale(5, 5)
                .setLayer(Fruit_GameLayer.EFFECT_BG_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected void playTileEffect(Fruit_Tile tileA, Fruit_Tile tileB) {
        // Add light bg
        mLightBgParticleSystem.oneShot(tileA.getCenterX(), tileA.getCenterY(), 1);
    }
    //========================================================

}
