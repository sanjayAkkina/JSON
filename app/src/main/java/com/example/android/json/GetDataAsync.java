package com.example.android.json;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Sanjay on 10/11/17.
 */


public class GetDataAsync extends AsyncTask<String, Void, ArrayList<String>> {
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        HttpURLConnection connection = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();


            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF-8");
                JSONObject root =  new JSONObject(json); //Represents object which categories is in
                JSONArray categories = root.getJSONArray("categories");
                for (int i = 0; i < categories.length(); i++)
                {
                    String keywordSearch = categories.get(i).toString();

                    Log.d("demo", String.valueOf(keywordSearch));

                    result.add(keywordSearch);
                }
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        if (result.size() > 0) {
            Log.d("demo", result.toString());
        } else {
            Log.d("demo", "null result");
        }
    }
}


