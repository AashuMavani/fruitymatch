package com.gamenative.fruitymatch.fruit_item.prize;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Prize {

    private final String mName;
    private final int mCount;
    private final int mDrawableId;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Prize(String name, int count, int drawableId) {
        mName = name;
        mCount = count;
        mDrawableId = drawableId;
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public String getName() {
        return mName;
    }

    public int getCount() {
        return mCount;
    }

    public int getDrawableId() {
        return mDrawableId;
    }
    //========================================================

}
