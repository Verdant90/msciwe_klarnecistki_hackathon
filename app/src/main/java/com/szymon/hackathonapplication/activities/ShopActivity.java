package com.szymon.hackathonapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.models.ShopItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivity extends Activity {

    @BindView(R.id.list_shop_items)
    ListView shopItemsListView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        setUpShopItemsAdapter();
    }

    private void setUpShopItemsAdapter() {
        // TODO TCI extract to DAO
        final List<ShopItem> shopItems = new ArrayList<>();
        shopItems.add(ShopItem.builder().title("title1").description("description1").cost(10L).build());
        shopItems.add(ShopItem.builder().title("title2").description("description1").cost(10L).build());
        shopItems.add(ShopItem.builder().title("title3").description("description1").cost(10L).build());
        shopItems.add(ShopItem.builder().title("title4").description("description1").cost(10L).build());
        shopItems.add(ShopItem.builder().title("title5").description("description1").cost(10L).build());

        final int textViewResourceId = 0;
        shopItemsListView.setAdapter(new ShopItemAdapter(this, textViewResourceId, shopItems));
    }

    public class ShopItemAdapter extends ArrayAdapter<ShopItem> {

        private final List<ShopItem> shopItems;
        private final LayoutInflater inflater;

        ShopItemAdapter(final Activity activity,
                        final int textViewResourceId,
                        final List<ShopItem> shopItems) {

            super(activity, textViewResourceId, shopItems);

            this.shopItems = shopItems;
            this.inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public  class ViewHolder {
            public TextView title;
            public TextView description;
            public TextView cost;
            public ImageView icon;
            public Button purchaseButton;

        }

        @NonNull
        public View getView(final int position,
                            final View convertView,
                            @NonNull final ViewGroup parent) {

            View view = convertView;
            ViewHolder holder;

            if (convertView == null) {
                final ViewGroup root = null;
                view = inflater.inflate(R.layout.item_shop, root);
                holder = viewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            fillViewHolder(holder, shopItems.get(position));

            return view;
        }

        @NonNull
        private ViewHolder viewHolder(final View view) {
            final ViewHolder holder = new ViewHolder();

            holder.title =  view.findViewById(R.id.text_shop_item_title);
            holder.description = view.findViewById(R.id.text_shop_item_description);
            holder.cost = view.findViewById(R.id.text_shop_item_cost);
            holder.icon =  view.findViewById(R.id.image_shop_item_icon);
            holder.purchaseButton = view.findViewById(R.id.button_purchase_shop_item);

            return holder;
        }

        private void fillViewHolder(ViewHolder holder, ShopItem shopItem) {
            holder.title.setText(shopItem.getTitle());
            holder.description.setText(shopItem.getDescription());
            holder.cost.setText(shopItem.getCost().toString());
            holder.purchaseButton.setOnClickListener(onPurchaseButtonClickedListener());
        }

        @NonNull
        private View.OnClickListener onPurchaseButtonClickedListener() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO context should be taken from HackatonApplication
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }

}
