package com.htccs.android.vkmusic.database.databasewall.viewbd;

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

import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter extends RecyclerView.Adapter<DataBaseAdapter.DataBaseHolder> {

    private RequestManager glide;
    private List<CardWall> cardWalls;

    public DataBaseAdapter(Context context) {
        cardWalls = new ArrayList<>();
        glide = Glide.with(context);
    }

    @NonNull
    @Override
    public DataBaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardWall = (CardView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        return new DataBaseHolder(cardWall);
    }

    @Override
    public void onBindViewHolder(@NonNull DataBaseHolder holder, int position) {
        holder.populate(cardWalls.get(position));
    }

    @Override
    public int getItemCount() {
        return cardWalls.size();
    }

    public void setCardWalls(List cardWalls) {
        this.cardWalls.clear();
        this.cardWalls.addAll(cardWalls);
        notifyDataSetChanged();
    }

    class DataBaseHolder extends RecyclerView.ViewHolder {

        private TextView nameGroup;
        private ImageView iconGroup;
        private TextView textPost;
        private ImageView picturePost;
        private TextView cardLike;
        private TextView cardRepost;
        private TextView cardDate;
        private String urlPicture;
        private TextView cardComment;

        DataBaseHolder(@NonNull final View itemView) {
            super(itemView);
            nameGroup = itemView.findViewById(R.id.name_group);
            textPost = itemView.findViewById(R.id.person_text_group);
            iconGroup = itemView.findViewById(R.id.icon_group);
            picturePost = itemView.findViewById(R.id.picture_post);
            cardLike = itemView.findViewById(R.id.count_like);
            cardRepost = itemView.findViewById(R.id.count_repost);
            cardDate = itemView.findViewById(R.id.date_post);
            cardComment = itemView.findViewById(R.id.count_comments);

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
            cardComment.setText(cardWall.cardComment);

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
