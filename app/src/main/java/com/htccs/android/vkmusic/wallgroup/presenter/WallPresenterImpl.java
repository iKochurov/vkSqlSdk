package com.htccs.android.vkmusic.wallgroup.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.listgroup.models.GroupInfo;
import com.htccs.android.vkmusic.wallgroup.models.CardWall;
import com.htccs.android.vkmusic.wallgroup.models.Item;
import com.htccs.android.vkmusic.wallgroup.models.WallInfo;
import com.htccs.android.vkmusic.wallgroup.view.WallView;
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

public class WallPresenterImpl implements WallPresenter {

    private WallView wallView;
    private ArrayList<CardWall> cardWalls = new ArrayList<>();
    private Gson gson;
    private String numbergroup;
    private GsonBuilder builder = new GsonBuilder();

    public WallPresenterImpl(WallView wallView, String numbergroup) {
        this.wallView = wallView;
        this.numbergroup = numbergroup;
    }

    @Override
    public void loadWall() {
        receptionData();
    }

    private void receptionData() {
        gson = builder.create();

        final VKRequest request = new VKApiGroups().getById(VKParameters.from("group_ids", numbergroup));
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
                                    String nameGroup = groupInfo.getResponse().get(0).getName();
                                    String urlIcon = groupInfo.getResponse().get(0).getPhotoFirstSize();
                                    String countLike = itemPost.getLikes().getCount().toString();
                                    String countRepost = itemPost.getReposts().getCount().toString();

                                    try {
                                        String urlPicture = itemPost.getAttachments().get(0).getPhoto().getPhoto_size();
                                        cardWalls.add(new CardWall(nameGroup, urlIcon, itemPost.getText(), urlPicture, countLike, countRepost));
                                    } catch (NullPointerException e) {
                                        cardWalls.add(new CardWall(nameGroup, urlIcon, itemPost.getText(), countLike, countRepost));
                                    }
                                }
                                wallView.populateWall(cardWalls);
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
