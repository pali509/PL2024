
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
        if(claseDe(bloque.lds(), Si_decs.class)){
            imprime((Si_decs)bloque.lds());
        }
        else imprime((No_decs)bloque.lds());
        if(claseDe(bloque.lis(), Si_Ins.class)){
            imprime((Si_Ins)bloque.lis());
        }
        else imprime((No_Ins)bloque.lis());
        System.out.println("}");
    }

    private void imprime(Si_decs sidecs){
        if(claseDe(sidecs.decs(), Muchas_decs.class)){
            imprime((Muchas_decs)sidecs.decs());
        }
        else imprime((Una_dec)sidecs.decs());
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

    private void imprime(AccesoArray a){
        imprimeOpnd(a.exp1(), 6);
        System.out.println("[");
        imprime(a.exp2());
        System.out.println("]");
    }

    private void imprime(AccesoCampo a){
        imprimeOpnd(a.exp(), 6);
        System.out.println(". ");
        imprime(a.iden());
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
