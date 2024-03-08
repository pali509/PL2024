import asint.*;

import java.io.InputStreamReader;
public class DomJudgejavacc{
   public static void main(String[] args) throws Exception {
     try{  
      AnalizadorSintacticoTinyjavacc asint = new AnalizadorSintacticoTinyDJjavacc(new InputStreamReader(System.in));
      asint.analiza();
     }
     catch(ParseException e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
     catch(TokenMgrError e) {
        System.out.println("ERROR_LEXICO"); 
     }
   }
}