package com.pp.module_home

import android.app.Application
import androidx.databinding.ObservableField
import com.pp.library_network.eyepetizer.api.EyeRetrofit
import com.pp.mvvm.LifecycleViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverViewModel(app: Application) : LifecycleViewModel(app) {
    val content = ObservableField<String>("发现")
    fun getData() {
        EyeRetrofit.eyepetizerApi
            .discover()
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    content.set(response.body()?.string())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    content.set(t.message.toString())
                }
            });
    }
}
