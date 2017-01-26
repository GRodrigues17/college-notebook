package br.com.collegenotebook.view.Fragment;

import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.animator.DraggableItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.R;
import br.com.collegenotebook.model.TimeSheet;
import br.com.collegenotebook.view.Adapter.TimeSheetAdapter;

/**
 * Created by GRodrigues17 on 24/10/2016.
 */

public class TimeSheetFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TimeSheetAdapter mAdapter;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;
    private List<TimeSheet> timeSheetList = new ArrayList<>();


    public static TimeSheetFragment newInstance(int someInt, String someTitle) {
        TimeSheetFragment fragmentDemo = new TimeSheetFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("someTitle", someTitle);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prepareSheetData();
        return inflater.inflate(R.layout.content_timesheet, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int SomeInt = getArguments().getInt("someInt", 0);
        String someTitle = getArguments().getString("someTitle", "");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(getContext(), 6, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        // drag & drop manager
       mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();
       mRecyclerViewDragDropManager.setDraggingItemShadowDrawable(
               (NinePatchDrawable) ContextCompat.getDrawable(getContext(), R.drawable.material_shadow_z3));
        // Start dragging after long press
        mRecyclerViewDragDropManager.setInitiateOnLongPress(true);
        mRecyclerViewDragDropManager.setInitiateOnMove(false);
        mRecyclerViewDragDropManager.setLongPressTimeout(750);

        // setup dragging item effects (NOTE: DraggableItemAnimator is required)
        mRecyclerViewDragDropManager.setDragStartItemAnimationDuration(250);
        mRecyclerViewDragDropManager.setDraggingItemAlpha(0.8f);
        mRecyclerViewDragDropManager.setDraggingItemScale(1.3f);
        mRecyclerViewDragDropManager.setDraggingItemRotation(15.0f);

        //adapter
        mAdapter = new TimeSheetAdapter(getContext(), timeSheetList);
        mRecyclerViewDragDropManager.setItemMoveMode(RecyclerViewDragDropManager.ITEM_MOVE_MODE_DEFAULT);
        mAdapter.setItemMoveMode(RecyclerViewDragDropManager.ITEM_MOVE_MODE_DEFAULT);

        mWrappedAdapter = mRecyclerViewDragDropManager.createWrappedAdapter(mAdapter);      // wrap for dragging

        GeneralItemAnimator animator = new DraggableItemAnimator(); // DraggableItemAnimator is required to make item animations properly.

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerViewDragDropManager.attachRecyclerView(mRecyclerView);


    }

    @Override
    public void onPause() {
        mRecyclerViewDragDropManager.cancelDrag();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (mRecyclerViewDragDropManager != null) {
            mRecyclerViewDragDropManager.release();
            mRecyclerViewDragDropManager = null;
        }

        if (mRecyclerView != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }

        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }
        mAdapter = null;
        mLayoutManager = null;

        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }



    private void prepareSheetData() {
        TimeSheet timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("materia");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("portugues");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("matematica");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("historia");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("geografia");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("ingles");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("sociologia");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("filosofia");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("artes");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("literatura");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("LP");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("TI");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("TE");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("SO");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("O&M");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("Biologia");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);

        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);
        timeSheet = new TimeSheet("");
        timeSheetList.add(timeSheet);

    }

}
