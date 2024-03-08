package asint;
import java.io.FileReader;

public class Mainjavacc{
   public static void main(String[] args) throws Exception {
      AnalizadorSintacticoTinyjavacc asint = new AnalizadorSintacticoTinyDJjavacc(new FileReader(args[0]));
      asint.disable_tracing();
	  asint.analiza();
   }
}