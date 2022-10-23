package com.pp.library_network.utils

import android.util.Log
import com.pp.library_network.eyepetizer.ApiService.Companion.API_KEY
import com.pp.library_network.eyepetizer.ApiService.Companion.APP_ID
import com.pp.library_network.eyepetizer.ApiService.Companion.AUTH
import com.pp.library_network.eyepetizer.ApiService.Companion.CID
import com.pp.library_network.eyepetizer.ApiService.Companion.UA
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

        val interceptor = object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original =chain.request()
                val newRequest = original.newBuilder()
                    .header("x-api-key", API_KEY)
                    .header("X-THEFAIR-APPID", APP_ID)
                    .header("X-THEFAIR-CID", CID)
                    .header("X-THEFAIR-AUTH", AUTH)
                    .header("X-THEFAIR-UA", UA)
                    .header("User-Agent", UA)
                    .method(original.method(), original.body())
                    .build()
                return chain.proceed(newRequest)
            }

        }

        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(logInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS);

        return builder.build();
    }
}