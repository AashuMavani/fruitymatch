package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.Fruit_SmokeEffect;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_ExplosionPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_PieTile extends Fruit_LayerObstacleTile {

    private static final int LAYER_PIECE = 5;
    private static final int PIE_PIECE = 20;
    private static final int PAN_PIECE = 10;

    private final Pie mPie;
    private final Fruit_ExplosionPieceEffectSystem mWhitePiePieceEffect;
    private final Fruit_ExplosionPieceEffectSystem mYellowPiePieceEffect;
    private final Fruit_ExplosionPieceEffectSystem mPanPieceEffect;
    private final Fruit_SmokeEffect mSmokeEffect;

    private final List<Fruit_DummyTile> mDummyTiles = new ArrayList<>(3);

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_PieTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, int obstacleLayer) {
        super(tileSystem, engine, texture, obstacleLayer);
        mPie = new Pie(engine, getPieTexture(obstacleLayer));
        mWhitePiePieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.WHITE_PIE_PIECE, PIE_PIECE);
        mYellowPiePieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.YELLOW_PIE_PIECE, PIE_PIECE);
        mPanPieceEffect = new Fruit_ExplosionPieceEffectSystem(engine, Fruit_Textures.PIE_PAN_PIECE, PAN_PIECE);
        mSmokeEffect = new Fruit_SmokeEffect(engine, Fruit_Textures.SMOKE_ANIMATION);
        mSmokeEffect.setScale(1.5f);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onStart() {
        mPie.activate(mX, mY);
        initDummyTile();
    }

    @Override
    public boolean isSwappable() {
        if (mIsObstacle) {
            return false;
        }
        return super.isSwappable();
    }

    @Override
    protected void onUpdateLayer(int obstacleLayer) {
        // Check the current layer
        if (obstacleLayer == 0) {
            // Break the last layer
            mPie.removeFromGame();
            removeDummyTile();
            playPieEffect();
        } else {
            // Update next layer texture
            switch (obstacleLayer) {
                case 1:
                    mPie.setTexture(Fruit_Textures.PIE_01);
                    break;
                case 2:
                    mPie.setTexture(Fruit_Textures.PIE_02);
                    break;
                case 3:
                    mPie.setTexture(Fruit_Textures.PIE_03);
                    break;
            }
            mPie.addBounceEffect();
            playLayerEffect();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private Texture getPieTexture(int obstacleLayer) {
        switch (obstacleLayer) {
            case 1:
                return Fruit_Textures.PIE_01;
            case 2:
                return Fruit_Textures.PIE_02;
            case 3:
                return Fruit_Textures.PIE_03;
            case 4:
                return Fruit_Textures.PIE_04;
            default:
                throw new IllegalArgumentException("Pie texture not found!");
        }
    }

    private void initDummyTile() {
        // Add the DummyTile as the remaining part of pie
        Fruit_DummyTile dummyA = (Fruit_DummyTile) mParent.getChildAt(mRow - 1, mCol);
        Fruit_DummyTile dummyB = (Fruit_DummyTile) mParent.getChildAt(mRow, mCol - 1);
        Fruit_DummyTile dummyC = (Fruit_DummyTile) mParent.getChildAt(mRow - 1, mCol - 1);
        dummyA.setTargetTile(this);
        dummyB.setTargetTile(this);
        dummyC.setTargetTile(this);
        mDummyTiles.add(dummyA);
        mDummyTiles.add(dummyB);
        mDummyTiles.add(dummyC);
    }

    private void removeDummyTile() {
        // We notify all the DummyTiles
        int size = mDummyTiles.size();
        for (int i = 0; i < size; i++) {
            Fruit_DummyTile dummy = mDummyTiles.get(i);
            dummy.popDummyTile();
        }
    }

    private void playPieEffect() {
        mSmokeEffect.activate(mX, mY);
        mPanPieceEffect.activate(mX, mY, PAN_PIECE);
        mWhitePiePieceEffect.activate(mX, mY, PIE_PIECE);
        mYellowPiePieceEffect.activate(mX, mY, PIE_PIECE);
        Fruit_Sounds.PIE_EXPLODE.play();
        Fruit_Sounds.PIE_PAN_EXPLODE.play();
    }

    private void playLayerEffect() {
        mSmokeEffect.activate(mX, mY);
        mWhitePiePieceEffect.activate(mX, mY, LAYER_PIECE);
        mYellowPiePieceEffect.activate(mX, mY, LAYER_PIECE);
        Fruit_Sounds.PIE_EXPLODE.play();
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    private static class Pie extends Sprite {

        private static final long TIME_TO_BOUNCE_OUT = 200;
        private static final long TIME_TO_BOUNCE_IN = 300;

        private final ScaleModifier mBounceOutModifier;
        private final ScaleModifier mBounceInModifier;

        //--------------------------------------------------------
        // Constructors
        //--------------------------------------------------------
        public Pie(Engine engine, Texture texture) {
            super(engine, texture);
            mBounceOutModifier = new ScaleModifier(1, 1.2f, TIME_TO_BOUNCE_OUT);
            mBounceInModifier = new ScaleModifier(1.2f, 1, TIME_TO_BOUNCE_IN, TIME_TO_BOUNCE_OUT,
                    OvershootTweener.getInstance());
            mBounceInModifier.setModifyBefore(false);
            setLayer(Fruit_GameLayer.TILE_LAYER);
        }
        //========================================================

        //--------------------------------------------------------
        // Overriding methods
        //--------------------------------------------------------
        @Override
        public void onUpdate(long elapsedMillis) {
            mBounceOutModifier.update(this, elapsedMillis);
            mBounceInModifier.update(this, elapsedMillis);
        }
        //========================================================

        //--------------------------------------------------------
        // Methods
        //--------------------------------------------------------
        public void activate(float x, float y) {
            setCenterX(x);
            setCenterY(y);
            addToGame();
        }

        public void addBounceEffect() {
            mBounceOutModifier.init(this);
            mBounceInModifier.init(this);
        }
        //========================================================

    }
    //========================================================

}
