package br.com.collegenotebook.view.Fragment;

import android.app.Dialog;
import android.content.Context;

import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.sql.Date;
import java.text.DateFormat;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.EditNameDialogListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.model.Matter;
import br.com.collegenotebook.view.Activity.CommentActivity;
import br.com.collegenotebook.widget.entity.TextEntity;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class NewMateriaDialog extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText edtNameSubject;
    private EditText edtProfessor;
    private View viewMarker;
    private Context context;
    boolean createSuccessful;
    private EditNameDialogListener activity;
    private CreateDirectoryListener directory;
    private Matter matter;
    private String nome;
    private String professor;
    private String pastaMateria;
    private int colorChoice;
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


        viewMarker = (View) dialogView.findViewById(R.id.viewMarker);
        viewMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseNotebookColor();
            }
        });

        edtNameSubject = (EditText) dialogView.findViewById(R.id.edt_subject_name);
        edtProfessor = (EditText) dialogView.findViewById(R.id.edt_professor_name);

        btnOk  = (Button) dialogView.findViewById(R.id.btnOk);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel) ;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validField()){

                    salvaMateria();

                    activity = (EditNameDialogListener) getActivity();
                    activity.onFinishEditDialog(matter);
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



    public void chooseNotebookColor(){
        final int initialColor = (R.color.colorPrimary);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle(R.string.select_color)
                .initialColor(initialColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(8) // magic number
                .setPositiveButton(R.string.ok, new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        changeBackgroundColor(selectedColor);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();


    }


    private void salvaMateria() {
        String dateCreated = DateFormat.getDateInstance().format(new java.util.Date());
        matter = new Matter();
        matter.setTitle(nome);
        matter.setInstructor(professor);
        matter.setFolder(pastaMateria);
        matter.setDate(dateCreated);
        matter.setColor(colorChoice);
        matter.setLike(0);

        directory = (CreateDirectoryListener) getActivity();
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
            activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(matter);
            this.dismiss();
            return true;
        }
        return false;
    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            mListener = (NoticeDialogListener) activity;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(activity.toString()
//                    + " must implement NoticeDialogListener");
//        }
//    }

    private void changeBackgroundColor(int selectedColor) {

        colorChoice = selectedColor;
        viewMarker.setBackgroundColor(selectedColor);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

}
