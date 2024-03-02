package com.gamenative.fruitymatch.fruit_game.layer.lock;

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

public class Fruit_Lock extends Fruit_LayerSprite {

    private static final int LOCK_PIECE = 4;

    private final Fruit_LockType mLockType;

    private final Fruit_ExplosionPieceEffectSystem mLockPieceEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Lock(Engine engine, Texture texture, Fruit_LockType lockType) {
        super(engine, texture);
        mLockType = lockType;
        mLockPieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.LOCK_PIECE, LOCK_PIECE);
        setLayer(Fruit_GameLayer.LOCK_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_LockType getLockType() {
        return mLockType;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void playLockEffect() {
        mLockPieceEffect.activate(getCenterX(), getCenterY(), LOCK_PIECE);
        Fruit_Sounds.LOCK_EXPLODE.play();
        // Remove the lock
        removeFromGame();
    }
    //========================================================

}
