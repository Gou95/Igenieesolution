package com.example.igenieesolution.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.igenieesolution.R;
import com.example.igenieesolution.Response.AirPurifierResponse;
import com.example.igenieesolution.Session.AppSession;
import com.example.igenieesolution.Session.Constants;
import com.example.igenieesolution.ViewModel.AirPurifierViewModel;
import com.example.igenieesolution.databinding.FragmentAirPurifyBinding;


public class Air_Purify_Fragment extends Fragment {


    boolean isChecked = false;
    FragmentAirPurifyBinding binding;
    AirPurifierViewModel airPurifierViewModel;
     String deviceId = "dd29d92cfe396bf498296647e0279f9ded76414a3dd9b3445ce18b0e29c58ee2";

     boolean isPowerOn = false;

     String str_jobMode;
     String str_windMode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentAirPurifyBinding.inflate(inflater, container, false);

        airPurifierViewModel = new ViewModelProvider(this).get(AirPurifierViewModel.class);
        airPurifierViewModel.init(getContext());


        initCliks();
        onAttachObservers();

        if (deviceId !=null && !deviceId.isEmpty()){
            fetchAirData();
            str_jobMode = AppSession.getInstance(getContext()).getString(Constants.AIRMODE);
            str_windMode = AppSession.getInstance(getContext()).getString(Constants.AIRWIND);
        }else {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
        }



        return binding.getRoot();
    }


    private void initCliks() {

        binding.cardArrowUp.setOnClickListener(view -> animateButton(binding.cardArrowUp));
        binding.cardArrowDown.setOnClickListener(view -> animateButton(binding.cardArrowDown));
        binding.cardArrowUp1.setOnClickListener(view -> animateButton(binding.cardArrowUp1));
        binding.cardArrowDown1.setOnClickListener(view -> animateButton(binding.cardArrowDown1));

        binding.linearClean.setOnClickListener(view -> handleJobMode("CLEAN"));
        binding.linearSleep.setOnClickListener(view -> handleJobMode("SLEEP"));
        binding.linearPower2.setOnClickListener(view -> togglePowerState());
        binding.linearLow.setOnClickListener(view -> handleWindMode("LOW"));
        binding.linearMedium.setOnClickListener(view -> handleWindMode("MID"));
        binding.linearHigh.setOnClickListener(view -> handleWindMode("HIGH"));
        binding.linearPower.setOnClickListener(view -> handleWindMode("POWER"));
        binding.linearAuto.setOnClickListener(view -> handleWindMode("AUTO"));

    }
    private void fetchAirData() {
        showLoader(true);
        airPurifierViewModel.getAirPurifierData(deviceId);
        airPurifierViewModel.setAirPurifierData(deviceId,str_jobMode);
        airPurifierViewModel.setAirPurifierData(deviceId,str_windMode);
    }


    private void onAttachObservers() {

        airPurifierViewModel.getLiveData().observe(getViewLifecycleOwner(),response -> {
            if (response != null && response.getResponse() !=null){
              //  Toast.makeText(getContext(), response.getStatus().getFlagMessage(), Toast.LENGTH_SHORT).show();
                handleAirPurifierResponse(response);
            }else {
                Toast.makeText(getContext(), "not response from API", Toast.LENGTH_SHORT).show();
            }
            showLoader(false);
        });


    }

    private void handleAirPurifierResponse(AirPurifierResponse response) {
        AirPurifierResponse.Response res = response.getResponse();
        if (res !=null && res.getAirQualitySensor() !=null){
            updateAirQualityData(
                    res.getAirQualitySensor().getPm1(),
                    res.getAirQualitySensor().getPm2(),
                    res.getAirQualitySensor().getPm10(),
                    res.getAirQualitySensor().getTotalPollution(),
                    res.getAirQualitySensor().getOder(),
                    res.getAirQualitySensor().getOder(),
                    res.getAirQualitySensor().getOdorLevel(),
                    res.getAirQualitySensor().getTotalPollutionLevel(),
                    res.getAirQualitySensor().getMonitoringEnabled()


            );

        }else {
            setDefaultAirQualityData();
        }
        isPowerOn = "POWER_ON".equals(res.getOperation().getAirPurifierOperationMode());
        binding.linearPower2.setBackgroundColor(isPowerOn ? Color.GREEN : Color.RED);
        binding.txtOnOff.setText(isPowerOn ? "ON" : "OFF");

        String windStrength = res.getAirFlow().getWindStrength();
        updateUILayoutairPurifierWindMode(windStrength);
        AppSession.getInstance(getContext()).putString(Constants.AIRWIND,res.getAirFlow().getWindStrength());
        AppSession.getInstance(getContext()).putString(Constants.AIRMODE,res.getAirPurifierJobMode().getCurrentJobMode());
        updateUILayoutairPurifierJobMode(AppSession.getInstance(getContext()).getString(Constants.AIRMODE));
        updateUILayoutairPurifierWindMode(AppSession.getInstance(getContext()).getString(Constants.AIRWIND));





    }

    private void updateUILayoutairPurifierWindMode(String wind) {
        if ("LOW".equals(wind)){
            binding.linearLow1.setVisibility(View.VISIBLE);
            binding.linearMedium1.setVisibility(View.GONE);
            binding.linearHigh1.setVisibility(View.GONE);
            binding.linearPower1.setVisibility(View.GONE);
            binding.linearAuto1.setVisibility(View.GONE);
        }

        else if ("MID".equals(wind)) {
            binding.linearLow1.setVisibility(View.GONE);
            binding.linearMedium1.setVisibility(View.VISIBLE);
            binding.linearHigh1.setVisibility(View.GONE);
            binding.linearPower1.setVisibility(View.GONE);
            binding.linearAuto1.setVisibility(View.GONE);
        }
        else if ("HIGH".equals(wind)) {
            binding.linearLow1.setVisibility(View.GONE);
            binding.linearMedium1.setVisibility(View.GONE);
            binding.linearHigh1.setVisibility(View.VISIBLE);
            binding.linearPower1.setVisibility(View.GONE);
            binding.linearAuto1.setVisibility(View.GONE);
        }
        else if ("POWER".equals(wind)) {
            binding.linearLow1.setVisibility(View.GONE);
            binding.linearMedium1.setVisibility(View.GONE);
            binding.linearHigh1.setVisibility(View.GONE);
            binding.linearPower1.setVisibility(View.VISIBLE);
            binding.linearAuto1.setVisibility(View.GONE);

        }
        else if ("AUTO".equals(wind)) {
            binding.linearLow1.setVisibility(View.GONE);
            binding.linearMedium1.setVisibility(View.GONE);
            binding.linearHigh1.setVisibility(View.GONE);
            binding.linearPower1.setVisibility(View.GONE);
            binding.linearAuto1.setVisibility(View.VISIBLE);

        }
    }

    private void updateUILayoutairPurifierJobMode(String jobMode) {
        if ("CLEAN".equals(jobMode)){

            binding.linearClean1.setVisibility(View.VISIBLE);
            binding.linearSleep1.setVisibility(View.GONE);
        }
        else if ("SLEEP".equals(jobMode)){
            binding.linearSleep1.setVisibility(View.VISIBLE);
            binding.linearClean1.setVisibility(View.GONE);
        }

    }

    private void handleJobMode(String newMode){
        if (deviceId != null && !deviceId.isEmpty()) {
            str_jobMode = newMode;
            fetchAirData();
        }
    }

private void handleWindMode(String windMode){
        if (deviceId != null && !deviceId.isEmpty()){
            str_windMode = windMode;
            fetchAirData();
        }
}

    private void togglePowerState() {
        if (deviceId != null && !deviceId.isEmpty()) {
            String mode = isPowerOn ? "POWER_OFF" : "POWER_ON";
            showLoader(true);
            airPurifierViewModel.setAirPurifierData(deviceId, mode);
            isPowerOn = !isPowerOn;
            binding.linearPower2.setBackgroundColor(isPowerOn ? Color.GREEN : Color.RED);
            binding.txtOnOff.setText(isPowerOn ? "ON" : "OFF");
        } else {
            Toast.makeText(getContext(), "Device ID not found", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateAirQualityData(int pm1, int pm2, int pm10, int totalPollution,int oder,int oder1,String oderLevel,String totalPollutionLevel,String monitoringEnabled) {
        binding.txtPm1.setText(String.valueOf(pm1));
        binding.txtPm2.setText(String.valueOf(pm2));
        binding.txtPm10.setText(String.valueOf(pm10));
        binding.txtTotalPopulation.setText(String.valueOf(totalPollution));
        binding.txtOder1.setText(String.valueOf(oder));
        binding.txtOder2.setText(String.valueOf(oder1));
        binding.txtOderLevel.setText(oderLevel);
        binding.txtTotalPopulationLevel.setText(totalPollutionLevel);
        binding.txtMonitoringEnabled.setText(monitoringEnabled);
    }

    private void setDefaultAirQualityData() {
        binding.txtPm1.setText("N/A");
        binding.txtPm2.setText("N/A");
        binding.txtPm10.setText("N/A");
        binding.txtTotalPopulation.setText("N/A");
        binding.txtOder1.setText("N/A");
        binding.txtOder2.setText("N/A");
        binding.txtOderLevel.setText("N/A");
        binding.txtTotalPopulationLevel.setText("N/A");
        binding.txtMonitoringEnabled.setText("N/A");

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

    private void showLoader(boolean show) {
        binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}