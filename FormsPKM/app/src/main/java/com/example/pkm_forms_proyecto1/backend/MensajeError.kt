package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.enums.TipoEnum

class MensajeError(val tipoEnum: TipoEnum) {
    var columna: Int = 0
    var linea: Int = 0
    var descripcion: String = ""
    var lexema: String = ""
}