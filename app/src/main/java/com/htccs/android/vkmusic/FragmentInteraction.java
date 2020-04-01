package com.htccs.android.vkmusic;

public interface FragmentInteraction {

    void onGroupItemClicked(String groupId, String title);

    void onFabClick();

    void onFindClick();

    void onFindingGroup(String id);
}
