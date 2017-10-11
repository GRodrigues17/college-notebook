package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Adapter.PagerAdapter;

/**
 * Created by Jessica Mendes on 11/10/2017.
 */

public class NotebookFragment extends Fragment {
    private SmartFragmentStatePagerAdapter adapterViewPager;
    public static NotebookFragment newInstance(int someInt, String someTitle) {
        NotebookFragment fragmentDemo = new NotebookFragment();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_notebook, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPager vpPager = (ViewPager) view.findViewById(R.id.pager);
        vpPager.setClipToPadding(false);
        vpPager.setPageMargin(12);
        adapterViewPager = new PagerAdapter(getFragmentManager());
        vpPager.setAdapter(adapterViewPager);

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
