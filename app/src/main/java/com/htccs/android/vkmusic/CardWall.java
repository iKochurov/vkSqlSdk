package com.htccs.android.vkmusic;

public class CardWall {
    String cardTitle;
    String cardUrlIcon;
    String cardText;
    String cardUrlPicture;
    String cardLike;
    String cardRepost;

    CardWall(String nameGroup, String urlIconGroup, String textGroup, String urlPictureGroup, String cardLike, String cardRepost) {
        this.cardTitle = nameGroup;
        this.cardText = textGroup;
        this.cardUrlIcon = urlIconGroup;
        this.cardUrlPicture = urlPictureGroup;
        this.cardLike = cardLike;
        this.cardRepost = cardRepost;

    }

    CardWall(String nameGroup, String urlIconGroup, String textGroup, String cardLike, String cardRepost) {
        this.cardTitle = nameGroup;
        this.cardText = textGroup;
        this.cardUrlIcon = urlIconGroup;
        this.cardLike = cardLike;
        this.cardRepost = cardRepost;
    }
}
