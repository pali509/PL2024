 //PD: INDENTAR BIEN!!!! HAY ESPACIOS RAROS
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
   
   private static enum Estado {
    //ESTADOS FINALES
    INICIO, REC_REAL, REC_INT, REC_BOOL, REC_TRUE, REC_FALSE, REC_AND, REC_NOT, REC_OR, REC_VAR, REC_MAS, REC_MENOS, REC_POS, REC_CERO, REC_LREAL, REC_EXP, 
    REC_EXP_CERO, REC_MUL, REC_DIV, REC_MAYOR, REC_MENOR, REC_MAYOR_IGUAL, REC_MENOR_IGUAL, REC_IGUAL, REC_DESIGUAL, REC_ASIG, REC_ESPACIO, REC_PAR_APERTURA,
    REC_PAR_CIERRE, REC_ARROBA, REC_AMPERSAND2, REC_PUNTO_COMA, REC_LLAVE_APERTURA, REC_LLAVE_CIERRE, REC_EOF, REC_COM, REC_C, REC_SALTO_LINEA, REC_TABULADOR,
    REC_RETORNO_CARRO, REC_RETROCESO,
    //ESTADOS NO FINALES:
    REC_R, REC_RE, REC_REA, REC_I, REC_IN, REC_B, REC_BO, REC_BOO, REC_T, REC_TR, REC_TRU, REC_F, REC_FA, REC_FAL, REC_FALS, REC_A, REC_AN, REC_N, REC_NO,
    REC_O, REC_PUNTO, REC_E, CASI_LREAL, REC_E_MAS_MENOS, REC_IGNO, REC_AMPERSAND, REC_ALMOHADILLA, REC_EXCLAMACION
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
        switch(estado) { //Orden para que este limpito: empezamos con case INICIO y luego vamos abriendo los estados en el orden en el que aparecen en inicio, luego abrimos los del siguiente etc...
          case INICIO: 
              //Palabras reservadas
              if (hayi()) transita(Estado.REC_I);
              else if (hayr()) transita(Estado.REC_R);
              else if (hayb()) transita(Estado.REC_B);
              else if (hayt()) transita(Estado.REC_T);
              else if (hayf()) transita(Estado.REC_F);
              else if (haya()) transita(Estado.REC_A);
              else if (hayn()) transita(Estado.REC_N);
              else if (hayo()) transita(Estado.REC_O);
              //Números
              else if (hayCero()) transita(Estado.REC_CERO);
              else if (hayDigitoPos()) transita(Estado.REC_POS);
              //Variables
              else if(hayLetra())  transita(Estado.REC_VAR);
              //Operadores
              else if (haySuma()) transita(Estado.REC_MAS);
              else if (hayResta()) transita(Estado.REC_MENOS);
              else if (hayMul()) transita(Estado.REC_MUL);
              else if (hayDiv()) transita(Estado.REC_DIV);
              else if (hayMayor()) transita(Estado.REC_MAYOR);
              else if (hayMenor()) transita(Estado.REC_MENOR);
              else if (hayIgual()) transita(Estado.REC_ASIG);
              else if (hayExclamacion()) transita(Estado.REC_EXCLAMACION);
              //Simbolos de puntuacion
              else if (hayPAp()) transita(Estado.REC_PAR_APERTURA);
              else if (hayPCierre()) transita(Estado.REC_PAR_CIERRE);
              else if (hayArroba()) transita(Estado.REC_ARROBA);
              else if (hayAmpersand()) transita(Estado.REC_AMPERSAND);
              else if (hayPuntoComa()) transita(Estado.REC_PUNTO_COMA);
              else if (hayLlAp()) transita(Estado.REC_LLAVE_APERTURA);
              else if (hayLlCierre()) transita(Estado.REC_LLAVE_CIERRE);
              //Cadenas Ignorables
              else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_ALMOHADILLA);
              else if (hayBarra()) transitaIgnorando(Estado.REC_IGNO);
              else if (hayEspacio()) transitaIgnorando(Estado.REC_ESPACIO);
              else if (hayEOF()) transita(Estado.REC_EOF);
              else error();
              break;
          case REC_I:
              //Sigo leyendo int
              if (hayn()) transita(Estado.REC_IN);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_R:
              //Sigo leyendo real
              if (haye()) transita(Estado.REC_RE);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_B:
              //Sigo leyendo bool
              if (hayo()) transita(Estado.REC_BO);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_T:
              //Sigo leyendo true
              if (hayt()) transita(Estado.REC_TR);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_F:
              //Sigo leyendo false
              if (haya()) transita(Estado.REC_FA);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_A:
              //Sigo leyendo and
              if (hayn()) transita(Estado.REC_AN);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_N:
              //Sigo leyendo not
              if (hayo()) transita(Estado.REC_NO);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_OR:
              //Sigo leyendo or
              if (hayr()) transita(Estado.REC_OR);
              //Leo una variable
              else if(hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else error();
              break;
          case REC_CERO:
              //Paso a leer decimales
              if (hayPunto()) transita(Estado.REC_PUNTO);
              //Paso a leer exponenciales
              else if (haye()) transita(Estado.REC_E);
              else return unidadCero();
              break;
          case REC_POS:
              //Paso a leer decimales
              if (hayPunto()) transita(Estado.REC_PUNTO);
              //Paso a leer exponenciales
              else if (haye()) transita(Estado.REC_E);
              else return unidadEntero();
              break;
          case REC_VAR:
              //Sigo leyendo variable
              if (hayLetra() || hayDigito()) transita(Estado.REC_VAR);
              else return unidadVar();  
              break;
           case REC_MAS:
              if (hayDigitoPos()) transita(Estado.REC_POS);
              else if(hayCero()) transita(Estado.REC_CERO);
              else return unidadMas();
              break;
           case REC_MENOS: 
              if (hayDigitoPos()) transita(Estado.REC_POS);
              else if(hayCero()) transita(Estado.REC_CERO);
              else return unidadMenos();
              break;
            case REC_MUL:
              return unidadMul();
              break;
            case REC_DIV:
              return unidadDiv();
              break;
            case REC_MAYOR:
              //Vamos a mayor o igual
              if (hayIgual()) transita(Estado.REC_MAYOR_IGUAL);
              else return unidadMayor();
            case REC_MENOR:
              //Vamos a menor o igual
              if (hayIgual()) transita(Estado.REC_MENOR_IGUAL);
              else return unidadMenor();
            case REC_ASIG:
              //Vamos a igualdad
              if (hayIgual()) transita(Estado.REC_IGUAL);
              else return unidadAsig();
            case REC_EXCLAMACION:
              //Vamos a desigualdad
              if(hayIgual()) transita(Estado.REC_DESIGUAL);
              else error();
            case REC_PAR_APERTURA:

            case REC_PAR_CIERRE:
            case REC_ARROBA:
            case REC_AMPERSAND:
            case REC_PUNTO_COMA:
            case REC_LLAVE_APERTURA:
            case REC_LLAVE_CIERRE:
            case REC_ALMOHADILLA:
            case REC_IGNO:
            case REC_ESPACIO:
            case REC_EOF: 
              return unidadEof();            
            case REC_IGUAL: return unidadIgual();
            case REC_COM: 
               if (hayNL()) transitaIgnorando(Estado.INICIO);
               else if (hayEOF()) transita(Estado.REC_EOF);
               else transitaIgnorando(Estado.REC_COM);
               break;
           
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
                                      sigCar >= 'A' && sigCar <= 'z' ||
                                      sigCar == '_';}
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
   private boolean hayAmpersand() {return sigCar == '&';} //??
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