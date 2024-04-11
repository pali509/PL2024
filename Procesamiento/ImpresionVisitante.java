

import asint.ProcesamientoDef;

import asint.SintaxisAbstractaTiny;

public class ImpresionVisitante extends SintaxisAbstractaTiny {
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        opnd.imprime(this);
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }

    private static void imprimeExpBin(ExpBin expb,Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.println(" "+op+" $f:"+ expb.leeFila()+",c:"+ expb.leeCol()+"$");
        imprimeOpnd(opnd1,np1);
    }

    // 1. ExpBin
    public void imprime(Suma suma) {
        Exp opnd0 = suma.opnd0();
        Exp opnd1 = suma.opnd1();

        imprimeExpBin(suma, opnd0, "+", opnd1, 2, 3);
    }
    public void imprime(Resta resta) {
        Exp opnd0 = resta.opnd0();
        Exp opnd1 = resta.opnd1();

        imprimeExpBin(this,opnd0,"-",opnd1,2,3);
    }
    public void imprime(Mul mul) {
        Exp opnd0 = mul.opnd0();
        Exp opnd1 = mul.opnd1();

        imprimeExpBin(this,opnd0,"*",opnd1,4,5);
    }
    public void imprime(Div div) {
        Exp opnd0 = div.opnd0();
        Exp opnd1 = div.opnd1();

        imprimeExpBin(this,opnd0,"/",opnd1,4,5);
    }
    public void imprime(Mod mod) {
        Exp opnd0 = mod.opnd0();
        Exp opnd1 = mod.opnd1();

        imprimeExpBin(this,opnd0,"%",opnd1,4,5);
    }
    public void imprime(Asig asig) {
        Exp opnd0 = asig.opnd0();
        Exp opnd1 = asig.opnd1();

        imprimeExpBin(this,opnd0,"=",opnd1,0,1);
    }
    public void imprime(Mayor mayor) {
        Exp opnd0 = mayor.opnd0();
        Exp opnd1 = mayor.opnd1();

        imprimeExpBin(this,opnd0,">",opnd1,1,2);
    }
    public void imprime(Menor menor) {
        Exp opnd0 = menor.opnd0();
        Exp opnd1 = menor.opnd1();

        imprimeExpBin(this,opnd0,"<",opnd1,1,2);
    }
    public void imprime(Mayor mayor) {
        Exp opnd0 = mayor.opnd0();
        Exp opnd1 = mayor.opnd1();

        imprimeExpBin(this,opnd0,">",opnd1,1,2);
    }
    public void imprime(MayorIg mayorIg) {
        Exp opnd0 = mayorIg.opnd0();
        Exp opnd1 = mayorIg.opnd1();

        imprimeExpBin(this,opnd0,">=",opnd1,1,2);
    }
    public void imprime(MenorIg menorIg) {
        Exp opnd0 = menorIg.opnd0();
        Exp opnd1 = menorIg.opnd1();

        imprimeExpBin(this,opnd0,"<=",opnd1,1,2);
    }
    public void imprime(Igual igual) {
        Exp opnd0 = igual.opnd0();
        Exp opnd1 = igual.opnd1();

        imprimeExpBin(this,opnd0,"==",opnd1,1,2);
    }
    public void imprime(Desigual desigual) {
        Exp opnd0 = desigual.opnd0();
        Exp opnd1 = desigual.opnd1();

        imprimeExpBin(this,opnd0,"!=",opnd1,1,2);
    }
    public void imprime(And and) {
        Exp opnd0 = And.opnd0();
        Exp opnd1 = And.opnd1();

        imprimeExpBin(this, exp1, "<and>", exp2, 4, 3);
    }
    public void imprime(Or or) {
        Exp opnd0 = or.opnd0();
        Exp opnd1 = or.opnd1();

        imprimeExpBin(this, exp1, "<or>", exp2, 4, 3);
    }

    // 2. ExpUn
    public void imprime(Neg neg) {
        Exp opnd = neg.opnd();

        System.out.println("- $f:"+neg.leeFila()+",c:"+neg.leeCol()+"$");
        imprimeOpnd(opnd, 5);
    }
    public void imprime(Not not) {
        Exp opnd = not.opnd();

        System.out.println("<not> $f:"+not.leeFila()+",c:"+not.leeCol()+"$");
        imprimeOpnd(opnd, 5);
    }

    // 3. Tipo
    public void imprime(Lit_ent lit_ent) {
        System.out.println("<int>");
    }
    public void imprime(Lit_real lit_real) {
        System.out.println("<real>");
    }
    public void imprime(Lit_bool lit_bool) {
        System.out.println("<bool>");
    }
    public void imprime(Lit_string lit_string) {
        System.out.println("<string>");
    }
    public void imprime(Iden iden) {
        System.out.println(iden.str() +"$f:"+iden.leeFila()+",c:"+iden.leeCol()+"$");
    }
    public void imprime(Array array) {
        array.tipo().imprime(this);
        System.out.print("[");
        array.num().imprime(this);
        System.out.println("] $f:" + array.num().leeFila() + ",c:" + array.num().leeCol() + "$");
    }
    public void imprime(Puntero puntero) {
        // TODO: Revisar, no se seguro
        puntero.tipo().imprime(this);
        System.out.println("^");
    }
    public void imprime(Struct struct) {
        System.out.println("<struct>");
        System.out.println("{");
        struct.lcamp().imprime(this);
        System.out.println("}");
    }


    // 4. Exp
    public void imprime(Exp_lit_ent exp_lit_ent) {
        System.out.println(exp_lit_ent.lex() + "$f:"+exp_lit_ent.leeFila()+",c:"+exp_lit_ent.leeCol()+"$" );
    }
    public void imprime(Exp_lit_real exp_lit_real) {
        System.out.println(exp_lit_real.lex() + "$f:"+exp_lit_ent.leeFila()+",c:"+exp_lit_ent.leeCol()+"$" );
    }
    public void imprime(Exp_lit_BoolTrue exp_lit_BoolTrue) {
        System.out.println("<true>" + "$f:"+exp_lit_BoolTrue.leeFila()+",c:"+exp_lit_BoolTrue.leeCol()+"$" );
    }
    public void imprime(Exp_lit_BoolFalse exp_lit_BoolFalse) {
        System.out.println("<false>" + "$f:"+exp_lit_BoolFalse.leeFila()+",c:"+exp_lit_BoolFalse.leeCol()+"$" );
    }
    public void imprime(Exp_null exp_null) {
        System.out.println("<null>");
    }
    public void imprime(Exp_lit_cadena exp_lit_cadena) {
        System.out.println(exp_lit_cadena.num() + "$f:"+exp_lit_cadena.leeFila()+",c:"+exp_lit_cadena.leeCol()+"$" );
    }
    public void imprime(Exp_Iden exp_Iden) {
        System.out.println(exp_Iden.num() + "$f:"+exp_lit_cadena.leeFila()+",c:"+exp_lit_cadena.leeCol()+"$" );
    }
    public void imprime(AccesoArray accesoArray) {
        imprimeOpnd(accesoArray.exp1(), 6);
        System.out.println("[");
        accesoArray.exp2().imprime(this);
        System.out.println("]");
    }
    public void imprime(AccesoCampo accesoCampo) {
        imprimeOpnd(accesoCampo.exp(), 6);
        System.out.println(".");
        accesoCampo.id().imprime(this);
    }
    public void imprime(AccesoPuntero accesoPuntero) {
        imprimeOpnd(accesoPuntero.exp(), 6);
        System.out.println("^");
        System.out.println(";");
    }


    // 5. Pform
    public void imprime(PFref pfref) {
        pfref.t().imprime(this);
        System.out.println("&");
        pfref.id().imprime(this);
    }
    public void imprime(PFnoref pfnoref) {
        pfnoref.t().imprime(this);
        pfnoref.id().imprime(this);
    }


    // 6. PFormOpt
    public void imprime(Si_pforms si_pforms) {
        si_pforms.pforms().imprime(this);
    }
    public void imprime(No_pforms no_pforms) {
        // TODO: Se deja vacio?
    }

    // 7. LPForm
    public void imprime(Un_pform un_pform) {
        un_pform.pform().imprime(this);
    }
    public void imprime(Muchos_pforms muchos_pforms) {
        muchos_pforms.pforms().imprime(this);
        System.out.println(",");
        muchos_pforms.pform().imprime(this);
    }


    // 8. Dec
    public void imprime(Dec_var dec_var) {
        dec_var.tipo().imprime(this);
        dec_var.iden().imprime(this);
        System.out.println(";");
    }
    public void imprime(Dec_tipo dec_tipo) {
        System.out.println("<type>");
        dec_tipo.tipo().imprime(this);
        dec_tipo.iden.imprime(this);
    }
    public void imprime(Dec_proc dec_proc) {
        System.out.println("<proc>");
        dec_proc.iden().imprime(this);
        System.out.println("(");
        dec_proc.pf().imprime(this);
        System.out.println(")");
        dec_proc.bq().imprime(this);
    }


    // 9. LDecsOpt
    public void imprime(Si_decs si_decs) {
        System.out.println("");
        si_decs.decs().imprime(this);
    }
    public void imprime(No_decs no_decs) {

    }


    // 9. LDecs
    public void imprime(Una_dec una_dec) {
        una_dec.dec().imprime(this);
    }
    public void imprime(Muchas_decs muchas_decs) {
        muchas_decs.ldecs().imprime(this);
        System.out.println(",");
        muchas_decs.dec().imprime(this);
    }


    // 10. Nodo
    public void imprime(Prog prog) {
        System.out.println("evalua");
        System.out.print("  ");
        prog.bq().imprime(this);
        System.out.println("<EOF>");
    }
    public void imprime(Bloque bloque) {
        System.out.println("{");
        bloque.lds().imprime(this);
        bloque.lis().imprime(this);
        System.out.println("}");
    }
    public void imprime(Camp camp) {
        camp.tipo().imprime(this);
        camp.iden().imprime(this);
    }
    public void imprime(Camp camp) {
        camp.tipo().imprime(this);
        camp.iden().imprime(this);
    }


    // 11. LCamp
    public void imprime(Un_camp un_camp) {
        un_camp.campo().imprime(this);
    }
    public void imprime(Muchos_camp muchos_camp) {
        muchos_camp.lcs().imprime(this);
        System.out.println(",");
        muchos_camp.campo().imprime(this);
    }


    // 12. Ins
    public void imprime(Ins_asig ins_asig) {
        System.out.println("@");
        ins_asig.e().imprime();
        System.out.println(";");
    }
    public void imprime(Ins_if ins_if) {
        System.out.println("<if>");
        ins_if.e().imprime(this);
        ins_if.bloque().imprime(this);
    }
    public void imprime(Ins_if_else ins_if_else) {
        System.out.println("<if>");
        ins_if_else.e().imprime();
        ins_if_else.bloque1().imprime(this);
        System.out.println("<else>");
        ins_if_else.bloque2().imprime(this);
    }
    public void imprime(Ins_while ins_while) {
        System.out.println("<while>");
        ins_while.e().imprime(this);
        ins_while.bloque().imprime(this);
    }
    public void imprime(Ins_read ins_read) {
        System.out.println("<read>");
        ins_read.e().imprime(this);
    }
    public void imprime(Ins_write ins_write) {
        System.out.println("<write>");
        ins_write.e().imprime(this);
    }
    public void imprime(Ins_new ins_new) {
        System.out.println("<new>");
        ins_new.e().imprime(this);
    }
    public void imprime(Ins_delete ins_delete) {
        System.out.println("<delete>");
        ins_delete.e().imprime(this);
    }
    public void imprime(Ins_nl ins_nl) {
        System.out.println("<nl>");
    }
    public void imprime(Ins_call ins_call) {
        System.out.println("<call>");
        ins_call.id().imprime(this);
        System.out.println("(");
        ins_call.pr().imprime(this);
        System.out.println(")");
    }
    public void imprime(Ins_bloque ins_bloque) {
        ins_bloque.bloque().imprime(this);
    }


    // 13. LInsOpt
    public void imprime(Si_Ins si_Ins) {
        si_Ins.ins().imprime(this);
    }
    public void imprime(No_Ins no_Ins) {

    }

    
    // 14. LIns
    public void imprime(Una_ins una_ins) {
        una_ins.ins().imprime(this);
    }
    public void imprime(Muchas_ins muchas_ins) {
        muchas_ins.li().imprime(this);
        System.out.println(";");
        muchas_ins.ins().imprime(this);
    }


    // 15. LPReal
    public void imprime(Si_preal si_preal) {
        si_preal.lpr().imprime(this);
    }
    public void imprime(No_preal no_preal) {

    }
    public void imprime(Un_PReal un_PReal) {
        un_PReal.e().imprime(this);
    }
    public void imprime(Muchos_preal muchos_preal) {
        muchos_preal.lpr().imprime(this);
        System.out.println(",");
        muchos_preal.e().imprime(this);
    }
}
