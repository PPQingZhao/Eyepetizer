package com.pp.library_network.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.pp.library_network.eyepetizer.bean.PageDataBean
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

class TrackingParamsAdapter: JsonDeserializer<PageDataBean.Card.CardData.Body.Metro.TrackingParams> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PageDataBean.Card.CardData.Body.Metro.TrackingParams {

        val dataSource = PageDataBean.Card.CardData.Body.Metro.TrackingParams()
        if (json.isJsonObject) {
            val jsonObj = JSONObject(json.asJsonObject.toString())
            val dataSourceJson = jsonObj.opt("data_source")
            if (dataSourceJson is JSONArray) {
                val list =  mutableListOf<PageDataBean.Card.CardData.Body.Metro.TrackingParams.DataSource>()
                dataSource.dataSource = list
                for (i in 0 until  dataSourceJson.length()) {
                    val paramsObj = dataSourceJson.getJSONObject(i)
                    val dataSource = PageDataBean.Card.CardData.Body.Metro.TrackingParams.DataSource()

                    val label = paramsObj.optString("label")
                    val paramsObject = paramsObj.optJSONObject("params")

                    val params = PageDataBean.Card.CardData.Body.Metro.TrackingParams.DataSource.Params()

                    params.dataId = paramsObject.optInt("data_id")
                    params.num = paramsObject.optInt("num")

                    dataSource.params = params
                    dataSource.label = label

                    list.add(dataSource)
                }
            } else {
                dataSource.dataSource = listOf()
            }
        }
        return dataSource
    }
}