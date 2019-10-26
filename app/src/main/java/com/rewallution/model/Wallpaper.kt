package com.rewallution.model

import android.os.Parcel
import android.os.Parcelable

class Wallpaper() : Parcelable {

    var id: String? = ""
    var created_at: String? = ""
    var updated_at: String? = ""
    var promoted_at: String? = ""
    var width: String? = ""
    var height: String? = ""
    var color: String? = ""
    var description: String? = ""
    var alt_description: String? = ""
    var likes: Int? = 0
    var liked_by_user: Boolean? = false
    var imageUrl: ImageUrl? = ImageUrl()
    var imageLink: ImageLink? = ImageLink()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        created_at = parcel.readString()
        updated_at = parcel.readString()
        promoted_at = parcel.readString()
        width = parcel.readString()
        height = parcel.readString()
        color = parcel.readString()
        description = parcel.readString()
        alt_description = parcel.readString()
        likes = parcel.readValue(Int::class.java.classLoader) as? Int
        liked_by_user = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        imageUrl = parcel.readParcelable(ImageUrl::class.java.classLoader)
        imageLink = parcel.readParcelable(ImageLink::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeString(promoted_at)
        parcel.writeString(width)
        parcel.writeString(height)
        parcel.writeString(color)
        parcel.writeString(description)
        parcel.writeString(alt_description)
        parcel.writeValue(likes)
        parcel.writeValue(liked_by_user)
        parcel.writeParcelable(imageUrl, flags)
        parcel.writeParcelable(imageLink, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wallpaper> {
        override fun createFromParcel(parcel: Parcel): Wallpaper {
            return Wallpaper(parcel)
        }

        override fun newArray(size: Int): Array<Wallpaper?> {
            return arrayOfNulls(size)
        }
    }

}