package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Customs.SquaredImageView;

import static android.widget.ImageView.ScaleType.CENTER_INSIDE;

/**
 * Created by GRodrigues17 on 17/10/2016.
 */

public class GalleryAdapter extends BaseAdapter {

    private final Context context;
    private File[] minhaLista;
    private LayoutInflater inflater;

    public GalleryAdapter(Context context, File[] minhaLista) {
        super();
        this.context = context;
        this.minhaLista = minhaLista;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return minhaLista.length;
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        SquaredImageView convertView = (SquaredImageView) view;
        if (convertView == null) {
            convertView = new SquaredImageView(context);
            convertView.setScaleType(CENTER_INSIDE);

        }

        Picasso.with(context)
                .load(minhaLista[position]) // load minha lista de arquivos
                .placeholder(R.drawable.placeholder) //uma tipo de load
                .error(R.drawable.error)
                .fit()
                .centerCrop()
                .tag(context)
                .into(convertView);
        return convertView;
    }

    public class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }
}
