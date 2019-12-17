package com.htccs.android.vkmusic.wallgroup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.FragmentInteractionPicture;
import com.htccs.android.vkmusic.PostsActivity;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.wallgroup.presenter.WallPresenter;
import com.htccs.android.vkmusic.wallgroup.presenter.WallPresenterImpl;
import com.htccs.android.vkmusic.wallgroup.view.WallView;
import com.htccs.android.vkmusic.wallgroup.view.WallViewImpl;

public class WallGroupFragment extends Fragment {

    public static final String TAG = WallGroupFragment.class.getSimpleName();
    private static final String GROUP_ID_EXTRA = "groupIdExtra";
    private FragmentInteractionPicture fragmentInteractionPicture;

    public static WallGroupFragment newInstance(String groupId) {
        Bundle args = new Bundle();
        args.putString(GROUP_ID_EXTRA, groupId);
        WallGroupFragment fragment = new WallGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PostsActivity) {
            fragmentInteractionPicture = (PostsActivity) context;
        } else {
            throw new IllegalStateException("your activity must implement " + FragmentInteractionPicture.class.getCanonicalName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View listGroupView = inflater.inflate(R.layout.fragment_list_groups, container, false);

        String idGroup = getArguments().getString(GROUP_ID_EXTRA);
        WallView groupsView = new WallViewImpl(listGroupView);
        WallPresenter wallPresenter = new WallPresenterImpl(groupsView, idGroup, fragmentInteractionPicture, getContext());
        groupsView.setPresenter(wallPresenter);
        wallPresenter.loadWall();

        return listGroupView;
    }
}
