package alex;
import errors.GestionErroresTiny;
%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%cup
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
     this.errores = errores;
    }

%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([a-z]|[A-Z]|_)
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}*|0)
parteDecimal = \. ({digito}*{digitoPositivo} | 0)
parteExponencial = [\e \E ] [\+ \- ]? ({digitoPositivo} {digito}* | 0)

int = (i|I)(n|N)(t|T)
real = (r|R)(e|E)(a|A)(l|L)
bool = (b|B)(o|O)(o|O)(l|L)
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
null = (n|N)(u|U)(l|L)(l|L)
proc = (p|P)(r|R)(o|O)(c|C)
if = (i|I)(f|F)
else = (e|E)(l|L)(s|S)(e|E)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
new = (n|N)(e|E)(w|W)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
nl = (n|N)(l|L)
type = (t|T)(y|Y)(p|P)(e|E)
call = (c|C)(a|A)(l|L)(l|L)
true = (t|T)(r|R)(u|U)(e|E)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
and = (a|A)(n|N)(d|D)
not = (n|N)(o|O)(t|T)
or = (o|O)(r|R)

identificador = {letra}({letra}|{digito})*
literalEntero = [\+\-]?{parteEntera}
literalReal = {literalEntero} ({parteDecimal}|{parteExponencial}|({parteDecimal}{parteExponencial}))
literalCadena =  \"([^\"])*\" 


opSuma = \+
opResta = \-
opMul = \*
opDiv = \/
opMod = \%
opMayor = \>
opMenor = \<
opMayorIgual = \>=
opMenorIgual = \<=
opIgual = \==
opDesigual = \!=
opAsig = \=
parA = \(
parC = \)
corcheteA = \[
corcheteC = \]
llaveA = \{
llaveC = \}
puntoYComa = \;
coma = \,
punto = \.
arroba = \@
ampersand = \&
ampersand2 = \&&
circunflejo = \^


separador = [ \t\r\b\n]
comentario = ##[^\n]* 
%%


{literalEntero}            {return ops.unidadEnt();}
{literalReal}              {return ops.unidadLitReal();}
{literalCadena}              {return ops.unidadCadena();}
{opSuma}            {return ops.unidadSuma();}
{opResta}           {return ops.unidadResta();}
{opMul}  {return ops.unidadMul();}
{opDiv}        {return ops.unidadDiv();}
{opMod}              {return ops.unidadMod();}
{opMayor}              {return ops.unidadMayor();}
{opMenor}              {return ops.unidadMenor();}
{opMayorIgual}              {return ops.unidadMayorIgual();}
{opMenorIgual}              {return ops.unidadMenorIgual();}
{opIgual}              {return ops.unidadIgual();}
{opDesigual}              {return ops.unidadDesigual();}
{opAsig}              {return ops.unidadAsig();}
{parA}              {return ops.unidadParA();}
{parC}              {return ops.unidadParC();}
{corcheteA}              {return ops.unidadCorcheteA();}
{corcheteC}              {return ops.unidadCorcheteC();}
{llaveA}              {return ops.unidadLlaveA();}
{llaveC}              {return ops.unidadLlaveC();}
{puntoYComa}              {return ops.unidadPuntoYComa();}
{coma}              {return ops.unidadComa();}
{circunflejo}              {return ops.unidadCircunflejo();}
{punto}              {return ops.unidadPunto();}
{arroba}              {return ops.unidadArroba();}
{ampersand}              {return ops.unidadAmpersand();}
{ampersand2}              {return ops.unidadAmpersand2();}
{separador}               {}
{comentario}              {}
{int}              {return ops.unidadInt();}
{real}              {return ops.unidadReal();}
{bool}              {return ops.unidadBool();}
{string}              {return ops.unidadString();}
{null}              {return ops.unidadNull();}
{proc}              {return ops.unidadProc();}
{if}              {return ops.unidadIf();}
{else}              {return ops.unidadElse();}
{while}              {return ops.unidadWhile();}
{struct}              {return ops.unidadStruct();}
{new}              {return ops.unidadNew();}
{delete}              {return ops.unidadDelete();}
{read}              {return ops.unidadRead();}
{write}              {return ops.unidadWrite();}
{nl}              {return ops.unidadNl();}
{type}              {return ops.unidadType();}
{call}              {return ops.unidadCall();}
{true}              {return ops.unidadTrue();}
{false}              {return ops.unidadFalse();}
{and}              {return ops.unidadAnd();}
{not}              {return ops.unidadNot();}
{or}              {return ops.unidadOr();}
{identificador}           {return ops.unidadId();}
[^]                       {ops.error();}  
