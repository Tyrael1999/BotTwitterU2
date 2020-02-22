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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import proyectoproyectoprogra.MotorClases.TwitterBot;
import twitter4j.Status;
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
    private Button twittear;
    @FXML
    private Button Follow;
    @FXML
    private Button Repli;
    @FXML
    private Button enviar;
    @FXML
    private TextArea tweet;
    
    Mensajes textos = new Mensajes();
    @FXML
    private Button botonCambiarRespuestas;
    @FXML
    private ScrollPane actividadReciente;
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
    
    private List<Status> statuses;
    @FXML
    private Button enviarArchivo;
    private GridPane timeline = new GridPane();
    private File selectedFile;
    private int flag = 0;
    private int tweets = 9;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarTimeline();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        tweet.setVisible(false);
        enviar.setVisible(false);
        tweet.setWrapText(true);
        cuenta.setWrapText(true);
        seguirCuenta.setWrapText(true);
        mensajePrivado.setWrapText(true);
        cambios.setWrapText(true);
        //botones y labels que no usare
        maximo.setVisible(false);
        maxMensaje.setVisible(false);
        limMens.setVisible(false);
        lim.setVisible(false);
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
        enviarArchivo.setVisible(false);
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

    public GridPane crearTweet(int j, List<Status> statuses) throws TwitterException, IOException{
        GridPane tweet = new GridPane();
        tweet.setPrefSize(460, 380);
        tweet.setMinSize(460, 380);
        tweet.setMaxSize(460, 380);
        ColumnConstraints column2 = new ColumnConstraints(450);
        tweet.getColumnConstraints().add(column2);
        RowConstraints row1 = new RowConstraints(50);
        RowConstraints row2 = new RowConstraints(280);
        RowConstraints row3 = new RowConstraints(50);
        tweet.getRowConstraints().add(row1);
        tweet.getRowConstraints().add(row2);
        tweet.getRowConstraints().add(row3);
        HBox casilla1 = new HBox();
        HBox casilla3 = new HBox();
        HBox casilla2 = new HBox();
        TextArea texto = new TextArea();
        ImageView foto = ImageViewBuilder.create().image(new Image(statuses.get(j).getUser().get400x400ProfileImageURL())).build();
        foto.setFitHeight(50);
        foto.setFitWidth(50);
        Label id = new Label();
        id.setText(statuses.get(j).getUser().getName()+"\n  @"+statuses.get(j).getUser().getScreenName());
        texto.setText(statuses.get(j).getText());
        texto.setEditable(false);
        texto.setWrapText(true);
        Button like = handleNewLike(j, statuses);
        Button retweet = handleNewRetweet(j, statuses);
        texto.setPrefSize(600, 600);
        ImageView corazon = ImageViewBuilder.create().image(new Image("https://icons-for-free.com/iconfiles/png/512/favorite+heart+love+valentines+day+icon-1320184635695804513.png")).build();
        corazon.setFitHeight(20);
        corazon.setFitWidth(20);
        like.setGraphic(corazon);
        like.setPrefSize(20, 20);
        ImageView flechas = ImageViewBuilder.create().image(new Image("http://simpleicon.com/wp-content/uploads/retweet.png")).build();
        flechas.setFitHeight(20);
        flechas.setFitWidth(20);
        retweet.setGraphic(flechas);
        retweet.setPrefSize(20, 20);
        casilla1.setSpacing(10);
        casilla2.setSpacing(10);
        casilla2.setPadding(new Insets(10));
        if (statuses.get(j).getUser().getScreenName().equals("javinMoraga")) {
            Button eliminar = handleEliminar(j, statuses);
            eliminar.setText("X");
            casilla2.getChildren().addAll(like,retweet, eliminar);
        }else{
            casilla2.getChildren().addAll(like,retweet);
        }
        casilla1.getChildren().addAll(foto,id);
        casilla3.setFillHeight(true);
        casilla3.setAlignment(Pos.BOTTOM_CENTER);
        if (statuses.get(j).getMediaEntities().length!=0) {
            ImageView contentImg = ImageViewBuilder.create().image(new Image(statuses.get(j).getMediaEntities()[0].getMediaURL())).build();
            contentImg.setFitHeight(200);
            contentImg.setFitWidth(200);
            casilla3.getChildren().addAll(texto,contentImg);
        }else{
            casilla3.getChildren().addAll(texto);
        }
        tweet.add(casilla1, 0, 0);
        tweet.add(casilla3, 0, 1);
        tweet.add(casilla2, 0, 2);
        tweet.setPadding(new Insets(5));
        tweet.setStyle("-fx-border-radius: 18 18 18 18;-fx-border-color:black");
        return tweet;
    }
    @FXML
    private void enviar(ActionEvent event) throws IOException {
        try {
            textos.setMensajeTweet(tweet.getText());
            mostrarInicio(true);
            mostrarTweetear(false);
            tweet.clear();
            TwitterBot bot = new TwitterBot();
            if(flag == 1){
                bot.tweetear(textos.getMensajeTweet(), selectedFile);
            }else{
                bot.tweetear(textos.getMensajeTweet());
            }
            statuses = bot.obtenerTimeline();
            GridPane tweet = crearTweet(0, statuses);
            insertRows(1, timeline);
            maximo.setText("0");
            timeline.add(tweet, 0, 0);
            tweets++;
            flag = 0;
            actividadReciente.setContent(timeline);
        } catch (TwitterException ex) {
            mostrarError(ex.getErrorMessage());
            maximo.setText("0");
        }   
    }
    
    public void insertRows(int count, GridPane timeline) {
        for (Node child : timeline.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(child);
            GridPane.setRowIndex(child, rowIndex == null ? count : count + rowIndex);
        }
    }
    private void deleteRow(GridPane grid, final int row) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(child);
            int r = rowIndex == null ? 0 : rowIndex;
            if (r > row) {
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                deleteNodes.add(child);
            }
        }
        grid.getChildren().removeAll(deleteNodes);
    }
    private void mostrarTimeline() throws IOException{
        try {
            TwitterBot bot = new TwitterBot();
            statuses = bot.obtenerTimeline();
            timeline.setPrefSize(statuses.size()*50, statuses.size()*300);
            timeline.setMinSize(statuses.size()*50, statuses.size()*300);
            timeline.setMaxSize(statuses.size()*50, statuses.size()*300);
            ColumnConstraints column = new ColumnConstraints(450);
            timeline.getColumnConstraints().add(column);
            for (int i = 0; i < 10; i++) {
                RowConstraints row = new RowConstraints(380);
                timeline.getRowConstraints().add(row);
            }
            for (int j = 0; j < 10; j++) {
                GridPane tweet = crearTweet(j, statuses);
                tweet.setPadding(new Insets(5));
                timeline.add(tweet, 0, j);
            }
            tweets = 10;
            actividadReciente.setContent(timeline);
            actividadReciente.fitToWidthProperty().set(true);
            actividadReciente.fitToHeightProperty().set(true);
            actividadReciente.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        } catch (TwitterException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private Button handleEliminar(int j, List<Status> statuses) throws TwitterException, IOException{
        TwitterBot bot = new TwitterBot();
        Button eliminar = new Button();
        eliminar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    bot.eliminarTweet(statuses.get(j).getId());
                    deleteRow(timeline, j);
                    actividadReciente.setContent(timeline);
                } catch (TwitterException ex) {
                    try {
                        mostrarError(ex.getErrorMessage());
                    } catch (IOException ex1) { }
                }
            }
        });
        return eliminar;
    }
    private Button handleNewLike(int j, List<Status> statuses) throws TwitterException, IOException{
        TwitterBot bot = new TwitterBot();
        Button like = new Button();
        like.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    bot.likeTweet(statuses.get(j).getId());
                    like.setStyle("-fx-background-color:#DC143C");
                } catch (TwitterException ex) {
                    if (ex.getErrorMessage().equals("You have already favorited this status.")) {
                        try {
                            bot.dislikeTweet(statuses.get(j).getId());
                            like.setStyle("-fx-background-color:");
                        } catch (TwitterException ex1) { try { mostrarError(ex1.getErrorMessage()); } catch (IOException ex2) { } }
                    }else{ try { mostrarError(ex.getErrorMessage()); } catch (IOException ex1) { } }
                }
            }
        });
        if (statuses.get(j).isFavorited()) {
            like.setStyle("-fx-background-color:#DC143C");
        }
        return like;
    }
    
    private Button handleNewRetweet(int j, List<Status> statuses) throws TwitterException, IOException{
        TwitterBot bot = new TwitterBot();
        Button retweet = new Button();
        retweet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    bot.retweet(statuses.get(j).getId());
                    retweet.setStyle("-fx-background-color:#4169E1");
                } catch (TwitterException ex) {
                    if (ex.getErrorMessage().equals("You have already retweeted this Tweet.")) {
                        try {
                            bot.unRetweet(statuses.get(j).getId());
                            retweet.setStyle("-fx-background-color:"); 
                        } catch (TwitterException ex1) { try { mostrarError(ex1.getErrorMessage()); } catch (IOException ex2) { } }
                    }else{ try { mostrarError(ex.getErrorMessage()); } catch (IOException ex1) { } }
                }
            }
        });
        if (statuses.get(j).isRetweeted()) {
            retweet.setStyle("-fx-background-color:#4169E1");
        }
        return retweet;
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
        maximo.setText("0");
        maxMensaje.setText("0");
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
        //responder mensajes automatico
    }

    @Override
    public void mostrarTweetear(boolean valor) {
        tweet.setVisible(valor);
        enviar.setVisible(valor);
        maximo.setVisible(valor);
        lim.setVisible(valor);
        volverTweet.setVisible(valor);
        enviarArchivo.setVisible(valor);
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
    private void subirArchivo(ActionEvent event) {  
        System.out.println("subir archivo..");
        FileChooser fc = new FileChooser();
        String ruta;
        selectedFile = fc.showOpenDialog(null);
        flag = 1;
    }   
    @FXML
    private void responderTweets(ActionEvent event) throws TwitterException, IOException {
        TwitterBot bot= new TwitterBot();
        bot.responderTweet(timeline, actividadReciente, tweets);
    }
}