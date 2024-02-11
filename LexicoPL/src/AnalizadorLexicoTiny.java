
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {
    
   public static class ECaracterInesperado extends RuntimeException {
       public ECaracterInesperado(String msg) {
           super(msg);
       }
   }; 

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private static String NL = System.getProperty("line.separator");
   
   private static enum Estado { //He puesto solo los estados finales, hay que poner todos?
    INICIO, REC_REAL, REC_INT, REC_BOOL, REC_TRUE, REC_FALSE, REC_AND, REC_NOT, REC_OR, REC_VAR, REC_MAS, REC_MENOS, REC_POS, REC_CERO, REC_LREAL, REC_EXP, 
    REC_EXP_CERO, REC_MUL, REC_DIV, REC_MAYOR, REC_MENOR, REC_MAYOR_IGUAL, REC_MENOR_IGUAL, REC_IGUAL, REC_DESIGUAL, REC_ASIG, REC_ESPACIO, REC_PAR_APERTURA,
    REC_PAR_CIERRE, REC_ARROBA, REC_AMPERSAND2, REC_PUNTO_COMA, REC_LLAVE_APERTURA, REC_LLAVE_CIERRE, REC_EOF, REC_COM, REC_C, REC_SALTO_LINEA, REC_TABULADOR,
    REC_RETORNO_CARRO, REC_RETROCESO
    //ESTADOS NO FINALES: Rec r, Rec re, Rec rea, Rec i, Rec in, Rec b, Rec bo, Rec boo, Rec t, Rec tr, Rec tru, Rec f, Rec fa, Rec fal, Rec fals, Rec a, Rec an,
    //Rec n, Rec no, Rec o, Rec ., Rec e, Casi LReal, Rec e+-, Rec Igno, Rec ampersand
   }

   private Estado estado;

   public AnalizadorLexicoTiny(Reader input) throws IOException {
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
              if(hayLetra())  transita(Estado.REC_ID); //Esto hay que dividirlo, depende de la letra, además no es REC_ID
              else if (hayDigitoPos()) transita(Estado.REC_ENT);
              else if (hayCero()) transita(Estado.REC_0);
              else if (haySuma()) transita(Estado.REC_MAS);
              else if (hayResta()) transita(Estado.REC_MENOS);
              else if (hayMul()) transita(Estado.REC_POR); //Cambiar por REC_MUL
              else if (hayDiv()) transita(Estado.REC_DIV);
              else if (hayPAp()) transita(Estado.REC_PAP);
              else if (hayPCierre()) transita(Estado.REC_PCIERR);
              else if (hayIgual()) transita(Estado.REC_IGUAL);
              else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_COM); //Hay que asegurarse de que son dos almohadillas no una
              else if (haySep()) transitaIgnorando(Estado.INICIO); //Espacio?
              else if (hayEOF()) transita(Estado.REC_EOF);
              //FALTAN: mayor, menor, mayorigual, menorigual, desigual, asignacion, arroba, ampersand, puntocoma, llave ap, llave ci, barra \
              else error();
              break;
           case REC_MAS:
               if (hayDigitoPos()) transita(Estado.REC_ENT); //Revisar si esos son los estados a los que voy
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadMas();
               break;
           case REC_MENOS: 
               if (hayDigitoPos()) transita(Estado.REC_ENT); //Revisar si esos son los estados a los que transito
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadMenos();
               break;
           case REC_DIV: return unidadDiv();              
           case REC_IGUAL: return unidadIgual();
           case REC_COM: 
               if (hayNL()) transitaIgnorando(Estado.INICIO);
               else if (hayEOF()) transita(Estado.REC_EOF);
               else transitaIgnorando(Estado.REC_COM);
               break;
           case REC_EOF: return unidadEof();
            //HAY QUE HACER ESTO PARA TODOS LOS ESTADOS 
         }
     }    
   }
   private void transita(Estado sig) throws IOException { //CREO QUE ESTO NO SE MODIFICA
     lex.append((char)sigCar);
     sigCar();         
     estado = sig;
   }
   private void transitaIgnorando(Estado sig) throws IOException { //CREO QUE ESTO NO SE MODIFICA
     sigCar();         
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     estado = sig;
   }
   private void sigCar() throws IOException { //CREO QUE NO HAY QUE MODIFICAR ESTO, O IGUAL HAY QUE METER AL RESTO DE IGNORABLES IGUAL QUE EL NL
     sigCar = input.read();
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

   //AQUÍ AÑADIR MÉTODOS DE TODAS LAS TRANSICIONES QUE NO ESTÉN DEFINIDAS --> IMPORTANTE: CARACTER A CARACTER
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'z';}
   private boolean hayDigitoPos() {return sigCar >= '1' && sigCar <= '9';}
   private boolean hayCero() {return sigCar == '0';}
   private boolean hayDigito() {return hayDigitoPos() || hayCero();}
   private boolean haySuma() {return sigCar == '+';}
   private boolean hayResta() {return sigCar == '-';}
   private boolean hayMul() {return sigCar == '*';}
   private boolean hayDiv() {return sigCar == '/';}
   private boolean hayPAp() {return sigCar == '(';}
   private boolean hayPCierre() {return sigCar == ')';}
   private boolean hayAsig() {return sigCar == '=';}
   private boolean hayPunto() {return sigCar == '.';}
   private boolean hayAlmohadilla() {return sigCar == '#';}
   private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n';}
   private boolean hayNL() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
   private boolean hayEOF() {return sigCar == -1;}
   //Nuevos que acabo de poner
   private boolean hayArroba() {return sigCar == '@';}
   private boolean hayPuntoComa() {return sigCar == ';';}
   private boolean hayAmpersand1() {return sigCar == '&';} //??
   private boolean hayAmpersand2() {return sigCar == '&';}//??
   private boolean hayLlAp() {return sigCar == '{';}
   private boolean hayLlCierre() {return sigCar == '}';}
   private boolean hayMayor() {return sigCar == '>';}
   private boolean hayMenor() {return sigCar == '<';}
   private boolean hayMayorIgual() {return sigCar == '>=';} //??
   private boolean hayMenorIgual() {return sigCar == '<=';} //??
   private boolean hayIgual() {return sigCar == '==';} //??
   private boolean hayDesigual() {return sigCar == '!=';} //??


   //Aquí hacer una función por clase léxica
    
   private UnidadLexica unidadEnt() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ENT,lex.toString());     
   }    
   private UnidadLexica unidadReal() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.REAL,lex.toString());     
   }    
   private UnidadLexica unidadMas() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAS);     
   }    
   private UnidadLexica unidadMenos() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOS);     
   }    
   private UnidadLexica unidadPor() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.POR);     
   }    
   private UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIV);     
   }    
   private UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAP);     
   }    
   private UnidadLexica unidadPCierre() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCIERRE);     
   }    
   private UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);     
   }      
   private UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
   }    
    //Las que acabo de hacer
    private UnidadLexica unidadVar() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.VAR);     
    }      
    private UnidadLexica unidadBool() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL);     
    }   
    private UnidadLexica unidadTrue() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.TRUE);     
    }      
    private UnidadLexica unidadFalse() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.FALSE);     
    }    
    private UnidadLexica unidadAnd() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AND);     
    }      
    private UnidadLexica unidadNot() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NOT);     
    }    
    private UnidadLexica unidadOr() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.OR);     
    }      
    private UnidadLexica unidadArroba() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ARROBA);     
    }    
    private UnidadLexica unidadAmpersand() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AMPERSAND);     
    }      
    private UnidadLexica unidadPuntoyComa() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PUNTOYCOMA);     
    }    
    private UnidadLexica unidadLlaveA() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.LLAVEA);     
    }      
    private UnidadLexica unidadLlaveC() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.LLAVEC);     
    }    
    private UnidadLexica unidadMayor() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAYOR);     
    }      
    private UnidadLexica unidadMenor() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOR);     
    }    
    private UnidadLexica unidadMayorIg() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAYORIG);     
    }      
    private UnidadLexica unidadMenorIg() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENORIG);     
    }    
    private UnidadLexica unidadDesigual() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DESIGUAL);     
    }      
    private UnidadLexica unidadAsig() {
      return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ASIG);     
    }      

   private void error() { //CREO QUE ESTO NO SE MODIFICA
     int curCar = sigCar;
     try{
       sigCar();
     }
     catch(IOException e) {}
     throw new ECaracterInesperado("("+filaActual+','+columnaActual+"):Caracter inexperado:"+(char)curCar); 
   }

   public static void main(String arg[]) throws IOException { //CREO QUE ESTO NO SE MODIFICA
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    } 
}