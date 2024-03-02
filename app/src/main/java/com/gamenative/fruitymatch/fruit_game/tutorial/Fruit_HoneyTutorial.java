package com.gamenative.fruitymatch.fruit_game.tutorial;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Colors;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.effect.tutorial.Fruit_TutorialFingerEffect;
import com.gamenative.fruitymatch.fruit_game.effect.tutorial.Fruit_TutorialHintEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.tutorial.Fruit_TutorialShadowEffect;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.ui.GameActivity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HoneyTutorial implements Fruit_Tutorial {

    private final Fruit_TutorialShadowEffect mShadowBg;
    private final Fruit_TutorialHintEffectSystem mHintEffect;
    private final Fruit_TutorialFingerEffect mFingerEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HoneyTutorial(Engine engine) {
        mShadowBg = new Fruit_TutorialShadowEffect(engine, Fruit_Colors.BLACK_80);
        mHintEffect = new Fruit_TutorialHintEffectSystem(engine);
        mFingerEffect = new Fruit_TutorialFingerEffect(engine, Fruit_Textures.TUTORIAL_FINGER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void show(GameActivity activity) {
        mShadowBg.addToGame();
        mHintEffect.activate(Fruit_Level.LEVEL_DATA.getTutorialHint().toCharArray());
        int marginX = (Fruit_GameWorld.WORLD_WIDTH - Fruit_Level.LEVEL_DATA.getColumn() * 300) / 2;
        int marginY = (Fruit_GameWorld.WORLD_HEIGHT - Fruit_Level.LEVEL_DATA.getRow() * 300) / 2;
        mFingerEffect.activate(marginX + 600, marginX + 900, marginY + 700, marginY + 700);
    }
    //========================================================

}
