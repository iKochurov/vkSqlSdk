package com.htccs.android.vkmusic.wallgroup.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.VkItemDecoration;
import com.htccs.android.vkmusic.wallgroup.presenter.WallViewAdapter;

import java.util.ArrayList;

public class WallViewImpl implements WallView {

    private RecyclerView recyclerView;
    private WallViewAdapter adapter;


    public WallViewImpl(View view) {
        recyclerView = view.findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new WallViewAdapter(view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new VkItemDecoration());
    }

    @Override
    public void populateWall(ArrayList wallView) {
        adapter.setCardWalls(wallView);
        adapter.notifyDataSetChanged();
    }
}
