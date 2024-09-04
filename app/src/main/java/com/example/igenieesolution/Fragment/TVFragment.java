package com.example.igenieesolution.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igenieesolution.R;
import com.example.igenieesolution.databinding.FragmentTVBinding;


public class TVFragment extends Fragment {

FragmentTVBinding binding;
    private boolean isPowerOn = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTVBinding .inflate(inflater, container, false);

        binding.power.setColorFilter(Color.RED);

        binding.power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPowerOn = !isPowerOn;
                if (isPowerOn){
                    binding.power.setColorFilter(Color.GREEN);
                    binding.linearTvShow.setVisibility(View.VISIBLE);
                }else {
                    binding.power.setColorFilter(Color.RED);
                    binding.linearTvShow.setVisibility(View.GONE);
                }
            }
        });


        return binding.getRoot();
    }
}