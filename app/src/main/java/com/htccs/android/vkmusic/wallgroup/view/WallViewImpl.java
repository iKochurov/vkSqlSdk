package com.htccs.android.vkmusic.wallgroup.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.wallgroup.presenter.WallViewAdapter;
import com.htccs.android.vkmusic.wallgroup.presenter.WallPresenter;

import java.util.ArrayList;

public class WallViewImpl implements WallView {

    private RecyclerView recyclerView;
    private WallPresenter presenter;
    private WallViewAdapter adapter;

    public WallViewImpl(View view) {
        recyclerView = view.findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new WallViewAdapter();
    }

    public void setPresenter(WallPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void populateWall(ArrayList wallView) {
        adapter.setCardWalls(wallView);
        recyclerView.setAdapter(adapter);
    }
}
