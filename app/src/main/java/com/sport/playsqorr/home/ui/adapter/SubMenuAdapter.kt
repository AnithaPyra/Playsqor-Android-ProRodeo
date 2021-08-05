package com.sport.playsqorr.home.ui.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sport.playsqorr.R
import com.sport.playsqorr.SportIdMatchupTypeInterface
import com.sport.playsqorr.dynamic.Leagues
import com.sport.playsqorr.home.model.SportAndIcon
import java.util.*

class SubMenuAdapter(
    var subMenuArrayList: ArrayList<Leagues?>?, var mContext: Context, var mActivity: Activity,
    var sportIdMatchupTypeInterface: SportIdMatchupTypeInterface
) : RecyclerView.Adapter<SubMenuAdapter.MyViewHolder>() {
    var SelectedIndex = 0

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var SubmenuLayout: LinearLayout
        var txtMenu: TextView
        var imgMenuIcon: AppCompatImageView
        fun setClickListener(itemClickListener: OnItemClickListener?) {
//            this.clickListener = itemClickListener;
            setClickListener(itemClickListener)
        }

        override fun onClick(v: View) {}

        //        private ItemClickListener clickListener;
        init {
            SubmenuLayout = view.findViewById<View>(R.id.menuLayout) as LinearLayout
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
        val arrSubMenu = subMenuArrayList?.get(position)


        if (arrSubMenu != null) {
            if(SportAndIcon.map.containsKey(arrSubMenu.leagueAbbreviation)) {
                SportAndIcon.map.get(arrSubMenu.leagueAbbreviation)
                    ?.let { holder.imgMenuIcon.setBackgroundResource(it) }
            }
           /* else{
                holder.imgMenuIcon.visibility = View.GONE
            }*/
        }
        if (position == SelectedIndex) {
            holder.SubmenuLayout.setBackgroundResource(R.drawable.corner_radius_black)
            holder.txtMenu.setTextColor(Color.WHITE)
            holder.imgMenuIcon.supportBackgroundTintList = ContextCompat.getColorStateList(mContext, R.color.ball_selected_color)

            //  holder.imgMenuIcon.setImageResource(R.drawable.menu_selected)
        } else {
            holder.SubmenuLayout.setBackgroundColor(Color.WHITE)
            holder.txtMenu.setTextColor(Color.BLACK)
            holder.imgMenuIcon.supportBackgroundTintList = ContextCompat.getColorStateList(mContext, R.color.ball_default_color)

            //  holder.imgMenuIcon.setImageResource(R.drawable.menu_unselected)
        }
        if (arrSubMenu != null) {
            println("jira submenuadapter ${arrSubMenu.leagueAbbreviation}")
            holder.txtMenu.text = arrSubMenu.leagueAbbreviation
        }
        holder.txtMenu.setOnClickListener {
            SelectedIndex = position
            notifyDataSetChanged()
            if (position == 0) {
                sportIdMatchupTypeInterface.setSportAndMatchupType(-2, -1)
            } else {
                if (arrSubMenu != null) {
                    sportIdMatchupTypeInterface.setSportAndMatchupType(
                        arrSubMenu.sportId,
                        arrSubMenu.leagueId
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return subMenuArrayList?.size!!
    }
}