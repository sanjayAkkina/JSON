package com.example.android.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Thacher on 10/12/2017.
 */

public class GetImageUrlsAsync extends AsyncTask<String, Void, String[]> {
    MainActivity activity;
    Bitmap bitmap;
    HttpURLConnection connection;
    String[] result;

    public GetImageUrlsAsync(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String[] doInBackground(String... strings) {

        try {
            System.out.println("Attempting to retrieve image URLs from " + strings[0]);
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Connection successful. Retrieving URLs...");
                String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                JSONObject root =  new JSONObject(json); //Represents object which categories is in
                System.out.println("JSONObject root good");
                JSONArray urls = root.getJSONArray("urls");
                result = new String[urls.length()];
                System.out.println("Found " + urls.length() + " URLs");
                for (int i = 0; i < urls.length(); i++) {
                    String currentUrl = urls.get(i).toString();

                    Log.d("demo", String.valueOf(currentUrl));

                    result[i] = currentUrl;
                }
                return result;
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String[] result){
        super.onPostExecute(result);
        activity.handleImageUrls(result);
    }
}
