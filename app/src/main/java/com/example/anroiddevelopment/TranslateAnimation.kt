package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.anroiddevelopment.databinding.FragmentTranslateAnimationBinding


class TranslateAnimation : Fragment() {
   lateinit var binding : FragmentTranslateAnimationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentTranslateAnimationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.TranslateButton1.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.left_to_right_translate)
            binding.translateimage1.startAnimation(animation)
        }
        binding.TranslateButton2.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(requireContext(),R.anim.right_to_left_translate)
            binding.translateimage2.startAnimation(animation)
        }
    }

}