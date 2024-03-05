package alex;

public class UnidadLexicaMultivaluada extends UnidadLexica {
  private String lexema;
  public UnidadLexicaMultivaluada(int fila, int columna, int clase, String lexema) {
     super(fila,columna,clase);  
     this.lexema = lexema;
   }
  public String lexema() {return lexema;}
  public String toString() {
    return lexema();  
  }
}
