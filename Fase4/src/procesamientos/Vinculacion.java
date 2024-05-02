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
            //Esta funcion hace falta pero no esta en la sintaxis abstracta
            //ref.set_vinculo(n);
        }
        else{
            throw new RuntimeException("Tipo inexistente");
        }
    }

    public void


vincula1(struct(LCamp)):
	vincula1(LCamp)

vincula1(lit_ent(string)):
	noop

vincula1(lit_real(string)):
	noop

vincula1(lit_bool(string)):
	noop

vincula1(lit_string(string)):
	noop

vincula1(muchos_camp(LCamp,  Camp)):
	vincula1(LCamp)
	vincula1(Camp)

vincula1(un_camp(Camp)):
	vincula1(Camp)

vincula1(camp(T, Id)):
	vincula1(T)

vincula1(Si_ins(LIns)):
	vincula1(LIns)

vincula1(no_ins()):
	noop

vincula1(muchas_ins(LIns, Ins)):
	vincula1(LIns)
	vincula1(Ins)

vincula1(una_ins(Ins)):
	vincula1(Ins)

vincula1(ins_asig(Exp)):
	vincula1(Exp)

vincula1(ins_if(Exp, Bloq)):
	vincula1(Exp)
	vincula(Bloq)

vincula1(ins_if_else(Exp, Bloq, Bloq)):
	vincula1(Exp)
	vincula(Bloq)
	vincula(Bloq)

vincula1(ins_while(Exp, Bloq)):
	vincula1(Exp)
	vincula(Bloq)

vincula1(ins_read(Exp)):
	vincula1(Exp)

vincula1(ins_write(Exp)):
	vincula1(Exp)

vincula1(ins_nl()):
	noop

vincula1(ins_new(Exp)):
	vincula1(Exp)

vincula1(ins_delete(Exp)):
	vincula1(Exp)

vincula1(ins_call(Iden, PRealOpt)):
	vincula1(Iden)
	vincula1(PRealOpt)

vincula1(ins_bloque(Bloq)):
	vincula(Bloq)

vincula1(si_preal(LPReal)):
	vincula1(LPreal)

vincula1(no_preal()):
	noop

vincula1(muchos_preal(LPReal, Exp)):
	vincula1(LPReal)
	vincula1(Exp)

vincula1(un_preal(Exp)):
	vincula1(Exp)

vincula1(asig(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(mayor(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(menor(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(mayorIgual(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(menorIgual(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(igual(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(desigual(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(suma(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(resta(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(and(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(or(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(mul(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(div(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(mod(Exp0, Exp1)):
	vincula1(Exp0)
	vincula1(Exp1)

vincula1(neg(Exp)):
	vincula1(Exp)
	
vincula1(not(Exp)):
	vincula1(Exp)

vincula1(acceso_array(Exp0, Exp1)):
	vincula1(Exp0)
vincula1(Exp1)

vincula1(acceso_campo(Exp, id)):
	vincula1(Exp)

vincula1(acceso_puntero(Exp)):
	vincula1(Exp)

vincula1(exp_litEntero(N)):
	noop

vincula1(exp_litReal(R)):
	noop

vincula1(exp_litCadena(Id)):
	noop

vincula1(exp_Identificador(id)):
	if contiene(ts,id) then 	
		$.vinculo = vinculoDe(ts,id)
	else
		error
	end if

vincula1(exp_litBoolTrue()):
	noop

vincula1(exp_litBoolFalse()):
	noop

vincula1(exp_null()):
	noop


    private class Vinculacion2 extends ProcesamientoDef{
    
        recolectaDecs2(si_decs(LDecs)):
	recolectaDecs2(LDecs)

    recolectaDecs2(no_decs()):
	noop

recolectaDecs2(muchas_decs(LDecs, Dec)):
	recolectaDecs2(LDecs)
	recolectaDec2(Dec)

    recolectaDecs2(una_dec(Dec)):
	recolectaDec2(Dec)

    recolectaDec2(dec_var(T, id)):
	vincula2(T)

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
