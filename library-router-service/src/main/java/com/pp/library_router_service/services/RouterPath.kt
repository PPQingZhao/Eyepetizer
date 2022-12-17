package com.pp.library_router_service.services

object RouterPath {
    object Home {
        const val fragment_home = "/home/fragment_home"
    }

    object Community {
        const val fragment_community = "/community/fragment_community"
    }

    object ItemDetails {
        const val activity_video_details = "/details/activity_video_details"
        const val activity_image_details = "/details/activity_image_details"
        const val activity_small_video_details = "/details/activity_small_video_details"
    }

    object Comments {
        const val fragment_comments = "/comments/fragment_comments"
    }

    object User {
        const val activity_login = "/user/activity_login"
        const val fragment_user = "/user/fragment_user"
    }

    object Search {
        const val activity_search = "/search/activity_search"
    }

    object Discovery {
        const val fragment_discovery = "/discovery/fragment_discovery"
        const val activity_tag_detail = "/discovery/tag_detail"
    }
}