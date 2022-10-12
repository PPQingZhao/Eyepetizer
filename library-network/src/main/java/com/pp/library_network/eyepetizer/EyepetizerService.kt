package com.pp.library_network.eyepetizer

import com.pp.library_network.utils.RetrofitUtil

interface EyepetizerService {
    /*
    首页
     每日推荐:  http://baobab.kaiyanapp.com/api/v5/index/tab/allRec
     日报精选: http://baobab.kaiyanapp.com/api/v5/index/tab/feed
     关注:  http://baobab.kaiyanapp.com/api/v6/community/tab/follow

    社区
       推荐:  http://baobab.kaiyanapp.com/api/v7/community/tab/rec

    发现
        发现更多: http://baobab.kaiyanapp.com/api/v7/index/tab/discovery

    通知
        主题： http://baobab.kaiyanapp.com/api/v7/tag/tabList
        通知：  http://baobab.kaiyanapp.com/api/v3/messages
        互动：  http://baobab.kaiyanapp.com/api/v7/topic/list

     视频详情页
        相关推荐：http://baobab.kaiyanapp.com/api/v4/video/related?id=186856
                 ======>>当前播放视频的id，从跳转页面视频item中获取
        评论：http://baobab.kaiyanapp.com/api/v2/replies/video?videoId=186856
                 ======>> 当前播放视频的id，从跳转页面视频item中获取
     */
    companion object {
        // 开眼视频 base url
        private const val BASE_URL = "http://baobab.kaiyanapp.com/"

        // 推荐
        const val URL_RECOMMEND = "${BASE_URL}api/v5/index/tab/allRec"

        // 日报精选
        const val URL_FEED = "${BASE_URL}api/v5/index/tab/feed"

        // 关注
        const val URL_FOLLOW = "${BASE_URL}api/v6/community/tab/follow"

        val retrofit = RetrofitUtil.create(BASE_URL)

    }

    /**
     * item type
     */
    object ItemType {
        val UNKNOWN = -1
        val SQUARE_CARD_COLLECTION = 0
        val TEXT_CARD = SQUARE_CARD_COLLECTION + 1
        val FOLLOW_CARD = TEXT_CARD + 1
        val VIDEO_SMALL_CARD = FOLLOW_CARD + 1
        val AUTOPLAYFOLLOWCARD = VIDEO_SMALL_CARD + 1


        /**
         * 字符串item type 转换 int
         */
        fun getItemType(type: String): Int {
            return when (type) {
                "squareCardCollection" -> SQUARE_CARD_COLLECTION
                "textCard" -> TEXT_CARD
                "followCard" -> FOLLOW_CARD
                "videoSmallCard" -> VIDEO_SMALL_CARD
                "autoPlayFollowCard" -> AUTOPLAYFOLLOWCARD

                else -> UNKNOWN
            }
        }
    }

    /**
     * item data type
     */
    object ItemDataType {
        val UNKNOWN = -1
        val ITEM_COLLECTION = 0
        val TEXT_CARD = ITEM_COLLECTION + 1
        val FOLLOW_CARD = TEXT_CARD + 1
        val VIDEO_BEAN_FOR_CLIENT = FOLLOW_CARD + 1


        /**
         * 字符串 data type 转换 int
         */
        fun getItemDataType(type: String): Int {
            return when (type) {
                "ItemCollection" -> ITEM_COLLECTION
                "TextCard" -> TEXT_CARD
                "FollowCard" -> FOLLOW_CARD
                "VideoBeanForClient" -> VIDEO_BEAN_FOR_CLIENT

                else -> UNKNOWN
            }
        }
    }


    /**
     * item content type
     */
    object ItemContentType {
        val UNKNOWN = -1
        val VIDEO = 1

        /**
         * 字符串 item content type 转换 int
         */
        fun getItemContentType(type: String): Int {
            return when (type) {
                "video" -> VIDEO
                else -> UNKNOWN
            }
        }
    }

    /**
     * item content data type
     */
    object ItemContentDataType {
        val UNKNOWN = -1
        val NORMAL = 1

        /**
         * 字符串 item content type 转换 int
         */
        fun getItemContentType(type: String): Int {
            return when (type) {
                "NORMAL" -> NORMAL
                else -> UNKNOWN
            }
        }
    }
}