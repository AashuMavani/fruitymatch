package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;
import com.gamenative.fruitymatch.fruit_item.product.Fruit_Product;
import com.gamenative.fruitymatch.fruit_item.product.Fruit_ProductManager;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ShopDialog extends Fruit_BaseDialog implements View.OnClickListener {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ShopDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_shop);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);
        setExitAnimationId(R.anim.fruit_exit_to_center);

        // Init button
        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        // Init product view
        Fruit_ProductManager productManager = new Fruit_ProductManager(mParent);
        initProduct(productManager.getAllProducts());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void initProduct(List<Fruit_Product> products) {
        ProductAdapter productAdapter = new ProductAdapter(mParent, products);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(mParent, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(productAdapter);
    }

    private void showMoreCoinDialog() {
        Fruit_MoreCoinDialog moreCoinDialog = new Fruit_MoreCoinDialog(mParent) {
            @Override
            public void updateCoin() {
                Fruit_ShopDialog.this.updateCoin();
            }
        };
        mParent.showDialog(moreCoinDialog);
    }

    private void showConfirmDialog(Fruit_Product product) {
        Fruit_ConfirmDialog confirmDialog = new Fruit_ConfirmDialog(mParent, product) {
            @Override
            public void updateCoin() {
                Fruit_ShopDialog.this.updateCoin();
            }
        };
        mParent.showDialog(confirmDialog);
    }

    public void updateCoin() {
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private final Context mContext;
        private final List<Fruit_Product> mProducts;

        //--------------------------------------------------------
        // Constructors
        //--------------------------------------------------------
        public ProductAdapter(Context context, List<Fruit_Product> products) {
            mContext = context;
            mProducts = products;
        }
        //========================================================

        //--------------------------------------------------------
        // Overriding methods
        //--------------------------------------------------------
        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_view_shop_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            // Init view holder
            Fruit_Product product = mProducts.get(position);
            holder.mImageProduct.setImageResource(product.getDrawableId());
            holder.mBtnProduct.setBackgroundResource(product.getButtonId());
            holder.mBtnProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Show confirm dialog
                    if (product.getName().equals(Fruit_Item.COIN)) {
                        showMoreCoinDialog();
                    } else {
                        showConfirmDialog(product);
                    }
                }
            });
            holder.mTxtProductDescription.setText(product.getDescription());

            // Init pop up effect
            long startDelay = 300 + position * 100L;
            holder.mImageField.popUp(200, startDelay);
            holder.mImageProduct.popUp(200, startDelay);
            holder.mBtnProduct.popUp(200, startDelay + 200);
            holder.mTxtProductDescription.popUp(200, startDelay);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }
        //========================================================

        //--------------------------------------------------------
        // Inner Classes
        //--------------------------------------------------------
        public class ProductViewHolder extends RecyclerView.ViewHolder {

            private final GameImage mImageField;
            private final GameImage mImageProduct;
            private final GameButton mBtnProduct;
            private final GameText mTxtProductDescription;

            //--------------------------------------------------------
            // Constructors
            //--------------------------------------------------------
            public ProductViewHolder(View view) {
                super(view);
                mImageField = view.findViewById(R.id.image_field);
                mImageProduct = view.findViewById(R.id.image_product);
                mBtnProduct = view.findViewById(R.id.btn_product);
                mTxtProductDescription = view.findViewById(R.id.txt_product);
            }
            //========================================================

        }
        //========================================================

    }
    //========================================================

}
