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
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import proyectoproyectoprogra.MotorClases.TwitterBot;
import twitter4j.DirectMessageList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * @version 1.2
 * @author Mecho
 */
public class InicioController implements Initializable,CambiaEscenas {
    
    int cambio = 0;
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
    private ScrollPane actividadReciente;
    @FXML
    private Label maximo;
    @FXML
    private Label lim;
    @FXML
    private Button volverTweet;
    @FXML
    private TextArea seguirCuenta;
    @FXML
    private Button volverSeguir;
    @FXML
    private TextArea cuenta;
    private int MAX = 0;
    private User yo = null;
    private List<Status> statuses;
    @FXML
    private Button enviarArchivo;
    private GridPane timeline = new GridPane();
    private File selectedFile;
    private int flag = 0;
    private String textSplit;
    private int tweets = 9;
    @FXML
    private Pane ventana;
    @FXML
    private Button buscar;
    @FXML
    private Button enviarMensaje;
    @FXML
    private ListView<User> listaUsuarios;
    @FXML
    private Button recargar;
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
        //botones y labels que no usare
        maximo.setVisible(false);
        lim.setVisible(false);
        volverTweet.setVisible(false);
        volverSeguir.setVisible(false);
        buscar.setVisible(false);
        seguirCuenta.setVisible(false);
        cuenta.setVisible(false);
        enviarArchivo.setVisible(false);
        listaUsuarios.setVisible(false);
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
        tweet.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(18,18,18,18,false), Insets.EMPTY)));
        ColumnConstraints column2 = new ColumnConstraints(450);
        tweet.getColumnConstraints().add(column2);
        RowConstraints row1 = new RowConstraints(50);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        RowConstraints row3 = new RowConstraints(50);
        tweet.getRowConstraints().add(row1);
        tweet.getRowConstraints().add(row2);
        tweet.getRowConstraints().add(row3);
        HBox casilla1 = new HBox();
        VBox casilla3 = new VBox();
        HBox casilla2 = new HBox();
        TextFlow texto = new TextFlow();
        String[] content = statuses.get(j).getText().split(" ");
        for (String palabra : content) {
            Text word = new Text(palabra+" ");
            for (int i = 0; i < word.getText().length(); i++) { 
                if (word.getText().charAt(i)== '@' || word.getText().charAt(i)== '#') {
                    word.setFill(Color.BLUE);
                }
            }
            texto.getChildren().add(word);
        }
        Image imagen = new Image(statuses.get(j).getUser().get400x400ProfileImageURL());
        Circle cuadro = new Circle(25);
        ImagePattern pattern = new ImagePattern(imagen);
        cuadro.setFill(pattern);
        Label id = new Label();
        id.setText(statuses.get(j).getUser().getName()+"\n  @"+statuses.get(j).getUser().getScreenName());
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
            eliminar.setPrefSize(20, 20);
            casilla2.getChildren().addAll(like,retweet, eliminar);
        }else{
            casilla2.getChildren().addAll(like,retweet);
        }
        casilla1.getChildren().addAll(cuadro,id);
        casilla3.setFillWidth(true);
        casilla3.setAlignment(Pos.BOTTOM_CENTER);
        if (statuses.get(j).getMediaEntities().length!=0) {
            ImageView contentImg = ImageViewBuilder.create().image(new Image(statuses.get(j).getMediaEntities()[0].getMediaURL())).build();
            contentImg.setFitHeight(350);
            contentImg.setPreserveRatio(true);
            contentImg.setFitWidth(450);
            casilla3.getChildren().addAll(texto,contentImg);
        }else{
            casilla3.getChildren().addAll(texto);
        }
        casilla3.maxHeightProperty().bind(casilla3.heightProperty());
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
    public void setProfile(User user) throws IOException{
        ventana.getChildren().clear();
        Image banner = null;
        if (user.getProfileBanner1500x500URL() != null) {
            banner = new Image(user.getProfileBanner1500x500URL());
            ImageView fondo = ImageViewBuilder.create().image(banner).build();
            fondo.setFitWidth(280);
            fondo.setFitHeight(120);
            ventana.getChildren().add(fondo);
        }
        Circle perfil = new Circle(30);
        ImagePattern pattern = new ImagePattern(new Image(user.get400x400ProfileImageURL()));
        perfil.setFill(pattern);
        perfil.setLayoutX(30);
        perfil.setLayoutY(100);
        ventana.getChildren().add(perfil);
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
        timeline.setStyle("-fx-background-color: #15202b");
        try {
            TwitterBot bot = new TwitterBot();
            yo = bot.getOwnUser();
            setProfile(yo);
            statuses = bot.obtenerTimeline();
            ColumnConstraints column = new ColumnConstraints(450);
            timeline.getColumnConstraints().add(column);
            for (int i = 0; i < 10; i++) {
                RowConstraints row = new RowConstraints();
                row.setVgrow(Priority.ALWAYS);
                timeline.getRowConstraints().add(row);
            }
            for (int j = 0; j < 10; j++) {
                GridPane tweet = crearTweet(j, statuses);
                tweet.setScaleShape(true);
                tweet.maxHeightProperty().bind(tweet.heightProperty());
                tweet.setPadding(new Insets(5));
                timeline.add(tweet, 0, j);
            }
            tweets = 10;
            actividadReciente.setContent(timeline);
            actividadReciente.fitToWidthProperty().set(true);
            actividadReciente.fitToHeightProperty().set(true);
            actividadReciente.setFitToWidth(true);
            actividadReciente.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        } catch (TwitterException ex) {
            mostrarError(ex.getErrorMessage());
        }
    }

    private Button handleEliminar(int j, List<Status> statuses) throws TwitterException, IOException{
        TwitterBot bot = new TwitterBot();
        Button eliminar = new Button();
        eliminar.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-color:#8899a6");
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
        like.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-color:#8899a6");
        like.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    bot.likeTweet(statuses.get(j).getId());
                    like.setStyle("-fx-background-color:#DC143C; -fx-background-radius: 10 10 10 10");
                } catch (TwitterException ex) {
                    if (ex.getErrorMessage().equals("You have already favorited this status.")) {
                        try {
                            bot.dislikeTweet(statuses.get(j).getId());
                            like.setStyle("-fx-background-color:#8899a6; -fx-background-radius: 10 10 10 10");
                        } catch (TwitterException ex1) { try { mostrarError(ex1.getErrorMessage()); } catch (IOException ex2) { } }
                    }else{ try { mostrarError(ex.getErrorMessage()); } catch (IOException ex1) { } }
                }
            }
        });
        if (statuses.get(j).isFavorited()) {
            like.setStyle("-fx-background-color:#DC143C; -fx-background-radius: 10 10 10 10");
        }
        return like;
    }
    
    private Button handleNewRetweet(int j, List<Status> statuses) throws TwitterException, IOException{
        TwitterBot bot = new TwitterBot();
        Button retweet = new Button();
        retweet.setStyle("-fx-background-color:#8899a6; -fx-background-radius: 10 10 10 10");
        retweet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    bot.retweet(statuses.get(j).getId());
                    retweet.setStyle("-fx-background-color:#4169E1; -fx-background-radius: 10 10 10 10");
                } catch (TwitterException ex) {
                    if (ex.getErrorMessage().equals("You have already retweeted this Tweet.")) {
                        try {
                            bot.unRetweet(statuses.get(j).getId());
                            retweet.setStyle("-fx-background-color:#8899a6; -fx-background-radius: 10 10 10 10"); 
                        } catch (TwitterException ex1) { try { mostrarError(ex1.getErrorMessage()); } catch (IOException ex2) { } }
                    }else{ try { mostrarError(ex.getErrorMessage()); } catch (IOException ex1) { } }
                }
            }
        });
        if (statuses.get(j).isRetweeted()) {
            retweet.setStyle("-fx-background-color:#4169E1; -fx-background-radius: 10 10 10 10");
        }
        return retweet;
    }

    @FXML
    private void volver(ActionEvent event) throws TwitterException, IOException {
        tweet.clear();
        cuenta.clear();
        seguirCuenta.clear();
        maximo.setText("0");
        mostrarTweetear(false);
        mostrarSeguir(false);
        mostrarInicio(true);
        setProfile(yo);
    }
    
    @FXML
    private void limites(KeyEvent event) {
        maximo.setTextFill(Color.WHITE);
        enviar.setDisable(false); 
        maximo.setText("0");
        if (tweet.getText().length()>=0) {
            maximo.setText(""+(tweet.getText().length()));
        }
        if (tweet.getText().length()>280) {
            maximo.setTextFill(Color.RED);
            enviar.setDisable(true);
        }
    }

    @Override
    public void mostrarInicio(boolean valor) {
        twittear.setVisible(valor);
        Follow.setVisible(valor);
        Repli.setVisible(valor);
        recargar.setVisible(valor);
        enviarMensaje.setVisible(valor);
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
        listaUsuarios.getItems().clear();
        buscar.setVisible(valor);
        seguirCuenta.setVisible(valor);
        volverSeguir.setVisible(valor);
        listaUsuarios.setVisible(valor);
    }
    
    @FXML
    public void mostrarMensajesPrivados(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

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
    private void responderTweets(ActionEvent event) throws IOException {
        try {
            TwitterBot bot= new TwitterBot();
            bot.responderTweet(timeline, actividadReciente, tweets);
            bot.responderSpam();
        } catch (IOException ex) {
            mostrarError(ex.getMessage());
        } catch (TwitterException ex1) {}
    }

    @FXML
    private void buscar(ActionEvent event) throws TwitterException, IOException {
        String nombre = null;
        nombre = seguirCuenta.getText();
        if (nombre.length() == 0) {
            System.out.println("Ingrese texto");
        }else{
            TwitterBot bot = new TwitterBot();
            ResponseList<User> usuarios = bot.searchUser(nombre);
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
                    try {
                        setProfile(listaUsuarios.getSelectionModel().getSelectedItem());
                        Button seguir = new Button();
                        seguir.setStyle("-fx-background-color: #1da1f2");
                        seguir.setStyle("-fx-background-radius: 10 10 10 10");
                        boolean validar = bot.validarSeguidos(listaUsuarios.getSelectionModel().getSelectedItem().getId());
                        if (validar) {
                            seguir.setText("Dejar de seguir");
                        }else{
                            seguir.setText("Seguir");
                        }
                        seguir.setTextFill(Color.WHITE);
                        seguir.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (validar) {
                                    try {
                                        bot.dejarSeguir(listaUsuarios.getSelectionModel().getSelectedItem().getScreenName());
                                    } catch (TwitterException ex) {}
                                }else{
                                    try {
                                        bot.seguirUsuario(listaUsuarios.getSelectionModel().getSelectedItem().getScreenName());
                                    } catch (TwitterException ex) {}
                                }
                                seguir.setDisable(true);
                            }
                        });
                        seguir.setLayoutX(150);
                        seguir.setLayoutY(120);
                        ventana.getChildren().add(seguir);
                    } catch (IOException ex) {
                        try {
                            mostrarError(ex.getMessage());
                        } catch (IOException ex1) {}
                    } catch (TwitterException ex) {}
                }
            });
        }
    }

    @FXML
    private void colorBoton1(MouseEvent event) {
        buscar.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton2(MouseEvent event) {
        enviarArchivo.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton3(MouseEvent event) {
        enviar.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton4(MouseEvent event) {
        volverTweet.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton5(MouseEvent event) {
        Follow.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton6(MouseEvent event) {
        twittear.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton7(MouseEvent event) {
        Repli.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton8(MouseEvent event) {
        recargar.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton9(MouseEvent event) {
        volverSeguir.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }
    @FXML
    private void colorBoton10(MouseEvent event) {
        enviarMensaje.setBackground(new Background(new BackgroundFill(Color.web("#1a79b4"), new CornerRadii(10,10,10,10,false), Insets.EMPTY)));
    }

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        timeline.getChildren().clear();
        mostrarTimeline();
    }

}