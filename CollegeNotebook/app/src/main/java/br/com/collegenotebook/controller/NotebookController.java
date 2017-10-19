package br.com.collegenotebook.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * Created by GRodrigues17 on 07/10/2016.
 */

public class NotebookController {
    private Context context;
    private File imageFile;
    public NotebookController(Context context){
        this.context = context;

    }

    public static String getCaminhoSdCard() {
        String lstrDir = System.getenv("SECONDARY_STORAGE");

        if (lstrDir == null || lstrDir.isEmpty()) {
             return "";
        }

        if (lstrDir.contains(":")) {
            return lstrDir.substring(0, lstrDir.indexOf(":"));
        }

        return lstrDir;
    }

    public File criaDiretorio(String nomeMateria) {

        File picsDir = new File(Environment.getExternalStorageDirectory() + "/Mattercam" + "/"+ nomeMateria);
        if (!picsDir.exists()) {
            picsDir.mkdir();
            Toast.makeText(context, "criado com sucesso.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "ja existe.", Toast.LENGTH_SHORT).show();
        }
        return picsDir;
    }


    public void criaDiretorioInterno(String nomeMateria)  {
        String fileName = "/Mattercam/" + nomeMateria;

        File file = new File(context.getFilesDir(), fileName);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    public Intent openCamera(File picsDir){
        //formato que minha imagem vai ser salva nesse diretórioo
        imageFile = new File(picsDir, System.currentTimeMillis() + ".jpg");
        Uri uri = Uri.fromFile(imageFile);
        //chamando a intent da câmera e tirando foto
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        return i;
    }



    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



    public void saveArrayToInternalStorage(String fileName, byte[] imagem){
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(imagem);
            fos.close();
        }catch (IOException e) {
            Log.w("InternalStorage", "Error writing", e);
        }
    }


    public void saveArrayToSDCard(String fileName, byte[] imagem){
        File path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(path, fileName);
        try{
            OutputStream os = new FileOutputStream(file);
            os.write(imagem);
            os.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing", e);
        }
    }

//    public void testLocalSave(){
//    if(isExternalStorageWritable()){
//        saveArrayToSDCard("nomeDaImagem", imagemEmBytes);
//    }else{
//        saveArrayToInternalStorage("nomeDaImagem", imagemEmBytes);
//    }
//
//    }


    public String testDirSave(String nomeMateria, Context context){
        String dir = "";
        if(getCaminhoSdCard().equals("")){
            //saveArrayToSDCard("nomeDaImagem", imagemEmBytes);
            criaDiretorioInterno(nomeMateria);
            dir="internnal";
        }else{
            //saveArrayToInternalStorage("nomeDaImagem", imagemEmBytes);
            criaDiretorio(nomeMateria);
            dir="sd";
        }
        return dir;

    }



}
