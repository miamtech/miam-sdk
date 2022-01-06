package com.example.androidtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miam.kmm_miam_sdk.android.di.KoinInitilizer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KoinInitilizer.init(this)
        setContentView(R.layout.activity_main)
    }
}