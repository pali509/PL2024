import java.io.IOException;
import java.io.Reader;


public class AnalizadorLexicoEval {
	enum Estado { INICIO, REC_MUL, REC_DIV, REC_PAP, REC_PCIE, REC_ID, REC_ENT, REC_0}
	
    private Reader input; // Flujo de entrada
    private StringBuffer lex; // Lexema del componente que se está reconociendo
    private int sigCar; // Siguiente carácter a procesar
    private int filaInicio; // Fila de inicio del componente léxico
    private int columnaInicio; // Columna de inicio del componente léxico
    private int filaActual; // Fila en el punto de lectura actual
    private int columnaActual; // Columna en el punto de lectura actual
    private Estado estado; // Estado del autómata

    public void AnalizadorLexicoTiny(Reader input) throws IOException {
        this.input = input;
        lex = new StringBuffer();
        sigCar = input.read();
        filaActual=1;
        columnaActual=1;
    }
    public UnidadLexica sigToken() throws IOException {
        estado = Estado.INICIO;
        filaInicio = filaActual;
        columnaInicio = columnaActual;
        lex.delete(0,lex.length());
        while(true) {
        switch(estado) {
        case INICIO:
        if(hayLetra()) transita(Estado.REC_ID);
        else if (hayDigitoPos()) transita(Estado.REC_ENT);
        else if (hayCero()) transita(Estado.REC_0);
    
        else error();
        break;
        case REC_ID:
        if (hayLetra() || hayDigito()) transita(Estado.REC_ID);
        else return unidadId();
        break;
            }
        }
    }
    
    private boolean hayLetra() {
    return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'z';
    }
    
    private UnidadLexica unidadEnt() {
        return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ENT,lex.toString());
    }
    
    private UnidadLexica unidadMas() {
        return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAS);
    }

    private UnidadLexica unidadId() {
        switch(lex.toString()) {
        case "evalua":
        return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EVALUA);
        case "donde":
        return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DONDE);
        default:
        return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.IDEN,lex.toString());
        }
    }     
    private void transita(Estado sig) throws IOException {
        lex.append((char)sigCar);
        sigCar();
        estado = sig;
    }
    
    private void transitaIgnorando(Estado sig) throws IOException {
        sigCar();
        filaInicio = filaActual;
        columnaInicio = columnaActual;
        estado = sig;
    }
    // Secuencia de caracteres que representan el fin de línea en la plataforma (LF en Unix,
    // CR+LF en MS Windows ...)
    private static String NL = System.getProperty("line.separator");
    
    private void sigCar() throws IOException {
        sigCar = input.read();
        // Si comienzo fin de línea, reconocerlo. Como resultado sigCar se fija a ‘\n’
        if (sigCar == NL.charAt(0)) saltaFinDeLinea();
        if (sigCar == '\n') {
        filaActual++;
        columnaActual=0;
        }
        else {
        columnaActual++;
        }
    }
    private void saltaFinDeLinea() throws IOException {
        for (int i=1; i < NL.length(); i++) {
        sigCar = input.read();
        if (sigCar != NL.charAt(i)) error();
        }
        sigCar = '\n';
    }
    private void error() {
        System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");
        System.exit(1);
    }        

}






    