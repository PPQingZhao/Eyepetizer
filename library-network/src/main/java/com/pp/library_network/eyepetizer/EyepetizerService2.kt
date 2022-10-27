package com.pp.library_network.eyepetizer

import com.pp.library_network.utils.RetrofitUtil

interface EyepetizerService2 {
    companion object {

/*
        const val APP_ID = "ahpagrcrf2p7m6rg"
        const val CID = "53db07b41302a7a3e569a27993ff4f13"
        const val API_KEY = "0530ee4341324ce2b26c23fcece80ea2"
        const val AUTH =
            "uTWtTYVSWAl50Kx2La5nLZrSIox+zS1gRB/KtdP3lBcwWwxRDekPW2EDMmT2AE9WJJ0QbqmSzZXLLmZkJXsa/dj0xNc8GZ0WXf20PLYH+uB3ePfz2u7d+7GSsc0vLkXiiV9vdZtq+rnu6cF4EMCaMIQz1Uf7PPQNia3IdPbC11BdssO5yMx9y/Scytg8tzFmAmaoZ0hSj3cjkOHMlUStSWOoiBClbaY+VsR7cum/pCyRQgJxFCOmh5MWnTdA5RNgF8/3JEBQ06qyClVCCjaXfA=="
        const val UA =
            "EYEPETIZER/7051610 (Redmi K30 5G;android;10;zh_CN;android;7.5.161;cn-bj;xiaomi;53db07b41302a7a3e569a27993ff4f13;WIFI;1080*2261) native/1.0"

*/

        const val APP_ID = "ahpagrcrf2p7m6rg"
        const val API_KEY = "0530ee4341324ce2b26c23fcece80ea2"
        const val AUTH =
            "Ee17OewjhONMFg7wu3+DMjYJ0+7BnnkF6VmMpTPvIUPmdBFN1esyXOPChQuxOgeeYU9Xg1w65fTNRjaSFCef7L11uxUVeN+sVTltut5ZRrkpgcYq7NXODEs/QObe36cdpCK/q6VgJmyngs5L2mQq/RNGsAjAY881y64hHnW9ZQ9kgNZOm01QqwbaptVqSAe9PQojEoUiiFCK0Hf3meOP+5kc/kBuLz/tum4/2miAz7inDALlcQ92IC67cwZiFdwmrSFIm9cMIsSgJF2ieA0gYQ=="
        const val UA =
            "EYEPETIZER/7051610 (ELS-AN00;android;10;zh_CN_#Hans;android;7.5.161;cn-bj;huawei;2248c7390ffd3039d84a554301e0fd73;WIFI;1200*2499) native/1.0"
        const val CID = "2248c7390ffd3039d84a554301e0fd73"

        const val VERSION = 7051610
        const val VERSION_NAME = "7.5.161"


        private const val BASE_URL_V1 = "http://api.eyepetizer.net/"

        const val URL_GET_PAGE = "/v1/card/page/get_page"
        const val URL_FOLLOW = "${URL_GET_PAGE}?page_type=card&page_label=follow"
        const val URL_RECOMMEND = "${URL_GET_PAGE}/v1/card/page/get_page?page_type=card&page_label=recommend"
        const val URL_DAILY = "${URL_GET_PAGE}/v1/card/page/get_page?page_type=card&page_label=daily_issue"

        private val retrofit = RetrofitUtil.createEyeRetrofit(BASE_URL_V1)
        val api: EyepetizerApi by lazy { retrofit.create(EyepetizerApi::class.java) }


        /**
         * 热度排序
         */
        const val SORT_TYPE_HOT = "hot"

        /**
         * 时间排序
         */
        const val SORT_TYPE_TIME = "time"
    }

    object ErrorCode {
        const val SUCCESS = 0
    }

    object CardType {
        const val SET_METRO_LIST = "set_metro_list"
        const val SET_BANNER_LIST = "set_banner_list"
        const val CALL_METRO_LIST = "call_metro_list"
    }

    object MetroType {
        const val VIDEO = "video"
        const val IMAGE = "image"

        class Data {
            // title
            // video_id
            // duration
            // tags
            // play_url
            // preview_url
            // cover
            // author  uid   nick  followed type  avatar.url
            /**
             * "metro_data": {
            "video_id": "314282",
            "title": "极简风动画，装神弄鬼的「皇家牧师」",
            "duration": {
            "value": 110,
            "text": "01:50"
            },
            "play_ctrl": {
            "autoplay": true,
            "autoplay_times": 0,
            "need_wifi": true,
            "need_cellular": true,
            "need_wifi_preload": false
            },
            "play_url": "http://static.thefair.net.cn/eyepetizer/pgc_video/video_summary/314282.mp4",
            "preview_url": "http://static.thefair.net.cn/eyepetizer/pgc_video/video_summary/314282.mp4",
            "recommend_level": "featured",
            "tags": [
            {
            "id": 0,
            "title": "# 动画",
            "link": ""
            }
            ],
            "cover": {
            "url": "http://img.kaiyanapp.com/cover/20220928/6d66ead2bcee6f0181e0a8d06cc917dd.jpg?imageMogr2/auto-orient/thumbnail/1242x/interlace/1/quality/80/format/webp",
            "img_info": {
            "width": 1242,
            "height": 720,
            "scale": 1.73
            }
            },
            "author": {
            "uid": 301178685,
            "nick": "全球动画精选",
            "description": "我们精选全球最好看的动画短片，有趣的人永远不缺童心。",
            "avatar": {
            "url": "http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg?imageMogr2/auto-orient/thumbnail/360x/interlace/1/quality/80/format/webp",
            "img_info": {
            "width": 300,
            "height": 300,
            "scale": 1
            },
            "shape": "circle"
            },
            "link": "eyepetizer://pgc/detail/301178685/?title\u003d%E5%85%A8%E7%90%83%E5%8A%A8%E7%94%BB%E7%B2%BE%E9%80%89\u0026userType\u003dPGC\u0026tabIndex\u003d1",
            "type": "pgc",
            "followed": false
            },
            "resource_id": 314282,
            "resource_type": "pgc_video",
            "hot_value": 0,
            "crop_area": {
            "x": 0,
            "y": 0,
            "width": 1,
            "height": 1,
            "origin_width": 1280,
            "origin_height": 720
            }
            },
             */
        }

        class ResourceType {
            // pgc_video
        }

        object Style {
            const val feed_cover_large_video = "feed_cover_large_video"
            const val slide_cover_image_with_footer = "slide_cover_image_with_footer"
            const val feed_cover_small_video = "feed_cover_small_video"
        }
    }

    object Interaction {
        const val V_SCROLL = "v-scroll"
        const val H_SCROLL = "h-scroll"
    }
}