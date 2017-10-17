package br.com.collegenotebook.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaouan.revealator.Revealator;

import br.com.collegenotebook.R;

/**
 * Created by Jessica Mendes on 16/10/2017.
 */

public class NotebookEmptyFragment extends Fragment {


    private View theAwesomeView;
    public static NotebookEmptyFragment newInstance(int someInt, String someTitle) {
        NotebookEmptyFragment fragmentDemo = new NotebookEmptyFragment();
        return fragmentDemo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_empty_matter, container, false);
        theAwesomeView = rootView.findViewById(R.id.the_awesome_view);

        Revealator.reveal(theAwesomeView)
                .from(rootView)
                .withCurvedTranslation()
                .withChildsAnimation()
                //.withDelayBetweenChildAnimation(...)
                //.withChildAnimationDuration(...)
                //.withTranslateDuration(...)
                //.withRevealDuration(...)
                //.withEndAction(...)
                .start();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
