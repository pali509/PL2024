package maquinaP;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.lang.model.type.NullType;

import asint.SintaxisAbstractaTiny.*;



public class MaquinaP {
   public static class EAccesoIlegitimo extends RuntimeException {} 
   public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
      public EAccesoAMemoriaNoInicializada(int pc,int dir) {
         super("pinst:"+pc+" dir:"+dir); 
      } 
   } 
   public static class EAccesoFueraDeRango extends RuntimeException {} 
   
   private GestorMemoriaDinamica gestorMemoriaDinamica;
   private GestorPilaActivaciones gestorPilaActivaciones;
    
   private class Valor {
      public int valorInt() {throw new EAccesoIlegitimo();}  
      public boolean valorBool() {throw new EAccesoIlegitimo();}
      public double valorReal() {throw new EAccesoIlegitimo();}
      public String valorString() {throw new EAccesoIlegitimo();}
      public NullType valorNull() {throw new EAccesoIlegitimo();}
   }

   private class ValorInt extends Valor {
      private int valor;
      public ValorInt(int valor) {
         this.valor = valor; 
      }
      public int valorInt() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }

   private class ValorBool extends Valor {
      private boolean valor;
      public ValorBool(boolean valor) {
         this.valor = valor; 
      }
      public boolean valorBool() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }

   private class ValorReal extends Valor{
      private double valor;
      public ValorReal(double valor) {
         this.valor = valor; 
      }
      public double valorReal() {return valor;}
      public String toString() {
        return String.valueOf(valor);
      }
   }

   private class ValorString extends Valor{
      private String valor;
      public ValorString(String valor) {
         this.valor = valor; 
      }
      public String valorString() {return valor;}
   }

   private class ValorNull extends Valor{
      private NullType valor;
      public ValorNull(NullType valor){
         this.valor = valor;
      }
      public NullType valorNull() {return valor;}
      public String toString(){
         return String.valueOf(valor);
      }
   }

   //PUEDE QUE FALTE VALORPUNTERO

   private List<Instruccion> codigoP;
   private Stack<Valor> pilaEvaluacion;
   private Valor[] datos; 
   private int pc;

   public interface Instruccion {
      void ejecuta();  
   }

   private class IApilaInt implements Instruccion {
      private int valor;
      public IApilaInt(int valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(valor)); 
         pc++;
      } 
      public String toString() {return "apila-int("+valor+")";};
   }

   private class IApilaBool implements Instruccion {
      private boolean valor;
      public IApilaBool(boolean valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorBool(valor)); 
         pc++;
      } 
      public String toString() {return "apila-bool("+valor+")";};
   }

   private class IApilaReal implements Instruccion {
      private double valor;
      public IApilaReal(double valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorReal(valor)); 
         pc++;
      } 
      public String toString() {return "apila-real("+valor+")";};
   }

   private class IApilaString implements Instruccion {
      private String valor;
      public IApilaString(String valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorString(valor)); 
         pc++;
      } 
      public String toString() {return "apila-string("+valor+")";};
   }

    private IApilaDir IAPILADIR;
    private class IApilaDir implements Instruccion {
        private int dir;
        public IApilaDir(int dir) {
            this.dir = dir;
        }
        public void ejecuta() {
            if (dir >= datos.length) throw new EAccesoFueraDeRango();
            if (datos[dir] == null)  throw new EAccesoAMemoriaNoInicializada(pc,dir);
            pilaEvaluacion.push(datos[dir]);
            pc++;
        }
        public String toString() {return "apila-dir("+dir+")";};
    }

    private IDesapilaDir IDESAPILADIR;

    private class IDesapilaDir implements Instruccion {
        private int dir;
        public IDesapilaDir(int dir) {
            this.dir = dir;
        }
        public void ejecuta() {
            Valor valor = pilaEvaluacion.pop();
            if (dir >= datos.length) throw new EAccesoFueraDeRango();
            datos[dir] = valor;
            pc++;
        }
        public String toString() {return "desapila-dir("+dir+")";};
    }


   private IApilaind IAPILAIND;
   private class IApilaind implements Instruccion {
      public void ejecuta() {
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        if (datos[dir] == null)  throw new EAccesoAMemoriaNoInicializada(pc,dir);
        pilaEvaluacion.push(datos[dir]);
        pc++;
      } 
      public String toString() {return "apila-ind";};
   }

   private IDesapilaind IDESAPILAIND;
   private class IDesapilaind implements Instruccion {
      public void ejecuta() {
        Valor valor = pilaEvaluacion.pop();
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        datos[dir] = valor;
        pc++;
      } 
      public String toString() {return "desapila-ind";};
   }

   private class IApilad implements Instruccion {
      private int nivel;
      public IApilad(int nivel) {
        this.nivel = nivel;  
      }
      public void ejecuta() {
        pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
        pc++;
      }
      public String toString() {
         return "apilad("+nivel+")";                 
      }
   }

   //AQUI CREO QUE IGUAL HAY QUE ELIMINAR ESTA, NO PARECE QUE SE USE
   private class IDesapilad implements Instruccion {
      private int nivel;
      public IDesapilad(int nivel) {
        this.nivel = nivel;  
      }
      public void ejecuta() {
        gestorPilaActivaciones.fijaDisplay(nivel,pilaEvaluacion.pop().valorInt());  
        pc++;
      }
      public String toString() {
         return "desapilad("+nivel+")";                 
      }
   }

   private ISuma ISUMA;
   private class ISuma implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()+opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "suma int";};
   }

   private IResta IRESTA;
   private class IResta implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()-opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "resta int";};
   }

   private IMul IMUL;
   private class IMul implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()*opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mul int";};
   }

   private IDiv IDIV;
   private class IDiv implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()/opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "div int";};
   }

   private INeg INEG;
   private class INeg implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(-opnd1.valorInt()));
         pc++;
      } 
      public String toString() {return "neg int";};
   }
   private IRSuma RSUMA;
   private class IRSuma implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()+opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "suma real";};
   }

   private IRResta RRESTA;
   private class IRResta implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()-opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "resta real";};
   }

   private IRMul RMUL;
   private class IRMul implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()*opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mul real";};
   }

   private IRDiv RDIV;
   private class IRDiv implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()/opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "div real";};
   }

   private IRNeg RNEG;
   private class IRNeg implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(-opnd1.valorReal()));
         pc++;
      } 
      public String toString() {return "neg real";};
   }

   private IMod MOD;
   private class IMod implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()%opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mod";};
   }

   private INot INOT;
   private class INot implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(!opnd1.valorBool()));
         pc++;
      } 
      public String toString() {return "not";};
   }

   private IAnd IAND;
   private class IAnd implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool()&&opnd2.valorBool()));
         pc++;
      } 
      public String toString() {return "and";};
   }

   private IOr IOR;
   private class IOr implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool()||opnd2.valorBool()));
         pc++;
      }
      public String toString() {return "or";};
   }

   private IMenorInt IMENORINT;
   private class IMenorInt implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() < opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "menor int";};
   }

   private IMenorReal IMENORREAL;
   private class IMenorReal implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() < opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "menor real";};
   }

   private IMenorBool IMENORBOOL;
   private class IMenorBool implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(!opnd1.valorBool() && opnd2.valorBool()));
         pc++;
      }
      public String toString() {return "menor bool";};
   }

   private IMenorString IMENORSTRING;
   private class IMenorString implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())<0));
         pc++;
      }
      public String toString() {return "menor string";};
   }
   
   private IMayorInt IMAYORINT;
   private class IMayorInt implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() > opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "mayor int";};
   }

   private IMayorReal IMAYORREAL;
   private class IMayorReal implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() > opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "mayor real";};
   }

   private IMayorBool IMAYORBOOL;
   private class IMayorBool implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && !opnd2.valorBool()));
         pc++;
      }
      public String toString() {return "mayor bool";};
   }

   private IMayorString IMAYORSTRING;
   private class IMayorString implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())>0));
         pc++;
      }
      public String toString() {return "mayor string";};
   }

   private IMenorIgualInt IMENORIGUALINT;
   private class IMenorIgualInt implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() <= opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "menor igual int";};
   }

   private IMenorIgualReal IMENORIGUALREAL;
   private class IMenorIgualReal implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() <= opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "menor igual real";};
   }

   private IMenorIgualBool IMENORIGUALBOOL;
   private class IMenorIgualBool implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool((!opnd1.valorBool() && opnd2.valorBool()) || (opnd1.valorBool() == opnd2.valorBool())));
         pc++;
      }
      public String toString() {return "menor igual bool";};
   }

   private IMenorIgualString IMENORIGUALSTRING;
   private class IMenorIgualString implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())<=0));
         pc++;
      }
      public String toString() {return "menor igual string";};
   }

   private IMayorIgualInt IMAYORIGUALINT;
   private class IMayorIgualInt implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() >= opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "mayor igual int";};
   }

   private IMayorIgualReal IMAYORIGUALREAL;
   private class IMayorIgualReal implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() <= opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "mayor igual real";};
   }

   private IMayorIgualBool IMAYORIGUALBOOL;
   private class IMayorIgualBool implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool((opnd1.valorBool() && !opnd2.valorBool()) || (opnd1.valorBool() == opnd2.valorBool())));
         pc++;
      }
      public String toString() {return "mayor igual bool";};
   }

   private IMayorIgualString IMAYORIGUALSTRING;
   private class IMayorIgualString implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())>=0));
         pc++;
      }
      public String toString() {return "mayor igual string";};
   }

   private IIgualInt IIGUALINT;
   private class IIgualInt implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() == opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "igual int";};
   }

   private IIgualReal IIGUALREAL;
   private class IIgualReal implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() == opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "igual real";};
   }

   private IIgualBool IIGUALBOOL;
   private class IIgualBool implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool() == opnd2.valorBool()));
         pc++;
      }
      public String toString() {return "igual bool";};
   }

   private IIgualString IIGUALSTRING;
   private class IIgualString implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())==0));
         pc++;
      }
      public String toString() {return "igual string";};
   }

   private IIgualPuntero IIGUALPUNTERO;
   private class IIgualPuntero implements Instruccion{
      public void ejecuta(){
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() == opnd2.valorInt()));
         pc++;
      }
      public String toString(){return "igual puntero";};
   }

   private IIgualNull IIGUALNULL;
   private class IIgualNull implements Instruccion{
      public void ejecuta(){
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorNull() == opnd2.valorNull()));
         pc++;
      }
      public String toString(){return "igual null";};
   }

   private IIgualPN IIGUALPN;
   private class IIgualPN implements Instruccion{
      public void ejecuta(){
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool((opnd1.valorNull() == opnd2.valorInt()) ||(opnd1.valorInt() == opnd2.valorNull()));
         pc++;
      }
      public String toString(){return "igual puntero null";};
   }

   private IDesigualInt IDESIGUALINT;
   private class IDesigualInt implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() != opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "desigual int";};
   }

   private IDesigualReal IDESIGUALREAL;
   private class IDesigualReal implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal() != opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "desigual real";};
   }

   private IDesigualBool IDESIGUALBOOL;
   private class IDesigualBool implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool() != opnd2.valorBool()));
         pc++;
      }
      public String toString() {return "desigual bool";};
   }

   private IDesigualString IDESIGUALSTRING;
   private class IDesigualString implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())!=0));
         pc++;
      }
      public String toString() {return "desigual string";};
   }

   private IDesigualPuntero IDESIGUALPUNTERO;
   private class IDesigualPuntero implements Instruccion{
      public void ejecuta(){
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt() != opnd2.valorInt()));
         pc++;
      }
      public String toString(){return "desigual puntero";};
   }

   private IDesigualNull IDESIGUALNULL;
   private class IDesigualNull implements Instruccion{
      public void ejecuta(){
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorNull() != opnd2.valorNull()));
         pc++;
      }
      public String toString(){return "desigual null";};
   }

   private IDesigualPN IDESIGUALPN;
   private class IDesigualPN implements Instruccion{
      public void ejecuta(){
         Valor opnd2 = pilaEvaluacion.pop();
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool((opnd1.valorNull() != opnd2.valorInt()) ||(opnd1.valorInt() != opnd2.valorNull()));
         pc++;
      }
      public String toString(){return "desigual puntero null";};
   }

   private IMueve IMUEVE;
   private class IMueve implements Instruccion {
      private int tam;
      public IMueve(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i=0; i < tam; i++) 
                datos[dirDestino+i] = datos[dirOrigen+i]; 
            pc++;
      } 
      public String toString() {return "mueve("+tam+")";};
   }

   private class IIrA implements Instruccion {
      private int dir;
      public IIrA(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
            pc=dir;
      } 
      public String toString() {return "ir-a("+dir+")";};
   }

//PUEDE QUE FALTE IR_V PERO NO SE USA EN LA MEMORIA

   private class IIrF implements Instruccion {
      private int dir;
      public IIrF(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
         if(! pilaEvaluacion.pop().valorBool()) { 
            pc=dir;
         }   
         else {
            pc++; 
         }
      } 
      public String toString() {return "ir-f("+dir+")";};
   }

   //PUEDE QUE FALTE IR_IND PERO NO SE USA EN LA MEMORIA

   private class IAlloc implements Instruccion {
      private int tam;
      public IAlloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = gestorMemoriaDinamica.alloc(tam);
        pilaEvaluacion.push(new ValorInt(inicio));
        pc++;
      } 
      public String toString() {return "alloc("+tam+")";};
   }

   //ESTA LA HE CAMBIADO PORQUE NO HACIA LO QUE DECIA LA MEMORIA
   private class IDealloc implements Instruccion {
      private int tam;
      private int inicio;
      public IDealloc(int tam, int inicio) {
        this.tam = tam;  
        this.inicio = inicio;
      }
      public void ejecuta() {
        gestorMemoriaDinamica.free(inicio,tam);
        pc++;
      } 
      public String toString() {return "dealloc("+tam+")";};
   }
   //VOY POR AQUI
   private class IActiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       private int dirretorno;
       public IActiva(int nivel, int tamdatos, int dirretorno) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
           this.dirretorno = dirretorno;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
          datos[base] = new ValorInt(dirretorno);
          datos[base+1] = new ValorInt(gestorPilaActivaciones.display(nivel));
          pilaEvaluacion.push(new ValorInt(base+2));
          pc++;
       }
       public String toString() {
          return "activa("+nivel+","+tamdatos+","+dirretorno+")";                 
       }
   }
   
   private class IDesactiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       public IDesactiva(int nivel, int tamdatos) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
          gestorPilaActivaciones.fijaDisplay(nivel,datos[base+1].valorInt());
          pilaEvaluacion.push(datos[base]); 
          pc++;
       }
       public String toString() {
          return "desactiva("+nivel+","+tamdatos+")";                 
       }
   }

   private IDup IDUP;
   private class IDup implements Instruccion {
       public void ejecuta() {
           pilaEvaluacion.push(pilaEvaluacion.peek());
           pc++;
       }
       public String toString() {
          return "dup";                 
       }
   }
   private Instruccion ISTOP;
   private class IStop implements Instruccion {
       public void ejecuta() {
           pc = codigoP.size();
       }
       public String toString() {
          return "stop";                 
       }
   }
   
   private Instruccion IIRIND;
   private class IIrind implements Instruccion {
       public void ejecuta() {
          pc = pilaEvaluacion.pop().valorInt();  
       }
       public String toString() {
          return "ir-ind";                 
       }
   }

   private Iint2real IINT2REAL;
   private class Iint2real implements Instruccion {
      public void ejecuta() {
         Valor valor = pilaEvaluacion.pop();
         double d = valor.valorInt();
         pilaEvaluacion.push(new ValorReal(d));
         pc++;
      }
      public String toString() {
         return "int2real";
      }
   }

   private ILeer_entrada_int ILEER_ENTRADA_INT;
   private class ILeer_entrada_int implements Instruccion {
      public void ejecuta() {
         Scanner scanner = new Scanner(System.in);
         String dato = scanner.nextLine();
         pilaEvaluacion.push(new ValorInt(Integer.parseInt(dato)));
         pc++;
      }
      public String toString() {
         return "leer_entrada_int";
      }
   }

   private ILeer_entrada_real ILEER_ENTRADA_REAL;
   private class ILeer_entrada_real implements Instruccion {
      public void ejecuta() {
         Scanner scanner = new Scanner(System.in);
         String dato = scanner.nextLine();
         pilaEvaluacion.push(new ValorReal(Double.parseDouble(dato)));
         pc++;
      }
      public String toString() {
         return "leer_entrada_real";
      }
   }


   private ILeer_entrada_string ILEER_ENTRADA_STRING;
   private class ILeer_entrada_string implements Instruccion {
      public void ejecuta() {
         Scanner scanner = new Scanner(System.in);
         String dato = scanner.nextLine();
         pilaEvaluacion.push(new ValorString(dato));
         pc++;
      }
      public String toString() {
         return "leer_entrada_string";
      }
   }

   private IMostrar_int IMOSTRAR_INT;
   private class IMostrar_int implements Instruccion {
      public void ejecuta() {
         System.out.println(pilaEvaluacion.pop().valorInt());
         pc++;
      }
      public String toString() {
         return "mostrar_int";
      }
   }

   private IMostrar_real IMOSTRAR_REAL;
   private class IMostrar_real implements Instruccion {
      public void ejecuta() {
         System.out.println(pilaEvaluacion.pop().valorReal());
         pc++;
      }
      public String toString() {
         return "mostrar_real";
      }
   }

   private IMostrar_bool IMOSTRAR_BOOL;
   private class IMostrar_bool implements Instruccion {
      public void ejecuta() {
         System.out.println(pilaEvaluacion.pop().valorBool());
         pc++;
      }
      public String toString() {
         return "mostrar_bool";
      }
   }

   private IMostrar_string IMOSTRAR_STRING;
   private class IMostrar_string implements Instruccion {
      public void ejecuta() {
         System.out.println(pilaEvaluacion.pop().valorString());
         pc++;
      }
      public String toString() {
         return "mostrar_string";
      }
   }

   public Instruccion suma_int(){return ISUMA;}
   public Instruccion suma_real(){return RSUMA;}
   public Instruccion resta_int(){return IRESTA;}
   public Instruccion resta_real(){return RRESTA;}
   public Instruccion mul_int() {return IMUL;}
   public Instruccion mul_real() {return RMUL;}
   public Instruccion div_int() {return IDIV;}
   public Instruccion div_real() {return RDIV;}
   public Instruccion mod() {return MOD;}
   public Instruccion and() {return IAND;}
   public Instruccion or() {return IOR;}
   public Instruccion not() {return INOT;}
   public Instruccion neg_int() {return INEG;}
   public Instruccion neg_real() {return RNEG;}
   public Instruccion mayor_int() {return IMAYORINT;}
   public Instruccion mayor_real() {return IMAYORREAL;}
   public Instruccion mayor_bool() {return IMAYORBOOL;}
   public Instruccion mayor_string() {return IMAYORSTRING;}
   public Instruccion mayorIgual_int() {return IMAYORIGUALINT;}
   public Instruccion mayorIgual_real() {return IMAYORIGUALREAL;}
   public Instruccion mayorIgual_bool() {return IMAYORIGUALBOOL;}
   public Instruccion mayorIgual_string() {return IMAYORIGUALSTRING;}
   public Instruccion menor_int() {return IMENORINT;}
   public Instruccion menor_real() {return IMENORREAL;}
   public Instruccion menor_bool() {return IMENORBOOL;}
   public Instruccion menor_string() {return IMENORSTRING;}
   public Instruccion menorIgual_int() {return IMENORIGUALINT;}
   public Instruccion menorIgual_real() {return IMENORIGUALREAL;}
   public Instruccion menorIgual_bool() {return IMENORIGUALBOOL;}
   public Instruccion menorIgual_string() {return IMENORIGUALSTRING;}
   public Instruccion igual_int() {return IIGUALINT;}
   public Instruccion igual_real() {return IIGUALREAL;}
   public Instruccion igual_bool() {return IIGUALBOOL;}
   public Instruccion igual_string() {return IIGUALSTRING;}
   public Instruccion igual_null() {return IIGUALNULL;}
   public Instruccion igual_puntero() {return IIGUALPUNTERO;}
   public Instruccion igual_pn() {return IIGUALPN;}
   public Instruccion desigual_int() {return IDESIGUALINT;}
   public Instruccion desigual_real() {return IDESIGUALREAL;}
   public Instruccion desigual_bool() {return IDESIGUALBOOL;}
   public Instruccion desigual_string() {return IDESIGUALSTRING;}
   public Instruccion desigual_null() {return IDESIGUALNULL;}
   public Instruccion desigual_puntero() {return IDESIGUALPUNTERO;}
   public Instruccion desigual_pn() {return IDESIGUALPN;}
   public Instruccion apila_int(int val) {return new IApilaInt(val);}
   public Instruccion apila_bool(boolean val) {return new IApilaBool(val);}
   public Instruccion apila_real(double val) {return new IApilaReal((val));}
   public Instruccion apila_string(String val) {return new IApilaString((val));}
   public Instruccion apilad(int nivel) {return new IApilad(nivel);}
   public Instruccion apila_ind() {return IAPILAIND;}
   public Instruccion desapila_ind() {return IDESAPILAIND;}
   public Instruccion copia(int tam) {return new ICopia(tam);}
   public Instruccion ir_a(int dir) {return new IIrA(dir);}
   public Instruccion ir_f(int dir) {return new IIrF(dir);}
   public Instruccion ir_ind() {return IIRIND;}
   public Instruccion mueve() {return IMUEVE;}
   public Instruccion alloc(int tam) {return new IAlloc(tam);} 
   public Instruccion dealloc(int tam) {return new IDealloc(tam);} 
   public Instruccion activa(int nivel,int tam, int dirretorno) {return new IActiva(nivel,tam,dirretorno);}
   public Instruccion desactiva(int nivel, int tam) {return new IDesactiva(nivel,tam);}
   public Instruccion desapilad(int nivel) {return new IDesapilad(nivel);}
   public Instruccion dup() {return IDUP;}
   public Instruccion stop() {return ISTOP;}
   public Instruccion leer_entrada_int() {return ILEER_ENTRADA_INT;}
   public Instruccion leer_entrada_real() {return ILEER_ENTRADA_REAL;}
   public Instruccion leer_entrada_string() {return ILEER_ENTRADA_STRING;}
   public Instruccion mostrar_int() {return IMOSTRAR_INT;}
   public Instruccion mostrar_real() {return IMOSTRAR_REAL;}
   public Instruccion mostrar_bool() {return IMOSTRAR_BOOL;}
   public Instruccion mostrar_string() {return IMOSTRAR_STRING;}


    public void int2real(){

    }
   public void emit(Instruccion i) {
      codigoP.add(i); 
   }




    private int tamdatos;
   private int tamheap;
   private int ndisplays;
   private Scanner input;
   public MaquinaP(Reader datos2, int tamdatos, int tampila, int tamheap, int ndisplays) {
	  this.input = new Scanner(datos2);
      this.tamdatos = tamdatos;
      this.tamheap = tamheap;
      this.ndisplays = ndisplays;
      this.codigoP = new ArrayList<>();  
      pilaEvaluacion = new Stack<>();
      datos = new Valor[tamdatos+tampila+tamheap];
      this.pc = 0;
      ISUMA = new ISuma();
      IAND = new IAnd();
      IOR = new IOr();
      IMUL = new IMul();
      IAPILAIND = new IApilaind();
      IDESAPILAIND = new IDesapilaind();
      IIRIND = new IIrind();
      IDUP = new IDup();
      ISTOP = new IStop();
      gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos,(tamdatos+tampila)-1,ndisplays); 
      gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos+tampila,(tamdatos+tampila+tamheap)-1);
      ILEER_ENTRADA_INT = new ILeer_entrada_int();
      ILEER_ENTRADA_REAL = new ILeer_entrada_real();
      ILEER_ENTRADA_STRING = new ILeer_entrada_string();
      IMOSTRAR_INT = new IMostrar_int();
      IMOSTRAR_REAL = new IMostrar_real();
      IMOSTRAR_BOOL = new IMostrar_bool();
      IMOSTRAR_STRING = new IMostrar_string();
   }
   public void ejecuta() {
      while(pc != codigoP.size()) {
          codigoP.get(pc).ejecuta();
      } 
   }
   public void muestraCodigo() {
     System.out.println("CodigoP");
     for(int i=0; i < codigoP.size(); i++) {
        System.out.println(" "+i+":"+codigoP.get(i));
     }
   }
   public void muestraEstado() {
     System.out.println("Tam datos:"+tamdatos);  
     System.out.println("Tam heap:"+tamheap); 
     System.out.println("PP:"+gestorPilaActivaciones.pp());      
     System.out.print("Displays:");
     for (int i=1; i <= ndisplays; i++)
         System.out.print(i+":"+gestorPilaActivaciones.display(i)+" ");
     System.out.println();
     System.out.println("Pila de evaluacion");
     for(int i=0; i < pilaEvaluacion.size(); i++) {
        System.out.println(" "+i+":"+pilaEvaluacion.get(i));
     }
     System.out.println("Datos");
     for(int i=0; i < datos.length; i++) {
        System.out.println(" "+i+":"+datos[i]);
     }
     System.out.println("PC:"+pc);
   }
   
   public static void main(String[] args) {
       MaquinaP m = new MaquinaP(5,10,10,2);
        
          /*
            int x;
            proc store(int v) {
             x = v
            }
           &&
            call store(5)
          */
            
       
       m.emit(m.activa(1,1,8));
       m.emit(m.dup());
       m.emit(m.apila_int(0));
       m.emit(m.suma_int());
       m.emit(m.apila_int(5));
       m.emit(m.desapila_ind());
       m.emit(m.desapilad(1));
       m.emit(m.ir_a(9));
       m.emit(m.stop());
       m.emit(m.apila_int(0));
       m.emit(m.apilad(1));
       m.emit(m.apila_int(0));
       m.emit(m.suma_int());
       m.emit(m.copia(1));
       m.emit(m.desactiva(1,1));
       m.emit(m.ir_ind());       
       m.muestraCodigo();
       m.ejecuta();
       m.muestraEstado();
   }
}

