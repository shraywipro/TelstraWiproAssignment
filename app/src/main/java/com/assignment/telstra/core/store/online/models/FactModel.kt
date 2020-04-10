package com.assignment.telstra.core.store.online.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FactModel {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rows")
    @Expose
    var rows: List<FactData>? = null
}