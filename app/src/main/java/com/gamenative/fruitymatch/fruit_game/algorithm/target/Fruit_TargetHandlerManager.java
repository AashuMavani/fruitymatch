package com.gamenative.fruitymatch.fruit_game.algorithm.target;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TargetHandlerManager {

    private final List<Fruit_TargetType> mTargetTypes;
    private final List<Integer> mTargetNums;

    private boolean mIsTargetChanged = false;

    private final List<Fruit_TargetHandler> mTargetHandlers = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_TargetHandlerManager() {
        mTargetTypes = Fruit_Level.LEVEL_DATA.getTargetTypes();
        mTargetNums = Fruit_Level.LEVEL_DATA.getTargetCounts();
        // Init TargetHandler from TargetType
        int size = mTargetTypes.size();
        for (int i = 0; i < size; i++) {
            Fruit_TargetType type = mTargetTypes.get(i);
            mTargetHandlers.add(getTargetHandle(type));
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void checkTargets(Fruit_Tile[][] tiles, int row, int col) {
        mIsTargetChanged = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // Check is tile match
                Fruit_Tile t = tiles[i][j];
                if (t.getTileState() != Fruit_TileState.MATCH) {
                    continue;
                }
                // Check is tile contain target
                int size = mTargetHandlers.size();
                for (int n = 0; n < size; n++) {
                    Fruit_TargetHandler handler = mTargetHandlers.get(n);
                    if (handler != null && handler.checkTarget(t)) {
                        // Update target count if found
                        updateTarget(mTargetTypes.get(n));
                    }
                }
            }
        }
    }

    public void updateTarget(Fruit_TargetType type) {
        // Update LevelData
        int index = mTargetTypes.indexOf(type);
        int num = mTargetNums.get(index);
        if (num > 0) {
            mTargetNums.set(index, num - 1);
            mIsTargetChanged = true;
        }
    }

    public boolean isTargetChange() {
        return mIsTargetChanged;
    }

    public boolean isTargetComplete() {
        // Check is all the target num equal 0
        int size = mTargetNums.size();
        for (int i = 0; i < size; i++) {
            if (mTargetNums.get(i) > 0) {
                return false;
            }
        }

        return true;
    }

    private Fruit_TargetHandler getTargetHandle(Fruit_TargetType type) {
        switch (type) {
            case STRAWBERRY:
                return new Fruit_StrawberryTargetHandler();
            case CHERRY:
                return new Fruit_CherryTargetHandler();
            case LEMON:
                return new Fruit_LemonTargetHandler();
            case COOKIE:
                return new Fruit_CookieTargetHandler();
            case CAKE:
                return new Fruit_CakeTargetHandler();
            case PIE:
                return new Fruit_PieTargetHandler();
            case CANDY:
                return new Fruit_CandyTargetHandler();
        }

        return null;
    }
    //========================================================

}
