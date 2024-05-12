import asint.SintaxisAbstractaTiny.*;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_ascendente.BISReader;
import c_ast_ascendente.ConstructorASTTiny;
import c_ast_ascendente.GestionErroresTiny.ErrorLexico;
import c_ast_ascendente.GestionErroresTiny.ErrorSintactico;
import c_ast_descendente.ConstructorASTsTiny;
import c_ast_descendente.ParseException;
import c_ast_descendente.TokenMgrError;
import maquinaP.MaquinaP;
import procesamientos.Asignacion_espacio;
import procesamientos.Comprobacion_tipos;
import procesamientos.Etiquetado;
import procesamientos.Gen_cod;
import procesamientos.MensajesError;
import procesamientos.Vinculacion;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;


public class DomJudge {
private static Prog construye_ast(Reader input, char constructor) throws Exception {
		if(constructor == 'a') {
			try {
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				// en esta fase no necesitamos volcar los distintos tokens le�dos: utilizamos
				// directamente la clase ConstructorASTTiny, en lugar de su especializaci�n
				// ConstructorASTTinyDJ, e invocamos a parse, en lugar de debug_parse.
				ConstructorASTTiny asint = new ConstructorASTTiny(alex);
				Prog p = (Prog) asint.parse().value;
				return p;
		}
		catch(ErrorLexico e) {
			System.out.println("ERROR_LEXICO");
		}
		catch(ErrorSintactico e) {
			System.out.println("ERROR_SINTACTICO");
			System.exit(0);
		}
		}
		else if(constructor == 'd') {
			try {
				// no necesitamos volcar los tokens: usamos directamente la clase generada
				// por javacc, y deshabilitamos la traza (por si estuviera activo DEBUG_PARSER.
				c_ast_descendente.ConstructorASTsTiny asint = new c_ast_descendente.ConstructorASTsTiny(input);
				asint.disable_tracing();
				return asint.analiza();
		}
		catch(TokenMgrError e) {
			System.out.println("ERROR_LEXICO");
		}
		catch(ParseException e) {
			System.out.println("ERROR_SINTACTICO");
			System.exit(0);
		}
		}
		else {
			System.err.println("Metodo de construccion no soportado:"+constructor);
		}
		return null;
		}

		public static void procesa(Prog p, Reader datos) throws Exception {

			Vinculacion v = new Vinculacion();
			v.procesa(p);
			if(!v.getMen()) {
				Comprobacion_tipos t = new Comprobacion_tipos();
				t.procesa(p);
				if(!t.getMenPreTipado() && !t.getMenTipado()){
					new Asignacion_espacio().procesa(p);
					new Etiquetado().procesa(p);
					MaquinaP m = new MaquinaP(datos,500,5000,5000,10);
					new Gen_cod(m).procesa(p);
					m.ejecuta();
				} 
			}
		}
		public static void main(String[] args) throws Exception {
			char constructor = (char)System.in.read();
			Reader r = new BISReader(System.in);
			Prog prog = construye_ast(r,constructor);
			if(prog != null) {
				procesa(prog, r);
			}
		} 
}
