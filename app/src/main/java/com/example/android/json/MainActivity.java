package com.example.android.json;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.util.ArrayList;

import org.apache.commons.io.*;


public class MainActivity extends AppCompatActivity {

    TextView    txtKeyword;
    Button      btnSearch;
    ImageView   imgMain;

    ImageButton btnPrev;
    ImageButton btnNext;

    ArrayList<String> keywordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKeyword  = (TextView)findViewById(R.id.txtKeyword);
        btnSearch   = (Button)findViewById(R.id.btnSearch);
        imgMain     = (ImageView)findViewById(R.id.imgMain);
        btnPrev     = (ImageButton)findViewById(R.id.btnPrev);
        btnNext     = (ImageButton)findViewById(R.id.btnNext);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected())
                {
                    Toast.makeText(MainActivity.this, "Internet Connected", Toast.LENGTH_SHORT).show();
                    new GetDataAsync(MainActivity.this).execute("http://dev.theappsdr.com/apis/photos/keywords.php?format=json"); //Insert URL
                } else {
                    Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    void handleResult(ArrayList<String> Result) {
        Log.d("demo", Result.toString());
    }

    void handleImage(Bitmap bitmap) {
        imgMain.setImageBitmap(bitmap);
    }

}