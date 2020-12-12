package com.alejoestrada.misdeudores.data.server

import java.io.Serializable

class Receta (val nombre: String = "", val foto: String? = "", val ingredientes: String = "", val preparacion: String = ""):
    Serializable