package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class RobotoBoldTextView extends TextView{
    public RobotoBoldTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public RobotoBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public RobotoBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Roboto-Bold.ttf", context);
        setTypeface(customFont);
    }
}
