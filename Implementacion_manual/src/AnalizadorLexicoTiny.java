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
	 REC_VAR, REC_MAS, REC_MENOS, REC_POS, REC_CERO, REC_LREAL, REC_LREAL_CERO, REC_EXP, REC_EXP_CERO, REC_MUL, REC_DIV, REC_MAYOR, REC_MENOR, REC_MAYOR_IGUAL, 
	 REC_MENOR_IGUAL, REC_IGUAL, REC_DESIGUAL, REC_ASIG, REC_PAR_APERTURA, REC_PAR_CIERRE, REC_ARROBA, REC_AMPERSAND2, REC_PUNTO_COMA, REC_LLAVE_APERTURA, 
	 REC_LLAVE_CIERRE, REC_EOF,
	 //ESTADOS NO FINALES
	 INICIO, REC_PUNTO, REC_E, CASI_LREAL, REC_E_MAS_MENOS, REC_AMPERSAND, REC_ALMOHADILLA, REC_EXCLAMACION, REC_COM
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
	         // Empezamos con case INICIO y luego vamos abriendo los estados en el orden en el que aparecen en inicio, luego abrimos los del siguiente etc...
	           case INICIO:
	               	//Numeros
	               if (hayCero()) transita(Estado.REC_CERO);
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
	               	// Cadenas Ignorables
	               else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_ALMOHADILLA);
	               else if (haySep()) transitaIgnorando(Estado.INICIO); // ' ' \n \t \r \b
	               else if (hayEOF()) transita(Estado.REC_EOF);
	               else error();
	               break;
	           case REC_CERO:
	               	// Paso a leer decimales
	               if (hayPunto()) transita(Estado.REC_PUNTO);
	               	// Paso a leer exponenciales
	               else if (hayE()) transita(Estado.REC_E);
	               	// Paso a leer enteros
	               else return unidadLitEntero();
	               break;
	           case REC_POS:
	               	// Paso a leer decimales
	               if (hayPunto()) transita(Estado.REC_PUNTO);
	               	// Paso a leer exponenciales
	               else if (hayE()) transita(Estado.REC_E);
	               else return unidadLitEntero();
	               break;
	           case REC_VAR:
	               	// Sigo leyendo variable
	               if (hayLetra() || hayDigito()) transita(Estado.REC_VAR);
	               else return unidadVar();  
	               break;
	            case REC_MAS:
	            	// Paso a leer digitos
	               if (hayDigitoPos()) transita(Estado.REC_POS);
	               	// Paso a leer cero
	               else if(hayCero()) transita(Estado.REC_CERO);
	               else return unidadMas();
	               break;
	            case REC_MENOS: 
	            	// Paso a leer digitos
	               if (hayDigitoPos()) transita(Estado.REC_POS);
	               	// Paso a leer cero
	               else if(hayCero()) transita(Estado.REC_CERO);
	               else return unidadMenos();
	               break;
	             case REC_MUL:
	               return unidadPor();
	             case REC_DIV:
	               return unidadDiv();
	             case REC_MAYOR:
	               // Paso a leer mayor o igual
	               if (hayIgual()) transita(Estado.REC_MAYOR_IGUAL);
	               else return unidadMayor();
	             case REC_MAYOR_IGUAL: 
	               return unidadMayorIg();
	             case REC_MENOR:
	               // Paso a leer menor o igual
	               if (hayIgual()) transita(Estado.REC_MENOR_IGUAL);
	               else return unidadMenor();
	             case REC_MENOR_IGUAL:
	               return unidadMenorIg();
	             case REC_ASIG:
	               // Paso a leer igualdad
	               if (hayIgual()) transita(Estado.REC_IGUAL);
	               else return unidadAsig();
	             case REC_EXCLAMACION:
	               // Paso a leer desigualdad
	               if(hayIgual()) transita(Estado.REC_DESIGUAL);
	               else error();
	               break;
	             case REC_DESIGUAL: 
	               return unidadDesigual();
	             case REC_PAR_APERTURA:
	               return unidadPAp();
	             case REC_PAR_CIERRE:
	               return unidadPCierre();
	             case REC_ARROBA:
	               return unidadArroba();
	             case REC_AMPERSAND:
	            	 // Paso a leer segundo ampersand
	               if(hayAmpersand()) transita(Estado.REC_AMPERSAND2);
	               else error();
	               break;
	             case REC_AMPERSAND2: 
	               return unidadAmpersand();
	             case REC_PUNTO_COMA:
	               return unidadPuntoyComa();
	             case REC_LLAVE_APERTURA:
	               return unidadLlaveA();
	             case REC_LLAVE_CIERRE: 
	               return unidadLlaveC();
	             case REC_ALMOHADILLA: 
	            	 // Paso a leer comentario
	               if(hayAlmohadilla()) transita(Estado.REC_COM);
	               else error();
	               break;
	             case REC_EOF: 
	               return unidadEof();  
	             case REC_IGUAL: 
	            	 return unidadIgual();
	             case REC_COM:
	            	 // Paso a estado inicial
	                if (haySalto()) transitaIgnorando(Estado.INICIO);
	                // Paso a leer EOF
	                else if (hayEOF()) transita(Estado.REC_EOF);
	                else transitaIgnorando(Estado.REC_COM);
	                break;
	             case REC_LREAL:
	            	 // Paso a leer real (no final)
	               if(hayCero()) transita(Estado.CASI_LREAL);
	               	// Paso a leer E
	               else if(hayE()) transita(Estado.REC_E);
	               	// Paso a leer real
	               else if(hayDigitoPos()) transita(Estado.REC_LREAL);
	               else return unidadLitReal();
	             case REC_LREAL_CERO: 
	            	// Paso a leer real (no final)
	               if(hayCero()) transita(Estado.CASI_LREAL);
	               	// Paso a leer E
	               else if(hayE()) transita(Estado.REC_E);
	               else return unidadLitReal();
	             case REC_EXP:
	            	 // Paso a leer exponencial
	               if(hayDigitoPos()) transita(Estado.REC_EXP);
	               else return unidadLitReal();
	             case REC_EXP_CERO: 
	               return unidadLitReal(); 
	             case REC_E: 
	            	 // Paso a leer exponente cero
	               if(hayCero()) transita(Estado.REC_EXP_CERO);
	               	// Paso a leer signo
	               else if(haySuma() || hayResta()) transita(Estado.REC_E_MAS_MENOS);
	               	// Paso a leer exponente
	               else if(hayDigitoPos()) transita(Estado.REC_EXP);
	               else error();
	               break;
	             case CASI_LREAL:
	            	// Paso a leer real (no final) 
	               if(hayCero()) transita(Estado.CASI_LREAL);
	               	// Paso a leer real
	               else if(hayDigitoPos()) transita(Estado.REC_LREAL);
	               else error();
	               break;
	             case REC_E_MAS_MENOS:
	            	// Paso a leer exponente cero
	               if(hayCero()) transita(Estado.REC_EXP_CERO);
	               	// Paso a leer exponente
	               else if(hayDigitoPos()) transita(Estado.REC_EXP);
	               else error();
	               break;
	
	          }
	      }    
	    }
	
	    private void transita(Estado sig) throws IOException { 
	      lex.append((char)sigCar);
	      sigCar();         
	      estado = sig;
	    }
	    
	    private void transitaIgnorando(Estado sig) throws IOException {
	      sigCar();         
	      filaInicio = filaActual;
	      columnaInicio = columnaActual;
	      estado = sig;
	    }
	    
	    private void sigCar() throws IOException {
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
	 
	    // TRANSICIONES
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
	    private boolean hayIgual() {return sigCar == '=';}
	    private boolean hayPunto() {return sigCar == '.';}
	    private boolean hayAlmohadilla() {return sigCar == '#';}
	    private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n'||sigCar == '\r' || sigCar == '\b';}
	    private boolean hayEOF() {return sigCar == -1;}
	    private boolean haySalto() {return sigCar == '\n';}
	    private boolean hayArroba() {return sigCar == '@';}
	    private boolean hayPuntoComa() {return sigCar == ';';}
	    private boolean hayAmpersand() {return sigCar == '&';}
	    private boolean hayLlAp() {return sigCar == '{';}
	    private boolean hayLlCierre() {return sigCar == '}';}
	    private boolean hayMayor() {return sigCar == '>';}
	    private boolean hayMenor() {return sigCar == '<';}
	    private boolean hayExclamacion() {return sigCar == '!';}
	    private boolean hayE() {return sigCar == 'e' || sigCar == 'E';}
	 
	    
	    // UNIDADES LEXICAS
	    // SOLO PARA LOS ESTADOS FINALES, LOS NO FINALES HACEN ERROR() SI NO HAY ENTRADA VÃ�LIDA
	    private UnidadLexica unidadLitEntero() {
	      return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.LENT,lex.toString());     
	    }    
	    private UnidadLexica unidadLitReal() {
	      return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.LREAL,lex.toString());     
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
	     private UnidadLexica unidadReal() {
	       return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.REAL);     
	     } 
	     private UnidadLexica unidadEnt() {
	       return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.INT);     
	     } 
	     
	 
	    private void error() { 
	      int curCar = sigCar;
	      try{
	        sigCar();
	      }
	      catch(IOException e) {}
	      throw new ECaracterInesperado("("+filaActual+','+columnaActual+"):Caracter inexperado:"+(char)curCar); 
	    }
	 
	    public static void main(String arg[]) throws IOException {
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