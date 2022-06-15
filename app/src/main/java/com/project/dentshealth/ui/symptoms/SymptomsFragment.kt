package com.project.dentshealth.ui.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.dentshealth.R
import com.project.dentshealth.databinding.FragmentSymptomsBinding

class SymptomsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSymptomsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.goodButton.setOnClickListener(this)
        binding.badButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.good_button) {
            val action = SymptomsFragmentDirections.actionSymptomsFragmentToGoodSymptomsFragment()
            findNavController().navigate(action)
        } else {
            val action = SymptomsFragmentDirections.actionSymptomsFragmentToBadSymptomsFragment()
            findNavController().navigate(action)
        }
    }
}