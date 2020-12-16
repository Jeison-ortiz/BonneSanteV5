package com.alejoestrada.misdeudores.data.server

import java.io.Serializable

class Usuario(
    val id: String? = "",
    val name: String? = "",
    val correo: String = "",
    val telefono: String = ""
): Serializable