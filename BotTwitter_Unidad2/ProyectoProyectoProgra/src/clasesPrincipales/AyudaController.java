/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPrincipales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 * Clase controladora del pop-up "ayuda", controla todas las posibles excepciones a través de 
 * la interfaz, generando un mensaje determinado segun el tipo de excepcion
 * @author jamch
 */
public class AyudaController implements Initializable {

    @FXML
    private Label mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMensaje(String mensaje){
        switch(mensaje){
            case "Missing required parameter: status.":
                this.mensaje.setText("ERROR: El tweet enviado no tiene contenido.");
                break;
            case "Status is a duplicate.":
                this.mensaje.setText("ERROR: El tweet enviado es un duplicado.");
                break;
            case "Cannot find specified user.":
                this.mensaje.setText("ERROR: No se puede encontrar el nombre de usuario.");
                break;
            case "There was an error sending your message: Your message cannot be blank..":
                this.mensaje.setText("ERROR: No puedes enviar un mensaje sin contenido.");
                break;
            case "User not found.":
                this.mensaje.setText("ERROR: Usuario no encontrado.");
                break;
            case "User has been suspended.":
                this.mensaje.setText("ERROR: La cuenta de este usuario ha sido suspendida.");
                break;
            case "No status found with that ID.":
                this.mensaje.setText("ERROR: No existe un tweet con esa id.");
                break;
            case "Ingrese una id.":
                this.mensaje.setText("ERROR: Ingrese una id.");
                break;
            case "Ingrese una id valida.":
                this.mensaje.setText("ERROR: Ingrese una id valida.");
                break;
            default:
                this.mensaje.setText(mensaje);
        }
    }
}
