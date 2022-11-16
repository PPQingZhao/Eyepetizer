package com.pp.library_network.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.pp.library_network.eyepetizer.bean.PageDataBean
import com.pp.library_network.eyepetizer.bean.Style
import com.pp.library_network.eyepetizer.bean.TrackingData
import java.lang.reflect.Type

class TrackingDataAdapter :
    JsonDeserializer<TrackingData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TrackingData? {
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