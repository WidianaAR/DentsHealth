package com.project.dentshealth.ui.symptoms

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.dentshealth.databinding.FragmentBadSymptomsBinding

class BadSymptomsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentBadSymptomsBinding
    private val PREFS_NAME = "Symptoms"
    private val KEY_DATA = "Data"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBadSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tlUserSymptoms.setEndIconOnClickListener(this)
        val activity = activity
        sharedPreferences = activity!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onClick(v: View?) {
        val data = binding.tiUserSymptoms.text.toString()

        val editor = sharedPreferences.edit()
        editor.putString(KEY_DATA, data).apply()

        val action = BadSymptomsFragmentDirections.actionBadSymptomsFragmentToAdditionSymptomsFragment()
        findNavController().navigate(action)
    }
}