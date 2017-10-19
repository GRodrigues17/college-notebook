package br.com.collegenotebook.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.NotebookController;
import br.com.collegenotebook.view.Fragment.AlbumEmptyFragment;
import br.com.collegenotebook.view.Fragment.GalleryFragment;

/**
 * Created by GRodrigues17 on 28/10/2016.
 */

public class GalleryActivity extends AppCompatActivity implements CreateDirectoryListener{
    static final int REQUEST_IMAGE_CAPTURE = 100;
    static final int SELECT_PICTURE = 1;
    private GalleryFragment galleryFragment;
    private AlbumEmptyFragment albumEmptyFragment;
    private NotebookController notebookController;
    private String nomeMateria;
    private File picsDir;
    private Uri uri;

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
        notebookController = new NotebookController(this);
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
        //notebookController.criaDiretorio(nomeMateria);
        String dir = notebookController.testDirSave(nomeMateria,this);
        // Procura o diretório específico da matéria

        if(dir.equals("sd")){
            String root_sd = Environment.getExternalStorageDirectory().toString();

            File file = new File( root_sd +"/Mattercam"+ "/" +nomeMateria) ;
            File[] list = file.listFiles();

            //valIdação se há algo dentro do diretório
            if (list==null || list.length == 0){
                Toast.makeText(this , "Naõ tem nada ainda.", Toast.LENGTH_SHORT).show();
                //chamo a tela de inserir conteúdo nesse diretório
                showGalleryEmpty();


            }else {
                showGallery();

            }

        }else if (dir.equals("internal")){
            Toast.makeText(this , "Naõ tem nada ainda.", Toast.LENGTH_SHORT).show();
            //chamo a tela de inserir conteúdo nesse diretório
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.your_placeholder, albumEmptyFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }

    }

    private void showGallery() {
        //envio por bundle o nome da matéria que quero exibir os dados
        enviaMensagemParaOFragment(nomeMateria , galleryFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.your_placeholder, galleryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showGalleryEmpty() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.your_placeholder, albumEmptyFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void enviaMensagemParaOFragment(String nomeMateria, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("nome_materia", nomeMateria );
        fragment.setArguments(bundle);
    }

    @Override
    public void onCreateMateriaListener(String pastaMateria) {
        notebookController.criaDiretorio(pastaMateria);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_fromGallery:
                openGalleryInternal();
                break;
            case R.id.menu_newPicture:
                openCamera();
        }
        return true;

    }

    private void openCamera() {
        picsDir =  notebookController.criaDiretorio(nomeMateria);
        File imageFile = new File(picsDir, System.currentTimeMillis() + ".jpg");
        uri = Uri.fromFile(imageFile);
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
        }


    }

    private void openGalleryInternal() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,GalleryActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // Bitmap photo = (Bitmap) data.getExtras().get("data");
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                handleSmallCameraPhoto(uri);
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

    private void handleSmallCameraPhoto(Uri uri)
    {
        Bitmap bmp=null;

        try {
            bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }


        Intent displayIntent = new Intent(this, CommentActivity.class);
        displayIntent.putExtra("BitmapImage", bmp);
        startActivity(displayIntent);


    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}