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
    public void recolecta_procs(Si_decs s){ //TODO como que inconvertable types
        if(s.es_muchas_decs()) {
            Muchas_decs dnew = (Muchas_decs) s;
            recolecta_procs(dnew);
        }
        else if(s.es_una_dec()) {
            Una_dec dnew = (Una_dec) s;
            recolecta_procs(dnew);
        }
    }
    public void recolecta_procs(No_decs n){
        //NOOP
    }

    public void recolecta_procs(Muchas_decs m){
        m.ldecs().procesa(this);
        m.dec().procesa(this);
    }

    public void recolecta_procs(Una_dec u){
        recolecta_procs(u.dec());
    }
    public void recolecta_procs(Dec d){
        if(d.es_dec_proc()) {
            Dec_proc dnew = (Dec_proc) d;

            //TODO Es o eso o hacer otro recolecta para hacer mp.emit(apila)
            procs.push(dnew);
        }
    }


    public void procesa(Dec_proc dec_proc) {
        dec_proc.bq().procesa(this);
        /*
        mp.ponInstruccion(mp.desactiva(dec_proc.nivel(), dec_proc.tam_datos()));
        mp.ponInstruccion(mp.ir_Ind());
         */
        recolecta_procs(dec_proc.bq().lds());
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


	public void gen_acc_val(Exp e){ //TODO como declaro v?
		/*
		gen-acc-val(Exp):
		si es_designador(Exp):
			v = emit fetch(r)
		sino:
			v = r
	 */
	}
    public void procesa(Ins_asig i){
        i.e().procesa(this);
        //TODO Falta desechar el resultado
    }

    public void procesa(Ins_if i){
        i.e().procesa(this);
		gen_acc_val(i.e());
		mp.emit(mp.ir_f(this.sig));
		i.bloque().procesa(this);
    }

    public void procesa(Ins_if_else i){
		i.e().procesa(this);
		gen_acc_val(i.e());
		mp.emit(mp.ir_f(i.sig()));
		i.bloque().procesa(this);
		mp.emit(mp.ir_f(i.sig()));
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
        i.e().procesa(this);
		/*
		let id = string.vinculo, id.vinculo = dec_proc(_, PFormOpt , _) in
		emit activa($.vinculo.nivel, $.vinculo.tam, $.dir_sig)
		gen-paso-pf(PFormOpt, PRealOpt)
		emit ir-a($.vinculo.dir_inic)
		end let

		 */
    }

    public void procesa(Ins_read i){
		Tipo t = refI(i.e().tipo());
		if(t.es_int()){
			mp.emit(mp.leer_entrada_int());}
		else if(t.es_real()){
			mp.emit(mp.leer_entrada_real());}
		else if(t.es_string()){
			mp.emit(mp.leer_entrada_string());}
		mp.emit(mp.store(i.e().procesa(this), t));
    }

    public void procesa(Ins_write i){
		/*
		r = gen-cod(Exp)
		gen-acc-val(Exp)
		emite mostrar_<v>
		 */
    }

    public void procesa(Ins_nl i){
        //NOOP
    }

    public void procesa(Ins_new i){
        /*
        emite store(gen-cod(Exp), emite alloc(ref!(Exp.tipo)))

		dealloc(d, ), si d  -1. Error de ejecución si d = -1

         */
    }

    public void procesa(Ins_delete i){
        /*
        d = gen-cod(Exp)
	si d != -1:
		emite dealloc(d, ref!(E.tipo))
	sino:
		ERROR

         */
    }
	public void procesa(Ins_bloque i){
		/*
		sea LDecs de  Bloque(LDecs, LIns)
		para cada dec_var(Tipo, id) en en LDecs
		//si existe otra variable id
			salvaguardamos dir(id)
			gen-cod(LIns)
			dealloc(dir(id),Tipo)
			restaurar dir(id)

		//sino
		dir(id)
		alloc()
		gen-cod(LIns)
		dealloc(dir(id),)

		 */
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
    	/*
    	if(e.getVinculo().getNivel() != 0) {
    		mp.apilad(e.getVinculo().getNivel());
    		mp.apila_int(e.getVinculo().getDir());
    		mp.suma_int();
    		if(e.getVinculo().es_parf_ref()){
    			mp.apila_int();
    	}
    	else{
    		mp.apila_int(e.getVinculo().getDir());
    	
    	*/
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
    	if(s.opnd0().tipo().es_bool() && s.opnd1().tipo().es_bool()) { //TODO COMPROBAR ESTO
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
    		mp.igual_raro();
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
    			mp.desigual_null();
    		}
    	}
    	else if(t0.es_puntero() && t1.es_null() || t0.es_null() && t1.es_puntero()) {
    		mp.desigual_raro();
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
    
    public void procesa(AccesoCampo s){ //TODO que esta mal de esto?
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
    	boolean a = false;
    	if(e.getVinculo() != null) {
    		a = true;
    	}
    	return a;
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

}