package br.com.collegenotebook.view.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.MainController;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class AlbumEmptyFragment extends Fragment {
    private ImageView imgTakePicture;
    private FragmentActivity listener;
    private ImageView imgOpenGallery;
    private File picsDir;

    private CreateDirectoryListener directoryListener;
    private MainController mainController;

    public static AlbumEmptyFragment newInstance() {
        return new AlbumEmptyFragment();
    }

    public AlbumEmptyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_album_empty, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String nomeMateria = getArguments().getString("nome_materia");
        Toast.makeText(getActivity(), "ALBUM VAZIO" , Toast.LENGTH_SHORT).show();
        mainController = new MainController(getContext());

        directoryListener = (CreateDirectoryListener) getActivity();
        directoryListener.onCreateMateriaListener(nomeMateria);

        imgTakePicture = (ImageView) view.findViewById(R.id.img_take_photo);
        imgOpenGallery = (ImageView) view.findViewById(R.id.img_add_gallery);

        //botão de abrir a câmera e tirar foto
        imgTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //valido se meu diretório existe
                picsDir =  mainController.criaDiretorio(nomeMateria);
                Intent i = mainController.openCamera(picsDir);
                getActivity().startActivityForResult(i, 123);

            }
        });

        imgOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int SELECT_PICTURE = 1;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);


//                final int IMAGE_VIEW_ACTIVITY_REQUEST_CODE   = 101;
//
//
//
//// Coloque este trecho de código onde você quer que chame a galeria.
//                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.fromFile(imageFile) );
//                startActivityForResult( intent, IMAGE_VIEW_ACTIVITY_REQUEST_CODE );
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateDirectoryListener ) {
            directoryListener= (CreateDirectoryListener) context;
        }
// else {
//            throw new ClassCastException(context.toString()
//                    + " must implemenet GameFragment.Listener");
//        }
    }

}
