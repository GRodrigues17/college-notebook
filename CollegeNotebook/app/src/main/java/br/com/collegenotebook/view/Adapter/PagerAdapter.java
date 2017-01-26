package br.com.collegenotebook.view.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import br.com.collegenotebook.view.Fragment.MainFragment;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;
import br.com.collegenotebook.view.Fragment.TimeSheetFragment;

/**
 * Created by GRodrigues17 on 24/10/2016.
 */

public class PagerAdapter  extends SmartFragmentStatePagerAdapter {
    private static int NUM_ITEMS = 2;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return MainFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return TimeSheetFragment.newInstance(2, "Page # 2");
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
    }


}
