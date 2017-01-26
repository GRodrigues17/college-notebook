package br.com.collegenotebook.view.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.EditNameDialogListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.model.Materia;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class NewMateriaDialog extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText edtNameSubject;
    private EditText edtProfessor;
    private Context context;
    boolean createSuccessful;
    private EditNameDialogListener activity;
    private CreateDirectoryListener directory;
    private Materia materia;
    private String nome;
    private String professor;
    private String pastaMateria;
    private Button btnOk;
    private Button btnCancel;

    public NewMateriaDialog() {
        // Empty constructor required for DialogFragment
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context =  inflater.getContext();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_insert_subject, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView);


        edtNameSubject = (EditText) dialogView.findViewById(R.id.edt_subject_name);
        edtProfessor = (EditText) dialogView.findViewById(R.id.edt_professor_name);

        btnOk  = (Button) dialogView.findViewById(R.id.btnOk);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel) ;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validField()){

                    salvaMateria();

                    activity = (EditNameDialogListener) getParentFragment();
                    activity.onFinishEditDialog(materia);
                    dismiss();
                }else {
                    edtNameSubject.setError("Campo vazio");

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void salvaMateria() {
        materia = new Materia();
        materia.setNome(nome);
        materia.setProfessor(professor);
        materia.setPasta(pastaMateria);
        //createSuccessful = new BaseController(getActivity()).insereDado(materia);


        directory = (CreateDirectoryListener) getParentFragment();
        directory.onCreateMateriaListener(pastaMateria);

    }

    private boolean validField() {
        nome = edtNameSubject.getText().toString();
        professor = edtProfessor.getText().toString();
        pastaMateria = nome;
        if (nome == null || nome.length()==0){
            return false;
        }
        else {
            return true;
        }

    }


    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            activity = (EditNameDialogListener) getParentFragment();
            activity.onFinishEditDialog(materia);
            this.dismiss();
            return true;
        }
        return false;
    }





}
