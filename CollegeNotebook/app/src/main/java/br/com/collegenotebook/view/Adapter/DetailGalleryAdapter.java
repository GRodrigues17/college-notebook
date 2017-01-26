package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Fragment.DetailGalleryFragment;
import br.com.collegenotebook.view.Fragment.PageDetailFragment;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;

/**
 * Created by GRodrigues17 on 15/01/2017.
 */

public class DetailGalleryAdapter extends SmartFragmentStatePagerAdapter {
    private final FragmentManager context;
    private File[] minhaLista;
    private String nomeMateria;

    public DetailGalleryAdapter(FragmentManager context, File[] minhaLista, String nomeMateria) {
        super(context);
        this.context = context;
        this.minhaLista = minhaLista;
        this.nomeMateria = nomeMateria;
    }


    @Override
    public Fragment getItem(int position) {
        return PageDetailFragment.newInstance(position, nomeMateria);
    }

    @Override
    public int getCount() {
        return minhaLista.length;
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
