/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPrincipales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Mecho
 */
public class ChatController implements Initializable {

    @FXML
    private ScrollPane ventanaChat;
    @FXML
    private ScrollPane ventanaContactor;
    @FXML
    private TextArea mensaje;
    @FXML
    private Button enviar;
    @FXML
    private Label nombreUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enviar(ActionEvent event) {
        String mensajeEnviado;
        mensajeEnviado = mensaje.getText();
    }
    
}
