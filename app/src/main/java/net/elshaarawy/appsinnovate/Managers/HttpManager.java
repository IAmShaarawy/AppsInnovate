package net.elshaarawy.appsinnovate.Managers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by elshaarawy on 25-Dec-16.
 */

public class HttpManager {
    private static HttpManager httpManager = new HttpManager();
    private final static String TAG = "CONNECTION";

    public static HttpManager getInstance(){
        return httpManager;
    }
    public  String get(URL url) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {

            connection = (HttpURLConnection) url.openConnection();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "/n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null && reader != null) {
                connection.disconnect();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "disconnected");
            }
        }
    }

    //Implementation of other Http Functions Post, Put, and Delete
}
