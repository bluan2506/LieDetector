package com.example.liedetector.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.liedetector.R
import com.example.liedetector.databinding.FragmentTwoFingerBinding

class TwoFingerFragment : Fragment() {
    private lateinit var binding: FragmentTwoFingerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoFingerBinding.inflate(inflater, container, false)
        return binding.root
    }
}