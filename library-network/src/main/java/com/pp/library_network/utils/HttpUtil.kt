package com.pp.library_network.utils

import android.util.Log
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.API_KEY
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.APP_ID
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.AUTH
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.CID
import com.pp.library_network.eyepetizer.EyepetizerService2.Companion.UA
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

        val builder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS);

        return builder.build();
    }

    fun getClient(vararg headers: Pair<String, String>): OkHttpClient {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Log.e(TAG, "===> $message")
        }

        val logInterceptor = HttpLoggingInterceptor(logger)
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val builder = original.newBuilder()
                    .method(original.method(), original.body())

                headers.onEach {
                    builder.header(it.first, it.second)
                }

                return chain.proceed(builder.build())
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