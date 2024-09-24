package com.example.igenieesolution.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.igenieesolution.Database.DataBaseHelper;
import com.example.igenieesolution.R;
import com.example.igenieesolution.Response.WashingResponse;
import com.example.igenieesolution.ViewModel.WashingViewModel;
import com.example.igenieesolution.databinding.FragmentWashingMachineBinding;

import java.util.List;


public class Washing_Machine_Fragment extends Fragment {


    FragmentWashingMachineBinding binding;

    DataBaseHelper db;
    String deviceId;
    private boolean isPowerOn = false;

    WashingViewModel washingViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentWashingMachineBinding.inflate(inflater, container, false);

        db = new DataBaseHelper(getContext());
        deviceId = db.getDeviceID("DEVICE_WASHER");

      //  binding.linearOnOff.setBackgroundColor(Color.RED);

        onInitMethod();
        onAttachObservers();
      //  setUpOnClicks();

        binding.cardArrowUp.setOnClickListener(view -> {
            animateButton(binding.cardArrowUp);
        });

        binding.cardArrowDown.setOnClickListener(view -> {
            animateButton(binding.cardArrowDown);
        });

        if (deviceId !=null && !deviceId.isEmpty()){
            fetchData();
        } else {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    private void fetchData(){
        washingViewModel.getWashingData(deviceId);
    }
    private void onInitMethod() {
        washingViewModel = new ViewModelProvider(this).get(WashingViewModel.class);
        washingViewModel.init(getContext());
    }

    private void onAttachObservers() {
        washingViewModel.getLiveData().observe(getViewLifecycleOwner(), response -> {
            if (response != null && response.getStatus() != null && "OK".equals(response.getStatus().getFlagMessage())) {
            //    Toast.makeText(getContext(), response.getStatus().getDeviceType(), Toast.LENGTH_SHORT).show();
                List<WashingResponse.Response> responseList = response.getResponse();
                if (responseList != null && !responseList.isEmpty()) {
                    WashingResponse.Response washingData = responseList.get(0);
                    if (washingData != null && washingData.getTimer() != null) {
                        int remainHour = washingData.getTimer().getRemainHour();
                        int remainMinute = washingData.getTimer().getRemainMinute();
                        int relativeHour = washingData.getTimer().getRelativeHourToStart();
                        int relativeMinute = washingData.getTimer().getRelativeMinuteToStart();
                        int totalHour = washingData.getTimer().getTotalHour();
                        int totalMinute = washingData.getTimer().getTotalMinute();


                        binding.txtHour.setText(String.valueOf(remainHour));
                        binding.txtMinute.setText(String.valueOf(remainMinute));
                        binding.txtHourStart.setText(String.valueOf(relativeHour));
                        binding.txtMinuteStart.setText(String.valueOf(relativeMinute));
                        binding.txtTotalHour.setText(String.valueOf(totalHour));
                        binding.txtTotalMinute.setText(String.valueOf(totalMinute));


                        String runState = washingData.getRunState().getCurrentState();
                        updateUIBasedOnState(runState);



                    } else {

                        Toast.makeText(getContext(), "Timer data is missing", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(getContext(), "No washing data available", Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(getContext(), "Error: " + (response != null ? response.getStatus().getFlagMessage() : "Unknown error"), Toast.LENGTH_SHORT).show();
            }
        });

        washingViewModel.getIsFailed().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
               // Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUIBasedOnState(String runState) {

        boolean runStateModeRun = "RUNNING".equalsIgnoreCase(runState);
        boolean runStateModeRising = "RINSING".equalsIgnoreCase(runState);
        boolean runStateModeSpinning = "SPINNING".equalsIgnoreCase(runState);

        if (runStateModeRun) {
            binding.linearCurrentState.setBackgroundColor(Color.GREEN);
            binding.txtPowerOnOff.setText("RUNNING");
        } else if (runStateModeRising) {
            binding.linearCurrentState.setBackgroundColor(Color.BLUE);
            binding.txtPowerOnOff.setText("RINSING");
        } else if (runStateModeSpinning) {
            binding.linearCurrentState.setBackgroundColor(Color.GRAY);
            binding.txtPowerOnOff.setText("SPINNING");
        } else {
            binding.linearCurrentState.setBackgroundColor(Color.RED);
            binding.txtPowerOnOff.setText("POWER_OFF");
        }
    }


//    private void setUpOnClicks() {
//        binding.linearOnOff.setOnClickListener(view -> {
//            binding.linearOnOff.setBackgroundColor(Color.GREEN);
//            binding.txtPowerOff.setText("Power ON");
//            isPowerOn = true;
//            if (deviceId != null && !deviceId.isEmpty()) {
//                washingViewModel.getWashingData(deviceId);
//            } else {
//                Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void animateButton(View view) {
        if (!isPowerOn) {
            // Start animation for the clicked button
            animateScale(view);

            isPowerOn = true;

            // Reset the animation state after the duration of the animation
            view.postDelayed(() -> {
                isPowerOn = false;
            }, 1600); // 800ms for scale up + 800ms for scale down
        }
    }


    private void animateScale(View view) {
        // Scale up animation
        ObjectAnimator scaleXUp = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f);
        ObjectAnimator scaleYUp = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f);

        scaleXUp.setDuration(800);
        scaleYUp.setDuration(800);

        scaleXUp.start();
        scaleYUp.start();

        // Scale back to original size after 800ms
        scaleXUp.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator scaleXBack = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1f);
                ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f);

                scaleXBack.setDuration(800);
                scaleYBack.setDuration(800);

                scaleXBack.start();
                scaleYBack.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }


}