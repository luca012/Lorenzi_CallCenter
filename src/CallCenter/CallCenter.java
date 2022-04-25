package CallCenter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;

public class CallCenter {
	
	private Map<String, Cliente> clienti;
	private Map<String, Operatore> operatori;
	private Map<String, Stack<Telefonata>> telefonate;
	
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
	
	// nella mappa telefonate la chiave codice è sbagliata
	
	public boolean chiamata(String numero) {
		
		if(cercaCliente(numero) != null) {
			LocalDateTime dataOraInizio = LocalDateTime.now();
			List<Operatore> list = new ArrayList<Operatore>(operatori.values());
			Operatore o = list.get(random(0, list.size()));
			System.out.println("\n** CHIAMATA DA: " + numero + " ***");
			System.out.println(clienti.get(numero).toString());
			if (clienti.containsKey(numero) && !telefonate.containsKey(o.getCodice())) {
				Stack<Telefonata> lista = new Stack<>();
				telefonate.put(o.getCodice(), lista);
			} else if (clienti.containsKey(numero) && telefonate.containsKey(o.getCodice())) {
				System.out.println("-- Ultima telefonata --" + telefonate.get(o.getCodice()).peek().getDataOraInizio());
				System.out.println("Data e ora di inizio" + telefonate.get(o.getCodice()).peek().getDataOraInizio());
				System.out.println("Data e ora di fine" + telefonate.get(o.getCodice()).peek().getDataOraFine());
				System.out.println(telefonate.get(o.getCodice()).peek().getO().toString());
			}
			telefonate.get(o.getCodice()).push(new Telefonata(dataOraInizio, LocalDateTime.now(), clienti.get(numero), o));
			return true;
		}
		else {
			return false;
		}
	}
	
	public void stampaChiamateOperatore(String codice) {
		if(cercaOperatore(codice) != null) {
			System.out.println(telefonate.get(codice).toString());
		} else {
			System.out.println("Nessun operatore con questo codice");
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
			this.operatori = (Map<String, Operatore>) oisOperatori.readObject();
			this.clienti = (Map<String, Cliente>) oisClienti.readObject();
			this.telefonate = ( Map<String, Stack<Telefonata>>) oisTelefonate.readObject();
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
