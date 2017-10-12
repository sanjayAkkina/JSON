package com.example.android.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by thach on 10/12/2017.
 */

public class GetImageAsync extends AsyncTask<String, Void, String> {
    MainActivity activity;
    Bitmap bitmap;

    public GetImageAsync(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection;
        bitmap = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (Exception e) {
            //Handle the exceptions
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        activity.handleResultImage(bitmap);
    }
}

