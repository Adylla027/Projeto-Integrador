package com.adylla.atividade4

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

//Popup que aparece quando o botão de compartilhar é clicado no visualizarDiario
fun Context.showCompartilharPopup(
    onConfirm: () -> Unit

){

    AlertDialog.Builder(this)
        .setTitle("Deseja compartilhar?")
        .setMessage("Compartilhar essa nota?")
        .setPositiveButton("Confirmar"){ dialog, _ ->
            onConfirm()
            dialog.dismiss()
        }
        .setNegativeButton("Cancelar"){ dialog, _ ->
            dialog.dismiss()
        }
        .show()

}

