package com.pp.library_network.utils

object PageType {

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