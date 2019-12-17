package com.htccs.android.vkmusic.database.databasegroup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.database.activity.DataBaseActivity;
import com.htccs.android.vkmusic.database.databasegroup.presenter.DataBaseGroupPresenterImpl;
import com.htccs.android.vkmusic.listgroup.presenter.GroupsPresenter;
import com.htccs.android.vkmusic.listgroup.view.GroupsView;
import com.htccs.android.vkmusic.listgroup.view.GroupsViewImpl;

public class DataBaseGroupFragment extends Fragment {

    public static final String TAG = DataBaseGroupFragment.class.getSimpleName();
    private FragmentInteraction fragmentInteraction;

    public static DataBaseGroupFragment newInstance() {
        return new DataBaseGroupFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataBaseActivity) {
            fragmentInteraction = (DataBaseActivity) context;
        } else {
            throw new IllegalStateException("your activity must implement " + FragmentInteraction.class.getCanonicalName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listGroupView = inflater.inflate(R.layout.fragment_list_groups, container, false);
        GroupsView groupsView = new GroupsViewImpl(listGroupView);
        GroupsPresenter groupsPresenter = new DataBaseGroupPresenterImpl(listGroupView, groupsView, fragmentInteraction, getContext());
        ((GroupsViewImpl) groupsView).setPresenter(groupsPresenter);
        groupsPresenter.loadGroups();
        return listGroupView;
    }
}