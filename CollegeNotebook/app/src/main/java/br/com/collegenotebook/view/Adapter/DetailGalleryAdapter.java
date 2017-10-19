package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.*;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Activity.PageDetailActivity;
import br.com.collegenotebook.view.Fragment.PageDetailFragment;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;


/**
 * Created by Jessica Mendes on 17/10/2017.
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
