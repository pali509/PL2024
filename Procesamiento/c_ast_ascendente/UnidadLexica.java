package c_ast_ascendente;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   public static class StringLocalizado {
     private int beginLine;
     private int beginColumn;
     private String image;
     public StringLocalizado(String s, int fila, int col) {
         this.image = s;
         this.beginLine = fila;
         this.beginColumn = col;  
     }
     public int beginLine() {return beginLine;}
     public int beginColumn() {return beginColumn;}
     public String image() {return image;}
   }
   public UnidadLexica(int fila, int columna, int clase, String lexema) {
     super(clase, new StringLocalizado(lexema,fila,columna));  
   }
   public int clase () {return sym;}
   public int fila() {return ((StringLocalizado)value).beginLine();}
   public int columna() {return ((StringLocalizado)value).beginColumn();}
   public String lexema() {return ((StringLocalizado)value).image();}
}
