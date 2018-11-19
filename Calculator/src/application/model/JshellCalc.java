package application.model;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;


/**
 * Klasa z pakietu model odpowiedzialna za tworzenie api aplikacji
 * Zawiera metode compute odpowiedzialna za przetworzenie stringa wejsciowego zawierajacego dzialania arytmetyczne na wynik dzialania
 * 
 * 
 * @author Michal Szpunar 
 *
 */
public class JshellCalc {
	
	
	private final JShell jshell = JShell.create();

	/**
	 * Konstruktor klasy, w ktorym definiowane sa dodatkowe funkcjonalnosci pow i sqrt
	 */
	public JshellCalc() {
		try {
			jshell.eval("private double pow(double x, double y) { return Math.pow(x,y); }");
			jshell.eval("private double sqrt(double x) { return Math.sqrt(x); }");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Glowna metoda obliczajaca
	 * 
	 * @param input - pole tekstowe kalkulatora
	 * @return string zawierajacy wynik
	 * @throws ArithmeticException w przypadku bledu obliczenia
	 */
	public String compute(String input) throws ArithmeticException {

		List<SnippetEvent> events = jshell.eval(input);
		for (SnippetEvent e : events)
			if (e.causeSnippet() == null)
				if(e.value() == null || e.value().equals("NaN") || e.value().equals("Infinity"))
					throw new ArithmeticException();
				else
					return e.value();

		return null;

	}
	
	
	
	/**
	 * Metoda odpowiedzialna za wyswietlanie alertow
	 */
	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Blad");
		alert.setHeaderText(null);
		alert.setContentText("Niedozwolona skladnia lub bledna operacja arytmetyczna");
		alert.showAndWait();
	}

}
