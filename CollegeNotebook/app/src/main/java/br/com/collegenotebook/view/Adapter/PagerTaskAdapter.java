package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import br.com.collegenotebook.view.Fragment.MainFragment;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;
import br.com.collegenotebook.view.Fragment.TaskFragment;

/**
 * Created by Jessica Mendes on 13/10/2017.
 */

public class PagerTaskAdapter extends SmartFragmentStatePagerAdapter {
    int numItens;
    String date;


    public PagerTaskAdapter(FragmentManager fragmentManager,int numItens) {
        super(fragmentManager);
        this.numItens = numItens;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return numItens;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return TaskFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return MainFragment.newInstance(2, "Page # 2");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public float getPageWidth (int position) {
        return 1.00f;
    } {

    }
}
