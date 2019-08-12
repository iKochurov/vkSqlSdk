package com.htccs.android.vkmusic.ListGroups;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.htccs.android.vkmusic.FragmentView;
import com.htccs.android.vkmusic.IFragmentPresenter;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.WallGroup.WallGroupFragment;

import java.util.List;

import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class GroupViewAdapter extends RecyclerView.Adapter<GroupViewAdapter.CardGroupHolder> implements IFragmentPresenter {

    List<CardGroup> cardGroups;

    private static FragmentView fragmentView;

    @NonNull
    @Override
    public CardGroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardWall = (CardView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_groups, parent, false);
        return new CardGroupHolder(cardWall);
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

    @Override
    public void setFragment(FragmentView fragmentView) {
        this.fragmentView = fragmentView;
    }

    public static class CardGroupHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameGroup;
        ImageView iconGroup;
        String numberGroup;

        public CardGroupHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.group_list);
            nameGroup = itemView.findViewById(R.id.name_group_list);
            iconGroup = itemView.findViewById(R.id.icon_group_list);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentView.setData(new WallGroupFragment(numberGroup));
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

    GroupViewAdapter(List cardGroups) {
        this.cardGroups = cardGroups;
    }

    GroupViewAdapter() {
        this.cardGroups = cardGroups;
    }

    public void setCardGroups(List cardGroups) {
        this.cardGroups = cardGroups;
    }
}

