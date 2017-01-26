package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

import br.com.collegenotebook.DrawableUtils;
import br.com.collegenotebook.R;
import br.com.collegenotebook.model.TimeSheet;

/**
 * Created by GRodrigues17 on 24/10/2016.
 */

public class TimeSheetAdapter extends RecyclerView.Adapter<TimeSheetAdapter.MyViewHolder>
        implements DraggableItemAdapter<TimeSheetAdapter.MyViewHolder> {

    private Context context;
    private int mLastRemovedPosition = -1;
    private List<TimeSheet> timeSheets;
    TimeSheet timeSheet;
    private static final String TAG = "MyDraggableItemAdapter";
    private int mItemMoveMode = RecyclerViewDragDropManager.ITEM_MOVE_MODE_DEFAULT;

    // NOTE: Make accessible with short name
    private interface Draggable extends DraggableItemConstants {
    }


    public TimeSheetAdapter(Context context, List<TimeSheet> timeSheets) {
        this.context = context;
        this.timeSheets = timeSheets;
        // DraggableItemAdapter requires stable ID, and also
        // have to implement the getItemId() method appropriately.
        setHasStableIds(true);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.class_time_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TimeSheet timeSheet = timeSheets.get(position);
        holder.mTextView.setText(timeSheet.getTimeSheet());

        final int dragState = holder.getDragStateFlags();

        if (((dragState & Draggable.STATE_FLAG_IS_UPDATED) != 0)) {
            int bgResId;

            if ((dragState & Draggable.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_item_dragging_active_state;
                DrawableUtils.clearState(holder.mContainer.getForeground());

            } else if ((dragState & Draggable.STATE_FLAG_DRAGGING) != 0) {
                bgResId = R.drawable.bg_item_dragging_state;
            } else {
                bgResId = R.drawable.bg_item_normal_state;
            }

            holder.mContainer.setBackgroundResource(bgResId);
        }

    }

    @Override
    public int getItemCount() {
        return timeSheets.size();
    }

    @Override
    public long getItemId(int position) {
        return timeSheets.get(position).getId();
    }


    public void setItemMoveMode(int itemMoveMode) {
        mItemMoveMode = itemMoveMode;
    }


    @Override
    public void onMoveItem(int fromPosition, int toPosition) {

        if (fromPosition == toPosition) {
            return;
        }

            moveItem(fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);

    }


    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    public void moveItem(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return;
        }
        final TimeSheet item = timeSheets.remove(fromPosition);

        timeSheets.add(toPosition, item);
        mLastRemovedPosition = -1;

    }


    public static class MyViewHolder extends AbstractDraggableItemViewHolder {
        public FrameLayout mContainer;
        public View mDragHandle;
        public TextView mTextView;

        public MyViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mDragHandle = v.findViewById(R.id.drag_handle);
            mTextView = (TextView) v.findViewById(R.id.text1);
        }
    }
}
