package com.gamenative.fruitymatch.fruit_level;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_BombTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_CakeTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_CandyTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_CombineTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_CookieTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_GeneratorTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_GloveTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_HammerTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_HoneyTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_IceTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_LockTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_Match3Tutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_Match4Tutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_Match5Tutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_MatchLTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_MatchTTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_PieTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_PipeTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_ShellTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_StarfishTutorial;
import com.gamenative.fruitymatch.fruit_game.tutorial.Fruit_Tutorial;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public enum Fruit_TutorialType {
    NONE,
    MATCH_3,
    MATCH_4,
    MATCH_T,
    MATCH_L,
    MATCH_5,
    COMBINE,
    LOCK,
    COOKIE,
    CAKE,
    CANDY,
    PIE,
    ICE,
    HONEY,
    STARFISH,
    SHELL,
    PIPE,
    GENERATOR,
    HAMMER,
    BOMB,
    GLOVE;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public int getDrawableId() {
        switch (this) {
            case MATCH_3:
                return R.drawable.fruit_ui_tutorial_match_3;
            case MATCH_4:
                return R.drawable.fruit_ui_tutorial_match_4;
            case MATCH_T:
                return R.drawable.fruit_ui_tutorial_match_t;
            case MATCH_L:
                return R.drawable.fruit_ui_tutorial_match_l;
            case MATCH_5:
                return R.drawable.fruit_ui_tutorial_match_5;
            case COMBINE:
                return R.drawable.fruit_ui_tutorial_combine;
            case LOCK:
                return R.drawable.fruit_ui_tutorial_lock;
            case COOKIE:
                return R.drawable.fruit_ui_tutorial_cookie;
            case CAKE:
                return R.drawable.fruit_ui_tutorial_cake;
            case CANDY:
                return R.drawable.fruit_ui_tutorial_candy;
            case PIE:
                return R.drawable.fruit_ui_tutorial_pie;
            case ICE:
                return R.drawable.fruit_ui_tutorial_ice;
            case HONEY:
                return R.drawable.fruit_ui_tutorial_honey;
            case STARFISH:
                return R.drawable.fruit_ui_tutorial_starfish;
            case SHELL:
                return R.drawable.fruit_ui_tutorial_shell;
            case PIPE:
                return R.drawable.fruit_ui_tutorial_pipe;
            case GENERATOR:
                return R.drawable.fruit_ui_tutorial_generator;
            case HAMMER:
                return R.drawable.fruit_ui_tutorial_hammer;
            case BOMB:
                return R.drawable.fruit_ui_tutorial_bomb;
            case GLOVE:
                return R.drawable.fruit_ui_tutorial_glove;
            default:
                throw new IllegalArgumentException("TutorialType drawable not found!");
        }
    }

    public int getStringId() {
        switch (this) {
            case MATCH_3:
                return R.string.txt_tutorial_match_3;
            case MATCH_4:
                return R.string.txt_tutorial_match_4;
            case MATCH_T:
                return R.string.txt_tutorial_match_t;
            case MATCH_L:
                return R.string.txt_tutorial_match_l;
            case MATCH_5:
                return R.string.txt_tutorial_match_5;
            case COMBINE:
                return R.string.txt_tutorial_combine;
            case LOCK:
                return R.string.txt_tutorial_lock;
            case COOKIE:
                return R.string.txt_tutorial_cookie;
            case CAKE:
                return R.string.txt_tutorial_cake;
            case CANDY:
                return R.string.txt_tutorial_candy;
            case PIE:
                return R.string.txt_tutorial_pie;
            case ICE:
                return R.string.txt_tutorial_ice;
            case HONEY:
                return R.string.txt_tutorial_honey;
            case STARFISH:
                return R.string.txt_tutorial_starfish;
            case SHELL:
                return R.string.txt_tutorial_shell;
            case PIPE:
                return R.string.txt_tutorial_pipe;
            case GENERATOR:
                return R.string.txt_tutorial_generator;
            case HAMMER:
                return R.string.txt_tutorial_hammer;
            case BOMB:
                return R.string.txt_tutorial_bomb;
            case GLOVE:
                return R.string.txt_tutorial_glove;
            default:
                throw new IllegalArgumentException("TutorialType String not found!");
        }
    }

    public Fruit_Tutorial getTutorial(Engine engine) {
        switch (this) {
            case MATCH_3:
                return new Fruit_Match3Tutorial(engine);
            case MATCH_4:
                return new Fruit_Match4Tutorial(engine);
            case MATCH_T:
                return new Fruit_MatchTTutorial(engine);
            case MATCH_L:
                return new Fruit_MatchLTutorial(engine);
            case MATCH_5:
                return new Fruit_Match5Tutorial(engine);
            case COMBINE:
                return new Fruit_CombineTutorial(engine);
            case LOCK:
                return new Fruit_LockTutorial(engine);
            case COOKIE:
                return new Fruit_CookieTutorial(engine);
            case CAKE:
                return new Fruit_CakeTutorial(engine);
            case CANDY:
                return new Fruit_CandyTutorial(engine);
            case PIE:
                return new Fruit_PieTutorial(engine);
            case ICE:
                return new Fruit_IceTutorial(engine);
            case HONEY:
                return new Fruit_HoneyTutorial(engine);
            case STARFISH:
                return new Fruit_StarfishTutorial(engine);
            case SHELL:
                return new Fruit_ShellTutorial(engine);
            case PIPE:
                return new Fruit_PipeTutorial(engine);
            case GENERATOR:
                return new Fruit_GeneratorTutorial(engine);
            case HAMMER:
                return new Fruit_HammerTutorial(engine);
            case BOMB:
                return new Fruit_BombTutorial(engine);
            case GLOVE:
                return new Fruit_GloveTutorial(engine);
            default:
                throw new IllegalArgumentException("Tutorial not found!");
        }
    }
    //========================================================

}
