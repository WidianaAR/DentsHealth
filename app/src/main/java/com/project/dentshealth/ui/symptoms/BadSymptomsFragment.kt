package com.project.dentshealth.ui.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.dentshealth.MainActivity
import com.project.dentshealth.R
import com.project.dentshealth.databinding.FragmentBadSymptomsBinding

class BadSymptomsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentBadSymptomsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBadSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tlUserSymptoms.setEndIconOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val data = binding.tiUserSymptoms.text
        showText(data.toString())
    }

    private fun showText(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }
}