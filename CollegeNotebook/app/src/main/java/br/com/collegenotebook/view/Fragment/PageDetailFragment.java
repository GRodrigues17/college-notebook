package br.com.collegenotebook.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Adapter.DetailGalleryAdapter;

/**
 * Created by GRodrigues17 on 15/01/2017.
 */

public class PageDetailFragment extends Fragment
{
    private String nomeMateria;
    private int position;


    public static PageDetailFragment newInstance(int position,String nomeMateria) {
        PageDetailFragment fragment = new PageDetailFragment();
        Bundle args = new Bundle();
        args.putInt("position_number", position);
        args.putString("nome_materia", nomeMateria);
        fragment.setArguments(args);
        return fragment;
    }

    public PageDetailFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_detail, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.detail_image);
        position = getArguments().getInt("position_number");
        nomeMateria = getArguments().getString("nome_materia");

        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File( root_sd +"/CollegeNotebook"+ "/" + nomeMateria) ;
        File list[] = file.listFiles();
        Picasso.with(getActivity())
                .load(list[position]) // load minha lista de arquivos
                .placeholder(R.drawable.placeholder) //uma tipo de load
                .error(R.drawable.error)
                .fit()
                .centerInside()
                .tag(getActivity())
                .into(imageView);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}


