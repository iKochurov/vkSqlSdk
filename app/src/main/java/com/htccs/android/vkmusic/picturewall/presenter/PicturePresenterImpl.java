package com.htccs.android.vkmusic.picturewall.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.picturewall.view.PictureViewImpl;

import java.io.File;
import java.io.FileOutputStream;

public class PicturePresenterImpl implements PicturePresenter {

    private static final int REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 1234;

    private PictureViewImpl pictureView;
    private Context context;
    private Fragment fragment;

    public PicturePresenterImpl(PictureViewImpl pictureView, Context context, Fragment fragment) {
        this.pictureView = pictureView;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public void savePicture() {
        ImageView imageView = pictureView.imageView;

        if (checkPermission(context)) {
            try {
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                FileOutputStream outStream = null;
                File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File directory = new File(sdCard.getAbsolutePath());
                directory.mkdirs();
                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(directory, fileName);
                outStream = new FileOutputStream(outFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                MediaStore.Images.Media.insertImage(context.getContentResolver(), outFile.getAbsolutePath(), outFile.getName(), outFile.getName());
                outStream.flush();
                outStream.close();

                pictureView.successfullySaved(context);
            } catch (Exception e) {
                Log.d("EXCEPTION ", e.getMessage());
            }
        }
    }

    private boolean checkPermission(Context context) {
        int permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            fragment.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE);
            return false;
        }
    }
}
