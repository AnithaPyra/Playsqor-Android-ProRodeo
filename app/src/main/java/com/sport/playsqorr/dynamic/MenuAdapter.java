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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.playsqorr.R;
import com.sport.playsqorr.fragments.HomeFragment;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    ArrayList<MenuData> menuArrayList;
    Context mContext;
    Activity mActivity;
    Fragment objfragment;
    int SelectedIndex = 0;

    public MenuAdapter(ArrayList<MenuData> menuArrayList, Context mContext, Activity mActivity, HomeFragment fragment) {
        this.menuArrayList = menuArrayList;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.objfragment = fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LinearLayout menuLayout;
        public TextView txtMenu;
        public ImageView imgMenuIcon;


        //        private ItemClickListener clickListener;
        public MyViewHolder(View view) {
            super(view);
            menuLayout = (LinearLayout) view.findViewById(R.id.menuLayout);
            txtMenu = (TextView) view.findViewById(R.id.txtMenu);
            imgMenuIcon = (ImageView) view.findViewById(R.id.imgMenuIcon);

        }

        public void setClickListener(AdapterView.OnItemClickListener itemClickListener) {
            this.setClickListener(itemClickListener);// = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            System.out.println("testonclick");
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
        MenuData menulist = menuArrayList.get(position);
        holder.txtMenu.setText(menulist.getStrName());
        if (position == SelectedIndex) {
            holder.menuLayout.setBackgroundResource(R.drawable.corner_radius_black);
            holder.txtMenu.setTextColor(Color.WHITE);
            holder.imgMenuIcon.setImageResource(R.drawable.menu_selected);
        } else {
            holder.menuLayout.setBackgroundColor(Color.WHITE);
            holder.txtMenu.setTextColor(Color.BLACK);
            holder.imgMenuIcon.setImageResource(R.drawable.menu_unselected);
        }
        holder.menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedIndex = position;
                notifyDataSetChanged();
                if (position == 0) {
                    ((HomeFragment) objfragment).refreshSubMenu(menulist.getArrLeague());
                } else {
                    ArrayList<Leagues> leagues = new ArrayList<>();
                    Leagues objLeague = new Leagues();
                    objLeague.setLeagueAbbreviation(menulist.getStrName());
                    leagues.add(0, objLeague);
                    leagues.addAll(menulist.getArrLeague());
                    ((HomeFragment) objfragment).refreshSubMenu(leagues);
                }
            }
        });

    }

    public int getItemCount() {
        return menuArrayList.size();
    }

}
