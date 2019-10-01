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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import clasesAyuda.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proyectoproyectoprogra.MotorClases.TwitterBot;
import twitter4j.TwitterException;

/**
 * @version 1.2
 * @author Mecho
 */
public class InicioController implements Initializable,CambiaEscenas {
    
    int cambio = 0;
    @FXML
    private ImageView Fondo;
    @FXML
    private ImageView perfil;
    @FXML
    private Label Respuesta;
    @FXML
    private Button twittear;
    @FXML
    private Button Follow;
    @FXML
    private Button Repli;
    @FXML
    private TextArea cambioRespuesta;
    @FXML
    private Button confirmar;
    @FXML
    private Button enviar;
    @FXML
    private TextArea tweet;
    
    Mensajes textos = new Mensajes();
    @FXML
    private Button change;
    @FXML
    private Button botonCambiarRespuestas;
    @FXML
    private Pane actividadReciente;
    @FXML
    private Pane planoRespuestas;
    @FXML
    private Button cambiarMensajeAutomatico;
    @FXML
    private Button cambiarMensajePrivado;
    @FXML
    private Button guardarMensajes;
    @FXML
    private Button predeterminado;
    @FXML
    private TextArea cambios;
    @FXML
    private Button volver;
    @FXML
    private Text encabezado1;
    @FXML
    private Label mensaje1;
    @FXML
    private Label mensaje2;
    @FXML
    private Text encabezado2;
    @FXML
    private Label maximo;
    @FXML
    private Label lim;
    @FXML
    private Button volverTweet;
    @FXML
    private TextArea mensajePrivado;
    @FXML
    private Button enviarMensajePrivado;
    @FXML
    private Button enviar2;
    @FXML
    private Button volverMensaje;
    @FXML
    private TextArea seguirCuenta;
    @FXML
    private Button seguir;
    @FXML
    private Button volverSeguir;
    @FXML
    private TextArea cuenta;
    @FXML
    private Label maxMensaje;
    @FXML
    private Label limMens;
    
    private int MAX = 0;
    @FXML
    private Button retweet;
    @FXML
    private Button like;
    @FXML
    private TextArea idReTweet;
    @FXML
    private TextArea idLike;
    @FXML
    private Button volverLike;
    @FXML
    private Button volverRetweet;
    @FXML
    private Button Likear;
    @FXML
    private Button Retwetear;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tweet.setVisible(false);
        enviar.setVisible(false);
        tweet.setWrapText(true);
        cuenta.setWrapText(true);
        seguirCuenta.setWrapText(true);
        mensajePrivado.setWrapText(true);
        cambios.setWrapText(true);
        cambioRespuesta.setWrapText(true);
        //botones y labels que no usare
        Respuesta.setVisible(false);
        change.setVisible(false);
        cambioRespuesta.setVisible(false);
        confirmar.setVisible(false);
        maximo.setVisible(false);
        maxMensaje.setVisible(false);
        limMens.setVisible(false);
        lim.setVisible(false);
        Respuesta.setText(textos.getMensajePredeterminado());
        //todo el plano que tiene las opciones para cambiar respuestas
        planoRespuestas.setVisible(false);
        mensaje1.setVisible(false);
        mensaje2.setVisible(false); 
        encabezado1.setVisible(false);
        encabezado2.setVisible(false);
        volver.setVisible(false);
        cambios.setVisible(false);
        predeterminado.setVisible(false);
        guardarMensajes.setVisible(false);
        cambiarMensajePrivado.setVisible(false);
        cambiarMensajeAutomatico.setVisible(false);
        volverTweet.setVisible(false);  
        mensajePrivado.setVisible(false);
        enviar2.setVisible(false);
        volverMensaje.setVisible(false);
        volverSeguir.setVisible(false);
        seguir.setVisible(false);
        seguirCuenta.setVisible(false);
        cuenta.setVisible(false);
        Retwetear.setVisible(false);
        Likear.setVisible(false);
        volverRetweet.setVisible(false);
        volverLike.setVisible(false);
        idReTweet.setVisible(false);
        idLike.setVisible(false);
    }    

    @FXML
    private void handleChangeText(ActionEvent event) {
        cambioRespuesta.setVisible(true);
        confirmar.setVisible(true);
        Respuesta.setVisible(false);
        
    }

    @FXML
    private void handleTwittear(ActionEvent event) {
        MAX = 280;
        mostrarInicio(false);
        mostrarTweetear(true);
    }

    @FXML
    private void handleFollow(ActionEvent event) {
        mostrarInicio(false);
        mostrarSeguir(true);
    }

    @FXML
    private void enviar(ActionEvent event) throws IOException {
        try {
            textos.setMensajeTweet(tweet.getText());
            mostrarInicio(true);
            mostrarTweetear(false);
            tweet.clear();
            TwitterBot bot = new TwitterBot();
            bot.Tweetear(textos.getMensajeTweet());
            maximo.setText("0");
        } catch (TwitterException ex) {
            mostrarError(ex.getErrorMessage());
            maximo.setText("0");
        }
    }

    @FXML
    private void abrirCambiaRespuesta(ActionEvent event) {
        mostrarMensajesPredeterminados(true);
        mostrarInicio(false);
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        Mensajes men = new Mensajes();
        cambios.setVisible(false);
        if(cambio == 1){
            men.setMensajeReacciones(cambios.getText());
            mensaje1.setText(men.getMensajeReacciones());
        }
        if(cambio == 2){
            men.setMensajePrivado(cambios.getText());
            mensaje2.setText(men.getMensajePrivado());
        }
    }

    @FXML
    private void volverPredeterminado(ActionEvent event) {
    }

    @FXML
    private void cerrarCambiaRespuesta(ActionEvent event) {
        tweet.clear();
        cuenta.clear();
        seguirCuenta.clear();
        mensajePrivado.clear();
        cambios.clear();
        cambioRespuesta.clear();
        idLike.clear();
        idReTweet.clear();
        maximo.setText("0");
        maxMensaje.setText("0");
        mostrarLike(false);
        mostrarRetweet(false);
        mostrarTweetear(false);
        mostrarSeguir(false);
        mostrarMensajesPrivados(false);
        mostrarMensajesPredeterminados(false);
        mostrarInicio(true);
    }

    @FXML
    private void cambiarReacciones(ActionEvent event) {
        cambio = 1;
        cambios.setText(null);
        cambios.setVisible(true);
        
    }

    @FXML
    private void cambiarPrivado(ActionEvent event) {
        cambio = 2;
        cambios.setText(null);
        cambios.setVisible(true);
    }
    
    @FXML
    private void limites(KeyEvent event) {
        maximo.setTextFill(Color.BLACK);
        maxMensaje.setTextFill(Color.BLACK);    
        enviar2.setDisable(false); 
        enviar.setDisable(false); 
        maximo.setText("0");
        maxMensaje.setText("0");
        if (tweet.getText().length()>=0 || mensajePrivado.getText().length()>=0) {
            maximo.setText(""+(tweet.getText().length()));
            maxMensaje.setText(""+mensajePrivado.getText().length());
        }
        if (tweet.getText().length()>MAX || mensajePrivado.getText().length()>MAX) {
            maxMensaje.setTextFill(Color.RED);
            maximo.setTextFill(Color.RED);
            enviar.setDisable(true);
            enviar2.setDisable(true);
        }
    }

    @FXML
    private void escribirMensajePriv(ActionEvent event) {
        MAX = 10000;
        mostrarInicio(false);
        mostrarMensajesPrivados(true);
    }

    @FXML
    private void enviarMensajePrivado(ActionEvent event) throws IOException {
        try {
            mostrarInicio(true);
            mostrarMensajesPrivados(false);
            textos.setMensajePrivado(mensajePrivado.getText());
            textos.setUsuario(cuenta.getText());
            mensajePrivado.clear();
            cuenta.clear();
            TwitterBot bot = new TwitterBot();
            bot.enviarMensaje(textos.getMensajePrivado(), textos.getUsuario());
        } catch (TwitterException ex) {
            mostrarError(ex.getErrorMessage());
        }
    }

    @FXML
    private void seguir(ActionEvent event) throws IOException{
        try{
            mostrarInicio(true);
            mostrarSeguir(false);
            textos.setUsuarioFollow(seguirCuenta.getText());
            seguirCuenta.clear();
            TwitterBot bot = new TwitterBot();
            bot.seguirUsuario(textos.getUsuarioFollow());
        }catch (TwitterException ex) {
            mostrarError(ex.getErrorMessage());
        }
    }

    @Override
    public void mostrarInicio(boolean valor) {
        twittear.setVisible(valor);
        Follow.setVisible(valor);
        Repli.setVisible(valor);
        enviarMensajePrivado.setVisible(valor);
        botonCambiarRespuestas.setVisible(valor);
        like.setVisible(valor);
        retweet.setVisible(valor);
    }

    @Override
    public void mostrarTweetear(boolean valor) {
        tweet.setVisible(valor);
        enviar.setVisible(valor);
        maximo.setVisible(valor);
        lim.setVisible(valor);
        volverTweet.setVisible(valor);
    }

    @Override
    public void mostrarSeguir(boolean valor) {
        seguir.setVisible(valor);
        seguirCuenta.setVisible(valor);
        volverSeguir.setVisible(valor);
    }

    @Override
    public void mostrarMensajesPrivados(boolean valor) {
        mensajePrivado.setVisible(valor);
        enviar2.setVisible(valor);
        volverMensaje.setVisible(valor);
        cuenta.setVisible(valor);
        maxMensaje.setVisible(valor);
        limMens.setVisible(valor);
    }

    @Override
    public void mostrarMensajesPredeterminados(boolean valor) {
        planoRespuestas.setVisible(valor);
        mensaje1.setVisible(valor);
        mensaje2.setVisible(valor); 
        encabezado1.setVisible(valor);
        encabezado2.setVisible(valor);
        volver.setVisible(valor);
        predeterminado.setVisible(valor);
        guardarMensajes.setVisible(valor);
        cambiarMensajePrivado.setVisible(valor);
        cambiarMensajeAutomatico.setVisible(valor);
    }

    @Override
    public void mostrarLike(boolean valor) {
        idLike.setVisible(valor);
        Likear.setVisible(valor);
        volverLike.setVisible(valor);
    }

    @Override
    public void mostrarRetweet(boolean valor) {
        idReTweet.setVisible(valor);
        Retwetear.setVisible(valor);
        volverRetweet.setVisible(valor);
    }

    private void mostrarError(String error) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        URL location = AyudaController.class.getResource("Ayuda.fxml");
        loader.setLocation(location);
        AnchorPane ap = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        AyudaController controller = loader.getController();
        controller.setMensaje(error);
        System.out.println(error);
        stage.show();
    }
    @FXML
    private void confirm(ActionEvent event) {
    }

    @FXML
    private void handleRetweet(ActionEvent event) {
        mostrarInicio(false);
        mostrarRetweet(true);
    }

    @FXML
    private void handelLike(ActionEvent event) {
        mostrarInicio(false);
        mostrarLike(true);
    }

    @FXML
    private void like(ActionEvent event) throws IOException {
        try{
            mostrarInicio(true);
            mostrarLike(false);
            textos.setLike(idLike.getText());
            idLike.clear();
            if (!"".equals(textos.getLike())) {
                if (textos.getLike().matches("[0-9]+")) {
                    TwitterBot bot = new TwitterBot();
                    bot.likeTweet(Long.parseLong(textos.getLike()));
                }else{
                    mostrarError("Ingrese una id valida.");
                }
            }else{
                mostrarError("Ingrese una id.");
            }
        }catch(TwitterException ex){
            mostrarError(ex.getErrorMessage());
        }
    }

    @FXML
    private void retweet(ActionEvent event) throws IOException {
        try{
            mostrarInicio(true);
            mostrarRetweet(false);
            textos.setRetweet(idReTweet.getText());
            idReTweet.clear();
            if (!"".equals(textos.getRetweet())) {
                if (textos.getRetweet().matches("[0-9]+")) {
                    TwitterBot bot = new TwitterBot();
                    bot.retweet(Long.parseLong(textos.getRetweet()));
                }else{
                    mostrarError("Ingrese una id valida.");
                }
            }else{
                mostrarError("Ingrese una id.");
            }
        }catch(TwitterException ex){
            mostrarError(ex.getErrorMessage());
        }
    }
}
