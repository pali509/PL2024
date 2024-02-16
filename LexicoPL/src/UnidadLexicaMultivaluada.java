
public class UnidadLexicaMultivaluada extends UnidadLexica {
    private String lexema;
    public UnidadLexicaMultivaluada(int fila, int columna, ClaseLexica ent, String lexema) {
    super(fila,columna,ent);
    this.lexema = lexema;
    }
    public String lexema() {return lexema;}
    public String toString() {
    return lexema();
    }
}

