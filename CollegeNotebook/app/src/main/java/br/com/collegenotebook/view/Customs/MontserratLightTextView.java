package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jessica Mendes on 02/10/2017.
 */

public class MontserratLightTextView extends TextView {
    public MontserratLightTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public MontserratLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public MontserratLightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Montserrat-Light.ttf", context);
        setTypeface(customFont);
    }
}
