package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.R;
import br.com.collegenotebook.model.Task;
import br.com.collegenotebook.view.Adapter.TaskAdapter;

/**
 * Created by Jessica Mendes on 13/10/2017.
 */

public class TaskEmptyFragment extends Fragment {
    private List<Task> tasks;
    private TaskAdapter adapter;
    public static TaskFragment newInstance(int someInt, String someTitle) {
        TaskFragment fragmentDemo = new TaskFragment();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_empty_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
