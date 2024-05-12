package procesamientos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import asint.ProcesamientoDef;
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
    private MensajesError ms;
    private MensajesError ms2;//Para pretipado

    public boolean getMenTipado(){return ms.getHayError();}
    public boolean getMenPreTipado(){return ms2.getHayError();}
    public Tipo ambos_ok(Tipo t1, Tipo t2){
        if(t1.t_ok() && t2.t_ok())
            return new Ok();
        else {
            return new Error_();
        }
    }
    public void procesa(Prog prog) {
        ms = new MensajesError("tipado");
        ms2 = new MensajesError("pre-tipado");
        prog.bq().procesa(this);
        prog.set_tipo(prog.bq().tipo());
        if(ms2.getHayError() ){
            ms2.getErrores();
        }
        else if(ms.getHayError()){
            ms.getErrores();
        }
    }

    public void procesa(Bloque bloque) {
        bloque.lds().procesa(this);
        bloque.lis().procesa(this);
        bloque.set_tipo(ambos_ok(bloque.lds().decs().dec().tipo(), bloque.lis().ins().ins().e().tipo()));
    }

    public void procesa(Si_decs decs) {
        decs.decs().procesa(this);
        decs.set_tipo(decs.decs().tipo());
    }
    public void procesa(No_decs decs) {
        decs.set_tipo(new Ok());
    }
    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        decs.dec().procesa(this);
        decs.set_tipo(ambos_ok(decs.ldecs().tipo(), decs.dec().tipo()));
    }

    public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
        decs.set_tipo(decs.dec().tipo());
    }

    public void procesa(Dec_var dec) {
        dec.tipo().procesa(this);
        dec.set_tipo(dec.tipo());
    }

    public void procesa(Dec_tipo dec) {
        dec.tipo().procesa(this);
        dec.set_tipo(dec.tipo());
    }

    public void procesa(Dec_proc dec) {
        dec.pf().procesa(this);
        dec.bq().procesa(this);
        dec.set_tipo(ambos_ok(dec.pf().tipo(), dec.bq().tipo()));
    }

    public void procesa(Si_pforms pforms) {
        pforms.pforms().procesa(this);
        pforms.set_tipo(pforms.pforms().tipo());
    }

    public void procesa(No_pforms pforms) {
        pforms.set_tipo(new Ok());
    }

    public void procesa(Muchos_pforms pforms) {
        pforms.pforms().procesa(this);
        pforms.pform().procesa(this);
        pforms.set_tipo(ambos_ok(pforms.pforms().tipo(), pforms.pform().tipo()));
    }

    public void procesa(Un_pform pform) {
        pform.pform().procesa(this);
        pform.set_tipo(pform.pform().tipo());
    }
    public void procesa(PFref pform) {
        pform.t().procesa(this);
        pform.set_tipo(pform.t().tipo());
    }

    public void procesa(PFnoref pform) {
        pform.t().procesa(this);
        pform.set_tipo(pform.t().tipo());
    }

//TIPOS

    public void procesa(Lit_ent t) {
        t.set_tipo(new Ok());
    }
    public void procesa(Lit_real t) {
        t.set_tipo(new Ok());
    }

    public void procesa(Lit_bool t) {
        t.set_tipo(new Ok());
    }

    public void procesa(Lit_string t) {
        t.set_tipo(new Ok());
    }
    public void procesa(Array t) {
        if(t.num() < 0) {
            t.set_tipo(new Error_());
            ms2.addError(t.leeFila(),t.leeCol());
        }
        else{
            t.tipo().procesa(this);
            t.set_tipo(t.tipo());
        }
    }

    public void procesa(Puntero t) {
        t.tipo().procesa(this);
        t.set_tipo(t.tipo());
    }

    public void procesa(Struct t) {
        List<Camp> campos = new ArrayList<Camp>();
        if(hayRepetidos(t.lcamp(), campos)){
            t.set_tipo(new Error_());
            ms2.addError(t.leeFila(), t.leeCol());
        }
        else{
            t.lcamp().procesa(this);
            t.set_tipo(t.tipo());
        }
    }

    private boolean hayRepetidos(LCamp lcamp, List<Camp> campos) { //TODO COMPROBAR nuevo!
        if(!campos.isEmpty()){
            for(int i = 0; i < campos.size(); i++){
                if(lcamp.campo() == campos.get(i))
                    return false;
            }

        }
        if(lcamp.es_muchos_campos()){
            campos.add(lcamp.campo());
            return hayRepetidos(lcamp.lcs(), campos);
        }
        else{
            return true;
        }
    }

    public void procesa(Camp campo) {
        campo.tipo().procesa(this);
        campo.set_tipo(campo.tipo());
    }

    public void procesa(Muchos_camp camps) {
        camps.lcs().procesa(this);
        camps.campo().procesa(this);
        camps.set_tipo(ambos_ok(camps.lcs().tipo(), camps.campo().tipo()));
    }

    public void procesa(Un_camp camp) {
        camp.campo().procesa(this);
        camp.set_tipo(camp.tipo());
    }
    public void procesa(Iden t) {
        if(t.getVinculo().equals(Dec_tipo.class)) { //asi o con un .es_dec_tipo
            t.tipo().procesa(this);
            t.set_tipo(t.tipo());
        }
        else {
            t.set_tipo(new Error_());
            ms.addError(t.leeFila(),t.leeCol());
        }
    }
    //INSTRUCCIONES

    private Tipo refI(Tipo t){
        while(t.es_iden()){
            Iden r = ((Iden)t);
            Dec_tipo dec_tipo = (Dec_tipo) r.getVinculo();
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
            if(a0.getTam() == a1.getTam() && son_compatibles(a0.tipo(), a1.tipo())){
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
        }else if(t0.es_puntero() && t1.es_null()){
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
        if(e.es_iden()){
            return true;
        }
        else if(e.prioridad() == 6) { //Prioridad 5 para los accesos.
            return true;
        }
        return false;
    }
    public void procesa(Si_Ins ins) {
        ins.ins().li().procesa(this);
        ins.set_tipo(ins.ins().tipo());
    }

    public void procesa(No_Ins ins) {
        ins.set_tipo(new Ok());
    }

    public void procesa(Una_ins ins) {
        ins.ins().procesa(this);
        ins.set_tipo(ins.ins().tipo());
    }

    public void procesa(Muchas_ins ins) {
        ins.li().procesa(this);
        ins.ins().procesa(this);
        ins.set_tipo(ambos_ok(ins.li().tipo(),ins.ins().tipo()));
    }

    public void procesa(Ins_asig ins) {
        ins.e().procesa(this);
        if(ins.e().tipo().equals(new Ok())){
            ins.set_tipo(new Ok());
        }
        else{
            ins.e().set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }
    }

    public void procesa(Ins_if ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_bool()){
            ins.bloque().procesa(this);
            if(ins.bloque().tipo().equals(new Ok())){
                ins.set_tipo(new Ok());
            }
            else{
                ins.set_tipo(new Error_());
                ms.addError(ins.leeFila(),ins.leeCol());
            }
        }
        else{
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }

    }

    public void procesa(Ins_if_else ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_bool()){
            ins.bloque().procesa(this);
            ins.bloque2().procesa(this);
            if(ambos_ok(ins.bloque().tipo(), ins.bloque2().tipo()).equals(new Ok())){
                ins.set_tipo(new Ok());
            }
            else {
                ins.set_tipo(new Error_());
                ms.addError(ins.leeFila(), ins.leeCol());
            }
        }
        else{
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
            }
    }

    public void procesa(Ins_while ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_bool()){
            ins.bloque().procesa(this);
            if(ins.bloque().tipo().equals(new Ok()))
                ins.set_tipo(new Ok());
            else {
                ins.set_tipo(new Error_());
                ms.addError(ins.leeFila(),ins.leeCol());
            }
        }
        else {
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }

    }

    public void procesa(Ins_read ins) {
        ins.e().procesa(this);
        if((refI(ins.e().tipo()).es_int() || refI(ins.e().tipo()).es_real() || refI(ins.e().tipo()).es_string())
                && es_desig(ins.e())) {
            ins.set_tipo(new Ok());
        }
        else {
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }
    }


    public void procesa(Ins_write ins) {
        ins.e().procesa(this);
        if((refI(ins.e().tipo()).es_int() || refI(ins.e().tipo()).es_real() || refI(ins.e().tipo()).es_bool()
                || refI(ins.e().tipo()).es_string())){
            ins.set_tipo(new Ok());
        }
        else {
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }
    }

    public void procesa(Ins_new ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_puntero()){
            ins.set_tipo(new Ok());
        }
        else {
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }
    }

    public void procesa(Ins_delete ins) {
        ins.e().procesa(this);
        if(refI(ins.e().tipo()).es_puntero()){
            ins.set_tipo(new Ok());
        }
        else {
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }
    }

    public void procesa(Ins_nl ins) {
        ins.set_tipo(new Ok());
    }


    public void procesa(Ins_call ins) { //TODO yo me mato con el call asi lo digo
        ins.pr().procesa(this);
        Dec_proc dec = (Dec_proc) ins.getVinculo();

        ins.pr().set_tipo(chequeo_params(dec.pf(),ins.pr()));

        if(ins.pr().tipo().equals(new Ok())){
            ins.set_tipo(new Ok());
        }
        else{
            ins.set_tipo(new Error_());
            ms.addError(ins.leeFila(),ins.leeCol());
        }
    }
    public Tipo chequeo_params(No_pforms pf, No_preal pr){ return new Ok();}

    public Tipo chequeo_params(Un_pform pf, Un_PReal pr){
        if(pf.es_parf_ref()) {
            PFref pf2 = (PFref) pf.pform();
            return chequeo_params(pf2, pr.e());
        } else if (pf.es_parf_noRef()) {
            PFnoref pf2 = (PFnoref) pf.pform();
            return chequeo_params(pf2, pr.e());
        }
        else return new Error_();
    }

    public Tipo chequeo_params(Muchos_pforms pf, Muchos_preal pr){
        if(pf.es_parf_ref()) {
            PFref pf2 = (PFref) pf.pform();
            Muchos_pforms pf3 = (Muchos_pforms) pf.pforms();
            Muchos_preal pr2 = (Muchos_preal) pr.lpr();
            return ambos_ok(chequeo_params(pf3, pr2), chequeo_params(pf2, pr.e()));
        } else if (pf.es_parf_noRef()) {
            PFnoref pf2 = (PFnoref) pf.pform();
            Muchos_pforms pf3 = (Muchos_pforms) pf.pforms();
            Muchos_preal pr2 = (Muchos_preal) pr.lpr();
            return ambos_ok(chequeo_params(pf3, pr2), chequeo_params(pf2, pr.e()));
        }
        else return new Error_();


    }
    public Tipo chequeo_params(PFnoref pf, Exp e){
       e.procesa(this);
       pf.procesa(this);
       if(son_compatibles(pf.t(), e.tipo())){
            return new Ok();
        }
        else{
            return new Error_();
        }
    }
    public Tipo chequeo_params(PFref pf, Exp e){
        e.procesa(this);
        pf.procesa(this);
        if(son_compatibles(pf.t(), e.tipo()) && es_desig(e)){
            return new Ok();
        }
        else{
            return new Error_();
        }
    }

    public void procesa(Ins_bloque ins) {
        ins.bloque().procesa(this);
        ins.set_tipo(ins.bloque().tipo());
    }


    public void procesa(Si_preal lpreal) {
        lpreal.lpr().procesa(this);
        lpreal.set_tipo(lpreal.lpr().tipo());
    }

    public void procesa(No_preal lpreal) {
        lpreal.set_tipo(new Ok());
    }

    public void procesa(Muchos_preal lpreal) {
        lpreal.lpr().procesa(this);
        lpreal.e().procesa(this);
        lpreal.set_tipo(ambos_ok(lpreal.lpr().tipo(),lpreal.e().tipo()));
    }

    public void procesa(Un_PReal exp) {
        exp.e().procesa(this);
        exp.set_tipo(exp.e().tipo());
    }

    public void procesa(Exp_Iden exp) {
        if(exp.getVinculo().es_dec_var()){
            exp.set_tipo((exp.getVinculo().tipo()));
        }else if(exp.getVinculo().es_parf_noRef()){
            exp.set_tipo((exp.getVinculo()).tipo());
        }else if(exp.getVinculo().es_parf_ref()){
            exp.set_tipo((exp.getVinculo()).tipo());
        }
    }

    //TODO COMPROBAR nuevo!
    public void procesa(Exp_lit_ent exp) {
        exp.set_tipo(new Lit_ent());
    }

    public void procesa(Exp_lit_real exp) {
        exp.set_tipo(new Lit_real());
    }

    public void procesa(Exp_lit_cadena exp) {
        exp.set_tipo(new Lit_string());
    }

    public void procesa(Exp_lit_BoolTrue exp) {
        exp.set_tipo(new Lit_bool());
    }

    public void procesa(Exp_lit_BoolFalse exp) {
        exp.set_tipo(new Lit_bool());
    }

    public void procesa(Exp_null exp) {
        exp.set_tipo(new Null_T());
    }
    public void procesa(Mayor exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }

    public void procesa(Menor exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }
    public void procesa(MayorIg exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }

    public void procesa(MenorIg exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
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
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }

    public void procesa(Desigual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_relacional2(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
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
        }else if((refI(t0).es_null() && refI(t1).es_puntero()) || (refI(t0).es_puntero() && refI(t1).es_null())){
            return new Lit_bool();
        }else if(refI(t0).es_null() && refI(t1).es_null()){
            return new Lit_bool();
        }else{
            return new Error_();
        }
    }

    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }
    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }
    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }
    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_binat(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
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
            ms.addError(exp.leeFila(),exp.leeCol());

        }
    }

    public void procesa(AccesoPuntero exp) {
        exp.opnd0().procesa(this);
        if(refI(exp.opnd0().tipo()).es_puntero()) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
            ms.addError(exp.leeFila(),exp.leeCol());
        }

    }

    public void procesa(AccesoCampo exp) {
        exp.opnd0().procesa(this);

        if(refI(exp.opnd0().tipo()).es_struct()){
            //(probablemente el toString este mal)
            exp.set_tipo(tipo_de(refI(exp.opnd0().tipo()).lcamp(), exp.iden().toString()));
        }
        else{
            exp.set_tipo(new Error_());
        }
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
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
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }

    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        exp.set_tipo(tipo_logico(exp.opnd0().tipo(), exp.opnd1().tipo()));
        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
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
            ms.addError(exp.leeFila(),exp.leeCol());

        }
    }
    public void procesa(Asig exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if(es_desig(exp.opnd0())){
            if(son_compatibles(exp.opnd0().tipo(),exp.opnd1().tipo())) {
                exp.set_tipo(new Ok());
            }
            else
                exp.set_tipo(new Error_());
        }
        else
            exp.set_tipo(new Error_());

        if(exp.tipo().equals(new Error_())){
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }


    public void procesa(Neg exp) {
        exp.opnd0().procesa(this);
        if (refI(exp.opnd0().tipo()).es_int() || refI(exp.opnd0().tipo()).es_real()) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }
    public void procesa(Not exp) {
        exp.opnd0().procesa(this);
        if (refI(exp.opnd0().tipo()).es_bool()) {
            exp.set_tipo(exp.opnd0().tipo());
        }
        else{
            exp.set_tipo(new Error_());
            ms.addError(exp.leeFila(),exp.leeCol());
        }
    }
}
