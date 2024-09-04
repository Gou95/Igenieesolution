package com.example.igenieesolution.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.igenieesolution.Database.DataBaseHelper;
import com.example.igenieesolution.R;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.SetACResponse;
import com.example.igenieesolution.Session.AppSession;
import com.example.igenieesolution.Session.Constants;
import com.example.igenieesolution.ViewModel.ACSetDataViewModel;
import com.example.igenieesolution.ViewModel.ACViewModel;
import com.example.igenieesolution.databinding.FragmentAirConditionBinding;


public class Air_Condition extends Fragment {


    FragmentAirConditionBinding binding;
    ACViewModel acViewModel;
    DataBaseHelper dataBaseHelper;
    private boolean isPowerOn = false;
   String currentTargetTemperature = "";
    String deviceIdAC;
    ACSetDataViewModel acSetDataViewModel;
    String jobMode;
    String airFlow;
    private boolean isChecked = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAirConditionBinding.inflate(inflater, container, false);


        acViewModel = new ViewModelProvider(requireActivity()).get(ACViewModel.class);
        acSetDataViewModel = new ViewModelProvider(requireActivity()).get(ACSetDataViewModel.class);

        acSetDataViewModel.init(getContext());
        acViewModel.init(getContext());

        dataBaseHelper = new DataBaseHelper(getContext());
        deviceIdAC = dataBaseHelper.getDeviceID("DEVICE_AIR_CONDITIONER");



        if (deviceIdAC != null && !deviceIdAC.isEmpty()) {
            jobMode = AppSession.getInstance(getContext()).getString(Constants.MODE);
            airFlow = AppSession.getInstance(getContext()).getString(Constants.AIRFLOW);
            fetchAcData();

        } else {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
        }

        setupObservers();
        initClicks();


        return binding.getRoot();


    }
    private void fetchAcData() {
        showLoader(true);
        acViewModel.getAcModelData(deviceIdAC);
        acSetDataViewModel.getAcModelData(deviceIdAC, jobMode);
        acSetDataViewModel.getUpdateTemp(deviceIdAC,currentTargetTemperature);
    }

    private void initClicks() {
        binding.linearCool.setOnClickListener(view -> {
            handleJobMode("COOL");


        });
        binding.linearDry.setOnClickListener(view -> {
            handleJobMode("AIR_DRY");


        });
        binding.linearFan.setOnClickListener(view -> {
            handleJobMode("FAN");


        });
        binding.linearLow.setOnClickListener(view -> {
            handleJobMode("LOW");
            handleAirMode("LOW");

        });
        binding.linearMedium.setOnClickListener(view -> {
            handleJobMode("MID");
            handleAirMode("MID");

        });
        binding.linearHigh.setOnClickListener(view -> {
            handleJobMode("HIGH");
            handleAirMode("HIGH");


        });
       binding.cardOnoff.setOnClickListener(view -> togglePowerState());
        binding.cardArrowUp.setOnClickListener(view -> {
            adjustTemperature("increase");
            animateButton(binding.cardArrowUp);

        });
        binding.cardArrowDown.setOnClickListener(view -> {
            adjustTemperature("decrease");
            animateButton(binding.cardArrowDown);
        });
    }


    private void setupObservers() {
        acViewModel.getLiveData().observe(getViewLifecycleOwner(), response -> {
            if (response != null && response.getResponse() != null) {
                updateUI(response);
            } else {
                Log.e("AirCondition", "Invalid AC response");
                Toast.makeText(getContext(), "Invalid AC response", Toast.LENGTH_SHORT).show();
            }
            showLoader(false);
        });

        acSetDataViewModel.getLiveData().observe(getViewLifecycleOwner(), acResponse -> {
            if (acResponse != null && acResponse.getResponse() != null) {
                handleACResponse(acResponse);
            } else {
                Log.e("AirCondition", "Invalid SetAC response");
                Toast.makeText(getContext(), "Invalid SetAC response", Toast.LENGTH_SHORT).show();
            }
            showLoader(false);
        });
    }


    private void updateUI(AcResponse response) {
        AcResponse.Response responseData = response.getResponse();
        if (responseData != null && responseData.getTemperature() != null) {
            currentTargetTemperature = String.valueOf(responseData.getTemperature().getTargetTemperature());
            binding.txtTimer.setText(String.format("%s°C", currentTargetTemperature));
            binding.txtCurrentTemp.setText(String.format("%s°C", responseData.getTemperature().getCurrentTemperature()));
            AppSession.getInstance(getContext()).putString(Constants.MODE, responseData.getAirConJobMode().getCurrentJobMode());
            AppSession.getInstance(getContext()).putString(Constants.AIRFLOW, responseData.getAirFlow().getWindStrength());
            isPowerOn = "POWER_ON".equals(responseData.getOperation().getAirConOperationMode());
            binding.cardOnoff.setCardBackgroundColor(isPowerOn ? Color.GREEN : Color.RED);
            binding.txtOnoff.setText(isPowerOn ? "ON" : "OFF");
            if (responseData.getAirQualitySensor() != null) {
                updateAirQualityData(
                        responseData.getAirQualitySensor().getPm1(),
                        responseData.getAirQualitySensor().getPm2(),
                        responseData.getAirQualitySensor().getPm10(),
                        responseData.getAirQualitySensor().getTotalPollution()
                );
            } else {
                setDefaultAirQualityData();
            }
            updateUILayoutBasedOnJobMode(AppSession.getInstance(getContext()).getString(Constants.MODE));
            updateUILayoutBasedOnAirMode(AppSession.getInstance(getContext()).getString(Constants.AIRFLOW));
        }
    }


    private void handleACResponse(SetACResponse acResponse) {
        if (acResponse != null && acResponse.getResponse() != null) {
            currentTargetTemperature = String.valueOf(acResponse.getResponse().getTemperature().getTargetTemperature());
            binding.txtTimer.setText(String.format("%s°C", currentTargetTemperature));
        }
        String airModePerform = acResponse.getResponse().getAirConJobMode().getCurrentJobMode();

        updateUILayoutBasedOnJobMode(airModePerform);

        String power = acResponse.getResponse().getOperation().getAirConOperationMode();
        isPowerOn = "POWER_ON".equals(power);
        binding.cardOnoff.setCardBackgroundColor(isPowerOn ? Color.GREEN : Color.RED);
        binding.txtOnoff.setText(isPowerOn ? "ON" : "OFF");

        String windStrength = acResponse.getResponse().getAirFlow().getWindStrength();
        updateUILayoutBasedOnAirMode(windStrength);
        Log.d("AirCondition", "Wind Strength: " + windStrength);
    }



    private void handleJobMode(String newMode) {
        if (deviceIdAC == null || deviceIdAC.isEmpty()) {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
            return;
        }
        jobMode = newMode;
        fetchAcData();
    }

    private void handleAirMode(String newAirMode) {
        if (deviceIdAC == null || deviceIdAC.isEmpty()) {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
            return;
        }
        airFlow = newAirMode;
        fetchAcData();
    }



    private void adjustTemperature(String increase) {
        if (deviceIdAC != null && !deviceIdAC.isEmpty()) {

            currentTargetTemperature = increase;
           // binding.txtTimer.setText(String.format("%s°C", currentTargetTemperature));
            showLoader(true);
            acSetDataViewModel.getUpdateTemp(deviceIdAC, currentTargetTemperature);
            Log.d("temp", "adjustTemperature: "+currentTargetTemperature);
        } else {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateAirQualityData(int pm1, int pm2, int pm10, int totalPollution) {
        binding.txtPm1.setText(String.valueOf(pm1));
        binding.txtPm2.setText(String.valueOf(pm2));
        binding.txtPm10.setText(String.valueOf(pm10));
        binding.txtTotalPopulation.setText(String.valueOf(totalPollution));
    }

    private void setDefaultAirQualityData() {
        binding.txtPm1.setText("N/A");
        binding.txtPm2.setText("N/A");
        binding.txtPm10.setText("N/A");
        binding.txtTotalPopulation.setText("N/A");
    }

    private void showLoader(boolean show) {
        binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void togglePowerState() {
        if (deviceIdAC != null && !deviceIdAC.isEmpty()) {
            String mode = isPowerOn ? "POWER_OFF" : "POWER_ON";
            showLoader(true);
            acSetDataViewModel.getAcModelData(deviceIdAC, mode);
            isPowerOn = !isPowerOn;
            binding.cardOnoff.setCardBackgroundColor(isPowerOn ? Color.GREEN : Color.RED);
            binding.txtOnoff.setText(isPowerOn ? "ON" : "OFF");
        } else {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
        }
    }

//    private void togglePowerState() {
//        if (deviceIdAC == null || deviceIdAC.isEmpty()) {
//            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String mode = isPowerOn ? "POWER_OFF" : "POWER_ON";
//        showLoader(true);
//
//        acSetDataViewModel.getAcModelData(deviceIdAC, mode);
//        isPowerOn = !isPowerOn;
//        binding.cardOnoff.setCardBackgroundColor(isPowerOn ? Color.GREEN : Color.RED);
//        binding.txtOnoff.setText(isPowerOn ? "ON" : "OFF");
//    }


    private void updateUILayoutBasedOnJobMode(String jobMode) {

        if ("COOL".equals(jobMode)) {
            binding.linearCool1.setVisibility(View.VISIBLE);
            binding.linearFan1.setVisibility(View.GONE);
            binding.linearDry1.setVisibility(View.GONE);


        } else if ("AIR_DRY".equals(jobMode)) {
            binding.linearCool1.setVisibility(View.GONE);
            binding.linearFan1.setVisibility(View.GONE);
            binding.linearDry1.setVisibility(View.VISIBLE);


        } else if ("FAN".equals(jobMode)) {
            binding.linearCool1.setVisibility(View.GONE);
            binding.linearFan1.setVisibility(View.VISIBLE);
            binding.linearDry1.setVisibility(View.GONE);
        }
        else if ("LOW".equals(jobMode)) {

        } else if ("MID".equals(jobMode)) {


        } else if ("HIGH".equals(jobMode)) {


        }

    }
    private void updateUILayoutBasedOnAirMode(String windStrength) {

        if ("LOW".equals(windStrength)){
            binding.linearLow1.setVisibility(View.VISIBLE);
            binding.linearMedium1.setVisibility(View.GONE);
            binding.linearHigh1.setVisibility(View.GONE);
        } else if ("MID".equals(windStrength)) {
            binding.linearLow1.setVisibility(View.GONE);
            binding.linearMedium1.setVisibility(View.VISIBLE);
            binding.linearHigh1.setVisibility(View.GONE);
        }else if ("HIGH".equals(windStrength)) {
            binding.linearLow1.setVisibility(View.GONE);
            binding.linearMedium1.setVisibility(View.GONE);
            binding.linearHigh1.setVisibility(View.VISIBLE);
        }
    }

    private void animateButton(View view) {
        if (!isChecked) {
            // Start animation for the clicked button
            animateScale(view);

            isChecked = true;

            // Reset the animation state after the duration of the animation
            view.postDelayed(() -> {
                isChecked = false;
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