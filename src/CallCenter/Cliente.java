package CallCenter;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	private String codice;
	private String cognome;
	private String nome;
	private String numero;
	private Telefonata ultimaTelefonata = null;

	public Cliente(String codice, String cognome, String nome, String numero) {
		this.codice = codice;
		this.cognome = cognome;
		this.nome = nome;
		this.numero = numero;
	}
	
	public Cliente(Cliente c) {
		this.codice = c.codice;
		this.cognome = c.cognome;
		this.nome = c.nome;
		this.numero = c.numero;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Telefonata getUltimaTelefonata() {
		return ultimaTelefonata;
	}

	public void setUltimaTelefonata(Telefonata ultimaTelefonata) {
		this.ultimaTelefonata = ultimaTelefonata;
	}

	@Override
	public String toString() {
		return "\n-- Cliente --\nCodice: " + codice + "\nCognome: " + 
				cognome + "\nNome: " + nome;
	}
}
