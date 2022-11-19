package com.pp.library_router_service.services

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * user
 */
interface IUserService : IProvider {

    /**
     * 是否有用户登录
     */
   fun hasUser():Boolean

    /**
     * 获取user uid
     */
   fun getUid():Int?
}