package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.collegenotebook.R;

/**
 * Created by Jessica Mendes on 11/10/2017.
 */

public class ProfileFragment extends Fragment {
    public static CalendarFragment newInstance(int someInt, String someTitle) {
        CalendarFragment fragmentDemo = new CalendarFragment();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_profile, container, false);
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
