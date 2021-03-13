package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.minhaprova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.text1.text = viewmodel.name

        val settings = getSharedPreferences("prefs", MODE_PRIVATE)
        if (settings.getString("firstTime", "YES") == "YES") {
            Toast.makeText(this, R.string.welcome, Toast.LENGTH_SHORT).show()
            val editor = settings.edit()
            editor.putString("firstTime", "NO")
            editor.apply()
        }

        binding.button1.setOnClickListener {
            val intent = Intent(this, ActivityAcao1::class.java)
            startActivityForResult(intent, 1)
        }

        binding.button2.setOnClickListener {
            val dialog = CafeDialog()
            dialog.show(supportFragmentManager, "quercafe")
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, ActivityAcao2::class.java)
            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "NAO TA FUNCIONANDO O SNACK", Toast.LENGTH_SHORT).show()
            } else if (resultCode == Activity.RESULT_OK) {
                viewmodel.name = data?.getStringExtra("RESPOSTA").toString()
                binding.text1.text = viewmodel.name
            }
        } else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                binding.text2.text = "Cadastrado"
            }
        }
    }
}