package procesamientos;
import asint.SintaxisAbstractaTiny.*;
import asint.ProcesamientoDef;
import java.util.Stack;
import maquinaP.*;

public class Gen_cod extends ProcesamientoDef {
    private MaquinaP mp;

    public Gen_cod(MaquinaP mp) {
        this.mp = mp;
    }

    private Stack<Dec_proc> procs = new Stack<Dec_proc>();

    public void procesa(Prog p){
        p.bq().procesa(this);
    }

    public void procesa(Bloque b) {
		recolecta_procs(b.lds());
        b.lis().procesa(this);
		mp.emit(mp.stop());
        while (!procs.isEmpty()) {
            Dec_proc proc = procs.pop();
			mp.desapilad(proc.get_nivel());
			recolecta_procs(proc.bq().lds());
			proc.bq().lis().procesa(this);
            proc.procesa(this);
			mp.emit(mp.desactiva(proc.get_nivel(), proc.getTam()));
			mp.emit(mp.ir_ind());
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

    public void procesa(Dec_proc dec_proc) {
        dec_proc.bq().procesa(this);
		mp.emit(mp.desactiva(dec_proc.get_nivel(), dec_proc.getTam()));
		mp.emit(mp.ir_ind());
    }


    public void procesa(Si_Ins s){
        s.ins().procesa(this);
    }

    public void procesa(No_Ins n){
        // NOOP
    }

    public void procesa(Muchas_ins m){
        m.ins().procesa(this);
        m.li().procesa(this);
    }

    public void procesa(Una_ins u){
        u.ins().procesa(this);
    }


	public void gen_acc_val(Exp e){
		if(es_desig(e))
			mp.emit(mp.fetch(e.get_dir()));
	}
    public void procesa(Ins_asig i){
        i.e().procesa(this);
		mp.emit(mp.desecha());

    }

    public void procesa(Ins_if i){
        i.e().procesa(this);
		gen_acc_val(i.e());
		mp.emit(mp.ir_f(i.sig()));
		i.bloque().procesa(this);
    }

    public void procesa(Ins_if_else i){
		i.e().procesa(this);
		gen_acc_val(i.e());
		mp.emit(mp.ir_f(i.prim2()));
		i.bloque().procesa(this);
		mp.emit(mp.ir_a(i.sig()));
        i.bloque2().procesa(this);
    }

	public void procesa(Ins_while i){
		i.e().procesa(this);
		gen_acc_val(i.e());
		mp.emit(mp.ir_f(i.sig()));
		i.bloque().procesa(this);
		mp.emit(mp.ir_a(i.prim()));
	}
    public void procesa(Ins_call i){
		mp.emit(mp.activa(i.getVinculo().get_nivel(), i.getVinculo().tipo().getTam(), i.sig()));
		gen_paso_pf(i., i.pr());
		mp.emit(mp.ir_a(i.getVinculo().get_dir()));


		/*
		let id = string.vinculo, id.vinculo = dec_proc(_, PFormOpt , _) in
		emit activa($.vinculo.nivel, $.vinculo.tam, $.dir_sig)
		gen-paso-pf(PFormOpt, PRealOpt)
		emit ir-a($.vinculo.dir_inic)
		end let
		EL ENUNCIADO:
		Para cada parámetro formal con nombre u, si ya existe una variable u, debe
		salvaguardarse dir(u).
		o Para cada parámetro formal en modo valor ti ui:
			▪ dir(ui)  alloc(ti)
			▪ Evaluar el correspondiente parámetro real Ei. Sea tRi el tipo de Ei y sea ri el
		resultado de la evaluación:
		• Si ti es real y tRi es int:
			o Si Ei es un designador, vi  fetch(ri). Si no, vi  ri
			o Convertir vi a real. Sea v’i el resultado de dicha conversión
			o store(dir(ui), v’i)
		• en otro caso:
			o Si Ei es un designador, copy(dir(ui), ri, i)
			o En otro caso, store(dir(ui), ri)
		 */
    }

    public void procesa(Ins_read i){
		i.e().procesa(this);
		Tipo t = refI(i.e().tipo());
		if(t.es_int()){
			mp.emit(mp.leer_entrada_int());
			mp.emit(mp.store_int(i.e().get_dir(), Integer.parseInt(i.e().valor().toString())));
		}
		else if(t.es_real()){
			mp.emit(mp.leer_entrada_real());
			mp.emit(mp.store_real(i.e().get_dir(), Double.parseDouble(i.e().valor().toString())));
		}
		else if(t.es_string()){
			mp.emit(mp.leer_entrada_string());
			mp.emit(mp.store_string(i.e().get_dir(), i.e().valor().toString()));
		}

    }

    public void procesa(Ins_write i){
		i.e().procesa(this);
		gen_acc_val(i.e());
		Tipo t = refI(i.e().tipo());
		if(t.es_int()){
			mp.emit(mp.mostrar_int());}
		else if(t.es_real()){
			mp.emit(mp.mostrar_real());}
		else if(t.es_string()){
			mp.emit(mp.mostrar_string());}
		else if (t.es_bool()){
			mp.emit(mp.mostrar_bool());
		}
    }

    public void procesa(Ins_nl i){
        //NOOP
    }

    public void procesa(Ins_new i){
		try {
			i.e().procesa(this);
			Tipo t = refI(i.e().tipo());
			if(t.es_int()){
				mp.emit(mp.store_int(i.e().get_dir(), Integer.parseInt(i.e().valor().toString())));
			}
			else if(t.es_real()){
				mp.emit(mp.store_real(i.e().get_dir(), Double.parseDouble(i.e().valor().toString())));
			}
			else if(t.es_string()){
				mp.emit(mp.store_string(i.e().get_dir(), i.e().valor().toString()));
			}
			mp.emit(mp.alloc(refI(i.e().tipo()).getTam()));
			mp.emit(mp.dealloc(refI(i.e().tipo()).getTam(), i.e().get_dir()));
		} catch (Exception e) {
			System.out.println("Error de ejecución: " + e.getMessage());
		}


        /*
        emite store(gen-cod(Exp), emite alloc(ref!(Exp.tipo).tipo().tam))

		dealloc(d, ), si d  -1. Error de ejecución si d = -1

         */
    }

    public void procesa(Ins_delete i){
		try {
			i.e().procesa(this);
			mp.emit(mp.dealloc(refI(i.e().tipo()).getTam(), i.e().get_dir()));
		} catch (Exception e) {
			System.out.println("Error de ejecución: " +  e.getMessage());
		}
        /*
        d = gen-cod(Exp)
	si d != -1:
		emite dealloc(d, ref!(E.tipo))
	sino:
		ERROR

         */
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

    
    
    
    //GEN-COD EXPRESIONES
    
    public void procesa(Exp_lit_ent e){
    	mp.apila_int(Integer.parseInt(e.valor().toString()));
    }

    public void procesa(Exp_lit_real e){
    	mp.apila_real(Double.parseDouble(e.valor().toString()));
    }

    public void procesa(Exp_lit_cadena e){
    	mp.apila_string(e.valor().toString());
    }

    public void procesa(Exp_Iden e){
    	
    	if(e.getVinculo().get_nivel() != 0) {
    		mp.apilad(e.getVinculo().get_nivel());
    		mp.apila_int(e.getVinculo().get_dir());
    		mp.suma_int();
    		if(e.getVinculo().es_parf_ref()){
    			mp.apila_ind();
    		}
    	}
    	else{
    		mp.apila_int(e.getVinculo().get_dir());
    	}
    	
    }

    public void procesa(Exp_lit_BoolTrue e){
    	mp.apila_bool(true);
    }

    public void procesa(Exp_lit_BoolFalse e){
    	mp.apila_bool(false);
    }

    public void procesa(Exp_null e){
    	mp.apila_int(-1);
    }

    public void procesa(Asig a){
    	a.opnd0().procesa(this);
    	a.opnd1().procesa(this);
    	if(refI(a.opnd1().tipo()).es_int() && refI(a.opnd0().tipo()).es_real()) {
    		if(es_desig(a.opnd1())) {
    			mp.apila_ind();
    		}
    		mp.int2real();
    		mp.desapila_ind();
    	}
    	else {
    		if(es_desig(a.opnd1())) {
    			mp.mueve(refI(a.opnd0().tipo()).getTam());
    		}
    		else {
    			mp.desapila_ind();
    		}
    	}
    	
    }

    public void procesa(Suma s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0.es_int() && t1.es_int()) {
    		mp.suma_int();
    	}
    	else if(t0.es_real() && t1.es_real()) {
    		mp.suma_real();
    	}
  
    	
    }

    public void procesa(Resta s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0.es_int() && t1.es_int()) {
    		mp.resta_int();
    	}
    	else if(t0.es_real() && t1.es_real()) {
    		mp.resta_real();
    	}
    }

    public void procesa(Mul s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0.es_int() && t1.es_int()) {
    		mp.mul_int();
    	}
    	else if(t0.es_real() && t1.es_real()) {
    		mp.mul_real();
    	}
    }

    public void procesa(Div s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0.es_int() && t1.es_int()) {
    		mp.div_int();
    	}
    	else if(t0.es_real() && t1.es_real()) {
    		mp.div_real();
    	}
    }

    public void procesa(Mod s){
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}

    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(s.opnd0().tipo().es_int() && s.opnd1().tipo().es_int()) {
    		mp.mod();
    	}

    }

    public void procesa(And s){
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}

    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(s.opnd0().tipo().es_bool() && s.opnd1().tipo().es_bool()) {
    		mp.and();
    	}
    }

    public void procesa(Or s){
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}

    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(s.opnd0().tipo().es_bool() && s.opnd1().tipo().es_bool()) {
    		mp.or();
    	}
    }
    

    public void procesa(Mayor s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0 == t1) {
    		if(t0.es_real()) {
    			mp.mayor_real();
    			}
    		else if(t0.es_int()) {
    			mp.mayor_int();
    		}
    		else if(t0.es_bool()) {
    			mp.mayor_bool();
    		}
    		else if(t0.es_string()){
    			mp.mayor_string();
    		}
    	}

    }

    public void procesa(Menor s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0 == t1) {
    		if(t0.es_real()) {
    			mp.menor_real();
    			}
    		else if(t0.es_int()) {
    			mp.menor_int();
    		}
    		else if(t0.es_bool()) {
    			mp.menor_bool();
    		}
    		else if(t0.es_string()){
    			mp.menor_string();
    		}
    	}
    }

    public void procesa(MayorIg s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0 == t1) {
    		if(t0.es_real()) {
    			mp.mayorIgual_real();
    			}
    		else if(t0.es_int()) {
    			mp.mayorIgual_int();
    		}
    		else if(t0.es_bool()) {
    			mp.mayorIgual_bool();
    		}
    		else if(t0.es_string()){
    			mp.mayorIgual_string();
    		}
    	}
    }

    public void procesa(MenorIg s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0 == t1) {
    		if(t0.es_real()) {
    			mp.menorIgual_real();
    			}
    		else if(t0.es_int()) {
    			mp.menorIgual_int();
    		}
    		else if(t0.es_bool()) {
    			mp.menorIgual_bool();
    		}
    		else if(t0.es_string()){
    			mp.menorIgual_string();
    		}
    	}
    }

    public void procesa(Igual s){
    	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0 == t1) {
    		if(t0.es_real()) {
    			mp.igual_real();
    			}
    		else if(t0.es_int()) {
    			mp.igual_int();
    		}
    		else if(t0.es_bool()) {
    			mp.igual_bool();
    		}
    		else if(t0.es_string()){
    			mp.igual_string();
    		}
    		else if(t0.es_puntero()) {
    			mp.igual_puntero();
    		}
    		else if(t0.es_null()) {
    			mp.igual_null();
    		}
    	}
    	else if(t0.es_puntero() && t1.es_null() || t0.es_null() && t1.es_puntero()) {
    		mp.igual_pn_null();
    	}
    }

    public void procesa(Desigual s){
       	Tipo t0 = refI(s.opnd0().tipo());
    	Tipo t1 = refI(s.opnd1().tipo());
    	s.opnd0().procesa(this);
    	if(es_desig(s.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int() && t1.es_real()) {
    		mp.int2real();
    	}
    	s.opnd1().procesa(this);
    	if(es_desig(s.opnd1())) {
    		mp.apila_ind();
    	}
    	if(t0.es_real() && t1.es_int()) {
    		mp.int2real();
    	}
    	if(t0 == t1) {
    		if(t0.es_real()) {
    			mp.desigual_real();
    			}
    		else if(t0.es_int()) {
    			mp.desigual_int();
    		}
    		else if(t0.es_bool()) {
    			mp.desigual_bool();
    		}
    		else if(t0.es_string()){
    			mp.desigual_string();
    		}
    		else if(t0.es_puntero()) {
    			mp.desigual_puntero();
    		}
    		else if(t0.es_null()) {
    			mp.desigual_pn_null();
    		}
    	}
    	else if(t0.es_puntero() && t1.es_null() || t0.es_null() && t1.es_puntero()) {
    		mp.desigual_pn();
    	}
    }

    public void procesa(Not n){
    	Tipo t0 = refI(n.opnd0().tipo());
    	n.opnd0().procesa(this);
    	if(es_desig(n.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_bool()) {
    		mp.not();
    	}

    	
    }
    
    public void procesa(Neg n) {
    	Tipo t0 = refI(n.opnd0().tipo());
    	n.opnd0().procesa(this);
    	if(es_desig(n.opnd0())) {
    		mp.apila_ind();
    	}
    	if(t0.es_int()) {
    		mp.neg_int();
    	}
    	else if(t0.es_real()) {
    		mp.neg_real();
    	}
    }

    public void procesa(Lit_bool l){}

    public void procesa(Lit_ent l){}

    public void procesa(Lit_string l){}

    public void procesa(Lit_real l){}

    public void procesa(Iden i){}
    
    public void procesa(AccesoCampo s){
    	s.opnd0().procesa(this);
    	mp.apila_int(desp(refI(s.opnd0().tipo()).lcamp(), s.iden().toString()));
    	mp.suma_int();
    }
    public void procesa(AccesoArray a){
        a.opnd0().procesa(this);
        a.opnd1().procesa(this);
        if(es_desig(a.opnd1())) {
        	mp.apila_ind();
        }
        mp.apila_int(refI(a.opnd0().tipo()).tipo().getTam());
        mp.mul_int();
        mp.suma_int();
        
    }
    public void procesa(AccesoPuntero p) {
    	p.opnd0().procesa(this);
    	mp.apila_ind();
    }
    
    
    
    //FUNCIONES AUX PARA EXP
    private Tipo refI(Tipo t) {
    	while(t.es_iden()) {
    		Iden id = (Iden)t;
    		Dec_tipo d = (Dec_tipo) id.getVinculo();
    		t = d.tipo();
    	}
    	return t;
    }
    
    private boolean es_desig(Exp e) {
		if(e.es_iden()) {
			Exp_Iden e2 = (Exp_Iden) e;
			if (e2.getVinculo() != null && (e.es_dec_var() || e.es_parf_noRef() || e.es_parf_ref())) {
				return true;
			} else if (e.prioridad() == 5) { //Prioridad 5 para los accesos.
				return true;
			}
		}
		return false;
    }
    
    private int desp(LCamp lcs, String c) {
    	while(lcs.es_muchos_campos()) {
    		Camp campo = lcs.campo();
    		if(c.equals(campo.iden().toString())) {
    			return campo.get_desp();
    		}
    		lcs = lcs.lcs();
    	}
    	Camp campo = lcs.campo();
    	return campo.get_desp();
    }

	private void recolecta_procs(LDecsOpt lds){
		if(lds.es_una_dec()){
			Una_dec decs = (Una_dec)lds.decs();
			recolecta_procs(decs.dec());
		}else if(lds.es_muchas_decs()){
			Muchas_decs decs = (Muchas_decs)lds.decs();
			recolecta_procs(decs.ldecs());
			recolecta_procs(decs.dec());
		}
	}

	private void recolecta_procs(Dec dec){
		if(dec.es_dec_proc()){
			Dec_proc p = (Dec_proc)dec;
			procs.push(p);
		}
	}

	private void recolecta_procs(LDecs ldec){
		recolecta_procs(ldec.ldecs());
		recolecta_procs(ldec.dec());
	}

}