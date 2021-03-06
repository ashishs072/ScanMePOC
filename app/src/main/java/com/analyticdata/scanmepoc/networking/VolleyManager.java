package com.analyticdata.scanmepoc.networking;

import android.content.Context;

import com.analyticdata.scanmepoc.networking.request.IRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by M1028309 on 4/19/2015.
 */

public class VolleyManager extends StringRequest {
    private static Map<Integer, String> mStatusCodes;

    static {
        mStatusCodes = new HashMap();
        //TODO: Add appropriate error messages
        mStatusCodes.put(400, ""); // Unauthorized [login failed]
        mStatusCodes.put(401, ""); // Bad Request
        mStatusCodes.put(403, ""); // forbidden [restricted access]
        mStatusCodes.put(404, ""); //not found [webservice does not exist]
        mStatusCodes.put(500, ""); //internal server error
    }

    private final Map<String, String> mHeaders = null;
    private final Response.Listener<String> mSucessListener;
    private final Response.ErrorListener mErrorListener;
    private Context mContext;
    private String mParams;
    //private UIUtils mUtil;
    private boolean mHandleError;

    public VolleyManager(Context context, int method, String url, IRequest req) {
        super(method, url, null, null);
        this.mContext = context;
        mParams = req.getReqParams();
        /*CLog.i("AttachedmParams " + mParams+"::::"+url);
        CLog.i("Is valid json:" + isJSONValid(mParams));*/
        this.mSucessListener = req.getSuccessListener();
        this.mErrorListener = req.getErrorListener();
        this.mHandleError = req.isHandleError();

    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json; charset=utf-8");
        return header;
    }


    @Override
    protected void deliverResponse(String response) {
        //CLog.v("VM deliver response");
        this.mSucessListener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        byte[] val = new byte[0];
        try {
            val = mParams.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
           // CLog.e(e.getMessage());
        }
        return val;
    }


    @Override
    public void deliverError(VolleyError error) {
       /* Log.v("VM error");
        Log.v(error.getMessage());
        CLog.v(error.getLocalizedMessage());*/
       // UIUtils.getInstance().hideProgressDialog();
        if (error == null || error.networkResponse == null) {
            //  TODO Unknown error Show Alert dialog

           // displayAlert(mContext.getString(R.string.req_error));
            return;
        }
        if (mHandleError) {
            mErrorListener.onErrorResponse(error);
            return;
        }
        if (mStatusCodes.containsKey(error.networkResponse.statusCode)) {
            //displayAlert(mStatusCodes.get(error.networkResponse.statusCode));
            return;
        }


    }


   /* private void displayAlert(String msg) {
        if (UIUtils.getInstance().isNetworkAvailable(mContext)) {
            UIUtils.getInstance().showAlertDialog(mContext, msg, null); // TODO: Display netwrok related msg here
        } else {
            UIUtils.getInstance().showAlertDialog(mContext, msg, null);
        }
    }
*/
    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        String json = null;
        try {
            json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
           //CLog.e(e.getMessage());
        }
        return Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
    }


}
