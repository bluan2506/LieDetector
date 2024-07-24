package com.example.liedetector.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.liedetector.databinding.FragmentFingerBinding


class FingerFragment : Fragment() {

    private lateinit var binding: FragmentFingerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFingerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = "<font color=#83FFF8>LIE</font> <font color=#FFFFFF>DETECTOR</font>"
        binding.appName.setText(Html.fromHtml(text))
    }
}