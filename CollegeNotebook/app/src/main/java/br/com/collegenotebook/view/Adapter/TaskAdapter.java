package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.R;
import br.com.collegenotebook.holder.TaskHolder;
import br.com.collegenotebook.model.Task;

/**
 * Created by Jessica Mendes on 13/10/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
    private List<Task> listTasks = new ArrayList<>();
    private Context mContext;


    public TaskAdapter(List<Task> list, Context context) {
        listTasks = list;
//        if (list != null && list.size() > 0)
//            listTasks.addAll(list);

        mContext = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_card_task, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.txtHour.setText(listTasks.get(position).getHour());
        holder.txtTitle.setText(listTasks.get(position).getTask());
        holder.txtDescription.setText(listTasks.get(position).getTaskDescription());

    }

    @Override
    public int getItemCount() {
        return listTasks != null ? listTasks.size() : 0;
    }

    private void insertItem(Task user) {
        listTasks.add(user);
        notifyItemInserted(getItemCount());
    }

    private void updateItem(int position) {
        //listTasks.get(position).incrementAge();
        notifyItemChanged(position);
    }

    private void removerItem(int position) {
        listTasks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listTasks.size());
    }

    public void updateList(Task task) {
        insertItem(task);
    }
}
