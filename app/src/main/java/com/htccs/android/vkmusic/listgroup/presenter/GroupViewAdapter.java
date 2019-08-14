package com.htccs.android.vkmusic.listgroup.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.listgroup.GroupItemClickListener;
import com.htccs.android.vkmusic.listgroup.models.CardGroup;

import java.util.List;

import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class GroupViewAdapter extends RecyclerView.Adapter<GroupViewAdapter.CardGroupHolder> {

    private List<CardGroup> cardGroups;

    private GroupItemClickListener listener;

    public GroupViewAdapter(GroupItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardGroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardWall = (CardView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_groups, parent, false);
        CardGroupHolder cardGroupHolder = new CardGroupHolder(cardWall);
        cardGroupHolder.setListener(listener);
        return cardGroupHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardGroupHolder holder, int position) {
        holder.nameGroup.setText(cardGroups.get(position).nameGroup);
        holder.populateIcon(cardGroups.get(position));
        holder.numberGroup = cardGroups.get(position).numberGroup;
    }

    @Override
    public int getItemCount() {
        return cardGroups.size();
    }


    public static class CardGroupHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameGroup;
        ImageView iconGroup;
        String numberGroup;
        GroupItemClickListener listener;

        public void setListener(GroupItemClickListener listener) {
            this.listener = listener;
        }

        public CardGroupHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.group_list);
            nameGroup = itemView.findViewById(R.id.name_group_list);
            iconGroup = itemView.findViewById(R.id.icon_group_list);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onGroupItemClicked(numberGroup);
                }
            });
        }

        public void populateIcon(CardGroup cardGroup) {
            Glide
                    .with(getApplicationContext())
                    .load(cardGroup.urlIconGroup)
                    .into(iconGroup);
        }
    }

    public void setCardWalls(List cardGroups) {
        this.cardGroups = cardGroups;
    }
}

