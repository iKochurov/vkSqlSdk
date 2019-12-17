package com.htccs.android.vkmusic.database.databasegroup.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.database.PostDataBaseHelper;
import com.htccs.android.vkmusic.database.VkContract;
import com.htccs.android.vkmusic.listgroup.models.CardGroup;
import com.htccs.android.vkmusic.listgroup.presenter.GroupsPresenter;
import com.htccs.android.vkmusic.listgroup.view.GroupsView;

import java.util.ArrayList;

public class DataBaseGroupPresenterImpl implements GroupsPresenter, View.OnClickListener {

    private GroupsView groupsView;
    private FragmentInteraction fragmentInteraction;
    private ArrayList<CardGroup> cardGroups = new ArrayList<>();

    private PostDataBaseHelper baseHelper;
    private Context context;

    public DataBaseGroupPresenterImpl(View view, GroupsView groupsView, FragmentInteraction fragmentInteraction, Context context) {
        this.groupsView = groupsView;
        baseHelper = new PostDataBaseHelper(context);
        this.context = context;
        this.fragmentInteraction = fragmentInteraction;
    }

    @Override
    public void loadGroups() {
        receptionData();
    }

    @Override
    public void showGroupInfo(String groupId, String title) {
        fragmentInteraction.onGroupItemClicked(groupId, title);
    }

    private void receptionData() {
        SQLiteDatabase db = baseHelper.getReadableDatabase();

        Cursor cursor = db.query(
                VkContract.GroupTable.TABLE_NAME,
                new String[]{"*"},
                null,
                null,
                null,
                null,
                null);

        try {
            int idColumnIndex = cursor.getColumnIndex(VkContract.GroupTable.COLUMN_GROUP_ID);
            int nameColumnIndex = cursor.getColumnIndex(VkContract.GroupTable.COLUMN_NAME_GROUP);
            int iconColumnIndex = cursor.getColumnIndex(VkContract.GroupTable.COLUMN_ICON);

            while (cursor.moveToNext()) {
                String currentID = cursor.getString(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentIcon = cursor.getString(iconColumnIndex);

                cardGroups.add(new CardGroup(currentName, currentIcon, currentID));

            }
        } finally {
            cursor.close();
            groupsView.populateGroupList(cardGroups);
        }
    }

    @Override
    public void onClick(View view) {
    }
}