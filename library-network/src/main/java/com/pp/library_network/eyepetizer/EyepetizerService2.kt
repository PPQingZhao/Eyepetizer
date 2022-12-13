package com.pp.library_network.eyepetizer

import com.google.gson.Gson
import com.pp.library_network.eyepetizer.bean.Header
import com.pp.library_network.utils.RetrofitUtil

interface EyepetizerService2 {
    companion object {

        /*

        {
            "udid" = "074347ace25d41df856528937c4a3804eb4fea22",
            "vc" = "7051610",
            "vn" = "7.5.161",
            "deviceModel" = "ELS-AN00",
            "first_channel" = "huawei",
            "size" = "1200X2499",
            "system_version_code" = "29",
            "token" = "43f18a526760c43d",
            "page_type" = "card",
            "page_label" = "follow"
        }
*/

        const val udid = "074347ace25d41df856528937c4a3804eb4fea22"
        const val vc = 7051610
        const val vn = "7.5.161"
        const val deviceModel = "ELS-AN00"
        const val first_channel = "huawei"
        const val size = "1200X2499"
        const val system_version_code = 29
        const val token = "43f18a526760c43d"
        const val page_type = "card"
        const val page_label = "follow"


        // url
        private const val BASE_URL_V1 = "http://api.eyepetizer.net/"
        const val BASE_URL_GET_PAGE = "${BASE_URL_V1}v1/card/page/get_page"
        const val URL_GET_PAGE = "/v1/card/page/get_page"
        const val BASE_URL_TOPIC_HOT = "${URL_GET_PAGE}?page_type=card"

        /*
          用户密码登录
          http://api.eyepetizer.net/v1/user/oauth/password_login?username=17820460461&password=zpq940220&user_type=ugc
         */
        const val BASE_URL_PASSWORD_LOGIN = "${BASE_URL_V1}v1/user/oauth/password_login"

        /**
         * 获取用户信息:http://api.eyepetizer.net/v1/user/center/get_user_info?uid=304922815
         */
        const val BASE_URL_GET_USER_INFO = "${BASE_URL_V1}v1/user/center/get_user_info"

        const val URL_FOLLOW = "${URL_GET_PAGE}?page_type=card&page_label=follow"
        const val URL_DISCOVERY = "${URL_GET_PAGE}?page_type=card&page_label=/discover_v2"
        const val URL_HOT_QUERIES = "/v1/recommend/search/get_hot_queries?"


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
        val headerJson = """
           {
           	"x-api-key": "0530ee4341324ce2b26c23fcece80ea2",
           	"User-Agent": "EYEPETIZER/7051610 (ELS-AN00;android;10;zh_CN_#Hans;android;7.5.161;cn-bj;huawei;2248c7390ffd3039d84a554301e0fd73;WIFI;1200*2499) native/1.0",
           	"X-THEFAIR-APPID": "ahpagrcrf2p7m6rg",
           	"X-THEFAIR-AUTH": "Ee17OewjhONMFg7wu3+DMjYJ0+7BnnkF6VmMpTPvIUPmdBFN1esyXOPChQuxOgeeYU9Xg1w65fTNRjaSFCef7L11uxUVeN+sVTltut5ZRrkpgcYq7NXODEs/QObe36cdpCK/q6VgJmyngs5L2mQq/RNGsAjAY881y64hHnW9ZQ9kgNZOm01QqwbaptVqSAe9PQojEoUiiFCK0Hf3meOP+5kc/kBuLz/tum4/2miAz7jyhLget0KvX3PeT8qOn3CnXbX4i3k27s6Nq7YUI3cSug==",
           	"X-THEFAIR-CID": "2248c7390ffd3039d84a554301e0fd73",
           	"X-THEFAIR-UA": "EYEPETIZER/7051610 (ELS-AN00;android;10;zh_CN_#Hans;android;7.5.161;cn-bj;huawei;2248c7390ffd3039d84a554301e0fd73;WIFI;1200*2499) native/1.0",
           	"Cookie": "ky_udid=074347ace25d41df856528937c4a3804eb4fea22;ky_auth=_V1MTc4MjA0NjA0NjE6MTcwMjA0Mzk2NDQzOToyNWU4ZDM0OTYxNDI4Mjc2MTY5ZTlkZjQ1N2IyZjFmMQ;uid=304922815;login_date=20221213-215924;APPID=ahpagrcrf2p7m6rg;login_type=mobile;PHPSESSID=0d6152199ed1e910bdc98f2ca2b95464",
           	"Host": "api.eyepetizer.net"
           }
        """.trimIndent()
        private val header: Header = Gson().fromJson(headerJson, Header::class.java)
        private val retrofit = RetrofitUtil.createEyeRetrofit(
            BASE_URL_V1,
            null,
            "x-api-key" to header.xApiKey,
            "X-THEFAIR-APPID" to header.xTHEFAIRAPPID,
            "X-THEFAIR-CID" to header.xTHEFAIRCID,
            "X-THEFAIR-AUTH" to header.xTHEFAIRAUTH,
            "X-THEFAIR-UA" to header.xTHEFAIRUA,
            "User-Agent" to header.userAgent,
            "Cookie" to header.cookie,
            "Host" to header.host,
        )

        val api: EyepetizerApi by lazy { retrofit.create(EyepetizerApi::class.java) }
        val userApi: UserApi by lazy { retrofit.create(UserApi::class.java) }
        val itemApi: ItemApi by lazy { retrofit.create(ItemApi::class.java) }



        private val apiMap = mutableMapOf<String, Pair<Class<*>, Any>>()
        fun <clazz : Any> getApi(url: String, c: Class<clazz>): clazz {
            val api = apiMap[url]
            if (null != api && api.first == c) {
                return api.second as clazz
            }

            val createEyeRetrofit = RetrofitUtil.createEyeRetrofit(
                url,
                null,
                "x-api-key" to header.xApiKey,
                "X-THEFAIR-APPID" to header.xTHEFAIRAPPID,
                "X-THEFAIR-CID" to header.xTHEFAIRCID,
                "X-THEFAIR-AUTH" to header.xTHEFAIRAUTH,
                "X-THEFAIR-UA" to header.xTHEFAIRUA,
                "User-Agent" to header.userAgent,
                "Cookie" to header.cookie,
                "Host" to header.host,
            )

            val tarApi = createEyeRetrofit.create(c)
            apiMap[url] = c to tarApi

            return tarApi
        }
    }

    object ErrorCode {
        const val SUCCESS = 0
    }

    object SortType {
        /**
         * 热度排序
         */
        const val SORT_TYPE_HOT = "hot"

        /**
         * 时间排序
         */
        const val SORT_TYPE_TIME = "time"
    }

    object CardType {
        const val SET_METRO_LIST = "set_metro_list"
        const val SET_BANNER_LIST = "set_banner_list"
        const val CALL_METRO_LIST = "call_metro_list"
        const val CALL_CARD_LIST = "call_card_list"
        const val SET_SLIDE_METRO_LIST = "set_slide_metro_list"
        const val SET_WATERFALL_METRO_LIST = "set_waterfall_metro_list"
    }

    object MetroType {
        const val VIDEO = "video"
        const val IMAGE = "image"
        const val item = "item"

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

        object ResourceType {
            // pgc_video
            const val pgc_video = "pgc_video"
            const val ugc_video = "ugc_video"
            const val pgc_picture = "pgc_picture"
        }

        object Style {
            const val feed_cover_large_video = "feed_cover_large_video"
            const val slide_cover_image_with_footer = "slide_cover_image_with_footer"
            const val feed_cover_small_video = "feed_cover_small_video"
            const val feed_cover_large_image = "feed_cover_large_image"
            const val slot_image = "slot_image"
            const val description_text = "description_text"
            const val feed_item_detail = "feed_item_detail"
            const val slide_user = "slide_user"

            // 社区 宫格
            const val waterfall_cover_small_video = "waterfall_cover_small_video"
            const val waterfall_cover_small_image = "waterfall_cover_small_image"

            // 社区 精选热门话题
            const val slide_cover_image_with_title = "slide_cover_image_with_title"

            const val icon_grid = "icon_grid"
            const val more_link = "more_link"
            const val card_title = "card_title"
            const val default_web = "default_web"
            const val stacked_slide_cover_image = "stacked_slide_cover_image"
            const val slide_cover_image = "slide_cover_image"
        }
    }

    object Interaction {
        const val V_SCROLL = "v-scroll"
        const val H_SCROLL = "h-scroll"
    }
}