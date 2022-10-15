package com.pp.library_network.eyepetizer

import com.pp.library_network.utils.RetrofitUtil

interface EyepetizerService {
    /*
    首页
     每日推荐:  http://baobab.kaiyanapp.com/api/v5/index/tab/allRec
     日报精选: http://baobab.kaiyanapp.com/api/v5/index/tab/feed?tabIndex=2
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
        相关推荐：http://baobab.kaiyanapp.com/api/v4/video/related?id=13308
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

        // 社区 推荐
        const val URL_COMMUNITY_REC = "${BASE_URL}api/v7/community/tab/rec"

        val retrofit = RetrofitUtil.create(BASE_URL)

    }

    /**
     * item type
     */
    object ItemType {

        const val VIDEO = 0
        const val HEADER_5 = VIDEO + 1
        const val SQUARE_CARD_COLLECTION = HEADER_5 + 1
        const val TEXT_CARD = SQUARE_CARD_COLLECTION + 1
        const val FOLLOW_CARD = TEXT_CARD + 1
        const val VIDEO_SMALL_CARD = FOLLOW_CARD + 1
        const val AUTO_PLAY_FOLLO_WCARD = VIDEO_SMALL_CARD + 1
        const val HORIZONTAL_SCROLL_CARD = AUTO_PLAY_FOLLO_WCARD + 1
        const val COMMUNITY_COLUMN_CARD = HORIZONTAL_SCROLL_CARD + 1

        const val ITEM_END = COMMUNITY_COLUMN_CARD
        const val UNKNOWN = -1

        const val squareCardCollection = "squareCardCollection"
        const val textCard = "textCard"
        const val followCard = "followCard"
        const val videoSmallCard = "videoSmallCard"
        const val autoPlayFollowCard = "autoPlayFollowCard"
        const val header5 = "header5"
        const val video = "video"
        private const val horizontalScrollCard = "horizontalScrollCard"
        private const val communityColumnsCard = "communityColumnsCard"

        private val map by lazy { mutableMapOf<String, Int>() }

        init {
            map[squareCardCollection] = SQUARE_CARD_COLLECTION
            map[textCard] = TEXT_CARD
            map[followCard] = FOLLOW_CARD
            map[videoSmallCard] = VIDEO_SMALL_CARD
            map[squareCardCollection] = SQUARE_CARD_COLLECTION
            map[autoPlayFollowCard] = AUTO_PLAY_FOLLO_WCARD
            map[header5] = HEADER_5
            map[video] = VIDEO
            map[horizontalScrollCard] = HORIZONTAL_SCROLL_CARD
            map[communityColumnsCard] = COMMUNITY_COLUMN_CARD
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

        const val ITEM_COLLECTION = ItemType.ITEM_END + 1
        const val TEXT_CARD = ITEM_COLLECTION + 1
        const val FOLLOW_CARD = TEXT_CARD + 1
        const val VIDEO_BEAN_FOR_CLIENT = FOLLOW_CARD + 1
        const val NORMAL = VIDEO_BEAN_FOR_CLIENT + 1
        const val HORIZONTAL_SCROLL_CARD = NORMAL + 1

        const val ITEM_END = VIDEO_BEAN_FOR_CLIENT

        const val ItemCollection = "ItemCollection"
        const val TextCard = "TextCard"
        const val FollowCard = "FollowCard"
        const val VideoBeanForClient = "VideoBeanForClient"
        const val NORMAL_text = "NORMAL"
        const val HorizontalScrollCard = "HorizontalScrollCard"
        private val map by lazy { mutableMapOf<String, Int>() }

        init {
            map[ItemCollection] = ITEM_COLLECTION
            map[TextCard] = TEXT_CARD
            map[FollowCard] = FOLLOW_CARD
            map[VideoBeanForClient] = VIDEO_BEAN_FOR_CLIENT
            map[NORMAL_text] = NORMAL
            map[HorizontalScrollCard] = HORIZONTAL_SCROLL_CARD
        }

        /**
         * 字符串 data type 转换 int
         */
        fun getItemDataType(type: String): Int {
            return map[type] ?: UNKNOWN
        }
    }

    object ContentType {
        const val VIDEO = 0
        const val UGC_PICTURE = VIDEO + 1

        private const val video = "video"
        private const val ugcPicture = "ugcPicture"

        private val map = mutableMapOf<String, Int>()

        init {
            map[video] = VIDEO
            map[ugcPicture] = UGC_PICTURE
        }

        fun isVideo(type: String?) = map[type] == VIDEO
    }

}