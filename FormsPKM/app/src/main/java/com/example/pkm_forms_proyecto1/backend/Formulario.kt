package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.analizadores.FormLexer
import com.example.pkm_forms_proyecto1.analizadores.FormParser
import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import java.io.StringReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.LinkedList

class Formulario {
    val tabla: TablaSimbolos = TablaSimbolos()
    val listaErrores: LinkedList<MensajeError> = LinkedList()
    val datos = StringBuilder()
    val contador = Contador()

    fun analizar(texto: String):Boolean {
        val lexer = FormLexer(StringReader(texto))
        lexer.setErrores(listaErrores)
        val parser = FormParser(lexer, this)
        try {
            recorrerArbol(parser.parse().value as LinkedList<Accion>)
            return listaErrores.isEmpty()
        } catch (e: Exception) {
            return false;
        }
    }

    fun recorrerArbol(lista: LinkedList<Accion>){
        for (accion in lista){
            accion.realizarAccion(InfoCalculo(this))
        }
    }

    fun agregarDatos(autor: String, nombre: String, descripcion: String){
        val documento = StringBuilder()
        val ahora = LocalDateTime.now()
        documento.append("###\n")
        documento.append("\tAuthor: $autor\n")
        documento.append("\tFecha: ${ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\n")
        documento.append("\tHora: ${ahora.format(DateTimeFormatter.ofPattern("HH:mm"))}\n")
        documento.append("\tDescription: $descripcion\n")
        documento.append("\tTotal de secciones: ${contador.secciones}\n")
        documento.append("\tTotal de preguntas: ${contador.preguntas}\n")
        documento.append("\t\tAbiertas: ${contador.preguntasAbiertas}\n")
        documento.append("\t\tDesplegables: ${contador.preguntasDesplegables}\n")
        documento.append("\t\tSelección: ${contador.preguntasSeleccion}\n")
        documento.append("\t\tMúltiples: ${contador.preguntasMultiples}\n")
        documento.append("###\n")
        documento.append(datos)
    }

}