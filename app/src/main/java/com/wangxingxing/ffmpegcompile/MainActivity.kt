package com.wangxingxing.ffmpegcompile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wangxingxing.ffmpegcompile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sampleText.text = "FFmpeg版本：${getFFmpegVersion()}"
    }

    external fun getFFmpegVersion(): String

    companion object {
        init {
            System.loadLibrary("ffmpegcompile")
        }
    }
}