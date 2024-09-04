package com.example.igenieesolution.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igenieesolution.R;
import com.example.igenieesolution.databinding.FragmentWashTowerBinding;


public class Wash_Tower_Fragment extends Fragment {

    FragmentWashTowerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWashTowerBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}