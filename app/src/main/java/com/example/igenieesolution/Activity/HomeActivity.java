package com.example.igenieesolution.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.igenieesolution.Fragment.ACFragment;
import com.example.igenieesolution.Fragment.Air_Condition;
import com.example.igenieesolution.Fragment.Air_Purify_Fragment;
import com.example.igenieesolution.Fragment.Curtains_Fragment;
import com.example.igenieesolution.Fragment.Dish_Washer_Fragment;
import com.example.igenieesolution.Fragment.Light_Fragment;
import com.example.igenieesolution.Fragment.RefrigeratorFragment;
import com.example.igenieesolution.Fragment.TVFragment;
import com.example.igenieesolution.Fragment.VDP_Lock_Fragment;
import com.example.igenieesolution.Fragment.Washing_Machine_Fragment;
import com.example.igenieesolution.R;
import com.example.igenieesolution.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());




        binding.linearLowey.setOnClickListener(view -> setVisibleList(binding.llLobbyList));
        binding.linearKitchen.setOnClickListener(view -> setVisibleList(binding.llKitchenList));
        binding.linearBedroom.setOnClickListener(view -> setVisibleList(binding.llBedroomList));

        binding.linearAir.setOnClickListener(view -> replaceFragment(new Air_Condition()));
        binding.linearRefrigerator.setOnClickListener(view -> replaceFragment(new RefrigeratorFragment()));
        binding.linearLight.setOnClickListener(view -> replaceFragment(new Light_Fragment()));
        binding.linearTv.setOnClickListener(view -> replaceFragment(new TVFragment()));
        binding.linearVdpLock.setOnClickListener(view -> replaceFragment(new VDP_Lock_Fragment()));
        binding.linearWashingMachine.setOnClickListener(view -> replaceFragment(new Washing_Machine_Fragment()));
        binding.linearAirPurifier.setOnClickListener(view -> replaceFragment(new Air_Purify_Fragment()));
        binding.linearCurtains.setOnClickListener(view -> replaceFragment(new Curtains_Fragment()));
        binding.linearDishWasher.setOnClickListener(view -> replaceFragment(new Dish_Washer_Fragment()));

    }

    private void setVisibleList(View visibleList) {
        binding.llLobbyList.setVisibility(visibleList == binding.llLobbyList ? View.VISIBLE : View.GONE);
        binding.llKitchenList.setVisibility(visibleList == binding.llKitchenList ? View.VISIBLE : View.GONE);
        binding.llBedroomList.setVisibility(visibleList == binding.llBedroomList ? View.VISIBLE : View.GONE);
    }

    private void replaceFragment(Fragment fragment) {
        if (!isFinishing()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
    private void changeColor(View view) {
        view.setBackgroundColor(Color.GRAY); // Change the clicked layout color to grey
    }

    private void resetColor(View view) {
        view.setBackgroundColor(Color.TRANSPARENT); // Reset to default color
    }

}