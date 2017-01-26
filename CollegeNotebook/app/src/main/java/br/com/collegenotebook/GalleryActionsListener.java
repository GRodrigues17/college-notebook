package br.com.collegenotebook;

import android.view.View;

/**
 * Created by GRodrigues17 on 23/10/2016.
 */
public interface GalleryActionsListener {
    void openFABMenu(View view, String nomeMateria);
    void getDirectoryFiles(View view, final String nomeMateria);
}
