package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.anroiddevelopment.databinding.FragmentRotateAnimationBinding


class RotateAnimation : Fragment() {


    lateinit var binding : FragmentRotateAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentRotateAnimationBinding.inflate(inflater, container, false)
    return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RotateButton1.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.leftrotate)
            binding.rotateimage1.startAnimation(animation)
        }

        binding.RotateButton2.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.rightrotate)
            binding.rotateimage2.startAnimation(animation)
        }
    }
}