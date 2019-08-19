package com.htccs.android.vkmusic.wallgroup.presenter;

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
import com.htccs.android.vkmusic.wallgroup.models.CardWall;

import java.util.List;

public class WallViewAdapter extends RecyclerView.Adapter<WallViewAdapter.CardWallHolder> {

    private RequestManager glide;
    private List<CardWall> cardWalls;

    public WallViewAdapter(Context context) {

        glide = Glide.with(context);
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

    class CardWallHolder extends RecyclerView.ViewHolder {

        private TextView nameGroup;
        private ImageView iconGroup;
        private TextView textPost;
        private ImageView picturePost;
        private TextView cardLike;
        private TextView cardRepost;

        CardWallHolder(@NonNull final View itemView) {
            super(itemView);
            nameGroup = itemView.findViewById(R.id.name_group);
            textPost = itemView.findViewById(R.id.person_text_group);
            iconGroup = itemView.findViewById(R.id.icon_group);
            picturePost = itemView.findViewById(R.id.picture_post);
            cardLike = itemView.findViewById(R.id.count_like);
            cardRepost = itemView.findViewById(R.id.count_repost);
        }

        void populate(CardWall cardWall) {
            nameGroup.setText(cardWall.cardTitle);
            textPost.setText(cardWall.cardText);
            populateIcon(cardWall);
            populatePicture(cardWall);
            cardLike.setText(cardWall.cardLike);
            cardRepost.setText(cardWall.cardRepost);
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

    public void setCardWalls(List cardWalls) {
        this.cardWalls = cardWalls;
    }
}
