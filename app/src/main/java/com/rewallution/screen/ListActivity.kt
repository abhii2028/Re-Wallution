package com.rewallution.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rewallution.R
import com.rewallution.utility.Constant
import com.rewallution.utility.checkInt
import com.rewallution.utility.checkString
import com.rewallution.utility.setUIDesign
import kotlinx.android.synthetic.main.activity_wallpaper_list.*

class ListActivity : AppCompatActivity() {

    private var imageUrl = ""
    private var imageDrawable: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUIDesign(this@ListActivity,shouldChange = true)
        setContentView(R.layout.activity_wallpaper_list)

        init()
        addListener()
    }

    /**
     * Initialize all the variables and methods
     */
    private fun init() {
        // Intent extras
        if (intent.hasExtra(Constant.EXTRA_IMAGE_URL))
            imageUrl = intent.getStringExtra(Constant.EXTRA_IMAGE_URL).checkString()

        if (intent.hasExtra(Constant.EXTRA_IMAGE_DRAWABLE))
            imageDrawable = intent.getIntExtra(Constant.EXTRA_IMAGE_DRAWABLE,0).checkInt()

        // assign the data to the views
        Glide.with(this@ListActivity)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(getDrawable(imageDrawable))
            .error(getDrawable(imageDrawable))
            .into(profile_image)
    }

    /**
     * Method for all onCLick listeners
     */
    private fun addListener() {
        tvBack.setOnClickListener {
            onBackPressed()
        }
    }
}
