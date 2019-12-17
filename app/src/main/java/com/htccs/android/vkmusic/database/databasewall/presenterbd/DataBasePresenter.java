package com.htccs.android.vkmusic.database.databasewall.presenterbd;

public interface DataBasePresenter {

    void showAllPost(String groupId);

    void showMaxLikePost(String groupId);

    void showMaxRepost(String groupId);

    void showMaxComment(String groupId);
}
