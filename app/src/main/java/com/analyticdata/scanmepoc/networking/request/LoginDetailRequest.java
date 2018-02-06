package com.analyticdata.scanmepoc.networking.request;

import com.analyticdata.scanmepoc.util.UIUtils;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by M1030099 on 11/13/2017.
 */

public class LoginDetailRequest implements IRequest {

    private Response.Listener sucessListener;
    private Response.ErrorListener errorListener;
    private LoginDetailObject loginDetailObject;

    public LoginDetailRequest(Response.Listener sucessListener, Response.ErrorListener errorListener, LoginDetailObject object) {
        this.sucessListener = sucessListener;
        this.errorListener = errorListener;
        this.loginDetailObject = object;
    }

    @Override
    public String getReqParams() {
        return UIUtils.getInstance().toJson(loginDetailObject, true);
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getAction() {
        return "userlogin";
    }

    @Override
    public Response.ErrorListener getErrorListener() {
        return errorListener;
    }

    @Override
    public Response.Listener<String> getSuccessListener() {
        return sucessListener;
    }

    @Override
    public String getTag() {
        return "LoginDetailRequest";
    }

    @Override
    public boolean isHandleError() {
        return false;
    }
}
