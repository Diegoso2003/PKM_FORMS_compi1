package com.example.pkm_forms_proyecto1.analizadores;

import com.example.pkm_forms_proyecto1.auxiliares.Token;
import com.example.pkm_forms_proyecto1.enums.ColorToken;
import java.util.LinkedList;
import java.util.List;

%%

%public
%unicode
%class FormLexerColor
%standalone
%line
%column
%char

%state COMENTARIO, ESTILO, STRING

%{

private List<Token> listaTokens = new LinkedList<>();
private List<Token> cadena = new LinkedList<>();
private long inicio;
private long fin;

public List<Token> getListaTokens(){
	return listaTokens;
}

private void agregarToken(ColorToken color){
	listaTokens.add(new Token(yychar, yychar + yytext().length(), color));
}

private void agregarToken(ColorToken color, int longitud){
	listaTokens.add(new Token(yychar, yychar + longitud, color));
}

private void iniciarCadena(){
	yybegin(STRING);
	cadena.clear();
	inicio = yychar;
	fin = inicio + 1;
	cadena.add(new Token(inicio, fin, ColorToken.NARANJA));
}

private void agregarCadena(ColorToken color){
	String lexema = yytext();
	fin += lexema.length();
	cadena.add(new Token(yychar, fin, color));
}

private void finalizarCadena(){
	yybegin(YYINITIAL);
	cadena.add(new Token(yychar, yychar + yytext().length(), ColorToken.NARANJA));
	listaTokens.addAll(cadena);
}

private void agregarErrorCadena(){
	yybegin(YYINITIAL);
	listaTokens.add(new Token(inicio, fin, ColorToken.ROJO));
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
									case "styles":
										yybegin(ESTILO);
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
									case "TABLE":
									case "TEXT":
									case "VERTICAL":
									case "WHITE":
									case "WHILE":
									case "who_is_that_pokemon":
									case "width":
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
