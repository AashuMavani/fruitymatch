package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_database.Fruit_DatabaseHelper;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;
import com.gamenative.fruitymatch.fruit_item.product.Fruit_Product;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ConfirmDialog extends Fruit_BaseDialog implements View.OnClickListener {

    private final Fruit_Product mProduct;

    private int mSelectedId;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ConfirmDialog(GameActivity activity, Fruit_Product product) {
        super(activity);
        mProduct = product;
        setContentView(R.layout.fruit_dialog_confirm);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);
        setExitAnimationId(R.anim.fruit_exit_to_center);

        // Init product image
        GameImage imageProduct = (GameImage) findViewById(R.id.image_product);
        imageProduct.popUp(200, 300);
        imageProduct.setImageResource(mProduct.getDrawableId());

        // Init text
        GameText txtConfirm = (GameText) findViewById(R.id.txt_confirm);
        txtConfirm.popUp(200, 500);

        // Init button
        GameButton btnConfirm = (GameButton) findViewById(R.id.btn_confirm);
        btnConfirm.popUp(200, 700);
        btnConfirm.setOnClickListener(this);

        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_watch_ad) {
            showMoreCoinDialog();
        } else if (mSelectedId == R.id.btn_confirm) {
            showNewBoosterDialog();
            updateCoin();
        }
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismiss();
        } else if (id == R.id.btn_confirm) {
            mSelectedId = id;
            buyProduct();
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void buyProduct() {
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mParent);
        // Check is player has enough saving coin
        int price = mProduct.getPrice();
        int saving = databaseHelper.getItemCount(Fruit_Item.COIN);
        if (saving < price) {
            // Show the ad dialog if saving not enough
            mSelectedId = R.id.btn_watch_ad;
            return;
        }

        // Update coin from db
        databaseHelper.updateItemCount(Fruit_Item.COIN, saving - price);

        // Update item from db
        int num = databaseHelper.getItemCount(mProduct.getName());
        databaseHelper.updateItemCount(mProduct.getName(), num + 1);
    }

    private void showMoreCoinDialog() {
        Fruit_MoreCoinDialog moreCoinDialog = new Fruit_MoreCoinDialog(mParent);
        mParent.showDialog(moreCoinDialog);
    }

    private void showNewBoosterDialog() {
        Fruit_NewBoosterDialog newBoosterDialog = new Fruit_NewBoosterDialog(mParent, mProduct);
        mParent.showDialog(newBoosterDialog);
    }

    public void updateCoin() {
    }
    //========================================================

}
