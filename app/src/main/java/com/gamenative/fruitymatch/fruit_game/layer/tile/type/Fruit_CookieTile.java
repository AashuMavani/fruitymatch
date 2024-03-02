package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_CookiePiece;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_CookiePieceEffect;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;
import com.nativegame.natyengine.texture.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CookieTile extends Fruit_ObstacleTile {

    private static final int GLITTER_NUM = 4;
    private static final int COOKIE_PIECE = 6;

    private final ParticleSystem mExplosionParticleSystem;
    private final ParticleSystem mGlitterParticleSystem;
    private final List<Fruit_CookiePieceEffect> mCookiePieceEffects = new ArrayList<>(COOKIE_PIECE);

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_CookieTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture);
        mExplosionParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG, 1)
                .setDuration(750)
                .setAlpha(255, 0)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mGlitterParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.GLITTER, GLITTER_NUM)
                .setDuration(600)
                .setSpeedX(-300, 300)
                .setSpeedY(-300, 300)
                .setInitialRotation(0, 360)
                .setRotationSpeed(-360, 360)
                .setAlpha(255, 0, 400)
                .setScale(1, 0, 400)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        // Init cookie pieces
        for (int i = 0; i < COOKIE_PIECE; i++) {
            Fruit_CookiePiece cookiePiece = Fruit_CookiePiece.values()[i];
            mCookiePieceEffects.add(new Fruit_CookiePieceEffect(engine, cookiePiece.getTexture(), cookiePiece));
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void playTileEffect() {
        if (mIsObstacle) {
            playCookieEffect();
            mIsObstacle = false;
            return;
        }
        super.playTileEffect();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void playCookieEffect() {
        mExplosionParticleSystem.oneShot(getCenterX(), getCenterY(), 1);
        mGlitterParticleSystem.oneShot(getCenterX(), getCenterY(), GLITTER_NUM);
        for (int i = 0; i < COOKIE_PIECE; i++) {
            mCookiePieceEffects.get(i).activate(getCenterX(), getCenterY());
        }
        mCookiePieceEffects.clear();
        Fruit_Sounds.COOKIE_EXPLODE.play();
    }
    //========================================================

}
