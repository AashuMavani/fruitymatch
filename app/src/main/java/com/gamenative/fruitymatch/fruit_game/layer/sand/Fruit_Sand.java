package com.gamenative.fruitymatch.fruit_game.layer.sand;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_ExplosionPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Sand extends Fruit_LayerSprite {

    private static final int SAND_PIECES = 30;

    private final Fruit_SandType mSandType;
    private final Fruit_ExplosionPieceEffectSystem mYellowSandPieceEffect;
    private final Fruit_ExplosionPieceEffectSystem mBrownSandPieceEffect;

    private int mSandLayer;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Sand(Engine engine, Texture texture, Fruit_SandType sandType, int sandLayer) {
        super(engine, texture);
        mSandType = sandType;
        mSandLayer = sandLayer;
        mYellowSandPieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.YELLOW_SAND_PIECE, SAND_PIECES);
        mBrownSandPieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.BROWN_SAND_PIECE, SAND_PIECES);
        setRotation(sandType.getAngle());
        setLayer(Fruit_GameLayer.SAND_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_SandType getSandType() {
        return mSandType;
    }

    public int getSandLayer() {
        return mSandLayer;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void playSandEffect() {
        // Update sand layer
        mSandLayer--;

        if (mSandLayer == 0) {
            // Remove the sand
            removeFromGame();
            mYellowSandPieceEffect.activate(getCenterX(), getCenterY(), SAND_PIECES);
        } else {
            // Update sand texture
            setTexture(mSandType.getTexture(mSandLayer));
            mBrownSandPieceEffect.activate(getCenterX(), getCenterY(), SAND_PIECES);
        }
        Fruit_Sounds.SAND_EXPLODE.play();
    }
    //========================================================

}
