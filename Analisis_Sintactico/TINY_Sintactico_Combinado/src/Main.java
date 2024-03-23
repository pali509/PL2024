import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws Exception{
        String file;
        String option;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Teclea el nombre del archivo a analizar");
        file = reader.readLine();
        System.out.println("Teclea 'desc' para analisis descendente o 'asc' para analisis ascendente");
        option = reader.readLine();
        try{
            if (option.equals("desc")){

            }
            else if (option.equals("asc")){

            }
            else{
                System.out.println("Opción no válida");
            }
        }
        catch(ParseException e) {
            System.out.println("ERROR_SINTACTICO");
        }
        catch(asint.TokenMgrError e) {
            System.out.println("ERROR_LEXICO");
        }
    }
    }
}