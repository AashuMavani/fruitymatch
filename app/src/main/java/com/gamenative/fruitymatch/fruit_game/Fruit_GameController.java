package com.gamenative.fruitymatch.fruit_game;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gamenative.fruitymatch.Fruit_MainActivity;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_ad.Fruit_AdManager;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_game.algorithm.Fruit_Algorithm;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_Tutorial;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.gamenative.fruitymatch.fruit_level.Fruit_TutorialType;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_ErrorDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_LossDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_MoreMoveDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_ScoreDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_StartDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_TutorialDialog;
import com.gamenative.fruitymatch.fruit_ui.fruit_dialog.Fruit_WinDialog;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.ui.GameActivity;

/**
 * Created by Oscar Liang on 2022/02/23
 */


public class Fruit_GameController extends Entity implements EventListener, Fruit_AdManager.AdRewardListener {

    private final GameActivity mParent;
    private final Fruit_Algorithm mRegularTimeAlgorithm;
    private final Fruit_Algorithm mBonusTimeAlgorithm;

    private Fruit_GameState mState;
    private long mTotalTime;
    private boolean mExtraLives = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_GameController(GameActivity activity, Engine engine,
                                Fruit_Algorithm regularTimeAlgorithm, Fruit_Algorithm bonusTimeAlgorithm) {
        super(engine);
        mParent = activity;
        mRegularTimeAlgorithm = regularTimeAlgorithm;
        mBonusTimeAlgorithm = bonusTimeAlgorithm;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onStart() {
        showGameView();
        mRegularTimeAlgorithm.initAlgorithm();
        mState = Fruit_GameState.SHIFT_TILE;
        mTotalTime = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        switch (mState) {
            case WAITING:
                // We wait for the game event
                break;
            case SHIFT_TILE:
                mTotalTime += elapsedMillis;
                if (mTotalTime >= 1800) {
                    showStartDialog();
                    Fruit_Sounds.GAME_START.play();
                    mState = Fruit_GameState.SHOW_START_DIALOG;
                    mTotalTime = 0;
                }
                break;
            case SHOW_TUTORIAL:
                Fruit_Tutorial tutorial = Fruit_Level.LEVEL_DATA.getTutorialType().getTutorial(mEngine);
                tutorial.show(mParent);
                mState = Fruit_GameState.WAITING;
                break;
            case SHOW_START_DIALOG:
                mTotalTime += elapsedMillis;
                if (mTotalTime >= 2500) {
                    // Check is current level has tutorial
                    if (Fruit_Level.LEVEL_DATA.getTutorialType() != Fruit_TutorialType.NONE) {
                        showTutorialDialog();
                    }
                    dispatchEvent(Fruit_GameEvent.START_GAME);
                    mState = Fruit_GameState.WAITING;
                    mTotalTime = 0;
                }
                break;
            case SHOW_WIN_DIALOG:
                mTotalTime += elapsedMillis;
                if (mTotalTime >= 2500) {
                    mBonusTimeAlgorithm.startAlgorithm();
                    mState = Fruit_GameState.WAITING;
                    mTotalTime = 0;
                }
                break;
            case SHOW_LOSS_DIALOG:
                mTotalTime += elapsedMillis;
                if (mTotalTime >= 2500) {
                    removeGameView();
                    mState = Fruit_GameState.NAVIGATE_BACK;
                    mTotalTime = 0;
                }
                break;
            case SHOW_SCORE_DIALOG:
                mTotalTime += elapsedMillis;
                if (mTotalTime >= 1200) {
                    mEngine.stop();
                    mEngine.release();
                    showScoreDialog();
                    mState = Fruit_GameState.WAITING;
                    mTotalTime = 0;
                }
                break;
            case NAVIGATE_BACK:
                mTotalTime += elapsedMillis;
                if (mTotalTime >= 1200) {
                    mEngine.stop();
                    mEngine.release();
                    mParent.navigateBack();
                    mState = Fruit_GameState.WAITING;
                    mTotalTime = 0;
                }
                break;
        }
    }

    @Override
    public void onEvent(Event event) {
        switch ((Fruit_GameEvent) event) {
            case PLAYER_SWAP:
            case PLAYER_USE_BOOSTER:
                mRegularTimeAlgorithm.startAlgorithm();
                break;
            case PULSE_GAME:
                pulseGameView();
                break;
            case SHAKE_GAME:
                shakeGameView();
                break;
            case GAME_WIN:
                mRegularTimeAlgorithm.removeAlgorithm();
                showWinDialog();
                Fruit_Sounds.GAME_WIN.play();
                mState = Fruit_GameState.SHOW_WIN_DIALOG;
                break;
            case GAME_OVER:
                showLossDialog();
                Fruit_Sounds.GAME_OVER.play();
                mState = Fruit_GameState.SHOW_LOSS_DIALOG;
                break;
            case PLAYER_OUT_OF_MOVE:
                // Check player has extra lives
                if (mExtraLives) {
                    showMoreMoveDialog();
                    mExtraLives = false;
                } else {
                    dispatchEvent(Fruit_GameEvent.GAME_OVER);
                }
                break;
            case BONUS_TIME_END:
                removeGameView();
                mState = Fruit_GameState.SHOW_SCORE_DIALOG;
                break;
        }
    }

    @Override
    public void onEarnReward() {
        // The ad will pause the game, so we resume it
        mEngine.resume();
        dispatchEvent(Fruit_GameEvent.ADD_EXTRA_MOVES);
    }

    @Override
    public void onLossReward() {
        // The ad will pause the game, so we resume it
        mEngine.resume();
        dispatchEvent(Fruit_GameEvent.GAME_OVER);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void showGameView() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_game_view_show);
                mParent.findViewById(R.id.game_view).startAnimation(animation);
            }
        });
    }

    private void removeGameView() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_game_view_remove);
                mParent.findViewById(R.id.game_view).startAnimation(animation);
                mParent.findViewById(R.id.view_game_state).setVisibility(View.GONE);
                mParent.findViewById(R.id.view_game_booster).setVisibility(View.GONE);
            }
        });
    }

    private void pulseGameView() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_game_view_pulse);
                mParent.findViewById(R.id.game_view).startAnimation(animation);
            }
        });
    }

    private void shakeGameView() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_game_view_shake);
                mParent.findViewById(R.id.game_view).startAnimation(animation);
            }
        });
    }

    private void showStartDialog() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fruit_StartDialog startDialog = new Fruit_StartDialog(mParent);
                mParent.showDialog(startDialog);
            }
        });
    }

    private void showTutorialDialog() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fruit_TutorialDialog tutorialDialog = new Fruit_TutorialDialog(mParent) {
                    @Override
                    public void showTutorial() {
                        mState = Fruit_GameState.SHOW_TUTORIAL;
                    }
                };
                mParent.showDialog(tutorialDialog);
            }
        });
    }

    private void showWinDialog() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fruit_WinDialog winDialog = new Fruit_WinDialog(mParent);
                mParent.showDialog(winDialog);
            }
        });
    }

    private void showLossDialog() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fruit_LossDialog lossDialog = new Fruit_LossDialog(mParent);
                mParent.showDialog(lossDialog);
            }
        });
    }

    private void showScoreDialog() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fruit_ScoreDialog scoreDialog = new Fruit_ScoreDialog(mParent);
                mParent.showDialog(scoreDialog);
            }
        });
    }

    private void showMoreMoveDialog() {
        mParent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fruit_MoreMoveDialog moreMoveDialog = new Fruit_MoreMoveDialog(mParent) {
                    @Override
                    public void showAd() {
                        showRewardedAd();
                    }

                    @Override
                    public void quit() {
                        dispatchEvent(Fruit_GameEvent.GAME_OVER);
                    }
                };
                mParent.showDialog(moreMoveDialog);
            }
        });
    }

    private void showRewardedAd() {
        // Show rewarded ad
        Fruit_AdManager adManager = ((Fruit_MainActivity) mParent).getAdManager();
        adManager.setListener(this);
        boolean isConnect = adManager.showRewardAd();
        // Check connection
        if (isConnect) {
            // Pause the game when loading ad, or the pause dialog will show
            mEngine.pause();
        } else {
            // Show error dialog if no internet connect
            Fruit_ErrorDialog dialog = new Fruit_ErrorDialog(mParent) {
                @Override
                public void retry() {
                    adManager.requestAd();
                    showRewardedAd();
                }

                @Override
                public void quit() {
                    dispatchEvent(Fruit_GameEvent.GAME_OVER);
                }
            };
            mParent.showDialog(dialog);
        }
    }
    //========================================================

}
