package com.htccs.android.vkmusic.wallgroup.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htccs.android.vkmusic.FragmentInteractionPicture;
import com.htccs.android.vkmusic.database.PostDataBaseHelper;
import com.htccs.android.vkmusic.database.VkContract;
import com.htccs.android.vkmusic.listgroup.models.GroupInfo;
import com.htccs.android.vkmusic.wallgroup.models.CardWall;
import com.htccs.android.vkmusic.wallgroup.models.Item;
import com.htccs.android.vkmusic.wallgroup.models.Photo;
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
    private String postId;
    private FragmentInteractionPicture interactionPicture;
    private PostDataBaseHelper baseHelper;
    private Context context;

    public WallPresenterImpl(WallView wallView, String numbergroup, FragmentInteractionPicture fragmentInteractionPicture, Context context) {
        this.wallView = wallView;
        this.numbergroup = numbergroup;
        this.interactionPicture = fragmentInteractionPicture;
        baseHelper = new PostDataBaseHelper(context);
        this.context = context;
    }

    @Override
    public void loadWall() {
        receptionData();
    }

    @Override
    public void showPicture(String urlPicture) {
        interactionPicture.onClickPicture(urlPicture);
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
                            System.out.println(response.json.toString());
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
            String urlPicture = item.getAttachments().get(0).getPhoto().getPhotoOneSize();
            String urlMaxPicture = findMaxSizePhoto(item.getAttachments().get(0).getPhoto());
            cardWalls.add(new CardWall(nameGroup, urlIcon, textWall, urlPicture, countLike, countRepost, dateWall, urlMaxPicture));
            populateDataBase(postId, nameGroup, urlIcon, dateWall, textWall, urlPicture, countLike, countRepost);
        } catch (NullPointerException e) {
            cardWalls.add(new CardWall(nameGroup, urlIcon, textWall, countLike, countRepost, dateWall));
            populateDataBase(postId, nameGroup, urlIcon, dateWall, textWall, null, countLike, countRepost);
        }
    }

    private String findMaxSizePhoto(Photo photo) {
        if (photo.getPhotoMaxSize() != null) {
            return photo.getPhotoMaxSize();
        } else if (photo.getPhotoTwoSize() != null) {
            return photo.getPhotoTwoSize();
        } else {
            return photo.getPhotoOneSize();
        }
    }

    private void setInfo(ResponseGroup responseGroup, Item item) {
        nameGroup = responseGroup.getName();
        urlIcon = responseGroup.getPhotoFirstSize();
        countLike = item.getLikes().getCount().toString();
        countRepost = item.getReposts().getCount().toString();
        textWall = item.getText();
        dateWall = getDate(item.getDate());
        postId = String.valueOf(item.getId() + item.getId());
    }

    private void populateDataBase(String id, String nameGroup, String icon, String date, String text, String picture, String like, String repost) {
        SQLiteDatabase database = baseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VkContract.PostTable.COLUMN_ID, id);
        values.put(VkContract.PostTable.COLUMN_NAME_GROUP, nameGroup);
        values.put(VkContract.PostTable.COLUMN_ICON, icon);
        values.put(VkContract.PostTable.COLUMN_DATE, date);
        values.put(VkContract.PostTable.COLUMN_TEXT, text);
        if (!(picture == null)) {
            values.put(VkContract.PostTable.COLUMN_IMAGE, picture);
        } else {
            values.put(VkContract.PostTable.COLUMN_IMAGE, "0");
        }
        values.put(VkContract.PostTable.COLUMN_LIKE, Integer.parseInt(like));
        values.put(VkContract.PostTable.COLUMN_REPOST, repost);

        long newRowId = database.insert(VkContract.PostTable.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(context, "Ошибка при записи в БД", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешно добавлено под номером " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private String getDate(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return dateFormat.format(date);
    }
}
