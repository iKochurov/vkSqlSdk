package com.htccs.android.vkmusic.database.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.htccs.android.vkmusic.database.PostDataBaseHelper;
import com.htccs.android.vkmusic.database.VkContract;
import com.htccs.android.vkmusic.database.view.DataBaseView;
import com.htccs.android.vkmusic.wallgroup.models.CardWall;

import java.util.ArrayList;

public class DataBasePresenterImpl implements DataBasePresenter {

    private DataBaseView dataBaseView;
    private PostDataBaseHelper baseHelper;
    private ArrayList<CardWall> cardDataBase = new ArrayList<>();

    public DataBasePresenterImpl(DataBaseView dataBaseView, Context context) {
        this.dataBaseView = dataBaseView;
        baseHelper = new PostDataBaseHelper(context);
    }

    @Override
    public void showAllPost() {
        showDataBase();
    }

    @Override
    public void showMaxLikePost() {
        SQLiteDatabase db = baseHelper.getReadableDatabase();

        Cursor cursor = db.query(VkContract.PostTable.TABLE_NAME,
                new String[]{"*"},
                VkContract.PostTable.COLUMN_LIKE + " = " + "(SELECT MAX(" + VkContract.PostTable.COLUMN_LIKE + ") FROM " + VkContract.PostTable.TABLE_NAME + ")",
                null,
                null,
                null,
                null);

        try {
            int idColumnIndex = cursor.getColumnIndex(VkContract.PostTable._ID);
            int nameColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_NAME_GROUP);
            int iconColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_ICON);
            int dateColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_DATE);
            int textColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_TEXT);
            int likeColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_LIKE);
            int repostColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_REPOST);
            int imageColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_IMAGE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentIcon = cursor.getString(iconColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentText = cursor.getString(textColumnIndex);
                String currentLike = String.valueOf(cursor.getInt(likeColumnIndex));
                String currentRepost = cursor.getString(repostColumnIndex);
                String currentImage = cursor.getString(imageColumnIndex);

                if (!currentImage.equals("0")) {
                    cardDataBase.add(new CardWall(currentName, currentIcon, currentText, currentImage, currentLike, currentRepost, currentDate, null));
                } else {
                    cardDataBase.add(new CardWall(currentName, currentIcon, currentText, currentLike, currentRepost, currentDate));
                }
            }
        } finally {
            cursor.close();
            dataBaseView.populateRecycler(cardDataBase);
        }
    }

    private void showDataBase() {
        SQLiteDatabase db = baseHelper.getReadableDatabase();

        Cursor cursor = db.query(
                VkContract.PostTable.TABLE_NAME,
                new String[]{"*"},
                null,
                null,
                null,
                null,
                null);

        try {
            int idColumnIndex = cursor.getColumnIndex(VkContract.PostTable._ID);
            int nameColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_NAME_GROUP);
            int iconColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_ICON);
            int dateColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_DATE);
            int textColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_TEXT);
            int likeColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_LIKE);
            int repostColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_REPOST);
            int imageColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_IMAGE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentIcon = cursor.getString(iconColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentText = cursor.getString(textColumnIndex);
                String currentLike = cursor.getString(likeColumnIndex);
                String currentRepost = cursor.getString(repostColumnIndex);
                String currentImage = cursor.getString(imageColumnIndex);

                if (!currentImage.equals("0")) {
                    cardDataBase.add(new CardWall(currentName, currentIcon, currentText, currentImage, currentLike, currentRepost, currentDate, null));
                } else {
                    cardDataBase.add(new CardWall(currentName, currentIcon, currentText, currentLike, currentRepost, currentDate));
                }
            }
        } finally {
            cursor.close();
            dataBaseView.populateRecycler(cardDataBase);
        }
    }
}
