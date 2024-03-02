package com.gamenative.fruitymatch.fruit_game.layer.shell;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.modifier.FadeOutModifier;
import com.nativegame.natyengine.entity.modifier.PositionYModifier;
import com.nativegame.natyengine.entity.modifier.ScaleModifier;
import com.nativegame.natyengine.entity.sprite.Sprite;
import com.nativegame.natyengine.texture.Texture;
import com.nativegame.natyengine.util.modifier.tween.OvershootTweener;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Shell extends Fruit_LayerSprite {

    private final Fruit_ShellType mShellType;
    private final ShellEffect mShellEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Shell(Engine engine, Texture texture, Fruit_ShellType sandType) {
        super(engine, texture);
        mShellType = sandType;
        mShellEffect = new ShellEffect(engine, sandType.getTexture());
        setLayer(Fruit_GameLayer.SAND_LAYER - 1);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_ShellType getShellType() {
        return mShellType;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onStart() {
        mShellEffect.activate(mX, mY);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void playShellEffect() {
        mShellEffect.showRemoveEffect();
        removeFromGame();
        Fruit_Sounds.COLLECT_SHELL.play();
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    private static class ShellEffect extends Sprite {

        private final ScaleModifier mBounceOutModifier;
        private final ScaleModifier mBounceInModifier;
        private final FadeOutModifier mFadeOutModifier;
        private final PositionYModifier mPositionModifier;

        //--------------------------------------------------------
        // Constructors
        //--------------------------------------------------------
        public ShellEffect(Engine engine, Texture texture) {
            super(engine, texture);
            mBounceOutModifier = new ScaleModifier(1, 1.3f, 250);
            mBounceInModifier = new ScaleModifier(1.3f, 1, 250, 250,
                    OvershootTweener.getInstance());
            mBounceInModifier.setModifyBefore(false);
            mFadeOutModifier = new FadeOutModifier(500, 500);
            mPositionModifier = new PositionYModifier(500, 500);
            mPositionModifier.setAutoRemove(true);
            setLayer(Fruit_GameLayer.SAND_LAYER - 1);
        }
        //========================================================

        //--------------------------------------------------------
        // Overriding methods
        //--------------------------------------------------------
        @Override
        public void onUpdate(long elapsedMillis) {
            mBounceOutModifier.update(this, elapsedMillis);
            mBounceInModifier.update(this, elapsedMillis);
            mFadeOutModifier.update(this, elapsedMillis);
            mPositionModifier.update(this, elapsedMillis);
        }
        //========================================================

        //--------------------------------------------------------
        // Methods
        //--------------------------------------------------------
        public void activate(float x, float y) {
            mX = x;
            mY = y;
            addToGame();
        }

        public void showRemoveEffect() {
            mBounceOutModifier.init(this);
            mBounceInModifier.init(this);
            mFadeOutModifier.init(this);
            mPositionModifier.setValue(mY, mY - 800);
            mPositionModifier.init(this);
            setLayer(Fruit_GameLayer.TEXT_LAYER + 1);
        }
        //========================================================

    }
    //========================================================

}
