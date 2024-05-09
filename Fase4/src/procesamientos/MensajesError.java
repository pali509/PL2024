package procesamientos;

import java.util.ArrayList;
import java.util.List;

public class MensajesError {

    //PROPUESTA DE USO: 
    //en cada procesamiento 1. creo una instancia de esta clase con un booleano que indica si hay errores
    //                      2. voy añadiendo los errores a la instancia que he creado
    //                      3. Si el booleano == true --> ordeno a la instancia que imprima y termino la ejecucion
    //                      4. Si el booleano == false --> no imprimo nada y voy al siguiente procesamiento

    private boolean hayError;
    private List<Integer> listaFilas;
    private List<Integer> listaCols;
    private String tipo;

    public MensajesError(){
        hayError = false;
        listaFilas = new ArrayList<>();
        listaCols = new ArrayList<>();
    }
    
    public boolean getHayError(){
        return hayError;
    }
    
    public void setTipoError(String e) {
    	this.tipo = "Errores_" + e;
    }

    public void addError(int f, int c){
        listaFilas.add(f);
        listaCols.add(f);
    }

    public void getErrores(){
        //ordenaErrores();
        for (int i = 0; i < listaFilas.size(); i++) {
            System.out.println(tipo + " fila:" + listaFilas.get(i) + " col:" + listaCols.get(i));
        }
    }

    //POR SI HACE FALTA ORDENAR
    /*public void ordenaErrores(){
        Ordeno primero por filas y para filas iguales por columnas
    } */
}