package com.pp.library_network.utils

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

class ArraySecurityAdapter: JsonDeserializer<List<*>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<*> {
        if (json.isJsonArray) {
            val newGson = Gson()
            return newGson.fromJson(json, typeOfT)
        } else {
            return Collections.EMPTY_LIST
        }
    }
}