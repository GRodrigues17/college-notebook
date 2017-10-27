package br.com.collegenotebook.view.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.collegenotebook.R;
import br.com.collegenotebook.view.Adapter.DetailGalleryAdapter;
import br.com.collegenotebook.view.Customs.DepthPageTransformer;
import br.com.collegenotebook.view.Fragment.SmartFragmentStatePagerAdapter;

/**
 * Created by Jessica Mendes on 25/10/2017.
 */

public class PagerActivity extends AppCompatActivity {
    private int position;
    private String nomeMateria;
    private SmartFragmentStatePagerAdapter detailPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarPager);
        mToolbar.setTitle("Comentários");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        position = getIntent().getIntExtra("position_number", 0);
        nomeMateria = getIntent().getStringExtra("nome_materia");

        File file;
        String root_sd = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File( root_sd +"/Mattercam"+ "/" + nomeMateria) ;

        File list[] = file.listFiles();

        detailPagerAdapter = new DetailGalleryAdapter(getSupportFragmentManager(), list, nomeMateria);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager)findViewById(R.id.container_pager_detail);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setTranslationX(-1 * mViewPager.getWidth() * position);

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
