package com.htccs.android.vkmusic.listgroup.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.listgroup.models.CardGroup;
import com.htccs.android.vkmusic.listgroup.models.GroupInfo;
import com.htccs.android.vkmusic.listgroup.models.ListGroup;
import com.htccs.android.vkmusic.listgroup.view.GroupsView;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKBatchRequest;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

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

        final VKRequest vkRequest = VKApi.groups().get(VKParameters.from(VKApiConst.USER_ID));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(final VKResponse response) {
                super.onComplete(response);

                ListGroup list = gson.fromJson(response.json.toString(), ListGroup.class);
                List listGroup = list.getResponse().getItems();
                VKRequest[] vkRequestList = new VKRequest[listGroup.size()];

                for (int i = 0; i < listGroup.size(); i++) {
                    String idGroup = listGroup.get(i).toString();
                    Log.d("A", idGroup);
                    VKRequest vkRequest = VKApi.groups().getById(VKParameters.from("group_ids", idGroup));
                    vkRequestList[i] = vkRequest;
                }

                VKBatchRequest batchRequest = new VKBatchRequest(vkRequestList);
                batchRequest.executeWithListener(new VKBatchRequest.VKBatchRequestListener() {
                    @Override
                    public void onComplete(VKResponse[] responses) {
                        super.onComplete(responses);
                        for (int i = 0; i < responses.length; i++) {
                            String jsonText = responses[i].json.toString();
                            final GroupInfo groupInfo = gson.fromJson(jsonText, GroupInfo.class);
                            String nameGroup = groupInfo.getResponse().get(0).getName();
                            Log.d("A", nameGroup);
                            String iconGroup = groupInfo.getResponse().get(0).getPhotoFirstSize();
                            String numberGroup = groupInfo.getResponse().get(0).getId().toString();
                            cardGroups.add(new CardGroup(nameGroup, iconGroup, numberGroup));
                        }
                        groupsView.populateGroupList(cardGroups);
                        Log.d("A", String.valueOf(cardGroups.size()));
                    }
                });
            }
        });
    }
}
