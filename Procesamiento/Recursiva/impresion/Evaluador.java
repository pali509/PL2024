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

    private void imprime(And and){
        imprimeOpnd(and.opnd0(), 4);
        System.out.println("<and>");
        imprimeOpnd(and.opnd1(), 3);
    }

    private void imprime(Or or){
        imprimeOpnd(or.opnd0(), 4);
        System.out.println("<or>");
        imprimeOpnd(or.opnd1(), 4);
    }

    private void imprime(Mul mul){
        imprimeOpnd(mul.opnd0(), 4);
        System.out.println("*");
        imprimeOpnd(mul.opnd1(), 5);
    }

    private void imprime(Div div){
        imprimeOpnd(div.opnd0(), 4);
        System.out.println("/");
        imprimeOpnd(div.opnd1(), 5);
    }

    private void imprime(Mod mod){
        imprimeOpnd(mod.opnd0(), 4);
        System.out.println("%");
        imprimeOpnd(mod.opnd1(), 5);
    }

    private void imprime(Neg neg){
        System.out.println("-");
        imprimeOpnd(neg.opnd(), 5);
    }

    private void imprime(Not not){
        System.out.println("<not>");
        imprimeOpnd(not.opnd(), 5);
    }

    private void imprime(ACCESOARRAY){
        imprimeOpnd(Exp0, 6)
        print “ [ ” 
        imprime(Exp1)
        print “ ] ”
    }

    private void imprime(ACCESOCAMPO){
        imprimeOpnd(Exp, 6)
        print “. ” 
        imprime(iden(id))
    }

    private void imprime(ACCESOPUNTERO){
        imprimeOpnd(Exp, 6)
	    print “^ ”
    }

    private void imprime(Exp_lit_ent e){
        System.out.println("N");
    }

    private void imprime(Exp_lit_real e){
        System.out.println("R");
    }

    private void imprime(Exp_lit_cadena e){
        System.out.println(e.iden());
    }

    private void imprime(Exp_Iden e){
        System.out.println(e.iden());
    }

    private void imprime(Exp_lit_BoolTrue e){
        System.out.println("<true>");
    }

    private void imprime(Exp_lit_BoolFalse e){
        System.out.println("<false>");
    }

    private void imprime(Exp_null e){
        System.out.println("<null>");
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
