package alex;

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
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.IDENTIFICADOR,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
	 return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LITENTERO,alex.lexema()); 
  } 
  public UnidadLexica unidadLitReal() {
	 return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LITREAL,alex.lexema()); 
  } 
  public UnidadLexica unidadCadena() {
	 return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LITCADENA,alex.lexema()); 
  } 
  public UnidadLexica unidadSuma() {
	 return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SUMA); 
  } 
  public UnidadLexica unidadResta() {
	 return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.RESTA); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MUL); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DIV); 
  } 
  public UnidadLexica unidadMod() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MOD); 
  }
  public UnidadLexica unidadMayor() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYOR); 
  }
  public UnidadLexica unidadMenor() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOR); 
  }
  public UnidadLexica unidadMayorIgual() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYORIGUAL); 
  }
  public UnidadLexica unidadMenorIgual() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENORIGUAL); 
  }
  public UnidadLexica unidadIgual() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUAL); 
  }  
  public UnidadLexica unidadDesigual() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DESIGUAL); 
  }
  public UnidadLexica unidadAsig() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ASIG); 
  }  
  public UnidadLexica unidadParA() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PARA); 
  } 
  public UnidadLexica unidadParC() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PARC); 
  }   
  public UnidadLexica unidadCorcheteA() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CORCHETEA); 
  }   
  public UnidadLexica unidadCorcheteC() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CORCHETEC); 
  }   
  public UnidadLexica unidadPuntoYComa() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTOYCOMA); 
  } 
  public UnidadLexica unidadPunto() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTO); 
  }   
  public UnidadLexica unidadLlaveA() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLAVEA); 
  }   
  public UnidadLexica unidadLlaveC() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLAVEC); 
  }    
  public UnidadLexica unidadArroba() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ARROBA); 
  }  
  public UnidadLexica unidadAmpersand() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AMPERSAND); 
  } 
  public UnidadLexica unidadAmpersand2() {
	  return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AMPERSAND2); 
  } 
  public UnidadLexica unidadInt() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.INT); 
	  }  
  public UnidadLexica unidadReal() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REAL); 
	  }  
  public UnidadLexica unidadBool() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.BOOL); 
	  } 
  public UnidadLexica unidadString() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRING); 
	  }  
  public UnidadLexica unidadNull() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NULL); 
	  } 
  public UnidadLexica unidadProc() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PROC); 
	  } 
  public UnidadLexica unidadIf() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IF); 
	  } 
  public UnidadLexica unidadElse() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ELSE); 
	  }   
  public UnidadLexica unidadWhile() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WHILE); 
	  }   
  public UnidadLexica unidadStruct() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRUCT); 
	  } 
  public UnidadLexica unidadNew() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NEW); 
	  } 
  public UnidadLexica unidadDelete() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DELETE); 
	  }   
  public UnidadLexica unidadRead() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ); 
	  }   
  public UnidadLexica unidadWrite() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE); 
	  }   
  public UnidadLexica unidadNl() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL); 
	  }   
  public UnidadLexica unidadType() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TYPE); 
	  }
  public UnidadLexica unidadCall() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL); 
	  }  
  public UnidadLexica unidadTrue() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE); 
	  }   
  public UnidadLexica unidadFalse() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE); 
	  } 
  public UnidadLexica unidadAnd() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AND); 
	  }   
  public UnidadLexica unidadNot() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NOT); 
	  }  
  public UnidadLexica unidadOr() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OR); 
	  }   
  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
  }
  public void error()  {
      throw new ECaracterInesperado("***"+alex.fila()+","+alex.columna()+": Caracter inexperado: "+alex.lexema());
  }

}
