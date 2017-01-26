package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import br.com.collegenotebook.view.Customs.CustomFontUtils;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class RobotoRegularTextView extends TextView{
    public RobotoRegularTextView(Context context) {
        super(context);

        CustomFontUtils.applyCustomFont(this, context, null);
    }

    public RobotoRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        CustomFontUtils.applyCustomFont(this, context, attrs);
    }

    public RobotoRegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        CustomFontUtils.applyCustomFont(this, context, attrs);
    }

}
