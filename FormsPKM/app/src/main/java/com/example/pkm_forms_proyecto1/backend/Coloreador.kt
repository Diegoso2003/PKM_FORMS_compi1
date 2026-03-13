package com.example.pkm_forms_proyecto1.backend

import com.example.pkm_forms_proyecto1.analizadores.FormLexerColor
import java.io.StringReader

class Coloreador {

    fun obtenerColor(texto: String): List<Token> {
        var lexerColor = FormLexerColor(StringReader(texto))
        lexerColor.yylex()
        return lexerColor.listaTokens
    }
}