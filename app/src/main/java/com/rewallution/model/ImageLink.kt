package com.rewallution.model

import android.os.Parcel
import android.os.Parcelable

class ImageLink() : Parcelable {
    var self: String? = ""
    var html: String? = ""
    var download: String? = ""
    var download_location: String? = ""

    constructor(parcel: Parcel) : this() {
        self = parcel.readString()
        html = parcel.readString()
        download = parcel.readString()
        download_location = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(self)
        parcel.writeString(html)
        parcel.writeString(download)
        parcel.writeString(download_location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageLink> {
        override fun createFromParcel(parcel: Parcel): ImageLink {
            return ImageLink(parcel)
        }

        override fun newArray(size: Int): Array<ImageLink?> {
            return arrayOfNulls(size)
        }
    }
}