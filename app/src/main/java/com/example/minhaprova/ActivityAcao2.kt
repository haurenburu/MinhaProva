package com.example.minhaprova

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao2Binding

class ActivityAcao2 : AppCompatActivity() {
    lateinit var binding: ActivityAcao2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)
        binding.buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        binding.buttonOk.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val author = binding.editTextAuthor.text.toString()
            val ano = binding.editTextAno.text.toString()

            if(name != "" && author != "" && ano != "" ) {
                val l = Livro(0,
                    name,
                    author,
                    ano.toInt(),
                    5
                )

                val db = LivroDB(this)
                db.insert(l)

                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "VAZIO MF", Toast.LENGTH_SHORT).show()
            }
        }
    }
}