package com.htccs.android.vkmusic.wallgroup.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.wallgroup.WallPictureClickListener;
import com.htccs.android.vkmusic.wallgroup.models.CardWall;

import java.util.ArrayList;
import java.util.List;

public class WallViewAdapter extends RecyclerView.Adapter<WallViewAdapter.CardWallHolder> {

    private RequestManager glide;
    private List<CardWall> cardWalls;
    private WallPictureClickListener listener;

    public WallViewAdapter(WallPictureClickListener listener, Context context) {
        cardWalls = new ArrayList<>();
        glide = Glide.with(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardWallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardWall = (CardView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        return new CardWallHolder(cardWall);
    }

    @Override
    public void onBindViewHolder(@NonNull CardWallHolder holder, int position) {
        holder.populate(cardWalls.get(position));
    }

    @Override
    public int getItemCount() {
        return cardWalls.size();
    }

    public void setCardWalls(List cardWalls) {
        this.cardWalls.clear();
        this.cardWalls.addAll(cardWalls);
    }

    class CardWallHolder extends RecyclerView.ViewHolder {

        private TextView nameGroup;
        private ImageView iconGroup;
        private TextView textPost;
        private ImageView picturePost;
        private TextView cardLike;
        private TextView cardRepost;
        private TextView cardDate;
        private String urlPicture;
        private TextView commentPost;

        CardWallHolder(@NonNull final View itemView) {
            super(itemView);
            nameGroup = itemView.findViewById(R.id.name_group);
            textPost = itemView.findViewById(R.id.person_text_group);
            iconGroup = itemView.findViewById(R.id.icon_group);
            picturePost = itemView.findViewById(R.id.picture_post);
            cardLike = itemView.findViewById(R.id.count_like);
            cardRepost = itemView.findViewById(R.id.count_repost);
            cardDate = itemView.findViewById(R.id.date_post);
            commentPost = itemView.findViewById(R.id.count_comments);

            picturePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPictureClick(urlPicture);
                }
            });
        }

        void populate(CardWall cardWall) {
            nameGroup.setText(cardWall.cardTitle);
            textPost.setText(cardWall.cardText);
            populateIcon(cardWall);
            populatePicture(cardWall);
            cardLike.setText(cardWall.cardLike);
            cardRepost.setText(cardWall.cardRepost);
            cardDate.setText(cardWall.cardDate);
            urlPicture = cardWall.cardMaxPicture;
            commentPost.setText(cardWall.cardComment);
        }

        private void populateIcon(CardWall cardWall) {

            glide
                    .load(cardWall.cardUrlIcon)
                    .into(iconGroup);
        }

        private void populatePicture(CardWall cardWall) {

            glide
                    .load(cardWall.cardUrlPicture)
                    .into(picturePost);
        }
    }
}