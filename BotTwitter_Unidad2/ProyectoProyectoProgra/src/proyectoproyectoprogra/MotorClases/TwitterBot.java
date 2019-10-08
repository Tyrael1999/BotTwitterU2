/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoproyectoprogra.MotorClases;
import java.io.File;
import java.io.IOException;
import java.util.List;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
    public List<Status> retweetList(long id) throws TwitterException{
        return twitter.getRetweets(id);
    }
    public long getOwnId() throws TwitterException{
        return twitter.getId();
    }
}

