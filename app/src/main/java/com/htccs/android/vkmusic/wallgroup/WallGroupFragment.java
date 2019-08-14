package com.htccs.android.vkmusic.wallgroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.wallgroup.presenter.WallPresenter;
import com.htccs.android.vkmusic.wallgroup.presenter.WallPresenterImpl;
import com.htccs.android.vkmusic.wallgroup.view.WallView;
import com.htccs.android.vkmusic.wallgroup.view.WallViewImpl;

public class WallGroupFragment extends Fragment {

    public static final String TAG = WallGroupFragment.class.getSimpleName();
    private static final String GROUP_ID_EXTRA = "groupIdExtra";

    public static WallGroupFragment newInstance(String groupId) {
        Bundle args = new Bundle();
        args.putString(GROUP_ID_EXTRA, groupId);
        WallGroupFragment fragment = new WallGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View listGroupView = inflater.inflate(R.layout.fragment_list_groups, container, false);

        String idGroup = getArguments().getString(GROUP_ID_EXTRA);
        WallView groupsView = new WallViewImpl(listGroupView);
        WallPresenter wallPresenter = new WallPresenterImpl(groupsView, idGroup);
        ((WallViewImpl) groupsView).setPresenter(wallPresenter);
        wallPresenter.loadWall();

        return listGroupView;
    }

}
