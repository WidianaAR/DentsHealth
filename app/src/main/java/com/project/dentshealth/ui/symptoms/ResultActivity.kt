package com.project.dentshealth.ui.symptoms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.project.dentshealth.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultPageLoading.isGone = false

        val data = intent.getStringExtra("SYMPTOMS").toString()

        val url = "https://dents-python-vobgfqejjq-et.a.run.app/predict"

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                binding.resultPageLoading.isGone = true
                binding.tvResult.text = response },
            Response.ErrorListener { error ->
                binding.resultPageLoading.isGone = true
                showText(error.message.toString()) }) {
            override fun getParams(): HashMap<String, String> {
                val params: HashMap<String, String> = HashMap()
                params["text"] = data
                return params
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }

    private fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}