package asint;


public class SintaxisAbstractaTiny {
    
    public static abstract class Nodo  {
       public Nodo() {
		   fila=col=-1;
       }

       private Tipo tipo;
	   private int fila;
	   private int col;
       private int prim;
       private int sig;
        private int dir;
        private int nivel;
	   public Nodo ponFila(int fila) {
		    this.fila = fila;
            return this;			
	   }
	   public Nodo ponCol(int col) {
		    this.col = col;
            return this;			
	   }

        public void setPrim(int etq) {
            this.prim = etq;
        }
        public void setSig(int etq) {
            this.sig = etq;
        }
        public int prim(){
            return this.prim;
        }
        public int sig(){
           return this.sig;
        }
	   public int leeFila() {
		  return fila; 
	   }
	   public int leeCol() {
		  return col; 
	   }
        public Tipo tipo(){return tipo;}
        public void set_tipo(Tipo tipo) {this.tipo = tipo;}
        public abstract void procesa(Procesamiento p);
        public Boolean es_dec_var(){return false;}
        public Boolean es_parf_noRef(){return false;}
        public Boolean es_parf_ref(){return false;}
        public void set_dir(int dir) {
            this.dir = dir;
        }
        public void set_nivel(int n) {
            this.nivel = n;
        }
        public int get_dir() {return this.dir;}
        public int get_nivel() {return this.nivel;}
    }

    public static class StringLocalizado {
        private String image;
        private int beginLine;
        private int beginColumn;
        public StringLocalizado(String s, int fila, int col) {
            this.image = s;
            this.beginLine = fila;
            this.beginColumn = col;
        }
        public int beginLine() {return beginLine;}
        public int beginColumn() {return beginColumn;}
        public String image() {
            return image;
        }
        public boolean equals(Object o) {
            return (o == this) || (
                (o instanceof StringLocalizado) &&
                (((StringLocalizado)o).image.equals(image)));                
        }
        public int hashCode() {
            return image.hashCode();
        }
    }

    public static abstract class Exp  extends  Nodo {
        public Exp() {
            super();
        }   
        public abstract int prioridad();
       //De recursivo:
        public StringLocalizado iden() {throw new UnsupportedOperationException();}
        public StringLocalizado valor() {throw new UnsupportedOperationException();}

        public Exp opnd0() {throw new UnsupportedOperationException();}
        public Exp opnd1() {throw new UnsupportedOperationException();}

        public abstract Tipo tipo();
        public void set_tipo(Tipo t){};
        public Boolean es_iden(){return false;}
        public Boolean es_acceso_array(){return false;}
        public Boolean es_acceso_puntero(){return false;}
        public Boolean es_acceso_campo(){return false;}

    }
   
    
    public static abstract class ExpBin extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        private Tipo tipo;
        public Exp opnd0() {return opnd0;}
        public Exp opnd1() {return opnd1;}
        public ExpBin (Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
        public Tipo tipo(){return tipo;}
        public void set_tipo(Tipo tipo) {this.tipo = tipo;}
    }

    //Me la he inventado para tener una que solo tuviera 1 opnd
    public static abstract class ExpUn extends Exp {
        protected Exp opnd;
        private Tipo tipo;
        public Exp opnd0() {return opnd;}
        public ExpUn(Exp opnd) {
            super();
            this.opnd = opnd;
        }
        public Tipo tipo(){return tipo;}
        public void set_tipo(Tipo tipo) {this.tipo = tipo;}
    }
            
    public static class Suma extends ExpBin {
        public Suma(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public int prioridad() {return 2;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

    }
    public static class Resta extends ExpBin {
        public Resta(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
       public int prioridad() {return 2;}

    }
    public static class Mul extends ExpBin {
        public Mul(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 4;}
    }
    public static class Div extends ExpBin {
        public Div(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 4;}

    }

    //NUEVOS!!

    public static class Mod extends ExpBin {
        public Mod(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 4;}

    }
    public static class Asig extends ExpBin {
        public Asig(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 0;}

    }
    public static class Mayor extends ExpBin {
        public Mayor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}

    }
    public static class Menor extends ExpBin {
        public Menor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}

    }
    public static class MayorIg extends ExpBin {
        public MayorIg(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}

    }
    public static class MenorIg extends ExpBin {
        public MenorIg(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}

    }
    public static class Igual extends ExpBin {
        public Igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}

    }
    public static class Desigual extends ExpBin {
        public Desigual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 1;}


    }
    public static class Neg extends ExpUn {
        public Neg(Exp opnd) {
            super(opnd);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 5;}

    }
    public static class Not extends ExpUn {
        public Not(Exp opnd) {
            super(opnd);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 5;}

    }

    public static abstract class Tipo extends Nodo{
        public Tipo() {}
    	private int tam;

        public abstract void procesa(Procesamiento p);

        public StringLocalizado iden() {throw new UnsupportedOperationException();}
        public int num() {throw new UnsupportedOperationException();}
        public Tipo tipo() {throw new UnsupportedOperationException();}
        public LCamp lcamp() {throw new UnsupportedOperationException();}

        public Boolean es_iden() { return false;}

        public Boolean es_int() {return false;}

        public Boolean es_real() {return false;}

        public Boolean es_bool() {return false;}

        public Boolean es_string() {return false;}

        public Boolean es_array() {return false;}

        public Boolean es_struct() {return false;}

        public Boolean es_puntero() {return false;}
        public Boolean t_ok() {return false;}

        public Boolean es_null() {return false;}

		public int getTam() {
			return this.tam;
		}
		
		public void setTam(int n) {
			this.tam = n;
		}
		
    }
    public static class Ok extends Tipo{
        public Ok() {
            super();
        }
        public Boolean t_ok() {return true;}
        public void procesa(Procesamiento p) {}
    }

    public static class Error_ extends Tipo{
        public Error_() {
            super();
        }
        public Boolean t_ok() {return false;}
        public void procesa(Procesamiento p) {}
    }

    public static class Null_T extends Tipo{
        public Null_T() {
            super();
        }
        public Boolean es_null() {return true;}

        public void procesa(Procesamiento p) {}
        public Boolean equals(Tipo t) {
            return t.es_null();
        }
    }

    public static class Lit_ent extends Tipo {

        public Lit_ent() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_int() {return true;}

    }
    public static class Lit_real extends Tipo {

        public Lit_real() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_real() {return true;}

    }
    public static class Lit_bool extends Tipo {

        public Lit_bool() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_bool() {return true;}

    }
    public static class Lit_string extends Tipo {

        public Lit_string() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_string() {return true;}

    }
    public static class Iden extends Tipo {
        private StringLocalizado id;
        private Nodo vinculo;
        public Iden(StringLocalizado id) {
            super();
            this.id = id;
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public StringLocalizado str() {return id;}

        @Override
        public Boolean es_iden() {
            return true;
        }
        public void setVinculo(Nodo v) {
        	this.vinculo = v;
        }
        public Nodo getVinculo() {
        	return this.vinculo;
        }
        
    }
    public static class Array extends Tipo {
        private int num;
        private Tipo t;
        public Array(int image, Tipo t) {
            super();
            this.t = t;
            this.num = image;
        }

        public int num() {return num;}

        public Tipo tipo() {return t;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_array() {return true;}
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

        @Override
        public Boolean es_puntero() {
            return true;
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
        public Boolean es_struct() {return true;}

    }

    public static class Exp_lit_ent extends Exp {
        private StringLocalizado lex;
        public Exp_lit_ent(StringLocalizado lex) {
            super();
            this.lex = lex;
        }
        public StringLocalizado valor(){return lex;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 7;}
        public Tipo tipo(){return new Lit_ent();}
    }

    public static class Exp_lit_real extends Exp {
        private StringLocalizado lex;
        public Exp_lit_real(StringLocalizado lex) {
            super();
            this.lex = lex;
        }
        public StringLocalizado valor(){return lex;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public Tipo tipo(){return new Lit_real();}
    }
    public static class Exp_lit_BoolTrue extends Exp {
        public Exp_lit_BoolTrue() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public Tipo tipo(){return new Lit_bool();}
    }
    public static class Exp_null extends Exp {
        public Exp_null() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public Tipo tipo(){return new Null_T();}

    }
    public static class Exp_lit_BoolFalse extends Exp {
        public Exp_lit_BoolFalse() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public int prioridad() {return 7;}
        public Tipo tipo(){return new Lit_bool();}
    }

    public static class Exp_lit_cadena extends Exp {
        private StringLocalizado num;
        public Exp_lit_cadena(StringLocalizado num) {
            super();
            this.num = num;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public StringLocalizado valor(){return num;}
        public int prioridad() {return 7;}
        public Tipo tipo(){return new Lit_string();}
    }
    public static class Exp_Iden extends Exp {
        private Tipo tipo;
        private StringLocalizado num;
        private Nodo vinculo;
        public Exp_Iden(StringLocalizado num) {
            super();
            this.num = num;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

        public StringLocalizado valor(){return num;}
        public int prioridad() {return 7;}
        public Tipo tipo(){return tipo;}
        public void set_tipo(Tipo tipo) {this.tipo = tipo;}
        public void setVinculo(Nodo v) {this.vinculo = v;}
        public Nodo getVinculo() {return this.vinculo;}

        @Override
        public Boolean es_iden() {
            return true;
        }
    }

    public static class AccesoArray extends Exp {
        private Tipo tipo;
        private Exp exp1;
        private Exp exp2;
        public AccesoArray(Exp exp1, Exp exp2) {
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Exp opnd0(){return exp1;}
        public Exp opnd1(){return exp2;}

        public Tipo tipo() {
            return tipo;
        }

        public int prioridad() {return 6;}

        public void set_tipo(Tipo tipo) {this.tipo = tipo;}
        public Boolean es_acceso_array(){return true;}

    }
    public static class AccesoCampo extends Exp {
        private Tipo tipo;
        private StringLocalizado id;
        private Exp exp;
        public AccesoCampo(StringLocalizado id, Exp exp) {
            super();
            this.exp = exp;
            this.id = id;
        }

        public StringLocalizado iden(){return id;}
        public Exp opnd0(){return exp;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 6;}
        public Tipo tipo() {
            return tipo;
        }
        public void set_tipo(Tipo tipo) {this.tipo = tipo;}

        public Boolean es_acceso_campo(){return true;}
    }

    public static class AccesoPuntero extends Exp {
        private Tipo tipo;
        private Exp exp;
        public AccesoPuntero(Exp exp) {
            super();
            this.exp = exp;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Exp opnd0(){return exp;}
        public int prioridad() {return 6;}

        public Tipo tipo() {
            return tipo;
        }
        public void set_tipo(Tipo tipo) {this.tipo = tipo;}

        public Boolean es_acceso_puntero(){return true;}


    }

    public static class And extends ExpBin {
        private Exp exp1;
        private Exp exp2;
        public And(Exp exp1, Exp exp2) {
            super(exp1,exp2);
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Exp opnd0(){return exp1;}
        public Exp opnd1(){return exp2;}
        public int prioridad() {return 3;}

    }

    public static class Or extends ExpBin {
        public Or(Exp exp1, Exp exp2) {
            super(exp1,exp2);
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public int prioridad() {return 3;}
    }

    public static abstract class Pform extends Nodo {
        private Tipo t;
        private StringLocalizado id;
        public Pform(Tipo t, StringLocalizado id) {
            this.t = t;
            this.id = id;
        }

        public StringLocalizado id(){return id;}
        public Tipo t(){return t;}
        public abstract void procesa(Procesamiento p);

    }
    public static class PFref extends Pform {
        private Tipo t;
        private StringLocalizado id;
        public PFref(Tipo t, StringLocalizado id) {
            super(t,id);
            this.t = t;
            this.id = id;
        }
        public StringLocalizado id(){return id;}
        public Tipo t(){return t;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_parf_ref(){return true;}
    }

    public static class PFnoref extends Pform {
        private Tipo t;
        private StringLocalizado id;
        public PFnoref(Tipo t, StringLocalizado id) {
            super(t,id);
            this.t = t;
            this.id = id;
        }
        public StringLocalizado id(){return id;}
        public Tipo t(){return t;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_parf_noRef(){return true;}
    }

    public static abstract class PFormOpt extends Nodo{
        public PFormOpt() {}
        public abstract void procesa(Procesamiento p);

        public LPForm pforms() {throw new UnsupportedOperationException();}
    }
    public static class Si_pforms extends PFormOpt {
        private LPForm pforms;
        public Si_pforms(LPForm pforms) {
            super();
            this.pforms = pforms;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LPForm pforms() {return pforms;}

    }
    public static class No_pforms extends PFormOpt {
        public No_pforms() {
            super();
        }

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static abstract class LPForm extends Nodo{
        public LPForm() {}

        public abstract void procesa(Procesamiento p);

        public Pform pform() {throw new UnsupportedOperationException();}
        public LPForm pforms() {throw new UnsupportedOperationException();}
    }

    public static class Un_pform extends LPForm {
        private Pform pform;
        public Un_pform(Pform pform) {
            super();
            this.pform = pform;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Pform pform() {return pform;}
    }

    public static class Muchos_pforms extends LPForm {
        private LPForm pforms;
        private Pform pform;
        public Muchos_pforms(LPForm pforms, Pform pform) {
            super();
            this.pform = pform;
            this.pforms = pforms;
        }
        public Pform pform() {return pform;}
        public LPForm pforms() {return pforms;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }



    public static abstract class Dec extends Nodo {

        public Dec() {}
        public abstract void procesa(Procesamiento p);

        public StringLocalizado iden() {throw new UnsupportedOperationException();}
        public Tipo tipo() {throw new UnsupportedOperationException();}
        public PFormOpt pf() {throw new UnsupportedOperationException();}
        public Bloque bq() {throw new UnsupportedOperationException();}
        public Boolean es_dec_proc(){return false;}
    }
    public static class Dec_var extends Dec {
        private StringLocalizado id;
        private Tipo t;
        public Dec_var(StringLocalizado identificador, Tipo t) {
            this.id = identificador;
            this.t = t;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public StringLocalizado iden() {return id;}
        public Tipo tipo() {return t;}

        public Boolean es_dec_var(){return true;}

    }
    public static class Dec_tipo extends Dec {
        private StringLocalizado id;
        private Tipo t;

        public Dec_tipo(StringLocalizado id, Tipo t) {
            this.id = id;
            this.t = t;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public StringLocalizado iden() {return id;}
        public Tipo tipo() {return t;}

    }
    public static class Dec_proc extends Dec {
        private StringLocalizado id;
        private PFormOpt pf;
        private Bloque bq;
        private int tam;

        public Dec_proc(StringLocalizado id, PFormOpt pf, Bloque bq) {
            this.id = id;
            this.pf = pf;
            this.bq = bq;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public StringLocalizado iden() {return id;}
        public PFormOpt pf() {return pf;}
        public Bloque bq() {return bq;}
		public void setTam(int n) {
			this.tam = n;
		}
		public int getTam() {
			return this.tam;
		}
        public Boolean es_dec_proc(){return true;}
    }

    public static abstract class LDecs extends Nodo{
        public LDecs() {
            super();
        }
        public LDecs ldecs() {throw new UnsupportedOperationException();}
        public Dec dec() {throw new UnsupportedOperationException();}
    }
    public static abstract class LDecsOpt extends Nodo{
        public LDecsOpt() {
            super();
        }
        public LDecs decs() {throw new UnsupportedOperationException();}
        public Boolean es_si_decs(){return false;}
        public Boolean es_no_decs(){return false;}
        public Boolean es_una_dec(){return false;}
        public Boolean es_muchas_decs(){return false;}
    }

    public static class Si_decs extends LDecsOpt {
       private LDecs decs; 
       public Si_decs(LDecs decs) {
          super();
          this.decs = decs;
       }   
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LDecs decs() {return decs;}
        public Boolean es_si_decs(){return true;}
    }
    public static class No_decs extends LDecsOpt {
       public No_decs() {
          super();
       }   

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public Boolean es_no_decs(){return true;}
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
        public Boolean es_una_dec(){return true;}
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
        public Boolean es_muchas_decs(){return true;}
    }



    public static class Prog extends Nodo {
	   private Bloque bq;

       public Prog(Bloque bq) {
		   super();
		   this.bq = bq;
       }   
        public Bloque bq() {return bq;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

    }
    public static class Bloque extends Nodo{
        private LDecsOpt lds;
        private LInsOpt lis;
        public Bloque(LDecsOpt lds, LInsOpt lis) {
            this.lds = lds;
            this.lis = lis;
        }

        public LDecsOpt lds() {return lds;}
        public LInsOpt lis() {return lis;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    public static  class Camp extends Nodo{
        private Tipo t;
        private StringLocalizado id;
        private int desplazamiento;
        public Camp(Tipo t, StringLocalizado id) {
            this.t = t;
            this.id = id;
        }
        public void procesa(Procesamiento p){
            p.procesa(this);
        }
        public StringLocalizado iden() {return id;}
        public Tipo tipo() {return t;}
        public void set_desp(int i) {
            this.desplazamiento = i;
        }
        public int get_desp() {
            return this.desplazamiento;
        }

    }

    public static abstract class LCamp extends Nodo{
        public LCamp() {}
        public abstract void procesa(Procesamiento p);

        public Camp campo() {throw new UnsupportedOperationException();}
        public LCamp lcs() {throw new UnsupportedOperationException();}

        public Boolean es_un_campo() { return false;}
        public Boolean es_muchos_campos() { return false;}
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
        public Boolean es_un_campo() { return true;}

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
        public Boolean es_muchos_campos() { return true;}

    }
    public static abstract class Ins extends Nodo{
        public Ins() {}
        public abstract void procesa(Procesamiento p);

        public Exp e() {throw new UnsupportedOperationException();}
        public Bloque bloque() {throw new UnsupportedOperationException();}
        public Bloque bloque2() {throw new UnsupportedOperationException();}
        public LPRealOpt pr() {throw new UnsupportedOperationException();}
        public StringLocalizado id() {throw new UnsupportedOperationException();}
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
        public Bloque bloque(){return bq1;}
        public Bloque bloque2(){return bq2;}
        public void procesa(Procesamiento p) {
            p.procesa(this);
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

    }
    
    public static class Ins_nl extends Ins {
        public Ins_nl() {
            super();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }

    }

    public static class Ins_call extends Ins {
        private StringLocalizado id;
        private LPRealOpt pr;
        private Nodo vinculo;
        public Ins_call(StringLocalizado id, LPRealOpt pr) {
            super();
            this.id = id;
            this.pr = pr;
        }
        public StringLocalizado id() {return id;}
        public LPRealOpt pr() {return pr;}
        public void procesa(Procesamiento p) {p.procesa(this);}
        public void setVinculo(Nodo v) {this.vinculo = v;}
        public Nodo getVinculo() {return this.vinculo;}
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

    }

    public static abstract class LIns extends Nodo{
        public LIns() {}
        public abstract void procesa(Procesamiento p);

        public Ins ins() {throw new UnsupportedOperationException();}
        public LIns li() {throw new UnsupportedOperationException();}
    }

    public static abstract class LInsOpt extends Nodo{
        public LInsOpt() {}
        public abstract void procesa(Procesamiento p);
        public LIns ins() {throw new UnsupportedOperationException();}
    }

    public static class Si_Ins extends LInsOpt{
        private LIns ins;
        public Si_Ins(LIns ins){
            super();
            this.ins = ins;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LIns ins() {return ins;}

    }

    public static class No_Ins extends LInsOpt{
        public No_Ins() {
            super();
        }   

        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
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

    }

    public static abstract class LPReal extends Nodo{
        public LPReal() {}
        public abstract void procesa(Procesamiento p);
        public Exp e() {throw new UnsupportedOperationException();}
        public LPReal lpr() {throw new UnsupportedOperationException();}
    }

    public static abstract class LPRealOpt extends Nodo{
        public LPRealOpt() {}
        public abstract void procesa(Procesamiento p);
        public LPReal lpr() {throw new UnsupportedOperationException();}
    }

    public static class Si_preal extends LPRealOpt {
        private LPReal lpr;
        public Si_preal(LPReal lpr) {
            super();
            this.lpr = lpr;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public LPReal lpr() {return lpr;}


    }
    
    public static class No_preal extends LPRealOpt {
        public No_preal() {
            super();
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

    }

    // Constructoras
    public Prog prog(Bloque bq) {
        return new Prog(bq);
    }
    public Bloque bloque(LDecsOpt ld, LInsOpt li){
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

    public Exp exp_lit_BoolTrue() {
        return new Exp_lit_BoolTrue();
    }
    public Exp exp_lit_BoolFalse() {
        return new Exp_lit_BoolFalse();
    }

    public Exp exp_null() {
        return new Exp_null();
    }

    public Tipo lit_string() {
        return new Lit_string();
    }

    public Exp exp_iden(StringLocalizado id) {
        return new Exp_Iden(id);
    }

    public Exp exp_lit_cadena(StringLocalizado id) {
        return new Exp_lit_cadena(id);
    }

    public Exp accesoArray(Exp exp1, Exp exp2) {
        return new AccesoArray(exp1,exp2);
    }
    public Exp accesoCampo(StringLocalizado id, Exp exp) {
        return new AccesoCampo(id, exp);
    }
    public Exp accesoPuntero(Exp exp) {
        return new AccesoPuntero(exp);
    }

    public And and(Exp exp1, Exp exp2) {
        return new And(exp1,exp2);
    }
    public Or or(Exp exp1, Exp exp2) {
        return new Or(exp1,exp2);
    }

    public Tipo iden(StringLocalizado id) {
        return new Iden(id);
    }
    public Tipo lit_ent() {
        return new Lit_ent();
    }
    public Tipo lit_real() {
        return new Lit_real();
    }
    public Tipo lit_bool() {
        return new Lit_bool();
    }
    public Tipo array(Tipo t, int image) {
        return new Array(image,t);
    }
    public Tipo puntero(Tipo t) {
        return new Puntero(t);
    }
    public Tipo struct(LCamp lc) {
        return new Struct(lc);
    }

    public Camp camp(Tipo t, StringLocalizado id) {
        return new Camp(t, id);
    }

    public LCamp un_camp(Camp c) {
        return new Un_camp(c);
    }
    public LCamp muchos_camp(LCamp lc, Camp c) {
        return new Muchos_camp(lc,c);
    }
	//yo pondria mismo tipo a PFref y PFnoref
    public PFref pfref(Tipo t, StringLocalizado id) {
        return new PFref(t, id);
    }
    public PFnoref pfnoref(Tipo t, StringLocalizado id) {
        return new PFnoref(t, id);
    }
    public PFormOpt si_pforms(LPForm lpf){
        return new Si_pforms(lpf);
    }
    public PFormOpt no_pforms(){
        return new No_pforms();
    }
    public LPForm un_pform(Pform pf){
        return new Un_pform(pf);
    }
    public LPForm muchos_pforms(LPForm lpf, Pform pf){
        return new Muchos_pforms(lpf, pf);
    }

    public LInsOpt si_ins(LIns ins){
        return new Si_Ins(ins);
    }
    public LInsOpt no_ins(){
        return new No_Ins();
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
    public Ins ins_call(StringLocalizado id, LPRealOpt params_opt){
        return new Ins_call(id, params_opt);
    }
    public Ins ins_bloque(Bloque b){
        return new Ins_bloque(b);
    }


    public LPRealOpt si_preal(LPReal lpr){
        return new Si_preal(lpr);
    }
    public LPRealOpt no_preal(){
        return new No_preal();
    }
    public LPReal un_preal(Exp e){
        return new Un_PReal(e);
    }
    public LPReal muchos_preal(LPReal lpr, Exp e){
        return new Muchos_preal(lpr, e);
    }

    //estos ya no son nuevos (lo he puesto asi para que vayan todos juntitos)
    public Exp exp_lit_ent(StringLocalizado st) {
        return new Exp_lit_ent(st);
    }
    public Exp exp_lit_real(StringLocalizado st) {
        return new Exp_lit_real(st);
    }
    public Exp exp_Iden(StringLocalizado num) {
        return new Exp_Iden(num);
    }

    public LDecsOpt si_decs(LDecs decs) {
        return new Si_decs(decs);
    }
    public LDecsOpt no_decs() {
        return new No_decs();
    }
    public LDecs muchas_decs(LDecs decs, Dec dec) {
        return new Muchas_decs(decs,dec);
    }
    public LDecs una_dec(Dec dec) {
        return new Una_dec(dec);
    }

    public Dec dec_var(StringLocalizado identificador, Tipo t) {
    	return new Dec_var(identificador, t);
    }
    
    public Dec dec_tipo(StringLocalizado identificador, Tipo t) {
    	return new Dec_tipo(identificador, t);
    }
    
    public Dec dec_proc(StringLocalizado identificador, PFormOpt pformOpt, Bloque bloq) {
    	return new Dec_proc(identificador, pformOpt, bloq);
    }


}
