package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import android.graphics.BlurMaskFilter;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Colors;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.algorithm.special.handler.Fruit_SpecialTileHandlerManager;
import com.gamenative.fruitymatch.fruit_game.effect.Fruit_ScoreEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.piece.FruitPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileInitializer;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileResetter;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeInModifier;
import com.nativegame.natyengine.entity.modifier.ScaleInModifier;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;
import com.nativegame.natyengine.entity.shape.primitive.Circle;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SolidTile extends Fruit_Tile implements EventListener {

    private static final long TIME_TO_SHUFFLE = 800;
    private static final long TIME_TO_BOUNCE = 200;
    private static final int GLITTER_NUM = 4;

    protected final Fruit_TileSystem mParent;
    private final Fruit_SpecialTileHandlerManager mSpecialTileHandler;
    private final ScaleInModifier mShuffleScaleModifier;
    private final FadeInModifier mShuffleFadeModifier;
    private final ScaleModifier mBounceInModifier;
    private final ScaleModifier mBounceOutModifier;
    private final ParticleSystem mLightBgParticleSystem;
    private final ParticleSystem mGlitterParticleSystem;
    private final FruitPieceEffectSystem mFruitPieceEffect;
    private final Fruit_ScoreEffectSystem mScoreEffect;
    private final LightCircle mLightCircle;
    private final ColorFilter mLightFilter;

    private float mSpeed;
    private boolean mIsSwappable = true;
    private boolean mIsPoppable = true;
    private boolean mIsMatchable = true;
    private boolean mIsNegligible = false;
    private boolean mIsShufflable = true;

    private final List<Fruit_TileResetter> mTileResetters = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SolidTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, Fruit_FruitType fruitType) {
        this(tileSystem, engine, texture, fruitType, Fruit_SpecialType.NONE, Fruit_TileState.IDLE);
    }

    public Fruit_SolidTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, Fruit_FruitType fruitType, Fruit_TileState tileState) {
        this(tileSystem, engine, texture, fruitType, Fruit_SpecialType.NONE, tileState);
    }

    public Fruit_SolidTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, Fruit_FruitType fruitType, Fruit_SpecialType specialType) {
        this(tileSystem, engine, texture, fruitType, specialType, Fruit_TileState.IDLE);
    }

    public Fruit_SolidTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture, Fruit_FruitType fruitType, Fruit_SpecialType specialType, Fruit_TileState tileState) {
        super(engine, texture, fruitType, specialType, tileState);
        mParent = tileSystem;
        mSpecialTileHandler = new Fruit_SpecialTileHandlerManager(engine);
        mShuffleScaleModifier = new ScaleInModifier(0, TIME_TO_SHUFFLE);
        mShuffleFadeModifier = new FadeInModifier(0, TIME_TO_SHUFFLE);
        mBounceInModifier = new ScaleModifier(1, 1, 1, 0.8f, TIME_TO_BOUNCE);
        mBounceOutModifier = new ScaleModifier(1, 1, 0.8f, 1, TIME_TO_BOUNCE, TIME_TO_BOUNCE);
        mLightBgParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG, 1)
                .setDuration(750)
                .setAlpha(255, 0, 250)
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
        mFruitPieceEffect = new FruitPieceEffectSystem(engine, 4);
        mScoreEffect = new Fruit_ScoreEffectSystem(engine, 3);
        mLightCircle = new LightCircle(engine, 250);
        mLightFilter = new PorterDuffColorFilter(Fruit_Colors.WHITE_20, PorterDuff.Mode.SRC_ATOP);
        mSpeed = 2500f / 1000;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        // Show the tile when appear from top
        if (mY >= mMarginY - getHeight() / 2f && mY < mMarginY) {
            setVisible(true);
        }

        // Show the shuffle effect
        mShuffleScaleModifier.update(this, elapsedMillis);
        mShuffleFadeModifier.update(this, elapsedMillis);

        // Show the bounce effect
        mBounceInModifier.update(this, elapsedMillis);
        mBounceOutModifier.update(this, elapsedMillis);
    }

    @Override
    public void resetXByColumn(int column) {
        mX = column * getWidth() + mMarginX;
    }

    @Override
    public void resetYByRow(int row) {
        mY = row * getWidth() + mMarginY;
    }

    @Override
    public void initTile() {
        if (mIsShufflable
                && mFruitType != Fruit_FruitType.NONE
                && mTileState == Fruit_TileState.IDLE) {
            addShuffleEffect();
        }
    }

    @Override
    public void popTile() {
        mTileState = Fruit_TileState.MATCH;
        if (isPoppable() && mSpecialType.hasPower()) {
            checkSpecialTile();
        }
    }

    @Override
    public void matchTile() {
        popTile();
        if (isPoppable()) {
            // We only check obstacle when match 3 detected
            checkObstacleTile();
        }
    }

    @Override
    public void resetTile() {
        setTileType(Fruit_TileInitializer.getRandomType());
        mSpecialType = Fruit_SpecialType.NONE;
        mTileState = Fruit_TileState.IDLE;
        setSelect(false);
        setVisible(false);
        // Update the resetter
        int size = mTileResetters.size();
        for (int i = 0; i < size; i++) {
            Fruit_TileResetter resetter = mTileResetters.get(i);
            resetter.resetTile(this);
        }
    }

    @Override
    public void hideTile() {
        // Important to set it to None, or it may detected as original type
        mFruitType = Fruit_FruitType.NONE;
        setVisible(false);
    }

    @Override
    public void shuffleTile() {
        setTileType(Fruit_TileInitializer.getShuffledRandomType(mParent, mRow, mCol));
        addShuffleEffect();
    }

    @Override
    public void playTileEffect() {
        // Check is tile has effect
        if (!mFruitType.hasEffect() || !mSpecialType.hasEffect()) {
            return;
        }
        // No effect for upgrading tile
        if (mSpecialType != Fruit_SpecialType.UPGRADE) {
            mLightBgParticleSystem.oneShot(getCenterX(), getCenterY(), 1);
            mGlitterParticleSystem.oneShot(getCenterX(), getCenterY(), GLITTER_NUM);
            mFruitPieceEffect.activate(getCenterX(), getCenterY(), mFruitType, mSpecialType);
        }
        // Play score effect and notify score counter
        mScoreEffect.activate(getCenterX(), getCenterY(), mFruitType);
        dispatchEvent(Fruit_GameEvent.PLAYER_SCORE);
    }

    @Override
    public void moveTile(long elapsedMillis) {
        float speed = mSpeed * elapsedMillis;
        // Update x position
        float targetX = getTargetX();
        if (mX < targetX) {
            mX += speed;
            if (mX > targetX) {
                mX = targetX;
            }
        }
        if (mX > targetX) {
            mX -= speed;
            if (mX < targetX) {
                mX = targetX;
            }
        }

        // Update y position
        float targetY = getTargetY();
        if (mY < targetY) {
            mY += speed;
            if (mY > targetY) {
                mY = targetY;
            }
            // Start the bounce modifier when stop falling
            if (mY == targetY) {
                setScalePivotY(getHeight());
                mBounceInModifier.init(this);
                mBounceOutModifier.init(this);
            }
        }
        if (mY > targetY) {
            mY -= speed;
            if (mY < targetY) {
                mY = targetY;
            }
        }
    }

    @Override
    public void swapTile(long elapsedMillis) {
        float speed = mSpeed * elapsedMillis;
        // Update x position
        float targetX = mCol * getWidth() + mMarginX;
        if (mX < targetX) {
            mX += speed;
            if (mX > targetX) {
                mX = targetX;
            }
        }
        if (mX > targetX) {
            mX -= speed;
            if (mX < targetX) {
                mX = targetX;
            }
        }

        // Update y position
        float targetY = mRow * getHeight() + mMarginY;
        if (mY < targetY) {
            mY += speed;
            if (mY > targetY) {
                mY = targetY;
            }
        }
        if (mY > targetY) {
            mY -= speed;
            if (mY < targetY) {
                mY = targetY;
            }
        }
    }

    @Override
    public boolean isMoving() {
        return mX != getTargetX() || mY != getTargetY();
    }

    @Override
    public boolean isSwappable() {
        return mIsSwappable && mTileState != Fruit_TileState.UNREACHABLE;
    }

    @Override
    public void setSwappable(boolean swappable) {
        mIsSwappable = swappable;
    }

    @Override
    public boolean isPoppable() {
        return mIsPoppable;
    }

    @Override
    public void setPoppable(boolean poppable) {
        mIsPoppable = poppable;
    }

    @Override
    public boolean isMatchable() {
        return mIsMatchable && mFruitType != Fruit_FruitType.NONE;
    }

    @Override
    public void setMatchable(boolean matchable) {
        mIsMatchable = matchable;
    }

    @Override
    public boolean isShufflable() {
        // Do not shuffle obstacle, special tile, and unreachable tile
        return mIsShufflable
                && mFruitType != Fruit_FruitType.NONE
                && mSpecialType == Fruit_SpecialType.NONE
                && mTileState == Fruit_TileState.IDLE;
    }

    @Override
    public void setShufflable(boolean shufflable) {
        mIsShufflable = shufflable;
    }

    @Override
    public boolean isNegligible() {
        return mIsNegligible;
    }

    @Override
    public void setNegligible(boolean negligible) {
        mIsNegligible = negligible;
    }

    @Override
    public void addResetter(Fruit_TileResetter resetter) {
        mTileResetters.add(resetter);
    }

    @Override
    public void removeResetter(Fruit_TileResetter resetter) {
        mTileResetters.remove(resetter);
    }

    @Override
    public void selectTile() {
        // We only select swappable tile
        if (!isSwappable()) {
            return;
        }
        addLightEffect();
    }

    @Override
    public void unSelectTile() {
        // We only select swappable tile
        if (!isSwappable()) {
            return;
        }
        removeLightEffect();
    }

    @Override
    public void onEvent(Event event) {
        // Accelerate tile speed if player skip bonus time
        if (event == Fruit_GameEvent.BONUS_TIME_SKIP) {
            mSpeed = 20000f / 1000;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private float getTargetX() {
        return mCol * getWidth() + mMarginX;
    }

    private float getTargetY() {
        return mRow * getHeight() + mMarginY;
    }

    private void checkSpecialTile() {
        mSpecialTileHandler.checkSpecialTile(mParent.getChild(), this, mParent.getTotalRow(), mParent.getTotalColumn());
    }

    private void checkObstacleTile() {
        // Check upper tile
        if (mRow > 0) {
            Fruit_Tile upperTile = mParent.getChildAt(mRow - 1, mCol);
            if (upperTile instanceof Fruit_ObstacleTile && upperTile.isPoppable()) {
                if (((Fruit_ObstacleTile) upperTile).isObstacle()) {
                    upperTile.popTile();
                }
            }
        }
        // Check down tile
        if (mRow < mParent.getTotalRow() - 1) {
            Fruit_Tile downTile = mParent.getChildAt(mRow + 1, mCol);
            if (downTile instanceof Fruit_ObstacleTile && downTile.isPoppable()) {
                if (((Fruit_ObstacleTile) downTile).isObstacle()) {
                    downTile.popTile();
                }
            }
        }
        // Check left tile
        if (mCol > 0) {
            Fruit_Tile leftTile = mParent.getChildAt(mRow, mCol - 1);
            if (leftTile instanceof Fruit_ObstacleTile && leftTile.isPoppable()) {
                if (((Fruit_ObstacleTile) leftTile).isObstacle()) {
                    leftTile.popTile();
                }
            }
        }
        // Check right tile
        if (mCol < mParent.getTotalColumn() - 1) {
            Fruit_Tile rightTile = mParent.getChildAt(mRow, mCol + 1);
            if (rightTile instanceof Fruit_ObstacleTile && rightTile.isPoppable()) {
                if (((Fruit_ObstacleTile) rightTile).isObstacle()) {
                    rightTile.popTile();
                }
            }
        }
    }

    private void addShuffleEffect() {
        long duration = RandomUtils.nextInt(200, 700);
        mShuffleScaleModifier.setDuration(duration);
        mShuffleFadeModifier.setDuration(duration);
        mShuffleScaleModifier.init(this);
        mShuffleFadeModifier.init(this);
        setScalePivotY(getHeight() / 2f);
    }

    private void addLightEffect() {
        // We prevent adding multiple times from multiTouch
        if (!mLightCircle.isRunning()) {
            mLightCircle.activate(getCenterX(), getCenterY());
        }
        mPaint.setColorFilter(mLightFilter);
    }

    private void removeLightEffect() {
        if (mLightCircle.isRunning()) {
            mLightCircle.removeFromGame();
        }
        mPaint.setColorFilter(null);
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    private static class LightCircle extends Circle {

        //--------------------------------------------------------
        // Constructors
        //--------------------------------------------------------
        public LightCircle(Engine engine, int radius) {
            super(engine, radius);
            mPaint.setColor(Fruit_Colors.WHITE);
            mPaint.setMaskFilter(new BlurMaskFilter(150, BlurMaskFilter.Blur.NORMAL));
            setLayer(Fruit_GameLayer.EFFECT_BG_LAYER);
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
        //========================================================

    }
    //========================================================

}
