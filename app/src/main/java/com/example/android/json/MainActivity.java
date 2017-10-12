package com.example.android.json;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
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

    ProgressDialog progress;

    String finalURL = "http://dev.theappsdr.com/apis/photos/index.php?type=json&keyword=";
    String[] urlList;


    ArrayList<String> keywordList = new ArrayList<>();
    int selectedOption = 0;
    CharSequence options[];
    ProgressDialog loadingBar;
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKeyword  = (TextView)findViewById(R.id.txtKeyword);
        btnSearch   = (Button)findViewById(R.id.btnSearch);
        imgMain     = (ImageView)findViewById(R.id.imgMain);
        btnPrev     = (ImageButton)findViewById(R.id.btnPrev);
        btnNext     = (ImageButton)findViewById(R.id.btnNext);

        progress = new ProgressDialog(MainActivity.this);
        progress.setCancelable(false);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected())
                {
                    Toast.makeText(MainActivity.this, "Internet Connected", Toast.LENGTH_SHORT).show();
                    progress.setTitle("Loading Dictionary");
                    progress.show();
                    new GetDataAsync(MainActivity.this).execute("http://dev.theappsdr.com/apis/photos/keywords.php?format=json"); //Insert URL

                } else {
                    Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImage<urlList.length-1)
                {
                    currentImage++;
                }
                else
                {
                    currentImage = 0;
                }
                new GetImageAsync(MainActivity.this).execute(urlList[currentImage]);

                progress.setTitle("Loading Image");
                progress.show();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImage>0)
                {
                    currentImage--;
                }
                else
                {
                    currentImage = urlList.length-1;
                }
                new GetImageAsync(MainActivity.this).execute(urlList[currentImage]);

                progress.setTitle("Loading Image");
                progress.show();
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

    void handleResult(final ArrayList<String> Result) {
        progress.dismiss();
        Log.d("demo", Result.toString());

        options = new CharSequence[Result.size()];

        for(int i = 1; i<=Result.size(); i++)
        {
            options[i-1] = Result.get(i-1);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a keyword");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedOption = which;
                txtKeyword.setText(Result.get(selectedOption));
                progress.setTitle("Loading Image...");
                progress.show();
                new GetImageUrlsAsync(MainActivity.this).execute(finalURL + options[which].toString());
            }
        });
        builder.show();

    }

    void handleImageUrls(String[] urls) {
        urlList = new String[urls.length];

        for(int i = 0; i<urls.length; i++)
        {
            urlList[i] = urls[i];
        }

        progress.dismiss();
        new GetImageAsync(MainActivity.this).execute(urls[0]);

    }

    void handleResultImage(Bitmap bitmap) {
        imgMain.setImageBitmap(bitmap);
        progress.dismiss();
    }

}