package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import br.com.collegenotebook.R;

/**
 * Created by GRodrigues17 on 21/10/2016.
 */

public class CustomFontUtils {
    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public static void applyCustomFont(TextView customFontTextView, Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.RobotoRegularTextView);

        String fontName = attributeArray.getString(R.styleable.RobotoRegularTextView_font);

        // check if a special textStyle was used (e.g. extra bold)
        int textStyle = attributeArray.getInt(R.styleable.RobotoRegularTextView_textStyle, 0);

        // if nothing extra was used, fall back to regular android:textStyle parameter
        if (textStyle == 0) {
            textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        }

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        customFontTextView.setTypeface(customFont);

        attributeArray.recycle();
    }

    private static Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_roboto_medium))) {
            return FontCache.getTypeface("Roboto-Italic.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_roboto))) {
            /*
            information about the TextView textStyle:
            http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
            */
            switch (textStyle) {
                case Typeface.BOLD: // bold
                    return FontCache.getTypeface("Roboto-Medium.ttf", context);


                case Typeface.ITALIC: // italic
                    return FontCache.getTypeface("Roboto-Italic.ttf", context);

                case Typeface.BOLD_ITALIC: // bold italic
                    return FontCache.getTypeface("Roboto-MediumItalic.ttf", context);

                case 10: // extra light, equals @integer/font_style_extra_light
                    return FontCache.getTypeface("Roboto-Light.ttf", context);

                case 11: // extra bold, equals @integer/font_style_extra_bold
                    return FontCache.getTypeface("Roboto-LightItalic.ttf", context);

                case Typeface.NORMAL: // regular
                default:
                    return FontCache.getTypeface("Roboto-Regular.ttf", context);
            }
        }
        else {
            // no matching font found
            // return null so Android just uses the standard font (Roboto)
            return null;
        }
    }
}
