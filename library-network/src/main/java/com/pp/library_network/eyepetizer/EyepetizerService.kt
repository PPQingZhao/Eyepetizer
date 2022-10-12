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
        const val UNKNOWN = -1
        const val SQUARE_CARD_COLLECTION = 0
        const val TEXT_CARD = SQUARE_CARD_COLLECTION + 1
        const val FOLLOW_CARD = TEXT_CARD + 1
        const val VIDEO_SMALL_CARD = FOLLOW_CARD + 1
        const val AUTOPLAYFOLLOWCARD = VIDEO_SMALL_CARD + 1

        const val squareCardCollection = "squareCardCollection"
        const val textCard = "textCard"
        const val followCard = "followCard"
        const val videoSmallCard = "videoSmallCard"
        const val autoPlayFollowCard = "autoPlayFollowCard"

        private val map by lazy { mutableMapOf<String, Int>() }

        init {
            map.put(squareCardCollection, SQUARE_CARD_COLLECTION)
            map.put(textCard, TEXT_CARD)
            map.put(followCard, FOLLOW_CARD)
            map.put(videoSmallCard, VIDEO_SMALL_CARD)
            map.put(squareCardCollection, SQUARE_CARD_COLLECTION)
            map.put(autoPlayFollowCard, AUTOPLAYFOLLOWCARD)
        }

        /**
         * 字符串item type 转换 int
         */
        fun getItemType(type: String): Int {
            return map[type] ?: UNKNOWN
        }
    }

    /**
     * item data type
     */
    object ItemDataType {
        const val UNKNOWN = -1
        const val ITEM_COLLECTION = 0
        const val TEXT_CARD = ITEM_COLLECTION + 1
        const val FOLLOW_CARD = TEXT_CARD + 1
        const val VIDEO_BEAN_FOR_CLIENT = FOLLOW_CARD + 1

        const val ItemCollection = "ItemCollection"
        const val TextCard = "TextCard"
        const val FollowCard = "FollowCard"
        const val VideoBeanForClient = "VideoBeanForClient"
        private val map by lazy { mutableMapOf<String, Int>() }

        init {
            map.put(ItemCollection, ITEM_COLLECTION)
            map.put(TextCard, TEXT_CARD)
            map.put(FollowCard, FOLLOW_CARD)
            map.put(VideoBeanForClient, VIDEO_BEAN_FOR_CLIENT)
        }

        /**
         * 字符串 data type 转换 int
         */
        fun getItemDataType(type: String): Int {
            return map[type] ?: UNKNOWN
        }
    }


    /**
     * item content type
     */
    object ItemContentType {
        const val UNKNOWN = -1
        const val VIDEO = 1

        const val video = "video"

        private val map by lazy { mutableMapOf<String, Int>() }

        init {
            map.put(video, VIDEO)
        }

        /**
         * 字符串 item content type 转换 int
         */
        fun getItemContentType(type: String): Int {
            return map[type] ?: UNKNOWN
        }
    }

    /**
     * item content data type
     */
    object ItemContentDataType {
        const val UNKNOWN = -1
        const val NORMAL = 1

        const val NORMAL_TEXT = "NORMAL"

        private val map by lazy { mutableMapOf<String, Int>() }

        init {
            map.put(NORMAL_TEXT, NORMAL)
        }

        /**
         * 字符串 item content type 转换 int
         */
        fun getItemContentType(type: String): Int {
            return map[type] ?: UNKNOWN
        }
    }
}