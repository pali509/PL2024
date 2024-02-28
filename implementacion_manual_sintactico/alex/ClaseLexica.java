package alex;

public enum ClaseLexica {
	// Literales y variables
    VAR,
    LENT, 
    LREAL,
    // Palabras reservadas
    TRUE("<true>"), 
    FALSE("<false>"), 
    AND("<and>"), 
    NOT("<not>"), 
    OR("<or>"),  
    INT("<int>"), 
    REAL("<real>"),
    BOOL("<bool>"), 
    // Caracteres
    PAP("("), 
    PCIERRE(")"),  
    ARROBA("@"), 
    AMPERSAND("&&"), 
    PUNTOYCOMA(";"), 
    LLAVEA("{"), 
    LLAVEC("}"),
    // Operadores
    MAS("+"),
    MENOS("-"), 
    POR("*"), 
    DIV("/"), 
    // Simbolos de comparacion
    MAYOR(">"), 
    MENOR("<"), 
    MAYORIG(">="), 
    MENORIG("<="), 
    IGUAL("=="), 
    DESIGUAL("!="), 
    ASIG("="), 
    // End of File
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