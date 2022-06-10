package com.project.dentshealth.ui.symptoms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.project.dentshealth.databinding.FragmentBadSymptomsBinding
import org.json.JSONException


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
        val data = binding.tiUserSymptoms.text.toString()
        val intent = Intent(activity, ResultActivity::class.java)
        intent.putExtra("SYMPTOMS", data)
        startActivity(intent)
    }

    private fun showText(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }
}