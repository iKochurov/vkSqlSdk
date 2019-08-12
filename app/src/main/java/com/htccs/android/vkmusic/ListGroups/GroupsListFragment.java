package com.htccs.android.vkmusic.ListGroups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.htccs.android.vkmusic.PostsView;
import com.htccs.android.vkmusic.R;

import java.util.ArrayList;

public class GroupsListFragment extends Fragment implements PostsView {

    private RecyclerView recyclerView;
    GroupViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View listGroupView = inflater.inflate(R.layout.fragment_list_groups, container, false);
        recyclerView = listGroupView.findViewById(R.id.recycler_list);
        PostsPresenter presenterCards = new PostsPresenter();
        presenterCards.setView(this);
        presenterCards.sendingModel();
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new GroupViewAdapter();

        return listGroupView;
    }

    @Override
    public void setData(ArrayList cardWalls) {
        adapter.setCardGroups(cardWalls);
        recyclerView.setAdapter(adapter);
    }

}
