package alex;

public enum ClaseLexica {
 IDENTIFICADOR, 
 LITENTERO, 
 LITREAL, 
 LITCADENA,
 SUMA("+"),
 RESTA("-"),
 MUL("*"),
 DIV("/"),
 MOD("%"),
 MAYOR(">"),
 MENOR("<"),
 MAYORIGUAL(">="),
 MENORIGUAL("<="),
 IGUAL("=="),
 DESIGUAL("!="),
 ASIG("="),
 PARA("("),
 PARC(")"),
 PUNTOYCOMA(";"),
 COMA(","),
 CIRCUNFLEJO("^"),
 PUNTO("."),
 CORCHETEA("["),
 CORCHETEC("]"),
 LLAVEA("{"),
 LLAVEC("}"),
 ARROBA("@"),
 AMPERSAND("&"),
 AMPERSAND2("&&"),
 INT("<int>"), 
 REAL("<real>"),
 BOOL("<bool>"), 
 STRING("<string>"), 
 NULL("<null>"), 
 PROC("<proc>"), 
 IF("<if>"),
 ELSE("<else>"), 
 WHILE("<while>"), 
 STRUCT("<struct>"), 
 NEW("<new>"), 
 DELETE("<delete>"), 
 READ("<read>"), 
 WRITE("<write>"), 
 NL("<nl>"), 
 TYPE("<type>"), 
 CALL("<call>"), 
 TRUE("<true>"), 
 FALSE("<false>"), 
 AND("<and>"), 
 NOT("<not>"), 
 OR("<or>"),  
 EOF("EOF");
	
private String image;
public String getImage() {
     return image;
 }
 private ClaseLexica() {
     image = toString();
 }
 private ClaseLexica(String image) {
    this.image = image;  
 }

}
