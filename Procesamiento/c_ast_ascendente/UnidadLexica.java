package c_ast_ascendente;

import asint.SintaxisAbstractaTiny.StringLocalizado;
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
       public int fila() {return beginLine;}
       public int col() {return beginColumn;}
       public String str() {
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

   public StringLocalizado StringLocalizado(String s, int fila, int col) {
	   return new StringLocalizado(s, fila, col);
   }
   
   
   
}
