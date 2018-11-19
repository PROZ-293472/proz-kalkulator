package application.controller;

import application.model.JshellCalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Klasa kotrolera implementujaca metody obsluguj¹ce komponenty GUI
 * 
 * @author Michae Szpunar
 * @version 1.0
 */
public class CalcController {

	// P O L A

	@FXML
	private Button btnSubmit;

	@FXML
	private Button btnClear;

	@FXML
	private TextField txtField;

	private String txtFieldContent = "0";

	private JshellCalc api = new JshellCalc();

	/**
	 * Enumerator sluzacy do ustalenia, czy bezposrednio przed akcja by³ wcisniety guzik Submit
	 * Domyslnie ustawiony: bSub
	 * 
	 */
	private enum actionMemory {
		bSub, aSub
	};

	/**
	 * Enumerator sluzacy do ustalenia, czy bezposrednio przed akcja by³ wcisniety guzik operacji
	 * Domyslnie ustawiony: notLast
	 * 
	 */
	actionMemory mem = actionMemory.bSub;

	private enum operationMemory {
		last, notLast
	};

	operationMemory opMem = operationMemory.notLast;

	// M E T O D Y
	
	/**
	 * Metoda sprawdzajaca, czy dany przycisk nalezy do grupy przyciskow operacji
	 * 
	 * @param wcisniety przycisk
	 * @return bool
	 */
	private boolean isOperation(Button b) {
		String label = b.getText();
		if (label.equals("+") || label.equals("-") || label.equals("/") || label.equals("*") || label.equals("sqrt") || label.equals("pow"))
			return true;
		return false;
	}
	
	/**
	 * Metoda click stosowana przy wszystkich przyciskach oprocz submit, clear i zaawansowanych funckji matematycznych
	 * Funkcja wykrywa, czy ostatni przycisk byl przyciskiem Submit. Jesli byl - w zaleznosci od guzika albo nastepuje wyczyszcznie pola tekstowego, albo dopisanie znaku
	 * Funkcja wykrywa, czy ostatni przycisk byl przyciskiem operacji. Jesli byl - nastepuje podmiana ostatniego znaku na dopisany
	 * 
	 * @param event - klikniecie przycisku
	 */
	@FXML
	void click(ActionEvent event) {
		Button clickedBtn = (Button) event.getSource();
		String btnLabel = clickedBtn.getText();

		if (txtFieldContent.equals("0") && !(isOperation(clickedBtn)))
			txtField.clear();

		if (mem.equals(actionMemory.aSub)) {
			if (isOperation(clickedBtn)) {
				mem = actionMemory.bSub;
			} else {
				txtField.clear();
				mem = actionMemory.bSub;			}
		}

		if (isOperation(clickedBtn) && opMem.equals(operationMemory.last)) {
			txtFieldContent = txtFieldContent.substring(0, txtFieldContent.length() - 1) + btnLabel;
			txtField.setText(txtFieldContent);
		} else {
			String temp = txtField.getText();
			txtFieldContent = temp + btnLabel;
			txtField.setText(txtFieldContent);
		}

		if (isOperation(clickedBtn))
			opMem = operationMemory.last;
		else
			opMem = operationMemory.notLast;
	}
	
	/**
	 * Metoda obslugujaca wcisniecie guzika sqrt
	 * Zamienia string na argument funkcji zdefiniowanej w konstruktorze JshellCalc 
	 * 
	 * @param event
	 */
	@FXML
	public void sqrtClick(ActionEvent event) {
		txtFieldContent = "sqrt(" + txtFieldContent + ")";
		txtField.setText(txtFieldContent);
		mem = actionMemory.bSub;
	}

	/**
	 * Metoda obslugujaca wcisniecie guzika pow
	 * Zamienia string na argument funkcji zdefiniowanej w konstruktorze JshellCalc 
	 * 
	 * @param event
	 */
	@FXML
	public void powClick(ActionEvent event) {
		txtFieldContent = "pow(" + txtFieldContent + ",";
		txtField.setText(txtFieldContent);
		mem = actionMemory.bSub;
	}
	
	/**
	 * Metoda obslugujaca wcisniecie guzika +/-
	 * 
	 * @param event
	 */
	@FXML
	public void signClick(ActionEvent event) {
		if(txtFieldContent.charAt(0) == '-')
			txtFieldContent = txtFieldContent.substring(1, txtFieldContent.length());
		else if (txtFieldContent.equals("0"));
		else
			txtFieldContent ="-" + txtFieldContent;
		txtField.setText(txtFieldContent);		
	}

	/**
	 * Metoda zamieniajaca string txtFieldContent na wynik dzialania
	 * Korzysta z metody api.compute
	 * Jesli nie udalo siê wykonac dzialania wyswietla alert
	 * 
	 *@param event
	 */
	@FXML
	public void submit(ActionEvent event){
		try {
			txtFieldContent = api.compute(txtField.getText());	
			txtField.setText(txtFieldContent);
		}
		catch (ArithmeticException exc) {
			txtFieldContent = "0";
			api.showAlert();
		}
		mem = actionMemory.aSub;

	}

	/**
	 * Metoda przywracajaca poczatkowy stan kalkulatora
	 * 
	 * @param event
	 */
	@FXML
	public void clear(ActionEvent event) {

		txtFieldContent = "0";
		txtField.setText(txtFieldContent);

	}
}
