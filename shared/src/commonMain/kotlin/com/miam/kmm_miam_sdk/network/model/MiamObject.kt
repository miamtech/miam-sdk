package com.miam.kmm_miam_sdk.network.model

interface MiamObject {
    companion object{
        lateinit var type :String
        lateinit var relationship : Array<String>
    }
}