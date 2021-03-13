package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.google.android.material.snackbar.Snackbar

class ActivityAcao1 : AppCompatActivity() {
    lateinit var binding: ActivityAcao1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)


        binding.buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        binding.buttonOk.setOnClickListener {
            val res = binding.editText.text.toString()
            if(res != "") {
                val i = Intent()
                i.putExtra("RESPOSTA", res)
                setResult(Activity.RESULT_OK, i)
                finish()
            } else {
                Toast.makeText(this, R.string.erro1, Toast.LENGTH_SHORT).show()
            }
        }
    }
}