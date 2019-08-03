package com.example.asm_android_networking.Login_Register;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_android_networking.Repository.User;
import com.example.asm_android_networking.uitil.HttpHander;
import com.example.asm_android_networking.uitil.RetrofitClient;
import com.example.asm_android_networking.uitil.SumString;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundTask_GET extends AsyncTask<Void, Void, Boolean> {
    Context context;
    Boolean status_Login;
    String request;
    String url = SumString.URLserver;
    String email;
    String pass;
    Boolean asd;
    private ListActivity activity;
    ProgressDialog progressDialog;
    JSONObject response;
    public BackgroundTask_GET(Context context, String email, String pass, Boolean status_Login) {
        this.context = context;
        this.status_Login = status_Login;
        this.email = email;
        this.pass = pass;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        progressDialog.setMessage("Loaing...");
//        progressDialog.show();
    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        Call call = RetrofitClient.getInstance().getApt().loginuser();
        Log.w("asd", "onResponse: " + call);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                JSONObject JSOn = (JSONObject) response.body();

                try {
                    boolean status = JSOn.getBoolean("status");
                    String mess = JSOn.getString("mes");
                    Log.w("asd", "onResponse: " +status);
                    if (status){
                         asd = true;
                        Log.d("sad", mess);
                    }else {
                        asd = false;
                        Log.d("sad", mess);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        return asd;
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
//        if (progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
            status_Login = aVoid;
     }


}
