package com.example.catapp.data


import java.util.*


class Cat {
    var breeds: List<Any>? = null
    var id: String? = null
    var url: String? = null
    var width: Int? = null
    var height: Int? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}