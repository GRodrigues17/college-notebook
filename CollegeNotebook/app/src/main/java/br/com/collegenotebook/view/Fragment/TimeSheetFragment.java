package br.com.collegenotebook.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.CreateDirectoryListener;
import br.com.collegenotebook.EditNameDialogListener;
import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.BaseController;
import br.com.collegenotebook.controller.MainController;
import br.com.collegenotebook.model.Materia;
import br.com.collegenotebook.view.Activity.GalleryActivity;
import br.com.collegenotebook.view.Adapter.SubjectAdapter;

/**
 * Created by Jessica Mendes on 11/10/2017.
 */

public class TimeSheetFragment extends Fragment{


    public static TimeSheetFragment newInstance(int someInt, String someTitle) {
        TimeSheetFragment fragmentDemo = new TimeSheetFragment();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_timesheet, container, false);
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
