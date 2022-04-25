package CallCenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Telefonata {
	
	private LocalDateTime dataOraInizio;
	private LocalDateTime dataOraFine;
	private Cliente c;
	private Operatore o;
	
	public Telefonata(LocalDateTime dataOraInizio, LocalDateTime dataOraFine, Cliente c, Operatore o) {
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.c = c;
		this.o = o;
	}
	
	public Telefonata(Telefonata t) {
		this.dataOraInizio = t.dataOraInizio;
		this.dataOraFine = t.dataOraFine;
		this.c = t.c;
		this.o = t.o;
	}

	public String getDataOraInizio() {
		return toStringDateTime(dataOraInizio);
	}

	public String getDataOraFine() {
		return toStringDateTime(dataOraFine);
	}


	public Cliente getC() {
		return c;
	}

	public Operatore getO() {
		return o;
	}

	public String toStringDateTime(LocalDateTime ldt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		String formattedDateTime = ldt.format(formatter);
		return formattedDateTime;
	}
	
	@Override
	public String toString() {
		return "-- Telefonata --\nData e ora di inizio: " + toStringDateTime(this.dataOraInizio) + 
				"\nData e ora di fine: " + toStringDateTime(dataOraFine) + "\nRicevuta dal cliente: " + c.getCodice() + "\n";
	}
}
