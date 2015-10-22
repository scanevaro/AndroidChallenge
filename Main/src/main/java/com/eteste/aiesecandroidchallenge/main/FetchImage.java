package com.eteste.aiesecandroidchallenge.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by scanevaro on 22/10/2015.
 */
public class FetchImage extends AsyncTask<String, Void, String> {
    Bitmap image;
    ImageView iv;

    public FetchImage(ImageView iv) {
        this.iv = iv;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String url = strings[0];
            InputStream in = new URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        iv.setImageBitmap(image);
    }
}