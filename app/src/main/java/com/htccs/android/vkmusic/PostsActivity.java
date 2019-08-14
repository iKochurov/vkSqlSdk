package com.htccs.android.vkmusic;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.htccs.android.vkmusic.listgroup.GroupsListFragment;
import com.htccs.android.vkmusic.wallgroup.WallGroupFragment;

public class PostsActivity extends AppCompatActivity implements FragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayGroupListFragment();
    }

    private void displayGroupListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, GroupsListFragment.newInstance(), GroupsListFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onGroupItemCliked(String groupId) {
        Log.d("A", "Открываю второй фрагмент");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, WallGroupFragment.newInstance(groupId))
                .addToBackStack(null)
                .commit();
    }
}

