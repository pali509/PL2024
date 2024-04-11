import asint.SintaxisAbstractaTiny.*;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_ascendente.ConstructorASTTiny;
//import c_ast_descendente.ConstructorASTTiny;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args[0].equals("asc")) {
            if(args[1].equals("rec")) {
                Reader input = new InputStreamReader(new FileInputStream(args[2]));
                AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
                c_ast_ascendente.ConstructorASTTiny asint = new c_ast_ascendente.ConstructorASTTiny(alex);
                Prog prog = (Prog) asint.parse().value;
                System.out.println(new Evaluador().evalua(prog));
            }
            else if(args[1].equals("vis")) {
                Reader input = new InputStreamReader(new FileInputStream(args[2]));
                AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
                c_ast_ascendente.ConstructorASTTiny asint = new c_ast_ascendente.ConstructorASTTiny(alex);
                Prog prog = (Prog)asint.parse().value;
                prog.procesa(new Impresion());
            }
            else if(args[1].equals("int")) {
                Reader input = new InputStreamReader(new FileInputStream(args[2]));
                AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
                c_ast_ascendente.ConstructorASTTiny asint = new c_ast_ascendente.ConstructorASTTiny(alex);
                Prog prog = (Prog)asint.parse().value;
                prog.imprime();
            }
        }
        else {
            if(args[1].equals("rec")) {
                ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader(args[2]));
                asint.disable_tracing();
                System.out.println(new Evaluador().evalua(asint.analiza()));
            }
            else if(args[1].equals("vis")) {
                ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader(args[2]));
                asint.disable_tracing();
                asint.analiza().procesa(new Impresion());
            }
            else if(args[1].equals("int")) {
                ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader(args[2]));
                asint.disable_tracing();
                asint.analiza().imprime();
            }

        }
    }
}
