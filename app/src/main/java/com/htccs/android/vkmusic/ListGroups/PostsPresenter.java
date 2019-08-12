package com.htccs.android.vkmusic.ListGroups;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.IPostsPresenter;
import com.htccs.android.vkmusic.PostsView;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;

import java.util.ArrayList;
import java.util.List;

public class PostsPresenter implements IPostsPresenter {

    private ArrayList<CardGroup> cardGroups = new ArrayList<>();
    private Gson gson;
    private PostsView postsView;

    @Override
    public void setView(PostsView postsView) {
        this.postsView = postsView;
    }

    @Override
    public void sendingModel() {
        receptionData();
    }

    private void receptionData() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();

        final VKRequest vkRequest = VKApi.groups().get(VKParameters.from(VKApiConst.USER_ID));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                ListGroup list = gson.fromJson(response.json.toString(), ListGroup.class);
                List listGroup = list.getResponse().getItems();
                Log.d("size", String.valueOf(listGroup.size()));
                for (int i = 0; i < listGroup.size(); i++) {
                    String idGroup = listGroup.get(i).toString();
                    final VKRequest request = new VKApiGroups().getById(VKParameters.from("group_ids", idGroup));
                    request.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);

                            String jsonText = response.json.toString();

                            final GroupInfo groupInfo = gson.fromJson(jsonText, GroupInfo.class);

                            String nameGroup = groupInfo.getResponse().get(0).getName();
                            String iconGroup = groupInfo.getResponse().get(0).getPhoto50();
                            String numberGroup = groupInfo.getResponse().get(0).getId().toString();
                            System.out.println(iconGroup);
                            cardGroups.add(new CardGroup(nameGroup, iconGroup, numberGroup));
                            postsView.setData(cardGroups);
                        }
                    });
                }
            }
        });
    }
}