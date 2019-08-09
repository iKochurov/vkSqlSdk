package com.htccs.android.vkmusic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PostsPresenter implements IPostsPresenter {

    private ArrayList<CardWall> cardWalls = new ArrayList<>();
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
                ListGroup listGroup = gson.fromJson(response.json.toString(),ListGroup.class);
                List list = listGroup.getResponse().getItems();

            }
        });

        final VKRequest request = new VKApiGroups().getById(VKParameters.from("group_ids", "1959"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                String jsonText = response.json.toString();
                final GroupInfo groupInfo = gson.fromJson(jsonText, GroupInfo.class);
                VKList vkList = (VKList) response.parsedModel;

                try {
                    VKRequest requestWall = new VKApiWall()
                            .get(VKParameters.from(VKApiConst.OWNER_ID, "-" + vkList.get(0).fields.getInt("id"), VKApiConst.COUNT, 10));
                    requestWall.executeWithListener(new VKRequest.VKRequestListener() {

                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);
                            try {

                                WallInfo wallInfo = gson.fromJson(response.json.toString(), WallInfo.class);
                                List<Item> itemList = wallInfo.getResponse().getItems();

                                for (int i = 0; i < itemList.size(); i++) {

                                    Item itemPost = itemList.get(i);
                                    String urlIcon = groupInfo.getResponse().get(0).getPhoto50();
                                    String countLike = itemPost.getLikes().getCount().toString();
                                    String countRepost = itemPost.getReposts().getCount().toString();

                                    try {
                                        String urlPicture = itemPost.getAttachments().get(0).getPhoto().getPhoto_size();
                                        cardWalls.add(new CardWall(groupInfo.getResponse().get(0).getName(), urlIcon, itemPost.getText(), urlPicture, countLike, countRepost));
                                    } catch (NullPointerException e) {
                                        cardWalls.add(new CardWall(groupInfo.getResponse().get(0).getName(), urlIcon, itemPost.getText(), countLike, countRepost));
                                    }
                                    postsView.setData(cardWalls);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}