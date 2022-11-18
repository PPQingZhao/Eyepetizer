package com.pp.library_network.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.pp.library_network.eyepetizer.bean.Metro
import java.lang.reflect.Type

class DataSourceTypeAdapter :
    JsonDeserializer<Metro.TrackingParams.DataSource> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Metro.TrackingParams.DataSource? {
        try {

//            Log.e("TAG", typeOfT?.typeName?:"111")
            return context?.deserialize(json, typeOfT)
        } catch (e: JsonParseException) {
            e.printStackTrace()
        }
//        Log.e("TAG", "222222222222222222")
        return null
    }


}