package com.htccs.android.vkmusic;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.htccs.android.vkmusic.listgroup.GroupsListFragment;
import com.htccs.android.vkmusic.wallgroup.WallGroupFragment;

public class PostsActivity extends AppCompatActivity implements FragmentInteraction {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayGroupListFragment();
    }

    private void displayGroupListFragment() {
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, GroupsListFragment.newInstance(), GroupsListFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            super.onBackPressed();
            System.exit(0);
        } else {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onGroupItemCliked(String groupId) {
        Log.d("fragmentManager", "Открываю второй фрагмент");

        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, WallGroupFragment.newInstance(groupId), WallGroupFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit();
        }
    }


}

