package com.example.coiltest

import android.annotation.SuppressLint
import android.database.CrossProcessCursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById<ImageView>(R.id.imageView)
        textView = findViewById<TextView>(R.id.textView)

        loadCoil()

    }

    private fun loadCoil() {
        imageView.load("https://via.placeholder.com/600/92c952") {
            crossfade(true)
                .listener(
                    onStart = {
                              textView.visibility = View.VISIBLE
                    },
                    onSuccess = {_,_ ->
                        imageView.visibility = View.VISIBLE
                        textView.visibility = View.INVISIBLE
                    },
                    onError = { _, _ ->
                        imageView.visibility = View.INVISIBLE
                        textView.visibility = View.INVISIBLE
                        Log.d("TAG", "loadCoil: ")
                    })
                .transformations(CircleCropTransformation())
                .build()
        }
    }
}