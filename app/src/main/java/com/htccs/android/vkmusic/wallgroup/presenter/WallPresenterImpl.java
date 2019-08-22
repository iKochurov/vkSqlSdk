package com.htccs.android.vkmusic.wallgroup.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.listgroup.models.GroupInfo;
import com.htccs.android.vkmusic.wallgroup.models.CardWall;
import com.htccs.android.vkmusic.wallgroup.models.Item;
import com.htccs.android.vkmusic.wallgroup.models.ResponseGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WallPresenterImpl implements WallPresenter {

    private WallView wallView;
    private ArrayList<CardWall> cardWalls = new ArrayList<>();

    private String numbergroup;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    private String nameGroup;
    private String urlIcon;
    private String countLike;
    private String countRepost;
    private String textWall;
    private String dateWall;

    public WallPresenterImpl(WallView wallView, String numbergroup) {
        this.wallView = wallView;
        this.numbergroup = numbergroup;
    }

    @Override
    public void loadWall() {
        receptionData();
    }

    private void receptionData() {

        final VKRequest request = new VKApiGroups().getById(VKParameters.from("group_ids", numbergroup));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                String jsonText = response.json.toString();
                final GroupInfo groupInfo = gson.fromJson(jsonText, GroupInfo.class);
                VKList vkList = (VKList) response.parsedModel;

                try {
                    String ownerParameters = "-" + vkList.get(0).fields.getInt("id");

                    VKRequest requestWall = new VKApiWall()
                            .get(VKParameters.from(VKApiConst.OWNER_ID, ownerParameters, VKApiConst.COUNT, 10));

                    requestWall.executeWithListener(new VKRequest.VKRequestListener() {

                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);

                            WallInfo wallInfo = gson.fromJson(response.json.toString(), WallInfo.class);
                            List<Item> itemList = wallInfo.getResponse().getItems();
                            ResponseGroup responseGroup = groupInfo.getResponse().get(0);

                            for (int i = 0; i < itemList.size(); i++) {
                                Item itemPost = itemList.get(i);

                                setInfo(responseGroup, itemPost);
                                addCard(itemPost);
                            }
                            wallView.populateWall(cardWalls);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addCard(Item item) {
        try {
            String urlPicture = item.getAttachments().get(0).getPhoto().getPhoto_size();
            cardWalls.add(new CardWall(nameGroup, urlIcon, textWall, urlPicture, countLike, countRepost, dateWall));
        } catch (NullPointerException e) {
            cardWalls.add(new CardWall(nameGroup, urlIcon, textWall, countLike, countRepost, dateWall));
        }
    }

    private void setInfo(ResponseGroup responseGroup, Item item) {
        nameGroup = responseGroup.getName();
        urlIcon = responseGroup.getPhotoFirstSize();
        countLike = item.getLikes().getCount().toString();
        countRepost = item.getReposts().getCount().toString();
        textWall = item.getText();
        dateWall = getDate(item.getDate());
    }

    private String getDate(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return dateFormat.format(date);
    }
}
