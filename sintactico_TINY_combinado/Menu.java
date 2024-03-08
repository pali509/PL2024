import analizador_sintactico_cup+flex.*;

public class Menu {
    public static void main(String[] args) throws Exception {
        try{
            /*
            1.Pedir archivo
            2.Pedir opcion
            3. llamar con la opcion elegida a la clase Main de el que toque con e larchivo seleccionado
             */
        }
        catch(ParseException e) {
            System.out.println("ERROR_SINTACTICO");
        }
        catch(TokenMgrError e) {
            System.out.println("ERROR_LEXICO");
        }
    }
}

}