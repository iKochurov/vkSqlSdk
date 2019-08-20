package com.htccs.android.vkmusic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.htccs.android.vkmusic.listgroup.GroupsListFragment;
import com.htccs.android.vkmusic.wallgroup.WallGroupFragment;

public class PostsActivity extends AppCompatActivity implements FragmentInteraction {

    private final static String GROUPS = "Сообщества";
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayGroupListFragment();
    }

    private void displayGroupListFragment() {
        setTitle(GROUPS);
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        if (fragment == null) {
            fragment = GroupsListFragment.newInstance();
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, GroupsListFragment.TAG)
                .commit();
    }

    @Override
    public void onGroupItemCliked(String groupId, String title) {
        setTitle(title);
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        if (fragment == null) {
            fragment = WallGroupFragment.newInstance(groupId);
        }
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment, WallGroupFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            setTitle(GROUPS);
            fragmentManager.popBackStack();
        }
    }
}

