package com.example.pkm_forms_proyecto1.backend.atributos.pregunta.opciones

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError
import com.example.pkm_forms_proyecto1.auxiliares.agregarMensaje
import com.example.pkm_forms_proyecto1.backend.InfoCalculo
import com.example.pkm_forms_proyecto1.backend.Simbolo
import com.example.pkm_forms_proyecto1.backend.elementos.Elemento
import com.example.pkm_forms_proyecto1.backend.nodos.NodoExpresion
import com.example.pkm_forms_proyecto1.enums.TipoError
import com.example.pkm_forms_proyecto1.excepciones.ErrorSemantico
import java.util.LinkedList

class ListaOpciones(simbolo: Simbolo, val lista: LinkedList<NodoExpresion>): ParamOpci(simbolo) {
    override fun obtenerOpciones(infoCalculo: InfoCalculo, simbolo: Simbolo): LinkedList<String> {
        val lista2 = LinkedList<String>()
        if(lista.isEmpty()){
            val error = MensajeError(TipoError.SEMANTICO)
            error.descripcion = "debe haber almenos una opcion"
            throw ErrorSemantico(error)
        }
        for(nodo in lista){
            nodo.evaluarNodo(infoCalculo)
            lista2.add(validarString(infoCalculo, nodo))
        }
        return lista2
    }

    override fun agregarAtributo(
        infoCalculo: InfoCalculo,
        elemento: Elemento
    ) {
    }
}