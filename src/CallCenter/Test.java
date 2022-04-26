package CallCenter;

public class Test  {
	
	public static void menu() {
		System.out.println("\n *** Call Center *** ");
		System.out.println("1) Inserisci operatore");
		System.out.println("2) Elimina operatore");
		System.out.println("3) Inserisci cliente");
		System.out.println("4) Ricevi chiamata");
		System.out.println("5) Mostra chiamate ricevute per codice operatore");
		System.out.println("0) Fine programma");
		System.out.print("Opzione scelta:");
	}
	
	public static void main(String[] args) {
		CallCenter c = new CallCenter();
		c.carica();
		Input input = new Input();
		int scelta;
		do {
			menu();
			scelta = input.inputInt("");
			switch (scelta) {
				case 1:
					String codice = input.inputString("\nInserire il codice dell'operatore:");
					String nome = input.inputString("Inserire il nome:");
					String cognome = input.inputString("Inserire il cognome:");
					if (c.inserisciOperatore(codice, cognome, nome)) {
						System.out.println("Nuovo operatore aggiunto correttamente");
					} else {
						System.out.println("Esiste già un operatore con questo codice");
					}
					break;
				case 2:
					String codice1 = input.inputString("\nInserire il codice dell'operatore da eliminare:");
					if (c.eliminaOperatore(codice1)) {
						System.out.println("Operatore eliminato correttamente");
					} else {
						System.out.println("Nessun operatore con questo codice");
					}
					break;
				case 3:
					String codice2 = input.inputString("\nInserire il codice del cliente:");
					String nome1 = input.inputString("Inserire il nome:");
					String cognome1 = input.inputString("Inserire il cognome:");
					String numero = input.inputPhoneNumber();
					if (c.inserisciCliente(codice2, cognome1, nome1, numero)) {
						System.out.println("Nuovo cliente aggiunto correttamente");
					} else {
						System.out.println("Esiste già un cliente con questo numero di telefono");
					}
					break;
				case 4:
					String numero1 = input.inputPhoneNumber();
					if (!c.chiamata(numero1)) {
						System.out.println("Nessun cliente con questo numero di telefono");
					}
					break;
				case 5:
					String codice3 = input.inputString("\nInserire il codice dell'operatore:");
					c.stampaChiamateOperatore(codice3);
					break;
			}
		} while (scelta != 0);
		c.salva();
	}
}
