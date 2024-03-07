import asint.AnalizadorSintacticoTiny;
import asint.AnalizadorSintacticoTinyDJ;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
public class ProgramaDePrueba{
   public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String file = reader.readLine();
     try{  
      AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTinyDJ(new FileReader(file));
      asint.analiza();
     }
     catch(ErrorSintactico e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
     catch(ErrorLexico e) {
        System.out.println("ERROR_LEXICO"); 
     }
   }
}