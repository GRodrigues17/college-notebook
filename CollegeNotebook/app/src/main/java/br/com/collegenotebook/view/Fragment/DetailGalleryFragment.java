package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Adapter.DetailGalleryAdapter;
import br.com.collegenotebook.view.Adapter.PagerAdapter;
import br.com.collegenotebook.view.Customs.DepthPageTransformer;

/**
 * Created by GRodrigues17 on 15/01/2017.
 */

public class DetailGalleryFragment extends Fragment {

    int position;
    private static final String ARG_SECTION_NUMBER = "position_number";
    private static final String ARG_SUBJECT_NAME = "nome_materia";
    String nomeMateria;
    private SmartFragmentStatePagerAdapter detailPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.position = args.getInt(ARG_SECTION_NUMBER);
        this.nomeMateria = args.getString(ARG_SUBJECT_NAME);
    }

    public static DetailGalleryFragment newInstance(int position) {
        DetailGalleryFragment fragment = new DetailGalleryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailGalleryFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_detail_pager, container, false);

        final int pos = getArguments().getInt("position_number");
        nomeMateria = getArguments().getString("nome_materia");

        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();

        file = new File(root_sd + "/Mattercam" + "/" + nomeMateria);
        final File[] list = file.listFiles();

        detailPagerAdapter = new DetailGalleryAdapter(getFragmentManager(), list, nomeMateria);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.container_pager_detail);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setTranslationX(-1 * mViewPager.getWidth() * position);

        mViewPager.setAdapter(detailPagerAdapter);
        mViewPager.setCurrentItem(pos);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //noinspection ConstantConditions
                //setTitle(list.get(position).getName());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return rootView;
    }

}
