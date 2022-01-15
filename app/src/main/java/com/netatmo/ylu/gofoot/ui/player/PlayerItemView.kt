package com.netatmo.ylu.gofoot.ui.player

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.model.Player
import com.netatmo.ylu.gofoot.util.getDimension
import com.squareup.picasso.Picasso

class PlayerItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val avatarIv: ImageView
    private val nameTv: TextView

    var player: Player? = null
        set(value) {
            value?.let {
                Picasso.get().load(it.photo).into(avatarIv)
                nameTv.text = it.name
            }
            field = value
        }

    init {
        inflate(context, R.layout.item_player, this)
        setPadding(context.getDimension(R.dimen.default_padding))

        avatarIv = findViewById(R.id.item_player_avatar)
        nameTv = findViewById(R.id.item_player_name)
    }
}