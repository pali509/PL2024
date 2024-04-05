package asint;


public class SintaxisAbstractaTiny {
          
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

    public static abstract class Tipo {
        public Tipo() {}

        public abstract void procesa(Procesamiento p);

    }

    public static class Int extends Tipo {

        public Int() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "Int";
        }
    }
    public static class Real extends Tipo {

        public Real() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "Real";
        }
    }
    public static class Bool extends Tipo {

        public Bool() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "Bool";
        }
    }

    public static class Iden extends Tipo {
        private String id;
        public Iden(String id) {
            super();
            this.id = id;
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public String iden() {return id;}
        public String toString() {
            return "iden("+id+")";
        }
    }
    public static class Array extends Tipo {
        private String id;
        private Tipo t;
        public Array(String id, Tipo t) {
            super();
            this.t = t;
            this.id = id;
        }

        public String iden() {return id;}

        public Tipo tipo() {return t;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public String toString() {
            return "Array(tipo "+t.toString()+" string " +id+")";
        }
    }
    public static class Puntero extends Tipo {

        private Tipo t;
        public Puntero(Tipo t) {
            super();
            this.t = t;

        }

        public Tipo tipo() {return t;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public String toString() {
            return "Puntero(tipo "+t.toString()+")";
        }
    }
    public static class Struct extends Tipo {

        private LCamp lc;
        public Struct(LCamp lc) {
            super();
            this.lc = lc;

        }
        public LCamp lcamp() {return lc;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public String toString() {
            return "Struct(listaCampos "+lc.toString()+")";
        }
    }

    public static class Lit_ent extends Exp {
        public Lit_ent() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 7;}
        public String toString() {
            return "lit_ent";
        } 
    }

    public static class Lit_real extends Exp {
        public Lit_real() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public String toString() {
            return "lit_real";
        } 
    }
    public static class Lit_BoolTrue extends Exp {
        public Lit_BoolTrue() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public String toString() {
            return "lit_bool_true";
        }
    }
    public static class Exp_null extends Exp {
        public Exp_null() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public String toString() {
            return "exp_null";
        }
    }
    public static class Lit_BoolFalse extends Exp {
        public Lit_BoolFalse() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public String toString() {
            return "lit_bool_false";
        }
    }

    public static class Lit_string extends Exp {

        public Lit_string() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 7;}

        public String toString() {
            return "Literal_string";
        }
    }
    public static class Lit_cadena extends Exp {
        private String num;
        public Lit_cadena(String num) {
            super();
            this.num = num;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String num(){return num;}
        public int prioridad() {return 7;}
        public String toString() {
            return "lit_cadena("+num+")";
        }
    }
    public static class Exp_Iden extends Exp {

        private String num;
        public Exp_Iden(String num) {
            super();
            this.num = num;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public String num(){return num;}
        public int prioridad() {return 7;}
        public String toString() {
            return "Identificador("+num+")";
        }
    }

    public static abstract class Pform extends Nodo {
//AQUI FALTA UN PARAMETRO TIPO CREO 
        public Pform() {}
        public abstract void procesa(Procesamiento p);

        public String toString() {
            return "Parametro Formal(["+leeFila()+","+leeCol()+"])";
        }
    }

    public static abstract class LPforms extends Pform{
        public LPforms() {}
        public abstract void procesa(Procesamiento p);
    }
    public static class Si_pforms extends LPforms {
        private LPforms pforms;
        public Si_pforms(LPforms pforms) {
            super();
            this.pforms = pforms;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LPforms pforms() {return pforms;}
        public String toString() {
            return "si_pforms("+pforms.toString()+")";
        }

    }
    public static class No_pforms extends LPforms {
        public No_pforms() {
            super();
        }
        public String toString() {
            return "no_pforms()";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Un_pform extends LPforms {
        private Pform pform;
        public Un_pform(Pform pform) {
            super();
            this.pform = pform;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Pform pform() {return pform;}
        public String toString() {
            return "una_dec("+pform.toString()+")";
        }
    }

    public static class Muchos_pforms extends LPforms {
        private LPforms pforms;
        private Pform pform;
        public Muchos_pforms(LPforms pforms, Pform pform) {
            super();
            this.pform = pform;
            this.pforms = pforms;
        }
        public Pform pform() {return pform;}
        public LPforms pforms() {return pforms;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "muchas_decs("+pforms.toString()+","+pform.toString()+")";
        }
    }



    public static abstract class Dec extends Nodo {

        public Dec() {}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    public static class Dec_var extends Dec {
        private String id;
        private Tipo t;
        public Dec_var(String id, Tipo t) {
            this.id = id;
            this.t = t;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String iden() {return id;}
        public Tipo tipo() {return t;}
        public String toString() {
            return "dec_var("+id+"["+leeFila()+","+leeCol()+"],"+t.toString()+")";
        }
    }
    public static class Dec_tipo extends Dec {
        private String id;
        private Tipo t;

        public Dec_tipo(String id, Tipo t) {
            this.id = id;
            this.t = t;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String iden() {return id;}
        public Tipo tipo() {return t;}
        public String toString() {
            return "dec_tipo("+id+"["+leeFila()+","+leeCol()+"],"+t.toString()+")";
        }
    }
    public static class Dec_proc extends Dec {
        private String id;
        private Pform pf;
        private Bloque bq;

        public Dec_proc(String id, Pform pf, Bloque bq) {
            this.id = id;
            this.pf = pf;
            this.bq = bq;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String iden() {return id;}
        public Pform pf() {return pf;}
        public Bloque bq() {return bq;}
        public String toString() {
            return "dec_proc("+id+"["+leeFila()+","+leeCol()+"],"+pf.toString()+ ", "+pf.toString()+ ")";
        }
    }

    public static abstract class LDecs{
        public LDecs() {
            super();
        }
    }

    public static class Si_decs extends LDecs {
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
    public static class No_decs extends LDecs {
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



    public static class Prog extends Nodo { //Falta lo de eof que npi
	   private Bloque bq;

       public Prog(Bloque bq) {
		   super();
		   this.bq = bq;
       }   
        public Bloque bq() {return bq;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
       public String toString() {
            return "prog("+bq.toString()+")";
        } 
    }
    public static class Bloque {
        private LDecs lds;
        private LIns lis;
        public Bloque(LDecs lds, LIns lis) {
            this.lds = lds;
            this.lis = lis;
        }

        public LDecs lds() {return lds;}
        public LIns lis() {return lis;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "bloque("+lds.toString()+ ", " +lis.toString()+ ")";
        }
    }
    public static  class Camp {
        private Tipo t;
        private String id;
        public Camp(Tipo t, String id) {
            this.t = t;
            this.id = id;
        }
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        public String iden() {return id;}
        public Tipo tipo() {return t;}
        public String toString() {
            return "Campo("+t.toString()+ ", "+id+")";
        }
    }

    public static abstract class LCamp {
        public LCamp() {}
        public abstract void procesa(Procesamiento p);
    }

    public static class Un_camp extends LCamp{
        private Camp campo;

        public Un_camp(Camp campo) {
            this.campo = campo;
        }

        public Camp campo() {return campo;}

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "un_camp("+campo.toString()+")";
        }
    }

    public static class Muchos_camp extends LCamp {
        private LCamp lcs;
        private Camp campo;

        public Muchos_camp(LCamp lcs, Camp campo) {
            this.lcs = lcs;
            this.campo = campo;
        }

        public Camp campo() {
            return campo;
        }
        public LCamp lcs() {
            return lcs;
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "muchos_campos("+lcs.toString()+","+campo.toString()+")";
        }
    }
    public static abstract class Ins {
        public Ins() {}
        public abstract void procesa(Procesamiento p);
    }

    public static class Ins_asig extends Ins {
        private Exp e;
        public Ins_asig(Exp e) {
            super();
            this.e = e;
        }
        public Exp e() {return e;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_asig("+e.toString()+")";
        }
    }

    public static class Ins_if extends Ins {
        private Exp e;
        private Bloque bq;
        public Ins_if(Exp e, Bloque bq) {
            super();
            this.e = e;
            this.bq = bq;
        }
        public Exp e() {return e;}
        public Bloque bloque(){return bq;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_if("+e.toString()+ ", " +bq.toString()+ ")";
        }
    }

    public static class Ins_if_else extends Ins {
        private Exp e;
        private Bloque bq1;
        private Bloque bq2;
        public Ins_if_else(Exp e, Bloque bq1, Bloque bq2) {
            super();
            this.e = e;
            this.bq1 = bq1;
            this.bq2 = bq2;
        }
        public Exp e() {return e;}
        public Bloque bloque1(){return bq1;}
        public Bloque bloque2(){return bq2;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_if_else("+e.toString()+ ", " +bq1.toString()+", " +bq2.toString()+ ")";
        }
    }

    public static class Ins_while extends Ins {
        private Exp e;
        private Bloque bq;
        public Ins_while(Exp e, Bloque bq) {
            super();
            this.e = e;
            this.bq = bq;
        }
        public Exp e() {return e;}
        public Bloque bloque(){return bq;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_while("+e.toString()+ ", " +bq.toString()+ ")";
        }
    }

    public static class Ins_read extends Ins {
        private Exp e;
        public Ins_read(Exp e) {
            super();
            this.e = e;
        }
        public Exp e() {return e;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_read("+e.toString()+")";
        }
    }

    public static class Ins_write extends Ins {
        private Exp e;
        public Ins_write(Exp e) {
            super();
            this.e = e;
        }
        public Exp e() {return e;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_write("+e.toString()+")";
        }
    }

    public static class Ins_new extends Ins {
        private Exp e;
        public Ins_new(Exp e) {
            super();
            this.e = e;
        }
        public Exp e() {return e;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_new("+e.toString()+")";
        }
    }

    public static class Ins_delete extends Ins {
        private Exp e;
        public Ins_delete(Exp e) {
            super();
            this.e = e;
        }
        public Exp e() {return e;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_delete("+e.toString()+")";
        }
    }
    public static class Ins_nl extends Ins {
        public Ins_nl() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_nl";
        }
    }

    public static class Ins_call extends Ins {
        private String st;
        private LPReal pr;
        public Ins_call(String st, LPReal pr) {
            super();
            this.st = st;
            this.pr = pr;
        }
        public String string() {return st;}
        public LPReal pr() {return pr;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_call("+st+", "+pr.toString()+")";
        }
    }

    public static class Ins_bloque extends Ins {
        private Bloque bq;
        public Ins_bloque(Bloque bq) {
            super();
            this.bq = bq;
        }
        public Bloque bloque(){return bq;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "ins_bloque(" +bq.toString()+ ")";
        }
    }

    public static abstract class LIns {
        public LIns() {}
        public abstract void procesa(Procesamiento p);
    }

    public static class Una_ins extends LIns{
        private Ins ins;

        public Una_ins(Ins ins) {
            this.ins = ins;
        }

        public Ins ins() {return ins;}

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "una_ins("+ins.toString()+")";
        }
    }

    public static class Muchas_ins extends LIns {
        private LIns li;
        private Ins ins;

        public Muchas_ins(LIns li, Ins ins) {
            this.li = li;
            this.ins = ins;
        }

        public Ins ins() {return ins;}
        public LIns li() {return li;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "muchas_ins("+li.toString()+","+ins.toString()+")";
        }
    }

    public static abstract class LPReal{
        public abstract void procesa(Procesamiento p);
    }

    public static class Si_preal extends LPReal {
        private LPReal lpr;
        public Si_preal(LPReal lpr) {
            super();
            this.lpr = lpr;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LPReal lpr() {return lpr;}
        public String toString() {
            return "si_pforms("+lpr+")";
        }

    }
    public static class No_preal extends LPReal {
        public No_preal() {
            super();
        }
        public String toString() {
            return "no_preal()";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    public static class Un_PReal extends LPReal{
        private Exp e;

        public Un_PReal(Exp e) {
            this.e = e;
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Exp e() {return e;}
        public String toString() {
            return "una_ins("+e.toString()+")";
        }
    }

    public static class Muchos_preal extends LPReal {
        private LPReal lpr;
        private Exp e;

        public Muchos_preal(LPReal lpr, Exp e) {
            this.lpr = lpr;
            this.e = e;
        }

        public LPReal lpr() {return lpr;}
        public Exp e() {return e;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "muchas_ins("+lpr+","+e.toString()+")";
        }
    }

    // Constructoras
    public Prog prog(Bloque bq) {
        return new Prog(bq);
    }
    public Bloque bloque(LDecs ld, LIns li){
        return new Bloque(ld,li);
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

    public Exp lit_boolTrue() {
        return new Lit_BoolTrue();
    }
    public Exp lit_boolFalse() {
        return new Lit_BoolFalse();
    }

    public Exp exp_null() {
        return new Exp_null();
    }

    public Exp lit_string() {
        return new Lit_string();
    }

    public Exp exp_iden(String id) {
        return new Exp_Iden(id);
    }

    public Tipo iden(String id) {
        return new Iden(id);
    }
    public Tipo array(Tipo t, String st) {
        return new Array(st, t);
    }
    public Tipo puntero(Tipo t) {
        return new Puntero(t);
    }
    public Tipo struct(LCamp lc) {
        return new Struct(lc);
    }

    public Camp camp(Tipo t, String st) {
        return new Camp(t, st);
    }

    public LCamp un_camp(Camp c) {
        return new Un_camp(c);
    }
    public LCamp muchos_camp(LCamp lc, Camp c) {
        return new Muchos_camp(lc,c);
    }
    public LPforms si_pforms(LPforms lpf){
        return new Si_pforms(lpf);
    }
    public LPforms no_pforms(){
        return new No_pforms();
    }
    public LPforms un_pform(Pform pf){
        return new Un_pform(pf);
    }
    public LPforms muchos_pforms(LPforms lpf, Pform pf){
        return new Muchos_pforms(lpf, pf);
    }

    public LIns una_ins(Ins ins){
        return new Una_ins(ins);
    }
    public LIns muchas_ins(LIns li, Ins ins){
        return new Muchas_ins(li, ins);
    }

    public Ins ins_asig(Exp e){
        return new Ins_asig(e);
    }
    public Ins ins_if(Exp e, Bloque bq){
        return new Ins_if(e, bq);
    }
    public Ins ins_if_else(Exp e, Bloque bq1, Bloque bq2){
        return new Ins_if_else(e, bq1, bq2);
    }
    public Ins ins_while(Exp e, Bloque bq){
        return new Ins_while(e, bq);
    }
    public Ins ins_read(Exp e){
        return new Ins_read(e);
    }
    public Ins ins_write(Exp e){
        return new Ins_write(e);
    }
    public Ins ins_nl(){
        return new Ins_nl();
    }
    public Ins ins_new(Exp e){
        return new Ins_new(e);
    }
    public Ins ins_delete(Exp e){
        return new Ins_delete(e);
    }
    public Ins ins_call(String st, LPReal lpr){
        return new Ins_call(st, lpr);
    }


    public LPReal si_preal(LPReal lpr){
        return new Si_preal(lpr);
    }
    public LPReal no_preal(){
        return new No_preal();
    }
    public LPReal un_preal(Exp e){
        return new Un_PReal(e);
    }
    public LPReal muchos_preal(LPReal lpr, Exp e){
        return new Muchos_preal(lpr, e);
    }

    //estos ya no son nuevos (lo he puesto asi para que vayan todos juntitos)
    public Exp lit_ent() {
        return new Lit_ent();
    }
    public Exp lit_real() {
        return new Lit_real();
    }
    public Exp Exp_Iden(String num) {
        return new Exp_Iden(num);
    }

    public LDecs si_decs(LDecs decs) {
        return new Si_decs(decs);
    }
    public LDecs no_decs() {
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

-pform -> HACER CON AND Y NO AND?????

 */