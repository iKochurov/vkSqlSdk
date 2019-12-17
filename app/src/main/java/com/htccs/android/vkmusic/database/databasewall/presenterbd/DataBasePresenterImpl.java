package com.htccs.android.vkmusic.database.databasewall.presenterbd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.htccs.android.vkmusic.database.PostDataBaseHelper;
import com.htccs.android.vkmusic.database.VkContract;
import com.htccs.android.vkmusic.database.databasewall.viewbd.DataBaseView;
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
    public void showAllPost(String groupId) {

        SQLiteDatabase db = baseHelper.getReadableDatabase();
        //SELECT * FROM posts WHERE idGroup = groupId
        Cursor cursor = db.query(
                VkContract.PostTable.TABLE_NAME,
                new String[]{"*"},
                VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId,
                null,
                null,
                null,
                null);

        showResult(cursor);
    }

    @Override
    public void showMaxLikePost(String groupId) {
        SQLiteDatabase db = baseHelper.getReadableDatabase();
        //SELECT * FROM posts WHERE idGroup = 40836944 AND likePost = (SELECT MAX(likePost) FROM posts WHERE idGroup = groupId)
        Cursor cursor = db.query(VkContract.PostTable.TABLE_NAME,
                new String[]{"*"},
                VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId
                        + " AND " + VkContract.PostTable.COLUMN_LIKE + " = " + "(SELECT MAX("
                        + VkContract.PostTable.COLUMN_LIKE + ") FROM "
                        + VkContract.PostTable.TABLE_NAME + " WHERE "
                        + VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId + ")",
                null,
                null,
                null,
                null);

        showResult(cursor);
    }

    @Override
    public void showMaxRepost(String groupId) {
        SQLiteDatabase db = baseHelper.getReadableDatabase();
        //SELECT * FROM posts WHERE idGroup = 40836944 AND repostPost = (SELECT MAX(repostPost) FROM posts WHERE idGroup = groupId)
        Cursor cursor = db.query(VkContract.PostTable.TABLE_NAME,
                new String[]{"*"},
                VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId
                        + " AND " + VkContract.PostTable.COLUMN_REPOST + " = " + "(SELECT MAX("
                        + VkContract.PostTable.COLUMN_REPOST + ") FROM "
                        + VkContract.PostTable.TABLE_NAME + " WHERE "
                        + VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId + ")",
                null,
                null,
                null,
                null);

        showResult(cursor);
    }

    @Override
    public void showMaxComment(String groupId) {
        SQLiteDatabase db = baseHelper.getReadableDatabase();
        //SELECT * FROM posts WHERE idGroup = 40836944 AND commentPost = (SELECT MAX(commentPost) FROM posts WHERE idGroup = groupId)
        Cursor cursor = db.query(VkContract.PostTable.TABLE_NAME,
                new String[]{"*"},
                VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId
                        + " AND " + VkContract.PostTable.COLUMN_COMMENT + " = " + "(SELECT MAX("
                        + VkContract.PostTable.COLUMN_COMMENT + ") FROM "
                        + VkContract.PostTable.TABLE_NAME + " WHERE "
                        + VkContract.PostTable.COLUMN_GROUP_ID + " = " + groupId + ")",
                null,
                null,
                null,
                null);

        showResult(cursor);
    }

    private void showResult(Cursor cursor) {
        try {
            int idColumnIndex = cursor.getColumnIndex(VkContract.PostTable._ID);
            int nameColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_NAME_GROUP);
            int iconColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_ICON);
            int dateColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_DATE);
            int textColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_TEXT);
            int likeColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_LIKE);
            int repostColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_REPOST);
            int imageColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_IMAGE);
            int commentsColumnIndex = cursor.getColumnIndex(VkContract.PostTable.COLUMN_COMMENT);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentIcon = cursor.getString(iconColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentText = cursor.getString(textColumnIndex);
                String currentLike = cursor.getString(likeColumnIndex);
                String currentRepost = cursor.getString(repostColumnIndex);
                String currentImage = cursor.getString(imageColumnIndex);
                String currentComment = cursor.getString(commentsColumnIndex);

                if (!currentImage.equals("0")) {
                    cardDataBase.add(new CardWall(currentName, currentIcon, currentText, currentImage, currentLike, currentRepost, currentDate, null, currentComment));
                } else {
                    cardDataBase.add(new CardWall(currentName, currentIcon, currentText, currentLike, currentRepost, currentDate, currentComment));
                }
            }
        } finally {
            cursor.close();
            dataBaseView.populateRecycler(cardDataBase);
            cardDataBase.clear();
        }
    }
}