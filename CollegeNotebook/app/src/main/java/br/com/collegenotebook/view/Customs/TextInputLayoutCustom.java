package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import br.com.collegenotebook.R;

/**
 * Created by Jessica Mendes on 15/10/2017.
 */

public class TextInputLayoutCustom extends TextInputLayout {
    private Context context;

    public TextInputLayoutCustom(Context context) {
        super(context);
        this.context = context;
    }

    public TextInputLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TextInputLayoutCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        EditText editText = getEditText();
        if(editText != null) {
            editText.setBackground(ContextCompat.getDrawable(this.context, R.drawable.custom_rounded_edittext));
        }
    }

    @Override
    public void setError(@Nullable final CharSequence error) {
        super.setError(error);

        EditText editText = getEditText();
        if(editText != null) {
            editText.setBackground(ContextCompat.getDrawable(this.context, R.drawable.custom_rounded_edittext));
        }
    }

}
