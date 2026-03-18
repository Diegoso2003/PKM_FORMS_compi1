package com.example.pkm_forms_proyecto1.excepciones

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError

class ErrorSemantico(val mensajeError: MensajeError): Exception() {
}