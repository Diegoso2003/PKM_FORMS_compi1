package com.example.pkm_forms_proyecto1.analizadores;

import com.example.pkm_forms_proyecto1.auxiliares.MensajeError;
import com.example.pkm_forms_proyecto1.enums.Tipo;
import com.example.pkm_forms_proyecto1.enums.TipoError;
import com.example.pkm_forms_proyecto1.enums.Orientacion;
import com.example.pkm_forms_proyecto1.enums.TipoBorde;
import java.util.List;
import java_cup.runtime.*;

%%

%public
%unicode
%class FormLexer
%cup
%line
%column

%state STRING, INSTRUCCION, COMENTARIO, ESTILO

%{

	private StringBuilder texto = new StringBuilder();
	private int estadoAnterior = YYINITIAL;
	private int linea;
	private int columna;
	private List<MensajeError> errores;
	
	public void setErrores(List<MensajeError> errores){
		this.errores = errores;
	}
	
	private Symbol symbol(int type){
		return new Symbol(type, yyline+1, yycolumn+1);
	}
    	
	private Symbol symbol(int type, Object object){
		return new Symbol(type, yyline+1, yycolumn+1, object);
	}

	private void iniciarCadena(){
		yybegin(STRING);
    		texto.setLength(0);
    		linea = yyline+1;
    		columna = yycolumn+1;
    	}
    	
    	private Symbol reportarCadena(){
    		return new Symbol(sym.CADENA, linea, columna, texto.toString());
    	}
    	
    	private void reportarErrorLexico(){
    		int estado = yystate();
    		MensajeError error = new MensajeError(TipoError.LEXICO);
            	error.setColumna(yycolumn+1);
            	error.setLinea(yyline+1);
    		if (estado == STRING){
    			error.setDescripcion("cadena no cerrada");
    			error.setLexema(texto.toString());
    		} else {
    			error.setDescripcion("simbolo no reconocido");
              		error.setLexema(yytext());
    		}
    		errores.add(error);
    	}
    	
    	private void cambiarAInstruccion(){
    		estadoAnterior = INSTRUCCION;
    		yybegin(INSTRUCCION);
    	}
    	
    	private void regresarEstado(){
    		yybegin(estadoAnterior);
    		estadoAnterior = YYINITIAL;
    	}
    	
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [\t\f ]
Numero = [0-9]+
Decimal = {Numero}.{Numero}
Letra = [a-zA-Z]
Identificador = (_|{Letra})(_|{Letra}|{Numero})*
Hexadecimal = "#"[0-9A-F]{6}

%%

<ESTILO> {
	\"color\"						{ return symbol(sym.COLOR); }
	\"background color\"					{ return symbol(sym.BACKGROUND); }
	\"font family\"						{ return symbol(sym.FONT); }
	\"text size\"						{ return symbol(sym.TEXT_SIZE); }
	\"border\"						{ return symbol(sym.BORDER); }
	"]"							{ yybegin(YYINITIAL); return symbol(sym.CORCHECERRA); }
}

<INSTRUCCION> {
	\n							{ yybegin(YYINITIAL); return symbol(sym.DELIMIT); }
}

<YYINITIAL, INSTRUCCION, ESTILO> {
	{Identificador}						{ String lexema = yytext();
								  switch(lexema){
									case "BLACK":
										return symbol(sym.TCOLOR, lexema);
									case "BLUE":
										return symbol(sym.TCOLOR, lexema);
									case "content":
										return symbol(sym.CONTENT);
									case "correct":
										return symbol(sym.CORRECT);
									case "CURSIVE":
										return symbol(sym.FUENTE, lexema);
									case "DO":
										return symbol(sym.DO);
									case "DOUBLE":
										return symbol(sym.GROSOR, TipoBorde.DOUBLE);
									case "DOTTED":
										return symbol(sym.GROSOR, TipoBorde.DOTTED);
									case "draw":
										cambiarAInstruccion(); return symbol(sym.DRAW);
									case "DROP_QUESTION":
										return symbol(sym.DROP_QUESTION);
									case "ELSE":
										return symbol(sym.ELSE);
									case "elements":
										return symbol(sym.ELEMENTS);
									case "FOR":
										return symbol(sym.FOR);
									case "GREEN":
										return symbol(sym.TCOLOR, lexema);
									case "height":
										return symbol(sym.HEIGHT);
									case "HORIZONTAL":
										return symbol(sym.TORIENTACION, Orientacion.HORIZONTAL);
									case "IF":
										return symbol(sym.IF);
									case "in":
										return symbol(sym.IN);
									case "label":
										return symbol(sym.LABEL);
									case "LINE":
										return symbol(sym.GROSOR, TipoBorde.LINE);
									case "MONO":
										return symbol(sym.FUENTE, lexema);
									case "MULTIPLE_QUESTION":
										return symbol(sym.MULTIPLE_QUESTION);
									case "number":
										cambiarAInstruccion(); return symbol(sym.TIPO, Tipo.NUMBER);
									case "NUMBER":
										 return symbol(sym.NUMBER);
									case "OPEN_QUESTION":
										return symbol(sym.OPEN_QUESTION);
									case "options":
										return symbol(sym.OPTIONS);
									case "orientation":
										return symbol(sym.ORIENTATION);
									case "pointX":
										return symbol(sym.POINTX);
									case "pointY":
										return symbol(sym.POINTY);
									case "PURPLE":
										return symbol(sym.TCOLOR, lexema);
									case "RED":
										return symbol(sym.TCOLOR, lexema);
									case "SANS_SERIF":
										return symbol(sym.FUENTE, lexema);
									case "SECTION":
										return symbol(sym.SECTION);
									case "SELECT_QUESTION":
										return symbol(sym.SELECT_QUESTION);
									case "SKY":
										return symbol(sym.TCOLOR, lexema);
									case "special":
										return symbol(sym.TIPO, Tipo.SPECIAL);
									case "string":
										cambiarAInstruccion(); return symbol(sym.TIPO, Tipo.STRING);
									case "styles":
										yybegin(ESTILO); return symbol(sym.STYLES);
									case "TABLE":
										return symbol(sym.TABLE);
									case "TEXT":
										return symbol(sym.TEXT);
									case "VERTICAL":
										return symbol(sym.TORIENTACION, Orientacion.VERTICAL);
									case "WHITE":
										return symbol(sym.TCOLOR, lexema);
									case "WHILE":
										return symbol(sym.WHILE);
									case "who_is_that_pokemon":
										return symbol(sym.POKEMON, lexema);
									case "width":
										return symbol(sym.WIDTH);
									case "YELLOW":
										return symbol(sym.TCOLOR, lexema);
									default:
										return symbol(sym.IDENTI, lexema);
								}
								}
	"<="							{ return symbol(sym.MENOR_IGUAL); }
	">="							{ return symbol(sym.MAYOR_IGUAL); }
	"!!"							{ return symbol(sym.DIFERENTE); }
	">"							{ return symbol(sym.MAYOR_QUE); }
	"<"							{ return symbol(sym.MENOR_QUE); }
	"=="							{ return symbol(sym.IGUAL); }
	"||"							{ return symbol(sym.OPERALOGIOR); }
	"&&"							{ return symbol(sym.OPERALOGIAND); }
	"~"							{ return symbol(sym.OPERALOGINOT); }
	";"							{ return symbol(sym.PUNTO_COMA); }
	"="							{ return symbol(sym.ASIGN); }
	\"							{ iniciarCadena(); }
	{Numero}						{ return symbol(sym.NUMERO, Integer.parseInt(yytext())); }
	{Decimal}						{ return symbol(sym.DECIMAL, Double.parseDouble(yytext())); }
	{Hexadecimal}						{ return symbol(sym.HEXADECIMAL, yytext()); }
	"/*"							{ yybegin(COMENTARIO); }
	"+"							{ return symbol(sym.SUMA); }
	"-"							{ return symbol(sym.RESTA); }
	"*"							{ return symbol(sym.MULTI); }
	"/"							{ return symbol(sym.DIVIS); }
	"("							{ return symbol(sym.PARENAPER); }
	")"							{ return symbol(sym.PARENCERRA); }
	"%"							{ return symbol(sym.MODULO); }
	"^"							{ return symbol(sym.POTEN); }
	".."							{ return symbol(sym.RANGO); }
	"."							{ return symbol(sym.PUNTO); }
	":"							{ return symbol(sym.DOS_PUNTOS); }
	"{"							{ return symbol(sym.LLAVE_APER); }
	"}"							{ return symbol(sym.LLAVE_CERRA); }
	","							{ return symbol(sym.COMA); }
	"["							{ return symbol(sym.CORCHEAPER); }
	"]"							{ return symbol(sym.CORCHECERRA); }
	"?"							{ return symbol(sym.COMODIN); }
	"$".*							{ /* ignorar comentarios */ }
	{WhiteSpace}						{ /* ignorar espacios en blanco */ }
	.							{ reportarErrorLexico(); }
}

<STRING> {
	\\\"                					{ texto.append("\\\""); }
	\"                  					{ return reportarCadena();}
	\n							{ reportarErrorLexico(); }
	.							{ texto.append(yytext()); }
}

<COMENTARIO> {
	"*/"							{ yybegin(YYINITIAL); }
	[^]							{ /* ignorar comentarios */ }
}

<<EOF>>								{ return symbol(sym.EOF); }
