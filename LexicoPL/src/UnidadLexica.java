
public abstract class UnidadLexica {
	public enum ClaseLexica {
	    IDEN, ENT, REAL, PAP, PCIERRE, IGUAL, COMA,
	    MAS, MENOS, POR, DIV, EVALUA, DONDE, EOF
	}
	private ClaseLexica clase;
    private int fila;
    private int columna;
    public UnidadLexica(int fila, int columna, ClaseLexica clase) {
    this.fila = fila;
    this.columna = columna;
    this.clase = clase;
    }
    public ClaseLexica clase () {return clase;}
    public abstract String lexema();
    public int fila() {return fila;}
    public int columna() {return columna;}
}
