package com.project.dentshealth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.dentshealth.R
import com.project.dentshealth.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginSini.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.login_sini) {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}