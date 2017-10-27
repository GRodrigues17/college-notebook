package br.com.collegenotebook.listener;

import br.com.collegenotebook.model.Comment;
import br.com.collegenotebook.model.Matter;

/**
 * Created by Jessica Mendes on 22/10/2017.
 */

public interface EditCommentDialogListener {
        void onFinishEditDialog(Comment comment);
}
