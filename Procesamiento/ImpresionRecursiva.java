
import asint.SintaxisAbstractaTiny;


public class ImpresionRecursiva extends SintaxisAbstractaTiny {

    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        if(claseDe(opnd, Asig.class)){
            imprime((Asig)opnd);
        }
        else if(claseDe(opnd, Mayor.class)){
            imprime((Mayor)opnd);
        }
        else if(claseDe(opnd, Menor.class)){
            imprime((Menor)opnd);
        }
        else if(claseDe(opnd, MayorIg.class)){
            imprime((MayorIg)opnd);
        }
        else if(claseDe(opnd, MenorIg.class)){
            imprime((MenorIg)opnd);
        }
        else if(claseDe(opnd, Igual.class)){
            imprime((Igual)opnd);
        }
        else if(claseDe(opnd, Desigual.class)){
            imprime((Desigual)opnd);
        }
        else if(claseDe(opnd, Suma.class)){
            imprime((Suma)opnd);
        }
        else if(claseDe(opnd, Resta.class)){
            imprime((Resta)opnd);
        }
        else if(claseDe(opnd, And.class)){
            imprime((And)opnd);
        }
        else if(claseDe(opnd, Or.class)){
            imprime((Or)opnd);
        }
        else if(claseDe(opnd, Mul.class)){
            imprime((Mul)opnd);
        }
        else if(claseDe(opnd, Div.class)){
            imprime((Div)opnd);
        }
        else if(claseDe(opnd, Mod.class)){
            imprime((Mod)opnd);
        }
        else if(claseDe(opnd, Neg.class)){
            imprime((Neg)opnd);
        }
        else if(claseDe(opnd, Not.class)){
            imprime((Not)opnd);
        }
        else if(claseDe(opnd, AccesoArray.class)){
            imprime((AccesoArray)opnd);
        }
        else if(claseDe(opnd, AccesoCampo.class)){
            imprime((AccesoCampo)opnd);
        }
        else if(claseDe(opnd, AccesoPuntero.class)){
            imprime((AccesoPuntero)opnd);
        }
        else if(claseDe(opnd, Exp_lit_ent.class)){
            imprime((Exp_lit_ent)opnd);
        }
        else if(claseDe(opnd, Exp_lit_real.class)){
            imprime((Exp_lit_real)opnd);
        }
        else if(claseDe(opnd, Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)opnd);
        }
        else if(claseDe(opnd, Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)opnd);
        }
        else if(claseDe(opnd, Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)opnd);
        }
        else if(claseDe(opnd, Exp_Iden.class)){
            imprime((Exp_Iden)opnd);
        }
        else imprime((Exp_null)opnd);
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
        if(claseDe(asig.e(), Asig.class)){
            imprime((Asig)asig.e());
        }
        else if(claseDe(asig.e(), Mayor.class)){
            imprime((Mayor)asig.e());
        }
        else if(claseDe(asig.e(), Menor.class)){
            imprime((Menor)asig.e());
        }
        else if(claseDe(asig.e(), MayorIg.class)){
            imprime((MayorIg)asig.e());
        }
        else if(claseDe(asig.e(), MenorIg.class)){
            imprime((MenorIg)asig.e());
        }
        else if(claseDe(asig.e(), Igual.class)){
            imprime((Igual)asig.e());
        }
        else if(claseDe(asig.e(), Desigual.class)){
            imprime((Desigual)asig.e());
        }
        else if(claseDe(asig.e(), Suma.class)){
            imprime((Suma)asig.e());
        }
        else if(claseDe(asig.e(), Resta.class)){
            imprime((Resta)asig.e());
        }
        else if(claseDe(asig.e(), And.class)){
            imprime((And)asig.e());
        }
        else if(claseDe(asig.e(), Or.class)){
            imprime((Or)asig.e());
        }
        else if(claseDe(asig.e(), Mul.class)){
            imprime((Mul)asig.e());
        }
        else if(claseDe(asig.e(), Div.class)){
            imprime((Div)asig.e());
        }
        else if(claseDe(asig.e(), Mod.class)){
            imprime((Mod)asig.e());
        }
        else if(claseDe(asig.e(), Neg.class)){
            imprime((Neg)asig.e());
        }
        else if(claseDe(asig.e(), Not.class)){
            imprime((Not)asig.e());
        }
        else if(claseDe(asig.e(), AccesoArray.class)){
            imprime((AccesoArray)asig.e());
        }
        else if(claseDe(asig.e(), AccesoCampo.class)){
            imprime((AccesoCampo)asig.e());
        }
        else if(claseDe(asig.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)asig.e());
        }
        else if(claseDe(asig.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)asig.e());
        }
        else if(claseDe(asig.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)asig.e());
        }
        else if(claseDe(asig.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)asig.e());
        }
        else if(claseDe(asig.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)asig.e());
        }
        else if(claseDe(asig.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)asig.e());
        }
        else if(claseDe(asig.e(), Exp_Iden.class)){
            imprime((Exp_Iden)asig.e());
        }
        else imprime((Exp_null)asig.e());
    }

    private void imprime(Ins_if iif){
        System.out.println("<if>");
        if(claseDe(iif.e(), Asig.class)){
            imprime((Asig)iif.e());
        }
        else if(claseDe(iif.e(), Mayor.class)){
            imprime((Mayor)iif.e());
        }
        else if(claseDe(iif.e(), Menor.class)){
            imprime((Menor)iif.e());
        }
        else if(claseDe(iif.e(), MayorIg.class)){
            imprime((MayorIg)iif.e());
        }
        else if(claseDe(iif.e(), MenorIg.class)){
            imprime((MenorIg)iif.e());
        }
        else if(claseDe(iif.e(), Igual.class)){
            imprime((Igual)iif.e());
        }
        else if(claseDe(iif.e(), Desigual.class)){
            imprime((Desigual)iif.e());
        }
        else if(claseDe(iif.e(), Suma.class)){
            imprime((Suma)iif.e());
        }
        else if(claseDe(iif.e(), Resta.class)){
            imprime((Resta)iif.e());
        }
        else if(claseDe(iif.e(), And.class)){
            imprime((And)iif.e());
        }
        else if(claseDe(iif.e(), Or.class)){
            imprime((Or)iif.e());
        }
        else if(claseDe(iif.e(), Mul.class)){
            imprime((Mul)iif.e());
        }
        else if(claseDe(iif.e(), Div.class)){
            imprime((Div)iif.e());
        }
        else if(claseDe(iif.e(), Mod.class)){
            imprime((Mod)iif.e());
        }
        else if(claseDe(iif.e(), Neg.class)){
            imprime((Neg)iif.e());
        }
        else if(claseDe(iif.e(), Not.class)){
            imprime((Not)iif.e());
        }
        else if(claseDe(iif.e(), AccesoArray.class)){
            imprime((AccesoArray)iif.e());
        }
        else if(claseDe(iif.e(), AccesoCampo.class)){
            imprime((AccesoCampo)iif.e());
        }
        else if(claseDe(iif.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)iif.e());
        }
        else if(claseDe(iif.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)iif.e());
        }
        else if(claseDe(iif.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)iif.e());
        }
        else if(claseDe(iif.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)iif.e());
        }
        else if(claseDe(iif.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)iif.e());
        }
        else if(claseDe(iif.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)iif.e());
        }
        else if(claseDe(iif.e(), Exp_Iden.class)){
            imprime((Exp_Iden)iif.e());
        }
        else imprime((Exp_null)iif.e());
        imprime(iif.bloque());
    }

    private void imprime(Ins_if_else ifelse){
        System.out.println("<if>");
        if(claseDe(ifelse.e(), Asig.class)){
            imprime((Asig)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Mayor.class)){
            imprime((Mayor)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Menor.class)){
            imprime((Menor)ifelse.e());
        }
        else if(claseDe(ifelse.e(), MayorIg.class)){
            imprime((MayorIg)ifelse.e());
        }
        else if(claseDe(ifelse.e(), MenorIg.class)){
            imprime((MenorIg)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Igual.class)){
            imprime((Igual)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Desigual.class)){
            imprime((Desigual)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Suma.class)){
            imprime((Suma)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Resta.class)){
            imprime((Resta)ifelse.e());
        }
        else if(claseDe(ifelse.e(), And.class)){
            imprime((And)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Or.class)){
            imprime((Or)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Mul.class)){
            imprime((Mul)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Div.class)){
            imprime((Div)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Mod.class)){
            imprime((Mod)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Neg.class)){
            imprime((Neg)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Not.class)){
            imprime((Not)ifelse.e());
        }
        else if(claseDe(ifelse.e(), AccesoArray.class)){
            imprime((AccesoArray)ifelse.e());
        }
        else if(claseDe(ifelse.e(), AccesoCampo.class)){
            imprime((AccesoCampo)ifelse.e());
        }
        else if(claseDe(ifelse.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)ifelse.e());
        }
        else if(claseDe(ifelse.e(), Exp_Iden.class)){
            imprime((Exp_Iden)ifelse.e());
        }
        else imprime((Exp_null)ifelse.e());
        imprime(ifelse.bloque1());
        System.out.println("<else>");
        imprime(ifelse.bloque2());
    }

    private void imprime(Ins_while iwhile){
        System.out.println("<while>");
        if(claseDe(iwhile.e(), Asig.class)){
            imprime((Asig)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Mayor.class)){
            imprime((Mayor)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Menor.class)){
            imprime((Menor)iwhile.e());
        }
        else if(claseDe(iwhile.e(), MayorIg.class)){
            imprime((MayorIg)iwhile.e());
        }
        else if(claseDe(iwhile.e(), MenorIg.class)){
            imprime((MenorIg)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Igual.class)){
            imprime((Igual)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Desigual.class)){
            imprime((Desigual)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Suma.class)){
            imprime((Suma)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Resta.class)){
            imprime((Resta)iwhile.e());
        }
        else if(claseDe(iwhile.e(), And.class)){
            imprime((And)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Or.class)){
            imprime((Or)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Mul.class)){
            imprime((Mul)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Div.class)){
            imprime((Div)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Mod.class)){
            imprime((Mod)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Neg.class)){
            imprime((Neg)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Not.class)){
            imprime((Not)iwhile.e());
        }
        else if(claseDe(iwhile.e(), AccesoArray.class)){
            imprime((AccesoArray)iwhile.e());
        }
        else if(claseDe(iwhile.e(), AccesoCampo.class)){
            imprime((AccesoCampo)iwhile.e());
        }
        else if(claseDe(iwhile.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)iwhile.e());
        }
        else if(claseDe(iwhile.e(), Exp_Iden.class)){
            imprime((Exp_Iden)iwhile.e());
        }
        else imprime((Exp_null)iwhile.e());
        imprime(iwhile.bloque());
    }

    private void imprime(Ins_read read){
        System.out.println("<read>");
        if(claseDe(read.e(), Asig.class)){
            imprime((Asig)read.e());
        }
        else if(claseDe(read.e(), Mayor.class)){
            imprime((Mayor)read.e());
        }
        else if(claseDe(read.e(), Menor.class)){
            imprime((Menor)read.e());
        }
        else if(claseDe(read.e(), MayorIg.class)){
            imprime((MayorIg)read.e());
        }
        else if(claseDe(read.e(), MenorIg.class)){
            imprime((MenorIg)read.e());
        }
        else if(claseDe(read.e(), Igual.class)){
            imprime((Igual)read.e());
        }
        else if(claseDe(read.e(), Desigual.class)){
            imprime((Desigual)read.e());
        }
        else if(claseDe(read.e(), Suma.class)){
            imprime((Suma)read.e());
        }
        else if(claseDe(read.e(), Resta.class)){
            imprime((Resta)read.e());
        }
        else if(claseDe(read.e(), And.class)){
            imprime((And)read.e());
        }
        else if(claseDe(read.e(), Or.class)){
            imprime((Or)read.e());
        }
        else if(claseDe(read.e(), Mul.class)){
            imprime((Mul)read.e());
        }
        else if(claseDe(read.e(), Div.class)){
            imprime((Div)read.e());
        }
        else if(claseDe(read.e(), Mod.class)){
            imprime((Mod)read.e());
        }
        else if(claseDe(read.e(), Neg.class)){
            imprime((Neg)read.e());
        }
        else if(claseDe(read.e(), Not.class)){
            imprime((Not)read.e());
        }
        else if(claseDe(read.e(), AccesoArray.class)){
            imprime((AccesoArray)read.e());
        }
        else if(claseDe(read.e(), AccesoCampo.class)){
            imprime((AccesoCampo)read.e());
        }
        else if(claseDe(read.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)read.e());
        }
        else if(claseDe(read.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)read.e());
        }
        else if(claseDe(read.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)read.e());
        }
        else if(claseDe(read.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)read.e());
        }
        else if(claseDe(read.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)read.e());
        }
        else if(claseDe(read.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)read.e());
        }
        else if(claseDe(read.e(), Exp_Iden.class)){
            imprime((Exp_Iden)read.e());
        }
        else imprime((Exp_null)read.e());
    }

    private void imprime(Ins_write write){
        System.out.println("<write>");
        if(claseDe(write.e(), Asig.class)){
            imprime((Asig)write.e());
        }
        else if(claseDe(write.e(), Mayor.class)){
            imprime((Mayor)write.e());
        }
        else if(claseDe(write.e(), Menor.class)){
            imprime((Menor)write.e());
        }
        else if(claseDe(write.e(), MayorIg.class)){
            imprime((MayorIg)write.e());
        }
        else if(claseDe(write.e(), MenorIg.class)){
            imprime((MenorIg)write.e());
        }
        else if(claseDe(write.e(), Igual.class)){
            imprime((Igual)write.e());
        }
        else if(claseDe(write.e(), Desigual.class)){
            imprime((Desigual)write.e());
        }
        else if(claseDe(write.e(), Suma.class)){
            imprime((Suma)write.e());
        }
        else if(claseDe(write.e(), Resta.class)){
            imprime((Resta)write.e());
        }
        else if(claseDe(write.e(), And.class)){
            imprime((And)write.e());
        }
        else if(claseDe(write.e(), Or.class)){
            imprime((Or)write.e());
        }
        else if(claseDe(write.e(), Mul.class)){
            imprime((Mul)write.e());
        }
        else if(claseDe(write.e(), Div.class)){
            imprime((Div)write.e());
        }
        else if(claseDe(write.e(), Mod.class)){
            imprime((Mod)write.e());
        }
        else if(claseDe(write.e(), Neg.class)){
            imprime((Neg)write.e());
        }
        else if(claseDe(write.e(), Not.class)){
            imprime((Not)write.e());
        }
        else if(claseDe(write.e(), AccesoArray.class)){
            imprime((AccesoArray)write.e());
        }
        else if(claseDe(write.e(), AccesoCampo.class)){
            imprime((AccesoCampo)write.e());
        }
        else if(claseDe(write.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)write.e());
        }
        else if(claseDe(write.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)write.e());
        }
        else if(claseDe(write.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)write.e());
        }
        else if(claseDe(write.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)write.e());
        }
        else if(claseDe(write.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)write.e());
        }
        else if(claseDe(write.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)write.e());
        }
        else if(claseDe(write.e(), Exp_Iden.class)){
            imprime((Exp_Iden)write.e());
        }
        else imprime((Exp_null)write.e());
    }

    private void imprime(Ins_nl nl){
        System.out.println("<nl>");
    }

    private void imprime(Ins_new inew){
        System.out.println("<new>");
        if(claseDe(inew.e(), Asig.class)){
            imprime((Asig)inew.e());
        }
        else if(claseDe(inew.e(), Mayor.class)){
            imprime((Mayor)inew.e());
        }
        else if(claseDe(inew.e(), Menor.class)){
            imprime((Menor)inew.e());
        }
        else if(claseDe(inew.e(), MayorIg.class)){
            imprime((MayorIg)inew.e());
        }
        else if(claseDe(inew.e(), MenorIg.class)){
            imprime((MenorIg)inew.e());
        }
        else if(claseDe(inew.e(), Igual.class)){
            imprime((Igual)inew.e());
        }
        else if(claseDe(inew.e(), Desigual.class)){
            imprime((Desigual)inew.e());
        }
        else if(claseDe(inew.e(), Suma.class)){
            imprime((Suma)inew.e());
        }
        else if(claseDe(inew.e(), Resta.class)){
            imprime((Resta)inew.e());
        }
        else if(claseDe(inew.e(), And.class)){
            imprime((And)inew.e());
        }
        else if(claseDe(inew.e(), Or.class)){
            imprime((Or)inew.e());
        }
        else if(claseDe(inew.e(), Mul.class)){
            imprime((Mul)inew.e());
        }
        else if(claseDe(inew.e(), Div.class)){
            imprime((Div)inew.e());
        }
        else if(claseDe(inew.e(), Mod.class)){
            imprime((Mod)inew.e());
        }
        else if(claseDe(inew.e(), Neg.class)){
            imprime((Neg)inew.e());
        }
        else if(claseDe(inew.e(), Not.class)){
            imprime((Not)inew.e());
        }
        else if(claseDe(inew.e(), AccesoArray.class)){
            imprime((AccesoArray)inew.e());
        }
        else if(claseDe(inew.e(), AccesoCampo.class)){
            imprime((AccesoCampo)inew.e());
        }
        else if(claseDe(inew.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)inew.e());
        }
        else if(claseDe(inew.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)inew.e());
        }
        else if(claseDe(inew.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)inew.e());
        }
        else if(claseDe(inew.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)inew.e());
        }
        else if(claseDe(inew.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)inew.e());
        }
        else if(claseDe(inew.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)inew.e());
        }
        else if(claseDe(inew.e(), Exp_Iden.class)){
            imprime((Exp_Iden)inew.e());
        }
        else imprime((Exp_null)inew.e());
    }

    private void imprime(Ins_delete delete){
        System.out.println("<delete>");
        if(claseDe(delete.e(), Asig.class)){
            imprime((Asig)delete.e());
        }
        else if(claseDe(delete.e(), Mayor.class)){
            imprime((Mayor)delete.e());
        }
        else if(claseDe(delete.e(), Menor.class)){
            imprime((Menor)delete.e());
        }
        else if(claseDe(delete.e(), MayorIg.class)){
            imprime((MayorIg)delete.e());
        }
        else if(claseDe(delete.e(), MenorIg.class)){
            imprime((MenorIg)delete.e());
        }
        else if(claseDe(delete.e(), Igual.class)){
            imprime((Igual)delete.e());
        }
        else if(claseDe(delete.e(), Desigual.class)){
            imprime((Desigual)delete.e());
        }
        else if(claseDe(delete.e(), Suma.class)){
            imprime((Suma)delete.e());
        }
        else if(claseDe(delete.e(), Resta.class)){
            imprime((Resta)delete.e());
        }
        else if(claseDe(delete.e(), And.class)){
            imprime((And)delete.e());
        }
        else if(claseDe(delete.e(), Or.class)){
            imprime((Or)delete.e());
        }
        else if(claseDe(delete.e(), Mul.class)){
            imprime((Mul)delete.e());
        }
        else if(claseDe(delete.e(), Div.class)){
            imprime((Div)delete.e());
        }
        else if(claseDe(delete.e(), Mod.class)){
            imprime((Mod)delete.e());
        }
        else if(claseDe(delete.e(), Neg.class)){
            imprime((Neg)delete.e());
        }
        else if(claseDe(delete.e(), Not.class)){
            imprime((Not)delete.e());
        }
        else if(claseDe(delete.e(), AccesoArray.class)){
            imprime((AccesoArray)delete.e());
        }
        else if(claseDe(delete.e(), AccesoCampo.class)){
            imprime((AccesoCampo)delete.e());
        }
        else if(claseDe(delete.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)delete.e());
        }
        else if(claseDe(delete.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)delete.e());
        }
        else if(claseDe(delete.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)delete.e());
        }
        else if(claseDe(delete.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)delete.e());
        }
        else if(claseDe(delete.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)delete.e());
        }
        else if(claseDe(delete.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)delete.e());
        }
        else if(claseDe(delete.e(), Exp_Iden.class)){
            imprime((Exp_Iden)delete.e());
        }
        else imprime((Exp_null)delete.e());
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
        if(claseDe(muchos.e(), Asig.class)){
            imprime((Asig)muchos.e());
        }
        else if(claseDe(muchos.e(), Mayor.class)){
            imprime((Mayor)muchos.e());
        }
        else if(claseDe(muchos.e(), Menor.class)){
            imprime((Menor)muchos.e());
        }
        else if(claseDe(muchos.e(), MayorIg.class)){
            imprime((MayorIg)muchos.e());
        }
        else if(claseDe(muchos.e(), MenorIg.class)){
            imprime((MenorIg)muchos.e());
        }
        else if(claseDe(muchos.e(), Igual.class)){
            imprime((Igual)muchos.e());
        }
        else if(claseDe(muchos.e(), Desigual.class)){
            imprime((Desigual)muchos.e());
        }
        else if(claseDe(muchos.e(), Suma.class)){
            imprime((Suma)muchos.e());
        }
        else if(claseDe(muchos.e(), Resta.class)){
            imprime((Resta)muchos.e());
        }
        else if(claseDe(muchos.e(), And.class)){
            imprime((And)muchos.e());
        }
        else if(claseDe(muchos.e(), Or.class)){
            imprime((Or)muchos.e());
        }
        else if(claseDe(muchos.e(), Mul.class)){
            imprime((Mul)muchos.e());
        }
        else if(claseDe(muchos.e(), Div.class)){
            imprime((Div)muchos.e());
        }
        else if(claseDe(muchos.e(), Mod.class)){
            imprime((Mod)muchos.e());
        }
        else if(claseDe(muchos.e(), Neg.class)){
            imprime((Neg)muchos.e());
        }
        else if(claseDe(muchos.e(), Not.class)){
            imprime((Not)muchos.e());
        }
        else if(claseDe(muchos.e(), AccesoArray.class)){
            imprime((AccesoArray)muchos.e());
        }
        else if(claseDe(muchos.e(), AccesoCampo.class)){
            imprime((AccesoCampo)muchos.e());
        }
        else if(claseDe(muchos.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)muchos.e());
        }
        else if(claseDe(muchos.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)muchos.e());
        }
        else if(claseDe(muchos.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)muchos.e());
        }
        else if(claseDe(muchos.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)muchos.e());
        }
        else if(claseDe(muchos.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)muchos.e());
        }
        else if(claseDe(muchos.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)muchos.e());
        }
        else if(claseDe(muchos.e(), Exp_Iden.class)){
            imprime((Exp_Iden)muchos.e());
        }
        else imprime((Exp_null)muchos.e());
    }

    private void imprime(Un_PReal uno){
        if(claseDe(uno.e(), Asig.class)){
            imprime((Asig)uno.e());
        }
        else if(claseDe(uno.e(), Mayor.class)){
            imprime((Mayor)uno.e());
        }
        else if(claseDe(uno.e(), Menor.class)){
            imprime((Menor)uno.e());
        }
        else if(claseDe(uno.e(), MayorIg.class)){
            imprime((MayorIg)uno.e());
        }
        else if(claseDe(uno.e(), MenorIg.class)){
            imprime((MenorIg)uno.e());
        }
        else if(claseDe(uno.e(), Igual.class)){
            imprime((Igual)uno.e());
        }
        else if(claseDe(uno.e(), Desigual.class)){
            imprime((Desigual)uno.e());
        }
        else if(claseDe(uno.e(), Suma.class)){
            imprime((Suma)uno.e());
        }
        else if(claseDe(uno.e(), Resta.class)){
            imprime((Resta)uno.e());
        }
        else if(claseDe(uno.e(), And.class)){
            imprime((And)uno.e());
        }
        else if(claseDe(uno.e(), Or.class)){
            imprime((Or)uno.e());
        }
        else if(claseDe(uno.e(), Mul.class)){
            imprime((Mul)uno.e());
        }
        else if(claseDe(uno.e(), Div.class)){
            imprime((Div)uno.e());
        }
        else if(claseDe(uno.e(), Mod.class)){
            imprime((Mod)uno.e());
        }
        else if(claseDe(uno.e(), Neg.class)){
            imprime((Neg)uno.e());
        }
        else if(claseDe(uno.e(), Not.class)){
            imprime((Not)uno.e());
        }
        else if(claseDe(uno.e(), AccesoArray.class)){
            imprime((AccesoArray)uno.e());
        }
        else if(claseDe(uno.e(), AccesoCampo.class)){
            imprime((AccesoCampo)uno.e());
        }
        else if(claseDe(uno.e(), AccesoPuntero.class)){
            imprime((AccesoPuntero)uno.e());
        }
        else if(claseDe(uno.e(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)uno.e());
        }
        else if(claseDe(uno.e(), Exp_lit_real.class)){
            imprime((Exp_lit_real)uno.e());
        }
        else if(claseDe(uno.e(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)uno.e());
        }
        else if(claseDe(uno.e(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)uno.e());
        }
        else if(claseDe(uno.e(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)uno.e());
        }
        else if(claseDe(uno.e(), Exp_Iden.class)){
            imprime((Exp_Iden)uno.e());
        }
        else imprime((Exp_null)uno.e());
    }

    private void imprimeExpBin(Exp o0, Exp o1, String s, int p0, int p1){
        imprimeOpnd(o0, p0);
        System.out.println(s);
        imprimeOpnd(o1, p1);
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
        if(claseDe(a.exp2(), Asig.class)){
            imprime((Asig)a.exp2());
        }
        else if(claseDe(a.exp2(), Mayor.class)){
            imprime((Mayor)a.exp2());
        }
        else if(claseDe(a.exp2(), Menor.class)){
            imprime((Menor)a.exp2());
        }
        else if(claseDe(a.exp2(), MayorIg.class)){
            imprime((MayorIg)a.exp2());
        }
        else if(claseDe(a.exp2(), MenorIg.class)){
            imprime((MenorIg)a.exp2());
        }
        else if(claseDe(a.exp2(), Igual.class)){
            imprime((Igual)a.exp2());
        }
        else if(claseDe(a.exp2(), Desigual.class)){
            imprime((Desigual)a.exp2());
        }
        else if(claseDe(a.exp2(), Suma.class)){
            imprime((Suma)a.exp2());
        }
        else if(claseDe(a.exp2(), Resta.class)){
            imprime((Resta)a.exp2());
        }
        else if(claseDe(a.exp2(), And.class)){
            imprime((And)a.exp2());
        }
        else if(claseDe(a.exp2(), Or.class)){
            imprime((Or)a.exp2());
        }
        else if(claseDe(a.exp2(), Mul.class)){
            imprime((Mul)a.exp2());
        }
        else if(claseDe(a.exp2(), Div.class)){
            imprime((Div)a.exp2());
        }
        else if(claseDe(a.exp2(), Mod.class)){
            imprime((Mod)a.exp2());
        }
        else if(claseDe(a.exp2(), Neg.class)){
            imprime((Neg)a.exp2());
        }
        else if(claseDe(a.exp2(), Not.class)){
            imprime((Not)a.exp2());
        }
        else if(claseDe(a.exp2(), AccesoArray.class)){
            imprime((AccesoArray)a.exp2());
        }
        else if(claseDe(a.exp2(), AccesoCampo.class)){
            imprime((AccesoCampo)a.exp2());
        }
        else if(claseDe(a.exp2(), AccesoPuntero.class)){
            imprime((AccesoPuntero)a.exp2());
        }
        else if(claseDe(a.exp2(), Exp_lit_ent.class)){
            imprime((Exp_lit_ent)a.exp2());
        }
        else if(claseDe(a.exp2(), Exp_lit_real.class)){
            imprime((Exp_lit_real)a.exp2());
        }
        else if(claseDe(a.exp2(), Exp_lit_BoolTrue.class)){
            imprime((Exp_lit_BoolTrue)a.exp2());
        }
        else if(claseDe(a.exp2(), Exp_lit_BoolFalse.class)){
            imprime((Exp_lit_BoolFalse)a.exp2());
        }
        else if(claseDe(a.exp2(), Exp_lit_cadena.class)){
            imprime((Exp_lit_cadena)a.exp2());
        }
        else if(claseDe(a.exp2(), Exp_Iden.class)){
            imprime((Exp_Iden)a.exp2());
        }
        else imprime((Exp_null)a.exp2());
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
