package com.gamenative.fruitymatch.fruit_game.layer.honey;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_HoneyPiece;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_HoneyPieceEffect;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.ScaleInModifier;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Honey extends Fruit_LayerSprite {

    private static final int HONEY_PIECE = 5;
    private static final long TIME_TO_SCALE_IN = 300;

    private final Fruit_HoneyType mHoneyType;
    private final ScaleInModifier mScaleInModifier;

    private final List<Fruit_HoneyPieceEffect> mHoneyPieceEffects = new ArrayList<>(HONEY_PIECE);

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Honey(Engine engine, Texture texture, Fruit_HoneyType honeyType) {
        super(engine, texture);
        mHoneyType = honeyType;
        mScaleInModifier = new ScaleInModifier(TIME_TO_SCALE_IN, OvershootTweener.getInstance());
        // Init honey pieces
        for (int i = 0; i < HONEY_PIECE; i++) {
            mHoneyPieceEffects.add(new Fruit_HoneyPieceEffect(engine, Fruit_Textures.HONEY_PIECE, Fruit_HoneyPiece.values()[i]));
        }
        setRotation(honeyType.getAngle());
        setLayer(Fruit_GameLayer.HONEY_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_HoneyType getHoneyType() {
        return mHoneyType;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mScaleInModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void playHoneyEffect() {
        // Play explosion effect
        for (int i = 0; i < HONEY_PIECE; i++) {
            mHoneyPieceEffects.get(i).activate(getCenterX(), getCenterY());
        }
        mHoneyPieceEffects.clear();
        Fruit_Sounds.HONEY_EXPLODE.play();

        // Play show effect and add to game
        mScaleInModifier.init(this);
        addToGame();
    }
    //========================================================

}
