package com.analyticdata.scanmepoc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by M1030099 on 11/8/2017.
 */

public class UIUtils {

    private static UIUtils mUtils;
    public static UIUtils getInstance() {
        if (mUtils == null) {
            mUtils = new UIUtils();
        }
        return mUtils;
    }
    public String toJson(Object pojo, boolean prettyPrint) {
        try {
            ObjectMapper m = new ObjectMapper();
            JsonFactory jf = new JsonFactory();

            StringWriter sw = new StringWriter();
            JsonGenerator jg = jf.createGenerator(sw);
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
            m.writeValue(jg, pojo);
            return sw.toString();
        } catch (JsonMappingException jmEx) {
            return null;
        } catch (JsonGenerationException jgEx) {
            return null;
        } catch (IOException ioEx) {
            return null;
        }
    }
    public <T> T getPojoObject(String json, Class<T> clazz) {
        T t = null;
        ObjectMapper map = new ObjectMapper();
        try {
            t = map.readValue(json, clazz);
        } catch (IOException e) {
            //  e.printStackTrace();
            //CLog.d("test_bulk exception >>> " + e);
        }
        return t;
    }
    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null)
            return false;

        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);

    }
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static String convertintoCamelCase(String valueString) {
        String txt = valueString;
        txt = String.valueOf(txt.charAt(0)).toUpperCase() + txt.substring(1,txt.length()).toLowerCase();
        return txt;
    }
}
