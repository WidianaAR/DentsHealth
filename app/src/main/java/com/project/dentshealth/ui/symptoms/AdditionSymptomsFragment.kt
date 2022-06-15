package com.project.dentshealth.ui.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.project.dentshealth.databinding.FragmentAdditionSymptomsBinding

class AdditionSymptomsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAdditionSymptomsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdditionSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tlUserSymptoms.setEndIconOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val action = AdditionSymptomsFragmentDirections.actionAdditionSymptomsFragmentToResultSymptomsFragment()
        findNavController().navigate(action)
    }
}