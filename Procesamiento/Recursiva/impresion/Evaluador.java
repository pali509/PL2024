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

    private void imprime(Pform pform){
        imprime(//tipo);
        System.out.println(//string);
    }

    private void imprime(Array array){
        imprime(array.tipo());
        System.out.println("[" + array.iden() + "]");
    }

    private void imprime(Puntero puntero){
        imprime(puntero.tipo());
        System.out.println("^");
    }
    
    private void imprime(Iden iden){
        System.out.println(iden.iden());
    }

    private void imprime(Struct struct){
        System.out.println("<struct>");
        System.out.println("{");
        imprime(struct.lcamp());
        System.out.println("}");
    }

    private void imprime(Lit_ent ent){
        System.out.println("<int>");
    }

    private void imprime(Lit_real real){
        System.out.println("<real>");
    }

    private void imprime(Lit_Bool bool){
        System.out.println("<bool>");
    }

    private void imprime(Lit_string string){
        System.out.println("<string>");
    }

    private void imprime(Muchos_camp muchos){
        imprime(muchos.lcs());
        System.out.println(",");
        imprime(muchos.campo());
    }

    private void imprime(Un_camp uno){
        imprime(uno.campo());
    }

    private void imprime(Camp camp){
        imprime(camp.tipo());
        System.out.println(camp.iden());
    }

    private void imprime(Muchas_ins muchas){
        imprime(muchas.li());
        System.out.println(";");
        imprime(muchas.ins());
    }

    private void imprime(Una_ins una){
        imprime(una.ins());
    }

    private void imprime(Ins_asig asig){
        System.out.println("@");
        imprime(asig.e())
    }

    private void imprime(Ins_if iif){
        System.out.println("<if>");
        imprime(iif.e());
        imprime(iif.bloque());
    }

    private void imprime(Ins_if_else ifelse){
        System.out.println("<if>");
        imprime(ifelse.e());
        imprime(ifelse.bloque1());
        System.out.println("<else>");
        imprime(ifelse.bloque2());
    }

    private void imprime(Ins_while iwhile){
        System.out.println("<while>");
        imprime(iwhile.e());
        imprime(iwhile.bloque());
    }

    private void imprime(Ins_read read){
        System.out.println("<read>");
        imprime(read.e());
    }

    private void imprime(Ins_write write){
        System.out.println("<write>");
        imprime(write.e());
    }

    private void imprime(Ins_nl nl){
        System.out.println("<nl>");
    }

    private void imprime(Ins_new inew){
        System.out.println("<new>");
        imprime(inew.e());
    }

    private void imprime(Ins_delete delete){
        System.out.println("<delete>");
        imprime(delete.e());
    }

    private void imprime(Ins_call call){
        System.out.println("<call>");
        System.out.println(call.string());
        System.out.println("(");
        imprime(call.pr());
        System.out.println(")");
    }

    private void imprime(Ins_bloque bloque){
        imprime(bloque.bloque());
    }

    private void imprime(Si_preal preal){
        imprime(preal.lpr());
    }

    private void imprime(No_preal no){
        //Vacia
    }

    private void imprime(Muchos_preal muchos){
        imprime(muchos.lpr());
        System.out.println(",");
        imprime(muchos.e());
    }

    private void imprime(Un_PReal uno){
        imprime(uno.e());
    }

    private void imprime(Asig asig){
        imprimeOpnd(asig.opnd0(), 1);
        System.out.println("=");
        imprimeOpnd(asig.opnd1(), 0);
    }

    private void imprime(Mayor mayor){
        imprimeOpnd(mayor.opnd0(), 1);
        System.out.println(">");
        imprimeOpnd(mayor.opnd1(), 2);
    }

    private void imprime(Menor menor){
        imprimeOpnd(menor.opnd0(), 1);
        System.out.println("<");
        imprimeOpnd(menor.opnd1(), 2);
    }

    private void imprime(MayorIg m){
        imprimeOpnd(m.opnd0(), 1);
        System.out.println(">=");
        imprimeOpnd(m.opnd1(), 2);
    }

    private void imprime(MenorIg m){
        imprimeOpnd(m.opnd0(), 1);
        System.out.println("<=");
        imprimeOpnd(m.opnd1(), 2);
    }

    private void imprime(Igual igual){
        imprimeOpnd(igual.opnd0(), 1);
        System.out.println("==");
        imprimeOpnd(igual.opnd1(), 2);
    }

    private void imprime(Desigual desi){
        imprimeOpnd(desi.opnd0(), 1);
        System.out.println("!=");
        imprimeOpnd(desi.opnd1(), 2);
    }

    private void imprime(Suma suma){
        imprimeOpnd(suma.opnd0(), 2);
        System.out.println("+");
        imprimeOpnd(suma.opnd1(), 3);
    }

    private void imprime(Resta resta){
        imprimeOpnd(resta.opnd0(), 3);
        System.out.println("-");
        imprimeOpnd(resta.opnd1(), 3);
    }

    private void imprime(AND and){
        imprimeOpnd(and., 0);
    }

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
