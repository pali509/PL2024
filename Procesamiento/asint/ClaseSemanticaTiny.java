package asint;


import c_ast_ascendente.UnidadLexica;

public class ClaseSemanticaTiny extends SintaxisAbstractaTiny {
    public ClaseSemanticaTiny() {
        super();
    }
    public Exp mkop2(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "+": return suma(opnd1,opnd2);
            case "-": return resta(opnd1,opnd2);
            case "*": return mul(opnd1,opnd2);
            case "/": return div(opnd1,opnd2);

            case "%": return mod(opnd1,opnd2);
            case "=": return asig(opnd1,opnd2);
            case ">": return mayor(opnd1,opnd2);
            case "<": return menor(opnd1,opnd2);
            case ">=": return mayorIg(opnd1,opnd2);
            case "<=": return menorIg(opnd1,opnd2);
            case "==": return igual(opnd1,opnd2);
            case "!=": return desigual(opnd1,opnd2);

            default: throw new UnsupportedOperationException("Bad op");
        }
    }
    public Exp mkop1(String op, Exp opnd) {
        switch(op) {
            case "-": return neg(opnd);
            case "not": return not(opnd);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
    public StringLocalizado StringLocalizado(String s, int fila, int col) {
        return new StringLocalizado(s, fila, col);
    }
}
