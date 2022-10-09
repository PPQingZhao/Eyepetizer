package com.pp.library_network.utils

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpUtil {
    fun getClient(): OkHttpClient{
        val builder =  OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS);

        return builder.build();
    }
}