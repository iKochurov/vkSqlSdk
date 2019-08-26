package com.htccs.android.vkmusic.picturewall;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.picturewall.presenter.PicturePresenter;
import com.htccs.android.vkmusic.picturewall.presenter.PicturePresenterImpl;
import com.htccs.android.vkmusic.picturewall.view.PictureView;
import com.htccs.android.vkmusic.picturewall.view.PictureViewImpl;

public class PictureFragment extends Fragment {

    private static final int REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 1234;
    public static final String TAG = PictureFragment.class.getSimpleName();
    private static final String KEYURL = "picture";
    private PicturePresenter picturePresenter;
    private PictureView picture;

    public static PictureFragment newInstance(String urlPicture) {
        Bundle args = new Bundle();
        args.putString(KEYURL, urlPicture);
        PictureFragment fragment = new PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View pictureView = inflater.inflate(R.layout.fragment_picture, container, false);
        String urlPicture = getArguments().getString(KEYURL);

        picture = new PictureViewImpl(pictureView, urlPicture);
        picturePresenter = new PicturePresenterImpl((PictureViewImpl) picture, getContext(), this);

        return pictureView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            picturePresenter.savePicture();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    picturePresenter.savePicture();
                } else {
                    picture.showAlertDialog(getContext());
                }
        }
    }
}
