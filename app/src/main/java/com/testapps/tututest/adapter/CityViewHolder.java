package com.testapps.tututest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testapps.tututest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by affy on 31.08.16.
 */
public class CityViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.parent_list_item_city_title_text_view) TextView mTitle;
    @BindView(R.id.list_city_item) LinearLayout mLayoutItem;

    public CityViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
