package com.htccs.android.vkmusic.database.databasewall.viewbd;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.database.databasewall.presenterbd.DataBasePresenter;
import com.htccs.android.vkmusic.wallgroup.VkItemDecoration;

import java.util.ArrayList;

public class DataBaseViewImpl implements DataBaseView {

    private DataBasePresenter presenter;
    private RecyclerView recyclerView;
    private DataBaseAdapter adapter;

    public DataBaseViewImpl(View view) {
        recyclerView = view.findViewById(R.id.recycler_data);
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new DataBaseAdapter(view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new VkItemDecoration());
    }

    @Override
    public void setPresenter(DataBasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void populateRecycler(ArrayList dataView) {
        adapter.setCardWalls(dataView);
        adapter.notifyDataSetChanged();
    }
}
