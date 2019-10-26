package com.rewallution.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rewallution.R
import com.rewallution.utility.Constant
import com.rewallution.utility.setUIDesign
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUIDesign(this@HomeActivity, shouldChange = true)
        setContentView(R.layout.activity_home)

        init()
        addListener()
    }

    /**
     * Initialize all the variables and methods
     */
    private fun init() {
        //assigning the images through glide into card favorite and all list
        Glide.with(this@HomeActivity)
            .load(Constant.imageFavURL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(getDrawable(R.drawable.favorites))
            .into(ivFav)

        Glide.with(this@HomeActivity)
            .load(Constant.imageListURL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(getDrawable(R.drawable.list))
            .into(ivAllList)

        Glide.with(this@HomeActivity)
            .load(Constant.imageTrendingURL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(getDrawable(R.drawable.trending))
            .into(ivTrending)

        Glide.with(this@HomeActivity)
            .load(Constant.imageOurPicksURL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(getDrawable(R.drawable.picks))
            .into(ivPicks)
    }

    /**
     * Method for all onCLick listeners
     */
    private fun addListener() {
        cardFav.setOnClickListener(this@HomeActivity)
        cardAllList.setOnClickListener(this@HomeActivity)
        cardTrending.setOnClickListener(this@HomeActivity)
        cardOurPicks.setOnClickListener(this@HomeActivity)
    }

    /**
     * Onclick Method
     * @param view : View of the item
     */
    override fun onClick(view: View?) {
        when (view) {
            //card favorites
            cardFav -> {
                startActivity(
                    Intent(this@HomeActivity, ListActivity::class.java)
                        .putExtra(Constant.EXTRA_IMAGE_URL, Constant.imageFavURL)
                        .putExtra(Constant.EXTRA_IMAGE_DRAWABLE, R.drawable.favorites)
                )
            }

            //card all list
            cardAllList -> {
                startActivity(
                    Intent(this@HomeActivity, ListActivity::class.java)
                        .putExtra(Constant.EXTRA_IMAGE_URL, Constant.imageListURL)
                        .putExtra(Constant.EXTRA_IMAGE_DRAWABLE, R.drawable.list)
                )
            }

            //card trending
            cardTrending -> {
                startActivity(
                    Intent(this@HomeActivity, ListActivity::class.java)
                        .putExtra(Constant.EXTRA_IMAGE_URL, Constant.imageTrendingURL)
                        .putExtra(Constant.EXTRA_IMAGE_DRAWABLE, R.drawable.trending)
                )
            }

            //card our picks
            cardOurPicks -> {
                startActivity(
                    Intent(this@HomeActivity, ListActivity::class.java)
                        .putExtra(Constant.EXTRA_IMAGE_URL, Constant.imageOurPicksURL)
                        .putExtra(Constant.EXTRA_IMAGE_DRAWABLE, R.drawable.picks)
                )
            }

        }
    }

}
