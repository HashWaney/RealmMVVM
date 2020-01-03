package com.realmmvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.realmmvvm.R;
import com.realmmvvm.databinding.ItemLayoutBinding;
import com.realmmvvm.model.StoreInfo;

import java.util.Collections;
import java.util.List;

/**
 * Created by imtiaz on 9/12/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.StoreViewHolder> {

    private List<StoreInfo> storeModels;
    private ItemClickCallback callback;

    public ListAdapter() {
        storeModels = Collections.emptyList();
    }

    public void setCallback(ItemClickCallback callback) {
        this.callback = callback;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLayoutBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_layout, parent, false);
        itemBinding.setCallback(callback);
        return new StoreViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        holder.bindStore(storeModels.get(position));
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return storeModels.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setStores(List<StoreInfo> storeModels) {
        this.storeModels.clear();
        this.storeModels = storeModels;
        notifyDataSetChanged();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding itemBinding;

        public StoreViewHolder(ItemLayoutBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }

        void bindStore(StoreInfo storeModel) {
            itemBinding.setItemModel(storeModel);
        }
    }

    public interface ItemClickCallback {
        void onClick(StoreInfo model);
    }
}
