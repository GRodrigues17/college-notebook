package br.com.collegenotebook.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.MainController;
import br.com.collegenotebook.view.Fragment.AlbumEmptyFragment;
import br.com.collegenotebook.view.Fragment.GalleryFragment;

/**
 * Created by GRodrigues17 on 28/10/2016.
 */

public class GalleryActivity extends AppCompatActivity implements CreateDirectoryListener{

    private GalleryFragment galleryFragment;
    private AlbumEmptyFragment albumEmptyFragment;
    private MainController mainController;
    private FragmentTransaction ft;
    private String nomeMateria;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mainController = new MainController(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nomeMateria = bundle.getString("nome_materia");

        testando(nomeMateria);

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }



    private void testando(String nomeMateria) {
        albumEmptyFragment = new AlbumEmptyFragment();
        galleryFragment = new GalleryFragment();

        ft = getSupportFragmentManager().beginTransaction();

        //mainController.criaDiretorio(nomeMateria);
      String dir = mainController.testDirSave(nomeMateria,this);
//        Procura o diretório específico da matéria

        if(dir.equals("sd")){
            String root_sd = Environment.getExternalStorageDirectory().toString();

            File file = new File( root_sd +"/Mattercam"+ "/" +nomeMateria) ;
            File[] list = file.listFiles();

//        //valIdação se há algo dentro do diretório
            if (list==null || list.length == 0){
                Toast.makeText(this , "Naõ tem nada ainda.", Toast.LENGTH_SHORT).show();
                //chamo a tela de inserir conteúdo nesse diretório
                enviaMensagemParaOFragment(nomeMateria , albumEmptyFragment);
                ft.replace(R.id.your_placeholder, albumEmptyFragment);
                ft.commit();

            }else {
                //envio por bundle o nome da matéria que quero exibir os dados
                enviaMensagemParaOFragment(nomeMateria , galleryFragment);
                ft.replace(R.id.your_placeholder, galleryFragment);
                ft.commit();
            }

        }else if (dir.equals("internal")){
            Toast.makeText(this , "Naõ tem nada ainda.", Toast.LENGTH_SHORT).show();
            //chamo a tela de inserir conteúdo nesse diretório
            enviaMensagemParaOFragment(nomeMateria , albumEmptyFragment);
            ft.replace(R.id.your_placeholder, albumEmptyFragment);
            ft.commit();



        }



    }

//    public void read(View view) {
//        try {
//            FileInputStream fin = this.openFileInput(file);
//            int c;
//            String temp = "";
//            while ((c = fin.read()) != -1) {
//                temp = temp + Character.toString((char) c);
//            }
//            et.setText(temp);
//            Toast.makeText(getBaseContext(), "file read",
//                    Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//
//        }
//    }


    public void enviaMensagemParaOFragment(String nomeMateria, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("nome_materia", nomeMateria );
        fragment.setArguments(bundle);
    }

    @Override
    public void onCreateMateriaListener(String pastaMateria) {
        mainController.criaDiretorio(pastaMateria);
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
//            materiasListView.setVisibility(View.VISIBLE);
//            fab.show();

            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }
}