package com.pp.library_router_service.services

import android.app.Application
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * app 生命周期
 */
interface IAppService : IProvider {

    fun onCreate(application: Application)
}