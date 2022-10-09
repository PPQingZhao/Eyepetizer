package com.pp.module_home

import android.app.Application
import androidx.databinding.ObservableField
import com.pp.library_network.eyepetizer.api.EyeRetrofit
import com.pp.mvvm.LifecycleViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel(app: Application) : LifecycleViewModel(app) {
    val content = ObservableField<String>("推荐")
    fun getData() {
        EyeRetrofit.eyepetizerApi
            .recommend()
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
