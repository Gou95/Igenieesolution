package com.example.igenieesolution.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.igenieesolution.Fragment.AC_Bedroom_Fragment;
import com.example.igenieesolution.Fragment.AC_Lobby_Fragment;
import com.example.igenieesolution.Fragment.Air_Condition;
import com.example.igenieesolution.Fragment.Air_Purify_Fragment;
import com.example.igenieesolution.Fragment.BlindBedRoomFragment;
import com.example.igenieesolution.Fragment.Curtain_Lobby_Fragment;
import com.example.igenieesolution.Fragment.Curtains_Fragment;
import com.example.igenieesolution.Fragment.Dish_Washer_Fragment;
import com.example.igenieesolution.Fragment.Light_Bedroom_Fragment;
import com.example.igenieesolution.Fragment.Light_Fragment;
import com.example.igenieesolution.Fragment.Light_Lobby_Fragment;
import com.example.igenieesolution.Fragment.RefrigeratorFragment;
import com.example.igenieesolution.Fragment.TVFragment;
import com.example.igenieesolution.Fragment.VDP_Lock_Fragment;
import com.example.igenieesolution.Fragment.Wash_Tower_Fragment;
import com.example.igenieesolution.Fragment.Washing_Machine_Fragment;
import com.example.igenieesolution.R;
import com.example.igenieesolution.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setOnClickables();




    }

    private void setOnClickables() {

        binding.linearLowey.setOnClickListener(view -> {
            setVisibleList(binding.llLobbyList);
            handleToolClick(view,binding.linearKitchen,binding.linearBedroom);

        });
        binding.linearKitchen.setOnClickListener(view -> {
            setVisibleList(binding.llKitchenList);
            handleToolClick(view,binding.linearLowey,binding.linearBedroom);
        });
        binding.linearBedroom.setOnClickListener(view -> {
            setVisibleList(binding.llBedroomList);
            handleToolClick(view,binding.linearKitchen,binding.linearLowey);
        });


        binding.linearAir.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearWashingMachine, binding.linearLight, binding.linearVdpLock,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearDishWasher,binding.linearWashTower, new Air_Condition());
        });

        binding.linearRefrigerator.setOnClickListener(view -> {
            handleClick(view, binding.linearAir, binding.linearWashingMachine, binding.linearLight, binding.linearVdpLock,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearDishWasher,binding.linearWashTower, new RefrigeratorFragment());
        });

        binding.linearWashingMachine.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearLight, binding.linearVdpLock,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearDishWasher,binding.linearWashTower, new Washing_Machine_Fragment());
        });
        binding.linearLight.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearWashingMachine, binding.linearVdpLock,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearDishWasher,binding.linearWashTower, new Light_Fragment());

        });

        binding.linearVdpLock.setOnClickListener(view -> {

            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearWashingMachine, binding.linearLight,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearDishWasher,binding.linearWashTower, new VDP_Lock_Fragment());
        });

        binding.linearAirPurifier.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearWashingMachine, binding.linearLight,
                    binding.linearVdpLock,binding.linearCurtains,binding.linearDishWasher,binding.linearWashTower, new Air_Purify_Fragment());

        });
        binding.linearCurtains.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearWashingMachine, binding.linearLight,
                    binding.linearAirPurifier,binding.linearVdpLock,binding.linearDishWasher,binding.linearWashTower, new Curtains_Fragment());

        });
        binding.linearDishWasher.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearWashingMachine, binding.linearLight,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearWashTower,binding.linearVdpLock, new Dish_Washer_Fragment());
        });

        binding.linearWashTower.setOnClickListener(view -> {
            handleClick(view, binding.linearRefrigerator, binding.linearAir, binding.linearWashingMachine, binding.linearLight,
                    binding.linearAirPurifier,binding.linearCurtains,binding.linearDishWasher,binding.linearVdpLock, new Wash_Tower_Fragment());
        });

        binding.linearBedroomBlind.setOnClickListener(view -> {
            handleBedroomClick(view,binding.linearBedroomAC,binding.linearBedroomLight,new BlindBedRoomFragment());

        });
        binding.linearBedroomAC.setOnClickListener(view -> {
            handleBedroomClick(view,binding.linearBedroomBlind,binding.linearBedroomLight,new AC_Bedroom_Fragment());

        });
        binding.linearBedroomLight.setOnClickListener(view -> {
            handleBedroomClick(view,binding.linearBedroomAC,binding.linearBedroomBlind,new Light_Bedroom_Fragment());

        });
        binding.linearLobbyAC.setOnClickListener(view -> {
            handleBedroomClick(view,binding.linearLobbyCurtain,binding.linearLobbyLight,new AC_Lobby_Fragment());

        });
        binding.linearLobbyCurtain.setOnClickListener(view -> {
            handleBedroomClick(view,binding.linearLobbyAC,binding.linearLobbyLight,new Curtain_Lobby_Fragment());

        });
        binding.linearLobbyLight.setOnClickListener(view -> {
            handleBedroomClick(view,binding.linearLobbyAC,binding.linearLobbyCurtain,new Light_Lobby_Fragment());

        });

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



    private void handleClick(View clickedView, View otherView1, View otherView2,View otherView3,View otherView4,View otherView5,
                             View otherView6,View otherView7,View otherView8,Fragment fragment) {
       // resetAllBackgroundColors();

        clickedView.setBackgroundResource(R.color.green);
        otherView1.setBackgroundResource(R.color.blue);
        otherView2.setBackgroundResource(R.color.blue);
        otherView3.setBackgroundResource(R.color.blue);
        otherView4.setBackgroundResource(R.color.blue);
        otherView5.setBackgroundResource(R.color.blue);
        otherView6.setBackgroundResource(R.color.blue);
        otherView7.setBackgroundResource(R.color.blue);
        otherView8.setBackgroundResource(R.color.blue);

        replaceFragment(fragment);
    }

    private void handleToolClick(View clickedView,View view1,View view2){
        clickedView.setBackgroundResource(R.color.green);
        view1.setBackgroundResource(R.color.blue);
        view2.setBackgroundResource(R.color.blue);

    }
    private void handleBedroomClick(View view,View view1,View view2,Fragment fragment){
        replaceFragment(fragment);
        view.setBackgroundResource(R.color.green);
        view1.setBackgroundResource(R.color.blue);
        view2.setBackgroundResource(R.color.blue);
    }

}