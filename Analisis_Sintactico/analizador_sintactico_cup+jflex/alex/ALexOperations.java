package alex;

import asint.ClaseLexica;

public class ALexOperations {
  public static class ECaracterInesperado extends RuntimeException {
      public ECaracterInesperado(String msg) {
          super(msg);
      }
  }  
    
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadId() {
     return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
	 return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LITENTERO,alex.lexema()); 
  } 
  public UnidadLexica unidadLitReal() {
	 return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LITEREAL,alex.lexema());
  } 
  public UnidadLexica unidadCadena() {
	 return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LITCADENA,alex.lexema()); 
  } 
  public UnidadLexica unidadSuma() {
	 return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.SUMA, "+");
  } 
  public UnidadLexica unidadResta() {
	 return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.RESTA, "-");
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MUL, "*");
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DIV, "/");
  } 
  public UnidadLexica unidadMod() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MOD, "%");
  }
  public UnidadLexica unidadMayor() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MAYOR, ">");
  }
  public UnidadLexica unidadMenor() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MENOR, "<");
  }
  public UnidadLexica unidadMayorIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MAYORIGUAL, ">=");
  }
  public UnidadLexica unidadMenorIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MENORIGUAL, "<=");
  }
  public UnidadLexica unidadIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IGUAL, "==");
  }  
  public UnidadLexica unidadDesigual() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DESIGUAL, "!=");
  }
  public UnidadLexica unidadAsig() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ASIG, "=");
  }  
  public UnidadLexica unidadParA() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PARA, "(");
  } 
  public UnidadLexica unidadParC() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PARC, ")");
  }   
  public UnidadLexica unidadCorcheteA() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CORCHETEA, "[");
  }   
  public UnidadLexica unidadCorcheteC() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CORCHETEC, "]");
  }   
  public UnidadLexica unidadPuntoYComa() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PUNTOYCOMA, ";");
  } 
  public UnidadLexica unidadCircunflejo() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CIRCUNFLEJO, "^");
  }
  public UnidadLexica unidadComa() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.COMA, ",");
  }
  public UnidadLexica unidadPunto() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PUNTO, ".");
  }   
  public UnidadLexica unidadLlaveA() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LLAVEA, "{");
  }   
  public UnidadLexica unidadLlaveC() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LLAVEC, "}");
  }    
  public UnidadLexica unidadArroba() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ARROBA, "@");
  }  
  public UnidadLexica unidadAmpersand() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.AMPERSAND, "&");
  } 
  public UnidadLexica unidadAmpersand2() {
	  return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.AMPERSAND2, "&&");
  } 
  public UnidadLexica unidadInt() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.INT, "<int>");
	  }  
  public UnidadLexica unidadReal() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.REAL, "<real>");
	  }  
  public UnidadLexica unidadBool() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.BOOL, "<bool>");
	  } 
  public UnidadLexica unidadString() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.STRING, "<string>");
	  }  
  public UnidadLexica unidadNull() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NULL, "<null>");
	  } 
  public UnidadLexica unidadProc() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PROC, "<proc>");
	  } 
  public UnidadLexica unidadIf() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IF, "<if>");
	  } 
  public UnidadLexica unidadElse() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ELSE, "<else>");
	  }   
  public UnidadLexica unidadWhile() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.WHILE, "<while>");
	  }   
  public UnidadLexica unidadStruct() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.STRUCT, "<struct>");
	  } 
  public UnidadLexica unidadNew() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NEW, "<new>");
	  } 
  public UnidadLexica unidadDelete() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DELETE, "<delete>");
	  }   
  public UnidadLexica unidadRead() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.READ, "<read>");
	  }   
  public UnidadLexica unidadWrite() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.WRITE, "<write>");
	  }   
  public UnidadLexica unidadNl() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NL, "<nl>");
	  }   
  public UnidadLexica unidadType() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.TYPE, "<type>");
	  }
  public UnidadLexica unidadCall() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CALL, "<call>");
	  }  
  public UnidadLexica unidadTrue() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.TRUE, "<true>");
	  }   
  public UnidadLexica unidadFalse() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.FALSE, "<false>");
	  } 
  public UnidadLexica unidadAnd() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.AND, "<and>");
	  }   
  public UnidadLexica unidadNot() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NOT, "<not>");
	  }  
  public UnidadLexica unidadOr() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.OR, "<or>");
	  }   
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.EOF, "<EOF>");
  }
  public void error()  {
	  System.out.println("ERROR_LEXICO");
  }

}
