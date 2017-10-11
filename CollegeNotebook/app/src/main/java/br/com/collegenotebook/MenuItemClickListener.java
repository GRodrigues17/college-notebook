package br.com.collegenotebook;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Jessica Mendes on 10/10/2017.
 */

public interface MenuItemClickListener extends View.OnClickListener{

    boolean onNavigationItemSelected(View item);
    void onClick(View view);
}
