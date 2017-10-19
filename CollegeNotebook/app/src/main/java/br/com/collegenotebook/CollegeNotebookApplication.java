package br.com.collegenotebook;

import android.app.Application;
import android.support.multidex.MultiDex;


/**
 * Created by GRodrigues17 on 21/10/2016.
 */

public class CollegeNotebookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }

}
