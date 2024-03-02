package com.gamenative.fruitymatch.fruit_game;

import com.gamenative.fruitymatch.Fruit_MainActivity;
import com.gamenative.fruitymatch.fruit_game.algorithm.Fruit_Algorithm;
import com.gamenative.fruitymatch.fruit_game.algorithm.Fruit_BonusTimeAlgorithm;
import com.gamenative.fruitymatch.fruit_game.algorithm.Fruit_RegularTimeAlgorithm;
import com.gamenative.fruitymatch.fruit_game.algorithm.layer.Fruit_LayerHandlerManager;
import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.counter.Fruit_BoosterCounter;
import com.gamenative.fruitymatch.fruit_game.counter.Fruit_ComboCounter;
import com.gamenative.fruitymatch.fruit_game.counter.Fruit_MoveCounter;
import com.gamenative.fruitymatch.fruit_game.counter.Fruit_ScoreCounter;
import com.gamenative.fruitymatch.fruit_game.counter.Fruit_StarCounter;
import com.gamenative.fruitymatch.fruit_game.counter.Fruit_TargetCounter;
import com.gamenative.fruitymatch.fruit_game.hint.Fruit_HintController;
import com.gamenative.fruitymatch.fruit_game.layer.grid.Fruit_GridSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_game.swap.Fruit_SwapController;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_PauseDialog;
import com.nativegame.natyengine.Game;
import com.nativegame.natyengine.camera.FixedCamera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.input.touch.SingleTouchController;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameView;
import com.nativegame.natyengine.util.debug.EntityCounter;
import com.nativegame.natyengine.util.debug.FPSCounter;
import com.nativegame.natyengine.util.debug.UPSCounter;

/**
 * Created by Oscar Liang on 2022/02/23
 */


public class Fruit_JuicyMatch extends Game {

    private static final boolean DEBUG_MODE = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_JuicyMatch(GameActivity activity, GameView gameView, Engine engine) {
        super(activity, gameView, engine);
        engine.setDebugMode(DEBUG_MODE);
        engine.setTouchController(new SingleTouchController(gameView));
        engine.setCamera(new FixedCamera(gameView.getWidth(), gameView.getHeight(), Fruit_GameWorld.WORLD_WIDTH, Fruit_GameWorld.WORLD_HEIGHT));

        // Align the Camera to world center
        engine.getCamera().setCenterX(Fruit_GameWorld.WORLD_WIDTH / 2f);
        engine.getCamera().setCenterY(Fruit_GameWorld.WORLD_HEIGHT / 2f);

        // Init debug tools
        if (engine.isDebugMode()) {
            EntityCounter entityCounter = new EntityCounter(engine, 0, 200, 1000, 100);
            UPSCounter upsCounter = new UPSCounter(engine, 0, 0, 1000, 100);
            FPSCounter fpsCounter = new FPSCounter(engine, 0, 100, 1000, 100);
            int textSize = 100;
            int debugLayer = 12;
            entityCounter.setTextSize(textSize);
            upsCounter.setTextSize(textSize);
            fpsCounter.setTextSize(textSize);
            entityCounter.setLayer(debugLayer);
            upsCounter.setLayer(debugLayer);
            fpsCounter.setLayer(debugLayer);
            entityCounter.addToGame();
            upsCounter.addToGame();
            fpsCounter.addToGame();
        }

        // Init counter
        new Fruit_ComboCounter(engine).addToGame();
        new Fruit_ScoreCounter(activity, engine).addToGame();
        new Fruit_StarCounter(activity, engine).addToGame();
        new Fruit_MoveCounter(activity, engine).addToGame();
        new Fruit_TargetCounter(activity, engine).addToGame();

        // Init layer
        new Fruit_GridSystem(engine);
        Fruit_TileSystem tileSystem = new Fruit_TileSystem(engine);

        // Init Algorithm
        Fruit_Algorithm regularTimeAlgorithm = new Fruit_RegularTimeAlgorithm(engine, tileSystem,
                new Fruit_LayerHandlerManager(engine), new Fruit_TargetHandlerManager());
        Fruit_Algorithm bonusTimeAlgorithm = new Fruit_BonusTimeAlgorithm(engine, tileSystem);

        // Init controller
        new Fruit_SwapController(engine, tileSystem).addToGame();
        new Fruit_HintController(engine, tileSystem).addToGame();
        new Fruit_BoosterCounter(activity, engine, tileSystem).addToGame();
        new Fruit_GameController(activity, engine, regularTimeAlgorithm, bonusTimeAlgorithm).addToGame();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onPause() {
        showPauseDialog();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void showPauseDialog() {
        Fruit_PauseDialog dialog = new Fruit_PauseDialog(getActivity()) {
            @Override
            public void resumeGame() {
                resume();
            }

            @Override
            public void quitGame() {
                // Reduce one live
                ((Fruit_MainActivity) getActivity()).getLivesTimer().reduceLive();
                getActivity().navigateBack();
            }
        };
        getActivity().showDialog(dialog);
    }
    //========================================================

}
