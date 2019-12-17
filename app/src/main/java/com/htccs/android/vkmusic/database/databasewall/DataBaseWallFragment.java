package com.htccs.android.vkmusic.database.databasewall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.database.databasewall.presenterbd.DataBasePresenter;
import com.htccs.android.vkmusic.database.databasewall.presenterbd.DataBasePresenterImpl;
import com.htccs.android.vkmusic.database.databasewall.viewbd.DataBaseView;
import com.htccs.android.vkmusic.database.databasewall.viewbd.DataBaseViewImpl;

public class DataBaseWallFragment extends Fragment {
    public static final String TAG = DataBaseWallFragment.class.getSimpleName();
    private DataBasePresenter presenter;
    private static final String GROUP_ID_EXTRA = "idGroup";

    public static DataBaseWallFragment newInstance(String groupId) {
        Bundle args = new Bundle();
        args.putString(GROUP_ID_EXTRA, groupId);
        DataBaseWallFragment fragment = new DataBaseWallFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.data_base_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dataView = inflater.inflate(R.layout.fragment_data_base, container, false);

        DataBaseView view = new DataBaseViewImpl(dataView);
        presenter = new DataBasePresenterImpl(view, getContext());
        view.setPresenter(presenter);

        return dataView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_show_all) {
            presenter.showAllPost(getArguments().getString(GROUP_ID_EXTRA));
            return true;
        }
        if (id == R.id.action_show_max_like) {
            presenter.showMaxLikePost(getArguments().getString(GROUP_ID_EXTRA));
            return true;
        }
        if (id == R.id.action_show_max_comment) {
            presenter.showMaxComment(getArguments().getString(GROUP_ID_EXTRA));
            return true;
        }
        if (id == R.id.action_show_max_repost) {
            presenter.showMaxRepost(getArguments().getString(GROUP_ID_EXTRA));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}