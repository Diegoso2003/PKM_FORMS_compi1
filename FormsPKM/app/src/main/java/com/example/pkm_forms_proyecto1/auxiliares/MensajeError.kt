package com.example.pkm_forms_proyecto1.auxiliares

import com.example.pkm_forms_proyecto1.enums.TipoError

class MensajeError(val tipoError: TipoError) {
    var columna: Int = 0
    var linea: Int = 0
    var descripcion: String = ""
    var lexema: String = ""
}