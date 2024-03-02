package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.Fruit_SmokeEffect;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_ExplosionPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CakeTile extends Fruit_LayerObstacleTile {

    private static final int CAKE_PIECE = 4;

    private final Fruit_ExplosionPieceEffectSystem mCakePieceEffect;
    private final Fruit_SmokeEffect mSmokeEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_CakeTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, int obstacleLayer) {
        super(tileSystem, engine, texture, obstacleLayer);
        mCakePieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.CAKE_PIECE, CAKE_PIECE);
        mSmokeEffect = new Fruit_SmokeEffect(engine, Fruit_Textures.SMOKE_ANIMATION);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isSwappable() {
        if (mIsObstacle) {
            return false;
        }
        return super.isSwappable();
    }

    @Override
    protected void onUpdateLayer(int obstacleLayer) {
        // Update next layer texture
        switch (obstacleLayer) {
            case 0:
                playCakeEffect();
                break;
            case 1:
                setTexture(Fruit_Textures.CAKE_01);
                playLayerEffect();
                break;
            case 2:
                setTexture(Fruit_Textures.CAKE_02);
                playLayerEffect();
                break;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void playCakeEffect() {
        mCakePieceEffect.activate(getCenterX(), getCenterY(), CAKE_PIECE);
        Fruit_Sounds.CAKE_EXPLODE.play();
    }

    private void playLayerEffect() {
        mSmokeEffect.activate(getCenterX(), getCenterY());
    }
    //========================================================

}
