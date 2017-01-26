package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

import br.com.collegenotebook.R;
import br.com.collegenotebook.model.TimeSheet;
import br.com.collegenotebook.view.Adapter.TimeSheetAdap;

/**
 * Created by GRodrigues17 on 31/10/2016.
 */

public class TimeSheetFrag extends Fragment {

    private GridView gridTimeSheet;
    private List<TimeSheet> timeSheets;
    private TimeSheetAdap timeSheetAdap;

    public static TimeSheetFrag newInstance() {
        TimeSheetFrag fragmentDemo = new TimeSheetFrag();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timesheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridTimeSheet = (GridView) view.findViewById(R.id.gridTimeSheer);
        //timeSheets.addAll();
        timeSheetAdap = new TimeSheetAdap(getActivity(), timeSheets);
        gridTimeSheet.setAdapter(timeSheetAdap);
    }
}
