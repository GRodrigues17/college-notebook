package br.com.collegenotebook.view.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Adapter.DetailGalleryAdapter;
import br.com.collegenotebook.view.Customs.DepthPageTransformer;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;

/**
 * Created by GRodrigues17 on 22/01/2017.
 */

public class PageDetailActivity extends AppCompatActivity {
    private SmartFragmentStatePagerAdapter detailPagerAdapter;
    private ViewPager mViewPager;
    int position;
    String nomeMateria;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.content_detail_pager);
        position = getIntent().getIntExtra("position_number", 0);
        nomeMateria = getIntent().getStringExtra("nome_materia");
        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();

        file = new File(root_sd + "/Mattercam" + "/" + nomeMateria);
        final File[] list = file.listFiles();


        mViewPager = (ViewPager) findViewById(R.id.container_pager_detail);
        // Set up the ViewPager with the sections adapter.
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        detailPagerAdapter = new DetailGalleryAdapter(getSupportFragmentManager(), list, nomeMateria);
        mViewPager.setAdapter(detailPagerAdapter);

        mViewPager.setCurrentItem(position);

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




    }
}
