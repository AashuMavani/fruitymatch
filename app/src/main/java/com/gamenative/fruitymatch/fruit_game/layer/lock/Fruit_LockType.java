package com.gamenative.fruitymatch.fruit_game.layer.lock;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public enum Fruit_LockType {
    CENTER;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Texture getTexture() {
        switch (this) {
            case CENTER:
                return Fruit_Textures.LOCK;
            default:
                throw new IllegalArgumentException("Lock Texture not found!");
        }
    }
    //========================================================

}
