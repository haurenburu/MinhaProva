package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao3Binding

class ActivityAcao3 : AppCompatActivity() {
    lateinit var binding: ActivityAcao3Binding
    var cont = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)

        var db = LivroDB(this)

        var livro = db.findById(cont)
        var livros = db.findAll()

        binding.textView3Name.text = "Titulo ${livro.nome}"
        binding.textView3Author.text = "Autor ${livro.autor}"
        binding.textView3Year.text = "Ano ${livro.ano}"
        binding.textView3Year.text = "Nota ${livro.nota}"


        binding.button3Prev.setOnClickListener {
            if(cont > 1) binding.button3Next.isEnabled = true
            var l = db.findById(--cont)

            binding.textView3Name.text = "Titulo ${l.nome}"
            binding.textView3Author.text = "Autor ${l.autor}"
            binding.textView3Year.text = "Ano ${l.ano}"
            binding.textView3Year.text = "Nota ${l.nota}"

            if(cont == 1) binding.button3Prev.isEnabled = false
        }
        binding.button3Next.setOnClickListener {
            var l = db.findById(++cont)

            binding.textView3Name.text = "Titulo ${l.nome}"
            binding.textView3Author.text = "Autor ${l.autor}"
            binding.textView3Year.text = "Ano ${l.ano}"
            binding.textView3Year.text = "Nota ${l.nota}"

            if(cont == livros.size) binding.button3Next.isEnabled = false
            if(cont > 1) binding.button3Prev.isEnabled = true
        }


    }
}