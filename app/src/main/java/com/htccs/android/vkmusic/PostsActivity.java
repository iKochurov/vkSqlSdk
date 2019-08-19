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
        String tag = GroupsListFragment.TAG;
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        Fragment fragmentInstance = GroupsListFragment.newInstance();

        fragmentTransaction(fragmentInstance, fragment, tag);
    }

    @Override
    public void onGroupItemCliked(String groupId) {
        Log.d("fragmentManager", "Открываю второй фрагмент");
        String tag = WallGroupFragment.TAG;
        Fragment fragment = fragmentManager.findFragmentByTag(WallGroupFragment.TAG);
        Fragment fragmentInstance = WallGroupFragment.newInstance(groupId);
        
        fragmentTransaction(fragmentInstance, fragment, tag);
    }

    private void fragmentTransaction(Fragment fragment, Fragment tagFragment, String tag) {
        if (tagFragment == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, fragment, tag)
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
        } else {
            fragmentManager.popBackStack();
        }
    }
}

