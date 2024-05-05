package procesamientos;

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
        ts.add(new HashMap<String,Nodo>();)
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
    }
    
    public boolean inserta(String id, Nodo dec){
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

public class Vinculacion extends ProcesamientoDef{
    private TablaSimbolos ts;
    private Vinculacion2 vin = new Vinculacion2();

    public void vincula(Prog p){
        this.ts = new TablaSimbolos();
        p.bq().vincula(this);
    }

    public void vincula(Bloque b){
        TablaSimbolos aux = ts;
        ts = new TablaSimbolos(aux);
        b.lds().recolectaDecs(this);
        b.lds().recolectaDecs(vin);
        b.lis().vincula(this);
        ts = aux;
    }

    public void recolectaDecs(Si_decs s){
        s.decs().recolectaDecs(this);
    }

    public void recolectaDecs(No_decs n){
        //NOOP
    }

    public void recolectaDecs(Muchas_decs m){
        m.ldecs().recolectaDecs(this);
        m.dec().recolectaDec(this);
    }

    public void recolectaDecs(Una_dec u){
        u.dec().recolectaDec(this);
    }

    public void recolectaDec(Dec_var d){
        d.tipo().vincula(this);
        if (ts.contiene(d.iden())){
            throw new RuntimeException("Identificador existente");
        }
        else{
            ts.inserta(d.iden(), d);
        }
    }

    public void recolectaDec(Dec_tipo d){
        d.tipo().vincula(this);
        if (ts.contiene(d.iden())){
            throw new RuntimeException("Identificador existente");
        }
        else{
            ts.inserta(d.iden(), d);
        }
    }

    public void recolectaDec(Dec_proc d){
        if (ts.contiene(d.iden())){
            throw new RuntimeException("Identificador existente");
        }
        else{
            ts.inserta(d.iden(), d);
        }
        TablaSimbolos aux = ts;
        ts = new TablaSimbolos(aux);
        d.pf().recolectaDecs(this);
        d.bq().vincula(this);
        ts = aux
    }

    public void recolectaDecs(Si_pforms s){
        s.pforms().recolectaDecs(this);
    }

    public void recolectaDecs(No_pforms n){
        //NOOP
    }

    public void recolectaDecs(Muchos_pforms m){
        m.pforms().recolectaDecs(this);
        m.pform().recolectaDec(this);
    }

    public void recolectaDecs(Un_pform u){
        u.pform().recolectaDec(this);
    }

    public void recolectaDec(PFref p){
        p.t().vincula(this);
        if (ts.contiene(p.id())){
            throw new RuntimeException("Identificador existente");
        }
        else{
            ts.inserta(p.id(), p);
        }
    }

    public void recolectaDec(PFnoref p){
        p.t().vincula(this);
        if (ts.contiene(p.id())){
            throw new RuntimeException("Identificador existente");
        }
        else{
            ts.inserta(p.id(), p);
        }
    }

    public void vincula(Array a){
        a.tipo().vincula(this);
    }

    public void vincula(Puntero p){
        if (p.tipo() != Iden){
            p.tipo().vincula(this);
        }
    }

    public void vincula(Iden i){
        if (ts.contiene2(i.iden())){
            Nodo n = ts.vinculoDe(i.iden());
            //AQUI
            //Esta funcion hace falta pero no esta en la sintaxis abstracta
            //ref.set_vinculo(n);
        }
        else{
            throw new RuntimeException("Tipo inexistente");
        }
    }

    public void vincula(Struct s){
        s.lcamp().vincula(this);
    }

    public void vincula(Lit_ent l){
        //NOOP
    }

    public void vincula(Lit_real l){
        //NOOP
    }

    public void vincula(Lit_bool l){
        //NOOP
    }

    public void vincula(Lit_string l){
        //NOOP
    }

    public void vincula(Muchos_camp m){
        m.lcs().vincula(this);
        m.campo().vincula(this);
    }

    public void vincula(Un_camp u){
        u.campo().vincula(this);
    }

    public void vincula(Camp c){
        c.tipo().vincula(this);
    }

    public void vincula(Si_Ins s){
        s.ins().vincula(this);
    }

    public void vincula(No_Ins n){
        //NOOP
    }

    public void vincula(Muchas_ins m){
        m.li().vincula(this);
        m.ins().vincula(this);
    }

    public void vincula(Una_ins u){
        u.ins().vincula(this);
    }

    public void vincula(Ins_asig i){
        i.e().vincula(this);
    }

    public void vincula(Ins_if i){
        i.e().vincula(this);
        i.bloque().vincula(this);
    }

    public void vincula(Ins_if_else i){
        i.e().vincula(this);
        i.bloque().vincula(this);
        i.bloque2().vincula(this);
    }

    public void vincula(Ins_while i){
        i.e().vincula(this);
        i.bloque().vincula(this);
    }

    public void vincula(Ins_read i){
        i.e().vincula(this);
    }

    public void vincula(Ins_write i){
        i.e().vincula(this);
    }

    public void vincula(Ins_nl i){
        //NOOP
    }

    public void vincula(Ins_new i){
        i.e().vincula(this);
    }

    public void vincula(Ins_delete i){
        i.e().vincula(this);
    }

    public void vincula(Ins_call i){
        i.id().vincula(this);
        i.pr().vincula(this);
    }

    public void vincula(Ins_bloque i){
        i.bloque().vincula(this);
    }

    public void vincula(Si_preal s){
        s.lpr().vincula(this);
    }

    public void vincula(No_preal n){
        //NOOP
    }

    public void vincula(Muchos_preal m){
        m.lpr().vincula(this);
        m.e().vincula(this);
    }

    public void vincula(Un_PReal u){
        u.e().vincula(this);
    }

    public void vincula(Asig a){
        a.opnd0().vincula(this);
        a.opnd1().vincula(this);
    }

    public void vincula(Mayor m){
        m.opnd0().vincula(this);
        m.opnd1().vincula(this);
    }

    public void vincula(Menor m){
        m.opnd0().vincula(this);
        m.opnd1().vincula(this);
    }

    public void vincula(MayorIg m){
        m.opnd0().vincula(this);
        m.opnd1().vincula(this);
    }

    public void vincula(MenorIg m){
        m.opnd0().vincula(this);
        m.opnd1().vincula(this);
    }

    public void vincula(Igual i){
        i.opnd0().vincula(this);
        i.opnd1().vincula(this);
    }

    public void vincula(Desigual d){
        d.opnd0().vincula(this);
        d.opnd1().vincula(this);
    }

    public void vincula(Suma s){
        s.opnd0().vincula(this);
        s.opnd1().vincula(this);
    }

    public void vincula(Resta r){
        r.opnd0().vincula(this);
        r.opnd1().vincula(this);
    }

    public void vincula(And a){
        a.opnd0().vincula(this);
        a.opnd1().vincula(this);
    }

    public void vincula(Or o){
        o.opnd0().vincula(this);
        o.opnd1().vincula(this);
    }

    public void vincula(Mul m){
        m.opnd0().vincula(this);
        m.opnd1().vincula(this);
    }

    public void vincula(Div d){
        d.opnd0().vincula(this);
        d.opnd1().vincula(this);
    }

    public void vincula(Mod m){
        m.opnd0().vincula(this);
        m.opnd1().vincula(this);
    }

    public void vincula(Neg n){
        n.opnd0().vincula(this);
    }

    public void vincula(Not n){
        n.opnd0().vincula(this);
    }

    public void vincula(AccesoArray a){
        a.opnd0().vincula(this);
        a.opnd1().vincula(this);
    }

    public void vincula(AccesoCampo a){
        a.opnd0().vincula(this);
    }

    public void vincula(AccesoPuntero a){
        a.opnd0().vincula(this);
    }

    public void vincula(Exp_lit_ent e){
        //NOOP
    }

    public void vincula(Exp_lit_real e){
        //NOOP
    }

    public void vincula(Exp_lit_cadena e){
        //NOOP
    }

    public void vincula(Exp_Iden e){
        if (ts.contiene2(e.iden())){
            Nodo n = ts.vinculoDe(i.iden());
            //AQUI
            //Esta funcion hace falta pero no esta en la sintaxis abstracta
            //ref.set_vinculo(n);
        }
        else{
            throw new RuntimeException("Identificador no declarado");
        }
    }

    public void vincula(Exp_lit_BoolTrue e){
        //NOOP
    }

    public void vincula(Exp_lit_BoolFalse e){
        //NOOP
    }

    public void vincula(Exp_null e){
        //NOOP
    }

    private class Vinculacion2 extends ProcesamientoDef{
    
        public void recolectaDecs(Si_decs s){
            s.decs().recolectaDecs(vin);
        }

        public void recolectaDecs(No_decs n){
            //NOOP
        }

        public void recolectaDecs(Muchas_decs m){
            m.ldecs().recolectaDecs(vin);
            m.dec().recolectaDec(vin);
        }

        public void recolectaDecs(Una_dec u){
            u.dec().recolectaDec(vin)
        }

        public void recolectaDec(Dec_var d){
            d.tipo().vincula(vin);
        }

    recolectaDec2(dec_tipo(T, id)):
	vincula2(T)

    recolectaDec2(dec_proc(id, PFormOpt, Bloq)):
    recolectaDecs2(PFormOpt)
    }

    recolectaDecs2(si_pform(LPForm)):
	recolectaDecs2(LPForm)

    recolectaDecs2(no_pform()):
noop

recolectaDecs2(muchas_pforms(LPForm, PForm)):
	recolectaDecs2(LPForm)
	recolectaDec2(PForm)

    recolectaDecs2(una_pform(PForm)):
	recolectaDec2(PForm)

    recolectaDec2(pform_ref(T,id)):
	vincula2(T)

    recolectaDec2(pform_no_ref(T,id)):
	vincula2(T)

    vincula2(array(T, dim)):
	vincula2(T)

    vincula2(puntero(T)):
if (T =iden(Id)):
        		T.vinculo = vinculoDe(ts, Id)
       		 if (T.vinculo = -|):
            		error
		end if
    	else
       		 vincula2(T)
	end if


    
vincula2(iden(id)):
noop

vincula2(struct(LCamp)):
	vincula2(LCamp)


    vincula2(lit_ent(string)):
	noop

    vincula2(lit_real(string)):
	noop

    vincula2(lit_bool(string)):
	noop

    vincula2(lit_string(string)):
	noop

    vincula2(muchos_camp(LCamp,  Camp)):
	vincula2(LCamp)
	vincula2(Camp)

    vincula2(un_camp(Camp)):
	vincula2(Camp)

    vincula2(camp(T, Id)):
	vincula2(T)
}
