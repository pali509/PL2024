//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package alex;

public class UnidadLexicaUnivaluada extends UnidadLexica {
    public String lexema() {
        throw new UnsupportedOperationException();
    }

    public UnidadLexicaUnivaluada(int fila, int columna, ClaseLexica clase) {
        super(fila, columna, clase);
    }

    public String toString() {
        return this.clase().getImage();
    }
}
