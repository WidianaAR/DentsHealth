package com.psi.smarttoothcare.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.psi.smarttoothcare.R
import com.psi.smarttoothcare.databinding.ActivityRegisterBinding

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