package c_ast_ascendente;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {

	public static class StringLocalizado {
       private String image;
       private int beginLine;
       private int beginColumn;
       public StringLocalizado(String s, int fila, int col) {
           this.image = s;
           this.beginLine = fila;
           this.beginColumn = col;
       }
       public int beginLine() {return beginLine;}
       public int beginColumn() {return beginColumn;}
       public String image() {
           return image;
       }
   }
   public UnidadLexica(int fila, int columna, int clase, String lexema) {
     super(clase, new StringLocalizado(lexema,fila,columna));  
   }
   public int clase () {return sym;}
   public int fila() {return ((StringLocalizado)value).fila();}
   public int columna() {return ((StringLocalizado)value).col();}
   public String lexema() {return ((StringLocalizado)value).str();}
}
