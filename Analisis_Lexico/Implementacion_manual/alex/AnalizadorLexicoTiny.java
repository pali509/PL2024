//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package alex;

public enum ClaseLexica {
	VAR,
	LENT,
	LREAL,
	TRUE("<true>"),
	FALSE("<false>"),
	AND("<and>"),
	NOT("<not>"),
	OR("<or>"),
	INT("<int>"),
	REAL("<real>"),
	BOOL("<bool>"),
	PAP("("),
	PCIERRE(")"),
	ARROBA("@"),
	AMPERSAND("&&"),
	PUNTOYCOMA(";"),
	LLAVEA("{"),
	LLAVEC("}"),
	MAS("+"),
	MENOS("-"),
	POR("*"),
	DIV("/"),
	MAYOR(">"),
	MENOR("<"),
	MAYORIG(">="),
	MENORIG("<="),
	IGUAL("=="),
	DESIGUAL("!="),
	ASIG("="),
	EOF("EOF");

	private String image;

	public String getImage() {
		return this.image;
	}

	private ClaseLexica() {
		this.image = this.toString();
	}

	private ClaseLexica(String image) {
		this.image = image;
	}
}
