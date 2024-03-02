package com.gamenative.fruitymatch.fruit_game.tutorial;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.effect.tutorial.Fruit_TutorialFingerEffect;
import com.gamenative.fruitymatch.fruit_game.effect.tutorial.Fruit_TutorialHintEffectSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HammerTutorial implements Fruit_Tutorial {

    private final Fruit_TutorialHintEffectSystem mHintEffect;
    private final Fruit_TutorialFingerEffect mFingerEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HammerTutorial(Engine engine) {
        mHintEffect = new Fruit_TutorialHintEffectSystem(engine);
        mFingerEffect = new Fruit_TutorialFingerEffect(engine, Fruit_Textures.TUTORIAL_FINGER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void show(GameActivity activity) {
        mHintEffect.activate(Fruit_Level.LEVEL_DATA.getTutorialHint().toCharArray());
        int marginX = (Fruit_GameWorld.WORLD_WIDTH - Fruit_Level.LEVEL_DATA.getColumn() * 300) / 2;
        int marginY = (Fruit_GameWorld.WORLD_HEIGHT - Fruit_Level.LEVEL_DATA.getRow() * 300) / 2;
        mFingerEffect.activate(marginX + 1300, marginX + 1200, marginY + 1200, marginY + 1300);

        // Click the booster button
        GameButton btnHammer = (GameButton) activity.findViewById(R.id.btn_hammer);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnHammer.performClick();
            }
        });
    }
    //========================================================

}
