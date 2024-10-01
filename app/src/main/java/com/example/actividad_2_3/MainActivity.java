package com.example.actividad_2_3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView superFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        superFoto = findViewById(R.id.mImageView);
        Button downloadButton = findViewById(R.id.downloadButton);


        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = loadImageFromNetwork("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSDiNatrWCuOlQr5ehqf7q3xJc_BWJm4dR6g&s");
                        superFoto.post(new Runnable() {
                            @Override
                            public void run() {
                                superFoto.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        });
    }


    private Bitmap loadImageFromNetwork(String url) {
        Bitmap bitmap = null;
        try {
            java.net.URL imageUrl = new java.net.URL(url);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
