package com.gamenative.fruitymatch.fruit_item.product;

import android.app.Activity;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;
import com.nativegame.natyengine.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ProductManager {

    private final List<Fruit_Product> mProducts = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ProductManager(Activity activity) {
        // Init all the product
        Fruit_Product productWatchAd = new Fruit_Product(Fruit_Item.COIN, 0, R.drawable.fruit_ui_coin_50, R.drawable.fruit_ui_btn_watch_ad);
        Fruit_Product productGlove = new Fruit_Product(Fruit_Item.GLOVE, 50, R.drawable.fruit_ui_booster_glove, R.drawable.fruit_ui_btn_price_50);
        Fruit_Product productBomb = new Fruit_Product(Fruit_Item.BOMB, 60, R.drawable.fruit_ui_booster_bomb, R.drawable.fruit_ui_btn_price_60);
        Fruit_Product productHammer = new Fruit_Product(Fruit_Item.HAMMER, 70, R.drawable.fruit_ui_booster_hammer, R.drawable.fruit_ui_btn_price_70);

        // Init product description
        productWatchAd.setDescription(ResourceUtils.getString(activity, R.string.txt_coin));
        productGlove.setDescription(ResourceUtils.getString(activity, R.string.txt_glove));
        productBomb.setDescription(ResourceUtils.getString(activity, R.string.txt_bomb));
        productHammer.setDescription(ResourceUtils.getString(activity, R.string.txt_hammer));

        // Add product to list
        mProducts.add(productWatchAd);
        mProducts.add(productGlove);
        mProducts.add(productBomb);
        mProducts.add(productHammer);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public List<Fruit_Product> getAllProducts() {
        return mProducts;
    }
    //========================================================

}
