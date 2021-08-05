package com.sport.playsqorr.home.ui.adapter

import com.sport.playsqorr.dynamic.MenuData
import android.app.Activity
import android.content.Context
import android.graphics.Color
import com.sport.playsqorr.home.ui.fragment.HomeFragment
import com.sport.playsqorr.SportIdMatchupTypeInterface
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.AdapterView.OnItemClickListener
import com.sport.playsqorr.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sport.playsqorr.dynamic.Leagues
import com.sport.playsqorr.home.model.SportAndIcon
import java.util.ArrayList

class MenuAdapter(
    var menuArrayList: ArrayList<MenuData>,
    var mContext: Context,
    var mActivity: Activity,
    fragment: HomeFragment,
    sportIdMatchupTypeInterface: SportIdMatchupTypeInterface
) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {
    var objfragment: Fragment
    var SelectedIndex = 0
    var sportIdMatchupTypeInterface: SportIdMatchupTypeInterface

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var menuLayout: LinearLayout
        var txtMenu: TextView
        var imgMenuIcon: AppCompatImageView
        fun setClickListener(itemClickListener: OnItemClickListener?) {
            setClickListener(itemClickListener) // = itemClickListener;
        }

        override fun onClick(v: View) {
            println("testonclick")
        }

        //        private ItemClickListener clickListener;
        init {
            menuLayout = view.findViewById<View>(R.id.menuLayout) as LinearLayout
            txtMenu = view.findViewById<View>(R.id.txtMenu) as TextView
            imgMenuIcon = view.findViewById<View>(R.id.imgMenuIcon) as AppCompatImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_itemview, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menulist = menuArrayList[position]
        println("jira menuadapter onBindViewHolder ${menulist.strName}" +
                " position $position")
        holder.txtMenu.text = menulist.strName

        if(SportAndIcon.map.containsKey(menulist.strName)) {
            SportAndIcon.map.get(menulist.strName)
                ?.let { holder.imgMenuIcon.setBackgroundResource(it) }
        }
        if (position == SelectedIndex) {
            holder.menuLayout.setBackgroundResource(R.drawable.corner_radius_black)
            holder.txtMenu.setTextColor(Color.WHITE)
          //  holder.imgMenuIcon.setImageResource(R.drawable.menu_selected)
            println("jira menuadapter SelectedIndex $SelectedIndex")
            holder.imgMenuIcon.supportBackgroundTintList = ContextCompat.getColorStateList(mContext, R.color.ball_selected_color)
           // holder.imgMenuIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
        } else {
            println("jira menuadapter SelectedIndex ")

            holder.menuLayout.setBackgroundColor(Color.WHITE)
            holder.txtMenu.setTextColor(Color.BLACK)
          //  holder.imgMenuIcon.setImageResource(R.drawable.menu_unselected)
            holder.imgMenuIcon.supportBackgroundTintList = ContextCompat.getColorStateList(mContext, R.color.ball_default_color)

            //  holder.imgMenuIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.ball_default_color), android.graphics.PorterDuff.Mode.MULTIPLY);

        }
        holder.menuLayout.setOnClickListener {
            SelectedIndex = position
            notifyDataSetChanged()
            if (position == 0) {
                (objfragment as HomeFragment).refreshSubMenu(menulist.arrLeague)
                sportIdMatchupTypeInterface.setSportAndMatchupType(-1, -1)
            } else {
                val leagues = ArrayList<Leagues?>()
                val objLeague = Leagues()
                objLeague.setLeagueAbbreviation(menulist.strName)
                leagues.add(0, objLeague)
                leagues.addAll(menulist.arrLeague)
                (objfragment as HomeFragment).refreshSubMenu(leagues)
                sportIdMatchupTypeInterface.setSportAndMatchupType(
                    menulist.arrLeague[0].sportId,
                    -1
                )
            }
        }
    }

    override fun getItemCount(): Int {
        println("jira menuadapter getItemCount ${menuArrayList.size}")
        return menuArrayList.size
    }

    init {
        objfragment = fragment
        this.sportIdMatchupTypeInterface = sportIdMatchupTypeInterface
    }

    fun resetSportTabColor()
    {

    }

    fun setSportTabBackgroundSelected()
    {

    }
}