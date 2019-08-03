package com.example.asm_android_networking.uitil;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class HttpHander {

    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";
    private HttpURLConnection urlConnection = null;

    public HttpHander() {
    }


    public JSONObject makeHttpRequest(String url, String method,
                                      Map<String, String> params) {

        try {
            Uri.Builder builder = new Uri.Builder();
            URL urlObj;
            String encodedParams = "";
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.appendQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            if (builder.build().getEncodedQuery() != null) {
                encodedParams = builder.build().getEncodedQuery();

            }
            if ("GET".equals(method)) {
                url = url + encodedParams;
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);


            } else {
                urlObj = new URL(url);
                urlConnection = (HttpURLConnection) urlObj.openConnection();
                urlConnection.setRequestMethod(method);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(encodedParams.getBytes().length));
                urlConnection.getOutputStream().write(encodedParams.getBytes());
            }
            //Connect to the server
            urlConnection.connect();
            //Read the response
            is = urlConnection.getInputStream();
            Log.d("xxxx", String.valueOf(is));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();

            String line;

            //Parse the response
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            //Convert the response to JSON Object
            jObj = new JSONObject(json);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } catch (Exception e) {
            Log.e("Exception", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jObj;

    }






























    public String makeServercall(String url) throws IOException {
        String res = null;
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            InputStream inputStream = new BufferedInputStream(con.getInputStream());
            res = converStreamtostring(inputStream);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("asd", "sendGet: "+ e.getMessage() );
        }
        return res;
    }

    private String converStreamtostring(InputStream inputStream) {
        BufferedReader red = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            while ((line = red.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
