package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.collegenotebook.R;
import br.com.collegenotebook.model.TimeSheet;

/**
 * Created by GRodrigues17 on 31/10/2016.
 */

public class TimeSheetAdap extends BaseAdapter {

    private Context context;

    private List<TimeSheet> timeSheets;
    private LayoutInflater inflater;
    private TimeSheetAdap.ViewHolder holder;

    public TimeSheetAdap(Context context, List<TimeSheet> timeSheets) {
        super();
        this.context = context;
        this.timeSheets = timeSheets;
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup viewGroup2) {
        try {
            TimeSheet timeSheet = timeSheets.get(position);


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.class_time_item, null);

                holder = new TimeSheetAdap.ViewHolder();
                holder.txtTimeSheet = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);
                convertView.setLayoutParams(new GridView.LayoutParams(400, 500));
                convertView.setPadding(5, 5, 5, 5);

            } else {
                holder = (TimeSheetAdap.ViewHolder) convertView.getTag();

            }
            holder.txtTimeSheet.setText(timeSheet.getTimeSheet());


            return convertView;

        } catch (Exception e) {
            trace("Erro: " + e.getMessage());
        }

        return convertView;
    }

    private void trace(String msg) {
        toast(msg);
    }

    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return timeSheets.size();
    }

    @Override
    public Object getItem(int position) {
        return timeSheets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {
        public TextView txtTimeSheet;
    }
}
