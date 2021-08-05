package com.sport.playsqorr.matchup.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sport.playsqorr.R
import com.sport.playsqorr.adapters.MyCards_Adapter
import com.sport.playsqorr.databinding.Item1PlayerMatchupBinding
import com.sport.playsqorr.databinding.Item2PlayerMatchupBinding

class CardTypeMatchupAdapter(var context : Context):
RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    companion object {
        const val SINGLE_PLAYER = 1
        const val TWO_PLAYER = 2
    }

    class SinglePlayerMatchupViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var singlePlayerBinding: Item1PlayerMatchupBinding =
            Item1PlayerMatchupBinding.bind(view)
    }

    class TwoPlayerMatchupViewholder(view : View):RecyclerView.ViewHolder(view){

        var twoPLayerBinding : Item2PlayerMatchupBinding = Item2PlayerMatchupBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SinglePlayerMatchupViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_1_player_matchup, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }
}