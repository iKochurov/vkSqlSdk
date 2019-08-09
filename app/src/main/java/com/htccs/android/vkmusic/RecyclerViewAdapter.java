package com.htccs.android.vkmusic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardWallHolder> {

    List<CardWall> cardWalls;

    @NonNull
    @Override
    public CardWallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardWall = (CardView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        return new CardWallHolder(cardWall);
    }

    @Override
    public void onBindViewHolder(@NonNull CardWallHolder holder, int position) {
        holder.nameGroup.setText(cardWalls.get(position).cardTitle);
        holder.textPost.setText(cardWalls.get(position).cardText);
        holder.populateIcon(cardWalls.get(position));
        holder.populatePicture(cardWalls.get(position));
        holder.cardLike.setText(cardWalls.get(position).cardLike);
        holder.cardRepost.setText(cardWalls.get(position).cardRepost);

    }

    @Override
    public int getItemCount() {
        return cardWalls.size();
    }

    public static class CardWallHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameGroup;
        ImageView iconGroup;
        TextView textPost;
        ImageView picturePost;
        TextView cardLike;
        TextView cardRepost;

        public CardWallHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_list);
            nameGroup = itemView.findViewById(R.id.name_group);
            textPost = itemView.findViewById(R.id.person_text_group);
            iconGroup = itemView.findViewById(R.id.icon_group);
            picturePost = itemView.findViewById(R.id.picture_post);
            cardLike = itemView.findViewById(R.id.count_like);
            cardRepost = itemView.findViewById(R.id.count_repost);

        }

        public void populateIcon(CardWall cardWall){
            Glide
                    .with(getApplicationContext())
                    .load(cardWall.cardUrlIcon)
                    .into(iconGroup);
        }

        public void populatePicture(CardWall cardWall){
            Glide
                    .with(getApplicationContext())
                    .load(cardWall.cardUrlPicture)
                    .into(picturePost);
        }
    }

    RecyclerViewAdapter(List cardWalls) {
        this.cardWalls = cardWalls;
    }
}
