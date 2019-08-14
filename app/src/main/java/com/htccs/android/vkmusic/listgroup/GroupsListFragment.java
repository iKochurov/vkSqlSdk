package com.htccs.android.vkmusic.listgroup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.PostsActivity;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.listgroup.presenter.GroupsPresenter;
import com.htccs.android.vkmusic.listgroup.presenter.GroupsPresenterImpl;
import com.htccs.android.vkmusic.listgroup.view.GroupsView;
import com.htccs.android.vkmusic.listgroup.view.GroupsViewImpl;

public class GroupsListFragment extends Fragment {

    public static final String TAG = GroupsListFragment.class.getSimpleName();
    private FragmentInteraction fragmentInteraction;

    public static GroupsListFragment newInstance() {
        Bundle args = new Bundle();
        GroupsListFragment fragment = new GroupsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PostsActivity) {
            fragmentInteraction = (PostsActivity) context;
        } else {
            throw new IllegalStateException("your activity must implement " + FragmentInteraction.class.getCanonicalName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listGroupView = inflater.inflate(R.layout.fragment_list_groups, container, false);
        GroupsView groupsView = new GroupsViewImpl(listGroupView);
        GroupsPresenter groupsPresenter = new GroupsPresenterImpl(groupsView, fragmentInteraction);
        ((GroupsViewImpl) groupsView).setPresenter(groupsPresenter);
        groupsPresenter.loadGroups();

        return listGroupView;
    }
}
