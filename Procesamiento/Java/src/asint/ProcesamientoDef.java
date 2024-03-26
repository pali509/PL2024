package asint;

import asint.SintaxisAbstractaEval.*;


public class ProcesamientoDef implements Procesamiento {
    public void procesa(Dec dec) {}
    public void procesa(Muchas_decs decs) {}
    public void procesa(Una_dec decs) {}
    public void procesa(Si_decs decs) {}
    public void procesa(No_decs decs) {}
    public void procesa(Suma exp) {}
    public void procesa(Resta exp) {}
    public void procesa(Mul exp) {}
    public void procesa(Div exp) {}
    public void procesa(Lit_ent exp) {}
    public void procesa(Lit_real exp) {}
    public void procesa(Iden exp) {}
    public void procesa(Prog prog) {}

    public void procesa(SintaxisAbstractaEval.Mod exp) {

    }
    public void procesa(SintaxisAbstractaEval.Asig exp) {

    }

    public void procesa(SintaxisAbstractaEval.Mayor exp) {

    }

    public void procesa(SintaxisAbstractaEval.Menor exp) {

    }
    public void procesa(SintaxisAbstractaEval.MayorIg exp) {

    }

    public void procesa(SintaxisAbstractaEval.MenorIg exp) {

    }

    public void procesa(SintaxisAbstractaEval.Igual exp) {

    }

    public void procesa(SintaxisAbstractaEval.Desigual exp) {

    }

    public void procesa(SintaxisAbstractaEval.Neg exp) {

    }

    public void procesa(SintaxisAbstractaEval.Not exp) { }
}
