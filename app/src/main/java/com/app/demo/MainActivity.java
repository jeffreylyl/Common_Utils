package com.app.demo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.app.utils.L;
import com.app.utils.file.FileUtils;
import com.app.utils.image.UImageFiles;
import com.app.utils.file.UriParse;
import com.app.utils.image.ImageRotateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private final String[] requiredPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    Context context;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        context = this;
        requestPermission();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                requiredPermissions,
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                String path;
//                L.e("#####1 " + MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                L.e(path = UriParse.getFilePathWithUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this));
//                L.e("#####3 " + Uri.fromFile(new File(path)));
//                L.e("#####4 " + Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()));
                Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                final Bitmap b2 = ImageRotateUtil.rotateBitmapByDegree(b1, 90);

                img.setImageBitmap(null);
                img.setImageBitmap(b2);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri uri = null;
                        File f = null;
                        UImageFiles.writeToFile(b2, uri = Uri.fromFile(f = FileUtils.getFileCacheDir(context, new File("1"))));
                        try {
                            L.e("######## uri1 " + UriParse.getTempUri(context, f).getScheme());
                            L.e("######## uri2 " + UriParse.getTempUri(context, f).getPath());
                            L.e("######## uri3 " + UriParse.parseOwnUri(context, UriParse.getTempUri(context, f)));
                            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(UriParse.getFileWithUri(uri, MainActivity.this)));
                            img.setImageBitmap(null);
                            img.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, 100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
