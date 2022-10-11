package com.pp.library_network.eyepetizer.bean.follow

data class Author(
    val adTrack: Any,
    val approvedNotReadyVideoCount: Int,
    val description: String,
    val expert: Boolean,
    val follow: FollowX,
    val icon: String,
    val id: Int,
    val ifPgc: Boolean,
    val latestReleaseTime: Long,
    val link: String,
    val name: String,
    val recSort: Int,
    val shield: Shield,
    val videoNum: Int
)