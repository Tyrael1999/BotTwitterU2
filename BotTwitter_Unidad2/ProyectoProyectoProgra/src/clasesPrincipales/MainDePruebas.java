/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPrincipales;

import java.io.IOException;
import proyectoproyectoprogra.MotorClases.TwitterBot;
import twitter4j.TwitterException;

/**
 *
 * @author javie
 */
public class MainDePruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException {
        // TODO code application logic here
        TwitterBot bot = new TwitterBot();
        bot.responderTweet();
    }
    
}
