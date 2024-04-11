
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
        imprime(bloque.lds());
        imprime(bloque.lis());
        System.out.println("}");
    }

    private void imprime(LDecsOpt l){
        if(claseDe(l, Si_decs.class)){
            imprime(si_decs(l.decs()));
        }
        else imprime(no_decs());
    }

    private void imprime(LDecs l){
        if(claseDe(l, Muchas_decs.class)){
            imprime(muchas_decs(l.ldecs(), l.dec()));
        }
        else imprime(una_dec(l.dec()));
    }

    private void imprime(Si_decs sidecs){
        imprime(sidecs.decs());
    }

    private void imprime(No_decs nodecs){
        //Vacío
    }

    private void imprime(Muchas_decs muchas){
        imprime(muchas.ldecs());
        System.out.println(";");
        imprime(una_dec(muchas.dec()));
    }

    private void imprime(Una_dec una){
        imprime(una.dec());
    }

    private void imprime(Dec d){
        if(claseDe(d, Dec_var.class)){
            imprime(dec_var(d.iden(), d.tipo()));
        }
        else if(claseDe(d, Dec_tipo.class)){
            imprime(dec_tipo(d.iden(), d.tipo()));
        }
        else imprime(dec_proc(d.iden(), d.pf(), d.bq()));
    }

    private void imprime(Dec_var var){
        imprime(var.tipo());
        imprime(iden(var.iden().str()));
    }

    private void imprime(Dec_tipo tipo){
        System.out.println("<type>");
        imprime(tipo.tipo());
        imprime(iden(tipo.iden().str()));
    }

    private void imprime(Dec_proc proc){
        System.out.println("<proc>");
        imprime(iden(proc.iden().str()));
        System.out.println("(");
        imprime(proc.pf());
        System.out.println(")");
        imprime(proc.bq());
    }

    private void imprime(PFormOpt p){
        if(claseDe(p, Si_pforms.class)){
            imprime(si_pforms(p.pforms()));
        }
        else imprime(no_pforms());
    }

    private void imprime(Si_pforms sipform){
        imprime(sipform.pforms());
    }

    private void imprime(No_pforms nopform){
        //Vacía
    }

    private void imprime(LPForm l){
        if(claseDe(l, Muchos_pforms.class)){
            imprime(muchos_pforms(l.pforms(), l.pform()));
        }
        else imprime(un_pform(l.pform()));
    }

    private void imprime(Muchos_pforms muchos){
        imprime(muchos.pforms());
        System.out.println(",");
        imprime(muchos.pform());
    }

    private void imprime(Un_pform uno){
        imprime(uno.pform());
    }

    private void imprime(Pform p){
        if(claseDe(p, PFref.class)){
            imprime(pfref(p.t(), p.id()));
        }
        else imprime(pfnoref(p.t(), p.id()));
    }

    private void imprime(PFref pform){
        imprime(pform.t());
        System.out.println("&");
        imprime(iden(pform.id().str()));
    }

    private void imprime(PFnoref pform){
        imprime(pform.t());
        imprime(iden(pform.id().str()));
    }

    private void imprime(Tipo t){
        if(claseDe(t, Array.class)){
            imprime(array(t.tipo(), t.num()));
        }
        else if(claseDe(t, Puntero.class)){
            imprime(puntero(t.tipo()));
        }
        else if(claseDe(t, Iden.class)){
            imprime(iden(t.str()));
        }
        else if(claseDe(t, Struct.class)){
            imprime(struct(t.lcamp()));
        }
        else if(claseDe(t, Lit_ent.class)){
            imprime(lit_ent());
        }
        else if(claseDe(t, Lit_real.class)){
            imprime(lit_real());
        }
        else if(claseDe(t, Lit_bool.class)){
            imprime(lit_bool());
        }
        else imprime(lit_string());
    }

    private void imprime(Array array){
        imprime(array.tipo());
        System.out.println("[");
        imprime(iden(array.iden().str()));
        System.out.println("]");
    }

    private void imprime(Puntero puntero){
        imprime(puntero.tipo());
        System.out.println("^");
    }
    
    private void imprime(Iden iden){
        System.out.println(iden.str());
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

    private void imprime(Lit_bool bool){
        System.out.println("<bool>");
    }

    private void imprime(Lit_string string){
        System.out.println("<string>");
    }

    private void imprime(LCamp l){
        if(claseDe(l, Muchos_camp.class)){
            imprime(muchos_camp(l.lcs(), l.campo()));
        }
        else imprime(un_camp(l.campo()));
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
        imprime(iden(camp.iden().str()));
    }

    private void imprime(LIns l){
        if(claseDe(l, Muchas_ins.class)){
            imprime(muchas_ins(l.li(), l.ins()));
        }
        else imprime(una_ins(l.ins()));
    }

    private void imprime(LInsOpt l){
        if(claseDe(l, Si_Ins.class)){
            imprime(si_ins(l.ins()));
        }
        else imprime(no_ins());
    }

    private void imprime(Si_Ins s){
        imprime(s.ins());
    }

    private void imprime(No_Ins n){
        //Vacío
    }

    private void imprime(Muchas_ins muchas){
        imprime(muchas.li());
        System.out.println(";");
        imprime(una_ins(muchas.ins()));
    }

    private void imprime(Una_ins una){
        imprime(una.ins());
    }

    private void imprime(Ins i){
        if(claseDe(i, Ins_asig.class)){
            imprime(ins_asig(i.e()));
        }
        else if(claseDe(i, Ins_if.class)){
            imprime(ins_if(i.e(), i.bloque()));
        }
        else if(claseDe(i, Ins_if_else.class)){
            imprime(ins_if_else(i.e(), i.bloque(), i.bloque2()));
        }
        else if(claseDe(i, Ins_while.class)){
            imprime(ins_while(i.e(), i.bloque()));
        }
        else if(claseDe(i, Ins_read.class)){
            imprime(ins_read(i.e()));
        }
        else if(claseDe(i, Ins_write.class)){
            imprime(ins_write(i.e()));
        }
        else if(claseDe(i, Ins_nl.class)){
            imprime(ins_nl());
        }
        else if(claseDe(i, Ins_new.class)){
            imprime(ins_new(i.e()));
        }
        else if(claseDe(i, Ins_delete.class)){
            imprime(ins_delete(i.e()));
        }
        else if(claseDe(i, Ins_call.class)){
            imprime(ins_call(i.id(), i.pr()));
        }
        else imprime(ins_bloque(i.bloque()));
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
        imprime(ifelse.bloque());
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
        imprime(iden(call.id().str()));
        System.out.println("(");
        imprime(call.pr());
        System.out.println(")");
    }

    private void imprime(Ins_bloque bloque){
        imprime(bloque.bloque());
    }

    private void imprime(LPRealOpt l){
        if(claseDe(l, Si_preal.class)){
            imprime(si_preal(l.lpr()));
        }
        else imprime(no_preal());
    }

    private void imprime(Si_preal preal){
        imprime(preal.lpr());
    }

    private void imprime(No_preal no){
        //Vacia
    }

    private void imprime(LPReal l){
        if(claseDe(l, Muchos_preal.class)){
            imprime(muchos_preal(l.lpr(), l.e()));
        }
        else imprime(un_preal(l.e()));
    }

    private void imprime(Muchos_preal muchos){
        imprime(muchos.lpr());
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
//CLAU VAS POR AQUI
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
        imprime(iden(e.iden().str()));
    }

    private void imprime(Exp_Iden e){
        imprime(iden(e.iden().str()));
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
