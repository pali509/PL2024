

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;


public class ImpresionVisitante extends ProcesamientoDef {
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        opnd.procesa(this);
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }

    private void imprimeExpBin(ExpBin expb,Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.println(" "+op+" $f:"+ expb.leeFila()+",c:"+ expb.leeCol()+"$");
        imprimeOpnd(opnd1,np1);
    }

    private void imprimeExpUn(Exp eu, String s, Exp e, int p){
        System.out.println(s+" $f:"+eu.leeFila()+",c:"+eu.leeCol()+"$");
        imprimeOpnd(e, p);
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

        imprimeExpBin(resta,opnd0,"-",opnd1,3,3);
    }
    public void imprime(Mul mul) {
        Exp opnd0 = mul.opnd0();
        Exp opnd1 = mul.opnd1();

        imprimeExpBin(mul,opnd0,"*",opnd1,4,5);
    }
    public void imprime(Div div) {
        Exp opnd0 = div.opnd0();
        Exp opnd1 = div.opnd1();

        imprimeExpBin(div,opnd0,"/",opnd1,4,5);
    }
    public void imprime(Mod mod) {
        Exp opnd0 = mod.opnd0();
        Exp opnd1 = mod.opnd1();

        imprimeExpBin(mod,opnd0,"%",opnd1,4,5);
    }
    public void imprime(Asig asig) {
        Exp opnd0 = asig.opnd0();
        Exp opnd1 = asig.opnd1();

        imprimeExpBin(asig,opnd0,"=",opnd1,1,0);
    }
    public void imprime(Mayor mayor) {
        Exp opnd0 = mayor.opnd0();
        Exp opnd1 = mayor.opnd1();

        imprimeExpBin(mayor,opnd0,">",opnd1,1,2);
    }
    public void imprime(Menor menor) {
        Exp opnd0 = menor.opnd0();
        Exp opnd1 = menor.opnd1();

        imprimeExpBin(menor,opnd0,"<",opnd1,1,2);
    }
    public void imprime(MayorIg mayorIg) {
        Exp opnd0 = mayorIg.opnd0();
        Exp opnd1 = mayorIg.opnd1();

        imprimeExpBin(mayorIg,opnd0,">=",opnd1,1,2);
    }
    public void imprime(MenorIg menorIg) {
        Exp opnd0 = menorIg.opnd0();
        Exp opnd1 = menorIg.opnd1();

        imprimeExpBin(menorIg,opnd0,"<=",opnd1,1,2);
    }
    public void imprime(Igual igual) {
        Exp opnd0 = igual.opnd0();
        Exp opnd1 = igual.opnd1();

        imprimeExpBin(igual,opnd0,"==",opnd1,1,2);
    }
    public void imprime(Desigual desigual) {
        Exp opnd0 = desigual.opnd0();
        Exp opnd1 = desigual.opnd1();

        imprimeExpBin(desigual,opnd0,"!=",opnd1,1,2);
    }
    public void imprime(And and) {
        Exp exp1 = and.opnd0();
        Exp exp2 = and.opnd1();

        imprimeExpBin(and, exp1, "<and>", exp2, 4, 3);
    }
    public void imprime(Or or) {
        Exp exp1 = or.opnd0();
        Exp exp2 = or.opnd1();

        imprimeExpBin(or, exp1, "<or>", exp2, 4, 4);
    }

    // 2. ExpUn
    public void imprime(Neg neg) {
        Exp opnd = neg.opnd0();
        imprimeExpUn(neg, "-", opnd, 5);
    }
    public void imprime(Not not) {
        Exp opnd = not.opnd0();

        imprimeExpUn(not, "<not>", opnd, 5);
    }

    // 3. Tipo
    public Tipo iden(StringLocalizado id) {
        return new Iden(id);
    }
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
    public void imprime(Iden iden) { // REVISAR
        System.out.println(iden.str() +"$f:"+iden.leeFila()+",c:"+iden.leeCol()+"$");
    }
    public void imprime(Array array) {
        array.tipo().procesa(this);
        System.out.print("[");
        iden(array.num()).procesa(this);
        System.out.println("] $f:" + array.leeFila() + ",c:" + array.leeCol() + "$");
    }
    public void imprime(Puntero puntero) {
        puntero.tipo().procesa(this);
        System.out.println("^");
    }
    public void imprime(Struct struct) {
        System.out.println("<struct>");
        System.out.println("{");
        struct.lcamp().procesa(this);
        System.out.println("}");
    }


    // 4. Exp
    public void imprime(Exp_lit_ent exp_lit_ent) {
        System.out.println("N" + "$f:"+exp_lit_ent.leeFila()+",c:"+exp_lit_ent.leeCol()+"$" );
    }
    public void imprime(Exp_lit_real exp_lit_real) {
        System.out.println("R" + "$f:"+exp_lit_real.leeFila()+",c:"+exp_lit_real.leeCol()+"$" );
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
        iden(exp_lit_cadena.iden()).procesa(this);
    }
    public void imprime(Exp_Iden exp_Iden) {
        iden(exp_Iden.iden()).procesa(this);
    }
    public void imprime(AccesoArray accesoArray) {
        imprimeOpnd(accesoArray.opnd0(), 6);
        System.out.println("["+ "$f:"+accesoArray.leeFila()+",c:"+accesoArray.leeCol()+"$");
        accesoArray.opnd1().procesa(this);
        System.out.println("]");
    }
    public void imprime(AccesoCampo accesoCampo) {
        imprimeOpnd(accesoCampo.opnd0(), 6);
        System.out.println(".");
        iden(accesoCampo.iden()).procesa(this);
    }
    public void imprime(AccesoPuntero accesoPuntero) {
        imprimeOpnd(accesoPuntero.opnd0(), 6);
        System.out.println("^ " +"$f:"+accesoPuntero.leeFila()+",c:"+accesoPuntero.leeCol()+"$");

    }


    // 5. Pform
    public void imprime(PFref pfref) {
        pfref.t().procesa(this);
        System.out.println("&");
        iden(pfref.id()).procesa(this);
    }
    public void imprime(PFnoref pfnoref) {
        pfnoref.t().procesa(this);
        iden(pfnoref.id()).procesa(this);
    }


    // 6. PFormOpt
    public void imprime(Si_pforms si_pforms) {
        si_pforms.pforms().procesa(this);
    }
    public void imprime(No_pforms no_pforms) {
        // TODO: Se deja vacio
    }

    // 7. LPForm
    public void imprime(Un_pform un_pform) {
        un_pform.pform().procesa(this);
    }
    public void imprime(Muchos_pforms muchos_pforms) {
        muchos_pforms.pforms().procesa(this);
        System.out.println(",");
        muchos_pforms.pform().procesa(this);
    }


    // 8. Dec
    public void imprime(Dec_var dec_var) {
        dec_var.tipo().procesa(this);
        iden(dec_var.iden()).procesa(this);
        System.out.println(";");
    }
    public void imprime(Dec_tipo dec_tipo) {
        System.out.println("<type>");
        dec_tipo.tipo().procesa(this);
        iden(dec_tipo.iden()).procesa(this);
    }
    public void imprime(Dec_proc dec_proc) {
        System.out.println("<proc>");
        iden(dec_proc.iden()).procesa(this);
        System.out.println("(");
        dec_proc.pf().procesa(this);
        System.out.println(")");
        dec_proc.bq().procesa(this);
    }


    // 9. LDecsOpt
    public void imprime(Si_decs si_decs) {
        System.out.println("");
        si_decs.decs().procesa(this);
    }
    public void imprime(No_decs no_decs) {

    }


    // 9. LDecs
    public void imprime(Una_dec una_dec) {
        una_dec.dec().procesa(this);
    }
    public void imprime(Muchas_decs muchas_decs) {
        muchas_decs.ldecs().procesa(this);
        System.out.println(",");
        muchas_decs.dec().procesa(this);
    }


    // 10. Nodo
    public void imprime(Prog prog) {
        prog.bq().procesa(this);
        System.out.println("<EOF>");
    }
    public void imprime(Bloque bloque) {
        System.out.println("{");
        bloque.lds().procesa(this);
        bloque.lis().procesa(this);
        System.out.println("}");
    }
    public void imprime(Camp camp) {
        camp.tipo().procesa(this);
        iden(camp.iden()).procesa(this);
    }


    // 11. LCamp
    public void imprime(Un_camp un_camp) {
        un_camp.campo().procesa(this);
    }
    public void imprime(Muchos_camp muchos_camp) {
        muchos_camp.lcs().procesa(this);
        System.out.println(",");
        muchos_camp.campo().procesa(this);
    }


    // 12. Ins
    public void imprime(Ins_asig ins_asig) {
        System.out.println("@");
        ins_asig.e().procesa(this);
        System.out.println(";");
    }
    public void imprime(Ins_if ins_if) {
        System.out.println("<if>");
        ins_if.e().procesa(this);
        ins_if.bloque().procesa(this);
    }
    public void imprime(Ins_if_else ins_if_else) {
        System.out.println("<if>");
        ins_if_else.e().procesa(this);
        ins_if_else.bloque().procesa(this);
        System.out.println("<else>");
        ins_if_else.bloque2().procesa(this);
    }
    public void imprime(Ins_while ins_while) {
        System.out.println("<while>");
        ins_while.e().procesa(this);
        ins_while.bloque().procesa(this);
    }
    public void imprime(Ins_read ins_read) {
        System.out.println("<read>");
        ins_read.e().procesa(this);
    }
    public void imprime(Ins_write ins_write) {
        System.out.println("<write>");
        ins_write.e().procesa(this);
    }
    public void imprime(Ins_new ins_new) {
        System.out.println("<new>");
        ins_new.e().procesa(this);
    }
    public void imprime(Ins_delete ins_delete) {
        System.out.println("<delete>");
        ins_delete.e().procesa(this);
    }
    public void imprime(Ins_nl ins_nl) {
        System.out.println("<nl>");
    }
    public void imprime(Ins_call ins_call) {
        System.out.println("<call>");
        iden(ins_call.id()).procesa(this);
        System.out.println("(");
        ins_call.pr().procesa(this);
        System.out.println(")");
    }
    public void imprime(Ins_bloque ins_bloque) {
        ins_bloque.bloque().procesa(this);
    }


    // 13. LInsOpt
    public void imprime(Si_Ins si_Ins) {
        si_Ins.ins().procesa(this);
    }
    public void imprime(No_Ins no_Ins) {

    }

    
    // 14. LIns
    public void imprime(Una_ins una_ins) {
        una_ins.ins().procesa(this);
    }
    public void imprime(Muchas_ins muchas_ins) {
        muchas_ins.li().procesa(this);
        System.out.println(";");
        muchas_ins.ins().procesa(this);
    }


    // 15. LPReal
    public void imprime(Si_preal si_preal) {
        si_preal.lpr().procesa(this);
    }
    public void imprime(No_preal no_preal) {

    }
    public void imprime(Un_PReal un_PReal) {
        un_PReal.e().procesa(this);
    }
    public void imprime(Muchos_preal muchos_preal) {
        muchos_preal.lpr().procesa(this);
        System.out.println(",");
        muchos_preal.e().procesa(this);
    }
}
