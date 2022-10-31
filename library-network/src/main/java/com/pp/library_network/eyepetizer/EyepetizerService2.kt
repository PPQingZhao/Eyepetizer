package com.pp.library_network.eyepetizer

import com.pp.library_network.utils.RetrofitUtil

interface EyepetizerService2 {
    companion object {
        /*  {
              "x-api-key": "0530ee4341324ce2b26c23fcece80ea2",
              "User-Agent": "EYEPETIZER/7051610 (ELS-AN00;android;10;zh_CN_#Hans;android;7.5.161;cn-bj;huawei;2248c7390ffd3039d84a554301e0fd73;WIFI;1200*2499) native/1.0",
              "X-THEFAIR-APPID": "ahpagrcrf2p7m6rg",
              "X-THEFAIR-AUTH": "Ee17OewjhONMFg7wu3+DMjYJ0+7BnnkF6VmMpTPvIUPmdBFN1esyXOPChQuxOgeeYU9Xg1w65fTNRjaSFCef7L11uxUVeN+sVTltut5ZRrkpgcYq7NXODEs/QObe36cdpCK/q6VgJmyngs5L2mQq/RNGsAjAY881y64hHnW9ZQ9kgNZOm01QqwbaptVqSAe9PQojEoUiiFCK0Hf3meOP+5kc/kBuLz/tum4/2miAz7hbkaQ0MN+5j3PLrwsIXL51HmNQ1QI4rk2W4MDi5GL7jQ==",
              "X-THEFAIR-CID": "2248c7390ffd3039d84a554301e0fd73",
              "X-THEFAIR-UA": "EYEPETIZER/7051610 (ELS-AN00;android;10;zh_CN_#Hans;android;7.5.161;cn-bj;huawei;2248c7390ffd3039d84a554301e0fd73;WIFI;1200*2499) native/1.0",
              "Cookie": "ky_udid=074347ace25d41df856528937c4a3804eb4fea22;ky_auth=;PHPSESSID=83ce5646caf556a20e2b7f076d997eec;APPID=ahpagrcrf2p7m6rg",
              "Host": "api.eyepetizer.net"
          }*/

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
            "Ee17OewjhONMFg7wu3+DMjYJ0+7BnnkF6VmMpTPvIUPmdBFN1esyXOPChQuxOgeeYU9Xg1w65fTNRjaSFCef7L11uxUVeN+sVTltut5ZRrkpgcYq7NXODEs/QObe36cdpCK/q6VgJmyngs5L2mQq/RNGsAjAY881y64hHnW9ZQ9kgNZOm01QqwbaptVqSAe9PQojEoUiiFCK0Hf3meOP+5kc/kBuLz/tum4/2miAz7hbkaQ0MN+5j3PLrwsIXL51HmNQ1QI4rk2W4MDi5GL7jQ=="
        const val USER_AGENT =
            "EYEPETIZER/7051610 (ELS-AN00;android;10;zh_CN_#Hans;android;7.5.161;cn-bj;huawei;2248c7390ffd3039d84a554301e0fd73;WIFI;1200*2499) native/1.0"
        const val UA = USER_AGENT
        const val CID = "2248c7390ffd3039d84a554301e0fd73"

        const val VERSION = 7051610
        const val VERSION_NAME = "7.5.161"


        private const val BASE_URL_V1 = "http://api.eyepetizer.net/"
        const val URL_GET_PAGE = "/v1/card/page/get_page"
        const val BASE_URL_TOPIC_HOT = "${URL_GET_PAGE}/v1/card/page/get_page?page_type=card"

        /*
          用户密码登录
          http://api.eyepetizer.net/v1/user/oauth/password_login?username=17820460461&password=zpq940220&user_type=ugc
         */
        const val BASE_URL_PASSWORD_LOGIN =
            "${URL_GET_PAGE}/v1/user/oauth/password_login?user_type=ugc"

        const val URL_FOLLOW = "${URL_GET_PAGE}?page_type=card&page_label=follow"
        const val URL_RECOMMEND = "${URL_GET_PAGE}/v1/card/page/get_page?page_type=card&page_label=recommend"
        const val URL_DAILY = "${URL_GET_PAGE}/v1/card/page/get_page?page_type=card&page_label=daily_issue"
        const val URL_DISCOVERY = "${URL_GET_PAGE}?page_type=card&page_label=/discover_v2"

        /*
           val newRequest = original.newBuilder()
                           .header("x-api-key", API_KEY)
                          .header("X-THEFAIR-APPID", APP_ID)
                          .header("X-THEFAIR-CID", CID)
                          .header("X-THEFAIR-AUTH", AUTH)
                          .header("X-THEFAIR-UA", UA)
                          .header("User-Agent", UA)
                          .method(original.method(), original.body())
                          .build()
              * */
        private val retrofit = RetrofitUtil.createEyeRetrofit(
            BASE_URL_V1,
            "x-api-key" to API_KEY,
            "X-THEFAIR-APPID" to APP_ID,
            "X-THEFAIR-CID" to CID,
            "X-THEFAIR-AUTH" to AUTH,
            "X-THEFAIR-UA" to UA,
            "User-Agent" to USER_AGENT
        )
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
        }

        class ResourceType {
            // pgc_video
        }

        object Style {
            const val feed_cover_large_video = "feed_cover_large_video"
            const val slide_cover_image_with_footer = "slide_cover_image_with_footer"
            const val feed_cover_small_video = "feed_cover_small_video"
            const val feed_cover_large_image = "feed_cover_large_image"
        }
    }

    object Interaction {
        const val V_SCROLL = "v-scroll"
        const val H_SCROLL = "h-scroll"
    }
}