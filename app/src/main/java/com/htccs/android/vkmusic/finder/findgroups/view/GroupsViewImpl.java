package com.htccs.android.vkmusic.finder.findgroups.view;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.finder.findgroups.presenter.FindGroupsPresenter;

import java.util.ArrayList;

public class GroupsViewImpl implements GroupsView {

    private FindGroupsPresenter presenter;
    private RecyclerView recyclerView;
    private GroupViewAdapter adapter;

    public GroupsViewImpl(View view) {
        recyclerView = view.findViewById(R.id.recycler_list);
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new GroupViewAdapter(this, view.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGroupItemClicked(String groupId, String title) {
        presenter.showGroupInfo(groupId, title);
    }

    @Override
    public void populateGroupList(ArrayList cardView) {
        adapter.setCardWalls(cardView);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(FindGroupsPresenter presenter) {
        this.presenter = presenter;
    }
}
