package com.htccs.android.vkmusic.ListGroups;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.htccs.android.vkmusic.FragmentView;
import com.htccs.android.vkmusic.R;

public class PostsActivity extends AppCompatActivity implements FragmentView {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment = new GroupsListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        GroupViewAdapter groupViewAdapter = new GroupViewAdapter();
        groupViewAdapter.setFragment(this);

        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void setData(Fragment fragmentWall) {
        fragmentManager.beginTransaction().replace(R.id.container, fragmentWall).commit();
    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

}

