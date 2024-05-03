package asint;

import asint.SintaxisAbstractaTiny.*;

public interface Procesamiento {

    void procesa(Muchas_decs decs);
    void procesa(Una_dec decs);
    void procesa(Si_decs decs);
    void procesa(No_decs decs);
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Exp_lit_ent exp);
    void procesa(Exp_lit_real exp);


    //Nuevas:
    void procesa(Mod exp);
    void procesa(Asig exp);
    void procesa(Mayor exp);
    void procesa(Menor exp);
    void procesa(MayorIg exp);
    void procesa(MenorIg exp);
    void procesa(Igual exp);
    void procesa(Desigual exp);
    void procesa(Neg exp);

    void procesa(Un_PReal exp);

    void procesa(Exp_Iden exp);

    void procesa(Exp_lit_cadena exp);

    void procesa(Exp_lit_BoolTrue exp);

    void procesa(Exp_lit_BoolFalse exp);

    void procesa(Exp_null exp);
    void procesa(AccesoArray exp);
    void procesa(AccesoPuntero exp);
    void procesa(AccesoCampo exp);
    void procesa(And exp);
    void procesa(Or exp);

    void procesa(Lit_ent t);

    void procesa(Lit_real t);

    void procesa(Lit_bool t);
    void procesa(Lit_string t);
    void procesa(Iden t);
    void procesa(Array t);
    void procesa(Prog prog);
    void procesa(Puntero t);

    void procesa(Struct t);

    void procesa(PFref pform);
    void procesa(PFnoref pform);
    void procesa(PFormOpt pforms);
    void procesa(Si_pforms pforms);
    void procesa(No_pforms pforms);
    void procesa(Muchos_pforms pforms);
    void procesa(Un_pform pform);

    void procesa(Dec_var dec);
    void procesa(Dec_tipo dec);
    void procesa(Dec_proc dec);

    void procesa(Bloque bloque);

    void procesa(Camp campo);

    void procesa(Muchos_camp camps);
    void procesa(Un_camp camp);

    void procesa(Ins_asig ins);

    void procesa(Ins_read ins);

    void procesa(Ins_write ins);

    void procesa(Ins_new ins);

    void procesa(Ins_delete ins);

    void procesa(Ins_nl ins);

    void procesa(Ins_if ins);

    void procesa(Ins_if_else ins);

    void procesa(Ins_while ins);

    void procesa(Ins_call ins);
    void procesa(Ins_bloque ins);

    void procesa(Una_ins ins);

    void procesa(Muchas_ins ins);

    void procesa(Si_preal lpreal);

    void procesa(No_preal lpreal);

    void procesa(Muchos_preal lpreal);


    void procesa(Not exp);

    void procesa(LDecsOpt ldecs);
    void procesa(LInsOpt ins);
    void procesa(Si_Ins ins);

    void procesa(No_Ins ins);
}
