package br.com.collegenotebook.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;

import br.com.collegenotebook.GalleryActionsListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.SampleScrollListener;
import br.com.collegenotebook.controller.NotebookController;
import br.com.collegenotebook.view.Activity.PagerActivity;
import br.com.collegenotebook.view.Adapter.GalleryAdapter;

/**
 * Created by GRodrigues17 on 05/10/2016.
 */

public class GalleryFragment  extends Fragment implements GalleryActionsListener {
    private GalleryAdapter adapterGallery;
    private GridView listItem;
    private View view;;
    String nomeMateria;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.content_gallery, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        nomeMateria = getArguments().getString("nome_materia");
        getDirectoryFiles();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void getDirectoryFiles() {
        getAll();

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getContext(), PagerActivity.class);
                //intent.putParcelableArrayListExtra("data", data);
                intent.putExtra("position_number", position);
                intent.putExtra("nome_materia", nomeMateria);
                startActivity(intent);
            }
        });

    }


    public void enviaPosicaoParaOFragment(int position,String nomeMateria, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putInt("position_number", position);
        bundle.putString("nome_materia", nomeMateria);
        fragment.setArguments(bundle);
    }

    @Override
    public void onResume() {
        getAll();
        adapterGallery.notifyDataSetChanged();
        super.onResume();

    }

    @Override
    public void onPause() {
        getAll();
        adapterGallery.notifyDataSetChanged();
        listItem.smoothScrollToPosition(adapterGallery.getCount());
        super.onPause();


    }


    public void getAll() {
        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();

        file = new File( root_sd +"/Mattercam"+ "/" + nomeMateria) ;
        final File list[] = file.listFiles();

        listItem = (GridView) view.findViewById(R.id.grid_view);

        adapterGallery = new GalleryAdapter(getActivity(), list);
        listItem.setAdapter(adapterGallery);
        listItem.setOnScrollListener(new SampleScrollListener(getActivity()));
    }
}
