package br.com.collegenotebook.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.like.IconType;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

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


    public View getView(final int position, View convertView, ViewGroup viewGroup2) {
        try {
            Materia materia = materias.get(position);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.class_name_item, null);

                holder = new ViewHolder();
                holder.txtMateria = (TextView) convertView.findViewById(R.id.class_name);
                holder.txtProfessor = (TextView) convertView.findViewById(R.id.professor_name);
                holder.viewColor= (View) convertView.findViewById(R.id.viewColor);
                holder.viewFav= (View) convertView.findViewById(R.id.layoutFavItem);
                holder.viewIconFav= (LikeButton) convertView.findViewById(R.id.iconFav);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }
            holder.txtMateria.setText(materia.getNome());
            holder.txtProfessor.setText(materia.getProfessor());
            holder.viewColor.setBackgroundResource(R.color.colorPrimary);
            holder.viewIconFav.setIcon(IconType.Star);
            holder.viewIconFav.setLiked(false);
            holder.viewIconFav.setEnabled(true);
            holder.viewIconFav.setIconSizeDp(20);
            holder.viewIconFav.setCircleStartColorRes(R.color.colorAccent);
            holder.viewIconFav.setCircleEndColorRes(R.color.colorPrimary);
            holder.viewIconFav.setLikeDrawableRes(R.drawable.star_on);
            holder.viewIconFav.setUnlikeDrawableRes(R.drawable.star_off);
            holder.viewIconFav.setExplodingDotColorsRes(R.color.colorPrimary,R.color.colorAccent);
            holder.viewIconFav.setAnimationScaleFactor(2);
            holder.viewIconFav.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {


                }

                @Override
                public void unLiked(LikeButton likeButton) {


                }
            });
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
        public View viewFav;
        public LikeButton viewIconFav;
    }


   // private boolean onFavClick(int position,boolean checked) {
//        if (checked) {
//            holder.viewIconFav.setBackgroundResource(R.mipmap.ic_fav_enable);
//            return true;
//
//        } else
//            holder.viewIconFav.setBackgroundResource(R.mipmap.ic_fav_unable);
//        return false;

    //}




}
