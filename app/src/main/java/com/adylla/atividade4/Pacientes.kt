package com.adylla.atividade4

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pacientes(

    val pacienteId: String = "",
    val nome: String = "",
    val psicologoId: String = ""

): Parcelable
