//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package alex;

public abstract class UnidadLexica {
    private ClaseLexica clase;
    private int fila;
    private int columna;

    public UnidadLexica(int fila, int columna, ClaseLexica clase) {
        this.fila = fila;
        this.columna = columna;
        this.clase = clase;
    }

    public ClaseLexica clase() {
        return this.clase;
    }

    public abstract String lexema();

    public int fila() {
        return this.fila;
    }

    public int columna() {
        return this.columna;
    }
}
