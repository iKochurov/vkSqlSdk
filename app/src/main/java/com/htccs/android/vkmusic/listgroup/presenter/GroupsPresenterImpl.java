package com.htccs.android.vkmusic.listgroup.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.FragmentInteraction;
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

import static com.htccs.android.vkmusic.listgroup.GroupsListFragment.TAG;

public class GroupsPresenterImpl implements GroupsPresenter {

    private GroupsView groupsView;
    private FragmentInteraction fragmentInteraction;
    private ArrayList<CardGroup> cardGroups = new ArrayList<>();
    private Gson gson;
    private GsonBuilder builder = new GsonBuilder();

    public GroupsPresenterImpl(GroupsView groupsView, FragmentInteraction fragmentInteraction) {
        Log.d("A", "презентер");
        this.groupsView = groupsView;
        this.fragmentInteraction = fragmentInteraction;
    }

    @Override
    public void loadGroups() {
        receptionData();
    }

    @Override
    public void showGroupInfo(String groupId) {
        fragmentInteraction.onGroupItemCliked(groupId);
    }

    private void receptionData() {

        gson = builder.create();

        final VKRequest vkRequest = VKApi.groups().get(VKParameters.from(VKApiConst.FIELDS, "name,photo_50", VKApiConst.EXTENDED, 1));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(final VKResponse response) {
                super.onComplete(response);
                Log.d("response", response.json.toString());
                ResponseListGroup list = gson.fromJson(response.json.toString(), ResponseListGroup.class);
                Integer count = list.getResponse().getCount();

                for (int i = 0; i < count; i++) {
                    Items listGroup = list.getResponse().getItems().get(i);

                    String nameGroup = listGroup.getName();
                    String iconGroup = listGroup.getPhotoFirstSize();
                    String numberGroup = listGroup.getId().toString();
                    cardGroups.add(new CardGroup(nameGroup, iconGroup, numberGroup));
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
}
