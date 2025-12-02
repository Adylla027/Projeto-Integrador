package com.adylla.atividade4

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


fun Context.showEmailPopup(
    title: String = "Deseja compartilhar?",
    hint: String = "Digite o email de seu psicólogo",
    onConfirm: (String) -> Unit

){

    val input = EditText(this)
    input.hint = hint

    AlertDialog.Builder(this)
        .setTitle(title)
        .setView(input)
        .setPositiveButton("Confirmar"){ _, _ ->
            val email = input.text.toString().trim()
            onConfirm(email)

        }
        .setNegativeButton("Cancelar", null)
        .show()

}

