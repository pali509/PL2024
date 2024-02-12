public enum ClaseLexica { //Comprobar si faltan cosas, que creo que si
    VAR, //poner como lo de abajo pero < >
    ENT, 
    REAL, 
    BOOL, 
    TRUE, 
    FALSE, 
    AND, 
    NOT, 
    OR, 
    //Faltan LENT y LREAL lo tiene el de jflex
    PAP("("), 
    PCIERRE(")"), 
    ARROBA("@"), 
    AMPERSAND("&&"), 
    PUNTOYCOMA(";"), 
    LLAVEA("{"), 
    LLAVEC("}"),
    MAS("+"),
    MENOS("-"), 
    POR("*"), 
    DIV("/"), 
    MAYOR(">"), 
    MENOR("<"), 
    MAYORIG(">="), 
    MENORIG("<="), 
    IGUAL("=="), 
    DESIGUAL("!="), 
    ASIG("="), 
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

/*CODIGO EJEMPLO
 * IDEN, 
 ENT, 
 REAL, 
 PAP("("), 
 PCIERRE(")"), 
 IGUAL("="), 
 COMA(","), 
 MAS("+"), 
 MENOS("-"), 
 POR("*"), 
 DIV("/"), 
 EVALUA("<evalua>"), 
 DONDE("<donde>"), 
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
 */
}