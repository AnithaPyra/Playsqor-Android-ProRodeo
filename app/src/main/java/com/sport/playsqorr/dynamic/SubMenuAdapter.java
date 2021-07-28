package com.sport.playsqorr.dynamic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sport.playsqorr.R;

import java.util.ArrayList;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.MyViewHolder> {
    ArrayList<Leagues> subMenuArrayList;
    Context mContext;
    Activity mActivity;
    int SelectedIndex = 0;


    public SubMenuAdapter(ArrayList<Leagues> subMenuArrayList, Context mContext, Activity mActivity) {
        this.subMenuArrayList = subMenuArrayList;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public LinearLayout SubmenuLayout;
        public TextView txtMenu;
        public ImageView imgMenuIcon;


//        private ItemClickListener clickListener;
        public MyViewHolder(View view) {
            super(view);
            SubmenuLayout = (LinearLayout) view.findViewById(R.id.menuLayout);
            txtMenu = (TextView) view.findViewById(R.id.txtMenu);
            imgMenuIcon = (ImageView) view.findViewById(R.id.imgMenuIcon);

        }
        public void setClickListener(AdapterView.OnItemClickListener itemClickListener) {
//            this.clickListener = itemClickListener;
        this.setClickListener(itemClickListener);
        }


        @Override
        public void onClick(View v) {

        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_itemview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Leagues arrSubMenu = subMenuArrayList.get(position);
        holder.txtMenu.setText(arrSubMenu.leagueAbbreviation);
        if (position == SelectedIndex) {
            holder.SubmenuLayout.setBackgroundResource(R.drawable.corner_radius_black);
            holder.txtMenu.setTextColor(Color.WHITE);
            holder.imgMenuIcon.setImageResource(R.drawable.menu_selected);
        } else {
            holder.SubmenuLayout.setBackgroundColor(Color.WHITE);
            holder.txtMenu.setTextColor(Color.BLACK);
            holder.imgMenuIcon.setImageResource(R.drawable.menu_unselected);
        }

    }

    public int getItemCount() {
        return subMenuArrayList.size();
    }

}
