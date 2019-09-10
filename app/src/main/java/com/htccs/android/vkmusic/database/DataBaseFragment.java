package com.htccs.android.vkmusic.database;

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
import com.htccs.android.vkmusic.database.presenter.DataBasePresenter;
import com.htccs.android.vkmusic.database.presenter.DataBasePresenterImpl;
import com.htccs.android.vkmusic.database.view.DataBaseView;
import com.htccs.android.vkmusic.database.view.DataBaseViewImpl;

public class DataBaseFragment extends Fragment {
    public static final String TAG = DataBaseFragment.class.getSimpleName();
    private DataBasePresenter presenter;

    public static DataBaseFragment newInstance() {
        Bundle args = new Bundle();

        DataBaseFragment fragment = new DataBaseFragment();
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
            presenter.showAllPost();
            return true;
        }
        if (id == R.id.action_show_max_like) {
            presenter.showMaxLikePost();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
