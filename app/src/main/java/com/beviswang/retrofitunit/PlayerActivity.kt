package com.beviswang.retrofitunit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.beviswang.ijkmedialib.widget.IJKVideoPlayer
import kotlinx.android.synthetic.main.activity_player.*
import org.jetbrains.anko.toast

class PlayerActivity : AppCompatActivity() {
    private var title: String = ""
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        setVideoInfo(intent)
        bindData()
    }

    private fun setVideoInfo(i: Intent) {
        url = i.getStringExtra(VIDEO_URL)
        title = i.getStringExtra(VIDEO_TITLE)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent == null) return
        setVideoInfo(intent)
    }

    private fun bindData() {
        if (url.isEmpty() || title.isEmpty()) {
            toast("播放出错！")
            finish()
            return
        }
        player.setDataSource(url)
        player.setVideoTitle(title)
        player.setControllerCallback(object : IJKVideoPlayer.ControllerCallback {
            override fun onBackClick(view: View) {
                finish()
            }

            override fun onMenuClick(view: View) {
                player.showMediaInfo()
            }

            override fun onFullClick(view: View) {}

            override fun onPlayClick(view: View, isPlaying: Boolean) {}
        })
    }

    override fun onResume() {
        super.onResume()
        player.resume()
    }

    override fun onStop() {
        super.onStop()
        player.stopPlayback()
        player.release(true)
        player.stopBackgroundPlay()
    }

    companion object {
        const val VIDEO_URL = "com.beviswang.retrofitunit.videoUrl"
        const val VIDEO_TITLE = "com.beviswang.retrofitunit.videoTitle"
    }
}
