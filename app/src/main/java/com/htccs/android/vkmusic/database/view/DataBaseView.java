package com.htccs.android.vkmusic.database.view;

import com.htccs.android.vkmusic.database.presenter.DataBasePresenter;

import java.util.ArrayList;

public interface DataBaseView {

    void setPresenter(DataBasePresenter presenter);

    void populateRecycler(ArrayList dataView);
}
