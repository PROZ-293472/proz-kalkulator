package application.controller;

import application.model.JshellCalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

	private enum actionMemory {
		bSub, aSub
	};

	actionMemory mem = actionMemory.bSub;

	private enum operationMemory {
		last, notLast
	};

	operationMemory opMem = operationMemory.notLast;

	// M E T O D Y
	

	private boolean isOperation(Button b) {
		String label = b.getText();
		if (label.equals("+") || label.equals("-") || label.equals("/") || label.equals("*") || label.equals("sqrt") || label.equals("pow"))
			return true;
		return false;
	}
	
	
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

	@FXML
	public void sqrtClick(ActionEvent event) {
		txtFieldContent = "sqrt(" + txtFieldContent + ")";
		txtField.setText(txtFieldContent);
		mem = actionMemory.bSub;
	}

	@FXML
	public void powClick(ActionEvent event) {
		txtFieldContent = "pow(" + txtFieldContent + ",";
		txtField.setText(txtFieldContent);
		mem = actionMemory.bSub;
	}
	
	@FXML
	public void signClick(ActionEvent event) {
		if(txtFieldContent.charAt(0) == '-')
			txtFieldContent = txtFieldContent.substring(1, txtFieldContent.length());
		else if (txtFieldContent.equals("0"));
		else
			txtFieldContent ="-" + txtFieldContent;
		txtField.setText(txtFieldContent);		
	}

	@FXML
	public void submit(ActionEvent event) {

		txtFieldContent = api.compute(txtField.getText());

		if (api.forbidden(txtFieldContent)) {
			txtFieldContent = "0";
			api.showAlert();
		}

		else
			txtField.setText(txtFieldContent);
		mem = actionMemory.aSub;

	}

	@FXML
	public void clear(ActionEvent event) {

		txtFieldContent = "0";
		txtField.setText(txtFieldContent);

	}
}
