package asint;

import asint.SintaxisAbstractaEval.*;

public interface Procesamiento {
    void procesa(Dec dec);
    void procesa(Muchas_decs decs);
    void procesa(Una_dec decs);
    void procesa(Si_decs decs);
    void procesa(No_decs decs);
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Lit_ent exp);
    void procesa(Lit_real exp);
    void procesa(Iden exp);
    void procesa(Prog prog);

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
    void procesa(Not exp);

}
