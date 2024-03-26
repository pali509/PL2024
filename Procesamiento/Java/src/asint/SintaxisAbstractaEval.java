package asint;

import java.util.HashMap;
import java.util.Map;



public class SintaxisAbstractaEval {
          
    public static abstract class Nodo  {
       public Nodo() {
		   fila=col=-1;
       }   
	   private int fila;
	   private int col;
	   public Nodo ponFila(int fila) {
		    this.fila = fila;
            return this;			
	   }
	   public Nodo ponCol(int col) {
		    this.col = col;
            return this;			
	   }
	   public int leeFila() {
		  return fila; 
	   }
	   public int leeCol() {
		  return col; 
	   }
           public abstract void procesa(Procesamiento p);
    }
    

    public static abstract class Exp  extends  Nodo {
       public Exp() {
          super();
       }   
       public abstract int prioridad();

       //De recursivo:
       public String iden() {throw new UnsupportedOperationException();}
        public String valor() {throw new UnsupportedOperationException();}

       public Exp opnd0() {throw new UnsupportedOperationException();}
       public Exp opnd1() {throw new UnsupportedOperationException();}

    }
   
    
    private static abstract class ExpBin2 extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        public Exp opnd0() {return opnd0;}
        public Exp opnd1() {return opnd1;}
        public ExpBin2(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
    }

    //Me la he inventado para tener una que solo tuviera 1 opnd
    private static abstract class ExpBin1 extends Exp {
        protected Exp opnd;
        public Exp opnd() {return opnd;}
        public ExpBin1(Exp opnd) {
            super();
            this.opnd = opnd;
        }
    }
            
    public static class Suma extends ExpBin2 {
        public Suma(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public int prioridad() {return 2;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "suma("+opnd0+","+opnd1+")";
        }
    }
    public static class Resta extends ExpBin2 {
        public Resta(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
       public int prioridad() {return 2;}
       public String toString() {
            return "resta("+opnd0+","+opnd1+")";
        } 
    }
    public static class Mul extends ExpBin2 {
        public Mul(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 4;}
        public String toString() {
            return "mul("+opnd0+","+opnd1+")";
        } 
    }
    public static class Div extends ExpBin2 {
        public Div(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 4;}
        public String toString() {
            return "div("+opnd0+","+opnd1+")";
        } 
    }

    //NUEVOS!!

    public static class Mod extends ExpBin2 {
        public Mod(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 4;}
        public String toString() {
            return "asig("+opnd0+","+opnd1+")";
        }
    }
    public static class Asig extends ExpBin2 {
        public Asig(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 0;}
        public String toString() {
            return "asig("+opnd0+","+opnd1+")";
        }
    }
    public static class Mayor extends ExpBin2 {
        public Mayor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}
        public String toString() {
            return "mayor("+opnd0+","+opnd1+")";
        }
    }
    public static class Menor extends ExpBin2 {
        public Menor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}
        public String toString() {
            return "menor("+opnd0+","+opnd1+")";
        }
    }
    public static class MayorIg extends ExpBin2 {
        public MayorIg(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}
        //Alomejor no es asi el toString, no lo se
        public String toString() {
            return "mayorIgual("+opnd0+","+opnd1+")";
        }
    }
    public static class MenorIg extends ExpBin2 {
        public MenorIg(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}
        //Lo mismo que mayorIgual
        public String toString() {
            return "menorIgual("+opnd0+","+opnd1+")";
        }
    }
    public static class Igual extends ExpBin2 {
        public Igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}
        public String toString() {
            return "igual("+opnd0+","+opnd1+")";
        }
    }
    public static class Desigual extends ExpBin2 {
        public Desigual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}
        public String toString() {
            return "desigual("+opnd0+","+opnd1+")";
        }
    }
    public static class Neg extends ExpBin1 {
        public Neg(Exp opnd) {
            super(opnd);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 5;}
        public String toString() {
            return "negacion("+opnd+")";
        }
    }
    public static class Not extends ExpBin1 {
        public Not(Exp opnd) {
            super(opnd);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 5;}
        public String toString() {
            return "not("+opnd+")";
        }
    }
    public static class Lit_ent extends Exp {
        private String num;
        public Lit_ent(String num) {
            super();
            this.num = num;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 2;}
        public String num() {return num;}
        public String toString() {
            return "lit_ent("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }

    public static class Lit_real extends Exp {
        private String num;
        public Lit_real(String num) {
            super();
            this.num = num;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String num() {return num;}
        public int prioridad() {return 2;}
        public String toString() {
            return "lit_real("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }
	
	
    public static class Iden extends Exp {
        private String id;
        public Iden(String id) {
            super();
            this.id = id;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 2;}
        public String iden() {return id;}
        public String toString() {
            return "iden("+id+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    public static class Dec extends Nodo {
        private String id;
        private Exp exp;
        public Dec(String id, Exp exp) {
            this.id = id;
            this.exp = exp;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String iden() {return id;}
        public Exp exp() {return exp;}
        public String toString() {
            return "dec("+id+"["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    public static abstract class Decs extends Nodo {
       public Decs() {
       }
       public LDecs ldecs() {throw new UnsupportedOperationException();}

    }
    public static class Si_decs extends Decs {
       private LDecs decs; 
       public Si_decs(LDecs decs) {
          super();
          this.decs = decs;
       }   
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LDecs decs() {return decs;}
        public String toString() {
            return "si_decs("+decs+")";
        } 
 
    }
    public static class No_decs extends Decs {
       public No_decs() {
          super();
       }   
       public String toString() {
            return "no_decs()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static abstract class LDecs extends Nodo {
       public LDecs() {
		   super();
       }
    }
    	
    public static class Muchas_decs extends LDecs {
       private LDecs decs;
       private Dec dec;
       public Muchas_decs(LDecs decs, Dec dec) {
          super();
          this.dec = dec;
          this.decs = decs;
       }
       public Dec dec() {return dec;}
       public LDecs ldecs() {return decs;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
       public String toString() {
            return "muchas_decs("+decs+","+dec+")";
        } 
    }

    public static class Una_dec extends LDecs {
       private Dec dec;
       public Una_dec(Dec dec) {
          super();
          this.dec = dec;
       }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
       public Dec dec() {return dec;}
       public String toString() {
            return "una_dec("+dec+")";
        } 
    }

    public static class Prog extends Nodo {
	   private Exp exp;
	   private Decs decs;
       public Prog(Exp exp, Decs decs) {
		   super();
		   this.exp = exp;
		   this.decs = decs;
       }   
        public Decs decs() {return decs;}
        public Exp exp() {return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
       public String toString() {
            return "prog("+exp+","+decs+")";
        } 
    }

     // Constructoras    
    public Prog prog(Exp exp, Decs decs) {
        return new Prog(exp,decs);
    }
    public Exp suma(Exp opnd0, Exp opnd1) {
        return new Suma(opnd0,opnd1);
    }
    public Exp resta(Exp opnd0, Exp opnd1) {
        return new Resta(opnd0,opnd1);
    }
    public Exp mul(Exp opnd0, Exp opnd1) {
        return new Mul(opnd0,opnd1);
    }
    public Exp div(Exp opnd0, Exp opnd1) {
        return new Div(opnd0,opnd1);
    }

    //NUEVOS!!
    public Exp mod(Exp opnd0, Exp opnd1) {
        return new Mod(opnd0,opnd1);
    }
    public Exp asig(Exp opnd0, Exp opnd1) {
        return new Asig(opnd0,opnd1);
    }
    public Exp mayor(Exp opnd0, Exp opnd1) {
        return new Mayor(opnd0,opnd1);
    }
    public Exp menor(Exp opnd0, Exp opnd1) {
        return new Menor(opnd0,opnd1);
    }
    public Exp mayorIg(Exp opnd0, Exp opnd1) {
        return new MayorIg(opnd0,opnd1);
    }
    public Exp menorIg(Exp opnd0, Exp opnd1) {
        return new MenorIg(opnd0,opnd1);
    }
    public Exp igual(Exp opnd0, Exp opnd1) {
        return new Igual(opnd0,opnd1);
    }
    public Exp desigual(Exp opnd0, Exp opnd1) {
        return new Desigual(opnd0,opnd1);
    }
    public Exp neg(Exp opnd) {
        return new Neg(opnd);
    }
    public Exp not(Exp opnd) {
        return new Not(opnd);
    }


    //estos ya no son nuevos (lo he puesto asi para que vayan todos juntitos)
    public Exp lit_ent(String num) {
        return new Lit_ent(num);
    }
    public Exp lit_real(String num) {
        return new Lit_real(num);
    }
    public Exp iden(String num) {
        return new Iden(num);
    }
    public Dec dec(String id, Exp exp) {
        return new Dec(id,exp);
    }
    public Decs si_decs(LDecs decs) {
        return new Si_decs(decs);
    }
    public Decs no_decs() {
        return new No_decs();
    }
    public LDecs muchas_decs(LDecs decs, Dec dec) {
        return new Muchas_decs(decs,dec);
    }
    public LDecs una_dec(Dec dec) {
        return new Una_dec(dec);
    }
}

/*Faltan:
-bloq
-Dividir dec en dec_var, dec_tipo y dec_proc
-Si_pform
-No_pform
-Muchas_pforms
-Una_pform
-pform
-array
-puntero
-struct
-lit_bool
-lit_string
-Muchos_camps
-Un_camp
-camp
-Muchas_ins
-Una_ins
-ins_asig
-ins_if
-ins_if_else
-ins_while
-ins_read
-ins_write
-ins_nl
-ins_new
-ins_delete
-ins_call
-ins_preal
-Si_preal
-No_preal
-Muchas_preal
-Una_preal
 */