package com.sport.playsqorr.matchup.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sport.playsqorr.R

class CardTypeMatchupActivity : AppCompatActivity() {
    lateinit var ivBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_type_matchup)

        ivBack = findViewById(R.id.ivBack)

        ivBack.setOnClickListener{
            onBackPressed()
        }
    }
}