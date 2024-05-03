package procesamientos;

import java.util.HashSet;

import asint.Procesamiento;
import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny;
import asint.SintaxisAbstractaTiny.*;

// CLASE de parejas de T
class Par {
    private Tipo t1;
    private Tipo t2;
    public Par(Tipo t1, Tipo t2){
        this.t1 = t1;
        this.t2 = t2;
    }
    public Tipo t1(){
        return t1;
    }
    public Tipo t2(){
        return t2;
    }
    public boolean equals(Object p){
        return t1.equals(((Par)p).t1()) && t2.equals(((Par)p).t2());
    }
    public int hashCode() {
        return t1.hashCode() + t2.hashCode();
    }
}
public class Comprobacion_tipos extends ProcesamientoDef {
    private HashSet<String> tn = new HashSet<String>();
    private HashSet<String> tr = new HashSet<String>();
    // set de parejas de T
    private HashSet<Par> st = new HashSet<Par>();

    public boolean ambos_ok(Tipo t1, Tipo t2){
        return true;
    }
    public void procesa(Prog prog) {
        prog.bq().procesa(this);

        //lo del ok
    }

    public void procesa(Bloque bloque) {
        bloque.lds().procesa(this);
        bloque.lis().procesa(this);
        //bloque.ok = ambos_ok ()
    }

    public void procesa(Si_decs decs) {
       decs.decs().procesa(this);
        //$.tipo = decs.dec.t_ok();
    }
    public void procesa(No_decs decs) {
        //$.tipo = ok
    }
    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        decs.dec().procesa(this);
        //ambos_ok()
    }

    public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
        //$.tipo = decs.dec.t_ok();
    }

    public void procesa(Dec_var dec) {
        dec.tipo().procesa(this);
        //ok
    }

    public void procesa(Dec_tipo dec) {
        dec.tipo().procesa(this);
    }

    public void procesa(Dec_proc dec) {
        dec.pf().procesa(this);
        dec.bq().procesa(this);
        //ambos_ok
    }

    public void procesa(Si_pforms pforms) {
        pforms.pforms().procesa(this);
        //$.tipo = pforms.t_ok()
    }

    public void procesa(No_pforms pforms) {
        //ok
    }

    public void procesa(Muchos_pforms pforms) {
        pforms.pforms().procesa(this);
        pforms.pform().procesa(this);
        //ambos_ok
    }

    public void procesa(Un_pform pform) {
        pform.pform().procesa(this);
        //t_ok
    }
    public void procesa(PFref pform) {
        pform.t().procesa(this);
    }

    public void procesa(PFnoref pform) {
        pform.t().procesa(this);
    }

//TIPOS

    public void procesa(Lit_ent t) {
        //ok
    }
    public void procesa(Lit_real t) {
        //ok
    }

    public void procesa(Lit_bool t) {
        //ok
    }

    public void procesa(Lit_string t) {
        //ok
    }
    public void procesa(Array t) {
        if(t.num() < 0) {
            //error
        }
        else{
            t.tipo().procesa(this);
            //t_ok
        }
    }

    public void procesa(Puntero t) {
        t.tipo().procesa(this);
        //t_ok
    }

    public void procesa(Struct t) {
        if(hayRepetidos(t.lcamp())){
            //error
        }
        else{
            t.lcamp().procesa(this);
            //t_ok
        }
    }

    private boolean hayRepetidos(LCamp lcamp) {
        return true; //TODO
    }

    public void procesa(Camp campo) {
        campo.tipo().procesa(this);
    }

    public void procesa(Muchos_camp camps) {
        camps.lcs().procesa(this);
        camps.campo().procesa(this);
        //ambosok
    }

    public void procesa(Un_camp camp) {
        camp.campo().procesa(this);
        //t_ok()
    }
    public void procesa(Iden t) {
        //if vinculo = dec_tipo
        t.tipo().procesa(this);
        //t_ok()
        //else error
    }
    //INSTRUCCIONES

    public void procesa(Si_Ins ins) {
        ins.ins().li().procesa(this);
        //t_ok()
    }

    public void procesa(No_Ins ins) {
        //ok
    }

    public void procesa(Una_ins ins) {
        ins.ins().procesa(this);
        //t_ok()
    }

    public void procesa(Muchas_ins ins) {
        ins.li().procesa(this);
        ins.ins().procesa(this);
        //ambos_ok
    }

    public void procesa(Ins_asig ins) {
        ins.e().procesa(this);
        //if(ins.e().t_ok())
        //t_ok()
        //else error
    }

    public void procesa(Ins_if ins) {

    }

    public void procesa(Ins_if_else ins) {

    }

    public void procesa(Ins_while ins) {

    }

    public void procesa(Ins_read ins) {

    }

    public void procesa(Ins_write ins) {

    }

    public void procesa(Ins_new ins) {

    }

    public void procesa(Ins_delete ins) {

    }

    public void procesa(Ins_nl ins) {

    }


    public void procesa(Ins_call ins) {

    }

    public void procesa(Ins_bloque ins) {

    }

    public void procesa(LInsOpt ins) {

    }


    public void procesa(Suma exp) {}
    public void procesa(Resta exp) {}
    public void procesa(Mul exp) {}
    public void procesa(Div exp) {}

    public void procesa(Exp_lit_ent exp) {

    }

    public void procesa(Exp_lit_real exp) {

    }

    public void procesa(Si_preal lpreal) {

    }

    public void procesa(No_preal lpreal) {

    }

    public void procesa(Muchos_preal lpreal) {

    }

    public void procesa(Un_PReal exp) {

    }

    public void procesa(Exp_Iden exp) {

    }

    public void procesa(Exp_lit_cadena exp) {

    }

    public void procesa(Exp_lit_BoolTrue exp) {

    }

    public void procesa(Exp_lit_BoolFalse exp) {

    }


    public void procesa(Exp_null exp) {

    }

    public void procesa(AccesoArray exp) {

    }

    public void procesa(AccesoPuntero exp) {

    }

    public void procesa(AccesoCampo exp) {

    }

    public void procesa(And exp) {

    }

    public void procesa(Or exp) {

    }

    public void procesa(Mod exp) {}
    public void procesa(Asig exp) {}

    public void procesa(Mayor exp) {}

    public void procesa(Menor exp) {}
    public void procesa(MayorIg exp) {}

    public void procesa(MenorIg exp) {}

    public void procesa(Igual exp) {}

    public void procesa(Desigual exp) {}

    public void procesa(Neg exp) {}

    public void procesa(Not exp) { }

    public void procesa(LDecsOpt ldecs) {
    }

}
