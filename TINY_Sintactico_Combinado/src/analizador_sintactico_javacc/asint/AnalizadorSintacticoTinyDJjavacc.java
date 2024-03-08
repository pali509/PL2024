package asint;
import java.io.Reader;

import static asint.AnalizadorSintacticoTinyConstants.tokenImage;

public class AnalizadorSintacticoTinyDJjavacc extends AnalizadorSintacticoTinyjavacc{
	private void imprime(Token t) {
		switch (tokenImage[t.kind]) {
    	case "<or>":
     		System.out.println(tokenImage[t.kind]);
     		break;
     	case "<int_>":
     		System.out.println("<int>");
            break;
     	case "<real>":	
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<bool>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<string>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<null_>":
     		System.out.println("<null>");
            break;
     	case "<proc>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<if_>":
     		System.out.println("<if>");
            break;
     	case "<else_>":
     		System.out.println("<else>");
            break;
     	case "<while_>":
     		System.out.println("<while>");
            break;
     	case "<struct>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<new_>":
     		System.out.println("<new>");
            break;
     	case "<delete>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<read>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<write>":	
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<nl>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<type>":
     		System.out.println(tokenImage[t.kind]);
            break;
     	case "<call>":
     		System.out.println(tokenImage[t.kind]);
     		break;     		
     	case "<true_>":
     		System.out.println("<true>");
     		break;
     	case "<false_>":
     		System.out.println("<false>");
     		break;
     	case "<and>":
     		System.out.println(tokenImage[t.kind]);
     		break;
     	case "<not>":
     		System.out.println(tokenImage[t.kind]);
     		break;
     	case "<EOF>":
     		System.out.println(tokenImage[t.kind]);
     		break;
	    default:
	         System.out.println(t.image);
	         break;
	 }
	}
	public AnalizadorSintacticoTinyDJjavacc(Reader r) {
		super(r);
		disable_tracing();
	}
	
	final protected void trace_token(Token t, String where) {	
		imprime(t);
		
	}	
}

