package com.project.dentshealth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.dentshealth.MainActivity
import com.project.dentshealth.R
import com.project.dentshealth.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonLogin.setOnClickListener(this)
        binding.daftarSini.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.button_login) {
            intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}