package application.controller;

import application.model.JshellCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;


public class CalcController {

    @FXML
    private Button inBtn;

    @FXML
    private Button btn7;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtField;

    @FXML
    private Button operationBtn;

    private JshellCalc api = new JshellCalc();

    private enum actionMemory {bSub, aSub};
    actionMemory mem = actionMemory.bSub;


    @FXML
    public void click(ActionEvent event){
        Button clickedBtn = (Button) event.getSource();
        String btnLabel = clickedBtn.getText();
        if (mem == actionMemory.aSub){
            if (clickedBtn == operationBtn) mem=actionMemory.bSub;
            else {
                txtField.clear();
                mem = actionMemory.bSub;
            }
        }
        String temp = txtField.getText();
        txtField.setText(temp + btnLabel);
    }
}

