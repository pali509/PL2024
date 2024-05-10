package procesamientos;

import java.util.Stack;
import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;
import maquinaP.MaquinaP;

public class Etiquetado extends ProcesamientoDef {
    private MaquinaP mp;

    public Etiquetado(MaquinaP mp) {
        this.mp = mp;
    }

    private Stack<Dec_proc> procs = new Stack<Dec_proc>();
    private int etq;

    public void procesa(Prog p){
        etq = 0;
        p.bq().procesa(this);
    }

    public void procesa(Bloque b){
        // b.prim = etq;
        etq++;
        // b.sig = etq;
        b.lds().procesa(this);
        b.lis().procesa(this);
        while (!procs.isEmpty()) {
            Dec_proc proc = procs.pop();
            proc.procesa(this);
        }
    }

    public void procesa(Si_decs s){
        s.decs().procesa(this);
    }

    public void procesa(No_decs n){
        //NOOP
    }

    public void procesa(Muchas_decs m){
        m.ldecs().procesa(this);
        m.dec().procesa(this);
    }

    public void procesa(Una_dec u){
        u.dec().procesa(this);
    }

    public void procesa(Dec d){
        if(d.es_dec_proc()) {
            Dec_proc dnew = (Dec_proc) d;

            //TODO Es o eso o hacer otro recolecta para hacer mp.emit(apila)
            procs.push(dnew);
        }
    }

    public void procesa(Dec_proc dec_proc){
        dec_proc.bq().procesa(this);
    }

    public void procesa(Si_Ins s){
        s.ins().procesa(this);
    }

    public void procesa(No_Ins n){
        //NOOP
    }

    public void procesa(Muchas_ins m){
        // b.prim = etq;
        m.ins().procesa(this);
        m.li().procesa(this);
        // b.sig = etq;
    }

    public void procesa(Una_ins u){
        u.ins().procesa(this);
    }

    public void procesa(Ins_asig i){
        i.e().procesa(this);
    }

    public void procesa(Ins_if i){
        i.prim = etq;
        i.e().procesa(this);
        if(es_desig(i.e()))
            etq++;
        mp.emit(mp.ir_f(i.sig));
        i.bloque().procesa(this);
        i.sig = etq;
    }

    public void procesa(Ins_if_else i){
        i.prim = etq;
        i.e().procesa(this);
        if(es_desig(i.e()))
            etq++;
        mp.emit(mp.ir_f(i.bloque2().prim()));
        i.bloque().procesa(this);
        mp.emit(mp.ir_f(i.sig()));
        i.bloque2().procesa(this);
        i.sig = etq;
    }

    public void procesa(Ins_call i){
        i.e().procesa(this);
    }

    public void procesa(Ins_while i){
        i.e().procesa(this);
        i.bloque().procesa(this);
    }

    public void procesa(Ins_read i) {
        i.prim = etq;
        i.e().procesa(this);
        Tipo t = refI(i.e().tipo());
        if (t.es_int() || t.es_real() || t.es_string()) {
            etq++;
            mp.emit(mp.store(i.e().procesa(this), t));
        }
        i.sig = etq;
    }

    public void procesa (Ins_write i){
        i.prim = etq;
        i.e().procesa(this);
        i.sig = etq;
    }

    public void procesa (Ins_nl i){
        //NOOP
    }

    public void procesa (Ins_new i){
        i.prim = etq;
        i.e().procesa(this);
        i.sig = etq;
    }

    public void procesa (Ins_delete i){
        i.prim = etq;
        i.e().procesa(this);
        i.sig = etq;
    }

    public void procesa(Ins_bloque i){
        i.prim = etq;
        i.e().procesa(this);
        i.sig = etq;
    }
    public void procesa (Si_preal s){
        s.lpr().procesa(this);
    }

    public void procesa (No_preal n){
        //NOOP
    }

    public void procesa (Muchos_preal m){
        m.lpr().procesa(this);
        m.e().procesa(this);
    }

    public void procesa (Un_PReal u){
        u.e().procesa(this);
    }

    // ETIQUETADO EXPRESIONES
    public void procesa (Exp_lit_ent e){
        etq++;
    }

    public void procesa (Exp_lit_real e){
        etq++;
    }

    public void procesa (Exp_lit_cadena e){
        etq++;
    }

    public void procesa (Exp_Iden e){
        etq++;
    }

    public void procesa (Exp_lit_BoolTrue e){
        etq++;
    }

    public void procesa (Exp_lit_BoolFalse e){
        etq++;
    }

    public void procesa (Exp_null e){
        etq++;
    }

    public void procesa (Asig a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
        etq++;
        String t0 = a.opnd0().tipo().iden().toString();
        String t1 = a.opnd1().tipo().iden().toString();
        if(refI(a.opnd1().tipo()).es_int() && refI(a.opnd0().tipo()).es_real()) {
            if (es_desig(a.opnd1())) {
                etq++;
            }
        } else {
            if (es_desig(a.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Suma s){
        s.opnd0().procesa(this);
        s.opnd1().procesa(this);
        etq++;
        if ((refI(s.opnd0().tipo()).es_int() || refI(s.opnd0().tipo()).es_real()) &&
                (refI(s.opnd1().tipo()).es_int() || refI(s.opnd1().tipo()).es_real())) {
            if (es_desig(s.opnd0())) {
                etq++;
            }
            if (es_desig(s.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Resta r){
        r.opnd0().procesa(this);
        r.opnd1().procesa(this);
        etq++;
        if ((refI(r.opnd0().tipo()).es_int() || refI(r.opnd0().tipo()).es_real()) &&
                (refI(r.opnd1().tipo()).es_int() || refI(r.opnd1().tipo()).es_real())) {
            if (es_desig(r.opnd0())) {
                etq++;
            }
            if (es_desig(r.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Mul m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
        etq++;
        if ((refI(m.opnd0().tipo()).es_int() || refI(m.opnd0().tipo()).es_real()) &&
                (refI(m.opnd1().tipo()).es_int() || refI(m.opnd1().tipo()).es_real())) {
            if (es_desig(m.opnd0())) {
                etq++;
            }
            if (es_desig(m.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Div d){
        d.opnd0().procesa(this);
        d.opnd1().procesa(this);
        etq++;
        if ((refI(d.opnd0().tipo()).es_int() || refI(d.opnd0().tipo()).es_real()) &&
                (refI(d.opnd1().tipo()).es_int() || refI(d.opnd1().tipo()).es_real())) {
            if (es_desig(d.opnd0())) {
                etq++;
            }
            if (es_desig(d.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Mod m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
        etq++;
        if (refI(m.opnd0().tipo()).es_int() && refI(m.opnd1().tipo()).es_int()) {
            if (es_desig(m.opnd0())) {
                etq++;
            }
            if (es_desig(m.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (And a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
        etq++;
        if (refI(a.opnd0().tipo()).es_bool() && refI(a.opnd1().tipo()).es_bool()) {
            if (es_desig(a.opnd0())) {
                etq++;
            }
            if (es_desig(a.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Or o){
        o.opnd0().procesa(this);
        o.opnd1().procesa(this);
        etq++;
        if (refI(o.opnd0().tipo()).es_bool() && refI(o.opnd1().tipo()).es_bool()) {
            if (es_desig(o.opnd0())) {
                etq++;
            }
            if (es_desig(o.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Mayor ma){
        ma.opnd0().procesa(this);
        ma.opnd1().procesa(this);
        etq++;
        if ((refI(ma.opnd0().tipo()).es_int() || refI(ma.opnd0().tipo()).es_real()) &&
                (refI(ma.opnd1().tipo()).es_int() || refI(ma.opnd1().tipo()).es_real())) {
            if (es_desig(ma.opnd0())) {
                etq++;
            }
            if (es_desig(ma.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Menor me){
        me.opnd0().procesa(this);
        me.opnd1().procesa(this);
        etq++;
        if ((refI(me.opnd0().tipo()).es_int() || refI(me.opnd0().tipo()).es_real()) &&
                (refI(me.opnd1().tipo()).es_int() || refI(me.opnd1().tipo()).es_real())) {
            if (es_desig(me.opnd0())) {
                etq++;
            }
            if (es_desig(me.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (MayorIg mai){
        mai.opnd0().procesa(this);
        mai.opnd1().procesa(this);
        etq++;
        if ((refI(mai.opnd0().tipo()).es_int() || refI(mai.opnd0().tipo()).es_real()) &&
                (refI(mai.opnd1().tipo()).es_int() || refI(mai.opnd1().tipo()).es_real())) {
            if (es_desig(mai.opnd0())) {
                etq++;
            }
            if (es_desig(mai.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (MenorIg mei){
        mei.opnd0().procesa(this);
        mei.opnd1().procesa(this);
        etq++;
        if ((refI(mei.opnd0().tipo()).es_int() || refI(mei.opnd0().tipo()).es_real()) &&
                (refI(mei.opnd1().tipo()).es_int() || refI(mei.opnd1().tipo()).es_real())) {
            if (es_desig(mei.opnd0())) {
                etq++;
            }
            if (es_desig(mei.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Igual ig){
        ig.opnd0().procesa(this);
        ig.opnd1().procesa(this);
        etq++;
        if ((refI(ig.opnd0().tipo()).es_int() || refI(ig.opnd0().tipo()).es_real()) &&
                (refI(ig.opnd1().tipo()).es_int() || refI(ig.opnd1().tipo()).es_real())) {
            if (es_desig(ig.opnd0())) {
                etq++;
            }
            if (es_desig(ig.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Desigual de){
        de.opnd0().procesa(this);
        de.opnd1().procesa(this);
        etq++;
        String t0 = de.opnd0().tipo().iden().toString();
        String t1 = de.opnd1().tipo().iden().toString();
        if ((refI(de.opnd0().tipo()).es_int() || refI(de.opnd0().tipo()).es_real()) &&
                (refI(de.opnd1().tipo()).es_int() || refI(de.opnd1().tipo()).es_real())) {
            if (es_desig(de.opnd0())) {
                etq++;
            }
            if (es_desig(de.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa (Not no){
        no.opnd0().procesa(this);
        etq++;
        if (refI(no.opnd0().tipo()).es_int() || refI(no.opnd0().tipo()).es_real() {
            if (es_desig(no.opnd0())) {
                etq++;
            }
        }
    }

    public void procesa (Lit_bool l){
        //NOOP
    }

    public void procesa (Lit_ent l){
        //NOOP
    }

    public void procesa (Lit_string l){
        //NOOP
    }

    public void procesa (Lit_real l){
        //NOOP
    }

    public void procesa (Iden i){
        //NOOP
    }
    public void procesa (Struct s){
        s.lcamp().procesa(this);
    }
    public void procesa (Array a){
        a.tipo().procesa(this);
    }
    public void procesa (Puntero p){

    }

    //FUNCIONES AUX PARA EXP
    private Tipo refI(Tipo t){
        while (t.es_iden()) {
            Iden id = (Iden) t;
            Dec_tipo d = (Dec_tipo) id.getVinculo();
            t = d.tipo();
        }
        return t;
    }

    private boolean es_desig (Exp e){
        if (e.es_iden()) {
            return true;
        } else if (e.prioridad() == 6) { //Prioridad 5 para los accesos.
            return true;
        }
        return false;
    }

    private int desp (LCamp lcs, String c){
        while (lcs.es_muchos_campos()) {
            Camp campo = lcs.campo();
            if (c.equals(campo.iden().toString())) {
                return campo.get_desp();
            }
            lcs = lcs.lcs();
        }
        Camp campo = lcs.campo();
        return campo.get_desp();
    }
}