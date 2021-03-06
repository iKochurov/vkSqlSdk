package com.htccs.android.vkmusic.listgroup.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.database.PostDataBaseHelper;
import com.htccs.android.vkmusic.database.VkContract;
import com.htccs.android.vkmusic.listgroup.models.CardGroup;
import com.htccs.android.vkmusic.listgroup.models.Items;
import com.htccs.android.vkmusic.listgroup.models.ResponseListGroup;
import com.htccs.android.vkmusic.listgroup.view.GroupsView;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

import static com.htccs.android.vkmusic.listgroup.GroupsListFragment.TAG;

public class GroupsPresenterImpl implements GroupsPresenter, View.OnClickListener {

    private GroupsView groupsView;
    private FloatingActionButton actionButton;
    private FragmentInteraction fragmentInteraction;
    private ArrayList<CardGroup> cardGroups = new ArrayList<>();

    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    private String nameGroup;
    private String iconGroup;
    private String numberGroup;
    private PostDataBaseHelper baseHelper;
    private Context context;

    public GroupsPresenterImpl(View view, GroupsView groupsView, FragmentInteraction fragmentInteraction, Context context) {
        this.groupsView = groupsView;
        baseHelper = new PostDataBaseHelper(context);
        this.context = context;
        this.fragmentInteraction = fragmentInteraction;
        actionButton = view.findViewById(R.id.fab);
        actionButton.setOnClickListener(this);
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
        final VKRequest vkRequest = VKApi.groups().get(VKParameters.from(VKApiConst.FIELDS, "name,photo_50", VKApiConst.EXTENDED, 1));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(final VKResponse response) {
                super.onComplete(response);
                ResponseListGroup list = gson.fromJson(response.json.toString(), ResponseListGroup.class);
                Integer count = list.getResponse().getCount();
                List<Items> itemsList = list.getResponse().getItems();

                for (int i = 0; i < count; i++) {
                    Items listGroup = itemsList.get(i);

                    setInfo(listGroup);
                    cardGroups.add(new CardGroup(nameGroup, iconGroup, numberGroup));
                    populateDataBase(numberGroup, nameGroup, iconGroup);
                }
                groupsView.populateGroupList(cardGroups);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.e(TAG, "onError: ", error.httpError);
            }
        });
    }

    private void setInfo(Items items) {
        nameGroup = items.getName();
        iconGroup = items.getPhotoFirstSize();
        numberGroup = items.getId().toString();
    }

    private void populateDataBase(String id, String nameGroup, String icon) {
        SQLiteDatabase database = baseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VkContract.GroupTable.COLUMN_GROUP_ID, id);
        values.put(VkContract.GroupTable.COLUMN_NAME_GROUP, nameGroup);
        values.put(VkContract.GroupTable.COLUMN_ICON, icon);

        long newRowId = database.update(VkContract.GroupTable.TABLE_NAME, values, "idGroup = ?", new String[]{id});
        if (newRowId == 0) {
            newRowId = database.insert(VkContract.GroupTable.TABLE_NAME, null, values);
        }

        if (newRowId == -1) {
            Toast.makeText(context, "Ошибка при записи в БД", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("nomer", String.valueOf(newRowId));
        }
    }

    @Override
    public void onClick(View view) {
        fragmentInteraction.onFabClick();
    }
}
