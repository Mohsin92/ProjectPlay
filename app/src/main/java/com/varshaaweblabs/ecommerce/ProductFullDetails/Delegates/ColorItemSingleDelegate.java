package com.varshaaweblabs.ecommerce.ProductFullDetails.Delegates;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Attribute_Group;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dinesh on 3/10/17.
 */

public class ColorItemSingleDelegate extends AdapterDelegate<ArrayList<Attribute_Group>> {


    private LayoutInflater inflater;
    private SingleItemHelper mItemListener;
    private Context context;
    String id;

    public ColorItemSingleDelegate(Context mContext, SingleItemHelper mListener) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemListener = mListener;
        context=mContext;
    }


    @Override
    protected void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }


    @Override
    protected boolean isForViewType(@NonNull ArrayList<Attribute_Group> items, int position) {

//        return items.get(position) != null;
        return items.get(position) instanceof Attribute_Group;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ColorItemViewHolder(inflater.inflate(R.layout.prod_att_list_color, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ArrayList<Attribute_Group> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        configureSelectionItemCardViewHolder((ColorItemViewHolder) holder, items.get(position));
    }


    private void configureSelectionItemCardViewHolder(ColorItemViewHolder holder, Object mValue) {

        if (mValue instanceof Attribute_Group) {
            holder.bind(((Attribute_Group) mValue));
        }
    }


    class ColorItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.color_item)
        AppCompatImageView mColorItem;
        private Attribute_Group mItem;
        @BindView(R.id.main_card)
        CardView mMainCard;
        final View mView;

        ColorItemViewHolder(View view) {
            super(view);

            mView = view;
            ButterKnife.bind(this, view);
            mView.setOnClickListener(this);

        }

        void bind(Attribute_Group mItem) {
            this.mItem = mItem;

            if(mItem.getIs_visible()) {

                mMainCard.setCardBackgroundColor(Color.parseColor(mItem.getHtmlColorCode()));


                if (this.mItem.getSelected()) {
                    mColorItem.setImageResource(R.drawable.ic_done_white);
                    id = this.mItem.getIdAttributes().toString();
                    mItemListener.defaultItem(id);
                } else {
                    mColorItem.setImageDrawable(null);
                }
            }

        }

        @Override
        public void onClick(View view) {
            if (mItemListener != null) {
                mItemListener.selectedItem(this.mItem, getAdapterPosition());

            }
        }
    }

    public interface SingleItemHelper {

        void selectedItem(Attribute_Group mColorItem, int Pos);
        void defaultItem(String id);

//        boolean isSelected(int position);

    }
}
