package com.example.taxitestapp.network;

/**
 * Created by mihai on 2/17/2018.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mixacya on 3/21/17.
 */

public class ConnectionHelper {

    public static String sendGetResponce(String reference) throws IOException {
        URL url = new URL(reference);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        is.close();
        return builder.toString();
    }

}
