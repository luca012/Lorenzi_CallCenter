package CallCenter;

import java.io.Serializable;

public class Operatore implements Serializable {
	
	private String codice;
	private String cognome;
	private String nome;
	
	public Operatore(String codice, String cognome, String nome) {
		this.codice = codice;
		this.cognome = cognome;
		this.nome = nome;
	}
	
	public Operatore(Operatore o) {
		this.codice = o.codice;
		this.cognome = o.cognome;
		this.nome = o.nome;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "\n-- Ultimo Operatore --\nCodice: " + codice + "\nCognome: " + 
				cognome + "\nNome: " + nome;
	}
}
