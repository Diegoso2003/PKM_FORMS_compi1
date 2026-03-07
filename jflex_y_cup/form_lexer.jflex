package //nombre del package

import java_cup.runtime.*;

%%

%public
%unicode
%class DiagramaLexer
%cup
%line
%column

%state STRING, INSTRUCCION, ESTRELLA, ESTRELLA_COMPLE1, ESTRELLA_COMPLE2, COMENTARIO

%{

	private StringBuilder texto = new StringBuilder();
	private StringBuilder textoAuxi = new StrigBuilder();
	private int estadoAnterior = YYINITIAL;
	private int numeroEstrella;
	private int linea;
	private int columna;
	private List<MensajeError> errores;
	
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
    		MensajeError error = new MensajeError(TipoEnum.LEXICO);
            	error.setColumna(yycolumn+1);
            	error.setLinea(yyline+1);
    		if (estado == 0 || estado == 6){
    			error.setDescripcion("simbolo no reconocido en pseudocodigo");
              		error.setLexema(yytext());
    		} else if (estado == 2){
    			error.setDescripcion("simbolo no reconocido en configuracion");
              		error.setLexema(yytext());
    		} else {
    			texto.append(textoAuxi.toString());
    			error.setDescripcion("cadena no cerrada");
    			error.setLexema(texto.toString());
    		}
    		errores.add(error);
    	}
    	
    	private void agregarEstrellas(){
    		if(numeroEstrella == 0){
    			texto.append(textoAuxi.toString()).append(":]");
    			return;
    		}
    		for(int i = 0; i < numeroEstrella; i++){
    			texto.append("⭐");
    		}
    		textoAuxi.setLength(0);
    	}
    	
    	private void cambiarAInstruccion(){
    		estadoAnterior = INSTRUCCION;
    		yybegin(INSTRUCCION);
    	}
    	
    	private void regresarEstado(){
    		yybegin(estadoAnterior);
    		estadoAnterior = YYINITIAL;
    	}
    	
    	private void regresarACadena(){
    		yybegin(STRING); 
    		texto.append(textoAuxi.toString()).append(yytext());
    		textoAuxi.setLength(0);
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
Estrella = {EmojiAper1}"star"
Gato = {EmojiAper1}("^^"|"cat"){EmojiCerra1}

%%

<YYINITIAL, INSTRUCCION> {
	"<="							{ return symbol(sym.OPERACOMP, yytext()); }
	">="							{ return symbol(sym.OPERACOMP, yytext()); }
	"!!"							{ return symbol(sym.OPERACOMP, yytext()); }
	">"							{ return symbol(sym.MAYOR_QUE); }
	"<"							{ return symbol(sym.MENOR_QUE); }
	"=="							{ return symbol(sym.OPERACOMP, yytext()); }
	"||"							{ return symbol(sym.OPERALOGIOR); }
	"&&"							{ return symbol(sym.OPERALOGIAND); }
	"~"							{ return symbol(sym.OPERALOGINOT); }
	{Identificador}						{ String lexema = yytext();
								  switch(lexema){
								  	case "number":
								  		cambiarAInstruccion(); return symbol(sym.TIPO, Tipo.NUMBER);
								  	case "string":
								  		cambiarAInstruccion(); return symbol(sym.TIPO, Tipo.STRING);
								  	case "special":
								  		return symbol(sym.TIPO, Tipo.SPECIAL);
								  	case "in":
								  		return symbol(sym.IN);
								  	case "IF":
								  		return symbol(sym.IF);
								  	case "WHILE":
								  		return symbol(sym.WHILE);
								  	case "FOR":
								  		return symbol(sym.FOR);
								  	case "DO":
								  		return symbol(sym.DO);
								  	case "ELSE":
								  		return symbol(sym.ELSE);
								  	case "who_is_that_pokemon":
								  		return symbol(sym.FUNCION_SPECIAL);
								  	case "draw":
								  		return symbol(sym.DRAW);
								  	case "OPEN_QUESTION":
								  		return symbol(sym.OPEN_QUESTION);
								  	case "DROP_QUESTION":
								  		return symbol(sym.DROP_QUESTION);
								  	case "SELECT_QUESTION":
								  		return symbol(sym.SELECT_QUESTION);
								  	case "MULTIPLE_QUESTION":
								  		return symbol(sym.MULTIPLE_QUESTION);
								  	case "SECTION":
								  		return symbol(sym.SECTION);
								  	case "TEXT":
										return symbol(sym.TEXT);
									case "options":
								  		return symbol(sym.OPTIONS);
									case "correct":
										return symbol(sym.CORRECT);
									case "elements":
										return symbol(sym.ELEMENTS);
									case "styles":
										return symbol(sym.STYLES);
									case "VERTICAL":
										return symbol(sym.VERTICAL);
									case "HORIZONTAL":
										return symbol(sym.HORIZONTAL);
									case "orientation":
										return symbol(sym.ORIENTATION);
									case "pointX":
										return symbol(sym.POINTX);
									case "pointY":
										return symbol(sym.POINTY);
									case "RED":
										return symbol(sym.COLOR, Color.RED);
									case "BLUE":
										return symbol(sym.COLOR, Color.BLUE);
									case "GREEN":
										return symbol(sym.COLOR, Color.GREEN);
									case "PURPLE":
										return symbol(sym.COLOR, color.PURPLE);
									case "SKY":
										return symbol(sym.COLOR, color.SKY);
									case "YELLOW":
										return symbol(sym.COLOR, color.YELLOW);
									case "BLACK":
										return symbol(sym.COLOR, color.BLACK);
									case "WHITE":
										return symbol(sym.COLOR, color.WHITE);
									case "with":
										return symbol(sym.WITH);
									case "height":
										return symbol(sym.HEIGHT);
									case "content":
										return symbol(sym.content);
									case "MONO":
										return symbol(sym.FUENTE, Fuente.MONO);
									case "SANS_SERIF":
										return symbol(sym.FUENTE, Fuente.SANS_SERIF);
									case "CURSIVE":
										return symbol(sym.FUENTE, Fuente.CURSIVE);
									case "LINE":
										return symbol(sym.GROSOR, Grosor.LINE);
									case "DOTTED":
										return symbol(sym.GROSOR, Grosor.Double);
									case "DOUBLE":
										return symbol(sym.GROSOR, Grosor.DOUBLE);
								  	default:
								  		return symbol(sym.IDENTI, lexema);
								  }}						
	";"							{ return symbol(sym.PUNTO_COMA); }
	"="							{ return symbol(sym.ASIGN); }
	\"							{ iniciarCadena(); }
	{Numero}						{ return symbol(sym.NUMERO); }
	{Decimal}						{ return symbol(sym.DECIMAL); }
	"/*"							{ yybegin(COMENTARIO); }
	"+"							{ return symbol(sym.SUMA); }
	"-"							{ return symbol(sym.RESTA); }
	"*"							{ return symbol(sym.MULTI); }
	"/"							{ return symbol(sym.DIVIS); }
	"("							{ return symbol(sym.PARENAPER); }
	")"							{ return symbol(sym.PARENCERRA); }
	"%"							{ return symbol(sym.MODULO); }
	"^"							{ return symbol(sym.POTEN); }
	"]"							{ yybegin(YYINITIAL); return symbol(sym.CORCHECERRA); }
	".."							{ return symbol(sym.RANGO); }
	"."							{ return symbol(sym.PUNTO); }
	":"							{ return symbol(sym.DOS_PUNTOS); }
	"{"							{ return symbol(sym.LLAVE_APER); }
	"}"							{ return symbol(sym.LLAVE_CERRA); }
	","							{ return symbol(sym.COMA); }
	"["							{ return symbol(sym.CORCHEAPER); }
	"]"							{ return symbol(sym.CORCHECERRA); }
	"$".*							{ /* ignorar comentarios */ }
	.							{ reportarErrorLexico(); }
}

<STRING, ESTRELLA, ESTRELLA_COMPLE1, ESTRELLA_COMPLE2> {
	\\\"                					{ regresarACadena(); texto.append('"'); }
	\"                  					{ regresarEstado(); return reportarCadena();}
	\n							{ reportarErrorLexico(); }
}

<ESTRELLA> {
	":]"							{ yybegin(YYINITIAL); texto.append("⭐");}
	(":"|"-")						{ yybegin(ESTRELLA_COMPLE1); textoAuxi.append(yytext()); }
	.							{ yybegin(STRING); texto.append(textoAuxi.toString()).append(yytext()); }
}

<ESTRELLA_COMPLE1> {
	{Numero}						{ yybegin(ESTRELLA_COMPLE2); numeroEstrella = Integer.parseInt(yytext()); textoAuxi.append(numeroEstrella); }
	.							{ regresarACadena(); }
}

<ESTRELLA_COMPLE2> {
	{EmojiCerra1}						{ agregarEstrellas(); }
	.							{ regresarACadena(); }
}

<STRING> {
	{Sonrisa}						{ texto.append("😀"); }
	{Triste}						{ texto.append("🥲"); }
	{Serio}							{ texto.append("😐"); }
	{Corazon}						{ texto.append("❤️"); }
	{Gato}							{ texto.append("😺"); }
	{Estrella}						{ yybegin(ESTRELLA); textoAuxi.setLength(0); textoAuxi.append(yytext()); }
	.							{ texto.append(yytext()); }
}

<INSTRUCCION> {
	\n							{ yybegin(YYINITIAL); return symbol(sym.DELIMIT); }
}

<COMENTARIO> {
	"*/"							{ yybegin(YYINITIAL); }
	[^]							{ /* ignorar comentarios */ }
}
