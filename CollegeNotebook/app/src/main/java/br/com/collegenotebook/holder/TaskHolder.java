package br.com.collegenotebook.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.collegenotebook.R;

/**
 * Created by Jessica Mendes on 13/10/2017.
 */

public class TaskHolder extends RecyclerView.ViewHolder {
    public TextView txtHour;
    public TextView txtTitle;
    public TextView txtDescription;

    public TaskHolder(View view) {
        super(view);

        txtHour = (TextView) view.findViewById(R.id.txtHour);
        txtTitle = (TextView) view.findViewById(R.id.txtTaskTitle);
        txtDescription = (TextView) view.findViewById(R.id.txtTaksDescription);
    }
}
