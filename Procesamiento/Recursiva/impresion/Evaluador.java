package impresion;

import asint.SintaxisAbstractaTiny;

public class Evaluador extends SintaxisAbstractaTiny {

    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        imprime(opnd);
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }

    private void imprime(Prog prog){
        imprime(prog.bq());
        System.out.println("<EOF>");
    }
    private void imprime(Bloque bloque){
        System.out.println("{");
        imprime(bloque.lds());
        imprime(bloque.lis());
        System.out.println("}");
    }

    private void imprime(Si_decs sidecs){
        imprime(sidecs.decs());
        System.out.println("&&");
    }

    private void imprime(No_decs nodecs){
        //Vacío
    }

    private void imprime(Muchas_decs muchas){
        imprime(muchas.ldecs());
        System.out.println(";");
        imprime(muchas.dec());
    }

    private void imprime(Una_dec una){
        imprime(una.dec());
    }

    private void imprime(Dec_var var){
        imprime(var.tipo());
        System.out.println(var.iden());
    }

    private void imprime(Dec_tipo tipo){
        System.out.println("<type>");
        imprime(tipo.tipo());
        System.out.println(tipo.iden());
    }

    private void imprime(Dec_proc proc){
        System.out.println("<proc>");
        System.out.println(proc.iden());
        System.out.println("(");
        imprime(proc.pf());
        System.out.println(")");
        imprime(proc.bq());
    }

    private void imprime(Si_pforms sipform){
        imprime(sipform.pforms());
    }

    private void imprime(No_pforms nopform){
        //Vacía
    }

    private void imprime(Muchos_pforms muchos){
        imprime(muchos.pforms());
        System.out.println(",");
        imprime(muchos.pform());
    }

    private void imprime(Un_pform uno){
        imprime(uno.pform());
    }

    private void imprime(pfo)

imprime(pform(T, string)):
	imprime(T)
	print string

imprime(array(T, string)):
	imprime(T)
	print “[” + string + “]”


imprime(puntero(T)):
	imprime(T)
	print ”^”

imprime(iden(string)):
	print string

imprime(struct(LCamp)):
	print <struct>
	print “ {“
	imprime(LCamp)
	print “}”


imprime(lit_ent(string)):
	print <int>

imprime(lit_real(string)):
	print <real>

imprime(lit_bool(string)):
	print<bool>

imprime(lit_string(string)):
	print<string>

imprime(muchos_camp(LCamp,  Camp)):
	imprime(LCamp)
	print “,”
	imprime(Camp)

imprime(un_camp(Camp)):
	imprime(Camp)

imprime(camp(T, string)):
	imprime(T)
	print string

imprime(muchas_ins(LIns, Ins)):
	imprime(LIns)
	print “;”
	imprime(Ins)

imprime(una_ins(Ins)):
	imprime(Ins)

imprime(ins_asig(Exp)):
	print “@”
	imprime(Exp)

imprime(ins_if(Exp, Bloq)):
	print “<if>”
	imprime(Exp)
	imprime(Bloq)

imprime(ins_if_else(Exp, Bloq, Bloq)):
	print “<if>”
	imprime(Exp)
	imprime(Bloq)
	print “<else>”
	imprime(Bloq)

imprime(ins_while(Exp, Bloq)):
	print “<while>”
	imprime(Exp)
	imprime(Bloq)

imprime(ins_read(Exp)):
	print “<read>”
	imprime(Exp)

imprime(ins_write(Exp)):
	print “<write>”
	imprime(Exp)

imprime(ins_nl()):
	print “<nl>”

imprime(ins_new(Exp)):
	print “<new>”
	imprime(Exp)

imprime(ins_delete(Exp)):
	print “<delete>”
	imprime(Exp)

imprime(ins_call(string, PRealOpt)):
	print “<call>”
	print string
	print “(“
	imprime(PRealOpt)
	print “)”

imprime(ins_bloque(Bloq)):
	imprime(Bloq)

imprime(si_preal(LPReal)):
	imprime(LPreal)

imprime(no_preal()):
	skip

imprime(muchos_preal(LPReal, Exp)):
	imprime(LPReal)
	print “,”
	imprime(Exp)

imprime(un_preal(Exp)):
	imprime(Exp)

imprime(asig(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “=”
	imprimeOpnd(Exp1, 0)

imprime(mayor(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “>”
	imprimeOpnd(Exp1, 2)


imprime(menor(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “<”
	imprimeOpnd(Exp1, 2)

imprime(mayorIgual(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “>=”
	imprimeOpnd(Exp1, 2)

imprime(menorIgual(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “<=”
	imprimeOpnd(Exp1, 2)

imprime(igual(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “==”
	imprimeOpnd(Exp1, 2)

imprime(desigual(Exp0, Exp1)):
	imprimeOpnd(Exp0, 1)
	print “!=”
	imprimeOpnd(Exp1, 2)

imprime(suma(Exp0, Exp1)):
	imprimeOpnd(Exp0, 2)
	print “+” 
	imprimeOpnd(Exp1, 3)

imprime(resta(Exp0, Exp1)):
	imprimeOpnd(Exp0, 3)
	print “-” 
	imprimeOpnd(Exp1, 3)

imprime(and(Exp0, Exp1)):
	imprimeOpnd(Exp0, 4)
	print “<and>” 
	imprimeOpnd(Exp1, 3)

imprime(or(Exp0, Exp1)):
	imprimeOpnd(Exp0, 4)
	print “<or>” 
	imprimeOpnd(Exp1, 4)

imprime(mul(Exp0, Exp1)):
	imprimeOpnd(Exp0, 4)
	print “*” 
	imprimeOpnd(Exp1, 5)

imprime(div(Exp0, Exp1)):
	imprimeOpnd(Exp0, 4)
	print “/” 
	imprimeOpnd(Exp1, 5)

imprime(mod(Exp0, Exp1)):
	imprimeOpnd(Exp0, 4)
	print “%” 
	imprimeOpnd(Exp1, 5)

imprime(neg(Exp)):
	print “-” 
	imprimeOpnd(Exp, 5)
	

imprime(not(Exp)):
	print “<not>” 
	imprimeOpnd(Exp, 5)

imprime(acceso_array(Exp0, Exp1)):
	imprimeOpnd(Exp0, 6)
	print “ [ ” 
	imprime(Exp1)
	print “ ] ”

imprime(acceso_campo(Exp, id)):
	imprimeOpnd(Exp, 6)
	print “. ” 
	imprime(iden(id))

imprime(acceso_puntero(Exp)):
	imprimeOpnd(Exp, 6)
	print “^ ” 

imprime(exp_litEntero(N)):
	print N

imprime(exp_litReal(R)):
	print R

imprime(exp_litCadena(Id)):
	print Id

imprime(exp_Identificador(Id)):
	print Id

imprime(exp_litBoolTrue()):
	print “<true>”

imprime(exp_litBoolFalse()):
	print “<false>”

imprime(exp_null()):
	print “<null>”

    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }    
}

/*public class ECteNoDefinida extends RuntimeException {
        public ECteNoDefinida(String msg) {
            super(msg);
        }
    }
    public class ECteDuplicada extends RuntimeException {
        public ECteDuplicada(String msg) {
            super(msg);
        }
    }
    private Map<String,Float> env;
    public Evaluador() {
        this.env = new HashMap<>();
    }
    public float evalua(Prog n) {
        consEnv(n.decs());
        return eval(n.exp());        
    }
    private void consEnv(Decs decs) {
        if(claseDe(decs,Si_decs.class)) {
           consEnv(decs.ldecs());        
        }
    }
    private void consEnv(LDecs decs) {
        if(claseDe(decs,Muchas_decs.class)) {
            consEnv(decs.ldecs());
        }
        consEnv(decs.dec());
    }
    private void consEnv(Dec dec) {
        if(env.containsKey(dec.iden())) {
            throw new ECteDuplicada("Cte duplicada: "+dec.iden()+
                                     " fila:"+dec.leeFila()+" col:"+dec.leeCol()); 
        }
        else {
            env.put(dec.iden(),eval(dec.exp()));
        }
    }
    private float eval(Exp exp) {
        if(claseDe(exp,Lit_ent.class) || claseDe(exp,Lit_real.class)) {
            return Float.valueOf(exp.valor()).floatValue();
        }
        else if(claseDe(exp,Iden.class)) {
            if(! env.containsKey(exp.iden())) {
                throw new ECteNoDefinida("Cte indefinida: "+exp.iden()+
                                        " fila:"+exp.leeFila()+" col:"+exp.leeCol()); 
            }
            else {
                return env.get(exp.iden());
            }
        }
        else {
            float v1 = eval(exp.opnd0());
            float v2 = eval(exp.opnd1());
            if(claseDe(exp,Suma.class)) {
                return v1+v2;
            }
            else if(claseDe(exp,Resta.class)) {
                return v1-v2;
            }
            else if(claseDe(exp,Mul.class)) {
                return v1*v2;
            }
            else {
                return v1/v2;
            }
        }
    } 
    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }     */
