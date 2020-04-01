package com.htccs.android.vkmusic.finder.findgroups.view;

import com.htccs.android.vkmusic.finder.findgroups.GroupItemClickListener;
import com.htccs.android.vkmusic.finder.findgroups.presenter.FindGroupsPresenter;

import java.util.ArrayList;

public interface GroupsView extends GroupItemClickListener {

    void populateGroupList(ArrayList cardView);

    void setPresenter(FindGroupsPresenter presenter);
}
