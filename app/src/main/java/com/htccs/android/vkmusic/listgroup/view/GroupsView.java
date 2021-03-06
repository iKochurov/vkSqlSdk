package com.htccs.android.vkmusic.listgroup.view;

import com.htccs.android.vkmusic.listgroup.GroupItemClickListener;
import com.htccs.android.vkmusic.listgroup.presenter.GroupsPresenter;

import java.util.ArrayList;

public interface GroupsView extends GroupItemClickListener {

    void populateGroupList(ArrayList cardView);

    void setPresenter(GroupsPresenter presenter);
}
