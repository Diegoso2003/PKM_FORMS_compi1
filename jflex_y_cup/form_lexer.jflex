package //nombre del package

import java_cup.runtime.*;

%%

%public
%unicode
%class DiagramaLexer
%cup
%line
%column

%state STRING, INSTRUCCION, ESTRELLA, ESTRELLA_COMPLE1, ESTRELLA_COMPLE2, COMENTARIO, ESTILO

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

<ESTILO> {
	\"color\"						{ return symbol(sym.COLOR); }
	\"background color\"					{ return symbol(sym.BACKGROUND); }
	\"font family\"						{ return symbol(sym.FONT); }
	\"text size\"						{ return symbol(sym.TEXT_SIZE); }
	\"border\"						{ return symbol(sym.BORDER); }
}

<YYINITIAL, INSTRUCCION, ESTILO> {
	{Identificador}						{ String lexema = yytext();
								  switch(lexema){
									case "BLACK":
										return symbol(sym.TCOLOR, Tcolor.BLACK);
									case "BLUE":
										return symbol(sym.TCOLOR, Tcolor.BLUE);
									case "content":
										return symbol(sym.content);
									case "correct":
										return symbol(sym.CORRECT);
									case "CURSIVE":
										return symbol(sym.FUENTE, Fuente.CURSIVE);
									case "DO":
										return symbol(sym.DO);
									case "DOUBLE":
										return symbol(sym.GROSOR, Grosor.DOUBLE);
									case "DOTTED":
										return symbol(sym.GROSOR, Grosor.Double);
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
										return symbol(sym.TCOLOR, Tcolor.GREEN);
									case "height":
										return symbol(sym.HEIGHT);
									case "HORIZONTAL":
										return symbol(sym.HORIZONTAL);
									case "IF":
										return symbol(sym.IF);
									case "in":
										return symbol(sym.IN);
									case "LINE":
										return symbol(sym.GROSOR, Grosor.LINE);
									case "MONO":
										return symbol(sym.FUENTE, Fuente.MONO);
									case "MULTIPLE_QUESTION":
										return symbol(sym.MULTIPLE_QUESTION);
									case "number":
										cambiarAInstruccion(); return symbol(sym.TIPO, Tipo.NUMBER);
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
										return symbol(sym.TCOLOR, Tcolor.PURPLE);
									case "RED":
										return symbol(sym.TCOLOR, Tcolor.RED);
									case "SANS_SERIF":
										return symbol(sym.FUENTE, Fuente.SANS_SERIF);
									case "SECTION":
										return symbol(sym.SECTION);
									case "SELECT_QUESTION":
										return symbol(sym.SELECT_QUESTION);
									case "SKY":
										return symbol(sym.TCOLOR, Tcolor.SKY);
									case "special":
										return symbol(sym.TIPO, Tipo.SPECIAL);
									case "string":
										cambiarAInstruccion(); return symbol(sym.TIPO, Tipo.STRING);
									case "styles":
										yybegin(ESTILO); return symbol(sym.STYLES);
									case "TEXT":
										return symbol(sym.TEXT);
									case "VERTICAL":
										return symbol(sym.VERTICAL);
									case "WHITE":
										return symbol(sym.TCOLOR, Tcolor.WHITE);
									case "WHILE":
										return symbol(sym.WHILE);
									case "who_is_that_pokemon":
										return symbol(sym.FUNCION_SPECIAL);
									case "with":
										return symbol(sym.WITH);
									case "YELLOW":
										return symbol(sym.TCOLOR, Tcolor.YELLOW);
									default:
										return symbol(sym.IDENTI, lexema);
								}
								}
	"<="							{ return symbol(sym.OPERACOMP, yytext()); }
	">="							{ return symbol(sym.OPERACOMP, yytext()); }
	"!!"							{ return symbol(sym.OPERACOMP, yytext()); }
	">"							{ return symbol(sym.MAYOR_QUE); }
	"<"							{ return symbol(sym.MENOR_QUE); }
	"=="							{ return symbol(sym.OPERACOMP, yytext()); }
	"||"							{ return symbol(sym.OPERALOGIOR); }
	"&&"							{ return symbol(sym.OPERALOGIAND); }
	"~"							{ return symbol(sym.OPERALOGINOT); }
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
	"]"							{ yybegin(YYINITIAL); return symbol(sym.CORCHECERRA); }
	"?"							{ return symbol(sym.COMODIN); }
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
