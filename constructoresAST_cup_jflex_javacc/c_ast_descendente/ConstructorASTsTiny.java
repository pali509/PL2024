/* Generated By:JavaCC: Do not edit this line. ConstructorASTsTiny.java */
package c_ast_descendente;

import asint.SintaxisAbstractaTiny.*;
import asint.ClaseSemanticaTiny;
import c_ast_ascendente.UnidadLexica.StringLocalizado;

public class ConstructorASTsTiny implements ConstructorASTsTinyConstants {
  private ClaseSemanticaTiny sem = new ClaseSemanticaTiny();

  final public Prog analiza() throws ParseException {
    trace_call("analiza");
    try {
      Prog prog;
      prog = programa();
      jj_consume_token(0);
             {if (true) return prog;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("analiza");
    }
  }

  final public Prog programa() throws ParseException {
    trace_call("programa");
    try {
      Bloque bloque;
      bloque = bloque();
            {if (true) return sem.prog(bloque);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("programa");
    }
  }

  final public Bloque bloque() throws ParseException {
    trace_call("bloque");
    try {
          LDecsOpt decs_opt; LInsOpt ins_opt;
      jj_consume_token(35);
      decs_opt = declaraciones_opt();
      ins_opt = instrucciones_opt();
      jj_consume_token(36);
                  {if (true) return sem.bloque(decs_opt, ins_opt);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("bloque");
    }
  }

  final public LDecsOpt declaraciones_opt() throws ParseException {
    trace_call("declaraciones_opt");
    try {
         LDecs decs;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case int_:
      case real:
      case bool:
      case string:
      case proc:
      case struct:
      case type:
      case identificador:
      case 45:
        decs = lista_decs();
        jj_consume_token(37);
                  {if (true) return sem.si_decs(decs);}
        break;
      default:
        jj_la1[0] = jj_gen;
         {if (true) return sem.no_decs();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("declaraciones_opt");
    }
  }

  final public LInsOpt instrucciones_opt() throws ParseException {
    trace_call("instrucciones_opt");
    try {
         LIns ins;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case if_:
      case while_:
      case new_:
      case delete:
      case read:
      case write:
      case nl:
      case call:
      case 35:
      case 48:
        ins = lista_ins();
                  {if (true) return sem.si_ins(ins);}
        break;
      default:
        jj_la1[1] = jj_gen;
         {if (true) return sem.no_ins();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("instrucciones_opt");
    }
  }

  final public LDecs lista_decs() throws ParseException {
    trace_call("lista_decs");
    try {
      LDecs decs;Dec dec;
      dec = declaracion();
      decs = rlista_decs(sem.una_dec(dec));
        {if (true) return  decs;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_decs");
    }
  }

  final public LDecs rlista_decs(LDecs rdecs) throws ParseException {
    trace_call("rlista_decs");
    try {
          LDecs decs; Dec dec;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 38:
        jj_consume_token(38);
        dec = declaracion();
        decs = rlista_decs(sem.muchas_decs(rdecs,dec));
            {if (true) return decs;}
        break;
      default:
        jj_la1[2] = jj_gen;
                  {if (true) return rdecs;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_decs");
    }
  }

  final public Dec declaracion() throws ParseException {
    trace_call("declaracion");
    try {
   Token id;Token a; Tipo t; PFormOpt pform_opt; Bloque bloque;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case int_:
      case real:
      case bool:
      case string:
      case struct:
      case identificador:
      case 45:
        t = tipo();
        id = jj_consume_token(identificador);
      {if (true) return (Dec)sem.dec_var(t, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      case type:
        a = jj_consume_token(type);
        t = tipo();
        id = jj_consume_token(identificador);
          {if (true) return (Dec)sem.dec_tipo(t, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      case proc:
        a = jj_consume_token(proc);
        id = jj_consume_token(identificador);
        jj_consume_token(39);
        pform_opt = parametrosFormales_opt();
        jj_consume_token(40);
        bloque = bloque();
         {if (true) return (Dec)sem.dec_proc(id.image, pform_opt, bloque).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("declaracion");
    }
  }

  final public PFormOpt parametrosFormales_opt() throws ParseException {
    trace_call("parametrosFormales_opt");
    try {
   LPForm params;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case int_:
      case real:
      case bool:
      case string:
      case struct:
      case identificador:
      case 45:
        params = lista_pforms();
          {if (true) return sem.si_pforms(params);}
        break;
      default:
        jj_la1[4] = jj_gen;
         {if (true) return sem.no_pforms();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parametrosFormales_opt");
    }
  }

  final public LPForm lista_pforms() throws ParseException {
    trace_call("lista_pforms");
    try {
      LPForm params; PForm param;
      param = parametroFormal();
      params = rlista_pforms(sem.un_pform(param));
        {if (true) return  params;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_pforms");
    }
  }

  final public LPForm rlista_pforms(LPForm rparams) throws ParseException {
    trace_call("rlista_pforms");
    try {
          LPForm params; Pform param;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 41:
        jj_consume_token(41);
        param = parametroFormal();
        params = rlista_pforms(sem.muchos_pforms(rparams,param));
            {if (true) return params;}
        break;
      default:
        jj_la1[5] = jj_gen;
                  {if (true) return rparams;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_pforms");
    }
  }

  final public Pform parametroFormal() throws ParseException {
    trace_call("parametroFormal");
    try {
          Tipo t; Token id;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case int_:
      case real:
      case bool:
      case string:
      case struct:
      case identificador:
      case 45:
        t = tipo();
        jj_consume_token(42);
        id = jj_consume_token(identificador);
                  {if (true) return sem.pfref(t, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
        t = tipo();
        id = jj_consume_token(identificador);
                  {if (true) return sem.pfnoref(t, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parametroFormal");
    }
  }

  final public Tipo tipo() throws ParseException {
    trace_call("tipo");
    try {
         Tipo t1; Tipo t;
      t1 = tipo1();
      t = rtipo(t1);
          {if (true) return t;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo");
    }
  }

  final public Tipo rtipo(Tipo r) throws ParseException {
    trace_call("rtipo");
    try {
         Token litEntero; Tipo t;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        jj_consume_token(43);
        litEntero = jj_consume_token(literalEntero);
        jj_consume_token(44);
        t = rtipo(sem.array(r, litEntero.image).ponFila(litEntero.beginLine).ponCol(litEntero.beginColumn));
           {if (true) return t;}
        break;
      default:
        jj_la1[7] = jj_gen;
           {if (true) return r;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rtipo");
    }
  }

  final public Tipo tipo1() throws ParseException {
    trace_call("tipo1");
    try {
   Tipo t1; Tipo t2;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 45:
        jj_consume_token(45);
        t1 = tipo1();
      {if (true) return sem.puntero(t1);}
        break;
      case int_:
      case real:
      case bool:
      case string:
      case struct:
      case identificador:
        t2 = tipo2();
          {if (true) return t2;}
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo1");
    }
  }

  final public Tipo tipo2() throws ParseException {
    trace_call("tipo2");
    try {
     Token t; LCamp campos;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case identificador:
        t = jj_consume_token(identificador);
        {if (true) return sem.iden(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case struct:
        t = jj_consume_token(struct);
        jj_consume_token(46);
        campos = lista_campos();
        jj_consume_token(47);
        {if (true) return sem.struct(campos);}
        break;
      case int_:
        t = jj_consume_token(int_);
        {if (true) return sem.lit_ent();}
        break;
      case real:
        t = jj_consume_token(real);
        {if (true) return sem.lit_real();}
        break;
      case bool:
        t = jj_consume_token(bool);
        {if (true) return sem.lit_bool();}
        break;
      case string:
        t = jj_consume_token(string);
        {if (true) return sem.lit_string();}
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo2");
    }
  }

  final public LCamp lista_campos() throws ParseException {
    trace_call("lista_campos");
    try {
      LCamp campos;Camp campo;
      campo = campo();
      campos = rlista_campos(sem.un_camp(campo));
        {if (true) return  campos;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_campos");
    }
  }

  final public LCamp rlista_campos(LCamp rcampos) throws ParseException {
    trace_call("rlista_campos");
    try {
          LCamp campos; Camp campo;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 41:
        jj_consume_token(41);
        campo = campo();
        campos = rlista_campos(sem.muchos_camp(rcampos,campo));
            {if (true) return campos;}
        break;
      default:
        jj_la1[10] = jj_gen;
                  {if (true) return rcampos;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_campos");
    }
  }

  final public Camp campo() throws ParseException {
    trace_call("campo");
    try {
         Tipo t;Token id;
      t = tipo();
      id = jj_consume_token(identificador);
            {if (true) return sem.camp(t, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("campo");
    }
  }

  final public LIns lista_ins() throws ParseException {
    trace_call("lista_ins");
    try {
      LIns ins; Ins in;
      in = instruccion();
      ins = rlista_ins(sem.una_ins(in));
        {if (true) return  ins;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_ins");
    }
  }

  final public LIns rlista_ins(LIns rins) throws ParseException {
    trace_call("rlista_ins");
    try {
          LIns  ins; Ins in;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 38:
        jj_consume_token(38);
        in = instruccion();
        ins = rlista_ins(sem.muchas_ins(rins,in));
            {if (true) return ins;}
        break;
      default:
        jj_la1[11] = jj_gen;
                  {if (true) return rins;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_ins");
    }
  }

  final public Ins instruccion() throws ParseException {
    trace_call("instruccion");
    try {
   Token t, t1; Exp e0; Bloque bloque, bloque1; Token id; LPReal params_opt;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 48:
        jj_consume_token(48);
        e0 = e0();
      {if (true) return sem.ins_asig(e0);}
        break;
      case if_:
        t = jj_consume_token(if_);
        e0 = e0();
        bloque = bloque();
      {if (true) return sem.ins_if(e0, bloque);}
        break;
        t = jj_consume_token(if_);
        e0 = e0();
        bloque = bloque();
        t1 = jj_consume_token(else_);
        bloque1 = bloque();
     {if (true) return sem.ins_if_else(e0, bloque, bloque1);}
        break;
      case while_:
        t = jj_consume_token(while_);
        e0 = e0();
        bloque = bloque();
     {if (true) return sem.ins_while(e0, bloque);}
        break;
      case read:
        t = jj_consume_token(read);
        e0 = e0();
     {if (true) return sem.ins_read(e0);}
        break;
      case write:
        t = jj_consume_token(write);
        e0 = e0();
     {if (true) return sem.ins_write(e0);}
        break;
      case nl:
        t = jj_consume_token(nl);
     {if (true) return sem.ins_nl();}
        break;
      case new_:
        t = jj_consume_token(new_);
        e0 = e0();
     {if (true) return sem.ins_new(e0);}
        break;
      case delete:
        t = jj_consume_token(delete);
        e0 = e0();
     {if (true) return sem.ins_delete(e0);}
        break;
      case call:
        t = jj_consume_token(call);
        id = jj_consume_token(identificador);
        jj_consume_token(39);
        params_opt = parametrosReales_opt();
        jj_consume_token(40);
     {if (true) return sem.ins_call(id.image, params_opt).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      case 35:
        bloque = bloque();
          {if (true) return sem.ins_bloque(bloque);}
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("instruccion");
    }
  }

  final public LPRealOpt parametrosReales_opt() throws ParseException {
    trace_call("parametrosReales_opt");
    try {
         LPReal params;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case null_:
      case true_:
      case false_:
      case identificador:
      case literalEntero:
      case literalReal:
      case literalCadena:
      case 39:
      case 51:
      case 62:
        params = lista_preal();
                  {if (true) return sem.si_preal(params);}
        break;
      default:
        jj_la1[13] = jj_gen;
         {if (true) return sem.no_preal();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("parametrosReales_opt");
    }
  }

  final public LPReal lista_preal() throws ParseException {
    trace_call("lista_preal");
    try {
      LPReal params; Exp par;
      par = e0();
      params = rlista_preal(sem.un_preal(par));
        {if (true) return  params;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_preal");
    }
  }

  final public LPReal rlista_preal(LPReal rparams) throws ParseException {
    trace_call("rlista_preal");
    try {
         LPReal params; Exp par;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 41:
        jj_consume_token(41);
        par = e0();
        params = rlista_preal(sem.muchos_preal(rparams,par));
            {if (true) return params;}
        break;
      default:
        jj_la1[14] = jj_gen;
                  {if (true) return rparams;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_preal");
    }
  }

  final public Exp e0() throws ParseException {
    trace_call("e0");
    try {
     Exp e0, e1;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case null_:
      case true_:
      case false_:
      case identificador:
      case literalEntero:
      case literalReal:
      case literalCadena:
      case 39:
      case 51:
      case 62:
        e1 = e1();
          {if (true) return e1;}
        break;
        e1 = e1();
        jj_consume_token(49);
        e0 = e0();
         {if (true) return sem.asig(e1, e0);}
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e0");
    }
  }

  final public Exp e1() throws ParseException {
    trace_call("e1");
    try {
         Exp e1, e2;
      e2 = e2();
      e1 = re1(e2);
                 {if (true) return e1;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e1");
    }
  }

  final public Exp re1(Exp re2) throws ParseException {
    trace_call("re1");
    try {
   String op1; Exp e2, e0;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
        op1 = op1();
        e2 = e2();
        e0 = re1(sem.mkop2(re2, op1, e2));
     {if (true) return e0;}
        break;
      default:
        jj_la1[16] = jj_gen;
          {if (true) return  re2;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re1");
    }
  }

  final public Exp e2() throws ParseException {
    trace_call("e2");
    try {
   Exp e2, e3, minus;
      e3 = e3();
      minus = minus(e3);
      e2 = re2(minus);
      {if (true) return e2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e2");
    }
  }

  final public Exp re2(Exp re3) throws ParseException {
    trace_call("re2");
    try {
   Exp e3, e2;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 50:
        jj_consume_token(50);
        e3 = e3();
        e2 = re2(sem.mkop2(re3, "+",e3));
         {if (true) return e2;}
        break;
      default:
        jj_la1[17] = jj_gen;
         {if (true) return re3;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re2");
    }
  }

  final public Exp minus(Exp rminus) throws ParseException {
    trace_call("minus");
    try {
   Exp e3;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 51:
        jj_consume_token(51);
        e3 = e3();
      {if (true) return sem.mkop2(rminus, "-", e3);}
        break;
      default:
        jj_la1[18] = jj_gen;
          {if (true) return rminus;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("minus");
    }
  }

  final public Exp e3() throws ParseException {
    trace_call("e3");
    try {
   Exp e3, e4, e41;Token t;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case null_:
      case true_:
      case false_:
      case identificador:
      case literalEntero:
      case literalReal:
      case literalCadena:
      case 39:
      case 51:
      case 62:
        e4 = e4();
        t = jj_consume_token(and);
        e3 = e3();
         {if (true) return (Exp)sem.and(e4, e3);}
        break;
        e4 = e4();
        t = jj_consume_token(or);
        e41 = e4();
         {if (true) return (Exp)sem.or(e4, e41);}
        break;
        e4 = e4();
         {if (true) return e4;}
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e3");
    }
  }

  final public Exp e4() throws ParseException {
    trace_call("e4");
    try {
         Exp e4, e4;
      e5 = e5();
      e4 = re4(e5);
                 {if (true) return e4;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e4");
    }
  }

  final public Exp re4(Exp re4) throws ParseException {
    trace_call("re4");
    try {
   String op1; Exp e5, e4;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
      case 60:
      case 61:
        op1 = op4();
        e5 = e5();
        e4 = re4(sem.mkop2(re4, op1, e5));
     {if (true) return e4;}
        break;
      default:
        jj_la1[20] = jj_gen;
          {if (true) return  re4;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re4");
    }
  }

  final public Exp e5() throws ParseException {
    trace_call("e5");
    try {
   String op5; Exp e5, e5;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 51:
      case 62:
        op5 = op5();
        e5 = e5();
      {if (true) return sem.mkop1(op5, e5);}
        break;
      case null_:
      case true_:
      case false_:
      case identificador:
      case literalEntero:
      case literalReal:
      case literalCadena:
      case 39:
        e6 = e6();
           {if (true) return e6;}
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e5");
    }
  }

  final public Exp e6() throws ParseException {
    trace_call("e6");
    try {
   Exp e7, e6;
      e7 = e7();
      e6 = re6(e7);
      {if (true) return r6;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e6");
    }
  }

  final public Exp re6(Exp re6) throws ParseException {
    trace_call("re6");
    try {
   Exp e0, e6; Token id;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 43:
        jj_consume_token(43);
        e0 = e0();
        jj_consume_token(44);
         {if (true) return (Exp)sem.accesoArray(re6, e0);}
        break;
      case 52:
        jj_consume_token(52);
        id = jj_consume_token(identificador);
         {if (true) return  (Exp)sem.accesoCampo(re6, id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      case 45:
        jj_consume_token(45);
         {if (true) return (Exp)sem.accesoPuntero(re6);}
        break;
      default:
        jj_la1[22] = jj_gen;
         {if (true) return re6;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re6");
    }
  }

  final public Exp e7() throws ParseException {
    trace_call("e7");
    try {
   Token t; Exp e0;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case literalEntero:
        t = jj_consume_token(literalEntero);
      {if (true) return (Exp)sem.exp_lit_ent(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case literalReal:
        t = jj_consume_token(literalReal);
            {if (true) return (Exp)sem.exp_lit_real(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case true_:
        t = jj_consume_token(true_);
            {if (true) return (Exp)sem.exp_lit_BoolTrue(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case false_:
        t = jj_consume_token(false_);
            {if (true) return (Exp)sem.exp_lit_BoolFalse(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case literalCadena:
        t = jj_consume_token(literalCadena);
            {if (true) return (Exp)sem.exp_lit_cadena(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case identificador:
        t = jj_consume_token(identificador);
           {if (true) return (Exp)sem.exp_iden(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case null_:
        t = jj_consume_token(null_);
            {if (true) return (Exp)sem.exp_null();}
        break;
      case 39:
        jj_consume_token(39);
        e0 = e0();
        jj_consume_token(40);
           {if (true) return e0;}
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e7");
    }
  }

  final public String op1() throws ParseException {
    trace_call("op1");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 53:
        jj_consume_token(53);
          {if (true) return ">";}
        break;
      case 54:
        jj_consume_token(54);
          {if (true) return "<";}
        break;
      case 55:
        jj_consume_token(55);
          {if (true) return ">=";}
        break;
      case 56:
        jj_consume_token(56);
          {if (true) return "<=";}
        break;
      case 57:
        jj_consume_token(57);
          {if (true) return "==";}
        break;
      case 58:
        jj_consume_token(58);
          {if (true) return "!=";}
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("op1");
    }
  }

  final public String op4() throws ParseException {
    trace_call("op4");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        jj_consume_token(59);
           {if (true) return "*";}
        break;
      case 60:
        jj_consume_token(60);
           {if (true) return "/";}
        break;
      case 61:
        jj_consume_token(61);
           {if (true) return "%";}
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("op4");
    }
  }

  final public String op5() throws ParseException {
    trace_call("op5");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 51:
        jj_consume_token(51);
           {if (true) return "-";}
        break;
      case 62:
        jj_consume_token(62);
           {if (true) return "not";}
        break;
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("op5");
    }
  }

  /** Generated Token Manager. */
  public ConstructorASTsTinyTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[27];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20411780,0xbea000,0x0,0x20411780,0x20010780,0x0,0x20010780,0x0,0x20010780,0x20010780,0x0,0x0,0xbea000,0xe3000800,0x0,0xe3000800,0x0,0x0,0x0,0xe3000800,0x0,0xe3000800,0x0,0xe3000800,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x2000,0x10008,0x40,0x2000,0x2000,0x200,0x2000,0x800,0x2000,0x0,0x200,0x40,0x10008,0x40080081,0x200,0x40080081,0x7e00000,0x40000,0x80000,0x40080081,0x38000000,0x40080081,0x102800,0x81,0x7e00000,0x38000000,0x40080000,};
   }

  /** Constructor with InputStream. */
  public ConstructorASTsTiny(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ConstructorASTsTiny(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ConstructorASTsTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ConstructorASTsTiny(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ConstructorASTsTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ConstructorASTsTiny(ConstructorASTsTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ConstructorASTsTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      trace_token(token, "");
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
      trace_token(token, " (in getNextToken)");
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[63];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 27; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 63; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  private int trace_indent = 0;
  private boolean trace_enabled = true;

/** Enable tracing. */
  final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
    trace_enabled = false;
  }

  private void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  private void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  private void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + t.image + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  private void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + t1.image + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

        }
