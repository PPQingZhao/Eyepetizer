package com.pp.library_router_service.services

object RouterServiceImpl {
    /**
     * 数据库
     */
    object DataBase {
        private const val base = "/database_service"
        const val DATABASE_APP = "$base/app"
    }

    object User{
        private const val base = "/user_service"
        const val SERVICE_USER = "$base/user"
        const val SERVICE_APP = "$base/app"
    }
}