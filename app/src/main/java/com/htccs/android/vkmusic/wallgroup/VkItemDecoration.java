package com.htccs.android.vkmusic.wallgroup;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class VkItemDecoration extends RecyclerView.ItemDecoration {

    private final static Integer INDENT = 8;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = INDENT;
        outRect.bottom = INDENT;
    }
}