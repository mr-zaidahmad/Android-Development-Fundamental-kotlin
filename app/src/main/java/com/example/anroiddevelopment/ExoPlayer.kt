package com.example.anroiddevelopment

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.anroiddevelopment.databinding.ActivityExoPlayerBinding

class ExoPlayer : AppCompatActivity() {

    private lateinit var binding: ActivityExoPlayerBinding
    private lateinit var player :ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExoPlayerBinding.inflate(layoutInflater)
            setContentView(binding.root)

        player=ExoPlayer.Builder(this).build()

        binding.PlayerView.player=player

        val uri="android.resource://$packageName/${R.raw.sample}"

        val mediaItem=MediaItem.fromUri(uri)

        player.setMediaItem(mediaItem)

        player.prepare()
        player.play()


    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}