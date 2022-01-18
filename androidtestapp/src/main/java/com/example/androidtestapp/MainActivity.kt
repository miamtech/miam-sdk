package com.example.androidtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miam.kmm_miam_sdk.android.di.KoinInitilizer
import com.miam.kmm_miam_sdk.handler.PointOfSaleHandler
import com.miam.kmm_miam_sdk.handler.UserHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        KoinInitilizer.init(this)
        println( " ----------------------------- >  Miam Koin init" )
        PointOfSaleHandler.updateStoreId("35290")
        PointOfSaleHandler.setSupplier(7)
        println( "  ----------------------------- > Point Of Sale init")
        UserHandler.updateUserId("ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        println( "  ----------------------------- > user init")
        setContentView(R.layout.activity_main)
    }
}