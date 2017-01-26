package br.com.collegenotebook.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * Created by GRodrigues17 on 07/10/2016.
 */

public class MainController {
    private Context context;
    private File imageFile;
    public MainController(Context context){
        this.context = context;

    }

    public File criaDiretorio(String nomeMateria) {
        File picsDir = new File(Environment.getExternalStorageDirectory() + "/CollegeNotebook" + "/"+ nomeMateria);
        if (!picsDir.exists()) {
            picsDir.mkdir();
            Toast.makeText(context, "criado com sucesso.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "ja existe.", Toast.LENGTH_SHORT).show();
        }
        return picsDir;
    }

    public Intent openCamera(File picsDir ){
        //formato que minha imagem vai ser salva nesse diretórioo
        imageFile = new File(picsDir, System.currentTimeMillis() + ".jpg");

        //chamando a intent da câmera e tirando foto
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));

        return i;
    }


}
