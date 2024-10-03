package com.example.igenieesolution.Fragment;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.rtsp.RtspMediaSource;
import androidx.media3.common.MediaItem;

import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.igenieesolution.R;
import com.example.igenieesolution.ViewModel.LockViewModel;
import com.example.igenieesolution.databinding.FragmentVDPLockBinding;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;


public class VDP_Lock_Fragment extends Fragment {


    FragmentVDPLockBinding binding;
    private static final String TAG = "VDP_Lock_Fragment";

    private boolean isLocked = true;

    private static final int REWIND_INTERVAL_MS = 10000; // 10 seconds
    private static final int FORWARD_INTERVAL_MS = 10000; // 10 seconds
    private static final int TOAST_DURATION_MS = 2000;

    private GestureDetector gestureDetector;

    private Handler handler = new Handler();
    private Runnable runnable;
    LibVLC libVLC;
    LockViewModel lockViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentVDPLockBinding.inflate(inflater, container, false);

        lockViewModel = new ViewModelProvider(this).get(LockViewModel.class);
        lockViewModel.init(getContext());

        binding.cardStart.setCardBackgroundColor(Color.GREEN);
        binding.txtLock.setText("Lock");




        binding.cardStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                libVLC = new LibVLC(getContext());
                String url = getString(R.string.rtspUrl);
                Media media = new Media(libVLC, Uri.parse(url));
                media.addOption("--aout=opensles");
                media.addOption("--audio-time-stretch");
                media.addOption("-vvv"); // verbosity
                org.videolan.libvlc.MediaPlayer mediaPlayer = new org.videolan.libvlc.MediaPlayer(libVLC);
                mediaPlayer.setMedia(media);
                mediaPlayer.getVLCVout().setVideoSurface(binding.videoView.getHolder().getSurface(), binding.videoView.getHolder());
                mediaPlayer.getVLCVout().setWindowSize(binding.videoView.getWidth(), binding.videoView.getHeight());
                mediaPlayer.getVLCVout().attachViews();
                mediaPlayer.play();


//                Uri videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
//                binding.video.setVideoURI(videoUri);
            }
        });

        binding.cardStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.videoView != null) {
                    binding.videoView.getHolder().getSurface().release(); // Release the surface
                }
                binding.videoView.setVisibility(View.INVISIBLE); // Hide the VideoView
                binding.videoView.setVisibility(View.VISIBLE);
//                binding.videoView.stopPlayback();
            }
        });
        onAttachObserver();

        lockViewModel.getLockRes();



        binding.imgLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lockViewModel.getLockRes();
                Toast.makeText(getContext(), "open door", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void onAttachObserver() {

        lockViewModel.getLiveData().observe(getViewLifecycleOwner(),response -> {
            if ("admin".equals(response.getAccountType())){

            }
        });
    }



    private void toggleLock() {
        if (isLocked) {

            binding.imgLock.setImageResource(R.drawable.baseline_lock_open);
            binding.txtLock.setText("Lock");
        } else {
            // Change to closed lock
            binding.imgLock.setImageResource(R.drawable.baseline_lock);
            binding.txtLock.setText("Lock");
        }
        // Toggle the lock state
        isLocked = !isLocked;
    }


}