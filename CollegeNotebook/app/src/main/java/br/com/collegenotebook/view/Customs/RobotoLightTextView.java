package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class RobotoLightTextView extends TextView{
    public RobotoLightTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public RobotoLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public RobotoLightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Roboto-Light.ttf", context);
        setTypeface(customFont);
    }
}
