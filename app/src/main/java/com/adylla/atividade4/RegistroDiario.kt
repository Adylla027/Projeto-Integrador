package com.adylla.atividade4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistroDiario(
    var id: String,
    var title: String,
    var description: String
): Parcelable