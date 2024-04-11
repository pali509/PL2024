

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
        System.out.println(iden.id +"$f:"+iden.leeFila()+",c:"+iden.leeCol()+"$");
    }
    public void imprime(Array array) {
        array.tipo().imprime(this);
        System.out.print("[");
        array.num().imprime();
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
        struct.lcamp().imprime();
        System.out.println("}");
    }


    // 4. Exp
    public void imprime(Exp_lit_ent exp_lit_ent) {
        System.out.println(exp_lit_ent.num() + "$f:"+exp_lit_ent.leeFila()+",c:"+exp_lit_ent.leeCol()+"$" );
    }
    public void imprime(Exp_lit_real exp_lit_real) {
        System.out.println(exp_lit_real.num() + "$f:"+exp_lit_ent.leeFila()+",c:"+exp_lit_ent.leeCol()+"$" );
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
    public void imprime(PFormOpt pformOpt) {
        pformOpt.pforms().imprime(this);
    }


    // 6. PFormOpt
    public void imprime(Si_pforms si_pforms) {
        si_pforms.pforms().imprime(this);
    }
    public void imprime(No_pforms no_pforms) {
        // TODO: Se deja vacio?
    }
    public void imprime(Un_pform un_pform) {
        un_pform.pform().imprime(this);
    }
    public void imprime(Muchos_pforms muchos_pforms) {
        muchos_pforms.pforms().imprime(this);
        System.out.println(",");
        muchos_pforms.pform().imprime(this);
    }


    // 7. Dec
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


    // 8. LDecsOpt
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
        camp.tipo().imprime();
        camp.iden().imprime();
    }
    public void imprime(Camp camp) {
        camp.tipo().imprime();
        camp.iden().imprime();
    }


    // 11. LCamp
    public void imprime(Un_camp un_camp) {
        un_camp.campo().imprime();
    }
    public void imprime(Muchos_camp muchos_camp) {
        muchos_camp.lcs().imprime();
        System.out.println(",");
        muchos_camp.campo().imprime();
    }


    // 12. Ins







    public void imprime(Si_decs sidecs) {
        System.out.print("donde");
        sidecs.decs().imprime(this);
        System.out.println("&&");
    }


    public void imprime(Muchas_decs muchas) {
        muchas.ldecs().imprime(this);
        System.out.println(";");
        muchas.dec().imprime(this);
    }

    public void imprime(Una_dec una) {
        una.dec().procesa(this);
    }

    public void imprime(Dec_var var) {
        var.tipo().imprime(this);
        System.out.println(var.iden());
    }


    public void imprime(Dec_tipo tipo) {
        System.out.println("<type>");
        tipo.tipo().imprime(this);
        System.out.println(tipo.iden());
    }


    public void imprime(Dec_proc proc) {
        System.out.println("<proc>");
        System.out.println(proc.iden());
        System.out.println("(");
        proc.pf().imprime(this);
        System.out.println(")");
        proc.bq().imprime(this);
    }


    public void imprime(Si_pforms sipform) {
        sipform.pforms().imprime(this);
    }


    public void imprime(No_pforms nopform) {

    }


    public void imprime(Muchos_pforms muchos) {
        muchos.pforms().imprime(this);
        System.out.println(",");
        muchos.pform().imprime(this);
    }


    public void imprime(Un_pform uno) {
        uno.pform().imprime(this);
    }


    public void imprime(Pform pform) {
        pform.t().imprime(this);
        System.out.println(pform.st());
    }





    public void imprime(Iden iden) {
        System.out.println(iden.iden());
    }

    public void imprime(Struct struct) {
        System.out.println("<struct>");
        System.out.println("{");
        struct.lcamp().imprime(this);
        System.out.println("}");
    }

    public void imprime(Lit_ent ent) {
        System.out.println("<int>");
    }

    public void imprime(Lit_real real) {
        System.out.println("<real>");
    }

    public void imprime(Lit_bool bool) {
        System.out.println("<bool>");
    }

    public void imprime(Lit_string string) {
        System.out.println("<string>");
    }

    public void imprime(Muchos_camp muchos) {
        muchos.lcs().imprime(this);
        System.out.println(",");
        muchos.campo().imprime(this);
    }

    public void imprime(Un_camp uno) {
        uno.campo().imprime(this);
    }

    public void imprime(Camp camp) {
        camp.tipo().imprime(this);
        System.out.imprime(camp.iden());
    }
}
