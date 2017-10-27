package br.com.collegenotebook.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Activity.CommentActivity;
import br.com.collegenotebook.view.Activity.ZoomActivity;

/**
 * Created by GRodrigues17 on 15/01/2017.
 */

public class PageDetailFragment extends Fragment {
    private ImageView photoView;
    private ImageView fabEdit;
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
        photoView = (ImageView) rootView.findViewById(R.id.detail_image);
        fabEdit =(ImageView) rootView.findViewById(R.id.fab1);
        position = getArguments().getInt("position_number");
        nomeMateria = getArguments().getString("nome_materia");


        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File( root_sd +"/Mattercam"+ "/" + nomeMateria) ;

        File list[] = file.listFiles();

        Picasso.with(getActivity())
                .load(list[position]) // load minha lista de arquivos
                .placeholder(R.drawable.placeholder) //uma tipo de load
                .error(R.drawable.error)
                .fit()
                .centerInside()
                .tag(getActivity())
                .into(photoView);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ZoomActivity.class);
                intent.putExtra("position_number", position);
                intent.putExtra("nome_materia", nomeMateria);
                startActivity(intent);
            }
        });
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommentActivity.class);
                intent.putExtra("position_number", position);
                intent.putExtra("nome_materia", nomeMateria);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_page_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}


