package com.varshaaweblabs.ecommerce.ProductFullDetails.Adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Delegates.ColorItemSingleDelegate;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Attribute_Group;

import java.util.ArrayList;
import java.util.List;

public class ProductColor_Adapter extends RecyclerView.Adapter implements ColorItemSingleDelegate.SingleItemHelper {
    private AdapterDelegatesManager<ArrayList<Attribute_Group>> delegatesManager;
    private Helper mListener;
    private int singleSelectionPos = 987456;

    public void setValues(ArrayList<Attribute_Group> grp) {
        grps.addAll(grp);
        notifyDataSetChanged();
    }

    ArrayList<Attribute_Group> grps = new ArrayList<>();

    public ProductColor_Adapter(Context mContext, Helper mListener) {
        this.mListener = mListener;
        ArrayList<Attribute_Group> grp_att = new ArrayList<>();
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new ColorItemSingleDelegate(mContext, this));
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(grps, position, holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        delegatesManager.onBindViewHolder(grps, position, holder, payloads);
    }

    @Override
    public int getItemCount() {
        return grps.size();
    }


    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(grps, position);
    }


    @Override
    public void selectedItem(Attribute_Group mColorItem, int Pos) {
        for (Object value : grps) {
            if (((Attribute_Group) value).getIdAttributes() != mColorItem.getIdAttributes()) {
                ((Attribute_Group) value).setSelected(false);
            } else {
                ((Attribute_Group) value).setSelected(true);
            }

        }
        if (mListener != null) {
            mListener.onItemSelected(mColorItem);
        }
        notifyDataSetChanged();
    }

    @Override
    public void defaultItem(String id) {
        if (mListener != null) {
            mListener.onDefaultSelected(id);
        }
    }


    public interface Helper {

        void onItemSelected(Attribute_Group mSingleChoiceItem);
        void onDefaultSelected(String id);

    }

}