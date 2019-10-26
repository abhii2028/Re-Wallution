package com.rewallution.model

import android.os.Parcel
import android.os.Parcelable

class ImageUrl() : Parcelable {

    var raw: String? = ""
    var full: String? = ""
    var regular: String? = ""
    var small: String? = ""
    var thumb: String? = ""

    constructor(parcel: Parcel) : this() {
        raw = parcel.readString()
        full = parcel.readString()
        regular = parcel.readString()
        small = parcel.readString()
        thumb = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(raw)
        parcel.writeString(full)
        parcel.writeString(regular)
        parcel.writeString(small)
        parcel.writeString(thumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageUrl> {
        override fun createFromParcel(parcel: Parcel): ImageUrl {
            return ImageUrl(parcel)
        }

        override fun newArray(size: Int): Array<ImageUrl?> {
            return arrayOfNulls(size)
        }
    }
}