package com.example.igenieesolution.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igenieesolution.R;
import com.example.igenieesolution.databinding.FragmentDishWasherBinding;


public class Dish_Washer_Fragment extends Fragment {

    FragmentDishWasherBinding binding;

    boolean isChecked = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentDishWasherBinding.inflate(inflater, container, false);

        binding.cardArrowUp.setOnClickListener(view -> {
            animateButton(binding.cardArrowUp);
        });

        binding.cardArrowDown.setOnClickListener(view -> {
            animateButton(binding.cardArrowDown);
        });



        return binding.getRoot();
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