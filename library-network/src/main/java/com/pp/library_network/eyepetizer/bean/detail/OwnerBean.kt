package com.pp.library_network.eyepetizer.bean.detail


import com.google.gson.annotations.SerializedName

data class OwnerBean(
    @SerializedName("actionUrl")
    val actionUrl: String,
    @SerializedName("area")
    val area: Any?,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("bannedDate")
    val bannedDate: Any?,
    @SerializedName("bannedDays")
    val bannedDays: Int,
    @SerializedName("birthday")
    val birthday: Long,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("expert")
    val expert: Boolean,
    @SerializedName("followed")
    val followed: Boolean,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("ifPgc")
    val ifPgc: Boolean,
    @SerializedName("job")
    val job: String,
    @SerializedName("library")
    val library: String,
    @SerializedName("limitVideoOpen")
    val limitVideoOpen: Boolean,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("registDate")
    val registDate: Long,
    @SerializedName("releaseDate")
    val releaseDate: Long,
    @SerializedName("tagIds")
    val tagIds: Any?,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("university")
    val university: String,
    @SerializedName("uploadStatus")
    val uploadStatus: String,
    @SerializedName("userMedalBeanList")
    val userMedalBeanList: Any?,
    @SerializedName("userType")
    val userType: String,
    @SerializedName("username")
    val username: String
)