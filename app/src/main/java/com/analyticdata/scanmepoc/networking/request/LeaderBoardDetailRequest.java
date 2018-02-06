package com.analyticdata.scanmepoc.networking.request;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by M1030099 on 11/21/2017.
 */

public class LeaderBoardDetailRequest implements IRequest {
    private Response.Listener sucessListener;
    private Response.ErrorListener errorListener;

    public LeaderBoardDetailRequest(Response.Listener sucessListener, Response.ErrorListener errorListener) {
        this.sucessListener = sucessListener;
        this.errorListener = errorListener;

    }
    @Override
    public String getReqParams() {
        return null;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getAction() {
        return "leaderboard";
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
        return "LeaderBoardDetail";
    }

    @Override
    public boolean isHandleError() {
        return false;
    }
}
