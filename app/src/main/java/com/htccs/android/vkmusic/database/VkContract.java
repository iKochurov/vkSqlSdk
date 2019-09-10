package com.htccs.android.vkmusic.database;

import android.provider.BaseColumns;

public final class VkContract {

    private VkContract() {
    }

    public static final class PostTable implements BaseColumns {
        public final static String TABLE_NAME = "posts";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ID = "idPost";
        public final static String COLUMN_NAME_GROUP = "groupPost";
        public final static String COLUMN_TEXT = "textPost";
        public final static String COLUMN_LIKE = "likePost";
        public final static String COLUMN_REPOST = "repostPost";
        public final static String COLUMN_IMAGE = "imagePost";
        public final static String COLUMN_DATE = "datePost";
        public final static String COLUMN_ICON = "iconPost";

    }
}
