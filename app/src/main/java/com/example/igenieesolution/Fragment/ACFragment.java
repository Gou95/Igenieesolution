package com.example.igenieesolution.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.igenieesolution.R;
import com.example.igenieesolution.ViewModel.ACViewModel;
import com.example.igenieesolution.databinding.FragmentACBinding;


public class ACFragment extends Fragment {

    FragmentACBinding binding;
    private int count = 0;
    private int hour = 0;
    private boolean isPowerOn = false;

    ACViewModel acViewModel;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentACBinding.inflate(inflater, container, false);

        binding.power.setColorFilter(Color.RED);

        updateCountDisplay();
        updateTimeSet();
        viewModel();
        onAttachObservers();

        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                updateCountDisplay();

            }
        });

        binding.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 0) {
                    count--;
                    updateCountDisplay();

                }
            }
        });

        binding.imgTimerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour++;
                updateTimeSet();
            }
        });

        binding.imgTimerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hour > 0) {
                    hour--;
                    updateTimeSet();
                }
            }
        });

        binding.power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPowerOn = !isPowerOn;
                if (isPowerOn){
                    binding.power.setColorFilter(Color.GREEN);
                    binding.linearWindSpeed.setVisibility(View.VISIBLE);
                    binding.linearTimer.setVisibility(View.VISIBLE);
                    acViewModel.getAcModelData("2");
                }else {
                    binding.power.setColorFilter(Color.RED);
                    binding.linearWindSpeed.setVisibility(View.GONE);
                    binding.linearTimer.setVisibility(View.GONE);
                }
            }
        });

//        AcBody body = new AcBody();
//        body.setEmail("test123@example.com");
//        body.setPassword("12345678");



        return binding.getRoot();
    }

    private void onAttachObservers() {
        acViewModel.getLiveData().observe(getViewLifecycleOwner(), response -> {

            if (response.getStatus().equals("TRUE")) {
                Toast.makeText(getContext(), "API call succeeded: ", Toast.LENGTH_SHORT).show();
               // binding.txtTemperature.setText(response.getTemperature().getCurrentTemperature() + "°C");

            } else {
                Toast.makeText(getContext(), "API call failed: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewModel() {
        acViewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ACViewModel.class);
        acViewModel.init(getContext());
    }

    private void updateTimeSet() {
        binding.txtSetCount.setText(String.format("%d hr", hour));
    }

    private void updateCountDisplay() {
        binding.txtCount.setText(String.format("%d°C", count));
    }



}