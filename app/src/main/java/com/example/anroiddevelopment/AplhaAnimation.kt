package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anroiddevelopment.databinding.FragmentAplhaAnimationBinding

import android.view.animation.AnimationUtils


class AplhaAnimation : Fragment() {
     private lateinit var binding: FragmentAplhaAnimationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentAplhaAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fadeIn.setOnClickListener {
             val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in)
            binding.image11.startAnimation(animation)
        }
        binding.fadeOut.setOnClickListener {
              val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.fade_out)
            binding.image11.startAnimation(animation)
        }
    }
}