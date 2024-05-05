package procesamientos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

import java.util.*;

public class Etiquetado extends ProcesamientoDef {

    private Stack<Dec_proc> procs = new Stack<Dec_proc>();
    private int etq;

    private boolean esDesignador(Exp e){
        return true;
    }

    public void procesa(Prog p){
        etq = 0;
        p.bq().procesa(this);
    }

    public void procesa(Bloque b){
        b.lds().procesa(this);
        b.lis().procesa(this);
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
            if(esDesignador(a.opnd1())) {
                etq++;
            }
        } else {
            if(esDesignador(a.opnd1())) {
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
            if (esDesignador(s.opnd0())) {
                etq++;
            }
            if (esDesignador(s.opnd1())) {
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
            if (esDesignador(r.opnd0())) {
                etq++;
            }
            if (esDesignador(r.opnd1())) {
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
            if (esDesignador(m.opnd0())) {
                etq++;
            }
            if (esDesignador(m.opnd1())) {
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
            if (esDesignador(d.opnd0())) {
                etq++;
            }
            if (esDesignador(d.opnd1())) {
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
        if ((t0.equals("int") && t1.equals("int")))) {
            if (esDesignador(m.opnd0())) {
                etq++;
            }
            if (esDesignador(m.opnd1())) {
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
            if (esDesignador(a.opnd0())) {
                etq++;
            }
            if (esDesignador(a.opnd1())) {
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
            if (esDesignador(o.opnd0())) {
                etq++;
            }
            if (esDesignador(o.opnd1())) {
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
            if (esDesignador(ma.opnd0())) {
                etq++;
            }
            if (esDesignador(ma.opnd1())) {
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
            if (esDesignador(me.opnd0())) {
                etq++;
            }
            if (esDesignador(me.opnd1())) {
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
            if (esDesignador(mai.opnd0())) {
                etq++;
            }
            if (esDesignador(mai.opnd1())) {
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
            if (esDesignador(mei.opnd0())) {
                etq++;
            }
            if (esDesignador(mei.opnd1())) {
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
            if (esDesignador(ig.opnd0())) {
                etq++;
            }
            if (esDesignador(ig.opnd1())) {
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
            if (esDesignador(de.opnd0())) {
                etq++;
            }
            if (esDesignador(de.opnd1())) {
                etq++;
            }
        }
    }

    public void procesa(Not no){
        no.e().procesa(this);
        etq++;
        String t0 = no.opnd0().tipo().iden().toString();
        if ((t0.equals("int") || t0.equals("real"))) {
            if (esDesignador(no.opnd0())) {
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