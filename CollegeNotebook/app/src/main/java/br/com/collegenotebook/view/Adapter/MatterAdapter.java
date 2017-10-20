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
import br.com.collegenotebook.controller.BaseController;
import br.com.collegenotebook.controller.LoginController;
import br.com.collegenotebook.controller.NotebookController;
import br.com.collegenotebook.model.Matter;
import br.com.collegenotebook.model.User;
import br.com.collegenotebook.view.Fragment.NotebookFragment;

/**
 * Created by GRodrigues17 on 28/09/2016.
 */
public class MatterAdapter extends BaseAdapter {
    private Context context;
    private Matter matter;
    private List<Matter> matters;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private BaseController controller;

    public MatterAdapter(Context context, List<Matter> matters) {
        super();
        this.context = context;
        this.matters = matters;
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
        return matters.size();
    }

    public void remove(final Matter matter) {
        this.matters.remove(matter);
    }

    public void add(final Matter matter) {
        this.matters.add(matter);
    }

    public Object getItem(int position) {
        return matters.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup viewGroup2) {
        controller = new BaseController(context);
        try {

            matter = matters.get(position);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.class_name_item, null);

                holder = new ViewHolder();
                holder.txtMateria = (TextView) convertView.findViewById(R.id.class_name);
                holder.txtProfessor = (TextView) convertView.findViewById(R.id.professor_name);
                holder.viewColor = (View) convertView.findViewById(R.id.viewColor);
                holder.viewFav = (View) convertView.findViewById(R.id.layoutFavItem);
                holder.viewIconFav = (LikeButton) convertView.findViewById(R.id.iconFav);
                holder.dateCreated = (TextView) convertView.findViewById(R.id.txtDateCreate);
                holder.viewIconFav.setIcon(IconType.Star);
                holder.viewIconFav.setEnabled(true);
                holder.viewIconFav.setIconSizeDp(20);
                holder.viewIconFav.setCircleStartColorRes(R.color.colorAccent);
                holder.viewIconFav.setCircleEndColorRes(R.color.colorPrimary);
                holder.viewIconFav.setLikeDrawableRes(R.drawable.star_on);
                holder.viewIconFav.setUnlikeDrawableRes(R.drawable.star_off);
                holder.viewIconFav.setExplodingDotColorsRes(R.color.colorPrimary, R.color.colorAccent);
                holder.viewIconFav.setAnimationScaleFactor(2);
                if (matter.getLike()==1){
                    holder.viewIconFav.setLiked(true);
                }else {
                    holder.viewIconFav.setLiked(false);
                }


                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }
            holder.txtMateria.setText(matter.getTitle());
            holder.txtProfessor.setText(matter.getInstructor());
            holder.viewColor.setBackgroundColor(matter.getColor());
            holder.dateCreated.setText(matter.getDate());



            holder.viewIconFav.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {matter.setLike(1);
                    controller.isLike(1,matter.getId());
                    notifyDataSetChanged();
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    matter.setLike(0);
                    controller.isLike(0,matter.getId());
                    notifyDataSetChanged();
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
        public TextView dateCreated;
    }




}
