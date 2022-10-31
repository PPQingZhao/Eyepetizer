package com.pp.library_network.utils

import com.google.gson.Gson
import com.pp.library_network.eyepetizer.bean.PageDataBean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    fun create(baseUrl: String): Retrofit {

        val builder = Retrofit.Builder()
            .client(HttpUtil.getClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.build()
    }

    fun createEyeRetrofit(baseUrl: String, vararg headers: Pair<String, String>): Retrofit {

        val gson = Gson().newBuilder()
            .registerTypeHierarchyAdapter(
                PageDataBean.Card.CardData.Body.Metro.TrackingParams::class.java,
                TrackingParamsAdapter()
            )
            .create()
        val gsonConverter = GsonConverterFactory.create(gson)

        val builder = Retrofit.Builder()
            .client(HttpUtil.getClient(*headers))
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverter)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.build()
    }
}