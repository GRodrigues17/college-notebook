package br.com.collegenotebook.view.Customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by GRodrigues17 on 17/10/2016.
 */

public class SquaredImageView extends android.support.v7.widget.AppCompatImageView {

        public SquaredImageView(Context context) {
            super(context);
        }

        public SquaredImageView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        }
}
