package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.anroiddevelopment.databinding.FragmentXMLViewsMainBinding


class XMLViewsMainFragment : Fragment() {

    private lateinit var binding: FragmentXMLViewsMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentXMLViewsMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ScrollView.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_scrollView2)
        }

        binding.SelectionView.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_selectionView)
        }

         binding.Progressbar.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_progressBar)
        }

       binding.dateandtimeview.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_dateAndTimeViews)
        }

        binding.GridLayout.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_gridLayout)
        }
        binding.FrameLayout.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_framelayout)
        }

       binding.Materialcomponents.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_materialComponents)
        }

       binding.DrawableXML.setOnClickListener {
        findNavController().navigate(R.id.action_XMLViewsMainFragment_to_drawablexml)
        }

        binding.Styless.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_styles_)
        }
        binding.Guidelines.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_guidelinesConstraintLayout)
        }
        binding.Barrier.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_barrierConstraintLayout)
        }
        binding.Chains.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_chainsConstraintLayout)
        }
        binding.Bias.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_biasConstraintLayout)
        }
        binding.Ratio.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_ratioConstraintLayout)
        }
        binding.AplhaAnimationn.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_aplhaAnimation)
        }
        binding.ScaleAnimationn.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_scaleAnimation)
        }
        binding.RotateAnimationn.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_rotateAnimation)
        }
        binding.TranslateAnimationn.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_translateAnimation)
        }
        binding.Colors.setOnClickListener {
         findNavController().navigate(R.id.action_XMLViewsMainFragment_to_colors)
        }
        binding.Fonts.setOnClickListener {
            findNavController().navigate(R.id.action_XMLViewsMainFragment_to_fonts)
        }

    }
}