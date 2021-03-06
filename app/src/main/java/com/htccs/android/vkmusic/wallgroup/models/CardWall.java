package com.htccs.android.vkmusic.wallgroup.models;

public class CardWall {

    public String cardTitle;
    public String cardUrlIcon;
    public String cardText;
    public String cardUrlPicture;
    public String cardLike;
    public String cardRepost;
    public String cardDate;
    public String cardMaxPicture;
    public String cardComment;

    public CardWall(String nameGroup, String urlIconGroup, String textGroup, String urlPictureGroup, String cardLike, String cardRepost, String cardDate, String cardMaxPicture, String cardComment) {
        this.cardTitle = nameGroup;
        this.cardText = textGroup;
        this.cardUrlIcon = urlIconGroup;
        this.cardUrlPicture = urlPictureGroup;
        this.cardLike = cardLike;
        this.cardRepost = cardRepost;
        this.cardDate = cardDate;
        this.cardMaxPicture = cardMaxPicture;
        this.cardComment = cardComment;
    }

    public CardWall(String nameGroup, String urlIconGroup, String textGroup, String cardLike, String cardRepost, String cardDate, String cardComment) {
        this.cardTitle = nameGroup;
        this.cardText = textGroup;
        this.cardUrlIcon = urlIconGroup;
        this.cardLike = cardLike;
        this.cardRepost = cardRepost;
        this.cardDate = cardDate;
        this.cardComment = cardComment;
    }
}
