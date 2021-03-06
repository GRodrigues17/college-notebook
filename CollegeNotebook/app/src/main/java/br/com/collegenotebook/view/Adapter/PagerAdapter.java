package br.com.collegenotebook.view.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import br.com.collegenotebook.view.Fragment.MainFragment;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;

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
                return MainFragment.newInstance(2, "Page # 2");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title = null;
        if (position==0){
            title= "Notebooks ";
        }
        if (position==1){
            title= "To Do ";
        }
       return title;
    }

    @Override
    public float getPageWidth (int position) {
        return 1.00f;
    }


}
