package CallCenter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Telefonata implements Serializable,Comparable<Telefonata> {
	
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

	private String toStringDateTime(LocalDateTime ldt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		String formattedDateTime = ldt.format(formatter);
		return formattedDateTime;
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

	@Override
	public String toString() {
		return "-- Telefonata --\nData e ora di inizio: " + toStringDateTime(dataOraInizio) + 
				"\nData e ora di fine: " + toStringDateTime(dataOraFine) + "\nRicevuta dal cliente: " + c.getCodice() + "\n";
	}

	@Override
	public int compareTo(Telefonata t) {
		return getDataOraInizio().compareTo(t.getDataOraInizio());
	}
}
