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

    public void procesa(Dec_proc d){
        procs.push(d);
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

    }

    public void procesa(Exp_lit_real e){

    }

    public void procesa(Exp_lit_cadena e){

    }

    public void procesa(Exp_Iden e){

    }

    public void procesa(Exp_lit_BoolTrue e){

    }

    public void procesa(Exp_lit_BoolFalse e){

    }

    public void procesa(Exp_null e){

    }

    public void procesa(Asig a){

    }

    public void procesa(Suma s){

    }

    public void procesa(Resta r){

    }

    public void procesa(Mul m){

    }

    public void procesa(Div d){

    }

    public void procesa(Mod m){

    }

    public void procesa(And a){

    }

    public void procesa(Or o){

    }

    public void procesa(Mayor ma){

    }

    public void procesa(Menor me){

    }

    public void procesa(MayorIg mai){

    }

    public void procesa(MenorIg mei){

    }

    public void procesa(Igual ig){

    }

    public void procesa(Desigual de){

    }

    public void procesa(Not no){

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