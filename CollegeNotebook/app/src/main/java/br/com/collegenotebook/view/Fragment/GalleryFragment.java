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
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;

import br.com.collegenotebook.GalleryActionsListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.SampleScrollListener;
import br.com.collegenotebook.controller.NotebookController;
import br.com.collegenotebook.view.Activity.CommentActivity;
import br.com.collegenotebook.view.Activity.PageDetailActivity;
import br.com.collegenotebook.view.Adapter.GalleryAdapter;

/**
 * Created by GRodrigues17 on 05/10/2016.
 */

public class GalleryFragment  extends Fragment implements GalleryActionsListener {
    private File picsDirectory;
    private FloatingActionMenu menuRed;
    private FloatingActionButton fab1;
    private DetailGalleryFragment detailGalleryFragment;
    private NotebookController galleryController;
    private GalleryAdapter adapterGallery;
    private FragmentTransaction ft;
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
        openFABMenu(view, nomeMateria);
        getDirectoryFiles();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menuRed.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuRed.isOpened()) {
                    Toast.makeText(getActivity(), menuRed.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }

                menuRed.toggle(true);
            }
        });


    }

    @Override
    public void openFABMenu(final View view, final String nomeMateria) {
        menuRed = (FloatingActionMenu) view.findViewById(R.id.menu_red);
        galleryController = new NotebookController(getActivity());
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
//        final FloatingActionButton programFab1 = new FloatingActionButton(getActivity());
//        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
//        programFab1.setLabelText(getString(R.string.app_name));
//        programFab1.setImageResource(R.mipmap.ic_edit);

//        menuRed.addMenuButton(programFab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picsDirectory =  galleryController.criaDiretorio(nomeMateria);
                Intent i = galleryController.openCamera(picsDirectory);
                getActivity().startActivityForResult(i, 123);

//                fab1.setLabelColors(ContextCompat.getColor(getActivity(), R.color.colorMenu),
//                        ContextCompat.getColor(getActivity(), R.color.colorSub),
//                        ContextCompat.getColor(getActivity(), R.color.colorBackground));
//                fab1.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
            }
        });

    }

    @Override
    public void getDirectoryFiles() {
        ft = getFragmentManager().beginTransaction();
        detailGalleryFragment = new DetailGalleryFragment();
        getAll();

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               enviaPosicaoParaOFragment(position,nomeMateria, detailGalleryFragment);
                ft.replace(R.id.your_placeholder, detailGalleryFragment);
                ft.commit();

                //Intent intent = new Intent(getContext(), PageDetailActivity.class);
//                //intent.putParcelableArrayListExtra("data", data);
//                intent.putExtra("position_number", position);
//                intent.putExtra("nome_materia", nomeMateria);
//                startActivity(intent);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        adapterGallery.notifyDataSetChanged();
        listItem.smoothScrollToPosition(adapterGallery.getCount());
        super.onSaveInstanceState(outState);
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
