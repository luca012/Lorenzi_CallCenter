package CallCenter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.ArrayList;

public class CallCenter {
	
	private Map<String, Cliente> clienti;
	private Map<String, Operatore> operatori;
	private Map<String, ArrayList<Telefonata>> telefonate;
	
	public CallCenter() {
		this.clienti = new HashMap<>();
		this.operatori = new HashMap<>();
		this.telefonate = new HashMap<>();
	}
	
	private Cliente cercaCliente(String numero) {
		return clienti.get(numero);
	}
	
	private Operatore cercaOperatore(String codice) {
		return operatori.get(codice);
	}
	
	public boolean inserisciCliente(String codice, String cognome, String nome, String numero) {
		if (cercaCliente(numero) == null) {
			clienti.put(numero, new Cliente(codice, cognome, nome, numero));
			return true;
		}
		return false;
	}
	
	public boolean inserisciOperatore(String codice, String cognome, String nome) {
		if (cercaOperatore(codice) == null) {
			operatori.put(codice, new Operatore(codice, cognome, nome));
			return true;
		}
		return false;
	}
	
	public boolean eliminaOperatore(String codice) {
		if (cercaOperatore(codice) != null) {
			operatori.remove(codice);
			return true;
		}
		return false;
	}
	
	public int random(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public boolean chiamata(String numero) {
		
		if(cercaCliente(numero) != null) {
			LocalDateTime dataOraInizio = LocalDateTime.now();
			List<Operatore> list = new ArrayList<Operatore>(operatori.values());
			Operatore o = list.get(random(0, list.size()));
			System.out.println("\n** CHIAMATA DA: " + numero + " **, servito dall'operatore " + o.getCodice());
			System.out.println(clienti.get(numero).toString());
			if (clienti.get(numero).getUltimaTelefonata() == null && telefonate.get(o.getCodice()) == null) {
				ArrayList<Telefonata> lista = new ArrayList<>();
				telefonate.put(o.getCodice(), lista);
			} else if (clienti.get(numero).getUltimaTelefonata() != null && telefonate.get(o.getCodice()) == null) {
				ArrayList<Telefonata> lista = new ArrayList<>();
				telefonate.put(o.getCodice(), lista);
				System.out.println("\n-- Ultima Telefonata --\nData e ora di inizio: " + clienti.get(numero).getUltimaTelefonata().getDataOraInizio());
				System.out.println("Data e ora di fine: " + clienti.get(numero).getUltimaTelefonata().getDataOraFine());
				System.out.println(clienti.get(numero).getUltimaTelefonata().getO().toString());
			} else {
				System.out.println("\n-- Ultima Telefonata --\nData e ora di inizio: " + clienti.get(numero).getUltimaTelefonata().getDataOraInizio());
				System.out.println("Data e ora di fine: " + clienti.get(numero).getUltimaTelefonata().getDataOraFine());
				System.out.println(clienti.get(numero).getUltimaTelefonata().getO().toString());
			}
			ArrayList<Telefonata> app = telefonate.get(o.getCodice());
			telefonate.get(o.getCodice()).add(new Telefonata(dataOraInizio, LocalDateTime.now(), clienti.get(numero), o));
			clienti.get(numero).setUltimaTelefonata(app.get(app.size()-1));
			return true;
		}
		else {
			return false;
		}
	}
	
	public void stampaChiamateOperatore(String codice) {
		if(telefonate.get(codice) != null) {
			System.out.println(telefonate.get(codice).toString());
		} else {
			System.out.println("Nessun operatore con questo codice o l'operatore non ha ricevuto telefonate");
		}
	}
	
	/**
 	 * Salva su file binari i dati inerenti a Operatori, Clienti e Telefonate
 	 */
	
	public void salva() {
		ObjectOutputStream oosOperatori = null;
		ObjectOutputStream oosClienti = null;
		ObjectOutputStream oosTelefonate = null;
		try {
			oosOperatori = new ObjectOutputStream(new FileOutputStream("operatori.bin"));
			oosClienti = new ObjectOutputStream(new FileOutputStream("clienti.bin"));
			oosTelefonate = new ObjectOutputStream(new FileOutputStream("telefonate.bin"));
			oosOperatori.writeObject(operatori);
			oosClienti.writeObject(clienti);
			oosTelefonate.writeObject(telefonate);
			oosOperatori.close();
			oosClienti.close();
			oosTelefonate.close();
			System.out.println("\nDati salvati nei file operatori.bin, clienti.bin e telefonate.bin");
		} catch (IOException e) {
			System.out.println("\nErrore nella scrittura dei file di salvataggio");
		}
	}
	
 	/**
 	 * Carica da file binari i dati inerenti a Operatori, Clienti e Telefonate
 	 */
 	
	public void carica() {
		ObjectInputStream oisOperatori = null;
		ObjectInputStream oisClienti = null;
		ObjectInputStream oisTelefonate = null;
		try {
			oisOperatori = new ObjectInputStream(new FileInputStream("operatori.bin"));
			oisClienti = new ObjectInputStream(new FileInputStream("clienti.bin"));
			oisTelefonate = new ObjectInputStream(new FileInputStream("telefonate.bin"));
			operatori = (Map<String, Operatore>) oisOperatori.readObject();
			clienti = (Map<String, Cliente>) oisClienti.readObject();
			telefonate = (Map<String, ArrayList<Telefonata>>) oisTelefonate.readObject();
			oisOperatori.close();
			oisClienti.close();
			oisTelefonate.close();
			System.out.println("\nDati importati dai file operatori.bin, clienti.bin e telefonate.bin");
		} catch (IOException e) {
			System.out.println("\nFile binari da caricare non presenti");
		} catch (ClassNotFoundException e) {
			System.out.println("\nErrore nella lettura dei file binari salvati");
		}
	}
}
