package br.com.collegenotebook;

import android.graphics.drawable.Drawable;

/**
 * Created by GRodrigues17 on 24/10/2016.
 */


public class DrawableUtils {
    private static final int[] EMPTY_STATE = new int[] {};

    public static void clearState(Drawable drawable) {
        if (drawable != null) {
            drawable.setState(EMPTY_STATE);
        }
    }
}
