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
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;

import br.com.collegenotebook.R;
import br.com.collegenotebook.listener.EditCommentDialogListener;
import br.com.collegenotebook.model.Comment;

/**
 * Created by Jessica Mendes on 22/10/2017.
 */

public class NewCommentDialog extends DialogFragment implements TextView.OnEditorActionListener  {
    private Context context;
    private EditText edtCommentMarker;
    private ImageView imgMarker;
    private Button btnOk;
    private Button btnCancel;
    private Comment comment;
    private EditCommentDialogListener activity;
    private String commentText;

    public NewCommentDialog() {
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
        View dialogView = inflater.inflate(R.layout.dialog_new_comment, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView);



        edtCommentMarker = (EditText) dialogView.findViewById(R.id.edtCommentPin);
        imgMarker = (ImageView) dialogView.findViewById(R.id.imgTypeComment);

        btnOk  = (Button) dialogView.findViewById(R.id.btnOk);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel) ;
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validField()){

                    saveComment();

                    activity = (EditCommentDialogListener) getActivity();
                    activity.onFinishEditDialog(comment);
                    dismiss();
                }else {
                    edtCommentMarker.setError("Campo vazio");

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



    private void saveComment() {
        String dateCreated = DateFormat.getDateInstance().format(new java.util.Date());
        comment= new Comment();
        comment.setComment(commentText);
        comment.setDate(dateCreated);
    }

    private boolean validField() {
        String comment = edtCommentMarker.getText().toString();
        commentText = comment;
        if (comment == null || comment.length()==0){
            return false;
        }
        else {
            return true;
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            activity = (EditCommentDialogListener) getActivity();
            activity.onFinishEditDialog(comment);
            this.dismiss();
            return true;
        }
        return false;
    }
}
