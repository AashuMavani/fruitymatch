package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_ExplosionPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CandyTile extends Fruit_LayerObstacleTile {

    private static final int CANDY_PIECE = 10;
    private static final int WRAPPER_PIECE = 8;

    private final ParticleSystem mRingLightParticleSystem;
    private final Fruit_ExplosionPieceEffectSystem mCandyPieceEffect;
    private final Fruit_ExplosionPieceEffectSystem mWrapperPieceEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_CandyTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, int obstacleLayer) {
        super(tileSystem, engine, texture, obstacleLayer);
        mRingLightParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_RING, 1)
                .setDuration(500)
                .setAlpha(255, 0, 350)
                .setScale(0, 3)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mCandyPieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.CANDY_PIECE, CANDY_PIECE);
        mWrapperPieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.CANDY_WRAPPER_PIECE, WRAPPER_PIECE);
    }

    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onUpdateLayer(int obstacleLayer) {
        switch (obstacleLayer) {
            case 0:
                playCandyEffect();
                break;
            case 1:
                setTexture(Fruit_Textures.CANDY_01);
                playLayerEffect();
                break;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void playCandyEffect() {
        mRingLightParticleSystem.oneShot(getCenterX(), getCenterY(), 1);
        mCandyPieceEffect.activate(getCenterX(), getCenterY(), CANDY_PIECE);
        Fruit_Sounds.CANDY_EXPLODE.play();
    }

    private void playLayerEffect() {
        mWrapperPieceEffect.activate(getCenterX(), getCenterY(), WRAPPER_PIECE);
        Fruit_Sounds.CANDY_WRAPPER_EXPLODE.play();
    }
    //========================================================

}
