package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.shawn.nichol.bakingapp.Data.InstructionsExtractSteps;
import com.shawn.nichol.bakingapp.R;

public class StepsFragment extends Fragment {
    private static final String LOGTAG = "StepsFragment";

    private SharedViewModel model;
    public int mPosition;
    private String mURI;

    private TextView mDescriptionTextView;


    // EXO player
    // bandwidth meter to measure and estimate bandwidth
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    private SimpleExoPlayer player;
    private PlayerView playerView;
    private String mDescription;
    private View view;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    // Requires an empty constructor
    public StepsFragment() {
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_step, container, false);

        playerView = view.findViewById(R.id.video_view);
        mDescriptionTextView = view.findViewById(R.id.fragment_steps_tv);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Access ViewModel, for the steps index position
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);


        mPosition = model.getStepPosition();
        mURI = InstructionsExtractSteps.stepsVideoList.get(mPosition);
        mDescription = InstructionsExtractSteps.stepsDescriptionList.get(mPosition);

        Log.d(LOGTAG, "Index " + mPosition);
        Log.d(LOGTAG, mDescription);
        Log.d(LOGTAG, "URI " + mURI);

        mDescriptionTextView.setText(mDescription);







    }

    private void initializePlayer() {

//        Log.d(LOGTAG, "URI2 " + mURI);
//        player = ExoPlayerFactory.newSimpleInstance(
//                new DefaultRenderersFactory(getActivity()),
//                new DefaultTrackSelector(), new DefaultLoadControl());
//
//        playerView.setPlayer(player);
//
//        player.setPlayWhenReady(playWhenReady);
//        player.seekTo(currentWindow, playbackPosition);
//
//
//        Uri uri = Uri.parse(mURI);
//        MediaSource mediaSource = buildMediaSource(uri);
//        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
