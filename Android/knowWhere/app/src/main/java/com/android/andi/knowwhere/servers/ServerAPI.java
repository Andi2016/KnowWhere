package com.android.andi.knowwhere.servers;

import android.content.Context;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;

import com.android.andi.knowwhere.models.Coordinate;
import com.android.andi.knowwhere.models.Message;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BuildConfig;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Andi Xu on 4/4/18.
 */

public class ServerAPI {

    public static final String STATUS_MSG_OK = "OK";

    public static final int STATUS_OK = 200;
    public static final int STATUS_UNKNOWNERROR = -1;

    public static final String SERVER_BASE_URL = "http://143.215.114.174:8080";

    private static AsyncHttpClient client = null;
    private static PersistentCookieStore mCookieStore = null;

    public static void init(Context context) {
        client = null;
        client = new AsyncHttpClient();
        client.setUserAgent("Version " + BuildConfig.VERSION_NAME + " deviceID " + getDeviceId(context));
        if (mCookieStore == null) {
            mCookieStore = new PersistentCookieStore(context);
            client.setCookieStore(mCookieStore);
        }
    }

    private static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static void shutdown(Context context) {
        if (mCookieStore != null) {
            mCookieStore.clear();
            mCookieStore = null;
            mCookieStore = new PersistentCookieStore(context);
            client.setCookieStore(mCookieStore);
        }
    }

    public static PersistentCookieStore getCookieStore() {
        return mCookieStore;
    }

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (client == null)
            ServerAPI.init(context);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getAbsoulte(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (client == null)
            ServerAPI.init(context);
        client.get(url, params, responseHandler);
    }

    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (client == null)
            ServerAPI.init(context);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postJSON(Context context, String url, JSONObject data,
                                AsyncHttpResponseHandler responseHandler) {
        StringEntity entity;
        try {
            if (client == null) {
                ServerAPI.init(context);
            }
            entity = new StringEntity(data.toString());
            entity.setContentType("application/json");
            client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        if (client == null)
            ServerAPI.init(context);
        client.delete(getAbsoluteUrl(url), responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return ServerAPI.SERVER_BASE_URL + relativeUrl;
    }

    public static void putJSON(Context context, String url, JSONObject data,
                               AsyncHttpResponseHandler responseHandler) {
        StringEntity entity;
        try {
            if (client == null) {
                ServerAPI.init(context);
            }
            entity = new StringEntity(data.toString());
            entity.setContentType("application/json");
            client.put(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(Context context, String username, String email, String password, ServerResponseCallback callback){
        String url = "/user";

        try{
            JSONObject top = new JSONObject();
            top.put("username", username);
            top.put("firstname", "");
            top.put("lastname","");
            top.put("email", email);
            top.put("password",password);
            ServerInterface request = new ServerInterface(url, callback);
            request.executePostJSON(context, top);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static void getUserByUsername(Context context, String username, ServerResponseCallback callback){
        String url = "/user/" + username;

        try{
            ServerInterface request = new ServerInterface(url, callback);
            request.execute(context, null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This function get all nearby posts
     */
    public static void getPostsByUsername(Context context, String username, ServerResponseCallback callback){
        String url = "/user/" + username + "/friendLocation";

        try{
            ServerInterface request = new ServerInterface(url, callback);
            request.execute(context, null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Update whats up when posting
     */
    public static void createPostByUsername(Context context, String username, String whatsup, ServerResponseCallback callback){
        String url = "/user/" + username + "/whatsup";

        try{
            JSONObject top = new JSONObject();
            top.put("username", username);
            top.put("whatsup", whatsup);
            ServerInterface request = new ServerInterface(url, callback);
            request.executePutJSON(context, top);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Update status
     */
    public static void updateStatus(Context context, String username, String status, ServerResponseCallback callback){
        String url = "/user/" + username + "/status";

        try{
            JSONObject top = new JSONObject();
            top.put("username", username);
            top.put("status", status);
            ServerInterface request = new ServerInterface(url, callback);
            request.executePutJSON(context, top);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * This function updates coordinate
     */
    public static void updateCoordinate(Context context, String username, double latitude, double longitude, ServerResponseCallback callback){
        String url = "/user/" + username + "/coordinates";

        try{
            JSONObject top = new JSONObject();
            top.put("username", username);
            top.put("latitude", latitude);
            top.put("longitude", longitude);
            ServerInterface request = new ServerInterface(url, callback);
            request.executePutJSON(context, top);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * This function get all chat groups
     */
    public static void getGroupsByUsername(Context context, String username, ServerResponseCallback callback){
        String url = "/user/" + username + "/groupChatTime";

        try{
            ServerInterface request = new ServerInterface(url, callback);
            request.execute(context, null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This function get all messages under this group
     */
    public static void getMessagesByGroupName(Context context, String groupName, ServerResponseCallback callback){
        String url = "/group/" + groupName + "/chat";

        try{
            ServerInterface request = new ServerInterface(url, callback);
            request.execute(context, null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void sendMessage(Context context, Message message, ServerResponseCallback callback){
        String url = "/group/" + message.getGroupname() + "/chat";

        try{
            JSONObject top = new JSONObject();
            top.put("sender", message.getSender());
            top.put("groupname", message.getGroupname());
            top.put("content",message.getContent());
            ServerInterface request = new ServerInterface(url, callback);
            request.executePostJSON(context, top);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
