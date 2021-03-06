package com.example.minhaprova

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class CafeDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("Pergunta importante")
                .setMessage("Gostaria de uma xícara de café?")
                .setPositiveButton("Sim"
                ) { dialog, id ->
                    Toast.makeText(it.baseContext, "Ótimo", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Não"
                ) { dialog, id ->
                    Toast.makeText(it.baseContext, "Fica para a próxima", Toast.LENGTH_SHORT).show()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}