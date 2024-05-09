package procesamientos;

import java.util.Stack;
import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

public class Etiquetado extends ProcesamientoDef {

    private Stack<Dec_proc> procs = new Stack<Dec_proc>();
    private int etq;

    private boolean es_designador(Exp e){
        return true;
    }

    public void procesa(Prog p){
        etq = 0;
        p.bq().procesa(this);
    }

    public void procesa(Bloque b){
        // b.prim = etq;
        etq++;
        // b.sig = etq;
        recolecta_procs(b.lds());
        b.lis().procesa(this);
        while (!procs.isEmpty()) {
            Dec_proc proc = procs.pop();
            proc.procesa(this);
        }
    }

    public void recolecta_procs(LDecsOpt d){
        if(d.es_si_decs()) {
            Si_decs dnew = (Si_decs) d;
            recolecta_procs(dnew);
        }
        else if(d.es_no_decs()) {
            No_decs dnew = (No_decs) d;
            recolecta_procs(dnew);
        }
    }
    public void recolecta_procs(Si_decs s){ //TODO
        if(s.es_muchas_decs()) {
            Muchas_decs dnew = (Muchas_decs) s;
            recolecta_procs(dnew);
        }
        else if(s.es_una_dec()) {
            Una_dec dnew = (Una_dec) s;
            recolecta_procs(dnew);
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

    public void procesa(Dec_proc d){
        procs.push(d);
    }

    public void procesa(Si_Ins s){
        s.ins().procesa(this);
    }

    public void procesa(No_Ins n){
        //NOOP
    }

    public void procesa(Muchas_ins m){
        m.ins().procesa(this);
        m.li().procesa(this);
    }

    public void procesa(Una_ins u){
        u.ins().procesa(this);
    }

    public void procesa(Ins_asig i){
        i.e().procesa(this);
    }

    public void procesa(Ins_if i){
        i.e().procesa(this);
        i.bloque().procesa(this);
    }

    public void procesa(Ins_if_else i){
        i.e().procesa(this);
        i.bloque().procesa(this);
        i.bloque2().procesa(this);
    }

    public void procesa(Ins_call i){
        i.e().procesa(this);
    }

    public void procesa(Ins_while i){
        i.e().procesa(this);
        i.bloque().procesa(this);
    }

    public void procesa(Ins_read i){
        i.e().procesa(this);
    }

    public void procesa(Ins_write i){
        i.e().procesa(this);
    }

    public void procesa(Ins_nl i){
        //NOOP
    }

    public void procesa(Ins_new i){
        i.e().procesa(this);
    }

    public void procesa(Ins_delete i){
        i.e().procesa(this);
    }

    public void procesa(Si_preal s){
        s.lpr().procesa(this);
    }

    public void procesa(No_preal n){
        //NOOP
    }

    public void procesa(Muchos_preal m){
        m.lpr().procesa(this);
        m.e().procesa(this);
    }

    public void procesa(Un_PReal u){
        u.e().procesa(this);
    }

    public void procesa(Exp_lit_ent e){
        etq++;
    }

    public void procesa(Exp_lit_real e){
        etq++;
    }

    public void procesa(Exp_lit_cadena e){
        etq++;
    }

    public void procesa(Exp_Iden e){
        etq++;
    }

    public void procesa(Exp_lit_BoolTrue e){
        etq++;
    }

    public void procesa(Exp_lit_BoolFalse e){
        etq++;
    }

    public void procesa(Exp_null e){
        etq++;
    }

    public void procesa(Asig a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
        etq++;
        String t0 = a.opnd0().tipo().iden().toString();
        String t1 = a.opnd1().tipo().iden().toString();
        if(t0.equals("real") && t1.equals("int")) {
            if(es_designador(a.opnd1())) {
                etq++;
            }
        } else {
            if(es_designador(a.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Suma s){
        s.opnd0().procesa(this);
        s.opnd1().procesa(this);
        etq++;
        String t0 = s.opnd0().tipo().iden().toString();
        String t1 = s.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real")) || (t0.equals("real") && t1.equals("int"))) {
            if (es_designador(s.opnd0())) {
                etq++;
            }
            if (es_designador(s.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Resta r){
        r.opnd0().procesa(this);
        r.opnd1().procesa(this);
        etq++;
        String t0 = r.opnd0().tipo().iden().toString();
        String t1 = r.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real")) || (t0.equals("real") && t1.equals("int"))) {
            if (es_designador(r.opnd0())) {
                etq++;
            }
            if (es_designador(r.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Mul m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
        etq++;
        String t0 = m.opnd0().tipo().iden().toString();
        String t1 = m.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real")) || (t0.equals("real") && t1.equals("int"))) {
            if (es_designador(m.opnd0())) {
                etq++;
            }
            if (es_designador(m.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Div d){
        d.opnd0().procesa(this);
        d.opnd1().procesa(this);
        etq++;
        String t0 = d.opnd0().tipo().iden().toString();
        String t1 = d.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real")) || (t0.equals("real") && t1.equals("int"))) {
            if (es_designador(d.opnd0())) {
                etq++;
            }
            if (es_designador(d.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Mod m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
        etq++;
        String t0 = m.opnd0().tipo().iden().toString();
        String t1 = m.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("int"))) {
            if (es_designador(m.opnd0())) {
                etq++;
            }
            if (es_designador(m.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(And a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
        etq++;
        String t0 = a.opnd0().tipo().iden().toString();
        String t1 = a.opnd1().tipo().iden().toString();
        if ((t0.equals("bool") && t1.equals("bool"))) {
            if (es_designador(a.opnd0())) {
                etq++;
            }
            if (es_designador(a.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Or o){
        o.opnd0().procesa(this);
        o.opnd1().procesa(this);
        etq++;
        String t0 = o.opnd0().tipo().iden().toString();
        String t1 = o.opnd1().tipo().iden().toString();
        if ((t0.equals("bool") && t1.equals("bool"))) {
            if (es_designador(o.opnd0())) {
                etq++;
            }
            if (es_designador(o.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Mayor ma){
        ma.opnd0().procesa(this);
        ma.opnd1().procesa(this);
        etq++;
        String t0 = ma.opnd0().tipo().iden().toString();
        String t1 = ma.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real"))) {
            if (es_designador(ma.opnd0())) {
                etq++;
            }
            if (es_designador(ma.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Menor me){
        me.opnd0().procesa(this);
        me.opnd1().procesa(this);
        etq++;
        String t0 = me.opnd0().tipo().iden().toString();
        String t1 = me.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real"))) {
            if (es_designador(me.opnd0())) {
                etq++;
            }
            if (es_designador(me.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(MayorIg mai){
        mai.opnd0().procesa(this);
        mai.opnd1().procesa(this);
        etq++;
        String t0 = mai.opnd0().tipo().iden().toString();
        String t1 = mai.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real"))) {
            if (es_designador(mai.opnd0())) {
                etq++;
            }
            if (es_designador(mai.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(MenorIg mei){
        mei.opnd0().procesa(this);
        mei.opnd1().procesa(this);
        etq++;
        String t0 = mei.opnd0().tipo().iden().toString();
        String t1 = mei.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real"))) {
            if (es_designador(mei.opnd0())) {
                etq++;
            }
            if (es_designador(mei.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Igual ig){
        ig.opnd0().procesa(this);
        ig.opnd1().procesa(this);
        etq++;
        String t0 = ig.opnd0().tipo().iden().toString();
        String t1 = ig.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real"))) {
            if (es_designador(ig.opnd0())) {
                etq++;
            }
            if (es_designador(ig.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Desigual de){
        de.opnd0().procesa(this);
        de.opnd1().procesa(this);
        etq++;
        String t0 = de.opnd0().tipo().iden().toString();
        String t1 = de.opnd1().tipo().iden().toString();
        if ((t0.equals("int") && t1.equals("real"))) {
            if (es_designador(de.opnd0())) {
                etq++;
            }
            if (es_designador(de.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Not no){
        no.opnd0().procesa(this);
        etq++;
        String t0 = no.opnd0().tipo().iden().toString();
        if ((t0.equals("int") || t0.equals("real"))) {
            if (es_designador(no.opnd0())) {
                etq++;
            }
        }
    }

    public void procesa(Lit_bool l){
        //NOOP
    }

    public void procesa(Lit_ent l){
        //NOOP
    }

    public void procesa(Lit_string l){
        //NOOP
    }

    public void procesa(Lit_real l){
        //NOOP
    }

    public void procesa(Iden i){
        //NOOP
    }
    public void procesa(Struct s){
        s.lcamp().procesa(this);
    }
    public void procesa(Array a){
        a.tipo().procesa(this);
    }
    public void procesa(Puntero p) {

    }

}