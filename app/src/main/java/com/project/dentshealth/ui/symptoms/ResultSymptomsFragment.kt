package com.project.dentshealth.ui.symptoms

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.project.dentshealth.R
import com.project.dentshealth.databinding.FragmentResultSymptomsBinding

class ResultSymptomsFragment : Fragment() {
    private lateinit var binding: FragmentResultSymptomsBinding
    private val PREFS_NAME = "Symptoms"
    private val KEY_DATA = "Data"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResultSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPreferences = activity!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val data = sharedPreferences.getString(KEY_DATA, null).toString()

        val url = "https://dents-python-vobgfqejjq-et.a.run.app/predict"

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                binding.resultPageLoading.isGone = true
                binding.result.text = response },
            Response.ErrorListener { error ->
                binding.resultPageLoading.isGone = true
                showText(getString(R.string.network_warning)) }) {
            override fun getParams(): HashMap<String, String> {
                val params: HashMap<String, String> = HashMap()
                params["text"] = data
                return params
            }
        }
        val queue = Volley.newRequestQueue(activity!!.applicationContext)
        queue.add(stringRequest)
    }

    private fun showText(text: String) {
        Toast.makeText(activity!!.applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}