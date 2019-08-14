package com.htccs.android.vkmusic.listgroup.view;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.listgroup.presenter.GroupViewAdapter;
import com.htccs.android.vkmusic.listgroup.presenter.GroupsPresenter;

import java.util.ArrayList;

public class GroupsViewImpl implements GroupsView {

    private GroupsPresenter presenter;
    private RecyclerView recyclerView;
    private GroupViewAdapter adapter;

    public void setPresenter(GroupsPresenter presenter) {
        this.presenter = presenter;
    }

    public GroupsViewImpl(View view) {
        Log.d("A", "вьюха");
        recyclerView = view.findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new GroupViewAdapter(this, view.getContext());
    }


    @Override
    public void onGroupItemClicked(String groupId) {
        presenter.showGroupInfo(groupId);
    }

    @Override
    public void populateGroupList(ArrayList cardView) {
        adapter.setCardWalls(cardView);
        recyclerView.setAdapter(adapter);
    }
}
