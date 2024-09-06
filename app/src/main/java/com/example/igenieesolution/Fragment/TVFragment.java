package com.example.igenieesolution.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        initClicks();

        return binding.getRoot();
    }

    private void initClicks() {
        binding.linearOne.setOnClickListener(view -> {
            generateOutput(1);

        });
        binding.linearTwo.setOnClickListener(view -> {
            generateOutput(2);
        });

        binding.linearThree.setOnClickListener(view -> {
            generateOutput(3);
        });

        binding.linearFour.setOnClickListener(view -> {
            generateOutput(4);
        });

        binding.linearFive.setOnClickListener(view -> {
            generateOutput(5);
        });

        binding.linearSix.setOnClickListener(view -> {
            generateOutput(6);
        });

        binding.linearSeven.setOnClickListener(view -> {
            generateOutput(7);
        });

        binding.linearEight.setOnClickListener(view -> {
            generateOutput(8);
        });

        binding.linearNine.setOnClickListener(view -> {
            generateOutput(9);
        });
        binding.linearZero.setOnClickListener(view -> {
            generateOutput(0);
        });


    }

    private void generateOutput(int number) {
        // Example output: Show a Toast message with the number
        Toast.makeText(getContext(),  ""+number, Toast.LENGTH_SHORT).show();
    }
}