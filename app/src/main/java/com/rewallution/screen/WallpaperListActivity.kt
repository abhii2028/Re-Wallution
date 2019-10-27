package com.rewallution.screen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.rewallution.R
import com.rewallution.ReWallution
import com.rewallution.adapter.WallpaperListAdapter
import com.rewallution.api.ApiCalls
import com.rewallution.api.WallpaperAPI
import com.rewallution.model.ImageUrl
import com.rewallution.model.Wallpaper
import com.rewallution.utility.Constant
import com.rewallution.utility.checkInt
import com.rewallution.utility.checkString
import com.rewallution.utility.setUIDesign
import kotlinx.android.synthetic.main.activity_wallpaper_list.*

class WallpaperListActivity : AppCompatActivity(), WallpaperListAdapter.OnItemClickListener {

    private var imageUrl = ""
    private var imageDrawable: Int = 0
    private var listType: String = ""

    private var wallpaperList: ArrayList<Wallpaper> = ArrayList()
    private lateinit var wallpaperListAdapter: WallpaperListAdapter

    private var shouldLoadMore = false
    private var pageCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUIDesign(this@WallpaperListActivity, shouldChange = true)
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
            imageDrawable = intent.getIntExtra(Constant.EXTRA_IMAGE_DRAWABLE, 0).checkInt()

        if (intent.hasExtra(Constant.EXTRA_TYPE))
            listType = intent.getStringExtra(Constant.EXTRA_TYPE).checkString()

        // assign the data to the views
        Glide.with(this@WallpaperListActivity)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(getDrawable(imageDrawable))
            .error(getDrawable(imageDrawable))
            .into(profile_image)

        // Set adapter for the first time
        rvWallyList.layoutManager = LinearLayoutManager(this@WallpaperListActivity)
        wallpaperListAdapter =
            WallpaperListAdapter(
                this@WallpaperListActivity,
                this@WallpaperListActivity,
                this@WallpaperListActivity,
                wallpaperList
            )
        rvWallyList.adapter = wallpaperListAdapter

        wallpaperListApiCall(pageCount)

    }

    /**
     * Method for all onCLick listeners
     */
    private fun addListener() {
        tvBack.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * Wallpaper list api call
     */
    private fun wallpaperListApiCall(page: Int, limit: Int = 30) {
        WallpaperAPI(page, limit, listType, object : ApiCalls.APICallback {
            override fun onSuccess(content: JsonElement?) {
                try {
                    var tempWallpaperList: ArrayList<Wallpaper> = ArrayList()
                    if (content != null) {

                        tempWallpaperList =
                            Gson().fromJson<ArrayList<Wallpaper>>(
                                content,
                                object : TypeToken<ArrayList<Wallpaper>>() {}.type
                            )

                        if (tempWallpaperList.size > 0)
                            for (i in 0 until tempWallpaperList.size) {
                                val url =
                                    content.asJsonArray[i].asJsonObject.get("urls").asJsonObject
                                tempWallpaperList[i].imageUrl = Gson().fromJson<ImageUrl>(
                                    url,
                                    object : TypeToken<ImageUrl>() {}.type
                                )
                            }
                    }

                    wallpaperList.addAll(tempWallpaperList)
                    wallpaperListAdapter.notifyDataSetChanged()

                    shouldLoadMore = tempWallpaperList.size >= limit

                    pageCount++

                } catch (e: Exception) {
                    Log.d(ReWallution.TAG, "$e")
                }

            }

            override fun onFailed(content: JsonElement?) {
                Log.d(ReWallution.TAG, "Error")
            }

        }).executeCall()
    }

    /**
     * On item click in the list
     * @param position item position
     */
    override fun onItemClick(position: Int) {

    }

    /**
     * on Last item reached
     */
    fun onLastItemReached() {

        if (shouldLoadMore)
            wallpaperListApiCall(pageCount)
    }
}
