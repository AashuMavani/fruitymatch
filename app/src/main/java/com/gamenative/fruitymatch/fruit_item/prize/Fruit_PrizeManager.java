package com.gamenative.fruitymatch.fruit_item.prize;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_PrizeManager {

    private final Map<String, Fruit_Prize> mPrizeMap = new HashMap<>();

    public static final String PRIZE_COIN_50 = "prize_coin_50";
    public static final String PRIZE_COIN_150 = "prize_coin_150";
    public static final String PRIZE_GLOVE = "prize_glove";
    public static final String PRIZE_BOMB = "prize_bomb";
    public static final String PRIZE_HAMMER = "prize_hammer";

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_PrizeManager() {
        // Init all the prize
        Fruit_Prize prizeCoin50 = new Fruit_Prize(Fruit_Item.COIN, 50, R.drawable.fruit_ui_coin_50);
        Fruit_Prize prizeCoin150 = new Fruit_Prize(Fruit_Item.COIN, 150, R.drawable.fruit_ui_coin_150);
        Fruit_Prize prizeGlove = new Fruit_Prize(Fruit_Item.GLOVE, 1, R.drawable.fruit_ui_booster_glove);
        Fruit_Prize prizeBomb = new Fruit_Prize(Fruit_Item.BOMB, 1, R.drawable.fruit_ui_booster_bomb);
        Fruit_Prize prizeHammer = new Fruit_Prize(Fruit_Item.HAMMER, 1, R.drawable.fruit_ui_booster_hammer);

        // Add prize to map
        mPrizeMap.put(PRIZE_COIN_50, prizeCoin50);
        mPrizeMap.put(PRIZE_COIN_150, prizeCoin150);
        mPrizeMap.put(PRIZE_GLOVE, prizeGlove);
        mPrizeMap.put(PRIZE_BOMB, prizeBomb);
        mPrizeMap.put(PRIZE_HAMMER, prizeHammer);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_Prize getPrize(String key) {
        return mPrizeMap.get(key);
    }
    //========================================================

}
