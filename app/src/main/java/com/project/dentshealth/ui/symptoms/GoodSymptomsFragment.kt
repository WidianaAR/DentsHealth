package com.project.dentshealth.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.dentshealth.databinding.FragmentGoodSymptomsBinding

class GoodSymptomsFragment : Fragment() {
    private lateinit var binding: FragmentGoodSymptomsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGoodSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }
}