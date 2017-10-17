package br.com.collegenotebook.view.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.collegenotebook.R;

/**
 * Created by GRodrigues17 on 04/10/2016.
 */

public class AlbumEmptyFragment extends Fragment {

    public static AlbumEmptyFragment newInstance() {
        return new AlbumEmptyFragment();
    }

    public AlbumEmptyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_empty_album, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "ALBUM VAZIO" , Toast.LENGTH_SHORT).show();

    }

}
