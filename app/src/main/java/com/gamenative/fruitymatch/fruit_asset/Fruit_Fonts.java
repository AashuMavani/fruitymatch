package com.gamenative.fruitymatch.fruit_asset;

import android.content.Context;
import android.graphics.Typeface;

import com.gamenative.fruitymatch.R;
import com.nativegame.natyengine.util.ResourceUtils;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Fonts {

    public static Typeface BALOO;

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static void load(Context context) {
        BALOO = ResourceUtils.getFont(context, R.font.fruit_baloo);
    }
    //========================================================

}
