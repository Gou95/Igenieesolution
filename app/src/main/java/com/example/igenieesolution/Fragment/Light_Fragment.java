package com.example.igenieesolution.Fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.igenieesolution.Response.GetLightResponse;
import com.example.igenieesolution.Response.LightResponse;
import com.example.igenieesolution.ViewModel.GetLightViewModel;
import com.example.igenieesolution.ViewModel.LightViewModel;
import com.example.igenieesolution.databinding.FragmentLightBinding;


public class Light_Fragment extends Fragment {


    FragmentLightBinding binding;

    private boolean isPowerOn = false;

    LightViewModel viewModel;


    private String light1 = "100"; // Store dimming value from SeekBar
    private String light2 = "100";
    private String dimmingValue1 = "100"; // Store dimming value from SeekBar
    private String colorValue1 = "6500";
    private String dimmingValue2 = "100"; // Store dimming value from SeekBar
    private String colorValue2 = "6500";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(LightViewModel.class);
        viewModel.init(getContext());
        viewModel.getLightModelData("1");

        binding =  FragmentLightBinding.inflate(inflater, container, false);


       onAttachObservers();




       viewModel.getLightDataDimming("1","100","6500","0");


        binding.seekbarOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.txtHundred.setText(i+"/100");
             //   viewModel.getLightDataDimming("4",dimmingValue1,"6000","1");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               //  int finalProgress = seekBar.getProgress()  ;
                dimmingValue1 = Integer.toString(seekBar.getProgress());
                viewModel.getLightDataDimming("4",dimmingValue1,"6000","1");
//                binding.txtHundred.setText(Integer.toString(finalProgress) + "/100");

              //  Toast.makeText(getContext(), Integer.toString(finalProgress), Toast.LENGTH_SHORT).show();

                //showLoader(true);

            }
        });

        binding.seekbarTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

               binding.txtColor.setText(i + "/6500");
              //  viewModel.getLightDataDimming("4","100",colorValue1,"1");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              //  int finalProgress = seekBar.getProgress()   ;
                colorValue1 = Integer.toString(seekBar.getProgress());
                viewModel.getLightDataDimming("4","100",colorValue1,"1");
             //   binding.txtColor.setText(Integer.toString(finalProgress) + "/6500");
              //  Toast.makeText(getContext(), Integer.toString(finalProgress), Toast.LENGTH_SHORT).show();


              //  showLoader(true);

            }
        });
        binding.seekbarThree.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                binding.txtHundred2.setText(i +"/100");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // int finalProgress = seekBar.getProgress()  ;
               // binding.txtHundred2.setText(Integer.toString(finalProgress) + "/100");
                dimmingValue2 = Integer.toString(seekBar.getProgress());
              //  Toast.makeText(getContext(), Integer.toString(finalProgress), Toast.LENGTH_SHORT).show();
             viewModel.getLightDataDimming("1",dimmingValue2,"6000","1");

            // showLoader(true);
            }
        });
        binding.seekbarFour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.txtColor1.setText(i + "/6500");
               // viewModel.getLightDataDimming("1","100",colorValue2,"1");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //int finalProgress = seekBar.getProgress()   ;
                colorValue2 = Integer.toString(seekBar.getProgress());
                viewModel.getLightDataDimming("1","100",colorValue2,"1");

              //  binding.txtColor1.setText(Integer.toString(finalProgress) + "/6500");

              //  Toast.makeText(getContext(), Integer.toString(finalProgress), Toast.LENGTH_SHORT).show();


            }
        });

        binding.power.setColorFilter(Color.RED);
        binding.power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPowerOn = !isPowerOn;
                updatePowerUI();

            }
        });

        return binding.getRoot();
    }


    private void updatePowerUI() {
        if (isPowerOn) {
            binding.power.setColorFilter(Color.GREEN);

            viewModel.getLightModelData("1");
            binding.switchLight1.setChecked(true);
            binding.switchLight2.setChecked(true);
            binding.switchLight3.setChecked(true);
            binding.switchLight4.setChecked(true);
        } else {
            binding.power.setColorFilter(Color.RED);
            viewModel.getLightModelData("0");
            binding.switchLight1.setChecked(false);
            binding.switchLight2.setChecked(false);
            binding.switchLight3.setChecked(false);
            binding.switchLight4.setChecked(false);
        }
    }


    private void onAttachObservers() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), lightResponse -> {
            if (lightResponse != null && "OK".equals(lightResponse.getStatus().getFlagMessage())) {
                LightResponse.Response response = lightResponse.getStatus().getResponse();
                if (response != null) {


                    String channel1 = response.getChannel1();
                    String channel2 = response.getChannel2();
                    String channel3 = response.getChannel3();
                    String channel4 = response.getChannel4();

                    // Update SeekBar progress
                    dimmingValue1 = channel4;
                    dimmingValue2 = channel1;
                    colorValue1 = response.getChannel4CT();
                    colorValue2 = response.getChannel1CT();

                    binding.seekbarOne.setProgress(Integer.parseInt(dimmingValue1));
                    binding.seekbarTwo.setProgress(Integer.parseInt(colorValue1));
                    binding.seekbarThree.setProgress(Integer.parseInt(dimmingValue2));
                    binding.seekbarFour.setProgress(Integer.parseInt(colorValue2));

                    // Determine switch states
                    boolean isSwitch1Checked = Integer.parseInt(channel1) > 0;
                    boolean isSwitch2Checked = Integer.parseInt(channel2) > 0;
                    boolean isSwitch3Checked = Integer.parseInt(channel3) > 0;
                    boolean isSwitch4Checked = Integer.parseInt(channel4) > 0;

                    // Update switches
                    binding.switchLight1.setChecked(isSwitch3Checked);
                    binding.switchLight2.setChecked(isSwitch2Checked);
                    binding.switchLight3.setChecked(isSwitch4Checked);
                    binding.switchLight4.setChecked(isSwitch1Checked);



                    binding.switchLight1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                       if (isChecked){
                           viewModel.getLightDataDimming("2","100","6500","1");

                       }else {
                           viewModel.getLightDataDimming("2","0","6500","1");

                       }
                    });

                    binding.switchLight2.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked){
                            viewModel.getLightDataDimming("3","100","6500","1");

                        }else {
                            viewModel.getLightDataDimming("3","0","6500","1");

                        }

                    });

                    binding.switchLight3.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked){
                            viewModel.getLightDataDimming("4", dimmingValue1, colorValue1,"1");

                        }else {
                            viewModel.getLightDataDimming("4",dimmingValue1,colorValue1,"1");

                        }

                    });

                    binding.switchLight4.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked){
                            viewModel.getLightDataDimming("1",dimmingValue2,colorValue2,"1");

                        }else {
                            viewModel.getLightDataDimming("1",dimmingValue2,colorValue2,"1");

                        }

                    });

                }
                else {
                  //  Toast.makeText(getContext(), "Response is null", Toast.LENGTH_SHORT).show();
                }
            } else {
              //  Toast.makeText(getContext(), "Invalid Light response", Toast.LENGTH_SHORT).show();
            }

        });
    }




}