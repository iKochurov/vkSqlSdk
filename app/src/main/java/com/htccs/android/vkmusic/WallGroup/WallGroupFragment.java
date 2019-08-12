package com.htccs.android.vkmusic.WallGroup;

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

public class WallGroupFragment extends Fragment implements PostsView {

    private RecyclerView recyclerView;
    String numberGroups;
    RecyclerViewAdapter adapter;

    public WallGroupFragment(String numberGroups) {
        this.numberGroups = numberGroups;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View listGroupView = inflater.inflate(R.layout.fragment_list_groups, container, false);
        recyclerView = listGroupView.findViewById(R.id.recycler_list);
        WallCard presenterCards = new WallCard(numberGroups);
        presenterCards.setView(this);
        presenterCards.sendingModel();
        LinearLayoutManager layoutManagerForCards = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        adapter = new RecyclerViewAdapter();

        return listGroupView;
    }

    @Override
    public void setData(ArrayList cardWalls) {

        adapter.setCardWalls(cardWalls);
        recyclerView.setAdapter(adapter);
    }
}
