package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;


public class Asignacion_espacio extends ProcesamientoDef{
	
	private int dir = 0;
	private int nivel = 0;
	private int max_dir = 0;
	private int desplazamiento = 0;
	private Asignacion_espacio2 asig = new Asignacion_espacio2();
	
	
	public void procesa(Prog prog) {
		prog.bq().procesa(this);
	}
	
	public void procesa(Bloque b){
		int dir_ant = dir;
		b.lds().procesa(this);
		b.lis().procesa(this);
		dir = dir_ant;
		
	}
	
	public void procesa(Si_decs s) {
		s.decs().procesa(this);
		s.decs().procesa(asig);

	}
	
	public void asig_espacio(No_decs n) {}
	
	
	//PRIMERA PASADA EN LA SECCION DE DECLARACIONES 
	public void procesa(Muchas_decs decs) {
		 decs.ldecs().procesa(this);
	     decs.dec().procesa(this);
	}
	
	public void procesa(Una_dec dec) {
		dec.dec().procesa(this);
	}
	
	public void procesa(Dec_var dec) {
		dec.set_dir(dir);
		dec.set_nivel(nivel);
	    asig_tam(dec.tipo());
	    dir += dec.tipo().getTam();
		
	}
	public void procesa(Dec_tipo dec) {
		asig_tam(dec.tipo());
	}
	
	public void procesa(Dec_proc dec) {
		int dir_ant = dir;
		int max_dir_ant = max_dir;
		nivel++;
		dec.set_nivel(nivel);
		dir = 0;
		max_dir = 0;
		dec.pf().procesa(this);
		dec.pf().procesa(asig);
		dec.bq().procesa(this);;
		dec.setTam(dir);
		dir = dir_ant;
		max_dir = max_dir_ant;
		nivel--;
	}
	
	public void procesa(Si_pforms pform) {
		pform.pforms().procesa(this);
	}
	
	public void procesa(No_pforms pform) {}
	
	public void procesa(Muchos_pforms pforms) {
		pforms.pforms().procesa(this);
		pforms.pform().procesa(this);
	}
	
	public void procesa(Un_pform pform) {
		pform.pform().procesa(this);;
	}
	
	public void procesa(PFref pform) {
		pform.set_dir(dir);
		pform.set_nivel(nivel);
		asig_tam(pform.t());
		dir++;
	}
	
	public void procesa(PFnoref pform) {
		pform.set_dir(dir);
		pform.set_nivel(nivel);
		asig_tam(pform.t());
		dir += pform.t().getTam();
	}
	
	
	
	
	
	//ASIGNAMOS TAMAÑO A LOS TIPOS EN DOS PASADAS
	
	public void asig_tam(Tipo t) {
		if(t.es_int()) {
			t.setTam(1);
		}
		else if (t.es_real()) {
			t.setTam(1);
		}
		else if(t.es_bool()) {
			t.setTam(1);
		}
		else if(t.es_string()) {
			t.setTam(1);
		}

		else if(t.es_struct()) {
			t.setTam(asig_tam(t.lcamp()));
		}
		
	}
	
	public void asig_tam(Puntero p) {
		if(!p.es_iden()) {
			asig_tam(p.tipo());
		}
		p.setTam(1);
	}
	
	public void asig_tam(Iden id) {
		Dec_tipo d = (Dec_tipo)id.getVinculo();
		id.setTam(d.tipo().getTam());
	}
	
	public void asig_tam(Array a) {
		asig_tam(a.tipo());
		a.setTam(a.num()* a.tipo().getTam());
	}
	
	
	public void asig_tam1(Tipo t) {
		if(t.es_int()) {}
		else if (t.es_real()) {}
		else if(t.es_bool()) {}
		else if(t.es_string()) {}
		else if(t.es_iden()) {}
		else if(t.es_struct()) {
			asig_tam1(t.lcamp());
		}
		
	}
	
	public void asig_tam1(Puntero p) {
		
		if(p.es_iden()) {
			Iden id = (Iden)p.tipo();
			Dec_tipo d = (Dec_tipo)id.getVinculo();
			asig_tam(d.tipo());
			id.setTam(d.tipo().getTam());
		}
		else {
			asig_tam1(p.tipo());
		}

	}
	
	public void asig_tam1(Array a) {
		asig_tam1(a.tipo());
	}
	
	
	public int asig_tam(LCamp lc) {
		int tam = -1;
		if(lc.es_un_campo()) {
			tam = asig_tam((Un_camp)lc);
		}
		else if(lc.es_muchos_campos()) {
			tam = asig_tam((Muchos_camp)lc);
		}
		return tam;
	}
	
	public void asig_tam1(LCamp lc) {
		if(lc.es_un_campo()) {
			asig_tam1((Un_camp)lc);
		}
		else if(lc.es_muchos_campos()) {
			asig_tam1((Muchos_camp)lc);
		}
	}
	
	
	public int asig_tam(Un_camp campo) {
		asig_tam(campo.campo().tipo());
		campo.campo().set_desp(0);
		return campo.campo().tipo().getTam();

	}
	
	public void asig_tam1(Un_camp campo) {
		campo.campo().set_desp(0);
		asig_tam1(campo.campo().tipo());
	}
	
	public int asig_tam(Muchos_camp campos) {
		int desp_actual = asig_tam(campos.lcs());
		Camp c = campos.campo();
		asig_tam(c.tipo());
		c.set_desp(desp_actual);
		return desp_actual + c.tipo().getTam();

	}
	
	public void asig_tam1(Muchos_camp campos) {
		asig_tam1(campos.lcs());
		asig_tam1(campos.campo().tipo());

	}
	
	
	
	//ASIGNACION DE ESPACIO A INSTRUCCIONES
	
	public void procesa(Si_Ins ins) {
		ins.ins().procesa(this);
	}
	
	public void procesa(No_Ins ins) {}
	
	public void procesa(Una_ins ins) {
		ins.ins().procesa(this);
	}
	public void procesa(Muchas_ins ins) {
		ins.li().procesa(this);
		ins.ins().procesa(this);
	}
	public void procesa(Ins_asig ins) {}
	public void procesa(Ins_if ins) {
		ins.bloque().procesa(this);
	}
	public void procesa(Ins_if_else ins) {
		ins.bloque().procesa(this);
		ins.bloque2().procesa(this);
	}
	public void procesa(Ins_while ins) {
		ins.bloque().procesa(this);
	}
	public void procesa(Ins_read ins) {}
	public void procesa(Ins_write ins) {}
	public void procesa(Ins_nl ins) {}
	public void procesa(Ins_new ins) {}
	public void procesa(Ins_delete ins) {}
	public void porcesa(Ins_call ins) {}
	public void procesa(Ins_bloque ins) {
		ins.bloque().procesa(this);
	}
	
	
	
	//SEGUNDA PASADA A LA SECCION DE DECLARACIONES
	private class Asignacion_espacio2 extends ProcesamientoDef {
		
		public void procesa(Muchas_decs decs) {
			 decs.ldecs().procesa(asig);
		     decs.dec().procesa(asig);
		}
		
		public void procesa(Una_dec dec) {
			dec.dec().procesa(asig);
		}
		
		public void procesa(Dec_var dec) {
		    asig_tam1(dec.tipo());
			
		}
		public void procesa(Dec_tipo dec) {
			asig_tam1(dec.tipo());
		}
		
		public void procesa(Dec_proc dec) {}
		
		public void procesa(Si_pforms pform) {
			pform.pforms().procesa(asig);
		}
		
		public void procesa(No_pforms pform) {}
		
		public void procesa(Muchos_pforms pforms) {
			pforms.pforms().procesa(asig);
			pforms.pform().procesa(asig);
		}
		
		public void procesa(Un_pform pform ) {
			pform.pform().procesa(asig);
		}
		
		public void procesa(PFref pform) {
			pform.set_dir(dir);
			pform.set_nivel(nivel);
			asig_tam1(pform.t());
			dir++;

		}
		
		public void procesa(PFnoref pf) {
			pf.set_dir(dir);
			pf.set_nivel(nivel);
			asig_tam1(pf.t());
			dir += pf.t().getTam();
		}
	}
}
