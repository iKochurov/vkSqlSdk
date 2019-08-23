package com.htccs.android.vkmusic.wallgroup.view;

import com.htccs.android.vkmusic.wallgroup.WallPictureClickListener;
import com.htccs.android.vkmusic.wallgroup.presenter.WallPresenter;

import java.util.ArrayList;

public interface WallView extends WallPictureClickListener {

    void populateWall(ArrayList wallView);

    void setPresenter(WallPresenter presenter);
}
