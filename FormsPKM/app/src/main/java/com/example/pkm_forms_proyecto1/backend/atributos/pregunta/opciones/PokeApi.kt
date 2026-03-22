package com.example.pkm_forms_proyecto1.backend.atributos.pregunta.opciones

import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.backend.poke_api.RetrofitInstance
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.LinkedList

class PokeApi(simbolo: Simbolo, val inicio: NodoExpresion, val fin: NodoExpresion) :
    ParamOpci(simbolo) {
    override fun obtenerOpciones(
        infoCalculo: InfoCalculo, simbolo: Simbolo
    ): LinkedList<String> {
        val lista = LinkedList<String>()
        val valor1 = validarNumber(infoCalculo, inicio)
        val valor2 = validarNumber(infoCalculo, fin)
        val expr1 = validarNumeroEntero(valor1) && validarNumeroPositivo(valor1)
        val expr2 = validarNumeroEntero(valor2) && validarNumeroPositivo(valor2)
        if(!expr1 || !expr2 || valor1 > valor2){
            agregarMensaje(infoCalculo, simbolo, "parametros no validos")
            return lista
        }
        lista.addAll(runBlocking {
            whoIsThatPokemon(valor1.toInt(), valor2.toInt(), infoCalculo)
        })
        return lista
    }

    suspend fun whoIsThatPokemon(ini: Int, fin: Int, infoCalculo: InfoCalculo): List<String> {
        return try {
            val limit = fin - ini + 1
            val offset = ini - 1
            val response = RetrofitInstance.api.getPokemonsByRange(limit, offset)
            response.results.map { it.name }
        } catch (e: IOException) {
            agregarMensaje(infoCalculo, simbolo, "Error de red, verifique si conexion a internet")
            emptyList()
        } catch (e: Exception) {
            agregarMensaje(infoCalculo, simbolo, "Error en metodo who_is_that_pokemon intentar más tarde")
            emptyList()
        }
    }

    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {

    }
}