package com.pp.library_network.utils

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpUtil {
    private const val TAG = "OKHttp"

    fun getClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Log.e(TAG, "===> $message")
        }

        val logInterceptor = HttpLoggingInterceptor(logger)
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS);

        return builder.build();
    }
}