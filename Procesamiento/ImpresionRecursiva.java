
import asint.SintaxisAbstractaTiny;


public class ImpresionRecursiva extends SintaxisAbstractaTiny {

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
        imprime((LDecsOpt)bloque.lds());
        imprime(bloque.lis());
        System.out.println("}");
    }

    private void imprime(LDecsOpt l){

    }

    private void imprime(LDecs l){
        if(claseDe(l, Si_decs.class)){
            imprime(si_decs(l));
        }
        else imprime(no_decs());
    }

    private void imprime(Si_decs sidecs){
        if(claseDe(sidecs.decs(), Muchas_decs.class)){
            imprime(muchas_decs(((Muchas_decs)sidecs).ldecs(), ((Muchas_decs)sidecs).dec()));
        }
        else imprime(una_dec(sidecs.decs()));
        System.out.println("&&");
    }

    private void imprime(No_decs nodecs){
        //Vacío
    }

    private void imprime(Muchas_decs muchas){
        if (claseDe(muchas.ldecs(), Muchas_decs.class)){
            imprime((Muchas_decs)muchas.ldecs());
        }
        else imprime((Una_dec)muchas.ldecs());
        System.out.println(";");
        if(claseDe(muchas.dec(), Dec_var.class)){
            imprime((Dec_var)muchas.dec());
        }
        else if(claseDe(muchas.dec(), Dec_tipo.class)){
            imprime((Dec_tipo)muchas.dec());
        }
        else imprime((Dec_proc)muchas.dec());
    }

    private void imprime(Una_dec una){
        if(claseDe(una.dec(), Dec_var.class)){
            imprime((Dec_var)una.dec());
        }
        else if(claseDe(una.dec(), Dec_tipo.class)){
            imprime((Dec_tipo)una.dec());
        }
        else imprime((Dec_proc)una.dec());
    }

    private void imprime(Dec_var var){
        if(claseDe(var.tipo(), Array.class)){
            imprime((Array)var.tipo());
        }
        else if(claseDe(var.tipo(), Puntero.class)){
            imprime((Puntero)var.tipo());
        }
        else if(claseDe(var.tipo(), Iden.class)){
            imprime((Iden)var.tipo());
        }
        else if(claseDe(var.tipo(), Struct.class)){
            imprime((Struct)var.tipo());
        }
        else if(claseDe(var.tipo(), Lit_ent.class)){
            imprime((Lit_ent)var.tipo());
        }
        else if(claseDe(var.tipo(), Lit_real.class)){
            imprime((Lit_real)var.tipo());
        }
        else if(claseDe(var.tipo(), Lit_bool.class)){
            imprime((Lit_bool)var.tipo());
        }
        else imprime((Lit_string)var.tipo());
        System.out.println(var.iden());
    }

    private void imprime(Dec_tipo tipo){
        System.out.println("<type>");
        if(claseDe(tipo.tipo(), Array.class)){
            imprime((Array)tipo.tipo());
        }
        else if(claseDe(tipo.tipo(), Puntero.class)){
            imprime((Puntero)tipo.tipo());
        }
        else if(claseDe(tipo.tipo(), Iden.class)){
            imprime((Iden)tipo.tipo());
        }
        else if(claseDe(tipo.tipo(), Struct.class)){
            imprime((Struct)tipo.tipo());
        }
        else if(claseDe(tipo.tipo(), Lit_ent.class)){
            imprime((Lit_ent)tipo.tipo());
        }
        else if(claseDe(tipo.tipo(), Lit_real.class)){
            imprime((Lit_real)tipo.tipo());
        }
        else if(claseDe(tipo.tipo(), Lit_bool.class)){
            imprime((Lit_bool)tipo.tipo());
        }
        else imprime((Lit_string)tipo.tipo());
        System.out.println(tipo.iden());
    }

    private void imprime(Dec_proc proc){
        System.out.println("<proc>");
        System.out.println(proc.iden());
        System.out.println("(");
        if(claseDe(proc.pf(), Si_pforms.class)){
            imprime((Si_pforms)proc.pf());
        }
        else imprime((No_pforms)proc.pf());
        System.out.println(")");
        imprime(proc.bq());
    }

    private void imprime(Si_pforms sipform){
        if(claseDe(sipform.pforms(), Muchos_pforms.class)){
            imprime((Muchos_pforms)sipform.pforms());
        }
        else imprime((Un_pform)sipform.pforms());
    }

    private void imprime(No_pforms nopform){
        //Vacía
    }

    private void imprime(Muchos_pforms muchos){
        if(claseDe(muchos.pforms(), Muchos_pforms.class)){
            imprime((Muchos_pforms)muchos.pforms());
        }
        else imprime((Un_pform)muchos.pforms());
        System.out.println(",");
        if(claseDe(muchos.pform(), PFref.class)){
            imprime((PFref)muchos.pform());
        }
        else imprime((PFnoref)muchos.pform());
    }

    private void imprime(Un_pform uno){
        if(claseDe(uno.pform(), PFref.class)){
            imprime((PFref)uno.pform());
        }
        else imprime((PFnoref)uno.pform());
    }

    private void imprime(PFref pform){
        if(claseDe(pform.t(), Array.class)){
            imprime((Array)pform.t());
        }
        else if(claseDe(pform.t(), Puntero.class)){
            imprime((Puntero)pform.t());
        }
        else if(claseDe(pform.t(), Iden.class)){
            imprime((Iden)pform.t());
        }
        else if(claseDe(pform.t(), Struct.class)){
            imprime((Struct)pform.t());
        }
        else if(claseDe(pform.t(), Lit_ent.class)){
            imprime((Lit_ent)pform.t());
        }
        else if(claseDe(pform.t(), Lit_real.class)){
            imprime((Lit_real)pform.t());
        }
        else if(claseDe(pform.t(), Lit_bool.class)){
            imprime((Lit_bool)pform.t());
        }
        else imprime((Lit_string)pform.t());
        System.out.println("&");
        System.out.println(pform.st());
    }

    private void imprime(PFnoref pform){
        if(claseDe(pform.t(), Array.class)){
            imprime((Array)pform.t());
        }
        else if(claseDe(pform.t(), Puntero.class)){
            imprime((Puntero)pform.t());
        }
        else if(claseDe(pform.t(), Iden.class)){
            imprime((Iden)pform.t());
        }
        else if(claseDe(pform.t(), Struct.class)){
            imprime((Struct)pform.t());
        }
        else if(claseDe(pform.t(), Lit_ent.class)){
            imprime((Lit_ent)pform.t());
        }
        else if(claseDe(pform.t(), Lit_real.class)){
            imprime((Lit_real)pform.t());
        }
        else if(claseDe(pform.t(), Lit_bool.class)){
            imprime((Lit_bool)pform.t());
        }
        else imprime((Lit_string)pform.t());
        System.out.println(pform.st());
    }

    private void imprime(Array array){
        if(claseDe(array.tipo(), Array.class)){
            imprime((Array)array.tipo());
        }
        else if(claseDe(array.tipo(), Puntero.class)){
            imprime((Puntero)array.tipo());
        }
        else if(claseDe(array.tipo(), Iden.class)){
            imprime((Iden)array.tipo());
        }
        else if(claseDe(array.tipo(), Struct.class)){
            imprime((Struct)array.tipo());
        }
        else if(claseDe(array.tipo(), Lit_ent.class)){
            imprime((Lit_ent)array.tipo());
        }
        else if(claseDe(array.tipo(), Lit_real.class)){
            imprime((Lit_real)array.tipo());
        }
        else if(claseDe(array.tipo(), Lit_bool.class)){
            imprime((Lit_bool)array.tipo());
        }
        else imprime((Lit_string)array.tipo());
        System.out.println("[" + array.iden() + "]");
    }

    private void imprime(Puntero puntero){
        if(claseDe(puntero.tipo(), Array.class)){
            imprime((Array)puntero.tipo());
        }
        else if(claseDe(puntero.tipo(), Puntero.class)){
            imprime((Puntero)puntero.tipo());
        }
        else if(claseDe(puntero.tipo(), Iden.class)){
            imprime((Iden)puntero.tipo());
        }
        else if(claseDe(puntero.tipo(), Struct.class)){
            imprime((Struct)puntero.tipo());
        }
        else if(claseDe(puntero.tipo(), Lit_ent.class)){
            imprime((Lit_ent)puntero.tipo());
        }
        else if(claseDe(puntero.tipo(), Lit_real.class)){
            imprime((Lit_real)puntero.tipo());
        }
        else if(claseDe(puntero.tipo(), Lit_bool.class)){
            imprime((Lit_bool)puntero.tipo());
        }
        else imprime((Lit_string)puntero.tipo());
        System.out.println("^");
    }
    
    private void imprime(Iden iden){
        System.out.println(iden.iden());
    }

    private void imprime(Struct struct){
        System.out.println("<struct>");
        System.out.println("{");
        if(claseDe(struct.lcamp(), Muchos_camp.class)){
            imprime((Muchos_camp)struct.lcamp());
        }
        else imprime((Un_camp)struct.lcamp());
        System.out.println("}");
    }

    private void imprime(Lit_ent ent){
        System.out.println("<int>");
    }

    private void imprime(Lit_real real){
        System.out.println("<real>");
    }

    private void imprime(Lit_bool bool){
        System.out.println("<bool>");
    }

    private void imprime(Lit_string string){
        System.out.println("<string>");
    }

    private void imprime(Muchos_camp muchos){
        if(claseDe(muchos.lcs(), Muchos_camp.class)){
            imprime((Muchos_camp)muchos.lcs());
        }
        else imprime((Un_camp)muchos.lcs());
        System.out.println(",");
        imprime(muchos.campo());
    }

    private void imprime(Un_camp uno){
        imprime(uno.campo());
    }

    private void imprime(Camp camp){
        if(claseDe(camp.tipo(), Array.class)){
            imprime((Array)camp.tipo());
        }
        else if(claseDe(camp.tipo(), Puntero.class)){
            imprime((Puntero)camp.tipo());
        }
        else if(claseDe(camp.tipo(), Iden.class)){
            imprime((Iden)camp.tipo());
        }
        else if(claseDe(camp.tipo(), Struct.class)){
            imprime((Struct)camp.tipo());
        }
        else if(claseDe(camp.tipo(), Lit_ent.class)){
            imprime((Lit_ent)camp.tipo());
        }
        else if(claseDe(camp.tipo(), Lit_real.class)){
            imprime((Lit_real)camp.tipo());
        }
        else if(claseDe(camp.tipo(), Lit_bool.class)){
            imprime((Lit_bool)camp.tipo());
        }
        else imprime((Lit_string)camp.tipo());
        System.out.println(camp.iden());
    }

    private void imprime(LIns l){
        if(claseDe(l, Si_Ins.class)){
            imprime(si_ins(l));
        }
        else imprime(no_ins());
    }

    private void imprime(Si_Ins s){
        if(claseDe(s.ins(), Muchas_ins.class)){
            imprime((Muchas_ins)s.ins());
        }
        else imprime((Una_ins)s.ins());
    }

    private void imprime(No_Ins n){
        //Vacío
    }

    private void imprime(Muchas_ins muchas){
        if(claseDe(muchas.li(), Muchas_ins.class)){
            imprime((Muchas_ins)muchas.li());
        }
        else imprime((Una_ins)muchas.li());
        System.out.println(";");
        if(claseDe(muchas.ins(), Ins_asig.class)){
            imprime((Ins_asig)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_if.class)){
            imprime((Ins_if)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_if_else.class)){
            imprime((Ins_if_else)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_while.class)){
            imprime((Ins_while)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_read.class)){
            imprime((Ins_read)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_write.class)){
            imprime((Ins_write)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_nl.class)){
            imprime((Ins_nl)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_new.class)){
            imprime((Ins_new)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_delete.class)){
            imprime((Ins_delete)muchas.ins());
        }
        else if(claseDe(muchas.ins(), Ins_call.class)){
            imprime((Ins_call)muchas.ins());
        }
        imprime((Ins_bloque)muchas.ins());
    }

    private void imprime(Una_ins una){
        if(claseDe(una.ins(), Ins_asig.class)){
            imprime((Ins_asig)una.ins());
        }
        else if(claseDe(una.ins(), Ins_if.class)){
            imprime((Ins_if)una.ins());
        }
        else if(claseDe(una.ins(), Ins_if_else.class)){
            imprime((Ins_if_else)una.ins());
        }
        else if(claseDe(una.ins(), Ins_while.class)){
            imprime((Ins_while)una.ins());
        }
        else if(claseDe(una.ins(), Ins_read.class)){
            imprime((Ins_read)una.ins());
        }
        else if(claseDe(una.ins(), Ins_write.class)){
            imprime((Ins_write)una.ins());
        }
        else if(claseDe(una.ins(), Ins_nl.class)){
            imprime((Ins_nl)una.ins());
        }
        else if(claseDe(una.ins(), Ins_new.class)){
            imprime((Ins_new)una.ins());
        }
        else if(claseDe(una.ins(), Ins_delete.class)){
            imprime((Ins_delete)una.ins());
        }
        else if(claseDe(una.ins(), Ins_call.class)){
            imprime((Ins_call)una.ins());
        }
        imprime((Ins_bloque)una.ins());
    }

    private void imprime(Ins_asig asig){
        System.out.println("@");
        imprime(asig.e());
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
        if(claseDe(call.pr(), Si_preal.class)){
            imprime((Si_preal)call.pr());
        }
        else imprime((No_preal)call.pr());
        System.out.println(")");
    }

    private void imprime(Ins_bloque bloque){
        imprime(bloque.bloque());
    }

    private void imprime(Si_preal preal){
        if(claseDe(preal.lpr(), Muchos_preal.class)){
            imprime((Muchos_preal)preal.lpr());
        }
        else imprime((Un_PReal)preal.lpr());
    }

    private void imprime(No_preal no){
        //Vacia
    }

    private void imprime(Muchos_preal muchos){
        if(claseDe(muchos.lpr(), Muchos_preal.class)){
            imprime((Muchos_preal)muchos.lpr());
        }
        else imprime((Un_PReal)muchos.lpr());
        System.out.println(",");
        imprime(muchos.e());
    }

    private void imprime(Un_PReal uno){
        imprime(uno.e());
    }

    private void imprimeExpBin(Exp o0, Exp o1, String s, int p0, int p1){
        imprimeOpnd(o0, p0);
        System.out.println(s);
        imprimeOpnd(o1, p1);
    }

    private void imprime(Exp exp){
        if(claseDe(exp, Asig.class)){
            imprime(asig(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Mayor.class)){
            imprime(mayor(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Menor.class)){
            imprime(menor(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, MayorIg.class)){
            imprime(mayorIg(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, MenorIg.class)){
            imprime(menorIg(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Igual.class)){
            imprime(igual(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Desigual.class)){
            imprime(desigual(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Suma.class)){
            imprime(suma(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Resta.class)){
            imprime(resta(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, And.class)){
            imprime(and(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Or.class)){
            imprime(or(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Mul.class)){
            imprime(mul(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Div.class)){
            imprime(div(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Mod.class)){
            imprime(mod(((ExpBin)exp).opnd0(), ((ExpBin)exp).opnd1()));
        }
        else if(claseDe(exp, Neg.class)){
            imprime(neg(((ExpUn)exp).opnd()));
        }
        else if(claseDe(exp, Not.class)){
            imprime(not(((ExpUn)exp).opnd()));
        }
        else if(claseDe(exp, AccesoArray.class)){
            imprime(accesoArray(((AccesoArray)exp).exp1(), ((AccesoArray)exp).exp2()));
        }
        else if(claseDe(exp, AccesoCampo.class)){
            imprime(accesoCampo(((AccesoCampo)exp).num(), ((AccesoCampo)exp).exp()));
        }
        else if(claseDe(exp, AccesoPuntero.class)){
            imprime(accesoPuntero(((AccesoPuntero)exp).exp()));
        }
        else if(claseDe(exp, Exp_lit_ent.class)){
            imprime(exp_lit_ent());
        }
        else if(claseDe(exp, Exp_lit_real.class)){
            imprime(exp_lit_real());
        }
        else if(claseDe(exp, Exp_lit_BoolTrue.class)){
            imprime(exp_lit_BoolTrue());
        }
        else if(claseDe(exp, Exp_lit_BoolFalse.class)){
            imprime(exp_lit_BoolFalse());
        }
        else if(claseDe(exp, Exp_lit_cadena.class)){
            imprime(exp_lit_cadena(((Exp_lit_cadena)exp).num()));
        }
        else if(claseDe(exp, Exp_Iden.class)){
            imprime(exp_Iden(((Exp_Iden)exp).num()));
        }
        else imprime(exp_null());
    }

    private void imprime(Asig asig){
        imprimeExpBin(asig.opnd0(), asig.opnd1(), "=", 1, 0);
    }

    private void imprime(Mayor mayor){
        imprimeExpBin(mayor.opnd0(), mayor.opnd1(), ">", 1, 2);
    }

    private void imprime(Menor menor){
        imprimeExpBin(menor.opnd0(), menor.opnd1(), "<", 1, 2);
    }

    private void imprime(MayorIg m){
        imprimeExpBin(m.opnd0(), m.opnd1(), ">=", 1, 2);
    }

    private void imprime(MenorIg m){
        imprimeExpBin(m.opnd0(), m.opnd1(), "<=", 1, 2);
    }

    private void imprime(Igual igual){
        imprimeExpBin(igual.opnd0(), igual.opnd1(), "==", 1, 2);
    }

    private void imprime(Desigual desi){
        imprimeExpBin(desi.opnd0(), desi.opnd1(), "!=", 1, 2);
    }

    private void imprime(Suma suma){
        imprimeExpBin(suma.opnd0(), suma.opnd1(), "+", 2, 3);
    }

    private void imprime(Resta resta){
        imprimeExpBin(resta.opnd0(), resta.opnd1(), "-", 3, 3);
    }

    private void imprime(And and){
        imprimeExpBin(and.opnd0(), and.opnd1(), "<and>", 4, 3);
    }

    private void imprime(Or or){
        imprimeExpBin(or.opnd0(), or.opnd1(), "<or>", 4, 4);
    }

    private void imprime(Mul mul){
        imprimeExpBin(mul.opnd0(), mul.opnd1(), "*", 4, 5);
    }

    private void imprime(Div div){
        imprimeExpBin(div.opnd0(), div.opnd1(), "/", 4, 5);
    }

    private void imprime(Mod mod){
        imprimeExpBin(mod.opnd0(), mod.opnd1(), "%", 4, 5);
    }

    private void imprimeExpUn(String s, Exp e, int p){
        System.out.println(s);
        imprimeOpnd(e, p);
    }

    private void imprime(Neg neg){
        imprimeExpUn("-", neg.opnd(), 5);
    }

    private void imprime(Not not){
        imprimeExpUn("<not>", not.opnd(), 5);
    }

    private void imprime(AccesoArray a){
        imprimeOpnd(a.exp1(), 6);
        System.out.println("[");
        imprime(a.exp2());
        System.out.println("]");
    }

    private void imprime(AccesoCampo a){
        imprimeOpnd(a.exp(), 6);
        System.out.println(". ");
        System.out.println(a.num()); //CHEQUEAR
    }

    private void imprime(AccesoPuntero a){
        imprimeOpnd(a.exp(), 6);
        System.out.println("^ ");
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

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 
}
