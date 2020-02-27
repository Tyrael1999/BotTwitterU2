/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoproyectoprogra.MotorClases;
import clasesPrincipales.InicioController;
import java.io.File;
import java.io.IOException;
import static java.lang.Long.parseLong;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import twitter4j.IDs;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
/**
 * Clase que permite la interacciòn entre la interfaz y twitter.com
 * @author javier 
 */
public class TwitterBot {
    
    private Twitter twitter = null;
    private ConfigurationBuilder cb = null;
    /**
     * Constructor de la clase, se le asignan las claves otorgadas por los permisos de la API
     * @throws TwitterException
     * @throws IOException 
     */
    public TwitterBot() throws TwitterException, IOException {
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("N5CWHGkORJjkxQPIS89oHNriE")
          .setOAuthConsumerSecret("DQEueL7CCFxkNEPSZHBZ3x7pKXrqob2NfVRM7L70tw72ErKTnH")
          .setOAuthAccessToken("4633891577-GsiIovKat4inogoa1o9ZbFbHJ5IIvrnEWMn1aTB")
          .setOAuthAccessTokenSecret("I3bD1Hk3QiIO7xNueP3Hq81LrSdfhapmqRJEaa5Er8xOv");
        twitter = new TwitterFactory(cb.build()).getInstance();
    }
    
    public void eliminarTweet(long id) throws TwitterException{
        twitter.destroyStatus(id);
    }
    /**
     * Metodo que publica un String en el muro de la cuenta ligada al bot
     * @param mensaje String que contiene el mensaje
     * @throws TwitterException 
     */
    public void tweetear(String mensaje) throws TwitterException{
        twitter.updateStatus(mensaje);
    }
    
    public void tweetear(String mensaje, File imagen) throws TwitterException{
        StatusUpdate status = new StatusUpdate(mensaje);
        status.setMedia(imagen);
        twitter.updateStatus(status);
    }
    /**
     * Metodo que mediante una id, sigue a un determinado usuario
     * @param id nombre de usuario
     * @throws TwitterException 
     */
    public void seguirUsuario(String id) throws TwitterException{
        twitter.createFriendship(id);
    }
    /**
     * Metodo que envia un mensaje privado a un usuario seguido
     * @param mensaje String que contiene el mensaje
     * @param id nombre del usuario seguido
     * @throws TwitterException 
     */
    public void enviarMensaje(String mensaje, String id) throws TwitterException{
        twitter.sendDirectMessage(id, mensaje);
    }
    /**
     * Metodo que permite obtener la timeline de la cuenta asignada a la aplicacion
     * @return lista de status de la timeline
     * @throws TwitterException
     */
    public List<Status> obtenerTimeline() throws TwitterException{
        return twitter.getHomeTimeline();
    }
    /**
     * Metodo que da "like" a un tweet determinado
     * @param id identificador unico del tweet a dar like
     * @throws TwitterException 
     */
    public void likeTweet(long id) throws TwitterException{
        twitter.createFavorite(id);
    }
    public void dislikeTweet(long id)throws TwitterException{
        twitter.destroyFavorite(id);
    }
    /**
     * Metodo que retweetea un tweet determinado
     * @param id identificador unico del tweet a retweetear
     * @throws TwitterException 
     */
    public void retweet(long id) throws TwitterException{
        twitter.retweetStatus(id);
    }
    public void unRetweet(long id) throws TwitterException{
        twitter.unRetweetStatus(id);
    }
    public long getOwnId()throws TwitterException{
        return twitter.getId();
    }
    public User getOwnUser()throws TwitterException{
        return twitter.showUser("javinMoraga");
    }
    public void buscarUsuario(char[] nombre)throws TwitterException{
        IDs ids = twitter.getFollowersIDs(-1);
        do {
            for (long id : ids.getIDs()) {      
                String ID = "followers ID #" + id;
                String[] firstname = ID.split("#");
                String first_Name = firstname[0];
                String Id = firstname[1];
                String Name = twitter.showUser(id).getName();
                String usuario = twitter.showUser(id).getScreenName();
                char[] cadenaUsuario = usuario.toCharArray();
                int encontrado = 0;
                for (int i = 0; i < cadenaUsuario.length; i++) {
                    if (nombre[0] == cadenaUsuario[i]) {
                        int l = i;
                        for (int j = 0; j < nombre.length; j++) {
                            encontrado = 0;
                            if (nombre[j] == cadenaUsuario[l]) {
                                encontrado = 1;
                            }
                            l++;
                        }
                        if (encontrado == 1) {
                            System.out.println("Usuario: "+usuario+"\n");
                        }
                    }
                }
            }
        } while (ids.hasNext());
    }
    /**
     * Metodo a trabajar
     * @param timeline
     * @param actividadReciente
     * @param tweets
     * @throws IOException 
     */
    public void responderTweet(GridPane timeline, ScrollPane actividadReciente, int tweets) throws IOException{   
        Query query;
        QueryResult result;
        List<Status> statuses;
        InicioController aux = new InicioController();
        try{
            query = new Query("@javinMoraga");
            result = twitter.search(query);
            char arroba = '@';
            for(Status status : result.getTweets()){
                System.out.println(""+status.getText());
                String[] words= status.getText().split(" ");
                for (int i = 0; i < words.length; i++) {
                    System.out.println(""+words[i]);
                    if (words[i].equals("#seguir")) {
                        if(words[i+1].charAt(0) == arroba){
                            seguirUsuario(words[i+1]);
                        }else{
                            System.out.println("seguir a "+status.getUser().getScreenName());
                            seguirUsuario(status.getUser().getScreenName());
                        }
                    }else if (words[i].equals("#gustar")) {
                        if (i+1<words.length && isNumeric(words[i+1])) {
                            long id = parseLong(words[i+1]);
                            likeTweet(id);
                        }else{
                            likeTweet(status.getId());
                        }
                    }else if (words[i].equals("#difundir")) {
                        if (i+1<words.length && isNumeric(words[i+1])) {
                            long id = parseLong(words[i+1]);
                            retweet(id);
                        }else{
                            retweet(status.getId());
                        }
                    }
                    
                }
            }
        }catch (TwitterException ex) {}/*
        try {
            query = new Query("@javinMoraga #gustar");
            result = twitter.search(query);
            for (Status status : result.getTweets()) {
                twitter.createFavorite(status.getId());
                statuses = obtenerTimeline();
                GridPane tweet = aux.crearTweet(0, statuses);
                aux.insertRows(1, timeline);
                timeline.add(tweet, 0, 0);
                tweets++;
                actividadReciente.setContent(timeline);
                System.out.println("@" + status.getUser().getScreenName() + " : " + status.getText());
            }
        } catch (TwitterException ex) {}
        try {
            query = new Query("@javinMoraga #difundir");
            result = twitter.search(query);
            for (Status status : result.getTweets()) {
                twitter.retweetStatus(status.getId());
                statuses = obtenerTimeline();
                GridPane tweet = aux.crearTweet(0, statuses);
                aux.insertRows(1, timeline);
                timeline.add(tweet, 0, 0);
                tweets++;
                actividadReciente.setContent(timeline);
                System.out.println("@" + status.getUser().getScreenName() + " : " + status.getText());
            }
        } catch (TwitterException ex) {}*/
    }
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}