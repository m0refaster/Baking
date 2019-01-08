package com.shawn.nichol.bakingapp.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.shawn.nichol.bakingapp.Data.InstructionsExtractSteps;
import com.shawn.nichol.bakingapp.R;

import java.util.Objects;


public class StepsFragment extends Fragment {

    private static final String LOGTAG = "StepsFragment";

    private PlayerView mExoPlayerView;

    private SimpleExoPlayer mExoPlayer;

    private boolean mPlayWhenReady = true;

    private String mURI;

    // Requires an empty constructor
    public StepsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

//        if(savedInstanceState != null) {
//            startPosition = savedInstanceState.getLong(KEY_POSITION);
//        } else {
//            clearStartPosition();
//        }

        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        mExoPlayerView = rootView.findViewById(R.id.exo_player_view);
        TextView stepsTv = rootView.findViewById(R.id.fragment_steps_tv);

        // Access ViewModel, for the steps index position
        SharedViewModel model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SharedViewModel.class);

        int position = model.getStepPosition();
        mURI = InstructionsExtractSteps.stepsVideoList.get(position);
        String description = InstructionsExtractSteps.stepsDescriptionList.get(position);

        Log.d(LOGTAG, "Index " + position);
        Log.d(LOGTAG, description);
        Log.d(LOGTAG, "URI " + mURI);

        stepsTv.setText(description);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            if (!TextUtils.isEmpty(mURI)) {
                initializePlayer(Uri.parse(mURI));
//                if(mExoPlayerView != null) {
//                    mExoPlayer.onResume();
//                }
            } else {
                mExoPlayerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Util.SDK_INT < 23) {
            if (!TextUtils.isEmpty(mURI)) {
                initializePlayer(Uri.parse(mURI));
//                if(mExoPlayerView != null) {
//                    mExoPlayer.onResume();
//                }
            } else {
                mExoPlayerView.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT <= 23 || mExoPlayer == null) {
//            if(mExoPlayerView != null) {
//                mExoPlayer.onPause();
//            }
            releasePlayer();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(Util.SDK_INT > 23) {
//            if(mExoPlayerView != null) {
//                mExoPlayer.onPause();
//            }
            releasePlayer();
        }
    }


    private void initializePlayer(Uri mediaUri) {
        Log.d(LOGTAG, "initializePlayer mediaUri: " + mediaUri);
        if(mExoPlayer == null) {
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            @SuppressWarnings("deprecation") TrackSelection.Factory videoTrackSelection = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelection);

            // Create player
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

            // Bind player to view
            mExoPlayerView.setPlayer(mExoPlayer);

            // Measure BandWidth during playback.
            // Produces DataSource instance through which media data is loaded
            DataSource.Factory dataSource = new DefaultDataSourceFactory(Objects.requireNonNull(getContext()), Util.getUserAgent(getContext(), "Baking"));
            // MediaSource being played
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSource).createMediaSource(mediaUri);
            // Prepare player
            mExoPlayer.prepare(videoSource);

            mExoPlayer.setPlayWhenReady(mPlayWhenReady);
            mExoPlayerView.setVisibility(View.VISIBLE);
        }
    }

    private void releasePlayer() {
        if(mExoPlayer!= null) {
            mPlayWhenReady = mExoPlayer.getPlayWhenReady();

            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
}
