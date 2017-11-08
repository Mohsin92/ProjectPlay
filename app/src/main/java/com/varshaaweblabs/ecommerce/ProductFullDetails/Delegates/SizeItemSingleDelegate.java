package com.varshaaweblabs.ecommerce.ProductFullDetails.Delegates;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Adpter.ProductSize_Adapter;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Attribute_Group;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dinesh on 3/10/17.
 */

public class SizeItemSingleDelegate extends AdapterDelegate<ArrayList<Attribute_Group>> {


    private LayoutInflater inflater;
    private SizeItemSingleDelegate.SingleItemHelper mItemListener;
    private Context context;
    String id;

    public SizeItemSingleDelegate(Context mContext, ProductSize_Adapter mListener) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemListener = mListener;
        context = mContext;
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

        return items.get(position) instanceof Attribute_Group;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new SizeItemSingleDelegate.SizeItemViewHolder(inflater.inflate(R.layout.prod_att_list_item_demo, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ArrayList<Attribute_Group> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        configureSelectionItemCardViewHolder((SizeItemSingleDelegate.SizeItemViewHolder) holder, items.get(position));
    }


    private void configureSelectionItemCardViewHolder(SizeItemSingleDelegate.SizeItemViewHolder holder, Object mValue) {

        if (mValue instanceof Attribute_Group) {
            holder.bind(((Attribute_Group) mValue));
        }
    }


    class SizeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.size_item)
        AppCompatTextView mSizeItem;
        private Attribute_Group mItem;
        @BindView(R.id.main_card)
        CardView mMainCard;
        final View mView;

        SizeItemViewHolder(View view) {
            super(view);

            mView = view;
            ButterKnife.bind(this, view);
            mView.setOnClickListener(this);

        }

        void bind(Attribute_Group mItem) {
            this.mItem = mItem;
            if (mItem.getIs_visible()) {
                mSizeItem.setText(mItem.getName());
                if (this.mItem.getSelected()) {
                    mSizeItem.setBackground(ContextCompat.getDrawable(context, R.drawable.fill_square));
                    mSizeItem.setTextColor(Color.parseColor("#ffffff"));
                    id = this.mItem.getIdAttributes().toString();
                    mItemListener.defaultItem(id);
                } else {
                    mSizeItem.setBackground(ContextCompat.getDrawable(context, R.drawable.square_shape));
                    mSizeItem.setTextColor(Color.parseColor("#000000"));
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
