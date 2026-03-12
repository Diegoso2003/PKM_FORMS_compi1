package com.example.pkm_forms_proyecto1.analizadores;

import com.example.pkm_forms_proyecto1.backend.Token;
import com.example.pkm_forms_proyecto1.enums.ColorToken;
import java.util.ArrayList;
import java.util.List;

%%

%public
%unicode
%class FormLexerColor
%standalone
%line
%column

%state COMENTARIO, ESTILO, STRING

%{

private List<Token> listaTokens = new ArrayList<>();
private List<Token> cadena = new ArrayList<>();
private int columna;
private int linea;
private int longitud;

public List<Token> getListaTokens(){
	return listaTokens;
}

private void agregarToken(ColorToken color){
	listaTokens.add(new Token(yyline, yycolumn, yytext().length(), color));
}

private void agregarToken(ColorToken color, int longitud){
	listaTokens.add(new Token(yyline, yycolumn, longitud, color));
}

private void iniciarCadena(){
	cadena.clear();
	linea = yyline + 1;
	columna = yycolumn + 1;
	longitud = 1;
	cadena.add(new Token(linea, columna, 1, ColorToken.NARANJA));
}

private void agregarCadena(ColorToken color){
	String lexema = yytext();
	longitud += lexema.length();
	cadena.add(new Token(yyline + 1, yycolumn + 1, lexema.length(), color));
}

private void finalizarCadena(){
	cadena.add(new Token(yyline, yycolumn, yytext().length(), ColorToken.NARANJA));
	listaTokens.addAll(cadena);
}

private void agregarErrorCadena(){
	listaTokens.add(new Token(linea, columna, longitud, ColorToken.ROJO));
}

%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\t\f ]
Numero = [0-9]+
Decimal = {Numero}\.{Numero}
Letra = [a-zA-Z]
Identificador = (_|{Letra})(_|{Letra}|{Numero})*
EmojiAper1 = "@[:"
EmojiAper2 = "@["
EmojiCerra1 = ":]"
EmojiCerra2 = "]"
Hexadecimal = "#"[0-9A-F]{6}
Sonrisa = {EmojiAper1}(")"+|"smile"){EmojiCerra1}
Triste = {EmojiAper1}("("+|"sad"){EmojiCerra1}
Serio = {EmojiAper1}("|"+|"serious"){EmojiCerra1}
Corazon = {EmojiAper2}("<"+"3"+|"smile"){EmojiCerra2}
Estrella = {EmojiAper1}"star"(("-"|":"){Numero})?{EmojiCerra1}
Gato = {EmojiAper1}("^^"|"cat"){EmojiCerra1}

%%

<ESTILO> {
	\"color\"						{ agregarToken(ColorToken.MORADO); }
	\"background color\"					{ agregarToken(ColorToken.MORADO); }
	\"font family\"						{ agregarToken(ColorToken.MORADO); }
	\"text size\"						{ agregarToken(ColorToken.MORADO); }
	\"border\"						{ agregarToken(ColorToken.MORADO); }
	"]"							{ yybegin(YYINITIAL); agregarToken(ColorToken.AZUL); }
}

<YYINITIAL, ESTILO> {
	{Identificador}						{ String lexema = yytext();
								  switch(lexema){
									case "BLACK":
									case "BLUE":
									case "content":
									case "correct":
									case "CURSIVE":
									case "DO":
									case "DOUBLE":
									case "DOTTED":
									case "draw":
									case "DROP_QUESTION":
									case "ELSE":
									case "elements":
									case "FOR":
									case "GREEN":
									case "height":
									case "HORIZONTAL":
									case "IF":
									case "in":
									case "label":
									case "LINE":
									case "MONO":
									case "MULTIPLE_QUESTION":
									case "number":
									case "NUMBER":
									case "OPEN_QUESTION":
									case "options":
									case "orientation":
									case "pointX":
									case "pointY":
									case "PURPLE":
									case "RED":
									case "SANS_SERIF":
									case "SECTION":
									case "SELECT_QUESTION":
									case "SKY":
									case "special":
									case "string":
									case "styles":
									case "TABLE":
									case "TEXT":
									case "VERTICAL":
									case "WHITE":
									case "WHILE":
									case "who_is_that_pokemon":
									case "with":
									case "YELLOW":
										agregarToken(ColorToken.MORADO, lexema.length());
										break;
									default:
										/* no hace nada */
								}
								}
	"<="							{ /* no hace nada */ }
	">="							{ /* no hace nada */ }
	"!!"							{ /* no hace nada */ }
	">"							{ /* no hace nada */ }
	"<"							{ /* no hace nada */ }
	"=="							{ /* no hace nada */ }
	"||"							{ /* no hace nada */ }
	"&&"							{ /* no hace nada */ }
	"~"							{ /* no hace nada */ }
	";"							{ /* no hace nada */ }
	"="							{ /* no hace nada */ }
	\"							{ agregarToken(ColorToken.NARANJA); iniciarCadena(); }
	{Numero}						{ agregarToken(ColorToken.CELESTE); }
	{Decimal}						{ agregarToken(ColorToken.CELESTE); }
	{Hexadecimal}						{ /* no hace nada */ }
	"/*"							{ yybegin(COMENTARIO); }
	"+"							{ agregarToken(ColorToken.VERDE); }
	"-"							{ agregarToken(ColorToken.VERDE); }
	"*"							{ agregarToken(ColorToken.VERDE); }
	"/"							{ agregarToken(ColorToken.VERDE); }
	"("							{ agregarToken(ColorToken.AZUL); }
	")"							{ agregarToken(ColorToken.AZUL); }
	"%"							{ agregarToken(ColorToken.VERDE); }
	"^"							{ agregarToken(ColorToken.VERDE); }
	".."							{ /* no hace nada */ }
	"."							{ /* no hace nada */ }
	":"							{ /* no hace nada */ }
	"{"							{ agregarToken(ColorToken.AZUL); }
	"}"							{ agregarToken(ColorToken.AZUL); }
	","							{ /* no hace nada */ }
	"["							{ agregarToken(ColorToken.AZUL); }
	"]"							{ agregarToken(ColorToken.AZUL); }
	"?"							{ /* no hace nada */ }
	"$".*							{ /* ignorar comentarios */ }
	{WhiteSpace}						{ /* ignorar espacios en blanco */ }
	.							{ agregarToken(ColorToken.ROJO); }
}

<STRING> {
	\\\"                					{ agregarCadena(ColorToken.NARANJA); }
	\"                  					{ finalizarCadena(); }
	{Sonrisa}						{ agregarCadena(ColorToken.AMARILLO); }
	{Triste}						{ agregarCadena(ColorToken.AMARILLO); }
	{Serio}							{ agregarCadena(ColorToken.AMARILLO); }
	{Corazon}						{ agregarCadena(ColorToken.AMARILLO); }
	{Gato}							{ agregarCadena(ColorToken.AMARILLO); }
	{Estrella}						{ agregarCadena(ColorToken.AMARILLO); }
	.							{ agregarCadena(ColorToken.NARANJA); }
	\n							{ agregarErrorCadena(); }
}

<COMENTARIO> {
	"*/"							{ yybegin(YYINITIAL); }
	[^]							{ /* ignorar comentarios */ }
}
