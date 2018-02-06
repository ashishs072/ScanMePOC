package com.analyticdata.scanmepoc.networking.request;

import com.analyticdata.scanmepoc.networking.pojo.AddUserStoryDetail;
import com.analyticdata.scanmepoc.util.UIUtils;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by M1030099 on 11/23/2017.
 */

public class AddUserStoryDetailrequest implements IRequest{

    private Response.Listener sucessListener;
    private Response.ErrorListener errorListener;
    private AddUserStoryDetail addUserDetail;

    public AddUserStoryDetailrequest(Response.Listener sucessListener, Response.ErrorListener errorListener, AddUserStoryDetail object) {
        this.sucessListener = sucessListener;
        this.errorListener = errorListener;
        addUserDetail = object;
    }

    @Override
    public String getReqParams() {
        return UIUtils.getInstance().toJson(addUserDetail, true);
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getAction() {
        return "addstories";
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
        return null;
    }

    @Override
    public boolean isHandleError() {
        return false;
    }
}
