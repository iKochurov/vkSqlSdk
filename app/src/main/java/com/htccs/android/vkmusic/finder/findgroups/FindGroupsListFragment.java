package com.htccs.android.vkmusic.finder.findgroups;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.PostsActivity;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.finder.findgroups.presenter.FindGroupsPresenter;
import com.htccs.android.vkmusic.finder.findgroups.presenter.FindGroupsPresenterImpl;
import com.htccs.android.vkmusic.finder.findgroups.view.GroupsView;
import com.htccs.android.vkmusic.finder.findgroups.view.GroupsViewImpl;

public class FindGroupsListFragment extends Fragment {

    public static final String TAG = FindGroupsListFragment.class.getSimpleName();
    private FragmentInteraction fragmentInteraction;
    private static final String KEY = "ID";

    public static FindGroupsListFragment newInstance(String idGroup) {
        Bundle args = new Bundle();
        args.putString(KEY, idGroup);
        FindGroupsListFragment fragment = new FindGroupsListFragment();
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
        FindGroupsPresenter groupsPresenter = new FindGroupsPresenterImpl(listGroupView, groupsView, fragmentInteraction, getContext());
        ((GroupsViewImpl) groupsView).setPresenter(groupsPresenter);
        groupsPresenter.loadGroups( getArguments().getString(KEY));
        return listGroupView;
    }
}
