package br.com.collegenotebook.view.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.collegenotebook.R;

/**
 * Created by Jessica Mendes on 22/10/2017.
 */

public class ZoomActivity extends AppCompatActivity{
    private int position;
    private String nomeMateria;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        position = getIntent().getIntExtra("position_number", 0);
        nomeMateria = getIntent().getStringExtra("nome_materia");

        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File( root_sd +"/Mattercam"+ "/" + nomeMateria) ;

        File list[] = file.listFiles();
        final PhotoView photoView = (PhotoView) findViewById(R.id.iv_zoom);
        Picasso.with(this)
                .load(list[position]) // load minha lista de arquivos
                .placeholder(R.drawable.placeholder) //uma tipo de load
                .error(R.drawable.error)
                .fit()
                .centerInside()
                .tag(this)
                .into(photoView);

    }
}
