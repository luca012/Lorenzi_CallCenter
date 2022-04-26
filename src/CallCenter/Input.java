package CallCenter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Input {
	private Scanner input;

	public Input() {
		this.input = new Scanner(System.in);
	}

	public int inputInt(String msg) {
		int val;
		String buffer;
		do {
			try {
				System.out.print(msg + " ");
				buffer = this.input.nextLine();
				val = Integer.parseInt(buffer);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("Errore. Inserire un valore numerico");
			}
		} while (true);
		return val;
	}

	public int inputInt(String msg, int min, int max) {
		int val;
		String buffer;
		do {
			try {
				System.out.print(msg + " ");
				buffer = this.input.nextLine();
				val = Integer.parseInt(buffer);
				if ((val < min) || (val > max)) {
					System.out.println("Valore errato (" + min + "<= Val <=" + max + ")");
				} else {
					break;
				}
			} catch (NumberFormatException ex) {
				System.out.println("Errore. Inserire un valore numerico");
			}
		} while (true);
		return val;
	}

	public int inputInt(String msg, int minVal) {
		int val;
		String buffer;
		do {
			try {
				System.out.print(msg + " ");
				buffer = this.input.nextLine();
				val = Integer.parseInt(buffer);
				if (val < minVal) {
					System.out.println("Valore errato (val minimo accettato: " + minVal + "), riprovare");
				} else {
					break;
				}
			} catch (NumberFormatException ex) {
				System.out.println("Errore. Inserire un valore numerico");
			}
		} while (true);
		return val;
	}

	public double inputDouble(String msg, double minVal) {
		double val;
		String buffer;
		do {
			try {
				System.out.print(msg + " ");
				buffer = this.input.nextLine();
				val = Double.parseDouble(buffer);
				if (val < minVal) {
					System.out.println("Valore errato (val minimo accettato: " + minVal + "), riprovare");
				} else {
					break;
				}
			} catch (NumberFormatException ex) {
				System.out.println("Errore, riprovare");
			}
		} while (true);
		return val;
	}

	public double inputDouble(String msg) {
		double val;
		String buffer;
		do {
			try {
				System.out.print(msg + " ");
				buffer = this.input.nextLine();
				val = Double.parseDouble(buffer);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("Errore, riprovare");
			}
		} while (true);
		return val;
	}

	public float inputFloat(String msg) {
		float val;
		String buffer;
		do {
			try {
				System.out.print(msg + " ");
				buffer = this.input.nextLine();
				val = Float.parseFloat(buffer);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("Errore, riprovare");
			}
		} while (true);
		return val;
	}

	public String inputString(String msg) {
		String data;
		do {
			System.out.print(msg + " ");
			data = this.input.nextLine();
			if (data.isBlank() == true) {
				System.out.println("Errore, campo vuoto");
			} else {
				break;
			}
		} while (true);
		return data;
	}

	public String inputString(String msg, boolean flag) {
		String data;
		do {
			System.out.print(msg + " ");
			data = this.input.nextLine();
			if (!flag && data.isBlank() == true) {
				System.out.println("Errore, campo vuoto");
			} else {
				break;
			}
		} while (true);
		return data;
	}

	public String inputString(String msg, int LenChacacter) {
		String data;
		do {
			System.out.print(msg + " ");
			data = this.input.nextLine();
			if (data.isBlank() == true) {
				System.out.println("Errore, campo vuoto");
			} else if (data.length() != LenChacacter) {
				System.out.println("Lunghezza errata, riprovare! (inserire " + LenChacacter + " caratteri)");
			} else {
				break;
			}
		} while (true);
		return data;
	}

	public String inputStringMaxLen(String msg, int maxCharacter) {
		String data;
		do {
			System.out.print(msg + " ");
			data = this.input.nextLine();
			if (data.isBlank() == true) {
				System.out.println("Errore, campo vuoto");
			} else if (data.length() > maxCharacter) {
				System.out.println("Lunghezza errata, riprovare! (inserire fino a" + maxCharacter + "caratteri)");
			} else {
				break;
			}
		} while (true);
		return data;
	}

	public LocalDate inputDate(String msg) {
		System.out.println(msg);
		int year = this.inputInt("Anno: ", LocalDate.now().getYear());
		int month = this.inputInt("\nMese: ", 1, 12);
		int day = this.inputInt("\nGiorno: ", 1,
				month == 2 ? ((year % 4) == 0) && ((year % 100) == 0) && ((year % 400) != 0) ? 28 : 29
						: (month % 2) == 0 ? 30 : 31);
		return LocalDate.of(year, month, day);
	}

	public LocalTime inputTime(String msg) {
		System.out.println(msg);
		int h = this.inputInt("Ora : ", 0, 23);
		int m = this.inputInt("Minuto : ", 0, 59);
		int sec = this.inputInt("Secondo : ", 0, 59);
		return LocalTime.of(h, m, sec);
	}

	public String inputPhoneNumber() {
		String cell;
		do {
			cell = this.inputString("Inserire il numero di telefono: ");
			if (!cell.matches("\\d{10}")) {
				System.out.println("Numero inserito non valido, riprovare!");
			}
		} while (!cell.matches("\\d{10}"));
		return cell;
	}

}

