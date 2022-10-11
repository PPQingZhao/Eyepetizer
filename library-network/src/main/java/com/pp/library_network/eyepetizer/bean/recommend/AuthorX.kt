package com.pp.library_network.eyepetizer.bean.recommend

data class AuthorX(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
//    val follow: FollowX,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
//    val shield: ShieldX,
    val videoNum: Int
)