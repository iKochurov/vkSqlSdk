package com.htccs.android.vkmusic.database.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.database.databasegroup.DataBaseGroupFragment;
import com.htccs.android.vkmusic.database.databasewall.DataBaseWallFragment;

public class DataBaseActivity extends AppCompatActivity implements FragmentInteraction {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayGroupListFragment();
    }

    public void displayGroupListFragment() {

        Fragment fragment = fragmentManager.findFragmentByTag(DataBaseGroupFragment.TAG);
        if (fragment == null) {
            fragment = DataBaseGroupFragment.newInstance();
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, DataBaseGroupFragment.TAG)
                .commit();
    }

    @Override
    public void onGroupItemClicked(String groupId, String title) {
        Fragment fragment = fragmentManager.findFragmentByTag(DataBaseWallFragment.TAG);
        if (fragment == null) {
            fragment = DataBaseWallFragment.newInstance(groupId);
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, DataBaseWallFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFabClick() {
    }

    @Override
    public void onFindClick() {

    }

    @Override
    public void onFindingGroup(String id) {

    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
