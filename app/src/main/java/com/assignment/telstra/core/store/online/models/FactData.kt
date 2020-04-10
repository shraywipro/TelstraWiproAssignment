package com.assignment.telstra.core.store.online.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FactData{
        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("description")
        @Expose
        var decription: String? = null

        @SerializedName("imageHref")
        @Expose
        var imageUrl: String? = null

}