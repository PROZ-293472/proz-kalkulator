package application.model;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;


/**
 * Klasa z pakietu model odpowiedzialna za tworzenie api aplikacji
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
	 * @return
	 */
	public String compute(String input) {

		List<SnippetEvent> events = jshell.eval(input);
		for (SnippetEvent e : events)
			if (e.causeSnippet() == null)
				return e.value();

		return null;

	}
	
	/**
	 * Metoda sprawdzajaca, czy wykonanie metody compute sie powiodlo
	 * 
	 * @param input - pole tekstowe kalkulatora
	 * @return
	 */
	public boolean forbidden(String input) {
		if (input == null) return true;
		return false;
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
