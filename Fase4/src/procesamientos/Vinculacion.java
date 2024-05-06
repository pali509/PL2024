package procesamientos;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

class TablaSimbolos {

    Stack <HashMap<String,Nodo>> ts = new Stack<HashMap<String,Nodo>>();
    public TablaSimbolos(){
        ts.add(new HashMap<String,Nodo>());
    }
    public TablaSimbolos(TablaSimbolos ts2){
        for (HashMap<String,Nodo> map : ts2.ts){
            ts.add(new HashMap<String,Nodo>(map));
        }
        ts.add(new HashMap<String,Nodo>());
    }

    public boolean contiene(String id){
        if (ts.peek().containsKey(id)){
            return true;
        }
        return false;
    }
    public boolean contiene2(String id){
        for (HashMap<String,Nodo> map : ts){
            if (map.containsKey(id)){
                return true;
            }
        }
        return false;
    }
    
    public void inserta(String id, Nodo dec){
        ts.peek().put(id, dec);
    }

    public Nodo vinculoDe(String id){
        List<HashMap<String,Nodo>> t = new ArrayList<>(ts);
        ListIterator<HashMap<String,Nodo>> it = t.listIterator(t.size());
        while(it.hasPrevious()){
            HashMap<String,Nodo> map = it.previous();
            if (map.containsKey(id)){
                return map.get(id);
            }
        }
        return null;
    }
}

public class Vinculacion extends ProcesamientoDef {
    private TablaSimbolos ts;
    private Vinculacion2 vin = new Vinculacion2();
    private MensajesError men;

    public void procesa(Prog p){
        men = new MensajesError("vinculacion");
        this.ts = new TablaSimbolos();
        p.bq().procesa(this);
    }

    public void procesa(Bloque b){
        TablaSimbolos aux = ts;
        ts = new TablaSimbolos(aux);
        b.lds().procesa(this);
        b.lds().procesa(vin);
        b.lis().procesa(this);
        ts = aux;
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

    public void procesa(Dec_var d){
        d.tipo().procesa(this);
        if (ts.contiene(d.iden().toString())){
            men.addError(d.leeFila(), d.leeCol());
        }
        else{
            ts.inserta(d.iden().toString(), d);
        }
    }

    public void procesa(Dec_tipo d){
        d.tipo().procesa(this);
        if (ts.contiene(d.iden().toString())){
            men.addError(d.leeFila(), d.leeCol());
        }
        else{
            ts.inserta(d.iden().toString(), d);
        }
    }

    public void procesa(Dec_proc d){
        if (ts.contiene(d.iden().toString())){
            men.addError(d.leeFila(), d.leeCol());
        }
        else{
            ts.inserta(d.iden().toString(), d);
        }
        TablaSimbolos aux = ts;
        ts = new TablaSimbolos(aux);
        d.pf().procesa(this);
        d.pf().procesa(vin);
        d.bq().procesa(this);
        ts = aux;
    }

    public void procesa(Si_pforms s){
        s.pforms().procesa(this);
    }

    public void procesa(No_pforms n){
        //NOOP
    }

    public void procesa(Muchos_pforms m){
        m.pforms().procesa(this);
        m.pform().procesa(this);
    }

    public void procesa(Un_pform u){
        u.pform().procesa(this);
    }

    public void procesa(PFref p){
        p.t().procesa(this);
        if (ts.contiene(p.id().toString())){
            men.addError(p.leeFila(), p.leeCol());
        }
        else{
            ts.inserta(p.id().toString(), p);
        }
    }

    public void procesa(PFnoref p){
        p.t().procesa(this);
        if (ts.contiene(p.id().toString())){
            men.addError(p.leeFila(), p.leeCol());
        }
        else{
            ts.inserta(p.id().toString(), p);
        }
    }

    public void procesa(Array a){
        a.tipo().procesa(this);
    }

    public void procesa(Puntero p){
        if (!p.tipo().es_iden()){
            p.tipo().procesa(this);
        }
    }

    public void procesa(Iden i){
        if (ts.contiene2(i.iden().toString())){
            Nodo n = ts.vinculoDe(i.iden().toString());
            i.setVinculo(n);
        }
        else{
            men.addError(i.leeFila(), i.leeCol());
        }
    }

    public void procesa(Struct s){
        s.lcamp().procesa(this);
    }

    public void procesa(Lit_ent l){
        //NOOP
    }

    public void procesa(Lit_real l){
        //NOOP
    }

    public void procesa(Lit_bool l){
        //NOOP
    }

    public void procesa(Lit_string l){
        //NOOP
    }

    public void procesa(Muchos_camp m){
        m.lcs().procesa(this);
        m.campo().procesa(this);
    }

    public void procesa(Un_camp u){
        u.campo().procesa(this);
    }

    public void procesa(Camp c){
        c.tipo().procesa(this);
    }

    public void procesa(Si_Ins s){
        s.ins().procesa(this);
    }

    public void procesa(No_Ins n){
        //NOOP
    }

    public void procesa(Muchas_ins m){
        m.li().procesa(this);
        m.ins().procesa(this);
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

    public void procesa(Ins_call i){
        i.id().procesa(this);
        i.pr().procesa(this);
    }

    public void procesa(Ins_bloque i){
        i.bloque().procesa(this);
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

    public void procesa(Asig a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
    }

    public void procesa(Mayor m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
    }

    public void procesa(Menor m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
    }

    public void procesa(MayorIg m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
    }

    public void procesa(MenorIg m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
    }

    public void procesa(Igual i){
        i.opnd0().procesa(this);
        i.opnd1().procesa(this);
    }

    public void procesa(Desigual d){
        d.opnd0().procesa(this);
        d.opnd1().procesa(this);
    }

    public void procesa(Suma s){
        s.opnd0().procesa(this);
        s.opnd1().procesa(this);
    }

    public void procesa(Resta r){
        r.opnd0().procesa(this);
        r.opnd1().procesa(this);
    }

    public void procesa(And a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
    }

    public void procesa(Or o){
        o.opnd0().procesa(this);
        o.opnd1().procesa(this);
    }

    public void procesa(Mul m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
    }

    public void procesa(Div d){
        d.opnd0().procesa(this);
        d.opnd1().procesa(this);
    }

    public void procesa(Mod m){
        m.opnd0().procesa(this);
        m.opnd1().procesa(this);
    }

    public void procesa(Neg n){
        n.opnd0().procesa(this);
    }

    public void procesa(Not n){
        n.opnd0().procesa(this);
    }

    public void procesa(AccesoArray a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
    }

    public void procesa(AccesoCampo a){
        a.opnd0().procesa(this);
    }

    public void procesa(AccesoPuntero a){
        a.opnd0().procesa(this);
    }

    public void procesa(Exp_lit_ent e){
        //NOOP
    }

    public void procesa(Exp_lit_real e){
        //NOOP
    }

    public void procesa(Exp_lit_cadena e){
        //NOOP
    }

    public void procesa(Exp_Iden e){
        if (ts.contiene2(e.iden().toString())){
            Nodo n = ts.vinculoDe(e.iden().toString());
            e.setVinculo(n);
        }
        else{
            men.addError(e.leeFila(), e.leeCol());
        }
    }

    public void procesa(Exp_lit_BoolTrue e){
        //NOOP
    }

    public void procesa(Exp_lit_BoolFalse e){
        //NOOP
    }

    public void procesa(Exp_null e){
        //NOOP
    }

    private class Vinculacion2 extends ProcesamientoDef{
    
        public void procesa(Si_decs s){
            s.decs().procesa(vin);
        }

        public void procesa(No_decs n){
            //NOOP
        }

        public void procesa(Muchas_decs m){
            m.ldecs().procesa(vin);
            m.dec().procesa(vin);
        }

        public void procesa(Una_dec u){
            u.dec().procesa(vin);
        }

        public void procesa(Dec_var d){
            d.tipo().procesa(vin);
        }

        public void procesa(Dec_tipo d){
            d.tipo().procesa(vin);
        }

        public void procesa(Dec_proc d){
            //NOOP
        }

        public void procesa(Si_pforms s){
            s.pforms().procesa(vin);
        }

        public void procesa(No_pforms n){
            //NOOP
        }

        public void procesa(Muchos_pforms m){
            m.pforms().procesa(vin);
            m.pform().procesa(vin);
        }

        public void procesa(Un_pform u){
            u.pform().procesa(vin);
        }

        public void procesa(PFref p){
            p.t().procesa(vin);
        }

        public void procesa(PFnoref p){
            p.t().procesa(vin);
        }

        public void procesa(Array a){
            a.tipo().procesa(vin);
        }

        public void procesa(Puntero p){
            if (p.tipo().es_iden()){
                Iden t = (Iden)p.tipo();
                if(ts.contiene2(t.iden().toString())){
                    t.setVinculo(ts.vinculoDe(t.iden().toString()));
                }
                else{
                    men.addError(p.leeFila(), p.leeCol());
                }
            }
            else{
                p.tipo().procesa(vin);
            }
        }

        public void procesa(Iden i){
            //NOOP
        }

        public void procesa(Struct s){
            s.lcamp().procesa(vin);
        }

        public void procesa(Lit_ent l){
            //NOOP
        }

        public void procesa(Lit_real l){
            //NOOP
        }

        public void procesa(Lit_bool l){
            //NOOP
        }

        public void procesa(Lit_string l){
            //NOOP
        }

        public void procesa(Muchos_camp m){
            m.lcs().procesa(vin);
            m.campo().procesa(vin);
        }

        public void procesa(Un_camp u){
            u.campo().procesa(vin);
        }

        public void procesa(Camp c){
            c.tipo().procesa(vin);
        }
    }
}
