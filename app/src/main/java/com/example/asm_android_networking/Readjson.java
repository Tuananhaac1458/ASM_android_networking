package com.example.asm_android_networking;

import android.content.Context;

import com.example.asm_android_networking.Repository.Manga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Readjson {

    public static ArrayList<Manga> readMangaJson(Context context, JSONArray jsonArray)throws IOException, JSONException{
        JSONObject jsonObject = new JSONObject();
        ArrayList<Manga> mangas = new ArrayList<>();
        for (int k=0;k<jsonArray.length();k++){
            jsonObject = jsonArray.getJSONObject(k);

            String _id = jsonObject.getString("_id");
            String img = jsonObject.getString("img");
            String name = jsonObject.getString("name");
            int status = jsonObject.getInt("status");
            String tacgia = jsonObject.getString("tacgia");

            JSONArray arrcares = jsonObject.getJSONArray("category");
            String[] category = new String[arrcares.length()];
            for(int i=0;i < arrcares.length();i++) {
                category[i] = arrcares.getString(i);
            }

            Manga manga = new Manga();
            mangas = new ArrayList<>();
            manga.setId(_id);
            manga.setImg(img);
            manga.setName(name);
            manga.setStatus(status);
            manga.setCategori(category);
            manga.setTacgia(tacgia);

            mangas.add(manga);
        }
        return mangas;
    };
}
