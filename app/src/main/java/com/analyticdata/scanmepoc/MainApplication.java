package com.analyticdata.scanmepoc;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by M1030099 on 11/22/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
}
