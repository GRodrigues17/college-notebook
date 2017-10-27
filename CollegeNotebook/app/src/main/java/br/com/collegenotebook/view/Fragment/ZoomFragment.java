package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.collegenotebook.R;
import br.com.collegenotebook.model.Task;
import br.com.collegenotebook.view.Adapter.TaskAdapter;

/**
 * Created by Jessica Mendes on 22/10/2017.
 */

public class ZoomFragment extends Fragment {

    private String nomeMateria;
    private int position;
    public static ZoomFragment newInstance(int position, String nomeMateria) {
        ZoomFragment fragmentDemo = new ZoomFragment();
        Bundle args = new Bundle();
        args.putInt("position_number", position);
        args.putString("nome_materia", nomeMateria);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_zoom, container, false);
        final PhotoView photoView = (PhotoView) rootView.findViewById(R.id.iv_zoom);
        position = getArguments().getInt("position_number");
        nomeMateria = getArguments().getString("nome_materia");
        Picasso.with(getActivity())
                .load(position) // load minha lista de arquivos
                .placeholder(R.drawable.placeholder) //uma tipo de load
                .error(R.drawable.error)
                .fit()
                .centerInside()
                .tag(getActivity())
                .into(photoView);

        return rootView;
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
