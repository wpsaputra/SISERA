package com.virtualbox.torchick.rog.Activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.virtualbox.torchick.rog.Model.Publikasi;
import com.virtualbox.torchick.rog.Model.PublikasiWebApi;
import com.virtualbox.torchick.rog.Network.ApiClient;
import com.virtualbox.torchick.rog.Network.ApiInterface;
import com.virtualbox.torchick.rog.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublikasiActivity extends AppCompatActivity {
    TextView detail_judul, detail_publikasi, detail_abstrak;
    ImageView detail_cover;
    Button downloadButton;
    Publikasi p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publikasi);

        detail_judul = (TextView) findViewById(R.id.detail_judul);
        detail_publikasi = (TextView) findViewById(R.id.detail_publikasi);
        detail_abstrak = (TextView) findViewById(R.id.detail_abstrak);
        detail_cover = (ImageView) findViewById(R.id.detail_cover);
        downloadButton = (Button) findViewById(R.id.downloadButton);

        Intent i = getIntent();
        p = i.getParcelableExtra("publikasi");
//        changeJson();

        detail_judul.setText(p.getJudul_ind());
        detail_publikasi.setText(p.getNo_publikasi());
//        detail_abstrak.setText(stripHtml(p.getAbstrak_ind()));
//        detail_abstrak.setText(json);
        getAbstrak(p);

        if (!TextUtils.isEmpty(p.getFile_cover())) {
//            Glide.with(this).load("https://sultra.bps.go.id/website/cover_publikasi/"+p.getFile_cover())
            Glide.with(this).load(p.getFile_cover())
                    .placeholder(R.drawable.not_available)
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(detail_cover);
            detail_cover.setColorFilter(null);
        }

//        downloadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(haveStoragePermission()){
//                    downloadPdf();
//                };
//            }
//        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(p.getFile_pdf()));
                startActivity(browserIntent);
            }
        });
    }

    private String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if(html==null){
                return "";
            }else{
                return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
            }
        } else {
            if(html==null){
                return "";
            }else{
                return Html.fromHtml(html).toString();
            }
        }
    }

    private void downloadPdf(){
        String url = "https://sultra.bps.go.id/website/pdf_publikasi/"+p.getFile_pdf();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Publikasi BPS");
        request.setTitle(p.getFile_pdf());
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, p.getFile_pdf());

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    private boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }

    private void getAbstrak(Publikasi p){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<JsonObject> call = apiService.getPublikasiWebApiDetail(p.getHit());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject>call, Response<JsonObject> response) {
                JsonObject movies = response.body();
                try{
                    Gson gson = new Gson();
                    String json = movies.get("data").getAsJsonObject().get("abstract").toString();
                    Log.d("PUBLIKASI", json);
                    detail_abstrak.setText(json);

                }catch(Exception ex){
                    Log.d("Error", ex.getMessage());
                }

            }

            @Override
            public void onFailure(Call<JsonObject>call, Throwable t) {
                // Log error here since request failed
                Log.e("GAGAL", t.toString());
            }
        });


    }

}
