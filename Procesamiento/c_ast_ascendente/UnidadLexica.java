package c_ast_ascendente;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   public static class StringLocalizado {
     private int beginLine;
     private int beginColumn;
     private String s;
     public StringLocalizado(String s, int beginLine, int beginColumn) {
         this.s = s;
         this.beginLine = beginLine;
         this.beginColumn = beginColumn;
     }
     public int beginLine() {return beginLine;}
     public int beginColumn() {return beginColumn;}
     public String image() {return s;}
   }
   public UnidadLexica(int beginLine, int beginbeginColumnumn, int clase, String lexema) {
     super(clase, new StringLocalizado(lexema,beginLine,beginbeginColumnumn));
   }
   public int clase () {return sym;}
   public int fila() {return ((StringLocalizado)value).beginLine();}
   public int columna() {return ((StringLocalizado)value).beginColumn();}
   public String lexema() {return ((StringLocalizado)value).image();}
}
