package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.text.Layout;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import br.com.collegenotebook.R;
import br.com.collegenotebook.model.Materia;

/**
 * Created by GRodrigues17 on 28/09/2016.
 */
public class SubjectAdapter extends BaseAdapter {
    private Context context;

    private List<Materia> materias;
    private LayoutInflater inflater;
    private ViewHolder holder;

    public SubjectAdapter(Context context, List<Materia> materias) {
        super();
        this.context = context;
        this.materias = materias;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void notifyDataSetChanged() {
        try {
            super.notifyDataSetChanged();
        } catch (Exception e) {
            trace("Erro: dessa merda " + e.getMessage());
        }
    }

    private void trace(String msg) {
        toast(msg);
    }

    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public int getCount() {
        return materias.size();
    }

    public void remove(final Materia materia) {
        this.materias.remove(materia);
    }

    public void add(final Materia materia) {
        this.materias.add(materia);
    }

    public Object getItem(int position) {
        return materias.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup viewGroup2) {
        try {
            Materia materia = materias.get(position);


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.class_name_item, null);

                holder = new ViewHolder();
                holder.txtMateria = (TextView) convertView.findViewById(R.id.class_name);
                holder.txtProfessor = (TextView) convertView.findViewById(R.id.professor_name);
                holder.viewColor= (View) convertView.findViewById(R.id.viewColor);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }
            holder.txtMateria.setText(materia.getNome());
            holder.txtProfessor.setText(materia.getProfessor());
            holder.viewColor.setBackgroundResource(R.color.colorPrimary);


            return convertView;

        } catch (Exception e) {
            trace("Erro: " + e.getMessage());
        }

        return convertView;
    }

    static class ViewHolder {
        public TextView txtMateria;
        public TextView txtProfessor;
        public View viewColor;
    }


}