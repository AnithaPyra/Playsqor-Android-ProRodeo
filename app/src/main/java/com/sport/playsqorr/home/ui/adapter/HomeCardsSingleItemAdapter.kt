package com.sport.playsqorr.home.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sport.playsqorr.R
import com.sport.playsqorr.home.`interface`.IntentInterface
import com.sport.playsqorr.pojos.MyCardsPojo
import com.squareup.picasso.Picasso
import java.util.ArrayList

class HomeCardsSingleItemAdapter(
    var context: Context?,
    var listMyCardPojo: ArrayList<MyCardsPojo>,
    var sportBallIcon: Int,
    var intentInterface : IntentInterface
) : RecyclerView.Adapter<HomeCardsSingleItemAdapter.ViewHolder>() {
    var listSelectedData = ArrayList<MyCardsPojo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_mlb_new, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listSelectedData[position]
        if (context != null) {
            Glide.with(context!!).load(item.playerImageLeft).apply(RequestOptions())
                .into(holder.player1Img)
            Glide.with(context!!).load(item.playerImageRight).apply(RequestOptions())
                .into(holder.player2Img)
        }
        holder.tvCardTitle.text = item.cardTitle
        holder.tvMatchUpType.text = item.cardType
        holder.tvStartTime.text = item.cardTime
        holder.ivSportBall.setImageResource(sportBallIcon)


        holder.cvRoot.setOnClickListener {
            intentInterface.callNextScreen(item.cardId)
        }
    }

    override fun getItemCount(): Int {
        return listSelectedData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var player1Img: ImageView
        var player2Img: ImageView
        var tvCardTitle: TextView
        var tvMatchUpType: TextView
        var tvStartTime: TextView
        var ivSportBall : ImageView
        var cvRoot : CardView

        init {
            player1Img = itemView.findViewById(R.id.player1Img)
            player2Img = itemView.findViewById(R.id.player2Img)
            tvCardTitle = itemView.findViewById(R.id.tvCardTitle)
            tvMatchUpType = itemView.findViewById(R.id.tvMatchUpType)
            tvStartTime = itemView.findViewById(R.id.tvStartTime)
            ivSportBall = itemView.findViewById(R.id.ivSportBall)
            cvRoot = itemView.findViewById(R.id.cvRoot)

        }
    }


    init {
        listSelectedData.clear()
        listSelectedData.addAll(listMyCardPojo)
    }



}