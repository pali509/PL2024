package asint;

import alex.UnidadLexica;
import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
      private Boolean prueba;
       public AnalizadorSintacticoTinyDJ(Reader input, Boolean prueba) throws IOException {
          super(input); 
          this.prueba = prueba;
       }
     protected final void traza_emparejamiento(UnidadLexica unidad) {
      if (!prueba){
         switch(unidad.clase()) {
            case VAR: case LENT: case LREAL: System.out.println(unidad.lexema()); break;
            default: System.out.println(unidad.clase().getImage());
      }
	 }
     } 
}
