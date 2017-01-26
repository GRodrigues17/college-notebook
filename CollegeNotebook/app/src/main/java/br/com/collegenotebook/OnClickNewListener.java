package br.com.collegenotebook;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.view.View;

import br.com.collegenotebook.view.Fragment.NewMateriaDialog;

/**
 * Created by GRodrigues17 on 08/10/2016.
 */

public abstract class OnClickNewListener implements DialogInterface.OnClickListener {
    private NewMateriaDialog dialog;

    public OnClickNewListener(NewMateriaDialog dialog) {
        this.dialog = dialog;
    }



    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(onClicked()){
            dialog.dismiss();
        }
    }

    protected abstract boolean onClicked();

}
