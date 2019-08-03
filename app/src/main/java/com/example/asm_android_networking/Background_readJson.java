package com.example.asm_android_networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_android_networking.Adapter.Recyclerview_adapter;
import com.example.asm_android_networking.Repository.Manga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Background_readJson extends AsyncTask<JSONArray, Manga, ArrayList<Manga>> {
    Context context;
    JSONArray jsonArray;
    Recyclerview_adapter adapter;
    RecyclerView recyclerview_home;
    ProgressDialog  progressDialog;
    ArrayList<Manga>mangas = new ArrayList<>();
    String _id;
    String name;
    String tacgia;
    String img;
    int status;

    public Background_readJson(Context context, JSONArray jsonArray, RecyclerView recyclerview_home) {
        this.context = context;
        this.jsonArray = jsonArray;
        this.recyclerview_home = recyclerview_home;
    }



    @Override
    protected ArrayList<Manga> doInBackground(JSONArray... jsonArrays){

        JSONObject jsonObject = new JSONObject();
        for (int k=0;k<jsonArray.length();k++){
            try {
               // Log.d("asdsad", String.valueOf(jsonArray.length()));
                jsonObject = jsonArray.getJSONObject(k);
                _id = jsonObject.getString("_id");
                img = jsonObject.getString("img");
                name = jsonObject.getString("name");
                status = jsonObject.getInt("status");
                tacgia = jsonObject.getString("tacgia");
                JSONArray arrcares = jsonObject.getJSONArray("category");
                String[] category = new String[arrcares.length()];
                for(int i=0;i < arrcares.length();i++) {
                    category[i] = arrcares.getString(i);
                }
                Manga manga = new Manga();
                manga.setId(_id);
                manga.setImg(img);
                manga.setName(name);
                manga.setStatus(status);
                manga.setCategori(category);
                manga.setTacgia(tacgia);

                mangas.add(manga);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mangas;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Manga... values) {
        super.onProgressUpdate(values);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Manga> mangas) {
        super.onPostExecute(mangas);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
     //   adapter = new Recyclerview_adapter(context,mangas);
        recyclerview_home.setAdapter(adapter);
    }
}
