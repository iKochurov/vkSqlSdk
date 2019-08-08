package com.htccs.android.vkmusic;

public class CardWall {
    public String cardTitle;
    public String cardUrlIcon;
    public String cardText;
    public String cardUrlPicture;
    public String cardLike;

    CardWall(String nameGroup, String urlIconGroup, String textGroup,String urlPictureGroup, String cardLike){
        this.cardTitle = nameGroup;
        this.cardText = textGroup;
        this.cardUrlIcon = urlIconGroup;
        this.cardUrlPicture = urlPictureGroup;
        this.cardLike = cardLike;
    }

    CardWall(String nameGroup, String urlIconGroup, String textGroup, String cardLike){
        this.cardTitle = nameGroup;
        this.cardText = textGroup;
        this.cardUrlIcon = urlIconGroup;
        this.cardLike = cardLike;
    }
}
