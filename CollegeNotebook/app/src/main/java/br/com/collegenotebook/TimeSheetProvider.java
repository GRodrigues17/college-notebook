package br.com.collegenotebook;

import br.com.collegenotebook.model.TimeSheet;

/**
 * Created by GRodrigues17 on 24/10/2016.
 */
public abstract class TimeSheetProvider {

    public static abstract class TimeSheet {
        public abstract long getId();

        public abstract boolean isSectionHeader();

        public abstract int getViewType();

        public abstract String getText();

        public abstract void setPinned(boolean pinned);

        public abstract boolean isPinned();
    }

    public abstract int getCount();

    public abstract br.com.collegenotebook.model.TimeSheet getItem(int index);

    public abstract void removeItem(int position);

    public abstract void moveItem(int fromPosition, int toPosition);

    public abstract void swapItem(int fromPosition, int toPosition);

    public abstract int undoLastRemoval();
}