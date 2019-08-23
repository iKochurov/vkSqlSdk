package com.htccs.android.vkmusic.picturewall.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.htccs.android.vkmusic.R;
import com.htccs.android.vkmusic.picturewall.presenter.PicturePresenter;

public class PictureViewImpl implements PictureView {

    private RequestManager glide;
    public ImageView imageView;

    public PictureViewImpl(View view, String urlPicture) {
        glide = Glide.with(view.getContext());
        imageView = view.findViewById(R.id.wall_picture);
        showPicture(imageView, urlPicture);
    }

    private void showPicture(ImageView imageView, String url) {
        glide
                .load(url)
                .into(imageView);
    }

    public void successfullySaved(Context context) {
        Toast toast = Toast.makeText(context,
                "Успешно!", Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void showAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Вход")
                .setMessage("Отказано!")
                .setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
