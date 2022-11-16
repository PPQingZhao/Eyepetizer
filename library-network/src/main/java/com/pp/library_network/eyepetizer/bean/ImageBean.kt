package com.pp.library_network.eyepetizer.bean


import com.google.gson.annotations.SerializedName

data class ImageBean(
    @SerializedName("cover")
    val cover: Cover,
    @SerializedName("image_id")
    val imageId: String
)