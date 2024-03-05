package alex;

public class UnidadLexicaUnivaluada extends UnidadLexica {
   public String lexema() {throw new UnsupportedOperationException();}   
   public UnidadLexicaUnivaluada(int fila, int columna, int clase) {
     super(fila,columna,clase);  
   }

}
