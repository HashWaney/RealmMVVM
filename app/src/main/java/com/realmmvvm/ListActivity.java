package com.realmmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.realmmvvm.adapter.ListAdapter;
import com.realmmvvm.databinding.ListLayoutBinding;
import com.realmmvvm.viewmodel.ListViewModel;

public class ListActivity extends AppCompatActivity {

    private ListLayoutBinding binding;

    private ListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.list_layout);
        adapter = new ListAdapter();
        adapter.setCallback(callback);
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setEmptyView(binding.emptyMessage);
        binding.list.setAdapter(adapter);

        ListViewModel viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        subscribeUI(viewModel);
    }

    private void subscribeUI(ListViewModel viewModel) {
        viewModel.getStores().observe(this, dataModel -> {
            if (dataModel != null) {
                binding.setIsLoading(false);
                adapter.setStores(dataModel);
            } else {
                binding.setIsLoading(true);
            }

            adapter.notifyDataSetChanged();
            binding.executePendingBindings();
        });
    }

    ListAdapter.ItemClickCallback callback = model -> {
        Snackbar.make(binding.parentPanel, model.getCustomer_name(), Snackbar.LENGTH_LONG).show();
    };
}

