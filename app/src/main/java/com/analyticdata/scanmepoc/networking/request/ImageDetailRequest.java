package com.analyticdata.scanmepoc.networking.request;
import com.analyticdata.scanmepoc.activity.HomeActivity;
import com.analyticdata.scanmepoc.util.UIUtils;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by m1022867 on 4/23/2015.
 */
public class ImageDetailRequest implements IRequest {

    private Response.Listener sucessListener;
    private Response.ErrorListener errorListener;
    private ImageDetailsObject imageDetailObject;

    public ImageDetailRequest(Response.Listener sucessListener, Response.ErrorListener errorListener, ImageDetailsObject object) {
        this.sucessListener = sucessListener;
        this.errorListener = errorListener;
        imageDetailObject = object;
    }

    @Override
    public String getReqParams() {
        return UIUtils.getInstance().toJson(imageDetailObject, true);
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getAction() {
        return "getrecyclingdetails";
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
        return "ImageDetailRequest";
    }

    @Override
    public boolean isHandleError() {
        return false;
    }
}
