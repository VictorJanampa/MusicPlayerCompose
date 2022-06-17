package com.example.musicplayercompose

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat

private const val MY_MEDIA_ROOT_ID = "media_root_id"
private const val MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id"
private const val SERVICE_TAG = "media_browser_service"



class MediaPlaybackService : MediaBrowserServiceCompat() {

    private var mediaSession: MediaSessionCompat? = null
    private var mediaPlayer: MediaPlayer? = null
    //private lateinit var callback: MediaSessionCompat.Callback
    private lateinit var stateBuilder: PlaybackStateCompat.Builder
    private val songs = listOf(R.raw.hard_in_da_paint, R.raw.dreaming, R.raw.dreamer)

    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer.create(applicationContext,songs[0])
        mediaSession = MediaSessionCompat(baseContext, SERVICE_TAG).apply {

        // Enable callbacks from MediaButtons and TransportControls
        setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS
                or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        )

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
        stateBuilder = PlaybackStateCompat.Builder()
            .setActions(PlaybackStateCompat.ACTION_PLAY
                    or PlaybackStateCompat.ACTION_PLAY_PAUSE
            )
        setPlaybackState(stateBuilder.build())

        // MySessionCallback() has methods that handle callbacks from a media controller
        setCallback(object: MediaSessionCompat.Callback(){
            override fun onPlay() {
                startService(Intent(applicationContext, MediaBrowserService::class.java))
                isActive = true
                mediaPlayer?.start()
                //startForeground(id, myPlayerNotification)
            }

            override fun onStop() {
                stopSelf()
                isActive = false
                mediaPlayer?.stop()
                stopForeground(false)
            }

            override fun onPause() {
                mediaPlayer?.pause()
                stopForeground(false)
            }
        })

        // Set the session's token so that client activities can communicate with it.
        setSessionToken(sessionToken)
        }
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        return BrowserRoot(MY_EMPTY_MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {

    }
}