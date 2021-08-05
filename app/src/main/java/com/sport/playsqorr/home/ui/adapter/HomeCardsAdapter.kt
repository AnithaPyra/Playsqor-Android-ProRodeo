package com.sport.playsqorr.home.ui.adapter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sport.playsqorr.R
import com.sport.playsqorr.home.`interface`.IntentInterface
import com.sport.playsqorr.home.model.SportAndIcon
import com.sport.playsqorr.pojos.MyCardsPojo
import com.sport.playsqorr.views.MoreCards_MatchupScreen
import com.sport.playsqorr.views.OnBoarding
import java.util.*
import kotlin.collections.ArrayList

class HomeCardsAdapter(
    var context: Context?,
    var listMyCardPojo: ArrayList<MyCardsPojo>,
    var sportId: Int,
    var leagueId: Int,
    var intentInterface : IntentInterface
    ) : RecyclerView.Adapter<HomeCardsAdapter.ViewHolder>() {
    var listSelectedData = ArrayList<MyCardsPojo>()
    var adapterHomeCardsSingleItem: HomeCardsSingleItemAdapter? = null
    var listLeagueId = ArrayList<Int>()
    var leageAbbrevation = ""
    var sportName = ""
    var sportIcon =0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_league_list, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listSelectedData[position]



        adapterHomeCardsSingleItem = HomeCardsSingleItemAdapter(
            context,
            getSingleLeagueList(position),
            sportIcon,
            intentInterface
        )
        holder.recyclerviewCardSingleItem.adapter = adapterHomeCardsSingleItem
        holder.tvLeageAbbrevation.text = leageAbbrevation
       /* if(SportAndIcon.map.get(sportName)!= null) {
            sportIcon = SportAndIcon.map.get(sportName)!!
        }*/
        holder.ivSportsBall.setImageResource(sportIcon)

/*
        holder.recyclerviewCardSingleItem.setOnClickListener {

           */
/* val matchup = Intent(context, MoreCards_MatchupScreen::class.java)
            matchup.putExtra("home", "1")
            matchup.putExtra("cardid", item.cardId)
            matchup.putExtra("cardid_title", item.cardTitle)
            matchup.putExtra("cardid_color1", item.leagueId)
            matchup.putExtra("place", "homecards")
            matchup.putExtra("playerid_m", "0")
            activity?.let{
                val intent = Intent (it, Main::class.java)
                it.startActivity(intent)
            }*//*




        }
*/


    }

    override fun getItemCount(): Int {
        setLeagueIdList(sportId,leagueId)

        return listLeagueId.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvLeageAbbrevation : TextView
        var ivSportsBall : ImageView
         var recyclerviewCardSingleItem : RecyclerView

        init {

            tvLeageAbbrevation = itemView.findViewById(R.id.tvLeageAbbrevation)
            ivSportsBall = itemView.findViewById(R.id.ivSportsBall)
            recyclerviewCardSingleItem = itemView.findViewById(R.id.rvLeagueCards)
        }
    }

    fun updateList(sportId: Int, leagueId: Int) {
       // adapterHomeCardsSingleItem?.updateList(sportId,leagueId)
        setLeagueIdList(sportId,leagueId)
        notifyDataSetChanged()

    }

    fun getSingleLeagueList(position: Int):ArrayList<MyCardsPojo>
    {
        var listMyCardPojo = ArrayList<MyCardsPojo>()

        println("")
        for(item in this.listMyCardPojo)
        {


            if (item.leagueId.toInt() == listLeagueId[position]) {
                listMyCardPojo.add(item)
                leageAbbrevation = item.leagueAbbrevation
                sportName = item.sport_name
                sportIcon = SportAndIcon.map.get(sportName)!!
            }
        }

        return listMyCardPojo
    }

    fun setLeagueIdList(sportId:Int,leagueId:Int)
    {
        this.sportId = sportId
        this.leagueId = leagueId
        listLeagueId.clear()
        leageAbbrevation = ""
        sportName = ""
        sportIcon =0
        if(sportId != -1 && leagueId != -1)
        {
            listLeagueId.add(leagueId)

        }
        else if(sportId != -1)
        {
            for(item in listMyCardPojo)
            {
                if(item.sportId == sportId)
                {
                    if(!listLeagueId.contains(item.leagueId.toInt()))
                    {
                        listLeagueId.add(item.leagueId.toInt())
                    }
                }
            }
        }
        else
        {
            for(item in listMyCardPojo)
            {

                    if(!listLeagueId.contains(item.leagueId.toInt()))
                    {
                        listLeagueId.add(item.leagueId.toInt())
                    }

            }
        }
    }
    init {
        listSelectedData.addAll(listMyCardPojo)
    }
}