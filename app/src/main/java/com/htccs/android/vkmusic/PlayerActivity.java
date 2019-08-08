package com.htccs.android.vkmusic;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class PlayerActivity extends Activity {

    ArrayList<CardWall> cardWalls = new ArrayList<>(10);
    LinearLayoutManager layoutManagerForCards;
    Gson gson;
    String urlPicture = null;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        recyclerView = findViewById(R.id.recycler_list);
        layoutManagerForCards = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManagerForCards);
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();


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
                                System.out.println(response.json.toString());
                                WallInfo wallInfo = gson.fromJson(response.json.toString(), WallInfo.class);
                                List<Item> itemList = wallInfo.getResponse().getItems();

                                for (int i = 0; i < itemList.size(); i++) {

                                    Item itemPost = itemList.get(i);
                                    String urlIcon = groupInfo.getResponse().get(0).getPhoto50();
                                    String countLike = itemPost.getLikes().getCount().toString();

                                    try {
                                        urlPicture = itemPost.getAttachments().get(0).getPhoto().getPhoto_size();
                                        cardWalls.add(new CardWall(groupInfo.getResponse().get(0).getName(), urlIcon, itemPost.getText(), urlPicture, countLike));
                                        System.out.println(itemPost.getText());
                                    } catch (NullPointerException e) {
                                        cardWalls.add(new CardWall(groupInfo.getResponse().get(0).getName(), urlIcon, itemPost.getText(), countLike));
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(cardWalls);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

