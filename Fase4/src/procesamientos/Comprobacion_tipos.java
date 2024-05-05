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
    private HashSet<String> tr = new HashSet<String>(); //TODO MIRAR ESTO PARA IDEN
    // set de parejas de T
    private HashSet<Par> st = new HashSet<Par>();

    public boolean ambos_ok(Tipo t1, Tipo t2){
        if(t1.t_ok() && t2.t_ok())
            return true;
        else return false;
    }
    public void procesa(Prog prog) {
        prog.bq().procesa(this);
        if()
        //lo del ok
    }

    public void procesa(Bloque bloque) {
        bloque.lds().procesa(this);
        bloque.lis().procesa(this);
        ambos_ok(bloque.lds().decs().dec().tipo(), bloque.lis().ins().ins().e().tipo());
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

        /* Version Oscar:
        if(ref.vinculo().is_dec_tipo()){
            if(!tr.contains(ref.id().toString())){
                tr.add(ref.id().toString());
                T t = ((Dec_tipo)ref.vinculo()).tipo();
                t.procesa(this);
                ref.set_t_ok(t.t_ok());
            }
            else {
                ref.set_t_ok(true);
            }
        }else{
            ref.set_t_ok(false);
            System.out.println("Error: no se puede hacer referencia a un tipo que no existe" + ". Fila: " + ref.id().fila() + ", col: " + ref.id().col());
        }
         */
    }
    //INSTRUCCIONES

    private Tipo refI(Tipo t){
        while(t.es_iden()){
            Iden r = ((Iden)t);
            Dec_tipo dec_tipo = (Dec_tipo) r.vinculo();
            t = dec_tipo.tipo();
        }
        return t;
    }
    public boolean son_compatibles(Tipo t0, Tipo t1){
        Par p = new Par(t0, t1);
        if(st.contains(p)){
            return true;
        }else{
            st.add(p);
        }
        t0 = refI(t0);
        t1 = refI(t1);
        if(t0.es_int() && t1.es_int()){
            return true;
        }else if(t0.es_real() && (t1.es_int() || t1.es_real())){
            return true;
        }else if(t0.es_bool() && t1.es_bool()){
            return true;
        }else if(t0.es_string() && t1.es_string()){
            return true;
        }else if(t0.es_array() && t1.es_array()){
            Array a0 = (Array) t0;
            Array a1 = (Array) t1;
            if(a0.tam() == a1.tam() && son_compatibles(a0.tipo(), a1.tipo())){
                return true;
            }
        }
        else if(t0.es_struct() && t1.es_struct()){
            Struct r0 = (Struct) t0;
            Struct r1 = (Struct) t1;
            if(campos_compatibles(r0.lcamp(), r1.lcamp())){
                return true;
            }
        }else if(t0.es_puntero() && t1.es_puntero()){
            Puntero p0 = (Puntero) t0;
            Puntero p1 = (Puntero) t1;
            if(son_compatibles(p0.tipo(), p1.tipo())){
                return true;
            }
        }else if(t0.es_puntero() && t1.is_null()){
            return true;}
        st.remove(p);
        return false;
    }


    public boolean campos_compatibles(LCamp lc1, LCamp lc2){
        if(lc1.es_un_campo() && lc2.es_un_campo()){
            return son_compatibles(lc1.campo().tipo(), lc2.campo().tipo());
        }else if(lc1.es_un_campo() && lc2.es_un_campo()){
            return false;
        }else if(lc1.es_muchos_campos() && lc2.es_muchos_campos()){
            return false;
        }else if(lc1.es_muchos_campos() && lc2.es_muchos_campos()){
            return campos_compatibles(lc1.lcs(), lc2.lcs()) &&
                    son_compatibles(lc1.campo().tipo(), lc2.campo().tipo());
        }
        return false;

    }

    public boolean es_desig(Exp e){
        if(e.vinculo() != null && (e.vinculo().is_dec_var() || e.vinculo().is_parf_valor()
                || e.vinculo().is_parf_ref() || e.vinculo().is_dec_proc())){
            return true;
        }
        else if(e.prioridad() == 6) { //Prioridad 5 para los accesos.
            return true;
        }
        return false;
    }
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
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_bool()){
            ins.bloque().procesa(this);
            //si ok entonces t_ok = true, else error
        }
        /*
        else{
            if(it.e().tipo().t_ok()){
                System.out.println("Error: la expresion " + it.e().toString() + " no es booleana. Fila: "
                        + it.e().localizador().fila() + ", col: " + it.e().localizador().col());
            }
            it.set_t_ok(false);
        }
         */
    }

    public void procesa(Ins_if_else ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_bool()){
            ins.bloque().procesa(this);
            ins.bloque2().procesa(this);
            //si ok entonces t_ok = true, else error
        }
       //else error
    }

    public void procesa(Ins_while ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_bool()){
            ins.bloque().procesa(this);
            //si ok entonces t_ok = true, else error
        }
        /*
        else{
            if(it.e().tipo().t_ok()){
                System.out.println("Error: la expresion " + it.e().toString() + " no es booleana. Fila: "
                        + it.e().localizador().fila() + ", col: " + it.e().localizador().col());
            }
            it.set_t_ok(false);
        }
         */
    }

    public void procesa(Ins_read ins) {
        ins.e().procesa(this);
        if((refI(ins.e().tipo()).es_int() || refI(ins.e().tipo()).es_real() || refI(ins.e().tipo()).es_string())
                && es_desig(ins.e())){
            //si ok entonces t_ok = true, else error
        }
    }


    public void procesa(Ins_write ins) {
        ins.e().procesa(this);
        if((refI(ins.e().tipo()).es_int() || refI(ins.e().tipo()).es_real() || refI(ins.e().tipo()).es_bool()
                || refI(ins.e().tipo()).es_string())){
            //si ok entonces t_ok = true, else error
        }
    }

    public void procesa(Ins_new ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_puntero()){
            //si ok entonces t_ok = true, else error
        }//else error
    }

    public void procesa(Ins_delete ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_puntero()){
            //si ok entonces t_ok = true, else error
        }//else error
    }

    public void procesa(Ins_nl ins) {
        //ok = true
    }


    public void procesa(Ins_call ins) {
        //TODO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    }

    public void procesa(Ins_bloque ins) {
        ins.bloque().procesa(this);
        //si ok entonces ok, else error
    }

    /* TODO PREGUNTAR SI HACEN FALTA, si lo hacen seria comprobando es_una_ins -> procesa una_ins ...
    public void procesa(LInsOpt ins) {

    }
    public void procesa(LDecsOpt ldecs) {

    }
    */

    public void procesa(Si_preal lpreal) {
        lpreal.lpr().procesa(this);
        //$.tipo = lpreal.lpr().t_ok();
    }

    public void procesa(No_preal lpreal) {
        //$.tipo = ok
    }

    public void procesa(Muchos_preal lpreal) {
        lpreal.lpr().procesa(this);
        lpreal.e().procesa(this);
    }

    public void procesa(Un_PReal exp) {
        exp.e().procesa(this);
    }

    public void procesa(Exp_Iden exp) {

        if(exp.vinculo().is_dec_var()){
            exp.set_tipo(((Dec_var)exp.vinculo()).tipo());
        }else if(exp.vinculo().is_parf_valor()){
            exp.set_tipo(((PFnoref)exp.vinculo()).tipo());
        }else if(exp.vinculo().is_parf_ref()){
            exp.set_tipo(((PFref)exp.vinculo()).tipo());
        }
    }


/*
    //TODO PREGUNTARLE A LUCIA SI ESTO DEBERIA DE PONERLO AQUI
    public void procesa(Exp_lit_ent exp) {

    }

    public void procesa(Exp_lit_real exp) {

    }

    public void procesa(Exp_lit_cadena exp) {

    }

    public void procesa(Exp_lit_BoolTrue exp) {

    }

    public void procesa(Exp_lit_BoolFalse exp) {

    }


*/
    public void procesa(Exp_null exp) {
        exp.set_tipo(new Null_T());
    }
    public void procesa(Mayor exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }

    public void procesa(Menor exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    public void procesa(MayorIg exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }

    public void procesa(MenorIg exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    private Tipo tipo_relacional(Tipo t0, Tipo t1){
        if((refI(t0).es_int() || refI(t0).es_real()) && (refI(t1).es_int() || refI(t1).es_real())){
            return new Lit_bool();
        }else if(refI(t0).es_bool() && refI(t1).es_bool()){
            return new Lit_bool();
        }else if(refI(t0).es_string() && refI(t1).es_string()){
            return new Lit_bool();
        }else{
            return new Error_();
        }
    }

    public void procesa(Igual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional2(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }

    public void procesa(Desigual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional2(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    private Tipo tipo_relacional2(Tipo t0, Tipo t1){
        if((refI(t0).es_int() || refI(t0).es_real()) && (refI(t1).es_int() || refI(t1).es_real())){
            return new Lit_bool();
        }else if(refI(t0).es_bool() && refI(t1).es_bool()){
            return new Lit_bool();
        }else if(refI(t0).es_string() && refI(t1).es_string()){
            return new Lit_bool();
        }else if(refI(t0).es_puntero() && refI(t1).es_puntero()){
            return new Lit_bool();
        }else if((refI(t0).is_null() && refI(t1).es_puntero()) || (refI(t0).es_puntero() && refI(t1).is_null())){
            return new Lit_bool();
        }else if(refI(t0).is_null() && refI(t1).is_null()){
            return new Lit_bool();
        }else{
            return new Error_();
        }
    }

    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }
    private Tipo tipo_binat(Tipo t0, Tipo t1){
        if(refI(t0).es_int() && refI(t1).es_int()){
            return new Lit_ent();
        }else if((refI(t0).es_int() || refI(t0).es_real()) && (refI(t1).es_int() || refI(t1).es_real())){
            return new Lit_real();
        }else{
            return new Error_();
        }
    }



    public void procesa(AccesoArray exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if(refI(exp.opnd0().tipo()).es_array() && (refI(exp.opnd1().tipo()).es_int())) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
        }
    }

    //TODO preguntar si seria exp.set_tipo o exp.opnd0.set_tipo
    public void procesa(AccesoPuntero exp) {
        exp.opnd0().procesa(this);
        if(refI(exp.opnd0().tipo()).es_puntero()) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
        }

    }

    public void procesa(AccesoCampo exp) {
        exp.opnd0().procesa(this);

        if(refI(exp.opnd0().tipo()).es_struct()){
            exp.set_tipo(tipo_de(exp.opnd0(), exp.iden()));
        }
        else{
            exp.set_tipo(new Error_());
        }

    }
    private Tipo tipo_de(LCamp lcs, String c){
        while(lcs.es_muchos_campos()){
            Camp campo = lcs.campo();
            if(c.equals(campo.iden().toString())){
                return campo.tipo();
            }
            lcs = lcs.lcs();
        }
        Camp campo = lcs.campo();
        if(c.equals(campo.iden().toString())){
            return campo.tipo();
        }
        return new Error_();
    }

    public void procesa(And exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_logico(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }

    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_logico(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }

    private Tipo tipo_logico(Tipo t0, Tipo t1){
        if(refI(t0).es_bool() && refI(t1).es_bool()){
            return new Lit_bool();
        }else{
            return new Error_();
        }
    }

    public void procesa(Mod exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if(refI(exp.opnd0().tipo()).es_int() && refI(exp.opnd1().tipo()).es_int()){
             exp.set_tipo(new Lit_ent());
        }else{
            exp.set_tipo(new Error_());
        }
    }
    public void procesa(Asig exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_logico(exp.opnd0().tipo(), exp.opnd1().tipo()));
    }


    public void procesa(Neg exp) {
        exp.opnd0().procesa(this);
        if (refI(exp.opnd0().tipo()).es_int() || refI(exp.opnd0().tipo()).es_real()) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
        }
    }
    public void procesa(Not exp) {
        exp.opnd0().procesa(this);
        if (refI(exp.opnd0().tipo()).es_bool()) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
        }
        }




}
