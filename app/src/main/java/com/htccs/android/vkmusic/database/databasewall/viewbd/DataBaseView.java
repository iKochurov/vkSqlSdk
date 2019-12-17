package com.htccs.android.vkmusic.database.databasewall.viewbd;

import com.htccs.android.vkmusic.database.databasewall.presenterbd.DataBasePresenter;

import java.util.ArrayList;

public interface DataBaseView {

    void setPresenter(DataBasePresenter presenter);

    void populateRecycler(ArrayList dataView);
}
