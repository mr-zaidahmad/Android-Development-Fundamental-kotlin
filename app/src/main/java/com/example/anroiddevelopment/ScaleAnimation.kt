package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.anroiddevelopment.databinding.FragmentScaleAnimationBinding


class ScaleAnimation : Fragment() {

    private lateinit var binding : FragmentScaleAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentScaleAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scaleUpButton.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.scale_up)
            binding.scaleimage.startAnimation(animation)
        }
        binding.scaleDownButton.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.scale_down)
            binding.scaleimage.startAnimation(animation)
        }
    }
}