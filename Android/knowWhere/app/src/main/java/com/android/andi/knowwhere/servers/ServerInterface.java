package com.android.andi.knowwhere.servers;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Andi Xu on 4/4/18.
 */

public class ServerInterface {
    private ServerResponseCallback mCallback = null;
    private String mRequest = null;
    private ServerResponseData mResponseData = new ServerResponseData();
    public static final int LOGIN_REQUIRED = 403;

    public ServerInterface(String request, ServerResponseCallback callback){
        mRequest = request;
        mCallback = callback;
    }

    public void execute(Context context, RequestParams httpParams){
        ServerAPI.get(context, mRequest, httpParams, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] response) {
                String responseString = null;
                try {
                    responseString = new String(response, "UTF-8");
                    if (statusCode == ServerAPI.STATUS_OK){
                        mResponseData.statusCode = ServerAPI.STATUS_OK;
                    } else {
                        mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    }
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;

                } catch (UnsupportedEncodingException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String responseString = null;
                try {
                    if(responseBody==null)
                        return;
                    responseString = new String(responseBody, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.responseData = responseString;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

    public void executeUrl(Context context, RequestParams httpParams) {

        ServerAPI.getAbsoulte(context, mRequest, httpParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] response) {
                String responseString = null;
                try {
                    responseString = new String(response, "UTF-8");
                    if (statusCode == ServerAPI.STATUS_OK){
                        mResponseData.statusCode = ServerAPI.STATUS_OK;
                    } else {
                        mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    }
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;

                } catch (UnsupportedEncodingException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

    public void executePost(Context context, RequestParams httpParams) {

        ServerAPI.post(context, mRequest, httpParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] response) {
                String responseString = null;
                try {
                    responseString = new String(response, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;
                } catch (UnsupportedEncodingException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String responseString = null;
                try {
                    if(responseBody==null)
                        return;
                    responseString = new String(responseBody, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.responseData = responseString;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

    public void executePostJSON(Context context, JSONObject data) {

        ServerAPI.postJSON(context, mRequest, data, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] response) {
                String responseString = null;
                try {
                    responseString = new String(response, "UTF-8");
                    if (statusCode == ServerAPI.STATUS_OK){
                        mResponseData.statusCode = ServerAPI.STATUS_OK;
                    } else {
                        mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    }
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String responseString = null;

                try {
                    if(responseBody==null)
                        return;
                    responseString = new String(responseBody, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.responseData = responseString;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

    public void executeDelete(Context context, RequestParams httpParams) {

        ServerAPI.delete(context, mRequest, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String responseString = null;
                try {
                    responseString = new String(responseBody, "UTF-8");
                    if (statusCode == ServerAPI.STATUS_OK){
                        mResponseData.statusCode = ServerAPI.STATUS_OK;
                    } else {
                        mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    }
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String responseString = null;

                try {
                    if(responseBody==null)
                        return;
                    responseString = new String(responseBody, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.responseData = responseString;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

    public void executePut(Context context, RequestParams httpParams) {

        ServerAPI.delete(context, mRequest, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String responseString = null;
                try {
                    responseString = new String(responseBody, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;

                } catch (UnsupportedEncodingException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

    public void executePutJSON(Context context, JSONObject data) {

        ServerAPI.putJSON(context, mRequest,data, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String responseString = null;
                try {
                    responseString = new String(responseBody, "UTF-8");
                    if (statusCode == ServerAPI.STATUS_OK){
                        mResponseData.statusCode = ServerAPI.STATUS_OK;
                    } else {
                        mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    }
                    mResponseData.statusMsg = ServerAPI.STATUS_MSG_OK;
                    mResponseData.responseData = responseString;

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                String responseString = null;
                try {
                    if(responseBody==null)
                        return;
                    responseString = new String(responseBody, "UTF-8");
                    mResponseData.statusCode = ServerAPI.STATUS_UNKNOWNERROR;
                    mResponseData.responseData = responseString;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (mCallback != null) {
                    mCallback.onResponse(mResponseData);
                }
            }
        });
    }

}
