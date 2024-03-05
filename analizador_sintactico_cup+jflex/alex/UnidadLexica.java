package alex;

public abstract class UnidadLexica {
   private int clase;
   private int fila;
   private int columna;
   public UnidadLexica(int fila, int columna, int clase) {
     this.fila = fila;
     this.columna = columna;
     this.clase = clase;
   }
   public int clase () {return clase;}
   public abstract String lexema();
   public int fila() {return fila;}
   public int columna() {return columna;}
}
