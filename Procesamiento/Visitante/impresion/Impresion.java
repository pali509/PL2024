package impresion;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaEval.Exp;
import asint.SintaxisAbstractaEval.Dec;
import asint.SintaxisAbstractaEval.Muchas_decs;
import asint.SintaxisAbstractaEval.Una_dec;
import asint.SintaxisAbstractaEval.Si_decs;
import asint.SintaxisAbstractaEval.Suma;
import asint.SintaxisAbstractaEval.Resta;
import asint.SintaxisAbstractaEval.Mul;
import asint.SintaxisAbstractaEval.Div;
import asint.SintaxisAbstractaEval.Lit_ent;
import asint.SintaxisAbstractaEval.Lit_real;
import asint.SintaxisAbstractaEval.Iden;
import asint.SintaxisAbstractaEval.Prog;

public class Impresion extends SintaxisAbstractaTiny {
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        opnd.imprime(this);
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }

    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.print(" "+op+" ");
        imprimeOpnd(opnd1,np1);
    }

    public void imprime(Prog prog) {
        System.out.println("evalua");
        System.out.print("  ");
        prog.bq().procesa(this);
        System.out.println("<EOF>");
    }

    public void imprime(Bloque bloque) {
        System.out.println("{");
        bloque.lds().imprime(this);
        bloque.lis().imprime(this);
        System.out.println("}");
    }

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

    public void imprime(Array array) {
        array.tipo().imprime(this);
        System.out.println("[" + array.iden() + "]");
    }

    public void imprime(Puntero puntero) {
        puntero.tipo().imprime(this);
        System.out.println("^");
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
