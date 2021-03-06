/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPrincipales;
import clasesAyuda.Mensajes;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import proyectoproyectoprogra.MotorClases.TwitterBot;
import twitter4j.TwitterException;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import twitter4j.DirectMessageList;
import twitter4j.User;

/**
 * FXML Controller class
 *
 * @author Mecho
 */
public class ChatController implements Initializable {

    @FXML
    private GridPane ventanaChat;
    @FXML
    private ScrollPane ventanaContactor;
    @FXML
    private TextArea mensaje;
    @FXML
    private Button enviar;
    @FXML
    private Label nombreUsuario;
    @FXML
    private TextField buscador;
    @FXML
    private Button buscar;
    @FXML
    private ImageView perfil;
    @FXML
    private ListView<User> listaUsuarios;
    
    @FXML
    private Label maxMensaje;
    @FXML
    private Label limMens;
    Mensajes textos = new Mensajes();
    private DirectMessageList listaMensajes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TwitterBot bot = new TwitterBot();
            listaMensajes = bot.obtenerMensajes();
            mensaje.setEditable(false);
            enviar.setDisable(true);
        } catch (TwitterException ex) {} catch (IOException ex) {}
    }    

    @FXML
    private void enviar(ActionEvent event) throws IOException {
        try {
            maxMensaje.setText("0");
            textos.setMensajePrivado(mensaje.getText());
            textos.setUsuario(nombreUsuario.getText());
            mensaje.clear();
            TwitterBot bot = new TwitterBot();
            bot.enviarMensaje(textos.getMensajePrivado(), textos.getUsuario());
        } catch (TwitterException ex) {
            System.out.println(ex.getErrorMessage());
        }
    }

    @FXML
    private void buscar(ActionEvent event) throws TwitterException, IOException { 
        listaUsuarios.getItems().clear();
        char[] nombre = null;
        nombre = (buscador.getText()).toLowerCase().toCharArray();
        if (nombre.length == 0) {
            System.out.println("Ingrese texto");
        }else{
            TwitterBot bot = new TwitterBot();
            List<User> usuarios = bot.buscarUsuario(nombre);
            if (!usuarios.isEmpty()) {  
                int i = 0;
                for (User usuario : usuarios) {
                    listaUsuarios.getItems().add(i, usuario);
                    i++;
                }
                listaUsuarios.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
                    @Override
                    public ListCell<User> call(ListView<User> param) {
                        ListCell<User> cell = new ListCell<User>() {
                            @Override
                            protected void updateItem(User usuario, boolean empty) {
                                super.updateItem(usuario, empty);
                                if(usuario != null) {
                                    setText(usuario.getScreenName());
                                } else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                });
                listaUsuarios.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mensaje.setEditable(true);
                        enviar.setDisable(false);
                        try {
                            mensajes(listaMensajes, bot.getOwnId(),listaUsuarios.getSelectionModel().getSelectedItem().getId());
                            ventanaContactor.setVvalue(1.0);
                        } catch (TwitterException ex) { System.out.println(ex.getErrorMessage());}
                        perfil.setImage(new Image(listaUsuarios.getSelectionModel().getSelectedItem().get400x400ProfileImageURL()));
                        nombreUsuario.setText(listaUsuarios.getSelectionModel().getSelectedItem().getScreenName());
                    }
                });
            }
        }
        buscador.clear();
        ventanaContactor.setVvalue(1.0);
    }
    
    private void mensajes(DirectMessageList mensajes, long id, long amigo){
        ventanaChat.getChildren().clear();
        ventanaChat.getRowConstraints().clear();
        int count = 0;
        for (int i = mensajes.size()-1 ; i >= 0  ; i--) {
            System.out.println(mensajes.get(i).getText());
            VBox burbuja = new VBox();
            TextFlow chat = new TextFlow();
            Text mensaje = new Text(mensajes.get(i).getText());
            mensaje.setFill(Color.WHITE);
            chat.getChildren().add(mensaje);
            burbuja.setAlignment(Pos.CENTER);
            burbuja.getChildren().add(chat);
            burbuja.setScaleShape(true);
            burbuja.setStyle("-fx-border-color:black; -fx-border-radius: 5 5 5 5;");
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            burbuja.setPadding(new Insets(10, 10, 10, 10));
            burbuja.maxHeightProperty().bind(burbuja.heightProperty());
            if (mensajes.get(i).getSenderId() == id && mensajes.get(i).getRecipientId() == amigo) {
                burbuja.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, new CornerRadii(5,5,5,5,false), Insets.EMPTY)));
                ventanaChat.getRowConstraints().add(row);
                ventanaChat.add(burbuja, 1, count);
            }else if (mensajes.get(i).getSenderId() == amigo){
                ventanaChat.getRowConstraints().add(row);
                burbuja.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(5,5,5,5,false), Insets.EMPTY)));
                ventanaChat.add(burbuja, 0, count);
            }
            ventanaContactor.setFitToWidth(true);
            count++;
        }
    }
    @FXML
    private void limites(KeyEvent event) {
        maxMensaje.setTextFill(Color.WHITE);
        maxMensaje.setText("0");
        if (mensaje.getText().length()>=0) {
            maxMensaje.setText(""+mensaje.getText().length());
        }
        if (mensaje.getText().length()>1000) {
            maxMensaje.setTextFill(Color.RED);
            enviar.setDisable(true);
        }else{
            enviar.setDisable(false);
        }
    }

    @FXML
    private void colorBoton1(MouseEvent event) {
        enviar.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton2(MouseEvent event) {
        buscar.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
}
