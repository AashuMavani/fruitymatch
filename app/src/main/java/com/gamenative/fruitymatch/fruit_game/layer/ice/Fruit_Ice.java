package com.gamenative.fruitymatch.fruit_game.layer.ice;

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

public class Fruit_Ice extends Fruit_LayerSprite {

    private static final int ICE_PIECE = 20;

    private final Fruit_IceType mIceType;
    private final Fruit_ExplosionPieceEffectSystem mWhiteIcePieceEffect;
    private final Fruit_ExplosionPieceEffectSystem mBlueIcePieceEffect;

    private int mIceLayer;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Ice(Engine engine, Texture texture, Fruit_IceType iceType, int iceLayer) {
        super(engine, texture);
        mIceType = iceType;
        mIceLayer = iceLayer;
        mWhiteIcePieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.WHITE_ICE_PIECE, ICE_PIECE);
        mBlueIcePieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.BLUE_ICE_PIECE, ICE_PIECE);
        setRotation(iceType.getAngle());
        setLayer(Fruit_GameLayer.ICE_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_IceType getIceType() {
        return mIceType;
    }

    public int getIceLayer() {
        return mIceLayer;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void playIceEffect() {
        // Update ice layer
        mIceLayer--;

        if (mIceLayer == 0) {
            // Remove the ice
            removeFromGame();
            mBlueIcePieceEffect.activate(getCenterX(), getCenterY(), ICE_PIECE);
        } else {
            // Update ice texture
            setTexture(mIceType.getTexture(mIceLayer));
            mWhiteIcePieceEffect.activate(getCenterX(), getCenterY(), ICE_PIECE);
        }
        Fruit_Sounds.ICE_EXPLODE.play();
    }
    //========================================================

}
