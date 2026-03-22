package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.analizadores.FormLexer
import com.example.pkm_forms_proyecto1.analizadores.FormParser
import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.enums.TipoError
import java.io.StringReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.LinkedList

class Formulario {
    lateinit var tabla: TablaSimbolos
    val listaErrores: LinkedList<MensajeError> = LinkedList()
    var datos = StringBuilder()
    var textoForm = ""
    lateinit var contador:Contador
    var autor = ""
    var nombre = ""

    fun analizar(texto: String):Boolean {
        textoForm = texto
        tabla = TablaSimbolos()
        contador = Contador()
        listaErrores.clear()
        datos.clear()
        val lexer = FormLexer(StringReader(texto))
        lexer.setErrores(listaErrores)
        val parser = FormParser(lexer, this)
        try {
            val result = parser.parse()
            val lista = result?.value
            if (lista is LinkedList<*>) {
                val acciones = lista.filterIsInstance<Accion>()
                recorrerArbol(LinkedList(acciones))
                if (contador.preguntas == 0) {
                    val mensajeError = MensajeError(TipoError.SEMANTICO)
                    mensajeError.descripcion =
                        "el formulario debe tener al menos una pregunta para crearse"
                    listaErrores.add(mensajeError)
                }
            }
            return listaErrores.isEmpty()
        } catch (e: Exception) {
            val mensajeError = MensajeError(TipoError.SEMANTICO)
            mensajeError.descripcion = e.message ?: e.toString()
            e.printStackTrace()
            listaErrores.add(mensajeError)
            return false
        }
    }

    fun recorrerArbol(lista: LinkedList<Accion>){
        for (accion in lista){
            accion.realizarAccion(InfoCalculo(this))
        }
    }

    fun agregarDatos(autor: String, nombre: String, descripcion: String){
        this.autor = autor
        this.nombre = nombre
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
        datos = documento
    }

}