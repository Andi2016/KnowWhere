package com.android.andi.knowwhere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestTest extends AppCompatActivity {
    private static String baseUrl = "https://knowwhere-c8f53.firebaseio.com/";
    private static CloseableHttpClient httpclient;
    TextView _rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_test);

        _rest=findViewById(R.id.rest_test);
        try {
            Log.e("sdfsdf",getFriends("Joe"));
            _rest.setText(getFriends("Joe"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFriends(String username) throws Exception {
        String res = "", lst;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpRequest = new HttpGet(baseUrl + "user/" + username + "/friends.json");
            httpRequest.addHeader("accept", "application/json");

            CloseableHttpResponse response = httpclient.execute(httpRequest);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status >= 200 && status < 300) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            lst = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            response.close();
        } finally {
            httpclient.close();
        }
        String[] tokens = lst.substring(1, lst.length() - 1).split(",");
        for (int i = 0; i < tokens.length; i += 1) {
            String friendname = tokens[i].substring(1, tokens[i].length() - 4);
            res += friendname + ",";
        }
        return res.substring(0, res.length() - 1);

    }
}
